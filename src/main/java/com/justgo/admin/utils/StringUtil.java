package com.justgo.admin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理
 * Created by fancz on 2017/12/6.
 */
public class StringUtil {

    /**
     * 隐藏中间部分信息(根据字符串长度自动判断需隐藏的长度)
     * @param str
     * @return
     */
    public static String hidePartInfo(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        int keepLength = str.length()/3;
        String regex;
        if (m.find()){
            //中文
            regex = "([^(\\\\u4e00-\\\\u9fa5)]{"+(keepLength==0?1:str.length()==4?keepLength+1:keepLength)+"})([^(\\\\u4e00-\\\\u9fa5)]+)([^(\\\\u4e00-\\\\u9fa5)]{"+(keepLength)+"})";
            return str.replaceAll(regex, str.length()==4?"$1"+createAsterisk(str.length()-2):"$1"+createAsterisk(keepLength==0?1:keepLength)+"$3");
        }else {
            regex = "(\\w{"+(keepLength<=2?1:keepLength-2)+"})(\\w{"+(keepLength<2?1:keepLength==2?keepLength+2:keepLength+4)+"})(\\w{"+(keepLength<=2?1:keepLength-2)+"})";
            return str.replaceAll(regex,keepLength<2?"$1"+createAsterisk(1)+"$3":"$1"+createAsterisk(keepLength)+"$3");
        }
    }

    //生成很多个*号
    public static String createAsterisk(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
//        String email = "440981199202198650";
        String email = "123";
//        String email = "古力娜扎" ;
        String str = hidePartInfo(email);
        System.out.println(str);
    }
}
