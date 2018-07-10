package com.yuanzong.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiechaofeng
 * @date 2018/6/26 10:39
 * @desc
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    Configuration cfg;
    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model){
        model.put("descrip", "adam");
        return "demo";
    }
    @RequestMapping(value = "out")
    public void put(@RequestParam String name){
        try {
            String w="Welcome FreeMarker!";
            Map root = new HashMap();
            root.put("descrip",w);
            Template temp = cfg.getTemplate("demo.ftl");

            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path= ResourceUtils.getURL("classpath:").getPath()+"static/test.html";
            File file=new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            Writer file2 = new FileWriter(new File(path));
            temp.process(root, file2);
            file2.flush();
            file2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
