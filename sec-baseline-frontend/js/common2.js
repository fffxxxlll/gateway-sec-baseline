setTimeout(() => {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;
    
    option = {
        color:["pink"],
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
        }]
    };
    
    option && myChart.setOption(option);
 //使图片自适应页面
 window.addEventListener("resize", function() {
    myChart.resize();
  });}, 2000);