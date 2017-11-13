package com.vn.hcmute.team.cortana.mymoney.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;

public class JsonResponse<T> {
    
    private JsonObject json;
    private Gson gson;
    private Class<T> clazz;
    
    public JsonResponse(Class<T> clazz) {
        this.json = new JsonObject();
        this.gson = new Gson();
        this.clazz = clazz;
    }
    
    public String getStatus() {
        return json.get("status").getAsString();
    }
    
    public void setStatus(String status) {
        json.addProperty("status", status);
    }
    
    public String getMessage() {
        return json.get("message").getAsString();
    }
    
    public void setMessage(String message) {
        json.addProperty("message", message);
    }
    
    public T getData() {
        return gson.fromJson(json.get("data"), clazz);
    }
    
    public void setData(T data) {
        json.add("data", gson.toJsonTree(data));
    }
    
    public void setListData(List<T> data) {
        json.add("data", gson.toJsonTree(data));
    }
    
    public String toString() {
        return json.toString();
    }
    
}
