var upload = {};

//上传视频效果
upload.uploadVideo = function(){
	console.log("kaishi")
	document.getElementById('upload_video').onchange = function(){
		//判断视频格式  (jpg) c:\image\aa.png
		var videoUrl = this.children[0].value;
		var fix = videoUrl.substring(
			videoUrl.lastIndexOf(".")+1);
		if(fix!="mp4"){
			alert("请选择mp4格式的图片上传");
			return;
		}
		this.children[1].value = this.children[0].value;
		//向服务器发请求，上传文件
		var file = this.children[0].files[0];
		var fileData = new FormData();
		fileData.append("some",file);
		upload.uploadFile(fileData);
	}
}

upload.uploadFile = function(fileData){
	$.ajax({
		"url":"/ms2/product/uploadFile",
		"type":"post",
		"data":fileData,
		"dataType":"json",
		"contentType":false,//告诉jquery不要设置参数的编码方式
		"processData":false,//告诉jquery不要将参数转成字符串
		"success":function(data){
			if(data.code==0){
				alert(data.msg);
				//将新的文件名称保存到指定的隐藏框中
				if(data.obj.fix=="jpg"){
					$("#upload_image input[type='hidden']").
					val(data.obj.newName);
				}else if(data.obj.fix=="mp4"){
					$("#upload_video input[type='hidden']").
					val(data.obj.newName);
				}
			}
		},
		"error":function(){alert("系统繁忙!");}
	});
}