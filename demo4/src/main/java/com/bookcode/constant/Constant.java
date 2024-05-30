package com.bookcode.constant;

import java.util.HashMap;
import java.util.Map;
public class Constant {
    public static final String name = "蔡琴";
    public static final Map<String, Object> map;

    static {
        map = new HashMap<String, Object>();
        map.put("歌手",name);
        map.put("歌曲","被遗忘的时光");
    }
}