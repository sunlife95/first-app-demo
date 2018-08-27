package com.imooc.firstappdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 模板测试
 * @Author sunlingfu
 */
@Controller
public class TemplateController {
    /**
     * 返回HTML模板
     */
    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String ,Object> map, HttpSession session){
        map.put("hello","from TemplateController.helloHtml");
        System.out.println(session.getAttribute("test"));
        session.invalidate();
        return "/helloHtml";
    }
    /**
     * 返回HTML模板
     */
    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String ,Object> map, HttpSession session){
        map.put("hello","from TemplateController.helloFtl");
        session.setAttribute("test","nihao");
        return"/helloFtl";
    }
}
