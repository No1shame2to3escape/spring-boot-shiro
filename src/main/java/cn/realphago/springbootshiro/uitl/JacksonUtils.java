package cn.realphago.springbootshiro.uitl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/12 0:44
 */
public class JacksonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
