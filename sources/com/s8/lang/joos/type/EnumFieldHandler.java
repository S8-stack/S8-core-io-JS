package com.s8.lang.joos.type;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.s8.lang.joos.composing.ComposingScope;
import com.s8.lang.joos.composing.JOOS_ComposingException;
import com.s8.lang.joos.parsing.JOOS_ParsingException;

public class EnumFieldHandler extends PrimitiveFieldHandler {

	private Map<String, Object> map;

	public EnumFieldHandler(String name, Field field) {
		super(name, field);
		Class<?> enumType = field.getType();
		map = new HashMap<>();
		for(Object enumInstance : enumType.getEnumConstants()){
			map.put(enumInstance.toString(), enumInstance);
		}
	}


	@Override
	public void parse(Object object, String value) throws JOOS_ParsingException {
		try {
			field.set(object, map.get(value));
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new JOOS_ParsingException("Cannot set interger due to "+e.getMessage());
		}
	}

	@Override
	public boolean compose(Object object, ComposingScope scope) 
			throws IOException, JOOS_ComposingException  {

		scope.newItem();
		scope.append(name);
		scope.append(": ");
		
		Object item;
		try {
			item = field.get(object);
		} 
		catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new JOOS_ComposingException(e.getMessage());
		}
		if(item!=null) {
			scope.append(item.toString());
		}
		else {
			scope.append("NONE");	
		}
		return true;
	}

	/*
	@Override
	public String get(Object object) throws IllegalArgumentException, IllegalAccessException {
		return Short.toString(field.getShort(object));
	}
	 */
}
