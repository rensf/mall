package com.sys.common.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author rensf
 * @date 2021/6/24
 */
public class JsonUtils {

    /**
     * Map转换JSONObject
     * [Key必须是String或其子类]
     * @param map
     * @return
     */
    public static JSONObject mapToJson(Map<String, Object> map) {
        if (map.isEmpty()) {
            return JSON.parseObject(map.toString());
        }
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json;
    }

}
