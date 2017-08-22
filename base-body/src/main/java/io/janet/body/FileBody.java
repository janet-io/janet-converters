package io.janet.body;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.janet.body.util.StreamUtil;

public class FileBody extends ActionBody {

    private final File file;

    public FileBody(String mimeType, File file) {
        super(mimeType);
        if (file == null) throw new IllegalArgumentException("File is null");
        if (!file.exists()) throw new IllegalArgumentException("File doesn't exist");
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override public long length() {
        return file.length();
    }

    @Override public InputStream getContent() throws IOException {
        return new FileInputStream(file);
    }

    @Override public void writeContentTo(OutputStream os) throws IOException {
        StreamUtil.writeAll(getContent(), os, StreamUtil.DISK_CHUNK_SIZE);
    }

    @Override public String toString() {
        return "FileBody{" +
                "file=" + file +
                ", mimeType='" + mimeType() + '\'' +
                "} ";
    }
}
