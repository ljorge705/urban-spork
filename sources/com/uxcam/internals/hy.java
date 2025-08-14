package com.uxcam.internals;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import com.uxcam.internals.el;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.OnScreenActionResult;
import com.uxcam.screenaction.ScreenActionProvider;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.repository.ComposeOcclusionRepository;
import com.uxcam.screenshot.repository.OcclusionRepository;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hy extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    public static final String e = "hy";

    /* renamed from: a, reason: collision with root package name */
    public final el f202a;
    public ArrayList<GestureData> b = new ArrayList<>();
    public final ScreenActionProvider c;
    public final eq d;

    public hy(ScreenActionProvider screenActionProvider, eq eqVar, el elVar) {
        this.c = screenActionProvider;
        this.d = eqVar;
        int[] iArr = ga.w;
        int i = iArr[0];
        int i2 = iArr[1];
        Util.mmToPx(iArr[2], Util.getCurrentApplicationContext());
        this.f202a = elVar;
    }

    public final void a(GestureData gestureData) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        if (((gw) biVar.i()).f > 0.0f || !ga.f || gestureData.getGesture() == 10) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            gestureData.setActivityName(((fa) biVar2.d()).d.e());
            if (gestureData.getGesture() != 10) {
                int rawX = gestureData.getRawX();
                int rawY = gestureData.getRawY();
                for (UXCamOccludeView uXCamOccludeView : ScreenshotModule.getInstance().getScreenshotStateHolder().getViewsToHide()) {
                    if (uXCamOccludeView.getView().get() != null && uXCamOccludeView.isStopTrackingGestures()) {
                        View view = uXCamOccludeView.getView().get();
                        int[] iArr = new int[2];
                        view.getLocationOnScreen(iArr);
                        boolean z = false;
                        int i = iArr[0];
                        int i2 = iArr[1];
                        int width = view.getWidth() + i;
                        int height = view.getHeight() + i2;
                        if (rawX >= i && rawX <= width && rawY >= i2 && rawY <= height) {
                            z = true;
                        }
                        if (Objects.equals(gestureData.getActivityName(), uXCamOccludeView.getActivityName()) && z) {
                            return;
                        }
                    }
                }
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar3 = bi.D;
            Intrinsics.checkNotNull(biVar3);
            ArrayList<gp> arrayList = ((gw) biVar3.i()).f186a;
            gp gpVar = arrayList.get(arrayList.size() - 1);
            float time = gestureData.getTime() - gpVar.b;
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            if (time >= 1.0f || gestureData.getGesture() == 10) {
                a(gestureData, gpVar);
                return;
            }
            int iIndexOf = arrayList.indexOf(gpVar) - 1;
            if (iIndexOf >= 0) {
                gestureData.setTime(gestureData.getTime() - time);
                gestureData.setX(screenshotStateHolder.getQ() + gestureData.getX());
                gestureData.setY(screenshotStateHolder.getR() + gestureData.getY());
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar4 = bi.D;
                Intrinsics.checkNotNull(biVar4);
                a(gestureData, ((gw) biVar4.i()).f186a.get(iIndexOf));
            }
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent motionEvent) {
        a(1, motionEvent.getRawX(), motionEvent.getRawY());
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        if (motionEvent != null && !this.b.isEmpty()) {
            GestureData gestureDataCopy = this.b.get(0).copy();
            gestureDataCopy.setGesture(11);
            gestureDataCopy.setTrail(this.b);
            gestureDataCopy.processTrailToMatchWithIos();
            a(gestureDataCopy);
            this.b = new ArrayList<>();
        }
        if (ga.B) {
            new ScreenActionTracker(com.uxcam.aa.i, ScreenActionModule.getInstance().getScreenActionViewsRepository()).loopLayout();
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null) {
            return false;
        }
        int iA = a(motionEvent.getRawX(), motionEvent.getRawY(), motionEvent2.getRawX(), motionEvent2.getRawY());
        if (iA == 2 && Math.abs(f2) > 1.0f) {
            a(3, motionEvent2.getRawX(), motionEvent2.getRawY());
            return false;
        }
        if (iA == 1 && Math.abs(f2) > 1.0f) {
            a(2, motionEvent2.getRawX(), motionEvent2.getRawY());
            return false;
        }
        if (iA == 4 && Math.abs(f) > 1.0f) {
            a(5, motionEvent2.getRawX(), motionEvent2.getRawY());
            return false;
        }
        if (iA != 3 || Math.abs(f) <= 1.0f) {
            return false;
        }
        a(4, motionEvent2.getRawX(), motionEvent2.getRawY());
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        if (motionEvent != null) {
            a(6, motionEvent.getRawX(), motionEvent.getRawY());
        }
    }

    @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null) {
            return false;
        }
        if (this.b.isEmpty()) {
            a(12, motionEvent.getRawX(), motionEvent.getRawY());
        }
        a(12, motionEvent2.getRawX(), motionEvent2.getRawY());
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (motionEvent != null) {
            a(0, motionEvent.getRawX(), motionEvent.getRawY());
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public final void a(int i, float f, float f2) {
        int i2;
        try {
            OcclusionRepository occlusionRepository = ScreenshotModule.getInstance().getOcclusionRepository();
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            if (occlusionRepository.shouldOcclude(((fa) biVar.d()).d.e())) {
                OcclusionRepository occlusionRepository2 = ScreenshotModule.getInstance().getOcclusionRepository();
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar2 = bi.D;
                Intrinsics.checkNotNull(biVar2);
                UXCamOcclusion occlusion = occlusionRepository2.getOcclusion(((fa) biVar2.d()).d.e());
                if (occlusion != null && occlusion.isWithoutGesture()) {
                    return;
                }
            }
            int i3 = (int) f;
            int i4 = (int) f2;
            GestureData gestureData = new GestureData(i, Util.getCurrentUxcamTime(fp.f157n), i3, i4, i3, i4);
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            gestureData.decreaseOffset(screenshotStateHolder.getQ(), screenshotStateHolder.getR());
            Activity activity = (Activity) Util.getCurrentContext();
            if (activity.getWindow() != null) {
                activity.getWindow().getDecorView().getLocationOnScreen(new int[2]);
                gestureData.setY(gestureData.getY());
                gestureData.setX(gestureData.getX());
            }
            int rotation = ((WindowManager) Util.getCurrentApplicationContext().getSystemService("window")).getDefaultDisplay().getRotation();
            int deviceDefaultOrientation = Util.getDeviceDefaultOrientation(Util.getCurrentApplicationContext());
            boolean z = false;
            if (deviceDefaultOrientation == 1) {
                if (rotation != 0) {
                    if (rotation != 1) {
                        if (rotation != 2) {
                            if (rotation != 3) {
                                i2 = -1;
                            }
                            i2 = 2;
                        }
                        i2 = 3;
                    }
                    i2 = 0;
                }
                i2 = 1;
            } else {
                if (deviceDefaultOrientation == 2) {
                    if (rotation != 0) {
                        if (rotation != 1) {
                            if (rotation != 2) {
                                if (rotation != 3) {
                                }
                                i2 = 1;
                            }
                            i2 = 2;
                        }
                        i2 = 3;
                    }
                    i2 = 0;
                }
                i2 = -1;
            }
            gestureData.setOrientation(i2);
            if (gestureData.getGesture() == 12) {
                this.b.add(gestureData);
                return;
            }
            if (!this.b.isEmpty() && (gestureData.getGesture() == 2 || gestureData.getGesture() == 3 || gestureData.getGesture() == 4 || gestureData.getGesture() == 5)) {
                GestureData gestureDataCopy = this.b.get(0).copy();
                gestureDataCopy.setTrail(this.b);
                gestureDataCopy.processTrailToMatchWithIos();
                gestureDataCopy.setGesture(11);
                a(gestureDataCopy);
                ArrayList<GestureData> arrayList = this.b;
                GestureData gestureDataCopy2 = arrayList.get(arrayList.size() - 1).copy();
                gestureDataCopy2.setGesture(gestureData.getGesture());
                gestureDataCopy2.setRawX(gestureDataCopy.getX());
                gestureDataCopy2.setRawY(gestureDataCopy.getY());
                a(gestureDataCopy2);
                this.b = new ArrayList<>();
                z = true;
            } else if (!this.b.isEmpty()) {
                GestureData gestureDataCopy3 = this.b.get(0).copy();
                ArrayList<GestureData> arrayList2 = this.b;
                GestureData gestureDataCopy4 = arrayList2.get(arrayList2.size() - 1).copy();
                try {
                    int iA = a(gestureDataCopy3.getX(), gestureDataCopy3.getY(), gestureDataCopy4.getX(), gestureDataCopy4.getY());
                    if (iA == 2) {
                        gestureDataCopy3.setGesture(3);
                    } else if (iA == 1) {
                        gestureDataCopy3.setGesture(2);
                    } else if (iA == 4) {
                        gestureDataCopy3.setGesture(5);
                    } else if (iA == 3) {
                        gestureDataCopy3.setGesture(4);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                GestureData gestureDataCopy5 = this.b.get(0).copy();
                gestureDataCopy5.setGesture(11);
                gestureDataCopy5.setTrail(this.b);
                gestureDataCopy5.processTrailToMatchWithIos();
                a(gestureDataCopy5);
                this.b = new ArrayList<>();
            }
            gestureData.getGesture();
            gk.c.getClass();
            if (z) {
                return;
            }
            a(gestureData);
        } catch (Exception unused) {
            gk.a(e).getClass();
        }
    }

    public final void a(final GestureData gestureData, final gp gpVar) {
        el.aa aaVar;
        boolean zShouldOcclude = ScreenshotModule.getInstance().getOcclusionRepository().shouldOcclude(gpVar.f180a);
        UXCamOcclusion occlusion = ScreenshotModule.getInstance().getOcclusionRepository().getOcclusion(gpVar.f180a);
        if (!zShouldOcclude || occlusion == null || !occlusion.isWithoutGesture()) {
            gpVar.c.add(gestureData);
        }
        if (gestureData.getGesture() == 0 || gestureData.getGesture() == 1) {
            el elVar = this.f202a;
            elVar.getClass();
            Intrinsics.checkNotNullParameter(gestureData, "gestureData");
            if (elVar.e.isEmpty()) {
                elVar.a(gestureData);
            } else {
                ArrayList<GestureData> arrayList = elVar.e;
                GestureData gestureData2 = arrayList.get(arrayList.size() - 1);
                Intrinsics.checkNotNullExpressionValue(gestureData2, "rageGestures[rageGestures.size - 1]");
                GestureData gestureData3 = gestureData2;
                int x = gestureData.getX() - gestureData3.getX();
                int y = gestureData.getY() - gestureData3.getY();
                float fSqrt = (float) Math.sqrt((y * y) + (x * x));
                float time = gestureData.getTime() - gestureData3.getTime();
                if ((gestureData3.getGesture() == 0 && fSqrt <= elVar.c && time <= elVar.b) || (gestureData3.getGesture() == 1 && fSqrt <= elVar.c * 2 && time <= elVar.b * 2)) {
                    elVar.a(gestureData);
                } else {
                    if (elVar.f >= elVar.f137a && (aaVar = elVar.d) != null) {
                        Intrinsics.checkNotNull(aaVar);
                        aaVar.a(elVar.e);
                    }
                    elVar.e = new ArrayList<>();
                    elVar.f = 0;
                    elVar.a(gestureData);
                }
            }
        }
        a(ScreenActionTracker.getViewArrayList(), gestureData, new OnScreenActionResult() { // from class: com.uxcam.internals.hy$$ExternalSyntheticLambda0
            @Override // com.uxcam.screenaction.OnScreenActionResult
            public final void result(ScreenAction screenAction) {
                this.f$0.a(gestureData, gpVar, screenAction);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(GestureData gestureData, gp gpVar, ScreenAction screenAction) {
        ArrayList<GestureData> arrayList;
        if (screenAction != null) {
            gk.aa aaVarA = gk.a("compose");
            screenAction.getName();
            screenAction.getRect();
            aaVarA.getClass();
        }
        boolean z = ga.B;
        if (gestureData.getGesture() == 10 || !ga.B) {
            gestureData.setResponsive(Boolean.TRUE);
            return;
        }
        if (screenAction == null || !z) {
            return;
        }
        gestureData.setScreenAction(screenAction);
        if (gestureData.getGesture() != 0 && gestureData.getGesture() != 1 && gestureData.getGesture() != 6) {
            if (screenAction.uxCamView != null && (gestureData.isSwipe() || gestureData.getGesture() == 11)) {
                gestureData.setScreenAction(screenAction);
                UXCamView uXCamView = screenAction.uxCamView;
                uXCamView.isScrollableUp();
                uXCamView.isScrollableDown();
                uXCamView.isScrollableLeft();
                uXCamView.isScrollableRight();
                if (gestureData.getGesture() == 2 && uXCamView.isScrollableUp()) {
                    gestureData.setResponsive(Boolean.TRUE);
                } else if (gestureData.getGesture() == 3 && uXCamView.isScrollableDown()) {
                    gestureData.setResponsive(Boolean.TRUE);
                } else if (gestureData.getGesture() == 4 && uXCamView.isScrollableRight()) {
                    gestureData.setResponsive(Boolean.TRUE);
                } else if ((gestureData.getGesture() == 5 && uXCamView.isScrollableLeft()) || gestureData.getGesture() == 11) {
                    gestureData.setResponsive(Boolean.TRUE);
                }
            }
        } else {
            UXCamView uXCamView2 = screenAction.uxCamView;
            if (uXCamView2 != null && (uXCamView2.isClickable() || screenAction.uxCamView.hasOnClickListeners())) {
                gestureData.setResponsive(Boolean.valueOf(screenAction.uxCamView.isEnabled()));
            }
        }
        if (gestureData.isSwipe() && (arrayList = gpVar.c) != null && arrayList.size() > 1) {
            ArrayList<GestureData> arrayList2 = gpVar.c;
            GestureData gestureData2 = arrayList2.get(arrayList2.size() - 2);
            if (gestureData2.getGesture() == 11) {
                gestureData2.setScreenAction(null);
                gestureData2.setResponsive(gestureData.isResponsive());
            }
        }
        gestureData.toString();
        gk.aa aaVarA2 = gk.a("screenAction");
        KeyConstant.getGestureName(gestureData.getGesture());
        gestureData.isResponsive();
        gestureData.getRawX();
        gestureData.getRawY();
        gestureData.getTime();
        gestureData.getScreenAction().getName();
        gestureData.getScreenAction().getIdentifierString();
        gestureData.getOrientation();
        aaVarA2.getClass();
    }

    public final void a(ArrayList<UXCamView> arrayList, GestureData gestureData, final OnScreenActionResult onScreenActionResult) {
        if (arrayList.isEmpty()) {
            return;
        }
        UXCamView uXCamView = arrayList.get(0);
        ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
        ComposeOcclusionRepository composeOcclusionRepository = ScreenshotModule.getInstance().getComposeOcclusionRepository();
        Activity activity = (Activity) Util.getCurrentContext();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        this.c.getScreenAction(Util.getCurrentUxcamTime(fp.f157n), uXCamView, screenshotStateHolder.getViewsToHide(), gestureData, this.d.a(activity, ((fv) biVar.f()).k, ga.p), composeOcclusionRepository.getComposablesToHide(), new Function1() { // from class: com.uxcam.internals.hy$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return hy.a(onScreenActionResult, (ScreenAction) obj);
            }
        });
    }

    public static /* synthetic */ Unit a(OnScreenActionResult onScreenActionResult, ScreenAction screenAction) {
        onScreenActionResult.result(screenAction);
        return null;
    }

    public final void a() {
        el.aa aaVar;
        el elVar = this.f202a;
        if (elVar.f >= elVar.f137a && (aaVar = elVar.d) != null) {
            Intrinsics.checkNotNull(aaVar);
            aaVar.a(elVar.e);
        }
        elVar.e = new ArrayList<>();
        elVar.f = 0;
    }

    public static int a(float f, float f2, float f3, float f4) {
        double dAtan2 = ((((Math.atan2(f2 - f4, f3 - f) + 3.141592653589793d) * 180.0d) / 3.141592653589793d) + 180.0d) % 360.0d;
        double d = 45.0f;
        if (dAtan2 >= d && dAtan2 < 135.0f) {
            return 1;
        }
        if (dAtan2 < 0.0f || dAtan2 >= d) {
            double d2 = 315.0f;
            if (dAtan2 < d2 || dAtan2 >= 360.0f) {
                return (dAtan2 < ((double) 225.0f) || dAtan2 >= d2) ? 3 : 2;
            }
        }
        return 4;
    }
}
