package com.fivvy.profiler.data.repositories;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.clevertap.android.sdk.network.api.CtApi;
import com.fivvy.profiler.domain.models.DeviceInfo;
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

/* loaded from: classes5.dex */
public class BackgroundTask extends Worker {
    private static final String _KEY = "apikey:";
    private Context context;
    private Map<String, Object> contextualData;

    public BackgroundTask(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.context = context;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() throws Throwable {
        String dataFromFile;
        Data inputData = getInputData();
        String string = inputData.getString("data_file_path");
        String string2 = inputData.getString("api-key");
        String string3 = inputData.getString("api-secret");
        String string4 = inputData.getString("authApiUrl");
        String string5 = inputData.getString("sendDataApiUrl");
        if (string != null && (dataFromFile = readDataFromFile(string)) != null) {
            Gson gson = new Gson();
            DeviceInfo deviceInfo = new DeviceInfoRepositoryImpl(this.context).getDeviceInfo();
            this.contextualData = (Map) gson.fromJson(dataFromFile, Map.class);
            new SimpleDateFormat("yyyy-MM-dd");
            long time = new Date().getTime();
            this.contextualData.put("date", Long.valueOf(time));
            this.contextualData.put("trackId", Base64.encodeToString((deviceInfo.getDeviceId() + ":" + time).getBytes(), 2));
            String str = string2 + ":" + string3;
            String str2 = _KEY + string2.toLowerCase();
            String strEncodeToString = Base64.encodeToString(str.getBytes(), 2);
            String json = gson.toJson(this.contextualData);
            try {
                Map<String, String> tokenFromApi = getTokenFromApi(string4, str2, strEncodeToString);
                String str3 = tokenFromApi.get("token");
                if (str3 != null && !str3.isEmpty()) {
                    if (sendDataToEndpoint(string5, str3, json).get("code").equals("200")) {
                        Data dataBuild = new Data.Builder().putString("message", "Send information successfuly!").putInt("code", 200).build();
                        Log.d("WORK SUCCESS", String.valueOf(dataBuild));
                        return ListenableWorker.Result.success(dataBuild);
                    }
                }
                Data dataBuild2 = new Data.Builder().putString("message", tokenFromApi.get(AnalyticsPropertyKeys.ERROR_MESSAGE)).putString("code", tokenFromApi.get("code")).build();
                Log.d("WORK FAILURE", String.valueOf(dataBuild2));
                return ListenableWorker.Result.failure(dataBuild2);
            } catch (Exception e) {
                Log.d("EXCEPTION", e.toString());
            }
        }
        Data dataBuild3 = new Data.Builder().putString("message", "There is an error on worker!").putString("code", "0900").build();
        Log.d("WORK FAILURE", String.valueOf(dataBuild3));
        return ListenableWorker.Result.failure(dataBuild3);
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
        HashMap map = new HashMap();
        try {
            try {
                Response responseExecute = new OkHttpClient().newCall(new Request.Builder().url(str).header(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON).header(HttpHeaders.ACCEPT, NfcDataRepository.FILE_TYPE_JSON).post(RequestBody.create(MediaType.parse(NfcDataRepository.FILE_TYPE_JSON), String.format("{\"username\": \"%s\", \"password\": \"%s\"}", str2, str3))).build()).execute();
                try {
                    if (responseExecute.isSuccessful()) {
                        map.put("token", new JSONObject(responseExecute.body().string()).getString("accessToken"));
                        map.put("code", String.valueOf(200));
                    } else {
                        map.put("message", responseExecute.message());
                    }
                    if (responseExecute != null) {
                        responseExecute.close();
                    }
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
                map.put("message", e.toString());
            }
        } catch (Exception e2) {
            Log.d("Exception", e2.toString());
        }
        return map;
    }

    private Map<String, String> sendDataToEndpoint(String str, String str2, String str3) {
        HashMap map = new HashMap();
        try {
            Response responseExecute = new OkHttpClient().newCall(new Request.Builder().url(str).header(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + str2).post(RequestBody.create(MediaType.parse(CtApi.DEFAULT_CONTENT_TYPE), str3)).build()).execute();
            try {
                map.put("code", String.valueOf(responseExecute.code()));
                if (responseExecute.isSuccessful()) {
                    map.put("message", "Data sent successfully");
                    System.out.println("Data sent. Response code: " + responseExecute.code());
                } else {
                    map.put(AnalyticsPropertyKeys.ERROR_MESSAGE, responseExecute.message());
                    System.out.println("Data not sent. Response code: " + responseExecute.code());
                }
                if (responseExecute != null) {
                    responseExecute.close();
                }
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put(AnalyticsPropertyKeys.ERROR_MESSAGE, e.toString());
        }
        return map;
    }
}
