package com.s8.lang.joos.type.primitives;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.s8.lang.joos.composing.ComposingScope;
import com.s8.lang.joos.parsing.JOOS_ParsingException;
import com.s8.lang.joos.parsing.ParsingScope;
import com.s8.lang.joos.parsing.ParsingScope.OnParsedObject;
import com.s8.lang.joos.parsing.primitives.IntegerArrayScope;
import com.s8.lang.joos.type.PrimitivesArrayFieldHandler;

public class IntegerArrayFieldHandler extends PrimitivesArrayFieldHandler {

	public IntegerArrayFieldHandler(String name, Field field) {
		super(name, field);
	}

	@Override
	public boolean isItemValid(Object array, int index) {
		return true; // always valid
	}
	
	@Override
	public void composeItem(Object array, int index, ComposingScope scope) 
			throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
		scope.append(Integer.toString(Array.getInt(array, index)));
	}

	@Override
	public ParsingScope openScope(Object object) {
		return new IntegerArrayScope(new OnParsedObject() {
			@Override
			public void set(Object value) throws JOOS_ParsingException {
				try {
					IntegerArrayFieldHandler.this.set(object, value);
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					throw new JOOS_ParsingException("Failed to set object due to "+e.getMessage());
				}
			}	
		});
	}

}
