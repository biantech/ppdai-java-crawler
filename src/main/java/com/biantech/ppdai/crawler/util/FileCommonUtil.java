package com.biantech.ppdai.crawler.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileCommonUtil {
    Logger logger = LoggerFactory.getLogger(FileCommonUtil.class);
    public static void main(String [] args){
        try {
            FileCommonUtil utils = new FileCommonUtil();

            utils.renameMP3File();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void renameMP3File() throws IOException {
        String dir="E:\\english\\零起点口语特训-高级篇";
        logger.info(dir);
        File file = new File(dir);
        if(file.exists()){
            String[] fileNameList = file.list();
            for(String oneFileName :fileNameList){
                if(oneFileName.endsWith("mp3")){
                    if(oneFileName.length()<=6){
                        File oneTempFile = new File(dir+"\\"+oneFileName);
                        if(oneTempFile.exists()) {
                            String tempFileName = dir + "\\0" + oneFileName;
                            File tempRenameFile = new File(tempFileName);
                            boolean result=oneTempFile.renameTo(tempRenameFile);
                            logger.info("result="+result);
                            //FileUtils.copyFile(oneTempFile, tempRenameFile);
                        }
                    }
                }
            }
        }
    }

}
