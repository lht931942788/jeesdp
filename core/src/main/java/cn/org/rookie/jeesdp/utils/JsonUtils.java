package cn.org.rookie.jeesdp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * JSON工具类
 *
 * @author LIHAITAO
 */

public abstract class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 将对象转为JSON字符串
     *
     * @param bean 要转换的实体
     * @return 转换后JSON字符串
     */

    public static String toJsonString(Object bean) {
        try {
            return MAPPER.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象转为格式化的JSON字符串
     *
     * @param bean 要转换的实体
     * @return 转换后JSON字符串
     */

    public static String toIndentedJsonString(Object bean) {
        try {
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonString = MAPPER.writeValueAsString(bean);
            MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON字符串转为对象
     *
     * @param json JSON字符串
     * @param type 要转换的对象的类型
     * @param <T>  转换后的类型
     * @return 转换后的对象
     */

    public static <T> T toObject(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串转换为JSON对象
     *
     * @param json 要转换的字符串
     * @return 返回JSON对象
     */

    public static JsonNode toJsonNode(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将实体转换为JSON对象
     *
     * @param bean 要转换的实体
     * @return 返回JSON对象
     */

    public static JsonNode toJsonNode(Object bean) {
        return MAPPER.valueToTree(bean);
    }

}
