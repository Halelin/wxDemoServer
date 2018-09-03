<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="/ms/js/jquery.min.js"></script>
<script>
	var f1 =function(){
		$.post(
			"http://localhost:8080/ms/main/toIndexJson",
			{},
			function(data){
				for(i=0;i<data.coursedirections.length;i++){
					var trstr = '<tr class="mytr">';
					trstr += '<td>'+data.coursedirections[i].id+'</td>';	
					trstr += '<td>'+data.coursedirections[i].name+'</td>';
					trstr += '<td>'+data.coursedirections[i].picture_url+'</td>';
					trstr += '</tr>';
					$("#coursedirections").append(trstr);			
				}
				
			}
		)
	}
</script>
</head>
<body>
	<button onclick="f1()"></button>
	<table id="coursedirections">
		<tr >
			<td id="item_id">1</td>
			<td id="item_name">2</td>
			<td id="item_picture_url">3</td>
		</tr>
	</table>
	
	<%-- <c:forEach items="${coursedirections}" var="coursedirection">
		<div>${item.id}</div>
		<div>${item.name}</div>
		<div>${item.picture_url}</div>
	</c:forEach>	 --%>
</body>
</html>