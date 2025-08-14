package dialoobnhfjfdhmrftlq;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class kaqespyeeawijtnikoai implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private List efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private List uusbetktgiikylwfbevs;
    private ahpfbhknxzgojyggofxj.rvhplcmttaqkggggovhx vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public kaqespyeeawijtnikoai() {
    }

    public kaqespyeeawijtnikoai(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        try {
            this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
            this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "userID");
            this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "comment");
            this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.efmnkxwvqeqnaehsyess(jSONObject, "fraud_type_ids");
            if (jSONObject.has("requestIDs")) {
                this.uusbetktgiikylwfbevs = (List) jSONObject.getJSONArray("requestIDs").toList().stream().map(new Function() { // from class: dialoobnhfjfdhmrftlq.kaqespyeeawijtnikoai$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return obj.toString();
                    }
                }).collect(Collectors.toList());
            }
            if (jSONObject.has("userIDs")) {
                this.ctfsaqlysacfjtqixtmr = (List) jSONObject.getJSONArray("userIDs").toList().stream().map(new Function() { // from class: dialoobnhfjfdhmrftlq.kaqespyeeawijtnikoai$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return obj.toString();
                    }
                }).collect(Collectors.toList());
            }
            this.vjgujdxqyzpnlimdrvvt = (ahpfbhknxzgojyggofxj.rvhplcmttaqkggggovhx) iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "event_action", ahpfbhknxzgojyggofxj.rvhplcmttaqkggggovhx.class, null);
        } catch (JSONException unused) {
        }
    }
}
