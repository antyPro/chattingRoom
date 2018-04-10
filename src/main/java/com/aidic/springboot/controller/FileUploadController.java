package com.aidic.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 *
 */

@RestController
public class FileUploadController 
{	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String helloworld(HttpServletResponse response,HttpServletRequest req,@RequestParam(value = "file", required = false)MultipartFile  multipartFile) {
    	//System.out.println(multipartFile.getOriginalFilename());
		response.setHeader("Access-Control-Allow-Origin", "*");
    	String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String newName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		ServletContext servletContext = req.getServletContext();
		String realPath = servletContext.getRealPath("/");
		String filePath="/pic/"+sdf.format(date)+"/";
		File url = new File(realPath+"/"+filePath);
		File file = new File(realPath+filePath+newName);
		System.out.println("绝对路径:"+realPath+filePath+newName);
		try {       
			if(!url.exists()){  
				url.mkdirs();  
			}  
			multipartFile.transferTo(file);    
		} catch (IllegalStateException e) {       
			e.printStackTrace();       
		} catch (IOException e) {              
			e.printStackTrace();       
		} 
		Map<Object,Object> map = new HashMap<>();
		map.put("attachContenttype",new MimetypesFileTypeMap().getContentType(file));
		map.put("oldName", fileName);
		map.put("attachName",newName);
		map.put("absurl",realPath+filePath+newName);
		map.put("attachFileurl",filePath);
		map.put("attachFilesize", Integer.parseInt(String.valueOf(file.length())));
		Gson gson = new GsonBuilder().serializeNulls().create();
		String str = gson.toJson(map);
		return str;
    }
    
	/*@RequestMapping("/sendAttach")
	public String sendAttach(HttpServletRequest request,@RequestParam(value = "file", required = false)MultipartFile  multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String newName = UUIDUtils.generatorUUID()+suffix;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String filePath="/oa/sitemail/"+sdf.format(date)+"/"+newName;
		File file = new File(Constants.getFileuploadPath() + filePath);
		try {       
			if(!file.exists()){  
					file.mkdirs();  
			}  
			multipartFile.transferTo(file);    
		} catch (IllegalStateException e) {       
			e.printStackTrace();       
		} catch (IOException e) {              
			e.printStackTrace();       
		} 
		Map<Object,Object> map = new HashMap<>();
		map.put("attachContenttype",new MimetypesFileTypeMap().getContentType(file));
		map.put("oldName", fileName);
		map.put("attachName",newName);
		map.put("attachFileurl",filePath);
		map.put("attachFilesize", Integer.parseInt(String.valueOf(file.length())));
		map.put("attachFiletype", FileHelper.getFileFormat(file.getName()));
		Gson gson = new GsonBuilder().serializeNulls().create();
		String str = gson.toJson(map);
		return str;


	}*/
    
}
