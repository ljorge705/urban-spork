package cyxdnekglwjxeogqvedd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class oacciftezlubzxpkwvyc implements Serializable {
    Boolean dbuymyhwehsdoxafsfpy;
    rvhplcmttaqkggggovhx yvrzbryuycempgkdhpvj;

    public oacciftezlubzxpkwvyc() {
    }

    public Boolean dbuymyhwehsdoxafsfpy() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public String toString() {
        return "compareDataList [compareDataType=" + this.yvrzbryuycempgkdhpvj + ", isCompare=" + this.dbuymyhwehsdoxafsfpy + "]";
    }

    public rvhplcmttaqkggggovhx uusbetktgiikylwfbevs() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public List yvrzbryuycempgkdhpvj(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = (JSONObject) it.next();
                arrayList.add(new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.valueOf(jSONObject.getString("compareDataType")), Boolean.valueOf(jSONObject.getBoolean("isCompare"))));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx rvhplcmttaqkggggovhxVar, Boolean bool) {
        this.yvrzbryuycempgkdhpvj = rvhplcmttaqkggggovhxVar;
        this.dbuymyhwehsdoxafsfpy = bool;
    }
}
