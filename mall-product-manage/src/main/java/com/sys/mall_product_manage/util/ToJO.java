package com.sys.mall_product_manage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class ToJO {

    public static JSONObject toJO(Map map) {
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
