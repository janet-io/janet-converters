package io.janet.body;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ActionBody {

    public static final String MIMETYPE_UNKNOWN = "application/unknown";

    private final String mimeType;

    public ActionBody(String mimeType) {
        if (mimeType == null) {
            mimeType = MIMETYPE_UNKNOWN;
        }
        this.mimeType = mimeType;
    }

    public final String mimeType() {
        return mimeType;
    }

    public abstract long length();

    public abstract InputStream getContent() throws IOException;

    public abstract void writeContentTo(OutputStream os) throws IOException;

    @Override public String toString() {
        return "ActionBody{" +
                "mimeType='" + mimeType + '\'' +
                '}';
    }

}
