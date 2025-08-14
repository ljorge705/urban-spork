package com.google.android.play.core.splitcompat;

import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzn {
    private static final Pattern zza = Pattern.compile("lib/([^/]+)/(.*\\.so)$");
    private final zze zzb;

    zzn(zze zzeVar) throws IOException {
        this.zzb = zzeVar;
    }

    static /* bridge */ /* synthetic */ Set zza(zzn zznVar, Set set, zzt zztVar, ZipFile zipFile) throws IOException {
        HashSet hashSet = new HashSet();
        zznVar.zzf(zztVar, set, new zzj(zznVar, hashSet, zztVar, zipFile));
        return hashSet;
    }

    private static void zze(zzt zztVar, zzk zzkVar) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(zztVar.zza());
        } catch (IOException e) {
            e = e;
            zipFile = null;
        }
        try {
            String strZzb = zztVar.zzb();
            HashMap map = new HashMap();
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                Matcher matcher = zza.matcher(zipEntryNextElement.getName());
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    String strGroup2 = matcher.group(2);
                    Log.d("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'", strZzb, strGroup2, strGroup));
                    Set hashSet = (Set) map.get(strGroup);
                    if (hashSet == null) {
                        hashSet = new HashSet();
                        map.put(strGroup, hashSet);
                    }
                    hashSet.add(new zzm(zipEntryNextElement, strGroup2));
                }
            }
            HashMap map2 = new HashMap();
            for (String str : Build.SUPPORTED_ABIS) {
                if (map.containsKey(str)) {
                    Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI", str));
                    for (zzm zzmVar : (Set) map.get(str)) {
                        if (map2.containsKey(zzmVar.zza)) {
                            Log.d("SplitCompat", String.format("NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI", zzmVar.zza, str));
                        } else {
                            map2.put(zzmVar.zza, zzmVar);
                            Log.d("SplitCompat", String.format("NativeLibraryExtractor: using library %s for ABI %s", zzmVar.zza, str));
                        }
                    }
                } else {
                    Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are no native libraries for supported ABI %s", str));
                }
            }
            zzkVar.zza(zipFile, new HashSet(map2.values()));
            zipFile.close();
        } catch (IOException e2) {
            e = e2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e3) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(e, e3);
                }
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf(zzt zztVar, Set set, zzl zzlVar) throws IOException {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzm zzmVar = (zzm) it.next();
            File fileZzc = this.zzb.zzc(zztVar.zzb(), zzmVar.zza);
            boolean z = false;
            if (fileZzc.exists() && fileZzc.length() == zzmVar.zzb.getSize() && zze.zzp(fileZzc)) {
                z = true;
            }
            zzlVar.zza(zzmVar, fileZzc, z);
        }
    }

    final Set zzb(zzt zztVar) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        HashSet hashSet = new HashSet();
        zze(zztVar, new zzh(this, zztVar, hashSet, atomicBoolean));
        if (atomicBoolean.get()) {
            return hashSet;
        }
        return null;
    }

    final Set zzc() throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Log.d("SplitCompat", "NativeLibraryExtractor: synchronizing native libraries");
        Set<zzt> setZzj = this.zzb.zzj();
        for (String str : this.zzb.zzh()) {
            Iterator it = setZzj.iterator();
            while (true) {
                if (!it.hasNext()) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", str));
                    this.zzb.zzn(str);
                    break;
                }
                if (((zzt) it.next()).zzb().equals(str)) {
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (zzt zztVar : setZzj) {
            HashSet hashSet2 = new HashSet();
            zze(zztVar, new zzi(this, hashSet2, zztVar));
            for (File file : this.zzb.zzi(zztVar.zzb())) {
                if (!hashSet2.contains(file)) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", file.getAbsolutePath(), zztVar.zzb(), zztVar.zza().getAbsolutePath()));
                    this.zzb.zzo(file);
                }
            }
            hashSet.addAll(hashSet2);
        }
        return hashSet;
    }
}
