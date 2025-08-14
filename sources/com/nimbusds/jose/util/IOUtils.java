package com.nimbusds.jose.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class IOUtils {
    public static String readInputStreamToString(InputStream inputStream) throws IOException {
        return readInputStreamToString(inputStream, StandardCharset.UTF_8);
    }

    public static String readInputStreamToString(InputStream inputStream, Charset charset) throws IOException {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        while (true) {
            try {
                int i = inputStreamReader.read(cArr, 0, 1024);
                if (i >= 0) {
                    sb.append(cArr, 0, i);
                } else {
                    String string = sb.toString();
                    inputStreamReader.close();
                    return string;
                }
            } catch (Throwable th) {
                try {
                    inputStreamReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static String readFileToString(File file) throws IOException {
        return readInputStreamToString(new FileInputStream(file));
    }

    public static String readFileToString(File file, Charset charset) throws IOException {
        return readInputStreamToString(new FileInputStream(file), charset);
    }

    public static void closeSilently(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    private IOUtils() {
    }
}
