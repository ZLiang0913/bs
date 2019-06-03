
var perioddata;

<!--这个是柱状图-->
function loadData(option) {

    $.ajax({
        url: 'json/period.json',
        async: false,
        success: function (data) {
            console.log(2222);
            // var  heatmapdata =JSON.stringify(data);
            console.log(data);
            console.log(typeof data);
            perioddata = data;

            console.log(perioddata);
            option.series[0].data = perioddata;

        }
    });
}//loadData()

var myChart = echarts.init(document.getElementById('myBarDiv2'));
window.onresize = myChart.resize;


option = {
    title : {
        text: '哪些时间段的学习人数最多？'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['学习人数']
    },
    toolbox: {
        show : true,
        feature : {
            dataView : {show: true, readOnly: false},
            magicType: {
                type: ['line', 'bar']
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['00:00-01:00','01:00-02:00','02:00-03:00','03:00-04:00','04:00-05:00','05:00-06:00','06:00-07:00','07:00-08:00','08:00-09:00','09:00-10:00','10:00-11:00','11:00-12:00','12:00-13:00','13:00-14:00','14:00-15:00','15:00-16:00','16:00-17:00','17:00-18:00','18:00-19:00','19:00-20:00','20:00-21:00','21:00-22:00','22:00-23:00','23:00-00:00']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'学习人数',
            type:'bar',
            data:[],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
};

//加载数据到option
loadData(option);
//设置option
myChart.setOption(option);
