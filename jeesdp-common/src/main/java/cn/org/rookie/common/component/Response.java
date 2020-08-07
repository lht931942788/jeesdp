package cn.org.rookie.common.component;

import java.util.HashMap;

public class Response extends HashMap<String, Object> {

    private Response() {
    }

    public Response(int status) {
        put("status", status);
    }

    public Response(int status, String type, String code) {
        put("status", status);
        put("type", type);
        put("code", code);
    }

    public Response(int status, String type, String code, Object result) {
        put("status", status);
        put("type", type);
        put("code", code);
        put("result", result);
    }

    public static Response success() {
        return new Response(1);
    }

    public static Response success(String code) {
        return new Response(1, "success", code);
    }

    public static Response success(String code, Object result) {
        return new Response(1, "success", code, result);
    }

    public static Response error() {
        return new Response(0);
    }

    public static Response error(String code) {
        return new Response(0, "error", code);
    }

    public static Response error(String code, Object result) {
        return new Response(0, "error", code, result);
    }

    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
