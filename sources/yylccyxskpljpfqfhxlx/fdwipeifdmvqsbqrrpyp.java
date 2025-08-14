package yylccyxskpljpfqfhxlx;

import com.facebook.react.util.JSStackTrace;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fdwipeifdmvqsbqrrpyp {
    private List ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private oacciftezlubzxpkwvyc vjgujdxqyzpnlimdrvvt;
    private Date yvrzbryuycempgkdhpvj = new Date();

    public fdwipeifdmvqsbqrrpyp(String str, String str2, List list, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        this.dbuymyhwehsdoxafsfpy = str;
        this.uusbetktgiikylwfbevs = str2;
        this.ctfsaqlysacfjtqixtmr = list;
        this.vjgujdxqyzpnlimdrvvt = oacciftezlubzxpkwvycVar;
    }

    public List dbuymyhwehsdoxafsfpy() {
        return this.ctfsaqlysacfjtqixtmr;
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            jSONObject.put("date", simpleDateFormat.format(this.yvrzbryuycempgkdhpvj));
            jSONObject.put("threadName", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put(JSStackTrace.METHOD_NAME_KEY, this.uusbetktgiikylwfbevs);
            jSONObject.put("statusLogginType", this.vjgujdxqyzpnlimdrvvt.toString());
            if (this.vjgujdxqyzpnlimdrvvt != oacciftezlubzxpkwvyc.End) {
                jSONObject.put("paramList", new JSONArray((Collection) this.ctfsaqlysacfjtqixtmr));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void yvrzbryuycempgkdhpvj(oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        this.vjgujdxqyzpnlimdrvvt = oacciftezlubzxpkwvycVar;
    }

    public void yvrzbryuycempgkdhpvj(long j) {
        this.yvrzbryuycempgkdhpvj = new Date(j);
    }
}
