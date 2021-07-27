     //初始化Echarts
    var myChart2 = echarts.init(document.getElementById('main2'));
    // var productName2 = [];
    // var nums2 = [];
    // var id = 1;

    // //时间戳转换为时间函数
    // function formateDate(time){
    //     var hour=time.getHours();     //返回日期中的小时数（0到23）
    //     var minute=time.getMinutes(); //返回日期中的分钟数（0到59）
    //     var second=time.getSeconds(); //返回日期中的秒数（0到59）
    //     return  hour+":"+minute+":"+second;
    // }
    // 指定图表的配置项和数据
    // var option2 = {
    //     color:["#3398DB"],
    //     title: {
    //         text: '平均响应时延',
    //         left: '20%',
    //         top: '5%',
    //         textStyle: {
    //             fontSize: 20,
    //             fontStyle: "italic",
    //             color:'#3398DB'
    //           }
    //     },
    //     tooltip: {},
    //     legend: {
    //         data:['平均响应时延'],
    //         right: '10%', // 距离右边10%
    //         top: '5%'
    //     },
    //     grid:{
    //         top: '20%',
    //         left: '3%',
    //         right: '4%',
    //         bottom: '3%',
    //         containLabel:true
    //     },
    //     xAxis: {
    //         //结合
    //         data: productName2,
    //         axisTick: {
    //             show: false // 去除刻度线
    //           },
    //           axisLabel: {
    //             color: '#4c9bfd' // 文本颜色
    //           },
    //           axisLine: {
    //             show: true // 去除轴线
    //           },
    //           boundaryGap: false
    //     },

    //     yAxis: {
    //         axisTick: {
    //             show: false  // 去除刻度
    //           },
    //           axisLabel: {
    //             color: '#4c9bfd' // 文字颜色
    //           }
    //     },
    //     series: [{
    //         name: '平均响应时延',
    //         type: 'line',
    //         // 开始不显示拐点， 鼠标经过显示
    //         areaStyle: {},
    //         emphasis: {
    //             focus: 'series'
    //         },
    //         areaStyle: {
    //             // 渐变色，只需要复制即可
    //           color: new echarts.graphic.LinearGradient(
    //             0,
    //             0,
    //             0,
    //             1,
    //             [
    //               {
    //                 offset: 0,
    //                 color: "rgba(1, 132, 213, 0.4)"   // 渐变色的起始颜色
    //               },
    //               {
    //                 offset: 0.8,
    //                 color: "rgba(1, 132, 213, 0.1)"   // 渐变线的结束颜色
    //               }
    //             ],
    //             false
    //           ),
    //           shadowColor: "rgba(0, 0, 0, 0.1)"
    //       },
    //         //结合
    //         data: nums2
    //     }]
    // };
    var option2 = {
        title: {
            text: '某地区蒸发量和降水量',
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['蒸发量', '降水量']
        },
       
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '蒸发量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '降水量',
                type: 'bar',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint: {
                    data: [
                        {name: '年最高', value: 182.2, xAxis: 7, yAxis: 183},
                        {name: '年最低', value: 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    // function addData2(shift) {
    //     $.ajax({
    //         type:"GET",
    //         url:"http://10.17.70.52:8080/resgetinfo",
    //         data:{id:id.toString()},
    //         dataType:"json",
    //         async:true,
    //         success:function (json) {
    //             var newSQLTime = json.ts;
    //             var newTime = new Date(newSQLTime);
    //             productName2.push(formateDate(newTime));
    //             nums2.push(json.avgResTime);
    //             id++;
    //         },
    //         error :function(errorMsg) {
    //             alert("获取后台数据失败！");
    //         }
    //     });
    //     if (nums2.length >= 30) {
    //         productName2.shift();
    //         nums2.shift();
    //     }
    // }

    // setInterval(function () {
    //     addData2(true);
    //     myChart2.setOption({
    //         xAxis: {
    //             data: productName2
    //         },
    //         series: [{
    //             name:'时延',
    //             data: nums2
    //         }]
    //     });
    // }, 1500);

   
    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
    //使图片自适应页面
    window.addEventListener("resize", function() {
        myChart2.resize();
    });