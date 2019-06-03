app.controller("baseController",function($scope){
	//分页控件配置 
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 10,
		itemsPerPage : 10,
		perPageOptions : [ 10, 20, 30, 40, 50 ],
		onChange : function() {
			$scope.reloadList();
		}
	};
	// 重新加载列表数据
	$scope.reloadList = function() {
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}

	$scope.selectIds=[];//复选框中选中的id
	$scope.updateSelection=function($event,id){//$event此处相当于input中的代码
		if($event.target.checked){//target指源，用户勾选复选框
			$scope.selectIds.push(id);
		}else{
			var index=$scope.selectIds.indexOf(id);//查找id在selectIds中的位置
			$scope.selectIds.splice(index,1);//第一个参数：该指定id的下标，第二个参数：删除的个数
		}
	}
	
	//提取json字符串数据中某个属性，返回拼接字符串，用逗号隔开
	$scope.jsonToString=function(jsonString,key){
		var json=JSON.parse(jsonString);
		var value="";
		for(var i=0;i<json.length;i++){
			if(i>0) value+=",";
			
			value += json[i][key];
		}
		return value;
	}
})