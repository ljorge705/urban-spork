package com.singular.sdk.internal;

import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.singular.sdk.internal.Api;
import com.singular.sdk.internal.Constants;
import com.singular.sdk.internal.SingularParamsBase;
import io.grpc.internal.GrpcUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
class SingularRequestHandler {
    private static final SingularLog logger = SingularLog.getLogger("SingularRequestHandler");
    static int counter = 0;
    private static final String[] POST_PAYLOAD_PARAMS_KEYS = {"e", Constants.GLOBAL_PROPERTIES_KEY, Constants.RequestBody.INSTALL_REFERRER_KEY};

    SingularRequestHandler() {
    }

    static boolean makeRequest(SingularInstance singularInstance, String str, Map<String, String> map, long j, Api.OnApiCallback onApiCallback) throws JSONException, IOException {
        long currentTimeMillis = Utils.getCurrentTimeMillis();
        int i = counter + 1;
        counter = i;
        SingularLog singularLog = logger;
        singularLog.debug("---------------------------> /%d", Integer.valueOf(i));
        singularLog.debug("url = %s", str);
        singularLog.debug("params = %s", map);
        HttpURLConnection httpURLConnectionBuildRequest = buildRequest(singularInstance, str, map, j);
        try {
            try {
                return sendRequest(singularInstance, onApiCallback, currentTimeMillis, i, httpURLConnectionBuildRequest);
            } catch (IOException e) {
                throw e;
            }
        } finally {
            if (httpURLConnectionBuildRequest != null) {
                httpURLConnectionBuildRequest.disconnect();
            }
        }
    }

    static HttpURLConnection buildRequest(SingularInstance singularInstance, String str, Map<String, String> map, long j) throws JSONException, IOException {
        HttpURLConnection httpConnection;
        Map<String, String> postPayloadParams = getPostPayloadParams(map);
        String str2 = str + "?" + appendHash(getQueryString(singularInstance, map, j), singularInstance.getSingularConfig().secret);
        URL url = new URL(str2);
        if (url.getProtocol().equalsIgnoreCase("https")) {
            httpConnection = getHttpsConnection(url);
        } else {
            httpConnection = getHttpConnection(url);
        }
        setDefaultConnectionProperties(httpConnection);
        setPayloadForRequest(httpConnection, postPayloadParams, singularInstance.getSingularConfig().secret);
        logger.debug("__API__ %s %s", httpConnection.getRequestMethod(), str2);
        return httpConnection;
    }

    static boolean sendRequest(SingularInstance singularInstance, Api.OnApiCallback onApiCallback, long j, int i, HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        String response = readResponse(httpURLConnection);
        httpURLConnection.disconnect();
        long currentTimeMillis = Utils.getCurrentTimeMillis() - j;
        SingularLog singularLog = logger;
        singularLog.debug("%d %s", Integer.valueOf(responseCode), response);
        singularLog.debug("<--------------------------- /%d - took %dms", Integer.valueOf(i), Long.valueOf(currentTimeMillis));
        return onApiCallback.handle(singularInstance, responseCode, response);
    }

    private static String readResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStreamReader inputStreamReader;
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = httpURLConnection.getInputStream();
        if (httpURLConnection.getContentEncoding() != null && httpURLConnection.getContentEncoding().equals("gzip")) {
            inputStreamReader = new InputStreamReader(new GZIPInputStream(inputStream));
        } else {
            inputStreamReader = new InputStreamReader(inputStream);
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                stringBuffer.append(line);
            } else {
                return stringBuffer.toString();
            }
        }
    }

    private static Map<String, String> getPostPayloadParams(Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (String str : POST_PAYLOAD_PARAMS_KEYS) {
            if (map.containsKey(str)) {
                map2.put(str, map.get(str));
                map.remove(str);
            }
        }
        return map2;
    }

    private static void setPayloadForRequest(HttpURLConnection httpURLConnection, Map<String, String> map, String str) throws JSONException, IOException {
        if (httpURLConnection == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (map != null && map.size() > 0) {
                String string = new JSONObject(map).toString();
                String strSha1Hash = Utils.sha1Hash(string, str);
                jSONObject.put("payload", string);
                jSONObject.put("signature", strSha1Hash);
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(jSONObject.toString());
            outputStreamWriter.close();
        } catch (IOException | JSONException e) {
            logger.error("Error in JSON parsing or I/O ", e);
        }
    }

    private static void setDefaultConnectionProperties(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod(GrpcUtil.HTTP_METHOD);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Constants.HTTP_USER_AGENT);
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, NfcDataRepository.FILE_TYPE_JSON);
    }

    private static String getQueryString(SingularInstance singularInstance, Map<String, String> map, long j) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (map == null) {
            map = new HashMap<>();
        }
        TreeMap treeMap = new TreeMap(map);
        treeMap.put("rt", "json");
        treeMap.put("lag", String.valueOf(Utils.lagSince(j)));
        treeMap.put("c", Utils.getConnectionType(singularInstance.getContext()));
        if ((!treeMap.containsKey(SingularParamsBase.Constants.IDENTIFIER_UNIQUE_ID_KEY) || Utils.isEmptyOrNull((String) treeMap.get(SingularParamsBase.Constants.IDENTIFIER_UNIQUE_ID_KEY))) && !Utils.isEmptyOrNull(singularInstance.getDeviceInfo().oaid)) {
            treeMap.put(SingularParamsBase.Constants.IDENTIFIER_UNIQUE_ID_KEY, singularInstance.getDeviceInfo().oaid);
            treeMap.put("k", SingularParamsBase.Constants.OAID_KEYSPACE_VALUE);
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String strEncode = URLEncoder.encode((String) entry.getKey(), "UTF-8");
            String str = (String) entry.getValue();
            String strEncode2 = str != null ? URLEncoder.encode(str, "UTF-8") : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strEncode);
            sb.append("=");
            sb.append(strEncode2);
        }
        return sb.toString();
    }

    private static String appendHash(String str, String str2) {
        if (str == null) {
            return "";
        }
        String strSha1Hash = Utils.sha1Hash(String.format("?%s", str), str2);
        logger.debug("hash = %s", strSha1Hash);
        return Utils.isEmptyOrNull(strSha1Hash) ? str : str + "&h=" + strSha1Hash;
    }

    public static HttpURLConnection getHttpConnection(URL url) throws IOException {
        if (url != null) {
            return (HttpURLConnection) url.openConnection();
        }
        return null;
    }

    public static HttpURLConnection getHttpsConnection(URL url) throws IOException {
        if (url == null) {
            return null;
        }
        return (HttpsURLConnection) url.openConnection();
    }
}
