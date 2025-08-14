package dialoobnhfjfdhmrftlq;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class mchrzzmrgfkrwlaxrlna implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Map yvrzbryuycempgkdhpvj;

    public mchrzzmrgfkrwlaxrlna() {
        this.yvrzbryuycempgkdhpvj = new HashMap();
    }

    public mchrzzmrgfkrwlaxrlna(JSONArray jSONArray) {
        this();
        if (jSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                zwlltpaufqribmleigux zwlltpaufqribmleiguxVar = new zwlltpaufqribmleigux(jSONArray.getJSONObject(i));
                this.yvrzbryuycempgkdhpvj.put(zwlltpaufqribmleiguxVar.dbuymyhwehsdoxafsfpy(), zwlltpaufqribmleiguxVar.uusbetktgiikylwfbevs());
            } catch (JSONException unused) {
            }
        }
    }
}
