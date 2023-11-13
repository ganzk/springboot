package com.example.springbootfile.controller;

import com.example.springbootfile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 下载
     * @param name
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download/{name}")
    @ResponseBody
    public void download(@PathVariable String name, HttpServletResponse response) throws IOException {

        System.out.println("==============");
        System.out.println(name);

//        fileService.download(name,response);
        fileService.download_1(name,response);

    }

    /**
     * 上传接口
     * @param file
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {

        fileService.upload(file);

    }

    @RequestMapping("/index")
    public String index() throws IOException {
        return "index.html";
    }



}
