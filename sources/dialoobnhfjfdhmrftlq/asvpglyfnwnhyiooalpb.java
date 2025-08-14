package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import io.sentry.protocol.Gpu;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class asvpglyfnwnhyiooalpb implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private ahpfbhknxzgojyggofxj.fdwipeifdmvqsbqrrpyp danumarvmgpbarrzqyrz;
    private ebpswaqugdaofldkaqte.zwlltpaufqribmleigux dbuymyhwehsdoxafsfpy;
    private ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux dyrapphjndqarxdhyvgv;
    private String efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private Long ooztjhejjvpgrdhjnyju;
    private String uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public asvpglyfnwnhyiooalpb() {
        this.ooztjhejjvpgrdhjnyju = Long.valueOf(System.currentTimeMillis());
    }

    public void ctfsaqlysacfjtqixtmr(String str) {
        this.vjgujdxqyzpnlimdrvvt = str;
    }

    public void dbuymyhwehsdoxafsfpy(String str) {
        this.mxodkqpwhcryvsgsvabl = str;
    }

    public void uusbetktgiikylwfbevs(String str) {
        this.ctfsaqlysacfjtqixtmr = str;
    }

    public void vjgujdxqyzpnlimdrvvt(String str) {
        this.yvrzbryuycempgkdhpvj = str;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            ebpswaqugdaofldkaqte.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar = this.dbuymyhwehsdoxafsfpy;
            if (zwlltpaufqribmleiguxVar != null) {
                jSONObject.put("checkpoint", zwlltpaufqribmleiguxVar.toString());
            }
            ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar2 = this.dyrapphjndqarxdhyvgv;
            if (zwlltpaufqribmleiguxVar2 != null) {
                jSONObject.put("topic_type", zwlltpaufqribmleiguxVar2.toString());
            }
            ahpfbhknxzgojyggofxj.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = this.danumarvmgpbarrzqyrz;
            if (fdwipeifdmvqsbqrrpypVar != null) {
                jSONObject.put(Gpu.JsonKeys.API_TYPE, fdwipeifdmvqsbqrrpypVar.toString());
            }
            jSONObject.put("version", this.yvrzbryuycempgkdhpvj);
            jSONObject.put(TraceContext.JsonKeys.USER_ID, this.uusbetktgiikylwfbevs);
            jSONObject.put("device_id", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("requestID", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("server_timestamp", this.ooztjhejjvpgrdhjnyju);
            jSONObject.put("app_uuid", this.mxodkqpwhcryvsgsvabl);
            jSONObject.put("app_key", this.efmnkxwvqeqnaehsyess);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void yvrzbryuycempgkdhpvj(ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar) {
        this.dyrapphjndqarxdhyvgv = zwlltpaufqribmleiguxVar;
    }

    public void yvrzbryuycempgkdhpvj(ebpswaqugdaofldkaqte.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar) {
        this.dbuymyhwehsdoxafsfpy = zwlltpaufqribmleiguxVar;
    }

    public void yvrzbryuycempgkdhpvj(Long l) {
        this.ooztjhejjvpgrdhjnyju = l;
    }

    public void yvrzbryuycempgkdhpvj(String str) {
        this.efmnkxwvqeqnaehsyess = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public asvpglyfnwnhyiooalpb(org.json.JSONObject r3) throws org.json.JSONException {
        /*
            r2 = this;
            r2.<init>()
            if (r3 != 0) goto L6
            return
        L6:
            java.lang.String r0 = "requestID"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.vjgujdxqyzpnlimdrvvt = r0
            java.lang.String r0 = "user_id"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.uusbetktgiikylwfbevs = r0
            if (r0 != 0) goto L20
            java.lang.String r0 = "userId"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.uusbetktgiikylwfbevs = r0
        L20:
            java.lang.String r0 = "device_id"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.ctfsaqlysacfjtqixtmr = r0
            if (r0 != 0) goto L32
            java.lang.String r0 = "deviceId"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.ctfsaqlysacfjtqixtmr = r0
        L32:
            java.lang.String r0 = "app_uuid"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.mxodkqpwhcryvsgsvabl = r0
            java.lang.String r0 = "app_key"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.efmnkxwvqeqnaehsyess = r0
            java.lang.String r0 = "version"
            java.lang.String r0 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(r3, r0)
            r2.yvrzbryuycempgkdhpvj = r0
            java.lang.String r0 = "topic_type"
            boolean r1 = r3.has(r0)
            if (r1 == 0) goto L64
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
            ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux r0 = ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux.valueOf(r0)     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
            r2.dyrapphjndqarxdhyvgv = r0     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
            goto L76
        L5d:
            r0 = move-exception
            goto L60
        L5f:
            r0 = move-exception
        L60:
            r0.printStackTrace()
            goto L76
        L64:
            java.lang.String r0 = "event_type"
            boolean r1 = r3.has(r0)
            if (r1 == 0) goto L76
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
            ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux r0 = ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux.valueOf(r0)     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
            r2.dyrapphjndqarxdhyvgv = r0     // Catch: java.lang.IllegalArgumentException -> L5d org.json.JSONException -> L5f
        L76:
            java.lang.String r0 = "checkpoint"
            boolean r1 = r3.has(r0)
            if (r1 == 0) goto L92
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.IllegalArgumentException -> L89 org.json.JSONException -> L8e
            ebpswaqugdaofldkaqte.zwlltpaufqribmleigux r0 = ebpswaqugdaofldkaqte.zwlltpaufqribmleigux.valueOf(r0)     // Catch: java.lang.IllegalArgumentException -> L89 org.json.JSONException -> L8e
            r2.dbuymyhwehsdoxafsfpy = r0     // Catch: java.lang.IllegalArgumentException -> L89 org.json.JSONException -> L8e
            goto L92
        L89:
            r0 = move-exception
            r2.yvrzbryuycempgkdhpvj(r3, r0)
            goto L92
        L8e:
            r0 = move-exception
            r0.printStackTrace()
        L92:
            java.lang.String r0 = "api_type"
            boolean r1 = r3.has(r0)
            if (r1 == 0) goto Lae
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.IllegalArgumentException -> La5 org.json.JSONException -> Laa
            ahpfbhknxzgojyggofxj.fdwipeifdmvqsbqrrpyp r0 = ahpfbhknxzgojyggofxj.fdwipeifdmvqsbqrrpyp.valueOf(r0)     // Catch: java.lang.IllegalArgumentException -> La5 org.json.JSONException -> Laa
            r2.danumarvmgpbarrzqyrz = r0     // Catch: java.lang.IllegalArgumentException -> La5 org.json.JSONException -> Laa
            goto Lae
        La5:
            r0 = move-exception
            r2.yvrzbryuycempgkdhpvj(r3, r0)
            goto Lae
        Laa:
            r0 = move-exception
            r0.printStackTrace()
        Lae:
            java.lang.Long r0 = r2.ooztjhejjvpgrdhjnyju
            java.lang.String r1 = "server_timestamp"
            java.lang.Long r3 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(r3, r1, r0)
            r2.ooztjhejjvpgrdhjnyju = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb.<init>(org.json.JSONObject):void");
    }

    private void yvrzbryuycempgkdhpvj(JSONObject jSONObject, IllegalArgumentException illegalArgumentException) throws JSONException {
        ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar;
        if (this.dyrapphjndqarxdhyvgv != null) {
            illegalArgumentException.printStackTrace();
            return;
        }
        String string = jSONObject.getString("checkpoint");
        if (string.toLowerCase().contains("_response")) {
            zwlltpaufqribmleiguxVar = ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux.CHECKPOINT_RESPONSE;
        } else if (!string.toLowerCase().contains("updatetransactiontesult")) {
            return;
        } else {
            zwlltpaufqribmleiguxVar = ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux.UPDATE_TRANSACTION_RESPONSE;
        }
        this.dyrapphjndqarxdhyvgv = zwlltpaufqribmleiguxVar;
    }
}
