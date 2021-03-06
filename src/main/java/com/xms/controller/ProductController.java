package com.xms.controller;


import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/product")
public class ProductController {
	@RequestMapping("/toUpload")
	public String toUpload(){
		return "upload";
	}
	
	  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	    @ResponseBody
	    public  String uploadFileHandler( @RequestParam("file1") MultipartFile file) {
	        if (!file.isEmpty()) {
	            try {
	                // 文件存放服务端的位置
	                String rootPath = "d:/tmp";
	                File dir = new File(rootPath + File.separator + "tmpFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	                // 写文件到服务器
	                System.out.println(dir.getAbsolutePath());
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	                file.transferTo(serverFile);
	                file.transferTo(new File("D:/tmp/tmp"+ File.separator + file.getOriginalFilename()));
	              
	                return "You successfully uploaded file=" +  file.getOriginalFilename();
	            } catch (Exception e) {
	                return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " +  file.getOriginalFilename() + " because the file was empty.";
	        }
		 
	    }
	
}
