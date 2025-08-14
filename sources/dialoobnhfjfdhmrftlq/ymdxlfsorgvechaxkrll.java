package dialoobnhfjfdhmrftlq;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ymdxlfsorgvechaxkrll implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Set yvrzbryuycempgkdhpvj;

    public ymdxlfsorgvechaxkrll() {
        this.yvrzbryuycempgkdhpvj = new HashSet();
    }

    public Set dbuymyhwehsdoxafsfpy() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ymdxlfsorgvechaxkrll)) {
            return false;
        }
        ymdxlfsorgvechaxkrll ymdxlfsorgvechaxkrllVar = (ymdxlfsorgvechaxkrll) obj;
        if (!ymdxlfsorgvechaxkrllVar.yvrzbryuycempgkdhpvj(this)) {
            return false;
        }
        Set setDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        Set setDbuymyhwehsdoxafsfpy2 = ymdxlfsorgvechaxkrllVar.dbuymyhwehsdoxafsfpy();
        return setDbuymyhwehsdoxafsfpy != null ? setDbuymyhwehsdoxafsfpy.equals(setDbuymyhwehsdoxafsfpy2) : setDbuymyhwehsdoxafsfpy2 == null;
    }

    public int hashCode() {
        Set setDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        return (setDbuymyhwehsdoxafsfpy == null ? 43 : setDbuymyhwehsdoxafsfpy.hashCode()) + 59;
    }

    public String toString() {
        return "DeviceUsers(usersOnDeviceList=" + dbuymyhwehsdoxafsfpy() + ")";
    }

    protected boolean yvrzbryuycempgkdhpvj(Object obj) {
        return obj instanceof ymdxlfsorgvechaxkrll;
    }

    public ymdxlfsorgvechaxkrll(JSONObject jSONObject) throws JSONException {
        this();
        if (jSONObject != null && jSONObject.has("device_users_list")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("device_users_list");
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        this.yvrzbryuycempgkdhpvj.add(new cctxrwizduxmjefyvyrx(jSONArray.getJSONObject(i)));
                    } catch (JSONException unused) {
                    }
                }
            } catch (JSONException unused2) {
            }
        }
    }
}
