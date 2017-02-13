/**
 * Created by kj on 2017/2/12.
 */
function login() {
    $.post({
        url: "/airtransport/user/login",
        async: true,
        data:{password:$("#password").val(),username:$("#username").val()},
        dataType:'json',
        success: function (data) {
            if(data.status=="success")
                window.location.href="http://localhost:8080/airtransport/console.html";
            else
                alert("您的账号或密码有误");

        },
        error: function () {
            alert("fail")
        }
    })
}