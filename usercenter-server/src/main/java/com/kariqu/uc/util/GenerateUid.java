package com.kariqu.uc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: SanAction
 * @Date:2014/7/4 15:02
 * @Version: 1.0.
 * 生成uuid
 */
public class GenerateUid {

    /**
     * 生成uid通过日期+6位随机数
     * @return String
     */
    public static  String  creatUid(){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            String preUid=sdf.format(new Date());
            String nextUid="";
            while(nextUid.length()<6){
                nextUid+=(int)(Math.random()*10);
            }
            return preUid+nextUid;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        System.out.println(creatUid());
    }
}
