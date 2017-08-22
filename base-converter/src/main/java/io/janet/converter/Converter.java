package io.janet.converter;

import java.lang.reflect.Type;

import io.janet.body.ActionBody;

public interface Converter {

    Object fromBody(ActionBody body, Type type) throws ConverterException;

    ActionBody toBody(Object object) throws ConverterException;
}
