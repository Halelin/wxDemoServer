<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
	var f1 =function(){
		$.post{
			"http://localhost:8080/ms2/main/toIndexJson",
			{},
			function(data){
				if(data){
					$("#p_email").html("邮箱可以注册").css("color","green");
					array.email = true;
				}else{
					$("#p_email").html("邮箱已注册").css("color","red");
				}
			}
		}
	}
</script>
</head>
<body>
	<button onclick="f1()"></button>
	
</body>
</html>