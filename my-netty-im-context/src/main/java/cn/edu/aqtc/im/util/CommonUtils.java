package cn.edu.aqtc.im.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 通用工具类
 * @ClassName: CommonUtils
 * @Author: zhangjj
 * @Date: 2020-04-16
 */

public class CommonUtils {

    /**
     * @Description: 判断对象是否为空
     * @Param: [object]
     * @return: boolean
     * @Author: zhangjj
     * @Date: 2020-04-16
     */
    public static boolean objectIsNull(Object object) {

        /*
         * 判断对象是否为空
         */
        if (object == null) {
            return true;
        }

        /*
         * 判断对象是否 List类型 如果是判断size大小
         */
        if (object instanceof List) {
            if (((List) object).size() <= 0) {
                return true;
            }
        }

        /*
         * 判断对象是否 Map类型 如果是判断size大小
         */
        if (object instanceof Map) {
            if (((Map) object).size() <= 0) {
                return true;
            }
        }

        /*
         * 判断对象是否 Set类型 如果是判断size大小
         */
        if (object instanceof Set) {
            if (((Set) object).size() <= 0) {
                return true;
            }
        }

        /*
         * 判断对象是否String 类型
         */
        if (object instanceof String) {
            if ("null".equals(object)) {
                return true;
            }
            return ((String) object).isEmpty();
        }

        return false;
    }

    /**
     * @Description: 将对象转换成json字符串
     * @Param: [obj]
     * @return: java.lang.String
     * @Author: zhangjj
     * @Date: 2020-04-16
     */
    public static String toJSONString(Object obj) {
        return JSONObject.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
    }
}
