package cn.jackbin.easyexcelplus.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassUtil {
    /**
     * 获取属性值
     */
    public static Object getProperty(Field field, Object instance, Class<?> clazz) {
        try {
//            declaredField.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            Method readMethod = pd.getReadMethod();
            return readMethod.invoke(instance);
        } catch (InvocationTargetException | IllegalAccessException | IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
