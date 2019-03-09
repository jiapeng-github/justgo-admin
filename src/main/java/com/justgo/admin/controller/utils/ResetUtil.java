package com.justgo.admin.controller.utils;

import java.lang.reflect.Field;

public class ResetUtil<E> {

    public ResetUtil() {
    }

    //重置属性，把""改为null
    public static <E> void resetObject(E bo){
        if (bo == null) {
            return;
        }
        for (Class<?> clazz = bo.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] f = clazz.getDeclaredFields();
            for (Field aF : f) {
                aF.setAccessible(true);
                try {
                    if (aF.getType() == String.class && "".equals(aF.get(bo))) {
                        aF.set(bo, null);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
