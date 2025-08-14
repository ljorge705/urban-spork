package dialoobnhfjfdhmrftlq;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ppvnkbmzfphuuihfhotp implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Integer dbuymyhwehsdoxafsfpy;
    private double uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public ppvnkbmzfphuuihfhotp() {
        this.yvrzbryuycempgkdhpvj = "default_group";
        this.uusbetktgiikylwfbevs = 1.0d;
    }

    public ppvnkbmzfphuuihfhotp(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "group_name", "default_group");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "multiplier", Double.valueOf(1.0d)).doubleValue();
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, FirebaseAnalytics.Param.GROUP_ID);
    }
}
