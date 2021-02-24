/*
package cn.org.rookie.jeesdp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

*/
/**
 * JSON工具类
 *
 * @author LIHAITAO
 * <p>
 * 将对象转为JSON字符串
 * @param bean 要转换的实体
 * @return 转换后JSON字符串
 * <p>
 * 将JSON字符串转为对象
 * @param json JSON字符串
 * @param type 要转换的对象的类型
 * @param <T>  转换后的类型
 * @return 转换后的对象
 * <p>
 * 将字符串转换为JSON对象
 * @param json 要转换的字符串
 * @return 返回JSON对象
 * <p>
 * 将实体转换为JSON对象
 * @param bean 要转换的实体
 * @return 返回JSON对象
 *//*


public abstract class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    */
/**
 * 将对象转为JSON字符串
 *
 * @param bean 要转换的实体
 * @return 转换后JSON字符串
 *//*


    public static String toJsonString(Object bean) {
        try {
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
 * 将JSON字符串转为对象
 *
 * @param json JSON字符串
 * @param type 要转换的对象的类型
 * @param <T>  转换后的类型
 * @return 转换后的对象
 *//*


    public static <T> T toObject(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
 * 将字符串转换为JSON对象
 *
 * @param json 要转换的字符串
 * @return 返回JSON对象
 *//*


    public static JsonNode toJsonNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
 * 将实体转换为JSON对象
 *
 * @param bean 要转换的实体
 * @return 返回JSON对象
 *//*


    public static JsonNode toJsonNode(Object bean) {
        return mapper.valueToTree(bean);
    }

}
*/
