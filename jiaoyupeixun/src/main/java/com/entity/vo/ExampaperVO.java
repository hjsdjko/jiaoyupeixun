package com.entity.vo;

import com.entity.ExampaperEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 试卷表
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("exampaper")
public class ExampaperVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 试卷名称
     */

    @TableField(value = "exampaper_name")
    private String exampaperName;


    /**
     * 测试时长(分钟)
     */

    @TableField(value = "exampaper_date")
    private Integer exampaperDate;


    /**
     * 试卷总分
     */

    @TableField(value = "exampaper_myscore")
    private Integer exampaperMyscore;


    /**
     * 教师
     */

    @TableField(value = "jiaoshi_id")
    private Integer jiaoshiId;


    /**
     * 试卷状态
     */

    @TableField(value = "exampaper_types")
    private Integer exampaperTypes;


    /**
     * 创建时间 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：试卷名称
	 */
    public String getExampaperName() {
        return exampaperName;
    }


    /**
	 * 获取：试卷名称
	 */

    public void setExampaperName(String exampaperName) {
        this.exampaperName = exampaperName;
    }
    /**
	 * 设置：测试时长(分钟)
	 */
    public Integer getExampaperDate() {
        return exampaperDate;
    }


    /**
	 * 获取：测试时长(分钟)
	 */

    public void setExampaperDate(Integer exampaperDate) {
        this.exampaperDate = exampaperDate;
    }
    /**
	 * 设置：试卷总分
	 */
    public Integer getExampaperMyscore() {
        return exampaperMyscore;
    }


    /**
	 * 获取：试卷总分
	 */

    public void setExampaperMyscore(Integer exampaperMyscore) {
        this.exampaperMyscore = exampaperMyscore;
    }
    /**
	 * 设置：教师
	 */
    public Integer getJiaoshiId() {
        return jiaoshiId;
    }


    /**
	 * 获取：教师
	 */

    public void setJiaoshiId(Integer jiaoshiId) {
        this.jiaoshiId = jiaoshiId;
    }
    /**
	 * 设置：试卷状态
	 */
    public Integer getExampaperTypes() {
        return exampaperTypes;
    }


    /**
	 * 获取：试卷状态
	 */

    public void setExampaperTypes(Integer exampaperTypes) {
        this.exampaperTypes = exampaperTypes;
    }
    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
