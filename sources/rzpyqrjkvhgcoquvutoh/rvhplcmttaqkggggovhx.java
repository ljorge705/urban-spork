package rzpyqrjkvhgcoquvutoh;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rvhplcmttaqkggggovhx implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List ctfsaqlysacfjtqixtmr;
    private buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp dbuymyhwehsdoxafsfpy;
    private List uusbetktgiikylwfbevs;
    private buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp yvrzbryuycempgkdhpvj;

    public rvhplcmttaqkggggovhx() {
        this.yvrzbryuycempgkdhpvj = new buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp();
        this.dbuymyhwehsdoxafsfpy = new buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp();
        this.uusbetktgiikylwfbevs = new ArrayList();
        this.ctfsaqlysacfjtqixtmr = new ArrayList();
    }

    private buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        try {
            return new buermssrvkevhtyundwb.fdwipeifdmvqsbqrrpyp(jSONObject.getJSONObject(str));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    public rvhplcmttaqkggggovhx(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.dbuymyhwehsdoxafsfpy = yvrzbryuycempgkdhpvj(jSONObject, "user_payment_methods_aggregation");
        this.yvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(jSONObject, "device_payment_methods_aggregation");
    }
}
