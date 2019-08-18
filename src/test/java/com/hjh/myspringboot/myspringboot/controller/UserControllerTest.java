package com.hjh.myspringboot.myspringboot.controller;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-16 21:38
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenAddSuccess() throws Exception {
        String content = "{\"username\":hjh,\"password\":null,\"createDate\":"+new Date().getTime()+"}";
        String result = mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))  //请求
                .andExpect(status().isOk()) //断言响应结果
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();
        log.info("增加结果；"+result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"username\":\"hjh\",\"password\":null,\"createDate\":"+new Date().getTime()+"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))  //请求
                .andExpect(status().isOk()) //断言响应结果
                .andReturn().getResponse().getContentAsString();
        log.info("修改结果；"+result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()) //断言响应结果
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void whenGetSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()) //响应结果
                .andReturn().getResponse().getContentAsString();
        log.info("获得结果；"+result);
    }

    @Test
    public void whenListSuccess() throws Exception {
        String result = mockMvc.perform(get("/user").param("username","hjh")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()) //断言响应结果
                .andReturn().getResponse().getContentAsString();
        log.info("查询结果；"+result);
    }

    @Test
    public void validatorTest() throws Exception {
        String content = "{\"username\":null,\"password\":null,\"createDate\":"+new Date().getTime()+"}";

        String result = mockMvc.perform(put("/user/test/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk()) //断言响应结果
                .andReturn().getResponse().getContentAsString();
        log.info("查询结果；"+result);
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(multipart("/file")
        .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello".getBytes("utf-8"))))
                .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
        log.info("上传结果"+result);
    }
}
