package com.uxcam.internals;

import android.util.Pair;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import io.sentry.SentryEvent;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class hv implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    public final Thread.UncaughtExceptionHandler f200a;

    public hv(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f200a = uncaughtExceptionHandler;
    }

    public static Pair<JSONArray, String> a(String str, boolean z) throws JSONException {
        boolean zEquals;
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        String string = "";
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            try {
                zEquals = entry.getKey().getName().equals(str);
                jSONObject = new JSONObject();
                jSONObject.put("title", entry.getKey().getName());
                jSONObject.put("state", entry.getKey().getState());
                JSONArray jSONArray2 = new JSONArray();
                for (StackTraceElement stackTraceElement : entry.getValue()) {
                    if (zEquals && string.isEmpty()) {
                        string = stackTraceElement.toString();
                    }
                    jSONArray2.put(stackTraceElement.toString());
                }
                jSONObject.put("backtrace", jSONArray2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (zEquals && z) {
                jSONArray = new JSONArray().put(jSONObject);
                break;
            }
            jSONArray.put(jSONObject);
        }
        return new Pair<>(jSONArray, string);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) throws JSONException, InterruptedException {
        gk.a("hv").getClass();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(a(th));
        String string = th.getStackTrace()[0] != null ? th.getStackTrace()[0].toString() : "";
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            jSONArray.put(a(cause));
            if (string.isEmpty() && cause.getStackTrace()[0] != null) {
                string = cause.getStackTrace()[0].toString();
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashedThread", ((JSONArray) a(thread.getName(), true).first).getJSONObject(0));
            jSONObject.put("crashExceptions", jSONArray);
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            jSONObject.put(KeyConstant.KEY_SCREEN, ((gw) biVar.i()).g());
            jSONObject.put(SentryEvent.JsonKeys.THREADS, a(thread.getName(), false).first);
            jSONObject.put("crashedThread-TopOfStack", string);
            jSONObject.put("time", Util.getCurrentUxcamTime(fp.f157n));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            HashMap map = new HashMap();
            map.put(SentryEvent.JsonKeys.EXCEPTION, th);
            ht.a("UnCaughtExceptionHandled", (Map<String, String>) map);
        } catch (Exception e2) {
            fj fjVarB = new fj().b("UncaughtExceptionHandler::uncaughtException()");
            fjVarB.a("reason", e2.getMessage());
            fjVarB.a(2);
        }
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        fp fpVarE = biVar2.e();
        Util.getCurrentApplicationContext();
        fpVarE.a(jSONObject.toString());
        this.f200a.uncaughtException(thread, th);
    }

    public static JSONObject a(Throwable th) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("UnhandledExceptionName", th.getClass().getName());
            jSONObject.put("UnhandledExceptionReason", th.getMessage());
            JSONArray jSONArray = new JSONArray();
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                jSONArray.put(stackTrace[i].toString());
                i2++;
                if (i2 > 100) {
                    jSONArray.put("---BACKTRACE STOPPED AT 100 ENTRIES---");
                    break;
                }
                i++;
            }
            jSONObject.put("UnhandledExceptionCallStack", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
