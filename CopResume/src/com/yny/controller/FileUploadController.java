package com.yny.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping(value = "/file")  
public class FileUploadController {

	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	public String fileUpload(@RequestParam(value = "file", required = false) CommonsMultipartFile file,HttpServletRequest request,Model model){
		
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.print(file.getOriginalFilename());
		
		String root = request.getServletContext().getRealPath("/");
		
		root = root+File.separator+"upload";
		
		File rootFile = new File(root);
		
		if (!rootFile.exists()) {
			rootFile.mkdir();
		}
		
		File targetFile = new File(root, file.getOriginalFilename());
		
		 //保存  
        try {  
            file.transferTo(targetFile);  
            
            model.addAttribute("path", root+File.separator+file.getOriginalFilename());
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
		return	"file_success";
	}
	
	@RequestMapping(value="/fileDownload",method=RequestMethod.GET)
	public void fileUpload(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String root = request.getServletContext().getRealPath("/");
		root = root+File.separator+"upload/1.jpg";
		
		response.setCharacterEncoding("utf-8");  
        response.setContentType("multipart/form-data");  
        response.setHeader("Content-Disposition", "attachment;fileName="+root);  
        try {  
            File file=new File(root);  
            System.out.println(file.getAbsolutePath());  
            InputStream inputStream=new FileInputStream(file);  
            OutputStream os=response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
	}
	
	
	
}
