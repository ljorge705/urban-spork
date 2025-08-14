package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ConfigStorageClient {
    private static final String JSON_STRING_ENCODING = "UTF-8";
    private static final Map<String, ConfigStorageClient> clientInstances = new HashMap();
    private final Context context;
    private final String fileName;

    String getFileName() {
        return this.fileName;
    }

    private ConfigStorageClient(Context context, String str) {
        this.context = context;
        this.fileName = str;
    }

    public synchronized Void write(ConfigContainer configContainer) throws IOException {
        FileOutputStream fileOutputStreamOpenFileOutput = this.context.openFileOutput(this.fileName, 0);
        try {
            fileOutputStreamOpenFileOutput.write(configContainer.toString().getBytes("UTF-8"));
        } finally {
            fileOutputStreamOpenFileOutput.close();
        }
        return null;
    }

    @Nullable
    public synchronized ConfigContainer read() throws IOException {
        FileInputStream fileInputStreamOpenFileInput;
        Throwable th;
        try {
            fileInputStreamOpenFileInput = this.context.openFileInput(this.fileName);
            try {
                int iAvailable = fileInputStreamOpenFileInput.available();
                byte[] bArr = new byte[iAvailable];
                fileInputStreamOpenFileInput.read(bArr, 0, iAvailable);
                ConfigContainer configContainerCopyOf = ConfigContainer.copyOf(new JSONObject(new String(bArr, "UTF-8")));
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                }
                return configContainerCopyOf;
            } catch (FileNotFoundException | JSONException unused) {
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                }
                throw th;
            }
        } catch (FileNotFoundException | JSONException unused2) {
            fileInputStreamOpenFileInput = null;
        } catch (Throwable th3) {
            fileInputStreamOpenFileInput = null;
            th = th3;
        }
    }

    public synchronized Void clear() {
        this.context.deleteFile(this.fileName);
        return null;
    }

    public static synchronized ConfigStorageClient getInstance(Context context, String str) {
        Map<String, ConfigStorageClient> map;
        map = clientInstances;
        if (!map.containsKey(str)) {
            map.put(str, new ConfigStorageClient(context, str));
        }
        return map.get(str);
    }

    public static synchronized void clearInstancesForTest() {
        clientInstances.clear();
    }
}
