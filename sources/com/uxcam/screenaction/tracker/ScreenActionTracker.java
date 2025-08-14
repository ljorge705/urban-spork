package com.uxcam.screenaction.tracker;

import android.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.repository.ScreenActionViewsRepository;
import com.uxcam.screenaction.utils.ReflectionUtil;
import com.uxcam.screenaction.utils.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0015H\u0002J\u0006\u0010\u001a\u001a\u00020\u0013J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0019\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001cH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lcom/uxcam/screenaction/tracker/ScreenActionTracker;", "Lkotlinx/coroutines/CoroutineScope;", "pluginType", "", "screenActionViewsRepository", "Lcom/uxcam/screenaction/repository/ScreenActionViewsRepository;", "(Ljava/lang/String;Lcom/uxcam/screenaction/repository/ScreenActionViewsRepository;)V", "buttonCounter", "", "compoundButtonCounter", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "editTextCounter", "seekBarCounter", "unknownViewCounter", "viewGroupCounter", "attachOnTouchListener", "", "child", "Landroid/view/View;", "positionOfView", "getOldTouchListener", "Landroid/view/View$OnTouchListener;", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "loopLayout", "parent", "Landroid/view/ViewGroup;", "startLoop", "rootView", "(Landroid/view/ViewGroup;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ScreenActionTracker implements CoroutineScope {
    public static final String TAG = "ScreenActionTracker";
    private static boolean clearViewArray;
    private static boolean loopingLayout;
    private final /* synthetic */ CoroutineScope $$delegate_0;
    private int buttonCounter;
    private int compoundButtonCounter;
    private int editTextCounter;
    private final String pluginType;
    private final ScreenActionViewsRepository screenActionViewsRepository;
    private int seekBarCounter;
    private int unknownViewCounter;
    private int viewGroupCounter;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(0);
    private static ArrayList<UXCamView> viewArrayList = new ArrayList<>();

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/uxcam/screenaction/tracker/ScreenActionTracker$Companion;", "", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "com.uxcam.screenaction.tracker.ScreenActionTracker$loopLayout$1", f = "ScreenActionTracker.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.uxcam.screenaction.tracker.ScreenActionTracker$loopLayout$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a, reason: collision with root package name */
        public int f254a;
        public final /* synthetic */ View b;
        public final /* synthetic */ ScreenActionTracker c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, ScreenActionTracker screenActionTracker, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.b = view;
            this.c = screenActionTracker;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.b, this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f254a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                View view = this.b;
                if (view instanceof ViewGroup) {
                    this.f254a = 1;
                    if (this.c.startLoop((ViewGroup) view, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.uxcam.screenaction.tracker.ScreenActionTracker", f = "ScreenActionTracker.kt", i = {0, 0}, l = {52}, m = "startLoop", n = {"this", "rootView"}, s = {"L$0", "L$1"})
    /* renamed from: com.uxcam.screenaction.tracker.ScreenActionTracker$startLoop$1, reason: invalid class name and case insensitive filesystem */
    public static final class C08351 extends ContinuationImpl {

        /* renamed from: a, reason: collision with root package name */
        public ScreenActionTracker f255a;
        public ViewGroup b;
        public /* synthetic */ Object c;
        public int e;

        public C08351(Continuation<? super C08351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return ScreenActionTracker.this.startLoop(null, this);
        }
    }

    public ScreenActionTracker(String str, ScreenActionViewsRepository screenActionViewsRepository) {
        Intrinsics.checkNotNullParameter(screenActionViewsRepository, "screenActionViewsRepository");
        this.pluginType = str;
        this.screenActionViewsRepository = screenActionViewsRepository;
        this.$$delegate_0 = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
    }

    private final void attachOnTouchListener(View child, int positionOfView) {
        try {
            String str = this.pluginType;
            if (str != null) {
                String lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(lowerCase, "xamarin")) {
                    return;
                }
            }
            if (child.isShown() && child.getVisibility() == 0) {
                for (WeakReference weakReference : this.screenActionViewsRepository.getF253a()) {
                    if (weakReference != null && weakReference.get() == child) {
                        return;
                    }
                }
                View.OnTouchListener oldTouchListener = getOldTouchListener(child);
                if (oldTouchListener instanceof UXTouchEventListener) {
                    ((UXTouchEventListener) oldTouchListener).b = positionOfView;
                } else {
                    child.setOnTouchListener(new UXTouchEventListener(oldTouchListener, positionOfView));
                    this.screenActionViewsRepository.a(new WeakReference<>(child));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final boolean getClearViewArray() {
        INSTANCE.getClass();
        return clearViewArray;
    }

    private final View.OnTouchListener getOldTouchListener(View v) throws SecurityException {
        Class<?> superclass = v.getClass();
        while (!Intrinsics.areEqual(superclass, View.class)) {
            superclass = superclass.getSuperclass();
            Intrinsics.checkNotNullExpressionValue(superclass, "currentClass.superclass");
        }
        Field[] fields = superclass.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(fields, "fields");
        for (Field field : fields) {
            if (Intrinsics.areEqual("mListenerInfo", field.getName())) {
                field.setAccessible(true);
                return (View.OnTouchListener) ReflectionUtil.getFieldValue("mOnTouchListener", field.get(v));
            }
        }
        return null;
    }

    public static final ArrayList<UXCamView> getViewArrayList() {
        INSTANCE.getClass();
        return viewArrayList;
    }

    public static final void setClearViewArray(boolean z) {
        INSTANCE.getClass();
        clearViewArray = z;
    }

    public static final void setViewArrayList(ArrayList<UXCamView> arrayList) {
        INSTANCE.getClass();
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        viewArrayList = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startLoop(android.view.ViewGroup r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.uxcam.screenaction.tracker.ScreenActionTracker.C08351
            if (r0 == 0) goto L13
            r0 = r6
            com.uxcam.screenaction.tracker.ScreenActionTracker$startLoop$1 r0 = (com.uxcam.screenaction.tracker.ScreenActionTracker.C08351) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            com.uxcam.screenaction.tracker.ScreenActionTracker$startLoop$1 r0 = new com.uxcam.screenaction.tracker.ScreenActionTracker$startLoop$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            android.view.ViewGroup r5 = r0.b
            com.uxcam.screenaction.tracker.ScreenActionTracker r0 = r0.f255a
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Exception -> L57
            goto L4a
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.uxcam.screenaction.tracker.ScreenActionTracker.loopingLayout = r3
            r0.f255a = r4     // Catch: java.lang.Exception -> L57
            r0.b = r5     // Catch: java.lang.Exception -> L57
            r0.e = r3     // Catch: java.lang.Exception -> L57
            r2 = 800(0x320, double:3.953E-321)
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r2, r0)     // Catch: java.lang.Exception -> L57
            if (r6 != r1) goto L49
            return r1
        L49:
            r0 = r4
        L4a:
            com.uxcam.screenaction.tracker.ScreenActionTracker r6 = new com.uxcam.screenaction.tracker.ScreenActionTracker     // Catch: java.lang.Exception -> L57
            java.lang.String r1 = r0.pluginType     // Catch: java.lang.Exception -> L57
            com.uxcam.screenaction.repository.ScreenActionViewsRepository r0 = r0.screenActionViewsRepository     // Catch: java.lang.Exception -> L57
            r6.<init>(r1, r0)     // Catch: java.lang.Exception -> L57
            r6.loopLayout(r5)     // Catch: java.lang.Exception -> L57
            goto L5b
        L57:
            r5 = move-exception
            r5.printStackTrace()
        L5b:
            r5 = 0
            com.uxcam.screenaction.tracker.ScreenActionTracker.loopingLayout = r5
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.tracker.ScreenActionTracker.startLoop(android.view.ViewGroup, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public final void loopLayout() {
        try {
            this.screenActionViewsRepository.a();
        } catch (Exception unused) {
        }
        Activity activity = (Activity) Util.getCurrentContext();
        if (loopingLayout || activity == null) {
            return;
        }
        View viewFindViewById = activity.findViewById(R.id.content);
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new AnonymousClass1(viewFindViewById != null ? viewFindViewById.getRootView() : null, this, null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void loopLayout(android.view.ViewGroup r9) {
        /*
            r8 = this;
            int r0 = r9.getChildCount()
            r1 = 0
            r2 = r1
        L6:
            if (r2 >= r0) goto L81
            android.view.View r3 = r9.getChildAt(r2)
            if (r3 == 0) goto L7e
            boolean r4 = r3 instanceof android.view.ViewGroup
            if (r4 == 0) goto L21
            int r4 = r8.viewGroupCounter
            int r4 = r4 + 1
            r8.viewGroupCounter = r4
            r8.attachOnTouchListener(r3, r4)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            r8.loopLayout(r3)
            goto L7e
        L21:
            boolean r4 = r3 instanceof android.widget.CompoundButton
            if (r4 == 0) goto L2f
            int r4 = r8.compoundButtonCounter
            int r4 = r4 + 1
            r8.compoundButtonCounter = r4
            r8.attachOnTouchListener(r3, r4)
            goto L7e
        L2f:
            boolean r4 = r3 instanceof android.widget.Button
            if (r4 != 0) goto L75
            boolean r4 = r3 instanceof android.widget.ImageButton
            if (r4 != 0) goto L75
            java.lang.Class r4 = r3.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = "child.javaClass.name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r5 = 2
            r6 = 0
            java.lang.String r7 = "ActionMenuItemView"
            boolean r4 = kotlin.text.StringsKt.contains$default(r4, r7, r1, r5, r6)
            if (r4 == 0) goto L4f
            goto L75
        L4f:
            boolean r4 = r3 instanceof android.widget.EditText
            if (r4 == 0) goto L5d
            int r4 = r8.editTextCounter
            int r4 = r4 + 1
            r8.editTextCounter = r4
            r8.attachOnTouchListener(r3, r4)
            goto L7e
        L5d:
            boolean r4 = r3 instanceof android.widget.SeekBar
            if (r4 == 0) goto L6b
            int r4 = r8.seekBarCounter
            int r4 = r4 + 1
            r8.seekBarCounter = r4
            r8.attachOnTouchListener(r3, r4)
            goto L7e
        L6b:
            int r4 = r8.unknownViewCounter
            int r4 = r4 + 1
            r8.unknownViewCounter = r4
            r8.attachOnTouchListener(r3, r4)
            goto L7e
        L75:
            int r4 = r8.buttonCounter
            int r4 = r4 + 1
            r8.buttonCounter = r4
            r8.attachOnTouchListener(r3, r4)
        L7e:
            int r2 = r2 + 1
            goto L6
        L81:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.tracker.ScreenActionTracker.loopLayout(android.view.ViewGroup):void");
    }
}
