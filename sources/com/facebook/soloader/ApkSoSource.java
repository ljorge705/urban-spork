package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import com.facebook.soloader.ExtractFromZipSoSource;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ApkSoSource extends ExtractFromZipSoSource {
    private static final byte APK_SO_SOURCE_SIGNATURE_VERSION = 2;
    private static final byte LIBS_DIR_DOESNT_EXIST = 1;
    private static final byte LIBS_DIR_DONT_CARE = 0;
    private static final byte LIBS_DIR_SNAPSHOT = 2;
    public static final int PREFER_ANDROID_LIBS_DIRECTORY = 1;
    private static final String TAG = "ApkSoSource";
    private final int mFlags;

    public ApkSoSource(Context context, String str, int i) {
        this(context, new File(context.getApplicationInfo().sourceDir), str, i);
    }

    public ApkSoSource(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.mFlags = i;
    }

    @Override // com.facebook.soloader.ExtractFromZipSoSource, com.facebook.soloader.UnpackingSoSource
    protected UnpackingSoSource.Unpacker makeUnpacker(byte b) throws IOException {
        return new ApkUnpacker(this);
    }

    protected class ApkUnpacker extends ExtractFromZipSoSource.ZipUnpacker {
        private final int mFlags;
        private final File mLibDir;

        ApkUnpacker(ExtractFromZipSoSource extractFromZipSoSource) throws IOException {
            super(extractFromZipSoSource);
            this.mLibDir = new File(ApkSoSource.this.mContext.getApplicationInfo().nativeLibraryDir);
            this.mFlags = ApkSoSource.this.mFlags;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00bf  */
        @Override // com.facebook.soloader.ExtractFromZipSoSource.ZipUnpacker
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected boolean shouldExtract(java.util.zip.ZipEntry r8, java.lang.String r9) {
            /*
                r7 = this;
                java.lang.String r0 = r8.getName()
                com.facebook.soloader.ApkSoSource r1 = com.facebook.soloader.ApkSoSource.this
                java.lang.String r1 = r1.mCorruptedLib
                boolean r1 = r9.equals(r1)
                r2 = 1
                if (r1 == 0) goto L20
                com.facebook.soloader.ApkSoSource r8 = com.facebook.soloader.ApkSoSource.this
                r0 = 0
                r8.mCorruptedLib = r0
                java.lang.String r8 = "allowing consideration of corrupted lib %s"
                java.lang.Object[] r9 = new java.lang.Object[]{r9}
                java.lang.String r8 = java.lang.String.format(r8, r9)
                goto Lc1
            L20:
                int r1 = r7.mFlags
                r1 = r1 & r2
                if (r1 != 0) goto L3c
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "allowing consideration of "
                r8.<init>(r9)
                java.lang.StringBuilder r8 = r8.append(r0)
                java.lang.String r9 = ": self-extraction preferred"
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r8 = r8.toString()
                goto Lc1
            L3c:
                java.io.File r1 = new java.io.File
                java.io.File r3 = r7.mLibDir
                r1.<init>(r3, r9)
                r3 = 0
                java.lang.String r4 = r1.getCanonicalPath()     // Catch: java.io.IOException -> L64
                java.io.File r5 = r7.mLibDir     // Catch: java.io.IOException -> L64
                java.lang.String r5 = r5.getCanonicalPath()     // Catch: java.io.IOException -> L64
                boolean r4 = r4.startsWith(r5)     // Catch: java.io.IOException -> L64
                if (r4 != 0) goto L60
                java.lang.String r4 = "not allowing consideration of %s: %s not in lib dir"
                java.lang.Object[] r5 = new java.lang.Object[]{r0, r9}     // Catch: java.io.IOException -> L64
                java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.io.IOException -> L64
                goto L74
            L60:
                java.lang.String r4 = ""
                r5 = r2
                goto L75
            L64:
                r4 = move-exception
                java.lang.String r4 = r4.toString()
                java.lang.Object[] r4 = new java.lang.Object[]{r0, r9, r4}
                java.lang.String r5 = "not allowing consideration of %s: %s, IOException when constructing path: %s"
                java.lang.String r4 = java.lang.String.format(r5, r4)
            L74:
                r5 = r3
            L75:
                if (r5 == 0) goto Lbf
                boolean r4 = r1.isFile()
                if (r4 != 0) goto L88
                java.lang.String r8 = "allowing consideration of %s: %s not in system lib dir"
                java.lang.Object[] r9 = new java.lang.Object[]{r0, r9}
                java.lang.String r8 = java.lang.String.format(r8, r9)
                goto Lc1
            L88:
                long r4 = r1.length()
                long r8 = r8.getSize()
                int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r6 == 0) goto La7
                java.lang.Long r0 = java.lang.Long.valueOf(r4)
                java.lang.Long r8 = java.lang.Long.valueOf(r8)
                java.lang.Object[] r8 = new java.lang.Object[]{r1, r0, r8}
                java.lang.String r9 = "allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK"
                java.lang.String r8 = java.lang.String.format(r9, r8)
                goto Lc1
            La7:
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "not allowing consideration of "
                r8.<init>(r9)
                java.lang.StringBuilder r8 = r8.append(r0)
                java.lang.String r9 = ": deferring to libdir"
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r8 = r8.toString()
                r2 = r3
                goto Lc1
            Lbf:
                r2 = r3
                r8 = r4
            Lc1:
                java.lang.String r9 = "ApkSoSource"
                android.util.Log.d(r9, r8)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ApkSoSource.ApkUnpacker.shouldExtract(java.util.zip.ZipEntry, java.lang.String):boolean");
        }
    }

    @Override // com.facebook.soloader.UnpackingSoSource
    protected byte[] getDepsBlock() throws IOException {
        File canonicalFile = this.mZipFileName.getCanonicalFile();
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeByte((byte) 2);
            parcelObtain.writeString(canonicalFile.getPath());
            parcelObtain.writeLong(canonicalFile.lastModified());
            parcelObtain.writeInt(SysUtil.getAppVersionCode(this.mContext));
            if ((this.mFlags & 1) == 0) {
                parcelObtain.writeByte((byte) 0);
                return parcelObtain.marshall();
            }
            String str = this.mContext.getApplicationInfo().nativeLibraryDir;
            if (str == null) {
                parcelObtain.writeByte((byte) 1);
                return parcelObtain.marshall();
            }
            File canonicalFile2 = new File(str).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                parcelObtain.writeByte((byte) 1);
                return parcelObtain.marshall();
            }
            parcelObtain.writeByte((byte) 2);
            parcelObtain.writeString(canonicalFile2.getPath());
            parcelObtain.writeLong(canonicalFile2.lastModified());
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }
}
