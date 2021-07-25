setTimeout(() => {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;
    
    option = {
        color:["pink"],
        grid: { 
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            show: false,// 显示边框
            borderColor: '#012f4a',// 边框颜色
            containLabel: true // 包含刻度文字在内
          },
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            axisTick: {
                show: false // 去除刻度线
              },
              axisLabel: {
                color: '#4c9bfd' // 文本颜色
              },
              axisLine: {
                show: false // 去除轴线
              },
              boundaryGap: false  // 去除轴内间距
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false  // 去除刻度
              },
              axisLabel: {
                color: '#4c9bfd' // 文字颜色
              },
             splitLine: {
                lineStyle: {
                  color: '#012f4a' // 分割线颜色
                }
              } 
        },
        series: [{
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line',
            smooth: true,
        }]
    };
    
    option && myChart.setOption(option);
 //使图片自适应页面
 window.addEventListener("resize", function() {
    myChart.resize();
  });}, 2000);