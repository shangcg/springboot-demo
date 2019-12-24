package com.shangcg.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @Description: junit和mock单元测试示例
 * @Author: shangcg
 * @Date: 2019/12/24
 */

@RestController
public class UnitDemoController {

    @RequestMapping(value = "/hello.json", method = RequestMethod.GET)
    public String getListTag(HttpServletRequest request,
                             @RequestParam(value = "name", required = false, defaultValue = "0") String name) {
        try {
            return "hello :" + name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello everyone !";
    }

    @RequestMapping(value = "/save.json", method = RequestMethod.POST)
    public String saveTag(HttpServletRequest request,
                          @RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "level", required = true) Integer level) {
        try {
            return "recive your param " + "name: " + name + " level: " + level;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
