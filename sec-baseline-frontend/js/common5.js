// setTimeout(() => {
//     var chartDom = document.getElementById('main');
//     var myChart = echarts.init(chartDom);
//     var option;
//
//     option = {
//         color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00'],
//         title: {
//             text: '渐变堆叠面积图'
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
//             data: ['Line 1', 'Line 2', 'Line 3', 'Line 4', 'Line 5']
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
//                 name: 'Line 1',
//                 type: 'line',
//                 stack: '总量',
//                 smooth: true,
//                 lineStyle: {
//                     width: 0
//                 },
//                 showSymbol: false,
//                 areaStyle: {
//                     opacity: 0.8,
//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                         offset: 0,
//                         color: 'rgba(128, 255, 165)'
//                     }, {
//                         offset: 1,
//                         color: 'rgba(1, 191, 236)'
//                     }])
//                 },
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [140, 232, 101, 264, 90, 340, 250]
//             },
//             {
//                 name: 'Line 2',
//                 type: 'line',
//                 stack: '总量',
//                 smooth: true,
//                 lineStyle: {
//                     width: 0
//                 },
//                 showSymbol: false,
//                 areaStyle: {
//                     opacity: 0.8,
//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                         offset: 0,
//                         color: 'rgba(0, 221, 255)'
//                     }, {
//                         offset: 1,
//                         color: 'rgba(77, 119, 255)'
//                     }])
//                 },
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [120, 282, 111, 234, 220, 340, 310]
//             },
//             {
//                 name: 'Line 3',
//                 type: 'line',
//                 stack: '总量',
//                 smooth: true,
//                 lineStyle: {
//                     width: 0
//                 },
//                 showSymbol: false,
//                 areaStyle: {
//                     opacity: 0.8,
//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                         offset: 0,
//                         color: 'rgba(55, 162, 255)'
//                     }, {
//                         offset: 1,
//                         color: 'rgba(116, 21, 219)'
//                     }])
//                 },
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [320, 132, 201, 334, 190, 130, 220]
//             },
//             {
//                 name: 'Line 4',
//                 type: 'line',
//                 stack: '总量',
//                 smooth: true,
//                 lineStyle: {
//                     width: 0
//                 },
//                 showSymbol: false,
//                 areaStyle: {
//                     opacity: 0.8,
//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                         offset: 0,
//                         color: 'rgba(255, 0, 135)'
//                     }, {
//                         offset: 1,
//                         color: 'rgba(135, 0, 157)'
//                     }])
//                 },
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [220, 402, 231, 134, 190, 230, 120]
//             },
//             {
//                 name: 'Line 5',
//                 type: 'line',
//                 stack: '总量',
//                 smooth: true,
//                 lineStyle: {
//                     width: 0
//                 },
//                 showSymbol: false,
//                 label: {
//                     show: true,
//                     position: 'top'
//                 },
//                 areaStyle: {
//                     opacity: 0.8,
//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                         offset: 0,
//                         color: 'rgba(255, 191, 0)'
//                     }, {
//                         offset: 1,
//                         color: 'rgba(224, 62, 76)'
//                     }])
//                 },
//                 emphasis: {
//                     focus: 'series'
//                 },
//                 data: [220, 302, 181, 234, 210, 290, 150]
//             }
//         ]
//     };
//
//     option && myChart.setOption(option);
//  //使图片自适应页面
//  window.addEventListener("resize", function() {
//     myChart.resize();
//   });}, 2000);

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
        url:"http://localhost:8080/initres",
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
        color:["pink"],
        title: {
            text: '平均响应时延'
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

    function addData5(shift) {
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/resgetinfo",
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
        addData5(true);
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