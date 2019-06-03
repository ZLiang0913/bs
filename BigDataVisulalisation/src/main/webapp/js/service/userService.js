//服务层
app.service('userService',function($http){
	    	

	//增加 
	this.add=function(entity,smscode){
		return  $http.post('/add.do?smscode='+smscode,entity );
	}
	
	//发送验证码
	this.sendCode=function(phone){

		return $http.get("/sendCode.do?phone="+phone);
	}

	
	
});
