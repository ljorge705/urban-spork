package hbmifujbkwcjpgteyixs;

import com.clevertap.android.sdk.Constants;
import com.paygilant.pgdata.CheckPoint.ScreenListenerType;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ccbenmmkjpnsjembpxcv implements Serializable {
    private String ctfsaqlysacfjtqixtmr;
    private Integer dbuymyhwehsdoxafsfpy;
    private long uusbetktgiikylwfbevs;
    private ScreenListenerType yvrzbryuycempgkdhpvj;

    public ccbenmmkjpnsjembpxcv() {
    }

    public ccbenmmkjpnsjembpxcv(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "screenListenerType");
        this.yvrzbryuycempgkdhpvj = strLdeiitcdqlqzkidvrbjy == null ? null : ScreenListenerType.valueOf(strLdeiitcdqlqzkidvrbjy);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "screenId");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "timestamp").longValue();
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, Constants.KEY_ACTION);
    }
}
