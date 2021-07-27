    //初始化Echarts
    var myChart3 = echarts.init(document.getElementById('main3'));
    var productName3 = [];
    var nums3 = [];
    var id = 1;

    //时间戳转换为时间函数
    function formateDate(time){
        var hour=time.getHours();     //返回日期中的小时数（0到23）
        var minute=time.getMinutes(); //返回日期中的分钟数（0到59）
        var second=time.getSeconds(); //返回日期中的秒数（0到59）
        return  hour+":"+minute+":"+second;
    }



    //AJAX接收数据主体
    // $.ajax({
    //     type:"GET",
    //     url:"http://10.17.70.52:8080/initsen",
    //     data:{},
    //     dataType:"json",
    //     async:true,
    //     success:function (result) {

    //         for (var i = 0; i < result.length; i++){
    //             var SQLTime = result[i].ts;
    //             var time = new Date(SQLTime);
    //             productName3.push(formateDate(time));
    //             nums3.push(result[i].avgSendSize);
    //         }

    //     },
    //     error :function(errorMsg) {
    //         alert("获取后台数据失败！");
    //     }
    // });

    // 指定图表的配置项和数据
    var option3 = {
        color:["#3398DB"],
        title: {
            text: '平均发送大小',
            left: '20%',
            top: '5%',
            textStyle: {
                fontSize: 20,
                fontStyle: "italic",
                color:'#3398DB'
              }
        },
        tooltip: {},
        legend: {
            data:['流量'],
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
            data: productName3,
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
            name: '流量',
            type: 'line',
            //结合
            data: nums3
        }]
    };

    function addData3(shift) {
        $.ajax({
            type:"GET",
            url:"http://10.17.70.52:8080/sengetinfo",
            data:{id:id.toString()},
            dataType:"json",
            async:true,
            success:function (json) {
                var newSQLTime = json.ts;
                var newTime = new Date(newSQLTime);
                productName3.push(formateDate(newTime));
                nums3.push(json.avgSendSize);
                id++;
            },
            error :function(errorMsg) {
                alert("获取后台数据失败！");
            }
        });
        if (nums3.length >= 30) {
            productName3.shift();
            nums3.shift();
        }
    }

    setInterval(function () {
        addData3(true);
        myChart3.setOption({
            xAxis: {
                data: productName3
            },
            series: [{
                name:'流量',
                data: nums3
            }]
        });
    }, 1500);

    
    // 使用刚指定的配置项和数据显示图表。
    myChart3.setOption(option3);
    //使图片自适应页面
    window.addEventListener("resize", function() {
        myChart3.resize();
    });
