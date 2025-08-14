package androidx.compose.ui.input.nestedscroll;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NestedScrollModifierLocal.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J)\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b&\u0010'J-\u0010(\u001a\u00020)2\u0006\u0010$\u001a\u00020)2\u0006\u0010%\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-J!\u0010.\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b/\u00100J%\u00101\u001a\u00020)2\u0006\u0010%\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R/\u0010\u0015\u001a\u0004\u0018\u00010\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00064"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollModifierLocal;", "Landroidx/compose/ui/modifier/ModifierLocalConsumer;", "Landroidx/compose/ui/modifier/ModifierLocalProvider;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "connection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "getConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getDispatcher", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", Constants.KEY_KEY, "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getKey", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "nestedCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getNestedCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "parent", "getParent", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollModifierLocal;", "setParent", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollModifierLocal;)V", "parent$delegate", "Landroidx/compose/runtime/MutableState;", "value", "getValue", "onModifierLocalsUpdated", "", "scope", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NestedScrollModifierLocal implements ModifierLocalConsumer, ModifierLocalProvider<NestedScrollModifierLocal>, NestedScrollConnection {
    private final NestedScrollConnection connection;
    private final NestedScrollDispatcher dispatcher;

    /* renamed from: parent$delegate, reason: from kotlin metadata */
    private final MutableState parent;

    public final NestedScrollConnection getConnection() {
        return this.connection;
    }

    public final NestedScrollDispatcher getDispatcher() {
        return this.dispatcher;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public NestedScrollModifierLocal getValue() {
        return this;
    }

    public NestedScrollModifierLocal(NestedScrollDispatcher dispatcher, NestedScrollConnection connection) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.dispatcher = dispatcher;
        this.connection = connection;
        dispatcher.setCalculateNestedScrollScope$ui_release(new Function0<CoroutineScope>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return NestedScrollModifierLocal.this.getNestedCoroutineScope();
            }
        });
        this.parent = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final NestedScrollModifierLocal getParent() {
        return (NestedScrollModifierLocal) this.parent.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineScope getNestedCoroutineScope() {
        CoroutineScope originNestedScrollScope;
        NestedScrollModifierLocal parent = getParent();
        if ((parent == null || (originNestedScrollScope = parent.getNestedCoroutineScope()) == null) && (originNestedScrollScope = this.dispatcher.getOriginNestedScrollScope()) == null) {
            throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        }
        return originNestedScrollScope;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public ProvidableModifierLocal<NestedScrollModifierLocal> getKey() {
        return NestedScrollModifierLocalKt.getModifierLocalNestedScroll();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalConsumer
    public void onModifierLocalsUpdated(ModifierLocalReadScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        setParent((NestedScrollModifierLocal) scope.getCurrent(NestedScrollModifierLocalKt.getModifierLocalNestedScroll()));
        this.dispatcher.setParent$ui_release(getParent());
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo585onPreScrollOzD1aCk(long available, int source) {
        NestedScrollModifierLocal parent = getParent();
        long jMo585onPreScrollOzD1aCk = parent != null ? parent.mo585onPreScrollOzD1aCk(available, source) : Offset.INSTANCE.m1655getZeroF1C5BW0();
        return Offset.m1644plusMKHz9U(jMo585onPreScrollOzD1aCk, this.connection.mo585onPreScrollOzD1aCk(Offset.m1643minusMKHz9U(available, jMo585onPreScrollOzD1aCk), source));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo584onPostScrollDzOQY0M(long consumed, long available, int source) {
        long jMo584onPostScrollDzOQY0M = this.connection.mo584onPostScrollDzOQY0M(consumed, available, source);
        NestedScrollModifierLocal parent = getParent();
        return Offset.m1644plusMKHz9U(jMo584onPostScrollDzOQY0M, parent != null ? parent.mo584onPostScrollDzOQY0M(Offset.m1644plusMKHz9U(consumed, jMo584onPostScrollDzOQY0M), Offset.m1643minusMKHz9U(available, jMo584onPostScrollDzOQY0M), source) : Offset.INSTANCE.m1655getZeroF1C5BW0());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo754onPreFlingQWom1Mo(long r9, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPreFling$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPreFling$1
            r0.<init>(r8, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L41
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            long r9 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L7d
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L37:
            long r9 = r0.J$0
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L58
        L41:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal r11 = r8.getParent()
            if (r11 == 0) goto L5f
            r0.L$0 = r8
            r0.J$0 = r9
            r0.label = r4
            java.lang.Object r11 = r11.mo754onPreFlingQWom1Mo(r9, r0)
            if (r11 != r1) goto L57
            return r1
        L57:
            r2 = r8
        L58:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r4 = r11.getPackedValue()
            goto L66
        L5f:
            androidx.compose.ui.unit.Velocity$Companion r11 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r4 = r11.m4626getZero9UxMQ8M()
            r2 = r8
        L66:
            r6 = r9
            r9 = r4
            r4 = r6
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r11 = r2.connection
            long r4 = androidx.compose.ui.unit.Velocity.m4618minusAH228Gc(r4, r9)
            r2 = 0
            r0.L$0 = r2
            r0.J$0 = r9
            r0.label = r3
            java.lang.Object r11 = r11.mo754onPreFlingQWom1Mo(r4, r0)
            if (r11 != r1) goto L7d
            return r1
        L7d:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r0 = r11.getPackedValue()
            long r9 = androidx.compose.ui.unit.Velocity.m4619plusAH228Gc(r9, r0)
            androidx.compose.ui.unit.Velocity r9 = androidx.compose.ui.unit.Velocity.m4606boximpl(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal.mo754onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo583onPostFlingRZ2iAVY(long r16, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r20
            boolean r2 = r1 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPostFling$1
            if (r2 == 0) goto L17
            r2 = r1
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPostFling$1 r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPostFling$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L17
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L1c
        L17:
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPostFling$1 r2 = new androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal$onPostFling$1
            r2.<init>(r15, r1)
        L1c:
            java.lang.Object r1 = r2.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r10 = 2
            r4 = 1
            if (r3 == 0) goto L48
            if (r3 == r4) goto L3a
            if (r3 != r10) goto L32
            long r2 = r2.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8d
        L32:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L3a:
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r7 = r2.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal r7 = (androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r3
            r11 = r5
            goto L66
        L48:
            kotlin.ResultKt.throwOnFailure(r1)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r0.connection
            r2.L$0 = r0
            r11 = r16
            r2.J$0 = r11
            r13 = r18
            r2.J$1 = r13
            r2.label = r4
            r4 = r16
            r6 = r18
            r8 = r2
            java.lang.Object r1 = r3.mo583onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L65
            return r9
        L65:
            r7 = r0
        L66:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r4 = r1.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal r3 = r7.getParent()
            if (r3 == 0) goto L95
            long r6 = androidx.compose.ui.unit.Velocity.m4619plusAH228Gc(r11, r4)
            long r11 = androidx.compose.ui.unit.Velocity.m4618minusAH228Gc(r13, r4)
            r1 = 0
            r2.L$0 = r1
            r2.J$0 = r4
            r2.label = r10
            r13 = r4
            r4 = r6
            r6 = r11
            r8 = r2
            java.lang.Object r1 = r3.mo583onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L8c
            return r9
        L8c:
            r2 = r13
        L8d:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r4 = r1.getPackedValue()
            r13 = r2
            goto L9c
        L95:
            r13 = r4
            androidx.compose.ui.unit.Velocity$Companion r1 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r4 = r1.m4626getZero9UxMQ8M()
        L9c:
            long r1 = androidx.compose.ui.unit.Velocity.m4619plusAH228Gc(r13, r4)
            androidx.compose.ui.unit.Velocity r1 = androidx.compose.ui.unit.Velocity.m4606boximpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollModifierLocal.mo583onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setParent(NestedScrollModifierLocal nestedScrollModifierLocal) {
        this.parent.setValue(nestedScrollModifierLocal);
    }
}
