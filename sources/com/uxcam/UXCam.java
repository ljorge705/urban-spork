package com.uxcam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.uxcam.aa;
import com.uxcam.datamodel.UXConfig;
import com.uxcam.internals.bh;
import com.uxcam.internals.bi;
import com.uxcam.internals.bl;
import com.uxcam.internals.ea;
import com.uxcam.internals.eg;
import com.uxcam.internals.ej;
import com.uxcam.internals.ek;
import com.uxcam.internals.ff;
import com.uxcam.internals.fm;
import com.uxcam.internals.fu;
import com.uxcam.internals.ga;
import com.uxcam.internals.gk;
import com.uxcam.internals.hj;
import com.uxcam.internals.hk;
import com.uxcam.internals.hl;
import com.uxcam.internals.hn;
import com.uxcam.internals.hp;
import com.uxcam.internals.ht;
import com.uxcam.internals.hv;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import io.sentry.SentryEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class UXCam {
    public static void addScreenNameToIgnore(String str) {
        boolean z = aa.h;
        ga.D.add(str);
    }

    public static void addScreenNamesToIgnore(List<String> list) {
        boolean z = aa.h;
        TreeSet treeSet = ga.D;
        Intrinsics.checkNotNull(list);
        treeSet.addAll(list);
    }

    @Deprecated
    public static void addTagWithProperties(String str) {
        logEvent(str);
    }

    public static void addVerificationListener(OnVerificationListener onVerificationListener) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.f().b(onVerificationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void allowShortBreakForAnotherApp() {
        boolean z = aa.h;
        ff.j = 180000;
    }

    public static void applyOcclusion(UXCamOcclusion uXCamOcclusion) {
        boolean z = aa.h;
        ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().applyOcclusionFromSDK(uXCamOcclusion);
    }

    public static void attachUnsupportedView(MapFragment mapFragment) {
        boolean z = aa.h;
        aa.C0190aa.a(mapFragment);
    }

    public static void attachWebviewInterface(WebView webView) {
        boolean z = aa.h;
        Intrinsics.checkNotNullParameter(webView, "webView");
        ga.F = false;
        webView.addJavascriptInterface(new hp(), "UXCam");
    }

    public static void cancelCurrentSession() {
        ga.f168n = true;
        stopSessionAndUploadData();
    }

    @Deprecated
    public static void cancelRecording() {
        cancelCurrentSession();
    }

    public static void deletePendingUploads() {
        boolean z = aa.h;
        try {
            new fm(Util.getCurrentApplicationContext()).a();
            ht.a("deletePendingUploadApiCalled", new HashMap());
        } catch (Exception e) {
            e.printStackTrace();
            gk.c.getClass();
        }
    }

    @Deprecated
    public static void disableCrashHandling(boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.j().a(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getMultiSessionRecord() {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        return biVar.l().a().d == UXConfig.MultiSessionRecordStatus.ENABLED;
    }

    public static hj getOkHttpInterceptor() {
        ea.aa.C0192aa c0192aa = new ea.aa.C0192aa();
        hj.c = true;
        return new hj(c0192aa);
    }

    public static String getSdkVersionInfo() {
        return String.format(Locale.ENGLISH, "%s (%d)", "3.6.13", 580);
    }

    @Deprecated
    public static void identify(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.j().a(str);
    }

    @Deprecated
    public static void ignoreRecording() {
        cancelCurrentSession();
    }

    public static boolean isRecording() {
        try {
            boolean z = aa.h;
            return bh.f97a;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void logEvent(String str) {
        try {
            boolean z = aa.h;
            aa.C0190aa.a(str, (Map) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logNotification(Context context, Intent intent) throws JSONException {
        JSONObject jSONObject;
        boolean z = aa.h;
        if (intent == null || !intent.hasExtra("UXCam_data")) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(intent.getStringExtra("UXCam_data"));
            } catch (JSONException e) {
                e.getMessage();
            }
        }
        if (jSONObject != null) {
            ej ejVar = new ej(System.currentTimeMillis(), jSONObject);
            ArrayList arrayListA = ek.a(context);
            arrayListA.add(ejVar);
            JSONArray jSONArray = new JSONArray();
            try {
                Iterator it = arrayListA.iterator();
                while (it.hasNext()) {
                    ej ejVar2 = (ej) it.next();
                    ejVar2.getClass();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("timeStamp", ejVar2.f136a);
                    jSONObject2.put("uxCamData", ejVar2.b);
                    jSONObject2.put("timeLine", ejVar2.c);
                    jSONArray.put(jSONObject2);
                }
            } catch (JSONException e2) {
                e2.getMessage();
            }
            new eg(context).a("push_notification_data", jSONArray.toString());
        }
    }

    @Deprecated
    public static void markSessionAsFavorite() {
        setSessionProperty("kUXCam_isFavourite", "true");
    }

    public static void occludeAllTextFields(boolean z) {
        boolean z2 = aa.h;
        UXCamOccludeAllTextFields uXCamOccludeAllTextFieldsBuild = new UXCamOccludeAllTextFields.Builder().build();
        if (z) {
            ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().applyOcclusionFromSDK(uXCamOccludeAllTextFieldsBuild);
        } else {
            ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().removeOcclusionFromSDK(uXCamOccludeAllTextFieldsBuild);
        }
        if (z) {
            return;
        }
        Iterator it = CollectionsKt.toMutableList((Collection) ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().getViewsToHide()).iterator();
        while (it.hasNext()) {
            if (!((UXCamOccludeView) it.next()).isAddedByUser()) {
                it.remove();
            }
        }
    }

    @Deprecated
    public static void occludeAllTextView() {
        occludeAllTextFields(true);
    }

    public static void occludeRectsOnNextFrame(JSONArray rects) {
        boolean z = aa.h;
        Intrinsics.checkNotNullParameter(rects, "rects");
        try {
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder();
            int length = rects.length();
            for (int i = 0; i < length; i++) {
                JSONArray jSONArray = rects.getJSONArray(i);
                Rect rect = new Rect();
                rect.left = jSONArray.getInt(0);
                rect.top = jSONArray.getInt(1);
                rect.right = jSONArray.getInt(2);
                rect.bottom = jSONArray.getInt(3);
                screenshotStateHolder.addRectToHide(rect);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void occludeSensitiveScreen(boolean z) {
        try {
            UXCamOverlay uXCamOverlayBuild = new UXCamOverlay.Builder().build();
            if (z) {
                applyOcclusion(uXCamOverlayBuild);
            } else {
                removeOcclusion(uXCamOverlayBuild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void occludeSensitiveView(View occludeView) {
        try {
            boolean z = aa.h;
            Intrinsics.checkNotNullParameter(occludeView, "occludeView");
            UXCamOccludeView uXCamOccludeView = new UXCamOccludeView(true);
            uXCamOccludeView.setView(new WeakReference<>(occludeView));
            uXCamOccludeView.setStopTrackingGestures(false);
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().addViewToHide(uXCamOccludeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void occludeSensitiveViewWithoutGesture(View occludeView) {
        try {
            boolean z = aa.h;
            Intrinsics.checkNotNullParameter(occludeView, "occludeView");
            UXCamOccludeView uXCamOccludeView = new UXCamOccludeView(true);
            uXCamOccludeView.setView(new WeakReference<>(occludeView));
            uXCamOccludeView.setStopTrackingGestures(true);
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().addViewToHide(uXCamOccludeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void optIn() {
        optInOverall();
    }

    public static void optInOverall() {
        aa.k = true;
        aa.C0190aa.b();
        if (Util.getCurrentApplicationContext() != null) {
            new eg(Util.getCurrentApplicationContext()).a("opt_out", false);
        } else {
            ga.q = 0;
        }
        ga.q = 0;
        if (!bh.f97a) {
            startNewSession();
        }
        aa.k = true;
    }

    public static boolean optInOverallStatus() {
        boolean z = aa.h;
        return aa.C0190aa.a();
    }

    @Deprecated
    public static boolean optInStatus() {
        return optInOverallStatus();
    }

    public static boolean optInVideoRecordingStatus() {
        boolean z = aa.h;
        return aa.C0190aa.c();
    }

    public static void optIntoVideoRecording() {
        boolean z = aa.h;
        aa.C0190aa.b();
        if (Util.getCurrentApplicationContext() != null) {
            new eg(Util.getCurrentApplicationContext()).a("opt_out_of_video_recording", false);
        }
    }

    @Deprecated
    public static void optOut() {
        optOutOverall();
    }

    public static void optOutOfVideoRecording() {
        boolean z = aa.h;
        aa.C0190aa.b();
        if (Util.getCurrentApplicationContext() != null) {
            new eg(Util.getCurrentApplicationContext()).a("opt_out_of_video_recording", true);
        }
        if (bh.f97a) {
            bl.g = true;
        }
    }

    public static void optOutOverall() {
        aa.k = false;
        aa.C0190aa.b();
        if (Util.getCurrentApplicationContext() != null) {
            new eg(Util.getCurrentApplicationContext()).a("opt_out", true);
        } else {
            ga.q = 1;
        }
        cancelCurrentSession();
        aa.k = false;
    }

    @Deprecated
    public static boolean optStatus() {
        return optInStatus();
    }

    @Deprecated
    public static void pause() {
        pauseScreenRecording();
    }

    public static void pauseScreenRecording() {
        try {
            occludeSensitiveScreen(true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static int pendingSessionCount() {
        return pendingUploads();
    }

    public static int pendingUploads() {
        boolean z = aa.h;
        aa.C0190aa.b();
        int i = ga.f167a;
        return Util.getPendingSessionCount(true);
    }

    public static void pluginType(String str, String str2) {
        aa.i = str;
        aa.j = str2;
    }

    public static void removeAllScreenNamesToIgnore() {
        boolean z = aa.h;
        ga.D = new TreeSet();
    }

    public static void removeOcclusion(UXCamOcclusion uXCamOcclusion) {
        boolean z = aa.h;
        ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().removeOcclusionFromSDK(uXCamOcclusion);
    }

    public static void removeScreenNameToIgnore(String str) {
        boolean z = aa.h;
        ga.D.remove(str);
    }

    public static void removeScreenNamesToIgnore(List<String> list) {
        boolean z = aa.h;
        TreeSet treeSet = ga.D;
        Intrinsics.checkNotNull(list);
        treeSet.removeAll(CollectionsKt.toSet(list));
    }

    public static void removeVerificationListener(OnVerificationListener onVerificationListener) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.f().a(onVerificationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportBugEvent(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        aa aaVarJ = biVar.j();
        try {
            aaVarJ.getClass();
            aaVarJ.g.a(str, new JSONObject(), (Map<?, ?>) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportExceptionEvent(Throwable th) throws JSONException {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        aa aaVarJ = biVar.j();
        try {
            aaVarJ.getClass();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(SentryEvent.JsonKeys.EXCEPTION, hv.a(th));
                aaVarJ.g.a("", jSONObject, (Map<?, ?>) null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public static void resume() {
        resumeScreenRecording();
    }

    public static void resumeScreenRecording() {
        try {
            occludeSensitiveScreen(false, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void resumeShortBreakForAnotherApp() {
        boolean z = aa.h;
        ff.j = 0L;
    }

    public static List<String> screenNamesBeingIgnored() {
        boolean z = aa.h;
        return new ArrayList(ga.D);
    }

    @Deprecated
    public static void setAutomaticScreenNameTagging(boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.j().d.a(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setImprovedScreenCaptureEnabled(boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.j().d.d(z);
    }

    @Deprecated
    public static void setMultiSessionRecord(boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.j().d.b(z);
    }

    public static void setPushNotificationToken(String str) {
        boolean z = aa.h;
        aa.C0190aa.b();
        Context currentApplicationContext = Util.getCurrentApplicationContext();
        if (currentApplicationContext != null) {
            eg egVar = new eg(currentApplicationContext);
            if (str == null) {
                str = "";
            }
            egVar.a("push_notification_token", str);
        }
    }

    @Deprecated
    public static void setSessionProperty(String str, float f) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().b(str, Float.valueOf(f));
    }

    public static void setUserIdentity(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.j().a(str);
    }

    public static void setUserProperty(String str, float f) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().a(str, Float.valueOf(f));
    }

    public static void startApplicationWithKeyForCordova(Activity activity, String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(activity, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startNewSession() {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.k().a();
    }

    public static void startWithConfiguration(Context context, UXConfig uXConfig) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.k().a(context, uXConfig);
    }

    public static void startWithConfigurationCrossPlatform(Activity activity, UXConfig uXConfig) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(activity, uXConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startWithKeyForAppcelerator(Activity activity, String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(activity, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void stopApplicationAndUploadData() {
        try {
            stopSessionAndUploadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSessionAndUploadData() {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        if (biVar.z == null) {
            biVar.z = new hn(biVar.f(), biVar.e());
        }
        hn hnVar = biVar.z;
        Intrinsics.checkNotNull(hnVar);
        try {
            hnVar.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tagScreenName(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.j().b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void tagUsersName(String str) {
        identify(str);
    }

    public static void unOccludeSensitiveView(View view) {
        try {
            aa.a(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String urlForCurrentSession() {
        try {
            boolean z = aa.h;
            String url_session_id = ga.l;
            Intrinsics.checkNotNullExpressionValue(url_session_id, "url_session_id");
            return url_session_id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String urlForCurrentUser() {
        try {
            boolean z = aa.h;
            String url_device_id = ga.k;
            Intrinsics.checkNotNullExpressionValue(url_device_id, "url_device_id");
            return url_device_id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static void addTagWithProperties(String str, Map<String, Object> map) {
        logEvent(str, map);
    }

    public static void attachUnsupportedView(MapView mapView) {
        boolean z = aa.h;
        aa.C0190aa.a(mapView);
    }

    @Deprecated
    public static void addTagWithProperties(String str, JSONObject jSONObject) {
        logEvent(str, jSONObject);
    }

    public static void allowShortBreakForAnotherApp(boolean z) {
        boolean z2 = aa.h;
        if (z) {
            ff.j = 180000;
        } else {
            ff.j = 0L;
        }
    }

    public static void attachUnsupportedView(SupportMapFragment supportMapFragment) {
        boolean z = aa.h;
        aa.C0190aa.a(supportMapFragment);
    }

    public static void logEvent(String str, Map<String, Object> map) {
        try {
            boolean z = aa.h;
            aa.C0190aa.a(str, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void allowShortBreakForAnotherApp(int i) {
        boolean z = aa.h;
        ff.j = i;
    }

    public static void logEvent(String str, JSONObject jSONObject) {
        try {
            boolean z = aa.h;
            aa.C0190aa.a(str, Util.jsonObjectToMap(jSONObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void occludeSensitiveScreen(boolean z, boolean z2) {
        try {
            UXCamOverlay uXCamOverlayBuild = new UXCamOverlay.Builder().withoutGesture(z2).build();
            if (z) {
                applyOcclusion(uXCamOverlayBuild);
            } else {
                removeOcclusion(uXCamOverlayBuild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setSessionProperty(String str, int i) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().b(str, Integer.valueOf(i));
    }

    public static void setUserProperty(String str, int i) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().a(str, Integer.valueOf(i));
    }

    public static void startWithConfiguration(UXConfig uXConfig) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(uXConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startApplicationWithKeyForCordova(Activity activity, String str, String str2) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        hk hkVarK = biVar.k();
        try {
            ga.c = str2;
            hkVarK.a(activity, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str, Activity activity, boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            ((hl) biVar.k()).a(str, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKeyForAppcelerator(Activity activity, String str, String str2) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        hk hkVarK = biVar.k();
        try {
            ga.c = str2;
            hkVarK.a(activity, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportBugEvent(String str, Map<String, Object> map) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        aa aaVarJ = biVar.j();
        try {
            aaVarJ.getClass();
            aaVarJ.g.a(str, new JSONObject(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportExceptionEvent(Throwable th, Map<String, Object> map) throws JSONException {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        aa aaVarJ = biVar.j();
        try {
            aaVarJ.getClass();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(SentryEvent.JsonKeys.EXCEPTION, hv.a(th));
                aaVarJ.g.a("", jSONObject, map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public static void setSessionProperty(String str, String str2) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().b(str, str2);
    }

    public static void setUserProperty(String str, String str2) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().a(str, str2);
    }

    public static void startWithConfiguration(UXConfig uXConfig, Activity activity) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(uXConfig, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str, OnVerificationListener onVerificationListener) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        hk hkVarK = biVar.k();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        fu fuVarF = biVar2.f();
        try {
            hkVarK.a(str);
            fuVarF.b(onVerificationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportBugEvent(String str, JSONObject jSONObject) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        aa aaVarJ = biVar.j();
        try {
            Map<String, Object> mapJsonObjectToMap = Util.jsonObjectToMap(jSONObject);
            aaVarJ.getClass();
            aaVarJ.g.a(str, new JSONObject(), mapJsonObjectToMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSessionProperty(String str, boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().b(str, Boolean.valueOf(z));
    }

    public static void setUserProperty(String str, boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f().a(str, Boolean.valueOf(z));
    }

    public static void startWithConfiguration(UXConfig uXConfig, Activity activity, boolean z) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(uXConfig, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str, String str2) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        try {
            biVar.k().a(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str, String str2, OnVerificationListener onVerificationListener) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        hk hkVarK = biVar.k();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        fu fuVarF = biVar2.f();
        try {
            hkVarK.a(str, str2);
            fuVarF.b(onVerificationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
