package com.sys.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * @author rensf
 * @date 2021/6/24 17:55
 */
public class ToJson {

    public static JSONObject toJson(Map map) {
        if (map.isEmpty()) {
            return JSON.parseObject(map.toString());
        }
        JSONObject json = new JSONObject();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            json.put(entry.getKey().toString(), entry.getValue());
        }
        return json;
    }

}
