package com.shangcg.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;


public final class JacksonUtil {
    private JacksonUtil() {
        throw new IllegalStateException("error");
    }

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectMapper SORTED_MAPPER = new ObjectMapper();

    private static final String KEY_REGEX = "\"__KEY__\":.*?(,|})";
    private static final String VALUE_TEMPLATE = "\"__KEY__\":\"__VALUE__\",";

    private static final String KEY = "__KEY__";
    private static final String VALUE = "__VALUE__";

    static {
        SORTED_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new RuntimeException(String.format("JSON_PARSE_ERROR,content:%s", jsonStr), e);
        }
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {

        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(String.format("JSON_PARSE_ERROR,content:%s", jsonStr), e);
        }
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param content
     * @return
     */
    public static JsonNode readValue(String content) {

        try {
            return OBJECT_MAPPER.readTree(content);
        } catch (Exception e) {
            throw new RuntimeException(String.format("JSON_PARSE_ERROR,content:%s", content), e);
        }
    }

    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 对json字符串排序
     * 1）按照字段名称进行排序
     * 2) 对array进行排序（按照内部json字符串排序）
     * @param jsonStr
     * @return sortedJsonStr
     */
    public static String sort(String jsonStr) {

        jsonStr = sortJsonKey(jsonStr);
        jsonStr = sortJsonArray(jsonStr);
        return jsonStr;
    }

    /***
     * 按照字段名称进行排序
     * @param content
     * @return sortedJsonStr
     */
    private static String sortJsonKey(String content) {
        JsonNode jsonNode = readValue(content);
        try {
            Object obj = SORTED_MAPPER.treeToValue(jsonNode, Object.class);
            return SORTED_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("JSON_PARSE_ERROR,content:%s", content), e);
        }
    }

    /***
     * 对array进行排序（按照json字符串排序）
     * @param content
     * @return sortedJsonStr
     */
    private static String sortJsonArray(String content) {
        JsonNode jsonNode = readValue(content);
        if (jsonNode == null) {
            return null;
        }
        String sortedJsonStr = null;
        try {
            if(jsonNode.isArray()){
                sortedJsonStr = SORTED_MAPPER.writeValueAsString(sortJsonArray(jsonNode));
            }else{
                sortedJsonStr = SORTED_MAPPER.writeValueAsString(sortJsonObject(jsonNode));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("JSON_PARSE_ERROR,content:%s", content), e);
        }
        return sortedJsonStr;
    }

    /***
     * 递归对array进行排序
     * @param jsonNode
     * @return
     */
    private static ObjectNode sortJsonObject(JsonNode jsonNode) {
        ObjectNode newJsonObject = new ObjectMapper().createObjectNode();
        Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> entry = it.next();
            String key = entry.getKey();
            JsonNode jsonNodeI = entry.getValue();
            if (jsonNodeI.isArray()) {
                jsonNodeI = sortJsonArray(jsonNodeI);
            } else if (jsonNodeI.isObject()) {
                jsonNodeI = sortJsonObject(jsonNodeI);
            }
            newJsonObject.set(key, jsonNodeI);
        }

        return newJsonObject;
    }


    private static ArrayNode sortJsonArray(JsonNode jsonNode) {
        ArrayNode arrayNode = new ObjectMapper().createArrayNode();
        List<JsonNode> jsonValues = new ArrayList<>();
        for (JsonNode jsonNodeI : jsonNode) {
            jsonNodeI = sortJsonObject(jsonNodeI);
            jsonValues.add(jsonNodeI);
        }
        Collections.sort(jsonValues, (o1, o2) -> {
            return o1.toString().compareTo(o2.toString());
        });
        arrayNode.addAll(jsonValues);
        return arrayNode;
    }

    /**
     * 比较两个json是否相同，相同返回true
     *
     * @param originJson
     * @param targetJson
     * @return
     */
    public static boolean diff(String originJson, String targetJson) {
        if (originJson != null && originJson.equals(targetJson)) {
            return Boolean.TRUE;
        }
        if (originJson == null && targetJson == null) {
            return Boolean.TRUE;
        }
        if (originJson == null || targetJson == null) {
            return Boolean.FALSE;
        }
        String originJsonSort = sort(originJson);
        String targetJsonSort = sort(targetJson);
        return originJsonSort.equals(targetJsonSort);
    }

    /**
     * 替换json中所有与key相同键值对
     *
     * @param source json
     * @param key
     * @param value
     * @return
     */
    public static String replaceBykey(String source, String key, String value) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(key);
        String regex = KEY_REGEX.replace(KEY, key);
        String newValue = VALUE_TEMPLATE.replace(KEY, key).replace(VALUE, value);
        return source.replaceAll(regex, newValue);
    }

}