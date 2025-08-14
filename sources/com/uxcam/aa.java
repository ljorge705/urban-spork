package com.uxcam;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.uxcam.aa;
import com.uxcam.internals.bh;
import com.uxcam.internals.bi;
import com.uxcam.internals.ca;
import com.uxcam.internals.cb;
import com.uxcam.internals.fj;
import com.uxcam.internals.fu;
import com.uxcam.internals.fy;
import com.uxcam.internals.ga;
import com.uxcam.internals.gk;
import com.uxcam.internals.gv;
import com.uxcam.internals.hm;
import com.uxcam.internals.hr;
import com.uxcam.internals.ht;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.service.HttpPostService;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class aa {
    public static boolean h;
    public static String i;
    public static String j;
    public static boolean k;
    public static boolean l;

    /* renamed from: a, reason: collision with root package name */
    public final fu f70a;
    public final Context b;
    public final hm c;
    public final hr d;
    public final fy e;
    public final gv f;
    public final ca g;

    public aa(fu sessionRepository, Application application, hm uxCamStopper, hr uxConfigRepository, fy setUpTimelineHelper, gv timelineRepository, ca eventsValidatorAndSaver) {
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(uxCamStopper, "uxCamStopper");
        Intrinsics.checkNotNullParameter(uxConfigRepository, "uxConfigRepository");
        Intrinsics.checkNotNullParameter(setUpTimelineHelper, "setUpTimelineHelper");
        Intrinsics.checkNotNullParameter(timelineRepository, "timelineRepository");
        Intrinsics.checkNotNullParameter(eventsValidatorAndSaver, "eventsValidatorAndSaver");
        this.f70a = sessionRepository;
        this.b = application;
        this.c = uxCamStopper;
        this.d = uxConfigRepository;
        this.e = setUpTimelineHelper;
        this.f = timelineRepository;
        this.g = eventsValidatorAndSaver;
    }

    @JvmStatic
    public static final void a(View occludeView) {
        Intrinsics.checkNotNullParameter(occludeView, "occludeView");
        ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder();
        if (!screenshotStateHolder.getViewsToHide().isEmpty()) {
            for (UXCamOccludeView uXCamOccludeView : screenshotStateHolder.getViewsToHide()) {
                if (Intrinsics.areEqual(uXCamOccludeView.getView().get(), occludeView)) {
                    ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().removeViewToHide(uXCamOccludeView);
                }
            }
        }
    }

    public final void b(String str) {
        if (!bh.f97a) {
            this.f.a(str);
            return;
        }
        try {
            this.e.a(Util.getCurrentApplicationContext(), str);
        } catch (Exception e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("UXCamHelper::tagScreenName()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    /* renamed from: com.uxcam.aa$aa, reason: collision with other inner class name */
    public static final class C0190aa {
        public static void a(String str, Map map) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            if (biVar.v == null) {
                biVar.v = new cb(biVar.i());
            }
            cb cbVar = biVar.v;
            Intrinsics.checkNotNull(cbVar);
            cbVar.a(str, map);
            if (str != null) {
                int iHashCode = str.hashCode();
                if (iHashCode == -643057588) {
                    if (str.equals("UXCam_IgnoreDataFilters")) {
                        ga.r = true;
                        return;
                    }
                    return;
                }
                if (iHashCode == -67722491) {
                    if (str.equals("UXCam_IgnoreVideoFilters")) {
                        ga.s = true;
                    }
                } else if (iHashCode == 1306063903 && str.equals("UXCam_ForceSessionUpload")) {
                    aa.h = true;
                    if (ga.b == null || bh.f97a) {
                        return;
                    }
                    Context currentApplicationContext = Util.getCurrentApplicationContext();
                    SharedPreferences sharedPreferences = currentApplicationContext != null ? currentApplicationContext.getSharedPreferences("UXCamPreferences", 0) : null;
                    String str2 = "override_mobile_data_data_only_setting_" + ga.b;
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putBoolean(str2, true).apply();
                    }
                }
            }
        }

        @JvmStatic
        public static void b() {
            if (Util.getCurrentApplicationContext() != null) {
                return;
            }
            try {
                Util.setCurrentApplicationContext(Util.getApplicationContext());
            } catch (ClassNotFoundException e) {
                boolean z = aa.h;
                gk.a("aa").getClass();
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                boolean z2 = aa.h;
                gk.a("aa").getClass();
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                boolean z3 = aa.h;
                gk.a("aa").getClass();
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                boolean z4 = aa.h;
                gk.a("aa").getClass();
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                boolean z5 = aa.h;
                gk.a("aa").getClass();
                e5.printStackTrace();
            }
        }

        @JvmStatic
        public static boolean c() {
            if (Util.getCurrentApplicationContext() == null) {
                b();
            }
            Context currentApplicationContext = Util.getCurrentApplicationContext();
            boolean z = false;
            SharedPreferences sharedPreferences = currentApplicationContext != null ? currentApplicationContext.getSharedPreferences("UXCamPreferences", 0) : null;
            if (sharedPreferences != null && sharedPreferences.getBoolean("opt_out_of_video_recording", false)) {
                z = true;
            }
            return !z;
        }

        public static boolean a() {
            if (Util.getCurrentApplicationContext() == null) {
                b();
            }
            Context currentApplicationContext = Util.getCurrentApplicationContext();
            boolean z = false;
            SharedPreferences sharedPreferences = currentApplicationContext != null ? currentApplicationContext.getSharedPreferences("UXCamPreferences", 0) : null;
            if (sharedPreferences != null && sharedPreferences.getBoolean("opt_out", false)) {
                z = true;
            }
            return !z;
        }

        @JvmStatic
        public static void a(final SupportMapFragment supportMapFragment) {
            Intrinsics.checkNotNullParameter(supportMapFragment, "supportMapFragment");
            try {
                supportMapFragment.getMapAsync(new OnMapReadyCallback() { // from class: com.uxcam.aa$aa$$ExternalSyntheticLambda2
                    @Override // com.google.android.gms.maps.OnMapReadyCallback
                    public final void onMapReady(GoogleMap googleMap) {
                        aa.C0190aa.a(supportMapFragment, googleMap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final void a(SupportMapFragment supportMapFragment, GoogleMap googleMap) {
            Intrinsics.checkNotNullParameter(supportMapFragment, "$supportMapFragment");
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder();
            screenshotStateHolder.setGoogleMap(googleMap);
            screenshotStateHolder.setGoogleMapView(new WeakReference<>(supportMapFragment.getView()));
        }

        @JvmStatic
        public static void a(final MapFragment mapFragment) {
            Intrinsics.checkNotNullParameter(mapFragment, "mapFragment");
            try {
                mapFragment.getMapAsync(new OnMapReadyCallback() { // from class: com.uxcam.aa$aa$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.maps.OnMapReadyCallback
                    public final void onMapReady(GoogleMap googleMap) {
                        aa.C0190aa.a(mapFragment, googleMap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final void a(MapFragment mapFragment, GoogleMap googleMap) {
            Intrinsics.checkNotNullParameter(mapFragment, "$mapFragment");
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder();
            screenshotStateHolder.setGoogleMap(googleMap);
            screenshotStateHolder.setGoogleMapView(new WeakReference<>(mapFragment.getView()));
        }

        @JvmStatic
        public static void a(final MapView mapView) {
            Intrinsics.checkNotNullParameter(mapView, "mapView");
            try {
                mapView.getMapAsync(new OnMapReadyCallback() { // from class: com.uxcam.aa$aa$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.maps.OnMapReadyCallback
                    public final void onMapReady(GoogleMap googleMap) {
                        aa.C0190aa.a(mapView, googleMap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final void a(MapView mapView, GoogleMap googleMap) {
            Intrinsics.checkNotNullParameter(mapView, "$mapView");
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder();
            screenshotStateHolder.setGoogleMap(googleMap);
            screenshotStateHolder.setGoogleMapView(new WeakReference<>(mapView));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.aa.a(java.lang.String):void");
    }

    public final void a(boolean z) {
        if (l) {
            HashMap map = new HashMap();
            map.put("with", "disabled = " + z);
            map.put("reason", "invoked .disableCrashHandling() method too late.");
            map.put(OnfidoLauncher.KEY_RESULT, "crashes are logged");
            ht.a("[ TOGGLE ERROR ] Crash Handler", (Map<String, String>) map);
            return;
        }
        this.d.c(z);
    }

    @JvmStatic
    public static final void a() {
        boolean zIsEmpty = HttpPostService.c.isEmpty();
        int i2 = ga.f167a;
        boolean z = FilePath.isUxcamRootFolderEmpty(Boolean.TRUE) && HttpPostService.b;
        if (!zIsEmpty && !z) {
            gk.a("aa").getClass();
            return;
        }
        if (bh.f97a) {
            return;
        }
        gk.a("aa").getClass();
        gk.a("UXCamHelper").getClass();
        Util.getCurrentApplicationContext().stopService(new Intent(Util.getCurrentApplicationContext(), (Class<?>) HttpPostService.class));
        if (z) {
            gk.a("UXCam").a("UXCam 3.6.13[580] : session data sent successfully", new Object[0]);
        } else {
            gk.a("UXCam").getClass();
        }
    }
}
