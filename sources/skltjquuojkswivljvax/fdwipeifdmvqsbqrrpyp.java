package skltjquuojkswivljvax;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fdwipeifdmvqsbqrrpyp implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp() {
    }

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        try {
            this.yvrzbryuycempgkdhpvj = jSONObject.getString("ip");
            this.dbuymyhwehsdoxafsfpy = jSONObject.getString("userAgent");
            this.uusbetktgiikylwfbevs = jSONObject.getString("acceptLanguage");
            this.ctfsaqlysacfjtqixtmr = jSONObject.getString("cookie");
        } catch (JSONException unused) {
        }
    }
}
