package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class rvhplcmttaqkggggovhx implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List ctfsaqlysacfjtqixtmr;
    private List dbuymyhwehsdoxafsfpy;
    private List mxodkqpwhcryvsgsvabl;
    private List uusbetktgiikylwfbevs;
    private List vjgujdxqyzpnlimdrvvt;
    private List yvrzbryuycempgkdhpvj;

    public rvhplcmttaqkggggovhx(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("similarities")) {
            this.yvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(jSONObject, "similarities");
        }
        if (jSONObject.has("featureStats")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("featureStats");
                if (jSONObject2.has("TouchIntraTime")) {
                    this.dbuymyhwehsdoxafsfpy = yvrzbryuycempgkdhpvj(jSONObject2, "TouchIntraTime");
                }
                if (jSONObject2.has("TouchSize")) {
                    this.uusbetktgiikylwfbevs = yvrzbryuycempgkdhpvj(jSONObject2, "TouchSize");
                }
                if (jSONObject2.has("TouchVelocity")) {
                    this.ctfsaqlysacfjtqixtmr = yvrzbryuycempgkdhpvj(jSONObject2, "TouchVelocity");
                }
                if (jSONObject2.has("TouchEntireDeltaX")) {
                    this.vjgujdxqyzpnlimdrvvt = yvrzbryuycempgkdhpvj(jSONObject2, "TouchEntireDeltaX");
                }
                if (jSONObject2.has("DeltaX")) {
                    this.mxodkqpwhcryvsgsvabl = yvrzbryuycempgkdhpvj(jSONObject2, "DeltaX");
                }
            } catch (JSONException unused) {
            }
        }
    }

    private List yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(Double.valueOf(jSONArray.getDouble(i)));
                } catch (JSONException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
        return arrayList;
    }
}
