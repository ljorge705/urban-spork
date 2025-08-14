package com.drew.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class FileUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void saveBytes(File file, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public static byte[] readBytes(File file) throws Throwable {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            int i = 0;
            while (i != length) {
                try {
                    int i2 = fileInputStream2.read(bArr, i, length - i);
                    if (i2 == -1) {
                        break;
                    }
                    i += i2;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            fileInputStream2.close();
            return bArr;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static byte[] readBytes(String str) throws IOException {
        return readBytes(new File(str));
    }
}
