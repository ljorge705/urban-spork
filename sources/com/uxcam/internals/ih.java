package com.uxcam.internals;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ih {

    /* renamed from: a, reason: collision with root package name */
    public final File f210a;
    public File b;

    public ih(File file) {
        this.f210a = file;
    }

    public final void a() throws JSONException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        hd hdVar = new hd();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.f210a.getParentFile(), "video.zip"));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.b = new File(this.f210a.getParentFile(), "video.aes");
        FileOutputStream fileOutputStream2 = new FileOutputStream(this.b);
        CipherOutputStream cipherOutputStreamA = hdVar.a(fileOutputStream2);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.f210a), 16384);
        while (true) {
            int i = bufferedInputStream.read(bArr, 0, 16384);
            if (i == -1) {
                break;
            }
            cipherOutputStreamA.write(bArr, 0, i);
            byteArrayOutputStream.write(bArr, 0, i);
        }
        cipherOutputStreamA.close();
        bufferedInputStream.close();
        fileOutputStream2.close();
        zipOutputStream.putNextEntry(new ZipEntry("video.mp4.aes"));
        byte[] bArr2 = new byte[16384];
        FileInputStream fileInputStream = new FileInputStream(this.b);
        while (true) {
            int i2 = fileInputStream.read(bArr2, 0, 16384);
            if (i2 == -1) {
                fileInputStream.close();
                zipOutputStream.closeEntry();
                zipOutputStream.putNextEntry(new ZipEntry("metadata.json"));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("decryptKey", hdVar.c());
                jSONObject.put("decryptiv", hdVar.b());
                zipOutputStream.write(jSONObject.toString().getBytes());
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                fileOutputStream.close();
                return;
            }
            zipOutputStream.write(bArr2, 0, i2);
        }
    }
}
