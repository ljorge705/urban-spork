package com.clevertap.android.sdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FileUtils {
    private final CleverTapInstanceConfig config;
    private final Context context;

    public FileUtils(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
    }

    public void deleteDirectory(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            synchronized (FileUtils.class) {
                File file = new File(this.context.getFilesDir(), str);
                if (file.exists() && file.isDirectory()) {
                    for (String str2 : file.list()) {
                        this.config.getLogger().verbose(this.config.getAccountId(), "File" + str2 + " isDeleted:" + new File(file, str2).delete());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(this.config.getAccountId(), "writeFileOnInternalStorage: failed" + str + " Error:" + e.getLocalizedMessage());
        }
    }

    public void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            synchronized (FileUtils.class) {
                File file = new File(this.context.getFilesDir(), str);
                if (file.exists()) {
                    if (file.delete()) {
                        this.config.getLogger().verbose(this.config.getAccountId(), "File Deleted:" + str);
                    } else {
                        this.config.getLogger().verbose(this.config.getAccountId(), "Failed to delete file" + str);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(this.config.getAccountId(), "writeFileOnInternalStorage: failed" + str + " Error:" + e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.FileInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String readFromFile(java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            r1.<init>()     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            android.content.Context r2 = r7.context     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.io.File r2 = r2.getFilesDir()     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.lang.String r2 = "/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.lang.StringBuilder r8 = r1.append(r8)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            r1.<init>(r8)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            r1.<init>()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
        L37:
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> La8
            if (r0 == 0) goto L41
            r1.append(r0)     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> La8
            goto L37
        L41:
            r8.close()     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> La8
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L53 java.lang.Throwable -> La8
            r8.close()
            r2.close()
            r3.close()
            goto La7
        L53:
            r0 = move-exception
            goto L70
        L55:
            r1 = move-exception
            r3 = r0
            goto L5e
        L58:
            r1 = move-exception
            r3 = r0
            goto L63
        L5b:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L5e:
            r0 = r1
            goto La9
        L60:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L63:
            r0 = r1
            goto L70
        L65:
            r8 = move-exception
            r2 = r0
            r3 = r2
            r0 = r8
            r8 = r3
            goto La9
        L6b:
            r8 = move-exception
            r2 = r0
            r3 = r2
            r0 = r8
            r8 = r3
        L70:
            com.clevertap.android.sdk.CleverTapInstanceConfig r1 = r7.config     // Catch: java.lang.Throwable -> La8
            com.clevertap.android.sdk.Logger r1 = r1.getLogger()     // Catch: java.lang.Throwable -> La8
            com.clevertap.android.sdk.CleverTapInstanceConfig r4 = r7.config     // Catch: java.lang.Throwable -> La8
            java.lang.String r4 = r4.getAccountId()     // Catch: java.lang.Throwable -> La8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
            r5.<init>()     // Catch: java.lang.Throwable -> La8
            java.lang.String r6 = "[Exception While Reading: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> La8
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch: java.lang.Throwable -> La8
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch: java.lang.Throwable -> La8
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La8
            r1.verbose(r4, r0)     // Catch: java.lang.Throwable -> La8
            if (r8 == 0) goto L9b
            r8.close()
        L9b:
            if (r2 == 0) goto La0
            r2.close()
        La0:
            if (r3 == 0) goto La5
            r3.close()
        La5:
            java.lang.String r0 = ""
        La7:
            return r0
        La8:
            r0 = move-exception
        La9:
            if (r8 == 0) goto Lae
            r8.close()
        Lae:
            if (r2 == 0) goto Lb3
            r2.close()
        Lb3:
            if (r3 == 0) goto Lb8
            r3.close()
        Lb8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.utils.FileUtils.readFromFile(java.lang.String):java.lang.String");
    }

    public void writeJsonToFile(String str, String str2, JSONObject jSONObject) throws IOException {
        if (jSONObject != null) {
            FileWriter fileWriter = null;
            try {
                try {
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        synchronized (FileUtils.class) {
                            try {
                                File file = new File(this.context.getFilesDir(), str);
                                if (!file.exists() && !file.mkdir()) {
                                    return;
                                }
                                FileWriter fileWriter2 = new FileWriter(new File(file, str2), false);
                                try {
                                    fileWriter2.append((CharSequence) jSONObject.toString());
                                    fileWriter2.flush();
                                    fileWriter2.close();
                                    return;
                                } catch (Throwable th) {
                                    th = th;
                                    fileWriter = fileWriter2;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.config.getLogger().verbose(this.config.getAccountId(), "writeFileOnInternalStorage: failed" + e.getLocalizedMessage());
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                }
            } catch (Throwable th3) {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th3;
            }
        }
    }
}
