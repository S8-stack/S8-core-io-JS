package com.s8.blocks.joos.fields;

import java.io.IOException;
import java.lang.reflect.Field;

import com.s8.blocks.joos.JOOS_Lexicon;
import com.s8.blocks.joos.composing.ComposingScope;
import com.s8.blocks.joos.composing.JOOS_ComposingException;

/**
 * 
 * @author pc
 *
 */
public abstract class PrimitiveFieldHandler extends FieldHandler {

	
	
	/**
	 * 
	 * @param name
	 * @param field
	 */
	public PrimitiveFieldHandler(String name, Field field) {
		super(name, field);
	}


	/**
	 * 
	 * @param object
	 * @param value
	 * @throws ParsingException 
	 * @throws Exception
	 */
	//public abstract void parse(Object object, String value) throws JOOS_ParsingException;
	
	

	
	
	/**
	 * 
	 * @param object
	 * @param writer
	 * @return has actually output something
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws JOOS_ComposingException 
	 */
	@Override
	public abstract boolean compose(Object object, ComposingScope scope) throws IOException, JOOS_ComposingException;


	
	@Override
	public Class<?> getSubType() {
		return null;
	}
	
	
	
	@Override
	public void subDiscover(JOOS_Lexicon context) {
		// nothing to sub-discover
	}
}
