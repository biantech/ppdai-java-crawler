package com.biantech.ppdai.test;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FundSortTester {
    Logger logger = LoggerFactory.getLogger(FundSortTester.class);

    @Test
    public void fundSortTest() throws IOException {
        String fileName="D:\\FinanceDoc\\fundTemp300.txt";
        fundSort(fileName,"混合");
    }
    public void fundSort(String fileName,String sortType) throws IOException {
        HashMap<String, HashMap<String,String>> fundMap= new HashMap<>();
        Properties prop = new Properties();
        List<String> fundList= FileUtils.readLines(new File(fileName),"utf-8");
        for(String fundName :fundList){
            String preFundCorp = StringUtils.substring(fundName,0,2);
            HashMap<String,String> fundNameMap  = fundMap.get(preFundCorp);
            if(fundNameMap==null){
                fundNameMap = new HashMap<String,String>();
                fundMap.put(preFundCorp,fundNameMap);
            }
            fundNameMap.put(fundName,fundName);
            fundMap.put(preFundCorp,fundNameMap);
        }
        //Map.Entry<String,HashMap<String,String>> entry;
        TreeMap<Integer,String> sortedMap = new TreeMap();
        Set<Map.Entry<String,HashMap<String,String>>> set= fundMap.entrySet();
        ArrayList<FundCorpItem> listFundCorpItem = new ArrayList<FundCorpItem>();
        for(Map.Entry<String,HashMap<String,String>> entry:set){
          //logger.info(entry.getKey()+","+entry.getValue().size());
          listFundCorpItem.add(new FundCorpItem(entry.getValue().size(),entry.getKey()));
          //sortedMap.put(entry.getValue().size(),entry.getKey());
        }
        //Collections.sort(listFundCorpItem,Comparator.comparing(FundCorpItem:fundNum)(FundCorpItem item1,FundCorpItem item2)->item1.fundNum-item2.fundNum);
        Collections.sort(listFundCorpItem,Comparator.comparing(FundCorpItem::getFundNum));
        //logger.info(""+listFundCorpItem);
        for(FundCorpItem item:listFundCorpItem){
            logger.info(item.getCorpName()+","+item.getFundNum());
        }
    }



    class FundCorpItem{
        public FundCorpItem(int fundNum2,String corpName2){
            this.fundNum = fundNum2;
            this.corpName = corpName2;
        }
        public int fundNum;

        public int getFundNum() {
            return fundNum;
        }

        public void setFundNum(int fundNum) {
            this.fundNum = fundNum;
        }

        public String getCorpName() {
            return corpName;
        }

        public void setCorpName(String corpName) {
            this.corpName = corpName;
        }

        public String corpName;

        @Override
        public String toString() {
            return  corpName+","+ fundNum + '\'' ;
        }
    }
}
