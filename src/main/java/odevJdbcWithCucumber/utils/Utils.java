package odevJdbcWithCucumber.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String getRandom(String val){
        if (val.startsWith("{")){
            val = val.replaceAll("[{}]", "");
            String[] arr = val.split("-");
            switch (arr[0].toLowerCase()){
                case "int":
                    return getRandomNumber(Integer.parseInt(arr[1]));
                default:
                    return getRandomString(Integer.parseInt(arr[1]));
            }
        }else{
            return val;
        }
    }

    public static String getRandomString(int len){
        return RandomStringUtils.randomAlphabetic(len);
    }

    public static String getRandomNumber(int len){
        return RandomStringUtils.randomNumeric(len, len);
    }
}
