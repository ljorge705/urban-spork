package com.uxcam.internals;

import com.uxcam.screenaction.utils.Util;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.crypto.CipherOutputStream;
import org.json.JSONException;

/* loaded from: classes6.dex */
public final class fr {

    /* renamed from: a, reason: collision with root package name */
    public final File f160a;
    public final fs b;
    public final fw c;
    public final ft d;
    public final fx e;

    public fr(File file) {
        this.f160a = new File(file, "data.zip");
        hd hdVar = new hd();
        fs fsVar = new fs(file, hdVar);
        this.b = fsVar;
        fw fwVar = new fw(file, hdVar);
        this.c = fwVar;
        ft ftVar = new ft(file, hdVar);
        this.d = ftVar;
        this.e = new fx(file, fsVar.a(), fwVar.a(), ftVar.a());
    }

    public final void a(String str) throws IOException {
        fs fsVar = this.b;
        fsVar.c = str;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(fsVar.b.a(byteArrayOutputStream));
            gZIPOutputStream.write(fsVar.c.getBytes());
            gZIPOutputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(fsVar.f161a);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("DataFile::generateFileOnSD() -> catch1");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public final void a(File file) {
        fw fwVar = this.c;
        fwVar.c = file;
        try {
            byte[] bArr = new byte[16384];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(fwVar.f164a);
            CipherOutputStream cipherOutputStreamA = fwVar.b.a(fileOutputStream);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fwVar.c), 16384);
            while (true) {
                int i = bufferedInputStream.read(bArr, 0, 16384);
                if (i != -1) {
                    cipherOutputStreamA.write(bArr, 0, i);
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    cipherOutputStreamA.close();
                    bufferedInputStream.close();
                    fileOutputStream.close();
                    Util.deleteRecursive(fwVar.c);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("DataFile::generateFileOnSD() -> catch1");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public final File a() {
        try {
            this.d.b();
            this.e.a();
            fx fxVar = this.e;
            Util.deleteRecursive(fxVar.f165a);
            Util.deleteRecursive(fxVar.b);
            Util.deleteRecursive(fxVar.c);
            Util.deleteRecursive(this.f160a);
            return this.e.d;
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
