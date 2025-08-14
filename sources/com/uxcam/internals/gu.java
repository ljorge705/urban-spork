package com.uxcam.internals;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.GestureDetector;
import android.view.ScaleGestureDetector;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.repository.OcclusionRepository;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.json.JSONArray;

/* loaded from: classes6.dex */
public final class gu implements gs, CoroutineScope {

    /* renamed from: a, reason: collision with root package name */
    public final gv f183a;
    public final ex b;
    public final OcclusionRepository c;
    public final ScreenshotStateHolder d;
    public final fj e;
    public final el f;
    public final hy g;
    public final ScreenActionTracker h;
    public final gq i;
    public final ca j;
    public final CoroutineDispatcher k;
    public final /* synthetic */ CoroutineScope l;
    public GestureDetector m;

    /* renamed from: n, reason: collision with root package name */
    public ScaleGestureDetector f184n;

    @DebugMetadata(c = "com.uxcam.timeline.TimelineHandlerImpl$setupTimelineHandler$1", f = "TimelineHandlerImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class aa extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public aa(Context context, Continuation<? super aa> continuation) {
            super(2, continuation);
            this.b = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return gu.this.new aa(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((aa) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            gu.a(gu.this, this.b);
            return Unit.INSTANCE;
        }
    }

    public gu(gv timelineRepository, ex screenTagManager, OcclusionRepository occlusionRepository, ScreenshotStateHolder screenshotStateHolder, fj sdkEventLogger, el rageClickDetector, hy uxGestureListener, ScreenActionTracker screenActionTracker, gr timelineDataJSONParser, ca eventsValidatorAndSaver, CoroutineDispatcher ioDispatcher, CoroutineDispatcher mainDispatcher) {
        Intrinsics.checkNotNullParameter(timelineRepository, "timelineRepository");
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        Intrinsics.checkNotNullParameter(occlusionRepository, "occlusionRepository");
        Intrinsics.checkNotNullParameter(screenshotStateHolder, "screenshotStateHolder");
        Intrinsics.checkNotNullParameter(sdkEventLogger, "sdkEventLogger");
        Intrinsics.checkNotNullParameter(rageClickDetector, "rageClickDetector");
        Intrinsics.checkNotNullParameter(uxGestureListener, "uxGestureListener");
        Intrinsics.checkNotNullParameter(screenActionTracker, "screenActionTracker");
        Intrinsics.checkNotNullParameter(timelineDataJSONParser, "timelineDataJSONParser");
        Intrinsics.checkNotNullParameter(eventsValidatorAndSaver, "eventsValidatorAndSaver");
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        this.f183a = timelineRepository;
        this.b = screenTagManager;
        this.c = occlusionRepository;
        this.d = screenshotStateHolder;
        this.e = sdkEventLogger;
        this.f = rageClickDetector;
        this.g = uxGestureListener;
        this.h = screenActionTracker;
        this.i = timelineDataJSONParser;
        this.j = eventsValidatorAndSaver;
        this.k = mainDispatcher;
        this.l = CoroutineScopeKt.CoroutineScope(ioDispatcher);
    }

    @Override // com.uxcam.internals.gs
    public final void a(Context context, String str, boolean z, Activity activity, long j) {
        b(context, str, z, activity, j);
    }

    public final void b(Context context, String str, boolean z, Activity activity, long j) {
        Resources resources;
        Configuration configuration;
        if (str != null) {
            try {
                if (StringsKt.isBlank(str)) {
                    return;
                }
                this.b.a(str, z);
                if (this.b.g()) {
                    if (!this.f183a.e().isEmpty()) {
                        return;
                    } else {
                        this.b.a("unknown", z);
                    }
                }
                if (this.f183a.e().isEmpty()) {
                    for (bz bzVar : this.f183a.l()) {
                        if (bzVar != null) {
                            String str2 = bzVar.d;
                            Intrinsics.checkNotNullExpressionValue(str2, "event.activity");
                            if (str2.length() == 0) {
                                String strE = this.b.e();
                                Intrinsics.checkNotNull(strE);
                                bzVar.d = strE;
                            }
                        }
                    }
                }
                Integer numValueOf = null;
                BuildersKt__Builders_commonKt.launch$default(this, this.k, null, new aa(context, null), 2, null);
                a(j);
                if (activity != null && (resources = activity.getResources()) != null && (configuration = resources.getConfiguration()) != null) {
                    numValueOf = Integer.valueOf(configuration.orientation);
                }
                int orientation = this.d.getOrientation();
                if (numValueOf == null || orientation != numValueOf.intValue()) {
                    if (!this.d.isWaitingToStop()) {
                        if (numValueOf != null) {
                            this.d.setOrientation(numValueOf.intValue());
                        }
                        this.g.a(10, 0.0f, 0.0f);
                    }
                }
                if (ga.B) {
                    this.h.loopLayout();
                }
            } catch (Exception e) {
                e.printStackTrace();
                fj fjVarB = this.e.b("TimelineHandler::setupTimelineHandler()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a(2);
            }
        }
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.l.getCoroutineContext();
    }

    public static final void a(gu guVar, Context context) {
        el elVar = guVar.f;
        if (elVar.d == null) {
            elVar.d = new gt(guVar);
        }
        try {
            GestureDetector gestureDetector = new GestureDetector(context, guVar.g);
            guVar.m = gestureDetector;
            Intrinsics.checkNotNull(gestureDetector);
            gestureDetector.setOnDoubleTapListener(guVar.g);
            guVar.f184n = context != null ? new ScaleGestureDetector(context, guVar.g) : null;
        } catch (Exception unused) {
            gk.a("TimelineHandler").getClass();
        }
    }

    public final void a(long j) {
        gp gpVar = new gp();
        ArrayList arrayListE = this.f183a.e();
        if (!arrayListE.isEmpty()) {
            ex exVar = this.b;
            gp gpVar2 = (gp) arrayListE.get(arrayListE.size() - 1);
            if (exVar.a(gpVar2 != null ? gpVar2.f180a : null)) {
                return;
            }
        }
        String strE = this.b.e();
        Intrinsics.checkNotNull(strE);
        gpVar.f180a = strE;
        OcclusionRepository occlusionRepository = this.c;
        String strE2 = this.b.e();
        Intrinsics.checkNotNull(strE2);
        UXCamOcclusion occlusion = occlusionRepository.getOcclusion(strE2);
        if (occlusion != null) {
            OcclusionRepository occlusionRepository2 = this.c;
            String strE3 = this.b.e();
            Intrinsics.checkNotNull(strE3);
            gpVar.g = occlusionRepository2.shouldOcclude(strE3) && occlusion.isWithoutGesture();
        }
        gpVar.f = true;
        float currentUxcamTime = Util.getCurrentUxcamTime(j);
        if (arrayListE.isEmpty()) {
            currentUxcamTime = 0.0f;
        }
        gpVar.b = currentUxcamTime;
        try {
            if (!arrayListE.isEmpty()) {
                gp gpVar3 = (gp) arrayListE.get(arrayListE.size() - 1);
                float f = currentUxcamTime - (gpVar3 != null ? gpVar3.b : 0.0f);
                if (gpVar3 != null) {
                    gpVar3.e = f;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f183a.a(gpVar);
    }

    public final JSONArray a() {
        if (this.f183a.e().isEmpty()) {
            gp gpVar = new gp();
            gpVar.f180a = "unknown";
            gpVar.b = 0.0f;
            gpVar.e = Util.getCurrentUxcamTime(fp.f157n);
            this.f183a.a(gpVar);
        }
        JSONArray jSONArrayA = this.i.a();
        this.b.d();
        this.f183a.h();
        this.f183a.f();
        return jSONArrayA;
    }
}
