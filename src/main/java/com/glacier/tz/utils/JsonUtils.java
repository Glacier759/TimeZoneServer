package com.glacier.tz.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public class JsonUtils {

    private JSONObject result = new JSONObject();

    public void setStatus(Integer status) {
        result.put("status", status);
    }

    public void setInfoByObject(JSONObject info) {
        result.put("info", info);
    }

    public void setInfoByString(String info) {
        result.put("info", info);
    }

    public JSONObject getResult() {
        return result;
    }

    public String toString() {
        return result.toString();
    }
}
