package io.janet.gson;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import io.janet.body.ActionBody;
import io.janet.body.BytesArrayBody;
import io.janet.converter.Converter;
import io.janet.converter.ConverterException;

public class GsonConverter implements Converter {
    private final Gson gson;
    private String charset;

    public GsonConverter(Gson gson) {
        this(gson, "UTF-8");
    }

    public GsonConverter(Gson gson, String charset) {
        this.gson = gson;
        this.charset = charset;
    }

    @Override public Object fromBody(ActionBody body, Type type) throws ConverterException {
        String charset = this.charset;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(body.getContent(), charset);
            return gson.fromJson(isr, type);
        } catch (Throwable e) {
            throw ConverterException.forDeserialization(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override public ActionBody toBody(Object object) throws ConverterException {
        try {
            byte[] bytes = gson.toJson(object).getBytes(charset);
            return new BytesArrayBody("application/json; charset=" + charset, bytes);
        } catch (UnsupportedEncodingException e) {
            throw ConverterException.forSerialization(e);
        }
    }
}
