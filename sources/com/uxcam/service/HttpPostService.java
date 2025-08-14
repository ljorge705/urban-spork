package com.uxcam.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import com.uxcam.internals.bi;
import com.uxcam.internals.da;
import com.uxcam.internals.fm;
import com.uxcam.internals.fp;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0005B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/uxcam/service/HttpPostService;", "Landroid/app/Service;", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "()V", "aa", "uxcamlib_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class HttpPostService extends Service implements CoroutineScope {

    /* renamed from: a, reason: collision with root package name */
    public static final String f290a;
    public static boolean b;
    public static final List<String> c;

    public static final class aa {
        @JvmStatic
        public static void a(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                for (String str : HttpPostService.c) {
                    if (Intrinsics.areEqual(file.getAbsolutePath(), str)) {
                        HttpPostService.c.remove(str);
                    }
                }
            } catch (ConcurrentModificationException unused) {
                a(file);
            }
        }
    }

    @DebugMetadata(c = "com.uxcam.service.HttpPostService$onDestroy$1", f = "HttpPostService.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
    public static final class ab extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a, reason: collision with root package name */
        public int f291a;

        public ab(Continuation<? super ab> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new ab(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new ab(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f291a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.f291a = 1;
                if (DelayKt.delay(700L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            HttpPostService.b = false;
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.uxcam.service.HttpPostService$onStartCommand$1", f = "HttpPostService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class ac extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Message b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ac(Message message, Continuation<? super ac> continuation) {
            super(2, continuation);
            this.b = message;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HttpPostService.this.new ac(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((ac) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            HttpPostService httpPostService = HttpPostService.this;
            Message message = this.b;
            String str = HttpPostService.f290a;
            httpPostService.getClass();
            String string = message.getData().getString("arg_which_service");
            String str2 = HttpPostService.f290a;
            gk.a(str2).getClass();
            if (string != null) {
                int iHashCode = string.hashCode();
                if (iHashCode != 666429405) {
                    if (iHashCode != 901710240) {
                        if (iHashCode != 1586837812) {
                            if (iHashCode == 1592315741 && string.equals("value_stop_uxcam")) {
                                if (bi.D == null) {
                                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                                }
                                bi biVar = bi.D;
                                Intrinsics.checkNotNull(biVar);
                                fp fpVarE = biVar.e();
                                Util.getCurrentApplicationContext();
                                fpVarE.a("");
                            }
                        } else if (string.equals("screen_upload")) {
                            try {
                                new da(Util.getCurrentApplicationContext()).a();
                            } catch (Exception unused) {
                                gk.a(HttpPostService.f290a).getClass();
                            }
                        }
                    } else if (string.equals("stop_foreground")) {
                        gk.a(str2).getClass();
                    }
                } else if (string.equals("send_offline_data")) {
                    new fm(Util.getCurrentApplicationContext()).b();
                }
            }
            return Unit.INSTANCE;
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("HttpPostService", "HttpPostService::class.java.simpleName");
        f290a = "HttpPostService";
        List<String> listSynchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkNotNullExpressionValue(listSynchronizedList, "synchronizedList(ArrayList())");
        c = listSynchronizedList;
    }

    @JvmStatic
    public static final boolean a(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(file.getAbsolutePath(), it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO());
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new ab(null), 3, null);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getExtras() != null) {
            b = true;
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            String string = extras.getString("arg_which_service");
            if (string != null && !StringsKt.equals(string, "stop_foreground", true)) {
                Message message = new Message();
                message.arg1 = i2;
                message.setData(intent.getExtras());
                BuildersKt__Builders_commonKt.launch$default(this, null, null, new ac(message, null), 3, null);
            }
        }
        return 2;
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent rootIntent) {
        Intrinsics.checkNotNullParameter(rootIntent, "rootIntent");
        stopSelf();
    }
}
