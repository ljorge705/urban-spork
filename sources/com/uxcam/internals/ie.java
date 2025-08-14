package com.uxcam.internals;

import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ie implements Callback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f207a;
    public final /* synthetic */ Cif b;

    public ie(Cif cif, String str) {
        this.b = cif;
        this.f207a = str;
    }

    @Override // okhttp3.Callback
    public final void onFailure(Call call, IOException iOException) {
        gk.aa aaVarA = gk.a("OkHttp");
        iOException.getMessage();
        aaVarA.getClass();
        String strReplace = "[#status#] #method#".replace("#method#", "httpVerifyApplication").replace("#status#", "FAIL");
        HashMap map = new HashMap();
        map.put("site_of_error", "VerificationHandler::onFailure()");
        map.put("invokes_next", "treatAsOfflineSession()");
        map.put("reason", iOException.getMessage());
        ht.a(strReplace, (Map<String, String>) map);
        this.b.a(this.f207a);
    }

    @Override // okhttp3.Callback
    public final void onResponse(Call call, Response response) throws JSONException {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        if (biVar.p == null) {
            biVar.p = new du();
        }
        du duVar = biVar.p;
        Intrinsics.checkNotNull(duVar);
        duVar.f127a.f126a = response.receivedResponseAtMillis() - response.sentRequestAtMillis();
        try {
        } catch (JSONException e) {
            e.printStackTrace();
            String strReplace = "[#status#] #method#".replace("#method#", "httpVerifyApplication").replace("#status#", "FAIL");
            HashMap map = new HashMap();
            map.put("site_of_error", "try { }");
            map.put("reason", e.getMessage());
            ht.a(strReplace, (Map<String, String>) map);
        }
        JSONObject jSONObject = response.body() != null ? new JSONObject(response.body().string()) : null;
        if (jSONObject != null && response.code() == 200) {
            this.b.a(jSONObject, false, this.f207a);
            try {
                jSONObject.getJSONObject("data").remove("s3");
                jSONObject.getJSONObject("data").remove("sessionId");
                jSONObject.getJSONObject("data").remove("deviceUrl");
                jSONObject.getJSONObject("data").remove("sessionUrl");
            } catch (JSONException e2) {
                String strReplace2 = "[#status#] #method#".replace("#method#", "httpVerifyApplication").replace("#status#", "FAIL");
                HashMap map2 = new HashMap();
                map2.put("site_of_error", "try -- try { } : while removing previous data from ");
                map2.put("reason", e2.getMessage());
                ht.a(strReplace2, (Map<String, String>) map2);
            }
            new eg(this.b.f208a).a("settings_" + this.f207a.hashCode(), jSONObject.toString());
            return;
        }
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        ((fv) biVar2.f()).c = false;
        bh.b = false;
        gk.aa aaVarA = gk.a(Cif.c);
        response.message();
        aaVarA.getClass();
        String strReplace3 = "[#status#] #method#".replace("#method#", "httpVerifyApplication").replace("#status#", "FAIL");
        HashMap map3 = new HashMap();
        map3.put("site_of_error", "VerificationHandler::onResponse() ");
        map3.put("invokes_next", "treatAsOfflineSession()");
        map3.put("site_of_error", "200 != response.code()");
        map3.put("reason", "Expected status code { 200 } but received was { " + response.code() + " }");
        ht.a(strReplace3, (Map<String, String>) map3);
        this.b.a(this.f207a);
    }
}
