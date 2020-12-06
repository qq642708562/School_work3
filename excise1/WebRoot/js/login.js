function changeImg(){
    document.getElementById("vcodeImg").src="CreateVerifyImage.do?t="+Math.random();
}

var userName_correct = false;
var password_correct = false;
var vcode_correct = false;

function jqAjaxCheckLogin() {
    if(!userName_correct || !password_correct || !vcode_correct){
        $("#userName").blur();
        $("#password").blur();
        $("#vcode").blur();
        return;
    }
    var data = {
        userName: $("#userName").val(),
        password: $("#password").val(),
        vcode: $("#vcode").val()
    };
    if ($("#checkbox").prop("checked"))
        data.checkbox = "y";
    $.ajax({
        type: "post",
        url: "AjaxLoginCheck.do",
        data: data,
        dataType: "json",
        success: function (response) {
            if (response.code == 0) {
                window.location.href = "/excise1/jsp/main.jsp";
            } else {
                $("#checkError").text(response.info);
            }
        },
        error: function () {
            alert("error");
        }
    });
}

$(document).ready(function(){
    $("#userName").blur(function(){
        var $name=$('#userName');
        if ($name.val().length==0)
        {
         $name.next().text('用户名不能为空');
            // $("#s1").text('用户名不能为空');
        }else{
         $name.next().text('');  
         userName_correct = true;
        }
    });
    $("#password").blur(function(){
        var $name=$('#password');
        if ($name.val().length==0)
        {
         $name.next().html('密码不能为空<br>');
        }else{
            $name.next().text('');  
            password_correct = true;
        }
    });
    $("#vcode").blur(function(){
        var $name=$('#vcode');
        if ($name.val().length==0)
        {
         $("#vcodeError").html('验证码不能为空<br>');
        }else{
         $("#vcodeError").text(''); 
         vcode_correct = true;
        }
    });
});

// var xmlHttp;

// function createXmlHttp() {
//     if (window.XMLHttpRequest) {
//         xmlHttp = new XMLHttpRequest();
//     } else {
//         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
//     }
// }

// function ajaxCheckLogin(){
// var userName = document.getElementById("userName").value;
// var password = document.getElementById("password").value;
// var vcode = document.getElementById("vcode").value;
// var data = "userName="+userName+"&password="+password+"&vcode="+vcode;
// if(document.getElementById("checkbox").checked)
// data = data+"&checkbox=y";
// createXmlHttp(); 

// xmlHttp.open("post","AjaxLoginCheck.do",true);
// xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// xmlHttp.send(data);
// xmlHttp.onreadystatechange=function(){
//     if(xmlHttp.readyState==4&&xmlHttp.status==200){
//         var response = xmlHttp.responseText;
//         var json = JSON.parse(response);
//         if(json.code==0){
//             window.location.href = "main.jsp";
//         }else{
//             document.getElementById("checkError").innerText = json.info;
//         }
//     }
// }
// }