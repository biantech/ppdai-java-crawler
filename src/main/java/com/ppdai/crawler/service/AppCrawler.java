package com.ppdai.crawler.service;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

public class AppCrawler {


    public static void main(String []argv)
    {
        //Spider.create(new LaGouSpider())
        //        .addUrl("https://www.lagou.com/jobs/positionAjax.json?px=default&city=北京&needAddtionalResult=false&isSchoolJob=0")
        //        .thread(2)
        //        .run();
        Spider.create(new PPdaiSpider())
                .addUrl("https://invest.ppdai.com/loan/listpage/")
                //.addPipeline(new JsonFilePipeline("D:/temp"))
                .thread(1)
                .run();
        System.out.println("finished");
    }

}
