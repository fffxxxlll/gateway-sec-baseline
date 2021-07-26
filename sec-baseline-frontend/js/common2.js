// setTimeout(() => {
//     var chartDom = document.getElementById('main');
//     var myChart = echarts.init(chartDom);
//     var option;
//
//     option = {
//         color:["pink"],
//         xAxis: {
//             type: 'category',
//             data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
//         },
//         yAxis: {
//             type: 'value'
//         },
//         series: [{
//             data: [150, 230, 224, 218, 135, 147, 260],
//             type: 'line'
//         }]
//     };
//
//     option && myChart.setOption(option);
//  //使图片自适应页面
//  window.addEventListener("resize", function() {
//     myChart.resize();
//   });}, 2000);


setTimeout(() => {
    var productName = [];
    var nums = [];
    var id = 31;

    //时间戳转换为时间函数
    function formateDate(time){
        var hour=time.getHours();     //返回日期中的小时数（0到23）
        var minute=time.getMinutes(); //返回日期中的分钟数（0到59）
        var second=time.getSeconds(); //返回日期中的秒数（0到59）
        return  hour+":"+minute+":"+second;
    }



    //AJAX接收数据主体
    $.ajax({
        type:"GET",
        url:"http://10.17.100.131:8080/initres",
        data:{},
        dataType:"json",
        async:true,
        success:function (result) {

            for (var i = 0; i < result.length; i++){
                var SQLTime = result[i].ts;
                var time = new Date(SQLTime);
                productName.push(formateDate(time));
                nums.push(result[i].avgResTime);
            }

        },
        error :function(errorMsg) {
            alert("获取后台数据失败！");
        }
    });

    // 指定图表的配置项和数据
    var option = {
        color:["#3398DB"],
        title: {
            text: '平均响应时延',
            left: '10%',
            top: '5%',
            textStyle: {
                fontSize: 20,
                fontStyle: "italic",
                color:'#3398DB'
              }
        },
        tooltip: {},
        legend: {
            data:['平均响应时延'],
            right: '10%', // 距离右边10%
            top: '5%'
        },
        grid:{
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel:true
        },
        xAxis: {
            //结合
            data: productName,
            axisTick: {
                show: false // 去除刻度线
              },
              axisLabel: {
                color: '#4c9bfd' // 文本颜色
              },
              axisLine: {
                show: true // 去除轴线
              },
              boundaryGap: false
        },

        yAxis: {
            axisTick: {
                show: false  // 去除刻度
              },
              axisLabel: {
                color: '#4c9bfd' // 文字颜色
              }
        },
        series: [{
            name: '平均响应时延',
            type: 'line',
            // 开始不显示拐点， 鼠标经过显示
            areaStyle: {},
            emphasis: {
                focus: 'series'
            },
            areaStyle: {
                // 渐变色，只需要复制即可
              color: new echarts.graphic.LinearGradient(
                0,
                0,
                0,
                1,
                [
                  {
                    offset: 0,
                    color: "rgba(1, 132, 213, 0.4)"   // 渐变色的起始颜色
                  },
                  {
                    offset: 0.8,
                    color: "rgba(1, 132, 213, 0.1)"   // 渐变线的结束颜色
                  }
                ],
                false
              ),
              shadowColor: "rgba(0, 0, 0, 0.1)"
          },
            //结合
            data: nums
        }]
    };

    function addData2(shift) {
        $.ajax({
            type:"GET",
            url:"http://10.17.100.131:8080/resgetinfo",
            data:{id:id.toString()},
            dataType:"json",
            async:true,
            success:function (json) {
                var newSQLTime = json.ts;
                var newTime = new Date(newSQLTime);
                productName.push(formateDate(newTime));
                nums.push(json.avgResTime);
                id++;
            },
            error :function(errorMsg) {
                alert("获取后台数据失败！");
            }
        });
        if (shift) {
            productName.shift();
            nums.shift();
        }
    }

    setInterval(function () {
        addData2(true);
        myChart.setOption({
            xAxis: {
                data: productName
            },
            series: [{
                name:'时延',
                data: nums
            }]
        });
    }, 1500);

    //初始化Echarts
    var myChart = echarts.init(document.getElementById('main'));
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //使图片自适应页面
    window.addEventListener("resize", function() {
        myChart.resize();
    });}, 2000);