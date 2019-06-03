//品牌控制层
app.controller("dailyLoginController", function($scope, $http,dailyLoginService , $controller) {


	$controller("baseController", {
		$scope : $scope
	});// 控制器继承


    $scope.legend = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
    $scope.data ;
    $scope.item ;
    $scope.itemCat1List=[];
    $scope.selectItemCat1List=function(){
        $http({
            method:'POST',
            url:'dailyLoginAll.do',
        }).then(function successCallback (rs){
            console.log( rs.data)
            var itemList = rs.data;
            $scope.data =[];
            $scope.itemCat1List=[];
            for (var i = 0; i < itemList.x.length; i++) {
                $scope.itemCat1List.push({"id":itemList.x[i],"text":$scope.legend[parseInt(itemList.x[i],10)-1]});
                $scope.data.push({"value":parseInt(itemList.y[i]),"name":$scope.legend[parseInt(itemList.x[i],10)-1]});
            }
            console.log( $scope.itemCat1List);

            console.log( $scope.data);
            console.log($scope.data.length);
        });
    }
    //$scope.common(04);
    //$scope.item ={"loginDate":[20170401,20170402,20170403,20170404],"count":[550,519,563,889]}
    //公共方法
    $scope.common=function(index){
        $http({
            method:'POST',
            url:'dailyLogin.do?id='+index,
        }).then(function successCallback (rs){
            console.log( rs.data)
            var myObj = rs.data;


            $scope.item ={"loginDate":[],"count":[]};
            for (var i = 0; i < myObj.length; i++) {
                $scope.item["loginDate"].push(myObj[i]["loginDate"]);
                $scope.item["count"].push(myObj[i]["count"]);

            }
            console.log(typeof $scope.item);
            console.log($scope.item);

        });
    }


    /*//读取指定月份
    $scope.$watch("entity.goods.category1Id",function(newValue,oldValue){

        $scope.common(newValue);
    });*/
    //读取指定月份
    $scope.$watch("entity.goods.category1Id",function(newValue,oldValue){
        //alert(newValue);
        console.log(typeof newValue);
        if(newValue==undefined){
            $scope.common("04");
        }else {
            $scope.common(newValue.toString());
        }
    });
});

app.directive('sexbar', function() {
    return {
        scope: {
            id: "@",
            legend: "=",
            //item: "=",
            data: "@"
        },
        restrict: 'E',
        template: '<div  style="float:left;width:500px;height:500px;"></div>',
        replace: true,
        link: function($scope, element, attrs, controller) {
            var a = [];
            $scope.data=[];

            //var scaleData = [{"value":"22763","name":"四月"},{"value":"145","name":"五月"}];
            $scope.scaleData = [];


            var data = [];

            option = {
                color:['#ffa800','#00cfff','#ff3000','#ffe000','#006ced', '#ff5b00' ],
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                backgroundColor: '#04243E',
                title: {
                    text: '每月登录人数比',
                    left: 'center',
                    top: 20,
                    textStyle: {
                        color: '#ccc'
                    }
                },

                tooltip: {
                    show: false
                },
                legend: {
                    show: false
                },
                toolbox: {
                    show: false
                },
                series : [
                    {

                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '50%'],
                        data:data,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        itemStyle: {
                            normal: {
                                label:{
                                    show: true,
                                    position:'inside',
                                    fontSize: 16,
                                    formatter: '{b} : {c} ({d}%)'
                                }
                            },
                            labelLine :{show:true}
                        }
                    }
                ]
            };

            var myChart = echarts.init(document.getElementById($scope.id),'macarons');
            myChart.setOption(option,true);
            var flag = true;
            //var length = $scope.data.length;
            function arror() {
                //option.series[0].data= $scope.data.sort(function (a, b) { return a.value - b.value; })
                //console.log(option.series[0].data);
                $scope.data = JSON.parse($scope.data);
                console.log(typeof $scope.data);
                console.log( $scope.data);
                $scope.scaleData = $scope.data;
                console.log(typeof $scope.scaleData);
                console.log( $scope.scaleData);

                if (flag == true){

                    if($scope.scaleData.length>0){
                        for (var i = 0; i < $scope.scaleData.length; i++) {
                                data.push({
                                    value: $scope.scaleData[i].value,
                                    name: $scope.scaleData[i].name,

                                });
                        }
                        flag = false;
                    }
                }
                if (flag==false){
                    clearInterval(timer);
                }
                myChart.setOption(option);
            }

            var timer = setInterval(arror,1000);



        }
    };
});

app.directive('sexbar2', function() {
    return {
        scope: {
            id: "@",
            item: "@",
        },
        restrict: 'E',
        template: '<div style="float:left;width:1000px;height:500px;"></div>',
        replace: true,
        link: function($scope, element, attrs, controller) {


            var option = {
                backgroundColor: '#0f375f',
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            backgroundColor: '#333'
                        }
                    }
                },
                legend: {
                    data: ['登录人数'],
                    textStyle: {
                        color: '#ccc'
                    }
                },
                xAxis: {
                    axisLine: {
                        lineStyle: {
                            color: '#ccc'
                        }
                    },
                    data:$scope.item["loginDate"]

                },
                yAxis: {
                    splitLine: {show: false},
                    axisLine: {
                        lineStyle: {
                            color: '#ccc'
                        }
                    }
                },
                series: [{
                    name: '登录人数',
                    type: 'line',
                    barWidth: 10,
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#14c8d4'},
                                    {offset: 1, color: '#43eec6'}
                                ]
                            ),
                            label: {
                                show: true, //开启显示
                                position: 'right', //在上方显示
                                textStyle: { //数值样式
                                    color: 'red',
                                    fontSize: 16,
                                    fontWeight: 600
                                }
                            }
                        }

                    },
                    data: $scope.item["count"]
                }]
            };
            var myChart = echarts.init(document.getElementById($scope.id),'macarons');

            var timer = null;
            var num = 0;
            //var length = $scope.data.length;
            function arror() {
                //option.series[0].data= $scope.data.sort(function (a, b) { return a.value - b.value; })
                // console.log(option.xAxis.data);
                // console.log(option.series[0].data);
                // console.log(typeof $scope.item);
                // console.log($scope.item);
                $scope.item = JSON.parse($scope.item);
                        option.xAxis.data= $scope.item["loginDate"];
                        option.series[0].data= $scope.item["count"];
                        num++;
                       // flag = false;
                if (num>10){
                    clearInterval(timer);
                    timer = null;
                }

                myChart.setOption(option);
                /*$scope.$watch('data',function(newValue,oldValue) {
                    console.log(oldValue);
                    console.log(newValue);
                });*/
            }
            timer = setInterval(arror,2000);
        }
    };
});