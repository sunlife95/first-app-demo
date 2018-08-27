package com.imooc.firstappdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class FileUpload {
    @RequestMapping(value = "/goUploadImg", method = RequestMethod.GET)
    public String goUploadImg() {
        /* 跳转到templates目录下的uploadimg.html */
        return "uploadimg";
    }

    @RequestMapping(value = "/testUploadImg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("contentType:" + contentType);
        System.out.println("fileName:" + fileName);
       String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
       File dir = new File(uploadDir);
       if(!dir.exists()){
           dir.mkdir();
       }
        System.out.println(uploadDir);
        if (!file.isEmpty()) {
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(uploadDir+file.getOriginalFilename()));
                bos = new BufferedOutputStream(fos);
                bos.write(file.getBytes());
                bos.flush();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败" + e.getMessage();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return "上传失败，文件为空！";
        }
        return "uploadimg success";
    }

    @RequestMapping(value = "/goManyUploadImg", method = RequestMethod.GET)
    public String goManyUploadImg() {
        /* 跳转到templates目录下的uploadimg.html */
        return "uploadManyimg";
    }

    /**
     * 多文件具体上传时，主要是使用了MultipartHttpServletRequest和MultipartFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/batch/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0 ; i<files.size() ; i++){
            file = files.get(i);
            if(!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return"You failed to upload " + i + " => " + e.getMessage();
                    }
                }
            }
        }
        return "upload many file success!";
    }
}
