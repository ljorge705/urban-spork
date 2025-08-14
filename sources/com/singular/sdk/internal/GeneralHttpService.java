package com.singular.sdk.internal;

import android.net.Uri;
import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.singular.sdk.internal.GeneralHttpServiceBase;
import io.grpc.internal.GrpcUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class GeneralHttpService extends GeneralHttpServiceBase {
    private static final String BASE_URL = "https://sdk-api-v1.singular.net/api/v1";
    private final SingularLog logger;

    public GeneralHttpService() {
        super("https://sdk-api-v1.singular.net/api/v1");
        this.logger = SingularLog.getLogger("GeneralHttpService");
    }

    private HttpURLConnection buildRequest(String str, Map<String, String> map) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setConnectTimeout(10000);
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setRequestMethod(GrpcUtil.HTTP_METHOD);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Constants.HTTP_USER_AGENT);
            httpsURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON);
            httpsURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
            try {
                JSONObject jSONObject = map != null ? new JSONObject(map) : new JSONObject();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream(), "UTF-8");
                outputStreamWriter.write(jSONObject.toString());
                outputStreamWriter.close();
                return httpsURLConnection;
            } catch (Throwable th) {
                this.logger.error("Error in JSON Serialization ", th);
                this.logger.error(Utils.formatException(th));
                return null;
            }
        } catch (Throwable th2) {
            this.logger.error(Utils.formatException(th2));
            return null;
        }
    }

    @Override // com.singular.sdk.internal.GeneralHttpServiceBase
    public void sendRequest(final String str, final Map<String, String> map, final Map<String, String> map2, final GeneralHttpServiceBase.CompletionHandler completionHandler) {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.singular.sdk.internal.GeneralHttpService.1
            @Override // java.lang.Runnable
            public void run() {
                GeneralHttpService.this.sendSynchronousRequest(str, map, map2, completionHandler);
            }
        });
    }

    public void sendSynchronousRequest(String str, Map<String, String> map, Map<String, String> map2, GeneralHttpServiceBase.CompletionHandler completionHandler) {
        HttpURLConnection httpURLConnectionBuildRequest;
        InputStreamReader inputStreamReader;
        String str2 = "?a=" + SingularInstance.getInstance().getSingularConfig().apiKey;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str2 = str2 + "&" + Uri.encode(entry.getKey()) + "=" + Uri.encode(entry.getValue());
            }
        }
        try {
            httpURLConnectionBuildRequest = buildRequest(getBaseUrl() + str + (str2 + "&h=" + Utils.sha1Hash(str2, SingularInstance.getInstance().getSingularConfig().secret)), map2);
            try {
            } catch (Throwable th) {
                th = th;
                try {
                    completionHandler.onFailure("Error sending request: message - " + th.getMessage());
                    this.logger.error(Utils.formatException(th));
                    if (httpURLConnectionBuildRequest == null) {
                        return;
                    }
                    httpURLConnectionBuildRequest.disconnect();
                } finally {
                    if (httpURLConnectionBuildRequest != null) {
                        httpURLConnectionBuildRequest.disconnect();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnectionBuildRequest = null;
        }
        if (httpURLConnectionBuildRequest == null) {
            completionHandler.onFailure("Error sending request: connection is null");
            this.logger.error("Error sending request: connection is null");
            if (httpURLConnectionBuildRequest != null) {
                return;
            } else {
                return;
            }
        }
        httpURLConnectionBuildRequest.connect();
        int responseCode = httpURLConnectionBuildRequest.getResponseCode();
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = httpURLConnectionBuildRequest.getInputStream();
        if (httpURLConnectionBuildRequest.getContentEncoding() != null && httpURLConnectionBuildRequest.getContentEncoding().equals("gzip")) {
            inputStreamReader = new InputStreamReader(new GZIPInputStream(inputStream));
        } else {
            inputStreamReader = new InputStreamReader(inputStream);
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            } else {
                stringBuffer.append(line);
            }
        }
        completionHandler.onSuccess(stringBuffer.toString(), responseCode);
        if (httpURLConnectionBuildRequest == null) {
            return;
        }
        httpURLConnectionBuildRequest.disconnect();
    }
}
