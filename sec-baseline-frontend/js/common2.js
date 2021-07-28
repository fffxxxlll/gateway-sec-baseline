     //初始化Echarts
    var myChart2 = echarts.init(document.getElementById('main2'));
    var productName2 = []; 
    var nums21 = [];  //脚本
    var nums22 = [];  //用户
    var id = 1;

    //时间戳转换为时间函数
    function formateDate(time){
        var hour=time.getHours();     //返回日期中的小时数（0到23）
        var minute=time.getMinutes(); //返回日期中的分钟数（0到59）
        var second=time.getSeconds(); //返回日期中的秒数（0到59）
        return  hour+":"+minute+":"+second;
    }

    var option2 = {
        color:["red","#3398DB"],
        title: {
            text: '脚本量和用户量',
            left: '10%',
            top: '5%',
            textStyle: {
                fontSize: 20,
                fontStyle: "italic",
                color:'#3398DB'
              }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                saveAsImage: {show: true}
            },
            right:"4%"
            
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['脚本量', '用户量'],
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
       
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    show: false // 去除刻度线
                  },
                  axisLabel: {
                    color: '#4c9bfd' // 文本颜色
                  },
                  axisLine: {
                    show: true // 去除轴线
                  },
                  boundaryGap: true,
                data: productName2,
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisTick: {
                    show: false  // 去除刻度
                  },
                  axisLabel: {
                    color: '#4c9bfd' // 文字颜色
                  }
            }
        ],
        series: [
            {
                name: '脚本量',
                type: 'bar',
                data: nums21,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name: '用户量',
                type: 'bar',
                data: nums22,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            }
        ]
    };
    function addData2(shift) {
        $.ajax({
            type:"POST",
            url:"http://10.17.70.52:8080/usergetinfo",
            data:{id:id.toString()},
            dataType:"json",
            async:true,
            success:function (json) {
                var newSQLTime = json.ts;
                var newTime = new Date(newSQLTime);
                productName2.push(formateDate(newTime));
                nums21.push(json.robotNum);
                nums22.push(json.totalNum);
                id++;
            },
            error :function(errorMsg) {
                alert("获取后台数据失败！");
            }
        });
        if (nums21.length >= 20) {
            productName2.shift();
            nums21.shift();
            nums22.shift();
        }
    }

    setInterval(function () {
        addData2(true);
        myChart2.setOption({
            xAxis: {
                data: productName2
            },
            series: [{
                name:'脚本量',
                data: nums21
            },{
                name:'用户量',
                data: nums22
            }]
        });
    }, 1500);

   
    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
    //使图片自适应页面
    window.addEventListener("resize", function() {
        myChart2.resize();
    });