package ckoaurzatqnuxckmholu;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import io.grpc.internal.GrpcUtil;
import io.sentry.protocol.Response;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class zwlltpaufqribmleigux {
    private static String ldeiitcdqlqzkidvrbjy = null;
    private static final String vikftlgmuszlvyjnlikz = "zwlltpaufqribmleigux";
    private long ctfsaqlysacfjtqixtmr;
    private Integer danumarvmgpbarrzqyrz;
    private oacciftezlubzxpkwvyc dbuymyhwehsdoxafsfpy;
    private yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp dyrapphjndqarxdhyvgv;
    private String efmnkxwvqeqnaehsyess;
    private Context mxodkqpwhcryvsgsvabl;
    private Boolean ooztjhejjvpgrdhjnyju;
    private String uusbetktgiikylwfbevs;
    private Boolean vjgujdxqyzpnlimdrvvt;
    private Handler yvrzbryuycempgkdhpvj = new Handler(Looper.getMainLooper());

    public zwlltpaufqribmleigux(Context context, String str, Boolean bool, Integer num) {
        this.dyrapphjndqarxdhyvgv = null;
        this.ooztjhejjvpgrdhjnyju = Boolean.FALSE;
        this.mxodkqpwhcryvsgsvabl = context;
        this.efmnkxwvqeqnaehsyess = str;
        this.dyrapphjndqarxdhyvgv = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(bool, str, new ArrayList());
        this.ooztjhejjvpgrdhjnyju = bool;
        this.danumarvmgpbarrzqyrz = num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dbuymyhwehsdoxafsfpy(Object[] objArr) throws JSONException, IOException {
        final JSONObject jSONObjectYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(objArr);
        this.yvrzbryuycempgkdhpvj.post(new Runnable() { // from class: ckoaurzatqnuxckmholu.zwlltpaufqribmleigux$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                this.f$0.yvrzbryuycempgkdhpvj(jSONObjectYvrzbryuycempgkdhpvj);
            }
        });
    }

    protected JSONObject yvrzbryuycempgkdhpvj(Object... objArr) throws JSONException, IOException {
        int responseCode;
        String str;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()) {
            Log.i(vikftlgmuszlvyjnlikz, "doInBackground, Calling Method " + this.efmnkxwvqeqnaehsyess);
        }
        this.uusbetktgiikylwfbevs = (String) objArr[0];
        JSONObject jSONObject3 = (JSONObject) objArr[1];
        if (jSONObject3 != null) {
            jSONObject = jSONObject3;
        }
        this.vjgujdxqyzpnlimdrvvt = (Boolean) objArr[2];
        this.dbuymyhwehsdoxafsfpy = (oacciftezlubzxpkwvyc) objArr[3];
        if (objArr.length > 4) {
            ldeiitcdqlqzkidvrbjy = (String) objArr[4];
        }
        try {
            if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()) {
                Log.i(vikftlgmuszlvyjnlikz, "Before getHttpURLConnection");
            }
            jSONObject2.put("reqID", ldeiitcdqlqzkidvrbjy);
            jSONObject2.put("url", this.uusbetktgiikylwfbevs);
            HttpURLConnection httpURLConnectionYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj();
            this.ctfsaqlysacfjtqixtmr = System.currentTimeMillis();
            httpURLConnectionYvrzbryuycempgkdhpvj.connect();
            OutputStream outputStream = httpURLConnectionYvrzbryuycempgkdhpvj.getOutputStream();
            jSONObject.put("version", "3.0.6");
            if (outputStream != null) {
                if (this.vjgujdxqyzpnlimdrvvt.booleanValue()) {
                    outputStream.write(fdwipeifdmvqsbqrrpyp.yvrzbryuycempgkdhpvj(jSONObject.toString()));
                    outputStream.flush();
                    outputStream.close();
                } else {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    outputStreamWriter.write(jSONObject.toString());
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                }
            }
            System.currentTimeMillis();
            responseCode = httpURLConnectionYvrzbryuycempgkdhpvj.getResponseCode();
            System.currentTimeMillis();
            if (responseCode == 200) {
                jSONObject2.put("output", new JSONObject(yvrzbryuycempgkdhpvj(httpURLConnectionYvrzbryuycempgkdhpvj)));
                str = "";
            } else {
                str = "Http Result: " + responseCode;
            }
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(e);
            if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()) {
                String str2 = vikftlgmuszlvyjnlikz;
                Log.w(str2, e.toString());
                if (e.getMessage() != null) {
                    Log.w(str2, e.getMessage());
                }
            }
            String message = e.getMessage();
            responseCode = e instanceof SocketTimeoutException ? -3 : e instanceof JSONException ? -5 : e instanceof UnknownHostException ? -4 : e instanceof IOException ? -2 : -99;
            str = message;
        }
        try {
            jSONObject2.put(Response.JsonKeys.STATUS_CODE, responseCode);
            jSONObject2.put("ERROR", str);
            return jSONObject2;
        } catch (JSONException e2) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(e2);
            if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()) {
                return null;
            }
            String str3 = vikftlgmuszlvyjnlikz;
            Log.w(str3, e2.toString());
            if (e2.getMessage() == null) {
                return null;
            }
            Log.w(str3, e2.getMessage());
            return null;
        }
    }

    public void yvrzbryuycempgkdhpvj(ExecutorService executorService, final Object... objArr) {
        executorService.execute(new Runnable() { // from class: ckoaurzatqnuxckmholu.zwlltpaufqribmleigux$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws JSONException, IOException {
                this.f$0.dbuymyhwehsdoxafsfpy(objArr);
            }
        });
    }

    private HttpURLConnection yvrzbryuycempgkdhpvj() throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.uusbetktgiikylwfbevs).openConnection();
        httpURLConnection.setConnectTimeout(this.danumarvmgpbarrzqyrz.intValue());
        httpURLConnection.setReadTimeout(this.danumarvmgpbarrzqyrz.intValue());
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setChunkedStreamingMode(0);
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, this.vjgujdxqyzpnlimdrvvt.booleanValue() ? "application/octet-stream" : NfcDataRepository.FILE_TYPE_JSON);
        httpURLConnection.addRequestProperty(HttpHeaders.CONNECTION, Constants.KEY_HIDE_CLOSE);
        httpURLConnection.setRequestMethod(GrpcUtil.HTTP_METHOD);
        return httpURLConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0055 A[PHI: r5
      0x0055: PHI (r5v4 'e' java.io.IOException) = (r5v3 'e' java.io.IOException), (r5v7 'e' java.io.IOException) binds: [B:23:0x0053, B:11:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String yvrzbryuycempgkdhpvj(java.net.HttpURLConnection r5) throws java.io.IOException {
        /*
            r4 = this;
            java.io.InputStream r0 = r5.getInputStream()
            java.io.BufferedReader r1 = new java.io.BufferedReader
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            r2.<init>(r0)
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L13:
            java.lang.String r3 = r1.readLine()     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            if (r3 == 0) goto L1d
            r2.append(r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            goto L13
        L1d:
            r0.close()     // Catch: java.io.IOException -> L24
            r5.disconnect()     // Catch: java.io.IOException -> L24
            goto L5e
        L24:
            r5 = move-exception
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(r5)
            boolean r0 = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()
            if (r0 == 0) goto L5e
            goto L55
        L2f:
            r1 = move-exception
            goto L63
        L31:
            r1 = move-exception
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(r1)     // Catch: java.lang.Throwable -> L2f
            boolean r3 = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()     // Catch: java.lang.Throwable -> L2f
            if (r3 == 0) goto L44
            java.lang.String r3 = ckoaurzatqnuxckmholu.zwlltpaufqribmleigux.vikftlgmuszlvyjnlikz     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L2f
            android.util.Log.w(r3, r1)     // Catch: java.lang.Throwable -> L2f
        L44:
            r0.close()     // Catch: java.io.IOException -> L4b
            r5.disconnect()     // Catch: java.io.IOException -> L4b
            goto L5e
        L4b:
            r5 = move-exception
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(r5)
            boolean r0 = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()
            if (r0 == 0) goto L5e
        L55:
            java.lang.String r0 = ckoaurzatqnuxckmholu.zwlltpaufqribmleigux.vikftlgmuszlvyjnlikz
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r0, r5)
        L5e:
            java.lang.String r5 = r2.toString()
            return r5
        L63:
            r0.close()     // Catch: java.io.IOException -> L6a
            r5.disconnect()     // Catch: java.io.IOException -> L6a
            goto L7d
        L6a:
            r5 = move-exception
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(r5)
            boolean r0 = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.uusbetktgiikylwfbevs()
            if (r0 == 0) goto L7d
            java.lang.String r0 = ckoaurzatqnuxckmholu.zwlltpaufqribmleigux.vikftlgmuszlvyjnlikz
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r0, r5)
        L7d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ckoaurzatqnuxckmholu.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(java.net.HttpURLConnection):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void yvrzbryuycempgkdhpvj(JSONObject jSONObject) throws JSONException {
        yvrzbryuycempgkdhpvj((Object) jSONObject);
    }

    public void yvrzbryuycempgkdhpvj(Object obj) throws JSONException {
        JSONObject jSONObject;
        try {
            jSONObject = (JSONObject) obj;
            try {
                if (jSONObject.has(Response.JsonKeys.STATUS_CODE)) {
                    jSONObject.getInt(Response.JsonKeys.STATUS_CODE);
                }
            } catch (Exception e) {
                e = e;
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(e);
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.ooztjhejjvpgrdhjnyju, this.dyrapphjndqarxdhyvgv);
                this.dbuymyhwehsdoxafsfpy.yvrzbryuycempgkdhpvj(jSONObject);
            }
        } catch (Exception e2) {
            e = e2;
            jSONObject = null;
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.ooztjhejjvpgrdhjnyju, this.dyrapphjndqarxdhyvgv);
        this.dbuymyhwehsdoxafsfpy.yvrzbryuycempgkdhpvj(jSONObject);
    }
}
