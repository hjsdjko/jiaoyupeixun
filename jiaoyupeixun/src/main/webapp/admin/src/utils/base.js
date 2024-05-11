const base = {
    get() {
        return {
            url : "http://localhost:8080/jiaoyupeixun/",
            name: "jiaoyupeixun",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jiaoyupeixun/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "教育培训微信小程序"
        } 
    }
}
export default base
