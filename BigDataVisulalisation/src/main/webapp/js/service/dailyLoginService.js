//品牌服务
app.service("dailyLoginService",function($http){
    this.findAllByMonth=function(){
        return $http.post("dailyLoginAll.do");
    }




});