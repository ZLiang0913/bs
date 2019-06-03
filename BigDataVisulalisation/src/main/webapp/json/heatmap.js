
var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
    '7a', '8a', '9a','10a','11a',
    '12p', '1p', '2p', '3p', '4p', '5p',
    '6p', '7p', '8p', '9p', '10p', '11p'];
var days = ['Saturday', 'Friday', 'Thursday',
    'Wednesday', 'Tuesday', 'Monday', 'Sunday'];




<!--这个是柱状图-->
function loadData(option) {

    $.ajax({
        url: 'json/heatmap.json',
        async: false,
        success: function (data) {
            console.log(2222);
            // var  heatmapdata =JSON.stringify(data);
            heatmapdata = data;
            heatmapdata = heatmapdata.map(function (item) {
                return [item[1], item[0], item[2] || '-'];
            });
            console.log(heatmapdata);
            option.series[0].data = heatmapdata;

        }
    });
}//loadData()

var myChart = echarts.init(document.getElementById('myBarDiv'));
window.onresize = myChart.resize;


option = {
    title: {
        top: 20,
        left: 'center',
        text: '学生们在什么时间爱学习？'
    },
    tooltip: {
        position: 'top'
    },
    animation: false,
    grid: {
        height: '50%',
        y: '10%'
    },
    xAxis: {
        type: 'category',
        data: hours,
        axisLabel: {
            textStyle: {
                color: 'green',//坐标值得具体的颜色

            }
        }
    },
    yAxis: {
        type: 'category',
        data: days,
        axisLabel: {
            textStyle: {
                color: 'green',//坐标值得具体的颜色

            }
        }
    },
    visualMap: {
        min: 1,
        max: 15,
        calculable: true,
        orient: 'horizontal',
        left: 'center',
        bottom: '15%'
    },
    series: [{
        type: 'heatmap',

        label: {
            normal: {
                show: true
            }
        },
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    }]
};
//加载数据到option
loadData(option);
//设置option
myChart.setOption(option);
