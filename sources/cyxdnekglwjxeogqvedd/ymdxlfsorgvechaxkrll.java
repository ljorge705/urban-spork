package cyxdnekglwjxeogqvedd;

import com.paygilant.pgdata.CheckPoint.ScreenListenerType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ymdxlfsorgvechaxkrll implements Serializable {
    private Integer dbuymyhwehsdoxafsfpy;
    private ScreenListenerType yvrzbryuycempgkdhpvj;

    public ymdxlfsorgvechaxkrll() {
    }

    public String toString() {
        return "ScreenListenersList [screenListenerType=" + this.yvrzbryuycempgkdhpvj + ", screenId=" + this.dbuymyhwehsdoxafsfpy + "]";
    }

    public List yvrzbryuycempgkdhpvj(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = (JSONObject) it.next();
                arrayList.add(new ymdxlfsorgvechaxkrll(ScreenListenerType.valueOf(jSONObject.getString("screenListenerType")), Integer.valueOf(jSONObject.getInt("screenId"))));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public ymdxlfsorgvechaxkrll(ScreenListenerType screenListenerType, Integer num) {
        this.yvrzbryuycempgkdhpvj = screenListenerType;
        this.dbuymyhwehsdoxafsfpy = num;
    }

    public ymdxlfsorgvechaxkrll(JSONObject jSONObject) {
        this();
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = ScreenListenerType.valueOf(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "screenListenerType"));
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "screenId");
    }
}
