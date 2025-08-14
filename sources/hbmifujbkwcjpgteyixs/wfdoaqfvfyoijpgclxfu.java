package hbmifujbkwcjpgteyixs;

import com.paygilant.pgdata.CheckPoint.ScreenListenerType;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class wfdoaqfvfyoijpgclxfu implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Double[][] danumarvmgpbarrzqyrz;
    private ScreenListenerType dbuymyhwehsdoxafsfpy;
    private Long[][] efmnkxwvqeqnaehsyess;
    private Double[][] mxodkqpwhcryvsgsvabl;
    private Integer uusbetktgiikylwfbevs;
    private Long[][] vikftlgmuszlvyjnlikz;
    private Boolean yvrzbryuycempgkdhpvj;
    private List ctfsaqlysacfjtqixtmr = Arrays.asList("x", "y", RRWebVideoEvent.JsonKeys.SIZE, "pressure", "id");
    private List vjgujdxqyzpnlimdrvvt = Arrays.asList("timeEvent", "view_id");
    private List dyrapphjndqarxdhyvgv = Arrays.asList("lin_acc_x", "lin_acc_y", "lin_acc_z", "gyr_unc_x", "gyr_unc_y", "gyr_unc_z", "gra_x", "gra_y", "gra_z");
    private List ooztjhejjvpgrdhjnyju = Arrays.asList("nanoTimeSensor");

    public wfdoaqfvfyoijpgclxfu(JSONObject jSONObject) throws JSONException {
        this.yvrzbryuycempgkdhpvj = Boolean.FALSE;
        this.mxodkqpwhcryvsgsvabl = new Double[0][];
        this.efmnkxwvqeqnaehsyess = new Long[0][];
        this.danumarvmgpbarrzqyrz = new Double[0][];
        this.vikftlgmuszlvyjnlikz = new Long[0][];
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("fraudster")) {
            try {
                this.yvrzbryuycempgkdhpvj = Boolean.valueOf(jSONObject.getBoolean("fraudster"));
            } catch (JSONException unused) {
            }
        }
        if (jSONObject.has("actionType")) {
            try {
                this.dbuymyhwehsdoxafsfpy = ScreenListenerType.valueOf(jSONObject.getString("actionType"));
            } catch (JSONException unused2) {
            }
        }
        if (jSONObject.has("actionId")) {
            try {
                this.uusbetktgiikylwfbevs = Integer.valueOf(jSONObject.getInt("actionId"));
            } catch (JSONException unused3) {
            }
        }
        if (jSONObject.has("eventList")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("eventList");
                if (jSONArray.length() > 0) {
                    this.mxodkqpwhcryvsgsvabl = (Double[][]) Array.newInstance((Class<?>) Double.class, jSONArray.length(), jSONArray.getJSONArray(0).length());
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            this.mxodkqpwhcryvsgsvabl[i][i2] = Double.valueOf(jSONArray2.getDouble(i2));
                        }
                    }
                }
            } catch (Exception unused4) {
            }
        }
        if (jSONObject.has("sensorsData")) {
            try {
                JSONArray jSONArray3 = jSONObject.getJSONArray("sensorsData");
                if (jSONArray3.length() > 0) {
                    this.danumarvmgpbarrzqyrz = (Double[][]) Array.newInstance((Class<?>) Double.class, jSONArray3.length(), jSONArray3.getJSONArray(0).length());
                    for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                        JSONArray jSONArray4 = jSONArray3.getJSONArray(i3);
                        for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                            this.danumarvmgpbarrzqyrz[i3][i4] = Double.valueOf(jSONArray4.getDouble(i4));
                        }
                    }
                }
            } catch (Exception unused5) {
            }
        }
        if (jSONObject.has("sensorsTS")) {
            try {
                JSONArray jSONArray5 = jSONObject.getJSONArray("sensorsTS");
                if (jSONArray5.length() > 0) {
                    this.vikftlgmuszlvyjnlikz = (Long[][]) Array.newInstance((Class<?>) Long.class, jSONArray5.length(), jSONArray5.getJSONArray(0).length());
                    for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                        JSONArray jSONArray6 = jSONArray5.getJSONArray(i5);
                        for (int i6 = 0; i6 < jSONArray6.length(); i6++) {
                            this.vikftlgmuszlvyjnlikz[i5][i6] = Long.valueOf(jSONArray6.getLong(i6));
                        }
                    }
                }
            } catch (Exception unused6) {
            }
        }
        if (jSONObject.has("eventListTS")) {
            try {
                JSONArray jSONArray7 = jSONObject.getJSONArray("eventListTS");
                if (jSONArray7.length() > 0) {
                    this.efmnkxwvqeqnaehsyess = (Long[][]) Array.newInstance((Class<?>) Long.class, jSONArray7.length(), jSONArray7.getJSONArray(0).length());
                    for (int i7 = 0; i7 < jSONArray7.length(); i7++) {
                        JSONArray jSONArray8 = jSONArray7.getJSONArray(i7);
                        for (int i8 = 0; i8 < jSONArray8.length(); i8++) {
                            this.efmnkxwvqeqnaehsyess[i7][i8] = Long.valueOf(jSONArray8.getLong(i8));
                        }
                    }
                }
            } catch (Exception unused7) {
            }
        }
    }
}
