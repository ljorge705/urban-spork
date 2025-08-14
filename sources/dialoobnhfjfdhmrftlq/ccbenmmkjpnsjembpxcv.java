package dialoobnhfjfdhmrftlq;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ccbenmmkjpnsjembpxcv implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List yvrzbryuycempgkdhpvj;

    public ccbenmmkjpnsjembpxcv() {
        this.yvrzbryuycempgkdhpvj = new ArrayList();
    }

    public ccbenmmkjpnsjembpxcv(JSONObject jSONObject) throws JSONException {
        this();
        if (jSONObject != null && jSONObject.has("ip_list")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("ip_list");
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.yvrzbryuycempgkdhpvj.add(jSONArray.getString(i));
                }
            } catch (JSONException unused) {
            }
        }
    }
}
