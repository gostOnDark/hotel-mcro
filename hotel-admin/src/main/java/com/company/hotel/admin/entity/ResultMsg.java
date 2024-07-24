package com.company.hotel.admin.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;

/**
 * 结果实体类.
 */
@Data
public class ResultMsg {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Integer status;
    private String msg;
    private Object data;

    public ResultMsg(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultMsg(Object data) {
        this.data = data;
        this.msg = "ok";
        this.status = 200;
    }

    public ResultMsg() {
    }

    public static ResultMsg build(Integer status, String msg, Object data) {
        return new ResultMsg(status, msg, data);
    }

    public static ResultMsg ok(Object data) {
        return new ResultMsg(data);
    }

    public static ResultMsg ok() {
        return new ResultMsg(null);
    }

    public static ResultMsg build(Integer status, String msg) {
        return new ResultMsg(status, msg, null);
    }

    /**
     * 将json结果集转为ResultMsg对象.
     * @param jsonData json数据.
     * @param clazz ResultMsg的object类型.
     * @return ResultMsg对象.
     */
    public static ResultMsg formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultMsg.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null){
                if (data.isObject()){
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ResultMsg对象.
     * @param json
     * @return
     */
    public static ResultMsg format(String json){
        try {
            return MAPPER.readValue(json, ResultMsg.class);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    /**
     * Object是集合转化
     * @param jsonData
     * @param clazz
     * @return
     */
    public static ResultMsg formatToList(String jsonData,Class<?> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0 ){
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(),obj);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
