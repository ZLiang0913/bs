//品牌服务
app.service("dailyActivityService",function($http){
    this.findAll=function(){
        return $http.post("dailyActivity.do");
    }




});