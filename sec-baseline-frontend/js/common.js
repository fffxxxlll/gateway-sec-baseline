// function initChart() {
//     console.log("获取main元素", new Date());
//     var myChart = echarts.init(document.getElementById("main"),"light");
//     myChart.setOption({                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
//                 series:[{
//                     name:"访问来源",
//                     type:"pie",
//                     radius:"55%",//饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长
//                     //通过设置参数 roseType: 'angle' 把饼图显示成南丁格尔图。
//                     roseType:"angle",
//                     data:[
//                         {value:235,name:"视频广告"},
//                         {value:274, name:'联盟广告'},
//                         {value:310, name:'邮件营销'},
//                         {value:335, name:'直接访问'},
//                         {value:400, name:'搜索引擎'}
//                     ]
//                 }],
//                 //itemStyle 参数可以设置诸如阴影、透明度、颜色、边框颜色、边框宽度等：
//                 itemStyle:{
//                     normal:{
//                         shadowBlur:200,
//                         shadowColor:"rgba(0,0,0,.5)"
//                     }
//                 }
//     });
// }

console.log("准备加载file文件", new Date());
$(".include").each(function() {
    if (!!$(this).attr("file")) {
        var $includeObj = $(this);
        $(this).load($(this).attr("file"), function(html) {
            $includeObj.after(html).remove(); //加载的文件内容写入到当前标签后面并移除当前标签
            // console.log("加载完file文件", new Date);
            // // setTimeout(() => { initChart(); }, 2000);
            // const mainEle = document.getElementById("main")
            // mainEle.style.height = "500px";
            // console.log(mainEle.clientWidth, mainEle.clientHeight);
            // initChart();
        })
    }
});