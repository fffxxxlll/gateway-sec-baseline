function loginHome(){
    var name = $("#username").val();
    var pwd = $("#pwd").val();
    if(name==""){
        document.getElementById("tip").innerHTML="用户名不能为空哟";
        return false;
    }
    if(pwd==""){
        document.getElementById("tip").innerHTML="密码也不能为空哟";
        return false;
    }
    else{
        $.ajax({
            type:"post",  //请求类型
            url:"http://10.17.100.131:8080/login",//请求地址
            dataType:"json",//预期返回的参数类型
            async:true, //异步
            data:{username:name,pwd:pwd}, //传入后端Controller的参数
            success:function(data){
                var msg = data.msg;//提取出来的密码
                if(msg==="success"){
                    document.getElementById("tip").innerHTML="账号或密码输入错误";
                }else{
                    window.location.href="http://127.0.0.1:5500/sec-baseline-frontend/common.html";
                }
            }




        });
    }
}