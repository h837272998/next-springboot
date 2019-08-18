package com.hjh.myspringboot.myspringboot.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 20:51
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController  {

    public static final String  FOLDER = "D:/U";

    @PostMapping
    public Map upload(MultipartFile file) throws IOException {
        log.info("上传文件名"+file.getName());
        log.info("上传文件原始名"+file.getOriginalFilename());
        log.info("上传文件大小："+file.getSize());


        File file1 = new File(FOLDER,System.currentTimeMillis()+".txt");
        file.transferTo(file1);
        Map<String, String> map = new HashMap<>();
        map.put("path",file1.getAbsolutePath());
        return map;
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletResponse response, HttpServletRequest request) throws IOException {

        try(FileInputStream inputStream = new FileInputStream(new File(FOLDER, id + ".txt"));
            OutputStream outputStream = response.getOutputStream()) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }

}
