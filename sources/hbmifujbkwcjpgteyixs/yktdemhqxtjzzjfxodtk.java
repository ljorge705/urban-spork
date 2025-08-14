package hbmifujbkwcjpgteyixs;

import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class yktdemhqxtjzzjfxodtk implements Serializable {
    private String dbuymyhwehsdoxafsfpy;
    private Long yvrzbryuycempgkdhpvj;

    public yktdemhqxtjzzjfxodtk(Long l, String str) {
        this.yvrzbryuycempgkdhpvj = l;
        this.dbuymyhwehsdoxafsfpy = str;
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fileTimestamp", this.yvrzbryuycempgkdhpvj);
            jSONObject.put(ReactNativeBridgeUtiles.KEY_FILE_NAME, this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public yktdemhqxtjzzjfxodtk(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "fileTimestamp");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, ReactNativeBridgeUtiles.KEY_FILE_NAME);
    }
}
