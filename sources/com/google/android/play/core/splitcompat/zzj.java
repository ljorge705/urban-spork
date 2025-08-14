package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzj implements zzl {
    final /* synthetic */ Set zza;
    final /* synthetic */ zzt zzb;
    final /* synthetic */ ZipFile zzc;

    zzj(zzn zznVar, Set set, zzt zztVar, ZipFile zipFile) {
        this.zza = set;
        this.zzb = zztVar;
        this.zzc = zipFile;
    }

    @Override // com.google.android.play.core.splitcompat.zzl
    public final void zza(zzm zzmVar, File file, boolean z) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        this.zza.add(file);
        if (z) {
            return;
        }
        Log.i("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", this.zzb.zzb(), zzmVar.zza, this.zzb.zza().getAbsolutePath(), zzmVar.zzb.getName(), file.getAbsolutePath()));
        ZipFile zipFile = this.zzc;
        ZipEntry zipEntry = zzmVar.zzb;
        byte[] bArr = new byte[4096];
        if (file.exists()) {
            file.delete();
        }
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                zze.zzm(file);
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }
}
