package io.janet.body;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BytesArrayBody extends ActionBody {

    private final byte[] bytes;

    public BytesArrayBody(String mimeType, byte[] bytes) {
        super(mimeType);
        if (bytes == null) throw new IllegalArgumentException("byte[] is null");
        this.bytes = bytes;
    }

    @Override public long length() {
        return bytes.length;
    }

    @Override public InputStream getContent() {
        return new ByteArrayInputStream(bytes);
    }

    @Override public void writeContentTo(OutputStream os) throws IOException {
        os.write(bytes);
    }

    @Override public String toString() {
        return "BytesArrayBody{" +
                "bytes length=" + length() +
                ", mimeType='" + mimeType() + '\'' +
                "} ";
    }
}
