package com.fivvy.profiler.data.repositories;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.fivvy.profiler.domain.models.DeviceInfo;
import com.fivvy.profiler.domain.repositories.IDeviceInfoRepository;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class DeviceInfoRepositoryImpl implements IDeviceInfoRepository {
    private final Context context;
    private volatile boolean isPublicIPFetched = true;
    private String publicIP = "Unavailable";
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public DeviceInfoRepositoryImpl(Context context) {
        this.context = context;
    }

    private String getPublicIPAddress() {
        ExecutorService executorService;
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.INTERNET") != 0) {
            return this.publicIP;
        }
        if (!this.isPublicIPFetched) {
            synchronized (this) {
                if (!this.isPublicIPFetched) {
                    try {
                        try {
                            this.publicIP = (String) this.executorService.submit(new Callable() { // from class: com.fivvy.profiler.data.repositories.DeviceInfoRepositoryImpl$$ExternalSyntheticLambda0
                                @Override // java.util.concurrent.Callable
                                public final Object call() {
                                    return DeviceInfoRepositoryImpl.lambda$getPublicIPAddress$0();
                                }
                            }).get();
                            this.isPublicIPFetched = true;
                            executorService = this.executorService;
                        } catch (Exception e) {
                            e.printStackTrace();
                            this.publicIP = "Unavailable";
                            executorService = this.executorService;
                        }
                        executorService.shutdown();
                    } catch (Throwable th) {
                        this.executorService.shutdown();
                        throw th;
                    }
                }
            }
        }
        return this.publicIP;
    }

    static /* synthetic */ String lambda$getPublicIPAddress$0() throws Exception {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.ipify.org").openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(httpURLConnection.getInputStream())));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line).append('\n');
                    } else {
                        return sb.toString().trim();
                    }
                }
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Unavailable";
        }
    }

    @Override // com.fivvy.profiler.domain.repositories.IDeviceInfoRepository
    public DeviceInfo getDeviceInfo() {
        String publicIPAddress = getPublicIPAddress();
        try {
            this.executorService.shutdown();
            String string = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
            BatteryManager batteryManager = (BatteryManager) this.context.getSystemService("batterymanager");
            return new DeviceInfo.Builder().apiLevel(String.valueOf(Build.VERSION.SDK_INT)).deviceId(string).device(Build.DEVICE).hardware(Build.HARDWARE).brand(Build.BRAND).manufacturer(Build.MANUFACTURER).model(Build.MODEL).product(Build.PRODUCT).tags(Build.TAGS).type(Build.TYPE).base(Integer.toString(1)).id(Build.ID).host(Build.HOST).fingerprint(Build.FINGERPRINT).incrementalVersion(Build.VERSION.INCREMENTAL).releaseVersion(Build.VERSION.RELEASE).baseOs(Build.VERSION.BASE_OS).display(Build.DISPLAY).batteryStatus(batteryManager != null ? batteryManager.getIntProperty(6) : 1).publicIP(publicIPAddress).build();
        } catch (Exception e) {
            Log.d("EXCEPTION", e.toString());
            return null;
        }
    }
}
