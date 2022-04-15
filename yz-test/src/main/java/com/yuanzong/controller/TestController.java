package com.yuanzong.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @RequestMapping(value = "/file")
    public String file(Map<String, Object> model){
        return "file";
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
    @RequestMapping(value = "upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException
    {
//        String dirPath=request.getServletContext().getRealPath("/upload");
//        File dirFile=new File(dirPath);
//        if(!dirFile.exists()){
//            dirFile.mkdirs();
//        }
//        String fileName= UUID.randomUUID().toString().replace("-","");
        ByteArrayInputStream bis=new ByteArrayInputStream(file.getBytes());
        byte[] b=new byte[1024];
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        int len=0;
        while((len=bis.read(b))!=-1){
            bos.write(b);
        }
        System.out.println(bos.toByteArray());
        bos.close();
        bis.close();
        return "success";
    }

}
