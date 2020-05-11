package com.jennery.blog.spider.context;

import com.jennery.blog.spider.constant.Constant;
import com.jennery.blog.spider.util.SpiderHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class QiankuVideoSpider {

    private String qiankuFilePath = "D:\\spider\\qianku\\";

    @PostConstruct
    public void getAllVideo() {
        Elements elements = SpiderHttpUtil.getElements(Constant.QIANKU_VIDEO_URL, HttpMethod.GET, "body > div.module-box > div.module-con > ul > li");
        final List<String> hrefList = SpiderHttpUtil.dealSubSelector(elements, "a", "href");
        for (String href : hrefList) {
            getPerCategory(SpiderHttpUtil.reviseUrl(href));
        }
    }


    public void getPerCategory(String href) {
        Elements elements = SpiderHttpUtil.getElements(href, HttpMethod.GET, "body > div.result-box.video > div > div.w1440.data-box > div.clearfix.data-list > div");
        final List<String> srcList = SpiderHttpUtil.dealSubSelector(elements, "div > a > div.img-box-c > video", "src");
        for (String src : srcList) {
            downloadPerVideo(SpiderHttpUtil.reviseUrl(src));
        }
    }




    public void downloadPerVideo(String href) {
        log.info("start to downloaded video from href ==> [{}]", href);
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            String name = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
            final URL url = new URL("");
            File localFile = new File(qiankuFilePath + name);
            fos = new FileOutputStream(localFile);
            inputStream = url.openConnection().getInputStream();
            FileCopyUtils.copy(inputStream, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
