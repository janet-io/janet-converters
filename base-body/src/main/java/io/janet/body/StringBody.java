package io.janet.body;

import java.io.UnsupportedEncodingException;

public class StringBody extends BytesArrayBody {

    public static final String MIMETYPE = "text/plain; charset=UTF-8";
    private final String string;

    public StringBody(String string) {
        super(MIMETYPE, convertToBytes(string));
        if (string == null) throw new IllegalArgumentException("String is null");
        this.string = string;
    }

    @Override public String toString() {
        return "StringBody{" +
                "string='" + string + '\'' +
                ", mimeType='" + mimeType() + '\'' +
                '}';
    }

    private static byte[] convertToBytes(String string) {
        try {
            return string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
