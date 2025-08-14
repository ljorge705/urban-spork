package hbmifujbkwcjpgteyixs;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class lhsfeadmwwqkbzscxaex implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Boolean dbuymyhwehsdoxafsfpy;
    private double yvrzbryuycempgkdhpvj;

    public lhsfeadmwwqkbzscxaex(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has(OnfidoLauncher.KEY_RESULT)) {
            this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, OnfidoLauncher.KEY_RESULT, false);
        }
        if (jSONObject.has("anomalyScore")) {
            this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "anomalyScore", Double.valueOf(0.0d)).doubleValue();
        }
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(OnfidoLauncher.KEY_RESULT, this.dbuymyhwehsdoxafsfpy);
        jSONObject.put("anomalyScore", this.yvrzbryuycempgkdhpvj);
        return jSONObject;
    }
}
