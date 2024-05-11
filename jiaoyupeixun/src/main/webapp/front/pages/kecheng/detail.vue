<template>
<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback"
			  :bottom="100">
		<view class="container">
			<view>
				<swiper class="swiper" :indicator-dots='".swiper-pagination"==null?false:true'
					:autoplay='autoplaySwiper' :circular='true' indicator-active-color='#000000'
					indicator-color='rgba(0, 0, 0, .3)' :duration='1000' :interval='intervalSwiper'
					:vertical='"horizontal"=="horizontal"?false:true'>
					<swiper-item v-for="(swiper,index) in swiperList" :key="index">
						<image mode="aspectFill" :src="swiper"></image>
					</swiper-item>
				</swiper>
			</view>
			<view class="detail-content">
				<view class="price detail-list-item priceFavor"
					:style='{"boxShadow":"0 0 2rpx rgba(0,0,0,.8)","margin":"0 ","borderColor":"#fff","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","borderStyle":"solid","height":"68rpx"}'>
			<!-- 名字-->
						<view :style='{"textAlign":"left","fontSize":"45upx","color":"black"}'>{{detail.kechengName}}
						</view>
				</view>
   	 <view style="margin-top: 10rpx;"></view>

					<view class="detail-list-item"
						  :style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
						<view class="lable"
							  :style='{"width":"auto","fontSize":"28rpx","color":"#f06f4f","textAlign":"left"}'>
							网课类型：</view>
						<view class="text" :style='{"color":"#f06f4f","textAlign":"center","fontSize":"24rpx"}'>
                            {{detail.kechengValue}}</view>
					</view>

                    <view class="detail-list-item"
                          :style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'
                          @tap="download(detail.kechengFile)">
                        <view class="lable"
                              :style='{"width":"auto","fontSize":"28rpx","color":"#f06f4f","textAlign":"left"}'>
							网课文件：</view>
                        <view class="text" :style='{"color":"#f06f4f","textAlign":"center","fontSize":"24rpx"}'>
                            <view class="text" :style='{"color":"#333","textAlign":"left","fontSize":"28rpx"}'>下载</view>
                        </view>
                    </view>

					<view class="detail-list-item"
						  :style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
						<view class="lable"
							  :style='{"width":"auto","fontSize":"28rpx","color":"#f06f4f","textAlign":"left"}'>
							是否观看：</view>
						<view class="text" :style='{"color":"#f06f4f","textAlign":"center","fontSize":"24rpx"}'>
                            {{guankanjilu}}</view>
					</view>

			</view>
<!-- 视频 -->
			<view style="text-align: center;padding: 20upx;box-sizing: border-box;background: #FFFFFF;"
				  :style="{margin:'0 60rpx 0 60rpx',boxShadow:'0 0 4rpx 0 #006633 insta'}">
				<view class="uni-padding-wrap uni-common-mt">
					<view>
						<video id="myVideo" style="preload:auto" muted :src="detail.kechengVideo" controls></video>
					</view>
				</view>
			</view>
			<view class="time-content"
				:style='{"boxShadow":"0 0 2rpx rgba(0,0,0,.8)","margin":"0","borderColor":"#fff","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","borderStyle":"solid","height":"auto"}'>
				<view class="header" :style='{"color":"#333","fontSize":"28rpx"}'>
					简介/详情
				</view>
				<view class="content" style="line-height: 50upx;letter-spacing: 5upx;"
					:style='{"color":"#333","fontSize":"28rpx"}'>
					<rich-text :nodes="detail.kechengContent"></rich-text>
				</view>
			</view>



            <view class="bottom-content cu-bar bg-white tabbar border shop" v-if="guankanjilu == '未观看'">
                <view style="text-align: left;display: flex;">
                </view>
                <view style="text-align: right;">
					<button @tap="yiguankan" style="margin-right: 10upx;" class="cu-btn bg-red round shadow-blur"
                            :style="{width:'auto',borderRadius:'8rpx',height:'80rpx',fontSize:'28rpx',color:'#fff',backgroundColor:btnColor[3],borderColor:btnColor[3]}">标记为已观看</button>
                </view>
            </view>
		</view>

</mescroll-uni>


</template>

