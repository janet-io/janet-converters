package io.janet.body.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class StreamUtil {
    private StreamUtil() {
    }

    public static final int STRING_CHUNK_SIZE = 1024;
    public static final int NETWORK_CHUNK_SIZE = 4096;
    public static final int DISK_CHUNK_SIZE = 8192;

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (RuntimeException rethrown) {
            throw rethrown;
        } catch (Exception ignored) { }
    }

    public static void writeAll(InputStream is, OutputStream os, int chunkSize) throws IOException {
        try {
            byte[] buffer = new byte[chunkSize];
            int count;
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
        } finally {
            StreamUtil.closeQuietly(is);
        }
    }

    public static String convertToString(InputStream is) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            writeAll(is, result, STRING_CHUNK_SIZE);
            return result.toString("UTF-8");
        } catch (IOException e) {
            return null;
        } finally {
            closeQuietly(result);
        }
    }
}
