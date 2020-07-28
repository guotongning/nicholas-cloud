package com.ning.springcloud.baseutils.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @date 2020/7/24 16:08
 * @created by 不在能知，乃在能行 ——nicholas
 */
public class ProvenceIdUtil {
    private static Map<String, String> provenceIds = new HashMap<>();

    /**
     * 没有港澳台
     *
     * @param args
     */
    public static void main(String[] args) {
        getByNumberOrDesc("山西:1", "甘肃:2", "湖北:4");
        getByNumberOrDesc("14:1", "62:2", "42:4");
    }

    public static void getByNumberOrDesc(String... numberOrDesc) {
        StringBuilder sb = new StringBuilder();
        for (String s : numberOrDesc) {
            String[] split = s.split(":");
            sb.append(provenceIds.get(split[0])).append(":").append(split[1]).append(",");
        }
        String config = sb.toString();
        System.out.println(config.substring(0, config.length() - 1));
    }

    static {
        provenceIds.put("广东", "44");
        provenceIds.put("山东", "37");
        provenceIds.put("江苏", "32");
        provenceIds.put("河南", "41");
        provenceIds.put("河北", "13");
        provenceIds.put("浙江", "33");
        provenceIds.put("陕西", "61");
        provenceIds.put("湖南", "43");
        provenceIds.put("福建", "35");
        provenceIds.put("云南", "53");
        provenceIds.put("四川", "51");
        provenceIds.put("广西", "45");
        provenceIds.put("安徽", "34");
        provenceIds.put("海南", "46");
        provenceIds.put("江西", "36");
        provenceIds.put("湖北", "42");
        provenceIds.put("山西", "14");
        provenceIds.put("辽宁", "21");
        provenceIds.put("黑龙江", "23");
        provenceIds.put("内蒙古", "15");
        provenceIds.put("贵州", "52");
        provenceIds.put("甘肃", "62");
        provenceIds.put("青海", "63");
        provenceIds.put("新疆", "65");
        provenceIds.put("西藏", "54");
        provenceIds.put("吉林", "22");
        provenceIds.put("宁夏", "64");
        provenceIds.put("北京", "11");
        provenceIds.put("天津", "12");
        provenceIds.put("重庆", "50");
        provenceIds.put("上海", "31");

        provenceIds.put("44", "广东");
        provenceIds.put("37", "山东");
        provenceIds.put("32", "江苏");
        provenceIds.put("41", "河南");
        provenceIds.put("13", "河北");
        provenceIds.put("33", "浙江");
        provenceIds.put("61", "陕西");
        provenceIds.put("43", "湖南");
        provenceIds.put("35", "福建");
        provenceIds.put("53", "云南");
        provenceIds.put("51", "四川");
        provenceIds.put("45", "广西");
        provenceIds.put("34", "安徽");
        provenceIds.put("46", "海南");
        provenceIds.put("36", "江西");
        provenceIds.put("42", "湖北");
        provenceIds.put("14", "山西");
        provenceIds.put("21", "辽宁");
        provenceIds.put("23", "黑龙江");
        provenceIds.put("15", "内蒙古");
        provenceIds.put("52", "贵州");
        provenceIds.put("62", "甘肃");
        provenceIds.put("63", "青海");
        provenceIds.put("65", "新疆");
        provenceIds.put("54", "西藏");
        provenceIds.put("22", "吉林");
        provenceIds.put("64", "宁夏");
        provenceIds.put("11", "北京");
        provenceIds.put("12", "天津");
        provenceIds.put("50", "重庆");
        provenceIds.put("31", "上海");
    }
}
