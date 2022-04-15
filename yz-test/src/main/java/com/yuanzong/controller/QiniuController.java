package com.yuanzong.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yuanzong.beans.QiniuBean;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
public class QiniuController
{
    private QiniuBean qiniuBean;
    @PostConstruct
    private void init(){
        qiniuBean=new QiniuBean();
        qiniuBean.setQiniuAccessKey("fLSyh7804AixspGiwDhzVifuNc5e25D30YTMNubt");
        qiniuBean.setQiniuSecretKey("PacpKAM2pxDWFfsIk30Un4htrlI5w7wPrOvcKzGF");
        qiniuBean.setQiniuUrl("http://doc.xiangxue.xin/");
        qiniuBean.setQiniuBucketName("shangxue-doc");


    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String,Object> getToken(){
        HashMap<String,Object> res=new HashMap<>();
        Auth auth = Auth.create(qiniuBean.getQiniuAccessKey(), qiniuBean.getQiniuSecretKey());
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");

        String upToken = auth.uploadToken(qiniuBean.getQiniuBucketName(), null, 30000, putPolicy);
        res.put("uptoken",upToken);
        return res;
    }


    @RequestMapping("/upload")
    public String upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException
    {
        String dirPath = request.getServletContext().getRealPath("/upload");
        String prefix= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        String filePath = dirPath +"/"+System.currentTimeMillis()+prefix;
        File f = new File(filePath);

        file.transferTo(f);
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), qiniuBean.getQiniuBucketName()).toString();

        String key=null;
        Auth auth = Auth.create(qiniuBean.getQiniuAccessKey(), qiniuBean.getQiniuSecretKey());
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        try
        {
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()), fileRecorder);
            String upToken = auth.uploadToken(qiniuBean.getQiniuBucketName(), null, 30000, putPolicy);

            Response response = uploadManager.put(filePath,key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
