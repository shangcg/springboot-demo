package com.shangcg.controller;

import com.shangcg.base.RootTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @version v1.0
 * @Description: TODO
 * @Author: shangcg
 * @Date: 2019/12/24
 */


public class JunitDemoControllerTest extends RootTest {



    @Test  //对get方法的测试
    public void testGetListTag() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/hello.json")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .param("name", "shangcg")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("hello :shangcg", content);
    }

    @Test //对post测试
    public void saveTag() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/save.json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "shangcg")
                        .param("level", "1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("recive your param name: shangcg level: 1", content);
    }
}