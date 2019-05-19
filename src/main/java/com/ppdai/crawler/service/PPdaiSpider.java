package com.ppdai.crawler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class PPdaiSpider implements PageProcessor {
        Logger logger = LoggerFactory.getLogger(PPdaiSpider.class);

        private Site site = Site.me()
                .setRetryTimes(3)
                .setSleepTime(1000)
                .setCharset("UTF-8")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding","gzip, deflate, br")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Connection","keep-alive")
                //.addHeader("Content-Length","23")
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
               // .addHeader("Cookie","Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1516684224,1516688332,1516708458,1517989813; _ga=GA1.2.803780703.1515996477; user_trace_token=20180115140756-6e315eee-f9ba-11e7-a353-5254005c3644; LGUID=20180115140756-6e316229-f9ba-11e7-a353-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=ABAAABAAADEAAFI7B8A950147564B82F61A115D162E1281; LGSID=20180207155015-888d0972-0bdb-11e8-bdd2-525400f775ce; LGRID=20180207163606-f07d2f3d-0be1-11e8-af98-5254005c3644; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517992563; TG-TRACK-CODE=index_navigation; SEARCH_ID=ada31aea74d74f0ba5625adf851d1c6f; X_HTTP_TOKEN=4235610f3926fcdc9a4b942f0c350399; _putrc=0DA9DA012C722A8E; login=true; unick=%E7%8E%8B%E5%86%B6; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=0; gate_login_token=fc49718b5340e22bfe7adebb2937015b765f94906d1f154c; _gat=1")
                .addHeader("Host","www.lagou.com")
                .addHeader("Origin","https://www.lagou.com")
                .addHeader("Referer","https://www.lagou.com/jobs/list_Java")
                .addHeader("User-Agent","-Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3095.5 Mobile Safari/537.36")
                .addHeader("X-Anit-Forge-Code","0")
                .addHeader("X-Anit-Forge-Token","None")
                .addHeader("X-Requested-With","XMLHttpRequest");


    private Site site2 = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        String rawText=page.getRawText();
        logger.info(rawText);
        //page.putField("position",new JsonPathSelector("$.content.positionResult.result[*].positionName").selectList(page.getRawText()));
    }

    @Override
    public Site getSite() {
          return site2;
    }
}
