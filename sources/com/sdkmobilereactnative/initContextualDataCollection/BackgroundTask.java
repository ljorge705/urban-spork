package com.sdkmobilereactnative.initContextualDataCollection;

import android.content.Context;
import android.util.Base64;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.clevertap.android.sdk.network.api.CtApi;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class BackgroundTask extends Worker {
    private static String _KEY = "apikey:";
    private Map<String, Object> contextualData;

    public BackgroundTask(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        String dataFromFile;
        Data inputData = getInputData();
        String string = inputData.getString("data_file_path");
        String string2 = inputData.getString("api-key");
        String string3 = inputData.getString("api-secret");
        String string4 = inputData.getString("authApiUrl");
        String string5 = inputData.getString("sendDataApiUrl");
        if (string != null && (dataFromFile = readDataFromFile(string)) != null) {
            Gson gson = new Gson();
            this.contextualData = (Map) gson.fromJson(dataFromFile, Map.class);
            this.contextualData.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if (sendDataToEndpoint(string5, getTokenFromApi(string4, _KEY + string2.toLowerCase(), Base64.encodeToString((string2 + ":" + string3).getBytes(), 2)).get("token"), gson.toJson(this.contextualData))) {
                return ListenableWorker.Result.success();
            }
        }
        return ListenableWorker.Result.failure();
    }

    private String readDataFromFile(String str) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getTokenFromApi(String str, String str2, String str3) {
        Response responseExecute;
        HashMap map = new HashMap();
        try {
            responseExecute = new OkHttpClient().newCall(new Request.Builder().url(str).header(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON).header(HttpHeaders.ACCEPT, NfcDataRepository.FILE_TYPE_JSON).post(RequestBody.create(MediaType.parse(NfcDataRepository.FILE_TYPE_JSON), String.format("{\"username\": \"%s\", \"password\": \"%s\"}", str2, str3))).build()).execute();
            try {
                map.put("code", String.valueOf(responseExecute.code()));
                System.out.print("Auth Response code: " + responseExecute.code());
            } catch (Throwable th) {
                if (responseExecute != null) {
                    try {
                        responseExecute.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            map.put(AnalyticsPropertyKeys.ERROR_MESSAGE, "Invalid user credentials");
            System.out.println("Error on Auth. Invalid user credentials");
        }
        if (!responseExecute.isSuccessful()) {
            if (responseExecute != null) {
                responseExecute.close();
            }
            return map;
        }
        map.put("token", new JSONObject(responseExecute.body().string()).getString("accessToken"));
        if (responseExecute != null) {
            responseExecute.close();
        }
        return map;
    }

    private boolean sendDataToEndpoint(String str, String str2, String str3) {
        Response responseExecute;
        int iCode;
        new HashMap();
        try {
            responseExecute = new OkHttpClient().newCall(new Request.Builder().url(str).header(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + str2).post(RequestBody.create(MediaType.parse(CtApi.DEFAULT_CONTENT_TYPE), str3)).build()).execute();
            try {
                iCode = responseExecute.code();
            } finally {
            }
        } catch (IOException e) {
            System.out.println("Data not sent.");
            e.printStackTrace();
        }
        if (responseExecute.isSuccessful()) {
            System.out.println("Data sent. Response code: " + iCode);
            boolean z = iCode == 200;
            if (responseExecute != null) {
                responseExecute.close();
            }
            return z;
        }
        System.out.println("Data not sent. Response code: " + iCode);
        if (responseExecute != null) {
            responseExecute.close();
        }
        return false;
    }
}
