function loginHome(){
    var name = $("#username").val();
    var pwd = $("#pwd").val();
    var json = {
        "userName" : name,
        "pwd" : pwd
    };
    if(name==""){
        document.getElementById("tip").innerHTML="用户名不能为空哟";
        return false;
    }
    if(pwd==""){
        document.getElementById("tip").innerHTML="密码也不能为空哟";
        return false;
    }
    else{
        login(json);
    }
}



function login(json) {
    $.ajax({url:"http://10.17.100.131:8080/login",
        type: 'post',
        contentType: 'application/json',
        data:JSON.stringify(json)
        ,success:function(data){
            console.log(JSON.stringify(data));
            if(data.msg != null) {
                alert(data.msg)
            } else {
                document.cookie = "token=" + data.token;
                $(location).attr('href', 'common1.html');
            }
        },
        error:function (data) {
            alert(data);
        }

    });
}

function loginCheck (token) {
    $.ajax({
        url:"http://10.17.100.131:8080/success",
        type: 'post',
        beforeSend: function(request) {
            request.setRequestHeader("Jwt-Token", token);
        },
        success:function(data){
            if(data.msg !== "success"){

                alert(data.msg);
                $(location).attr('href', 'login.html');
            } else {
                $("html").show();
            }
        },
        error:function (data) {
            alert(data);
        }
    })
}

function getToken () {
    var token = getCookie("token");
    if(token == undefined)
        return null;
    else
        return token.split(";")[0]
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
