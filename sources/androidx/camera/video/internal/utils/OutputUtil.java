package androidx.camera.video.internal.utils;

import java.io.File;

/* loaded from: classes.dex */
public final class OutputUtil {
    private static final String TAG = "OutputUtil";

    private OutputUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getAbsolutePathFromUri(android.content.ContentResolver r7, android.net.Uri r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String[] r3 = new java.lang.String[]{r9}     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r7
            r2 = r8
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            if (r7 != 0) goto L16
            if (r7 == 0) goto L15
            r7.close()
        L15:
            return r0
        L16:
            int r9 = r7.getColumnIndexOrThrow(r9)     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L4f
            r7.moveToFirst()     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L4f
            java.lang.String r8 = r7.getString(r9)     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L4f
            if (r7 == 0) goto L26
            r7.close()
        L26:
            return r8
        L27:
            r9 = move-exception
            goto L2d
        L29:
            r8 = move-exception
            goto L51
        L2b:
            r9 = move-exception
            r7 = r0
        L2d:
            java.lang.String r1 = "OutputUtil"
            java.lang.String r2 = "Failed in getting absolute path for Uri %s with Exception %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L4f
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L4f
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L4f
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Throwable -> L4f
            r9 = 1
            r3[r9] = r8     // Catch: java.lang.Throwable -> L4f
            java.lang.String r8 = java.lang.String.format(r2, r3)     // Catch: java.lang.Throwable -> L4f
            androidx.camera.core.Logger.e(r1, r8)     // Catch: java.lang.Throwable -> L4f
            if (r7 == 0) goto L4e
            r7.close()
        L4e:
            return r0
        L4f:
            r8 = move-exception
            r0 = r7
        L51:
            if (r0 == 0) goto L56
            r0.close()
        L56:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.utils.OutputUtil.getAbsolutePathFromUri(android.content.ContentResolver, android.net.Uri, java.lang.String):java.lang.String");
    }

    public static boolean createParentFolder(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return parentFile.exists() ? parentFile.isDirectory() : parentFile.mkdirs();
    }
}
