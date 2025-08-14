package com.uxcam.internals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes6.dex */
public final class dm {
    public static String a(File file) throws NoSuchAlgorithmException, IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        try {
                            int i = fileInputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, i);
                        } catch (IOException e) {
                            throw new RuntimeException("Unable to process file for MD5", e);
                        }
                    } finally {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                            gk.c.getClass();
                        }
                    }
                }
                return String.format("%32s", new BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0');
            } catch (FileNotFoundException unused2) {
                gk.c.getClass();
                return null;
            }
        } catch (NoSuchAlgorithmException unused3) {
            gk.c.getClass();
            return null;
        }
    }
}
