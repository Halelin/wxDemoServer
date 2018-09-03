<%-- <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script src="../js/upload.js"></script>
<body>
	this is uploadpage!
	<div>
			<label>课程视频：</label></br>
			<span  class="upload_file" id="upload_video">
			<input type="file" name="">
			<button onclick="upload.uploadVideo()" value="上传">上传</button>
			<input type="text" name="">
			<input type="hidden" name="product.video"/>
			</span>					
	</div>
	
</body>
</html> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
       <title>Upload File Request Page</title>
    </head>
    <body>
        <form method="POST" action="uploadFile" enctype="multipart/form-data">
           File to upload: <input type="file" name="file1">
           <input type="submit" value="Upload"> Press here to upload the file!
        </form>
    </body>
</html>
