var datajson = {"xAxis":[],"yAxis":[],}


app.controller("dailyActivityController", function($scope,dailyActivityService , $controller) {
    $scope.findAll=function(){
        console.log(111);
        dailyActivityService.findAll().success(
            function(response){
                var data = response;
                if (data) {
                    for (var i=0;i<data.x.length;i++){
                        var str = data.x[i].toString();
                        var str2 = str.substring(0,6);
                        var flag=true;
                        for (var j=0;j<datajson.xAxis.length;j++) {
                            if (str2==datajson.xAxis[j]){
                                flag=false;
                                datajson.yAxis[j] += parseInt(data.y[i]!=null?data.y[i]:0);
                            }
                        }
                        if (flag==true) {
                            datajson.xAxis.push(str2);
                            datajson.yAxis.push(parseInt(data.y[i]));
                        }
                    }
                    console.log(datajson)

                }

            }
        );
    }



    $scope.getOption =function () {
        console.log(222)
        var option = null;
        option = {
            title: {
                text: 'Basic drilldown',
                left: 'center'
            },
            legend: {
                left: 'left',
                data: ['category']
            },
            xAxis: {
                type: 'category',
                data: datajson.xAxis
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: 'category',
                    type: 'bar',
                    data: datajson.yAxis
                }
            ]
        };
        return option;
    }
    $scope.initChart = function (myChart,option) {
        myChart.setOption(option);
        myChart.on('click',function(object){
            // 销毁之前的echarts实例
            echarts.dispose(document.getElementById('myBarDiv'));
            // 初始化一个新的实例
            var myChart = echarts.init(document.getElementById('myBarDiv'));
            // object为当前的这个echart对象，大家可以自己打印出来看看
            // console.dir(object);
            // $.ajax(
            //     type : 'get',
            //     url : interfaceUrl + '&category=' + object.name, // 此处可以替换为你的接口地址
            //     dataType : 'json',
            //     success : function (msg){
            //         option.xAxis.data = msg.xAxis;
            //         option.series[0].data = msg.yAxis[0];
            //         myChart.setOption(option, true);
            //     }
            // );
            console.dir(object.name);
            if (object.name == "Animals"){
                option.xAxis.data = [
                    'Cats','Dogs','Cows','Sheep','Pigs'
                ];
                option.series[0].data = [
                    4,2,1,2,1
                ];
                option.legend.data= ['Animals'];

                option.series[0].name=['Animals'];
            }
            if (object.name == "Fruits"){
                option.xAxis.data = [
                    'Apples','Oranges'
                ];
                option.series[0].data = [
                    4,2
                ];
                option.legend.data= ['Fruits'];

                option.series[0].name=['Fruits'];
            }
            if (object.name == "Cars"){
                option.xAxis.data = [
                    'Toyota','Opel','Volkswagen'
                ];
                option.series[0].data = [
                    4,2,2
                ];
                option.legend.data= ['Cars'];
                option.series[0].name=['Cars'];
            }
            myChart.setOption(option, true);
        });
    }
});

