package com.shangcg.controller;

import com.shangcg.base.RootTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * @version v1.0
 * @Description: TODO
 * @Author: shangcg
 * @Date: 2019/12/20
 */

public class UserControllerTest extends RootTest {


    @Test
    public void getUser() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/showUser")
                        .contentType(MediaType.TEXT_HTML_VALUE).characterEncoding("utf-8")
                        .param("userId", "8")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确

    }


    @Test
    public void testGetUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/showUser")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .param("userId", "8")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
    }


}