package cn.org.rookie.component;

import java.util.HashMap;

public class Response extends HashMap<String, Object> {

    private Response() {
    }

    public Response(int status) {
        put("status", status);
    }

    public Response(int status, String type, String message) {
        put("status", status);
        put("type", type);
        put("message", message);
    }

    public Response(int status, String type, Object data) {
        put("status", status);
        put("type", type);
        put("data", data);
    }

    public Response(int status, String type, String message, Object data) {
        put("status", status);
        put("type", type);
        put("message", message);
        put("data", data);
    }

    public static Response success() {
        return new Response(1);
    }

    public static Response success(String message) {
        return new Response(1, "success", message);
    }

    public static Response success(Object data) {
        return new Response(1, "success", data);
    }

    public static Response success(String message, Object data) {
        return new Response(1, "success", message, data);
    }

    public static Response error() {
        return new Response(0);
    }

    public static Response error(String message) {
        return new Response(0, "error", message);
    }

    public static Response error(String message, Object data) {
        return new Response(0, "error", message, data);
    }

    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
