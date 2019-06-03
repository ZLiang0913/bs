var drillDown = {
    getOption : function () {
        option = {
            title: {
                text: '每天的活跃学生情况是怎样的？',
                left: 'center'
            },
            legend: {
                icon: 'rect',
                itemWidth: 14,
                itemHeight: 5,
                itemGap: 13,
                data: ['活跃人数[人]'],
                right: '15%',
                textStyle: {
                    fontSize: 12,
                    color: 'red'
                }
            },
            xAxis: {
                type: 'category',
                data: []
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '活跃人数[人]',
                    type: 'bar',
                    data: [],
                    itemStyle: {
                        normal: {
                            label: {
                                show: true, //开启显示
                                position: 'top', //在上方显示
                                textStyle: { //数值样式
                                    color: 'black',
                                    fontSize: 16
                                }
                            }
                        }
                    },
                }
            ]
        };
        var datajson = {"xAxis":[],"yAxis":[]}
        $.ajax({
            type : 'post',  //传输类型
            async : false,  //同步执行
            url : 'dailyActivity.do', //web.xml中注册的Servlet的url-pattern
            timeout : 1000,
            data : {},
            dataType : 'json', //返回数据形式为json
            success : function(data) {
                console.log(data);

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
                    //初始化xAxis[0]的data
                    option.xAxis.data = datajson.xAxis;
                    /*for (var i=0; i<data.x.length; i++) {
                        option.xAxis[0].data.push(data.x[i].name);
                    }*/
                    //初始化series[0]的data
                    option.series[0].data = datajson.yAxis;
                    /*for (var i=0; i<data.y.length; i++) {
                        option.series[0].data.push(data.y[i].num);
                    }*/
                }
            },
            error : function(errorMsg) {
                alert("加载数据失败");
            }
        });//AJAX
        //var option = null;

        return option;
    },
    initChart : function (myChart,option) {
        myChart.setOption(option);
        myChart.on('click',function(object){
            // 销毁之前的echarts实例
            echarts.dispose(dom);
            // 初始化一个新的实例
            var myChart = echarts.init(dom);
            // object为当前的这个echart对象，大家可以自己打印出来看看
            // console.dir(object);
            var datajson = {"xAxis":[],"yAxis":[]};
            $.ajax({
                type : 'post',  //传输类型
                async : false,  //同步执行
                url : 'dailyActivity.do', //web.xml中注册的Servlet的url-pattern
                timeout : 1000,
                data : {},
                dataType : 'json', //返回数据形式为json
                success : function(data) {
                    console.log(data);
                    console.dir(object.name);
                    if (data) {
                        for (var i=0;i<data.x.length;i++){
                            var str = data.x[i].toString();
                            var str2 = str.substring(0,6);

                                if (str2==object.name){
                                    datajson.xAxis.push(str);
                                    datajson.yAxis.push(parseInt(data.y[i]!=null?data.y[i]:0));
                                }

                        }
                        console.log(datajson);
                        //初始化xAxis[0]的data
                        //option.xAxis.data = datajson.xAxis;
                        /*for (var i=0; i<data.x.length; i++) {
                            option.xAxis[0].data.push(data.x[i].name);
                        }*/
                        //初始化series[0]的data
                        //option.series[0].data = datajson.yAxis;
                        /*for (var i=0; i<data.y.length; i++) {
                            option.series[0].data.push(data.y[i].num);
                        }*/
                    }
                },
                error : function(errorMsg) {
                    alert("加载数据失败");
                }
            });//AJAX
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

            console.log(datajson);
                option.xAxis.data = datajson.xAxis;
                option.series[0].data = datajson.yAxis;
                option.legend.data= ['活跃人数[人]'];

                option.series[0].name=['活跃人数[人]'];


            myChart.setOption(option, true);
        });
    },
};
