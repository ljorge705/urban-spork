package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.soloader.SysUtil;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes5.dex */
public class DirectApkSoSource extends SoSource {
    private final File mApkFile;
    private final Set<String> mDirectApkLdPaths;
    private final Map<String, Set<String>> mLibsInApkMap = new HashMap();

    public DirectApkSoSource(Context context) {
        String str = context.getApplicationInfo().sourceDir;
        this.mDirectApkLdPaths = getDirectApkLdPaths("", str);
        this.mApkFile = new File(str);
    }

    public DirectApkSoSource(File file) {
        this.mDirectApkLdPaths = getDirectApkLdPaths(SysUtil.getBaseName(file.getName()), file.getAbsolutePath());
        this.mApkFile = file;
    }

    public DirectApkSoSource(File file, Set<String> set) {
        this.mDirectApkLdPaths = set;
        this.mApkFile = file;
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader == null) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
        for (String str2 : this.mDirectApkLdPaths) {
            Set<String> set = this.mLibsInApkMap.get(str2);
            if (TextUtils.isEmpty(str2) || set == null || !set.contains(str)) {
                Log.v("SoLoader", str + " not found on " + str2);
            } else {
                loadDependencies(str, i, threadPolicy);
                try {
                    i |= 4;
                    SoLoader.sSoFileLoader.load(str2 + File.separator + str, i);
                    Log.d("SoLoader", str + " found on " + str2);
                    return 1;
                } catch (UnsatisfiedLinkError e) {
                    Log.w("SoLoader", str + " not found on " + str2 + " flag: " + i, e);
                }
            }
        }
        return 0;
    }

    @Override // com.facebook.soloader.SoSource
    public File unpackLibrary(String str) throws IOException {
        throw new UnsupportedOperationException("DirectAPKSoSource doesn't support unpackLibrary");
    }

    public boolean isValid() {
        return !this.mDirectApkLdPaths.isEmpty();
    }

    static Set<String> getDirectApkLdPaths(String str, String str2) throws NoSuchMethodException, SecurityException {
        String fallbackApkLdPath;
        HashSet hashSet = new HashSet();
        String classLoaderLdLoadLibrary = SysUtil.Api14Utils.getClassLoaderLdLoadLibrary();
        if (classLoaderLdLoadLibrary != null) {
            for (String str3 : classLoaderLdLoadLibrary.split(":")) {
                if (str3.contains(str + ".apk!/")) {
                    hashSet.add(str3);
                }
            }
        }
        if (hashSet.isEmpty() && (fallbackApkLdPath = getFallbackApkLdPath(str2)) != null) {
            hashSet.add(fallbackApkLdPath);
        }
        return hashSet;
    }

    private static String[] getDependencies(String str, ElfByteChannel elfByteChannel) throws IOException {
        if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
            Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", str, "]");
        }
        try {
            return NativeDeps.getDependencies(str, elfByteChannel);
        } finally {
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.endSection();
            }
        }
    }

    @Nullable
    private static String getFallbackApkLdPath(String str) {
        String[] supportedAbis = SysUtil.getSupportedAbis();
        if (TextUtils.isEmpty(str) || supportedAbis.length <= 0) {
            return null;
        }
        return str + "!/lib/" + supportedAbis[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        r2 = new com.facebook.soloader.ElfZipFileChannel(r1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
    
        r8 = getDependencies(r8, r2);
        r3 = r8.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0041, code lost:
    
        if (r4 >= r3) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0043, code lost:
    
        r5 = r8[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0049, code lost:
    
        if (r5.startsWith("/") == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        com.facebook.soloader.SoLoader.loadLibraryBySoName(r5, r9 | 1, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
    
        throw r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void loadDependencies(java.lang.String r8, int r9, android.os.StrictMode.ThreadPolicy r10) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "/"
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile
            java.io.File r2 = r7.mApkFile
            r1.<init>(r2)
            java.util.Enumeration r2 = r1.entries()     // Catch: java.lang.Throwable -> L66
        Ld:
            boolean r3 = r2.hasMoreElements()     // Catch: java.lang.Throwable -> L66
            if (r3 == 0) goto L62
            java.lang.Object r3 = r2.nextElement()     // Catch: java.lang.Throwable -> L66
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch: java.lang.Throwable -> L66
            if (r3 == 0) goto Ld
            java.lang.String r4 = r3.getName()     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r5.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch: java.lang.Throwable -> L66
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L66
            boolean r4 = r4.endsWith(r5)     // Catch: java.lang.Throwable -> L66
            if (r4 == 0) goto Ld
            com.facebook.soloader.ElfZipFileChannel r2 = new com.facebook.soloader.ElfZipFileChannel     // Catch: java.lang.Throwable -> L66
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L66
            java.lang.String[] r8 = getDependencies(r8, r2)     // Catch: java.lang.Throwable -> L58
            int r3 = r8.length     // Catch: java.lang.Throwable -> L58
            r4 = 0
        L41:
            if (r4 >= r3) goto L54
            r5 = r8[r4]     // Catch: java.lang.Throwable -> L58
            boolean r6 = r5.startsWith(r0)     // Catch: java.lang.Throwable -> L58
            if (r6 == 0) goto L4c
            goto L51
        L4c:
            r6 = r9 | 1
            com.facebook.soloader.SoLoader.loadLibraryBySoName(r5, r6, r10)     // Catch: java.lang.Throwable -> L58
        L51:
            int r4 = r4 + 1
            goto L41
        L54:
            r2.close()     // Catch: java.lang.Throwable -> L66
            goto L62
        L58:
            r8 = move-exception
            r2.close()     // Catch: java.lang.Throwable -> L5d
            goto L61
        L5d:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch: java.lang.Throwable -> L66
        L61:
            throw r8     // Catch: java.lang.Throwable -> L66
        L62:
            r1.close()
            return
        L66:
            r8 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> L6b
            goto L6f
        L6b:
            r9 = move-exception
            r8.addSuppressed(r9)
        L6f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.DirectApkSoSource.loadDependencies(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    @Override // com.facebook.soloader.SoSource
    protected void prepare(int i) throws IOException {
        int iIndexOf;
        int i2;
        String strSubstring = null;
        for (String str : this.mDirectApkLdPaths) {
            if (!TextUtils.isEmpty(str) && (iIndexOf = str.indexOf(33)) >= 0 && (i2 = iIndexOf + 2) < str.length()) {
                strSubstring = str.substring(i2);
            }
            if (!TextUtils.isEmpty(strSubstring)) {
                ZipFile zipFile = new ZipFile(this.mApkFile);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    while (enumerationEntries.hasMoreElements()) {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (zipEntryNextElement != null && zipEntryNextElement.getName().startsWith(strSubstring) && zipEntryNextElement.getName().endsWith(".so") && zipEntryNextElement.getMethod() == 0) {
                            append(str, zipEntryNextElement.getName().substring(strSubstring.length() + 1));
                        }
                    }
                    zipFile.close();
                } catch (Throwable th) {
                    try {
                        zipFile.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        return getClass().getName() + "[root = " + this.mDirectApkLdPaths.toString() + AbstractJsonLexerKt.END_LIST;
    }

    private synchronized void append(String str, String str2) {
        if (!this.mLibsInApkMap.containsKey(str)) {
            this.mLibsInApkMap.put(str, new HashSet());
        }
        this.mLibsInApkMap.get(str).add(str2);
    }
}
