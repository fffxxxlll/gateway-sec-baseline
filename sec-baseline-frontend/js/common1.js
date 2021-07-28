var myChart1 = echarts.init(document.getElementById('main1'));

    //参数
    var times1 = [];
    var nums11 = [];
    var nums12 = [];
    var nums13 = [];
    var id = 1;
    var colors = ['#5470C6', '#EE6666', '#91CC75'];
    //时间戳转换为时间函数
    function formateDate(time){
        var hour=time.getHours();     //返回日期中的小时数（0到23）
        var minute=time.getMinutes(); //返回日期中的分钟数（0到59）
        var second=time.getSeconds(); //返回日期中的秒数（0到59）
        return  hour+":"+minute+":"+second;
    }
   var option1 = {
        color: colors,

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid:{
          top: '20%',
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel:true
      },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                saveAsImage: {show: true}
            },
            right:"4%"
            
        },
        legend: {
            data: ['cnt_400', 'cnt_500', 'cnt_total']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: times1
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: 'cnt_400',
                min: 0,
                max: 250,
                position: 'right',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                
            },
            {
                type: 'value',
                name: 'cnt_500',
                min: 0,
                max: 250,
                position: 'right',
                offset: 80,
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: colors[1]
                    }
                },
                
            },
            {
                type: 'value',
                name: 'cnt_total',
                // min: 0,
                // max: 25,
                position: 'left',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: colors[2]
                    }
                },
               
            }
        ],
        series: [
            {
                name: 'cnt_400',
                type: 'bar',
                data: nums11,
                markPoint: {
                  data: [
                      {type: 'max', name: '最大值'},
                      {type: 'min', name: '最小值'}
                  ]
              }
            },
            {
                name: 'cnt_500',
                type: 'bar',
                yAxisIndex: 1,
                data: nums12,
                markPoint: {
                  data: [
                      {type: 'max', name: '最大值'},
                      {type: 'min', name: '最小值'}
                  ]
              }
            },
            {
                name: 'cnt_total',
                type: 'line',
                yAxisIndex: 2,
                data: nums13,
                markPoint: {
                  data: [
                      {type: 'max', name: '最大值'},
                      {type: 'min', name: '最小值'}
                  ]
              }
            }
        ]
    };

    function addData(shift) {
        $.ajax({
            type:"POST",
            url:"http://10.17.70.52:8080/codegetinfo",
            data:{id:id.toString()},
            dataType:"json",
            async:true,
            success:function (json) {
                var newSQLTime = json.ts;
                var newTime = new Date(newSQLTime);


                times1.push(formateDate(newTime));
                nums11.push(json.cnt400);
                nums12.push(json.cnt500);
                nums13.push(json.cntTotal);
                id++;

            },
            error :function(errorMsg) {
                alert("获取后台数据失败！");
            }
        });
        if (times1.length >= 15) {
            times1.shift();
            nums11.shift();
            nums12.shift();
            nums13.shift();
        }
    }

    setInterval(function () {
        addData(true);
        myChart1.setOption({
            xAxis: {
                data: times1
            },
            series: [{
                name:'cnt_400',
                data: nums11
            },{
                name:'cnt_500',
                data: nums12
            },{
                name:'cnt_total',
                data: nums13
            }]
        });
    }, 1500);


    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);
