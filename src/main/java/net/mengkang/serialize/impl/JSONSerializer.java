package net.mengkang.serialize.impl;

import com.alibaba.fastjson.JSON;

import net.nengkang.serialize.Serializer;

public class JSONSerializer implements Serializer {

	@Override
	public String serialize(Object object) {
		return JSON.toJSONString(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, String text) {
		return JSON.parseObject(text, clazz);
	}
}