package util;

import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Bean转换Map工具类
 *
 * @author 蝈蝈
 * @since 0.1.0
 */
public class BeanToMapUtil {

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)  {
            return null;
        }
        if(obj instanceof Map){
            return (Map<String, Object>) obj;
        }

        Map<String, Object> map = Maps.newHashMap();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            if (value instanceof Date) {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put(key, sf.format(value));
            }else{
                map.put(key, value);
            }
        }
        return map;
    }
}
