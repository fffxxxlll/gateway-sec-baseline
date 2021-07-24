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
        url:"http://10.17.70.52:8080/initreq",
        data:{},
        dataType:"json",
        async:true,
        success:function (result) {

            for (var i = 0; i < result.length; i++){
                var SQLTime = result[i].ts;
                var time = new Date(SQLTime);
                productName.push(formateDate(time));
                nums.push(result[i].avgReqTime);
            }

        },
        error :function(errorMsg) {
            alert("获取后台数据失败！");
        }
    });

    // 指定图表的配置项和数据
    var option = {
        color:["pink"],
        title: {
            text: '平均请求时延'
        },
        tooltip: {},
        legend: {
            data:['时延']
        },
        grid:{
            containLabel:true
        },
        xAxis: {
            //结合
            data: productName
        },

        yAxis: {},
        series: [{
            name: '时延',
            type: 'line',
            //结合
            data: nums
        }]
    };

    function addData(shift) {
        $.ajax({
            type:"GET",
            url:"http://10.17.70.52:8080/reqgetinfo",
            data:{id:id.toString()},
            dataType:"json",
            async:true,
            success:function (json) {
                var newSQLTime = json.ts;
                var newTime = new Date(newSQLTime);


                productName.push(formateDate(newTime));
                nums.push(json.avgReqTime);
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
        addData(true);
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

