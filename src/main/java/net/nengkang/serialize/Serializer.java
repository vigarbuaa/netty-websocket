package net.nengkang.serialize;

import net.mengkang.serialize.impl.JSONSerializer;

/**
 * @date 2020-03-05
 * 
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();

    /**
     * java 对象转换成String
     */
    String serialize(Object object);

    /**
     * String转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, String text);
}
