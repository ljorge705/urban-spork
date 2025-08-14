package com.uxcam.xamarin;

import android.view.View;
import com.uxcam.OnVerificationListener;
import com.uxcam.UXCam;
import com.uxcam.aa;
import com.uxcam.datamodel.UXConfig;
import com.uxcam.internals.bi;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class XamarinAPI {
    public static void addScreenNameToIgnore(String str) {
        UXCam.addScreenNameToIgnore(str);
    }

    public static void addScreenNamesToIgnore(List<String> list) {
        UXCam.addScreenNamesToIgnore(list);
    }

    @Deprecated
    public static void addTagWithProperties(String str) {
        UXCam.addTagWithProperties(str);
    }

    public static void addVerificationListener(OnVerificationListener onVerificationListener) {
        UXCam.addVerificationListener(onVerificationListener);
    }

    @Deprecated
    public static void allowShortBreakForAnotherApp() {
        UXCam.allowShortBreakForAnotherApp();
    }

    public static void applyOcclusion(UXCamOcclusion uXCamOcclusion) {
        boolean z = aa.h;
        ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().applyOcclusionFromSDK(uXCamOcclusion);
    }

    public static void cancelCurrentSession() {
        UXCam.cancelRecording();
    }

    public static void cancelRecording() {
        UXCam.cancelRecording();
    }

    public static void deletePendingUploads() {
        UXCam.deletePendingUploads();
    }

    public static void disableCrashHandling(boolean z) {
        UXCam.disableCrashHandling(z);
    }

    public static boolean getMultiSessionRecord() {
        return UXCam.getMultiSessionRecord();
    }

    @Deprecated
    public static void identify(String str) {
        UXCam.setUserIdentity(str);
    }

    @Deprecated
    public static void ignoreRecording() {
        UXCam.cancelRecording();
    }

    public static boolean isRecording() {
        return UXCam.isRecording();
    }

    public static void logEvent(String str) {
        UXCam.logEvent(str);
    }

    @Deprecated
    public static void markSessionAsFavorite() {
        UXCam.markSessionAsFavorite();
    }

    public static void occludeAllTextFields(boolean z) {
        UXCam.occludeAllTextFields(z);
    }

    @Deprecated
    public static void occludeAllTextView() {
        UXCam.occludeAllTextView();
    }

    public static void occludeSensitiveScreen(boolean z) {
        try {
            UXCamOverlay uXCamOverlayBuild = new UXCamOverlay.Builder().build();
            if (z) {
                UXCam.applyOcclusion(uXCamOverlayBuild);
            } else {
                UXCam.removeOcclusion(uXCamOverlayBuild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void occludeSensitiveView(View view) {
        UXCam.occludeSensitiveView(view);
    }

    public static void occludeSensitiveViewWithoutGesture(View view) {
        UXCam.occludeSensitiveViewWithoutGesture(view);
    }

    public static void optIn() {
        UXCam.optIn();
    }

    public static void optInOverall() {
        UXCam.optInOverall();
    }

    public static boolean optInOverallStatus() {
        return UXCam.optInOverallStatus();
    }

    public static boolean optInStatus() {
        return UXCam.optInStatus();
    }

    public static boolean optInVideoRecordingStatus() {
        return UXCam.optInVideoRecordingStatus();
    }

    public static void optIntoVideoRecording() {
        UXCam.optIntoVideoRecording();
    }

    public static void optOut() {
        UXCam.optOut();
    }

    public static void optOutOfVideoRecording() {
        UXCam.optOutOfVideoRecording();
    }

    public static void optOutOverall() {
        UXCam.optOutOverall();
    }

    @Deprecated
    public static boolean optStatus() {
        return UXCam.optInStatus();
    }

    @Deprecated
    public static void pause() {
        pauseScreenRecording();
    }

    public static void pauseScreenRecording() {
        UXCam.pauseScreenRecording();
    }

    public static int pendingSessionCount() {
        return UXCam.pendingSessionCount();
    }

    public static void pluginType(String str, String str2) {
        UXCam.pluginType(str, str2);
    }

    public static void removeAllScreenNamesToIgnore() {
        UXCam.removeAllScreenNamesToIgnore();
    }

    public static void removeOcclusion(UXCamOcclusion uXCamOcclusion) {
        boolean z = aa.h;
        ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().removeOcclusionFromSDK(uXCamOcclusion);
    }

    public static void removeScreenNameToIgnore(String str) {
        UXCam.removeScreenNameToIgnore(str);
    }

    public static void removeScreenNamesToIgnore(List<String> list) {
        UXCam.removeScreenNamesToIgnore(list);
    }

    public static void removeVerificationListener(OnVerificationListener onVerificationListener) {
        UXCam.removeVerificationListener(onVerificationListener);
    }

    @Deprecated
    public static void resume() {
        resumeScreenRecording();
    }

    public static void resumeScreenRecording() {
        UXCam.resumeScreenRecording();
    }

    @Deprecated
    public static void resumeShortBreakForAnotherApp() {
        UXCam.resumeShortBreakForAnotherApp();
    }

    public static List<String> screenNamesBeingIgnored() {
        return UXCam.screenNamesBeingIgnored();
    }

    public static void setAutomaticScreenNameTagging(boolean z) {
        UXCam.setAutomaticScreenNameTagging(z);
    }

    public static void setMultiSessionRecord(boolean z) {
        UXCam.setMultiSessionRecord(z);
    }

    public static void setSessionProperty(String str, String str2) {
        UXCam.setSessionProperty(str, str2);
    }

    public static void setUserIdentity(String str) {
        UXCam.setUserIdentity(str);
    }

    public static void setUserProperty(String str, String str2) {
        UXCam.setUserProperty(str, str2);
    }

    public static void startNewSession() {
        UXCam.startNewSession();
    }

    public static void startWithConfiguration(UXConfig uXConfig) {
        try {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            biVar.k().a(uXConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void startWithKey(String str, String str2) {
        UXCam.startWithKey(str, str2);
    }

    @Deprecated
    public static void stopApplicationAndUploadData() {
        UXCam.stopApplicationAndUploadData();
    }

    public static void stopSessionAndUploadData() {
        UXCam.stopSessionAndUploadData();
    }

    public static void tagScreenName(String str) {
        UXCam.tagScreenName(str);
    }

    @Deprecated
    public static void tagUsersName(String str) {
        UXCam.setUserIdentity(str);
    }

    public static void unOccludeSensitiveView(View view) {
        UXCam.unOccludeSensitiveView(view);
    }

    public static String urlForCurrentSession() {
        return UXCam.urlForCurrentSession();
    }

    public static String urlForCurrentUser() {
        return UXCam.urlForCurrentUser();
    }

    @Deprecated
    public static void addTagWithProperties(String str, Map<String, Object> map) {
        UXCam.addTagWithProperties(str, map);
    }

    public static void allowShortBreakForAnotherApp(boolean z) {
        UXCam.allowShortBreakForAnotherApp(z);
    }

    public static void logEvent(String str, Map<String, Object> map) {
        UXCam.logEvent(str, map);
    }

    public static void setUserProperty(String str, int i) {
        UXCam.setUserProperty(str, i);
    }

    public static void startWithKey(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.k().a(str);
    }

    @Deprecated
    public static void addTagWithProperties(String str, JSONObject jSONObject) {
        UXCam.addTagWithProperties(str, jSONObject);
    }

    public static void allowShortBreakForAnotherApp(int i) {
        UXCam.allowShortBreakForAnotherApp(i);
    }

    public static void logEvent(String str, JSONObject jSONObject) {
        UXCam.logEvent(str, jSONObject);
    }

    public static void setUserProperty(String str, boolean z) {
        UXCam.setUserProperty(str, z);
    }

    public static void occludeSensitiveScreen(boolean z, boolean z2) {
        try {
            UXCam.applyOcclusion(new UXCamOverlay.Builder().withoutGesture(z2).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
