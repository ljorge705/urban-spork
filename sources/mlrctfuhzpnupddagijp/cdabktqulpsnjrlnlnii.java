package mlrctfuhzpnupddagijp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class cdabktqulpsnjrlnlnii implements ppvnkbmzfphuuihfhotp {
    private Boolean yvrzbryuycempgkdhpvj = Boolean.FALSE;

    public lsyvvjsmnvzorkeizuqg.oacciftezlubzxpkwvyc dbuymyhwehsdoxafsfpy() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cdabktqulpsnjrlnlnii)) {
            return false;
        }
        cdabktqulpsnjrlnlnii cdabktqulpsnjrlnlniiVar = (cdabktqulpsnjrlnlnii) obj;
        if (!cdabktqulpsnjrlnlniiVar.yvrzbryuycempgkdhpvj(this)) {
            return false;
        }
        Boolean boolUusbetktgiikylwfbevs = uusbetktgiikylwfbevs();
        Boolean boolUusbetktgiikylwfbevs2 = cdabktqulpsnjrlnlniiVar.uusbetktgiikylwfbevs();
        if (boolUusbetktgiikylwfbevs != null ? !boolUusbetktgiikylwfbevs.equals(boolUusbetktgiikylwfbevs2) : boolUusbetktgiikylwfbevs2 != null) {
            return false;
        }
        dbuymyhwehsdoxafsfpy();
        cdabktqulpsnjrlnlniiVar.dbuymyhwehsdoxafsfpy();
        return true;
    }

    public int hashCode() {
        Boolean boolUusbetktgiikylwfbevs = uusbetktgiikylwfbevs();
        int iHashCode = boolUusbetktgiikylwfbevs == null ? 43 : boolUusbetktgiikylwfbevs.hashCode();
        dbuymyhwehsdoxafsfpy();
        return ((iHashCode + 59) * 59) + 43;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("SanctionsScreeningScore(score=").append(uusbetktgiikylwfbevs()).append(", sanctionsResponse=null)");
        dbuymyhwehsdoxafsfpy();
        return sbAppend.toString();
    }

    public Boolean uusbetktgiikylwfbevs() {
        return this.yvrzbryuycempgkdhpvj;
    }

    protected boolean yvrzbryuycempgkdhpvj(Object obj) {
        return obj instanceof cdabktqulpsnjrlnlnii;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() {
        try {
            return new JSONObject(new ObjectMapper().writeValueAsString((Object) null));
        } catch (JsonProcessingException unused) {
            return null;
        }
    }

    public void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            fdwipeifdmvqsbqrrpyp.yvrzbryuycempgkdhpvj(new ObjectMapper().readValue(jSONObject.toString(), lsyvvjsmnvzorkeizuqg.oacciftezlubzxpkwvyc.class));
            throw null;
        } catch (JsonProcessingException unused) {
        }
    }
}
