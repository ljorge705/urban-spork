package com.uxcam.internals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes6.dex */
public final class fx {

    /* renamed from: a, reason: collision with root package name */
    public final File f165a;
    public final File b;
    public final File c;
    public final File d;

    public fx(File file, File file2, File file3, File file4) {
        this.f165a = file2;
        this.b = file3;
        this.c = file4;
        this.d = new File(file, "bundle.zip");
    }

    public final void a() throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(this.d));
        a(this.f165a, zipOutputStream);
        a(this.c, zipOutputStream);
        File file = this.b;
        if (file != null) {
            a(file, zipOutputStream);
        }
        zipOutputStream.close();
    }

    public static void a(File file, ZipOutputStream zipOutputStream) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        byte[] bArr = new byte[16384];
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            int i = fileInputStream.read(bArr, 0, 16384);
            if (i != -1) {
                zipOutputStream.write(bArr, 0, i);
            } else {
                fileInputStream.close();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }
}
