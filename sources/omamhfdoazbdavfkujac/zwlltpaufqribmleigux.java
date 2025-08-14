package omamhfdoazbdavfkujac;

import android.content.Context;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class zwlltpaufqribmleigux {
    private static final String efmnkxwvqeqnaehsyess = "zwlltpaufqribmleigux";
    public ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx ctfsaqlysacfjtqixtmr;
    Context dbuymyhwehsdoxafsfpy;
    private final ExecutorService mxodkqpwhcryvsgsvabl = Executors.newSingleThreadExecutor();
    private String uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private JSONObject yvrzbryuycempgkdhpvj;

    public zwlltpaufqribmleigux(JSONObject jSONObject, Context context, String str, String str2, ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx rvhplcmttaqkggggovhxVar) {
        this.yvrzbryuycempgkdhpvj = jSONObject;
        this.dbuymyhwehsdoxafsfpy = context;
        this.uusbetktgiikylwfbevs = str;
        this.ctfsaqlysacfjtqixtmr = rvhplcmttaqkggggovhxVar;
        this.vjgujdxqyzpnlimdrvvt = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean dbuymyhwehsdoxafsfpy() throws IOException {
        try {
            StringBuilder sbAppend = new StringBuilder().append(this.dbuymyhwehsdoxafsfpy.getFilesDir());
            String str = File.separator;
            String string = sbAppend.append(str).append(this.uusbetktgiikylwfbevs).toString();
            File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
            if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.dbuymyhwehsdoxafsfpy, rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs.intValue(), this.uusbetktgiikylwfbevs).booleanValue()) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(string + str + this.vjgujdxqyzpnlimdrvvt + ".json")));
                bufferedWriter.write(this.yvrzbryuycempgkdhpvj.toString());
                bufferedWriter.close();
                Log.d(efmnkxwvqeqnaehsyess, "saved file to : " + string);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return Boolean.FALSE;
        }
    }

    public void yvrzbryuycempgkdhpvj() {
        try {
            this.ctfsaqlysacfjtqixtmr.yvrzbryuycempgkdhpvj(Boolean.valueOf(((Boolean) this.mxodkqpwhcryvsgsvabl.submit(new Callable() { // from class: omamhfdoazbdavfkujac.zwlltpaufqribmleigux$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.dbuymyhwehsdoxafsfpy();
                }
            }).get()).booleanValue()));
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            this.ctfsaqlysacfjtqixtmr.yvrzbryuycempgkdhpvj(Boolean.FALSE);
        }
    }
}
