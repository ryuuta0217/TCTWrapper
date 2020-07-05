package com.ryuuta0217.tctwrapper.util;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class ReflectionUtil {

    @Nullable
    public static Field getStaticField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getField(fieldName);
        } catch(NoSuchFieldException e) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    @Nullable
    public static Object getStaticFieldValue(Class<?> clazz, String fieldName) {
        Field targetField = getStaticField(clazz, fieldName);
        if(targetField == null) return null;

        try {
            targetField.setAccessible(true);
            return targetField.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setStaticField(Class<?> clazz, String fieldName, Object value) {
        try {
            Field targetField = getStaticField(clazz, fieldName);
            if(targetField == null) return;
            targetField.setAccessible(true);
            targetField.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
