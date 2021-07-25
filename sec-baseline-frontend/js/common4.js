// setTimeout(() => {
//     var chartDom = document.getElementById('main');
//         var myChart = echarts.init(chartDom);
//         var option;
//
//     option = {
//         title: {
//             text: '堆叠区域图'
//         },
//         tooltip: {
//             trigger: 'axis',
//             axisPointer: {
//                 type: 'cross',
//                 label: {
//                     backgroundColor: '#6a7985'
//                 }
//             }
//         },
//         legend: {
//             data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
//         },
//         toolbox: {
//             feature: {
//                 saveAsImage: {}
//             }
//         },
//         grid: {
//             left: '3%',
//             right: '4%',
//             bottom: '3%',
//             containLabel: true
//         },
//         xAxis: [
//             {
//                 type: 'category',
//                 boundaryGap: false,
//                 data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
//             }
//         ],
//         yAxis: [
//             {
//                 type: 'value'
//             }
//         ],
//         series: [
//             {
//                 name: '邮件营销',
//                 type: 'line',
//                 stack: '总量',
//                 areaStyle: {},
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [120, 132, 101, 134, 90, 230, 210]
//             },
//             {
//                 name: '联盟广告',
//                 type: 'line',
//                 stack: '总量',
//                 areaStyle: {},
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [220, 182, 191, 234, 290, 330, 310]
//             },
//             {
//                 name: '视频广告',
//                 type: 'line',
//                 stack: '总量',
//                 areaStyle: {},
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [150, 232, 201, 154, 190, 330, 410]
//             },
//             {
//                 name: '直接访问',
//                 type: 'line',
//                 stack: '总量',
//                 areaStyle: {},
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [320, 332, 301, 334, 390, 330, 320]
//             },
//             {
//                 name: '搜索引擎',
//                 type: 'line',
//                 stack: '总量',
//                 label: {
//                     show: true,
//                     position: 'top'
//                 },
//                 areaStyle: {},
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [820, 932, 901, 934, 1290, 1330, 1320]
//             }
//         ]
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
        url:"http://localhost:8080/initreq",
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

    function addData4(shift) {
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/reqgetinfo",
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
        addData4(true);
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