<script>
	export default {
		data() {
			return {
				autoplaySwiper: false ? true : false,
				intervalSwiper: false ? $template2.front.base.swiper.autoplay.delay : 5000,
				btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9',
					'#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'
				],
				id: '',
				detail: {},//本页面数据对象
				swiperList: [],//主图
				commentList: [],//评论留言数据集合
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				guankanjilu:"未观看",
				hasNext: true,
				user: {},//登录用户信息
				collectionFlag: 0,//收藏
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			this.id = options.id;
		},
		async onShow(options) {
			let _this = this
			// 渲染数据
			_this.init();
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await _this.$api.session(table);
			_this.user = res.data;
			_this.btnColor = _this.btnColor.sort(() => {
				return (0.5 - Math.random());
			});
			
			let resAA = await _this.$api.page('guankanjilu', {
				kechengId:_this.id,
				yonghuId:_this.user.id
			});
			if(resAA.data.list.length>0){
				_this.guankanjilu = "已观看"
			}
			
			let cleanType = uni.getStorageSync('pingluenStateState')
			if (cleanType) {
				uni.removeStorageSync('pingluenStateState')
				_this.mescroll.num = 1
				_this.upCallback(_this.mescroll)
			}
		},
		destroyed: function() {
			//window.clearInterval(this.inter);
		},
		methods: {
		    //下载
            download(url) {
                let _this = this;
                uni.downloadFile({
                    url: url,
                    success: (res) => {
                        if (res.statusCode === 200) {
                            _this.$utils.msg('下载成功');
                            window.open(url);
                        }
                    }
                });
            },
			// 获取详情
			async init() {
				let res = await this.$api.info('kecheng', this.id);
				this.detail = res.data;
				// 主图
				this.swiperList = this.detail.kechengPhoto ? this.detail.kechengPhoto.split(",") : [];
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},

			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				mescroll.resetUpScroll()
			},

			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				this.mescroll.endByPage(this.mescroll.num, 10);
			},
			async yiguankan(){
				let _this = this
				let data = {
					kechengId:_this.id,
					yonghuId:_this.user.id
				}
				await _this.$api.save('guankanjilu', data);
				_this.$utils.msg("标记成功")
				_this.guankanjilu = "已观看"
			}
		}
	}
</script>

<style lang="scss">
	.container {
		// padding-bottom: 80upx;
	}

	.container:after {
		position: fixed;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		content: '';
		background-attachment: fixed;
		background-size: cover;
		background-position: center;
	}

	.swiper {
		width: 100%;
		height: 450upx;

		image {
			width: 100%;
		}
	}

	.detail-content {
		font-size: 28upx;
		color: #888888;
		// padding: 10upx 20upx;
		background: #FFFFFF;
		line-height: 45upx;

		.name {
			font-size: 38rpx;
			font-weight: blod;
			// margin-bottom: 20upx;
			padding-top: 20upx;
		}

		.price {
			font-size: 33rpx;
		}
	}

	.time-content {
		background: #FFFFFF;
		padding: 30upx;
		font-size: 30upx;
		border-radius: 20upx;
		margin-top: 20upx;

		.header {
			font-size: 33upx;
			margin-bottom: 20upx;
		}
	}

	.bottom-content {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
	}

	.comoment-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-bottom: 12upx;

		.btn-comment-content {
			color: #DD514C;
			font-weight: bold;
			padding: 0 12upx;
		}
	}

	.comment-item {
		margin-bottom: 10upx;
		border-bottom: 1px solid #EEEEEE;
		padding-bottom: 20upx;

		.content {
			margin-left: 20upx;
			margin-top: 20upx;
		}
	}

	.icon {
		width: 50upx;
		height: 50upx;
	}

	.popup-content {
		.popup-form {
			background: #FFFFFF;
			margin-top: 20upx;
		}

		.btn-content {
			text-align: right;
			background: #FFFFFF;

			button {
				margin-left: 20upx;
				margin-right: 20upx;
				margin-bottom: 20upx;
			}
		}
	}

	.detail-list-item {
		padding: 0 24upx;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		height: 68upx;
		border-bottom: 2upx solid #efefef;
		background-color: #fff;

		.lable {
			font-size: 28upx;
			color: #000;
		}

		.text {
			flex: 1;
			font-size: 24upx;
			color: #FF00FF;
			text-align: left;
		}

		&.favor {
			.el-icon-star-on {
				color: inherit;
				font-size: inherit;
			}
		}
	}

	.detail-content .shop-title {
		padding: 20upx 24upx;
		box-sizing: border-box;
		line-height: 1.4;
	}

	.priceFavor {
		height: 96upx !important;
	}

	.priceFavor .text {
		font-size: 64upx !important;
		color: red !important;
	}

	.priceFavor .icon {
		font-size: 56upx !important;
		text-align: right !important;
		color: red !important;
	}

	//订座样式
	.seat-list {
		display: flex;
		align-items: center;
		flex-wrap: wrap;
		background: #FFFFFF;
		margin: 20upx;
		border-radius: 20upx;
		padding: 20upx;
		font-size: 30upx;

		.seat-item {
			width: 33.33%;
			display: flex;
			align-items: center;
			flex-direction: column;
			margin-bottom: 20upx;

			.seat-icon {
				width: 50upx;
				height: 50upx;
				margin-bottom: 10upx;
			}
		}
	}
</style>
