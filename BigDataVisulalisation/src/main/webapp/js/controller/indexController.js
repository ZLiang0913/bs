//品牌控制层
app.controller("dailyLoginController", function($scope, $http,dailyLoginService , $controller) {

    $http({
        method:'POST',
        url:'index.do',
    }).then(function successCallback (rs){
        console.log(typeof rs);
        console.log( rs);
        $scope.strArray=[];
        for (var i = 0; i < rs.courseNum; i++) {


                strArray.push({"courseName":rs.course[i].courseName,"rate":rs.course[i].rate});

        }
        console.log("$scope.strArray"+$scope.strArray);

    });


    //读取指定月份
    $scope.$watch("entity.goods.category1Id",function(newValue,oldValue){
        console.log( typeof newValue);
        $http({
            method:'POST',
            url:'dailyLogin.do?id='+newValue,
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
    });
});

app.directive('sexbar', function() {
    return {
        scope: {
            id: "@",
            legend: "=",
            //item: "=",
            data: "="
        },
        restrict: 'E',
        template: '<div  style="float:left;width:500px;height:500px;"></div>',
        replace: true,
        link: function($scope, element, attrs, controller) {
            var a = [];

            var option = {
                backgroundColor: '#2c343c',

                title: {
                    text: 'Customized Pie',
                    left: 'center',
                    top: 20,
                    textStyle: {
                        color: '#ccc'
                    }
                },

                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },

                visualMap: {
                    show: false,
                    min: 80,
                    max: 600,
                    inRange: {
                        colorLightness: [0, 1]
                    }
                },
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '50%'],
                        data:$scope.data.sort(function (a, b) { return a.value - b.value; }),
                        roseType: 'radius',
                        label: {
                            normal: {
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                lineStyle: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#c23531',
                                shadowBlur: 200,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },

                        animationType: 'scale',
                        animationEasing: 'elasticOut',
                        animationDelay: function (idx) {
                            return Math.random() * 200;
                        }
                    }
                ]
            };
            $.ajax({
                            type: "post",
                            url: "dailyLoginAll.do",
                            async: false,
                            success: function (data) {
                                var myObj = JSON.parse(data);
                                /*console.log(typeof  data);
                                console.log(data)
                                console.log(typeof  myObj);
                                console.log(myObj)
                                console.log(typeof  myObj.x);
                                console.log(myObj.x);
                                console.log(myObj.x.length);*/
                                /*var loginDate = $scope.list.x;
                                var count = $scope.list.y;
                                console.log(typeof loginDate);
                                console.log(typeof loginDate.size);
                                console.log( loginDate);
                                console.log(typeof loginDate[0]);*/

                                for (var i = 0; i < myObj.x.length; i++) {

                                    $scope.data.push({"value":myObj.y[i],"name":$scope.legend[parseInt(myObj.x[i],10)-1]});
                                }
                                console.log( $scope.data);

                            },
                            error: function () {
                                alert("数据加载失败")
                            }
                        });

            var myChart = echarts.init(document.getElementById($scope.id),'macarons');
            myChart.setOption(option);
        }
    };
});

app.directive('sexbar2', function() {
    return {
        scope: {
            id: "@",
            item: "=",
        },
        restrict: 'E',
        template: '<div style="float:left;width:1000px;height:500px;"></div>',
        replace: true,
        link: function($scope, element, attrs, controller) {
            var a = [];

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
                    data:$scope.item["loginDate"],
                    axisLine: {
                        lineStyle: {
                            color: '#ccc'
                        }
                    }
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
                                position: 'top', //在上方显示
                                textStyle: { //数值样式
                                    color: 'red',
                                    fontSize: 16,
                                    fontWeight: 600
                                }
                            }
                        }

                    },
                    data:$scope.item["count"]
                }]
            };
            var timer = null;
            timer = setInterval(function () {
                    $.ajax({
                        type: "post",
                        url: "dailyLogin.do?id=04",
                        async: false,
                        success: function (data) {
                           /* $scope.data.push("barData",data);
                            console.log($scope.data);*/
                            console.log(11111)
                            var myObj = JSON.parse(data);
                            console.log(typeof  data);
                            console.log(data)
                            console.log(typeof  myObj);
                            console.log(myObj)
                            console.log(  myObj[0]["loginDate"]+","+myObj[0]["count"]);

                            for (var i = 0; i < myObj.length; i++) {
                                $scope.item["loginDate"].push(myObj[i]["loginDate"]);
                                $scope.item["count"].push(myObj[i]["count"]);

                            }
                            console.log($scope.item);
                            /*var barDate =[];
                            var cate =[];
                            for (var i = 0; i < myObj.length; i++) {
                               cate.push(myObj[i]["loginDate"]);
                                barDate.push(myObj[i]["count"]);

                            }
                            option.xAxis.data=cate;
                            option.series[0].data=barDate;

                            console.log( barDate);
                            console.log( cate);*/



                        },
                        error: function () {
                            alert("数据加载失败")
                        }
                    });
            }, 300);

            var myChart = echarts.init(document.getElementById($scope.id),'macarons');
            myChart.setOption(option);
        }
    };
});