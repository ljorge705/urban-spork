package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import com.clevertap.android.sdk.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PointerEvent.android.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B\u001f\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u001f\u001a\u00020\u001aH\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\fJ\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u001e\u0010\"\u001a\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u001c\u0010\t\u001a\u00020\nø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u0013ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0014\u0010\fR\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R/\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@@X\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u001e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006#"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEvent;", "", "changes", "", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "(Ljava/util/List;)V", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "(Ljava/util/List;Landroidx/compose/ui/input/pointer/InternalPointerEvent;)V", Constants.KEY_BUTTONS, "Landroidx/compose/ui/input/pointer/PointerButtons;", "getButtons-ry648PA", "()I", "I", "getChanges", "()Ljava/util/List;", "getInternalPointerEvent$ui_release", "()Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "keyboardModifiers", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "getKeyboardModifiers-k7X9c1A", "motionEvent", "Landroid/view/MotionEvent;", "getMotionEvent$ui_release", "()Landroid/view/MotionEvent;", "<set-?>", "Landroidx/compose/ui/input/pointer/PointerEventType;", "type", "getType-7fucELk", "setType-EhbLWgg$ui_release", "(I)V", "calculatePointerEventType", "calculatePointerEventType-7fucELk", "component1", Constants.COPY_TYPE, "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PointerEvent {
    public static final int $stable = 8;
    private final int buttons;
    private final List<PointerInputChange> changes;
    private final InternalPointerEvent internalPointerEvent;
    private final int keyboardModifiers;
    private int type;

    public final List<PointerInputChange> component1() {
        return this.changes;
    }

    /* renamed from: getButtons-ry648PA, reason: not valid java name and from getter */
    public final int getButtons() {
        return this.buttons;
    }

    public final List<PointerInputChange> getChanges() {
        return this.changes;
    }

    /* renamed from: getInternalPointerEvent$ui_release, reason: from getter */
    public final InternalPointerEvent getInternalPointerEvent() {
        return this.internalPointerEvent;
    }

    /* renamed from: getKeyboardModifiers-k7X9c1A, reason: not valid java name and from getter */
    public final int getKeyboardModifiers() {
        return this.keyboardModifiers;
    }

    /* renamed from: getType-7fucELk, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: setType-EhbLWgg$ui_release, reason: not valid java name */
    public final void m3224setTypeEhbLWgg$ui_release(int i) {
        this.type = i;
    }

    public PointerEvent(List<PointerInputChange> changes, InternalPointerEvent internalPointerEvent) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        this.changes = changes;
        this.internalPointerEvent = internalPointerEvent;
        MotionEvent motionEvent$ui_release = getMotionEvent$ui_release();
        this.buttons = PointerButtons.m3214constructorimpl(motionEvent$ui_release != null ? motionEvent$ui_release.getButtonState() : 0);
        MotionEvent motionEvent$ui_release2 = getMotionEvent$ui_release();
        this.keyboardModifiers = PointerKeyboardModifiers.m3340constructorimpl(motionEvent$ui_release2 != null ? motionEvent$ui_release2.getMetaState() : 0);
        this.type = m3220calculatePointerEventType7fucELk();
    }

    public final MotionEvent getMotionEvent$ui_release() {
        InternalPointerEvent internalPointerEvent = this.internalPointerEvent;
        if (internalPointerEvent != null) {
            return internalPointerEvent.getMotionEvent();
        }
        return null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PointerEvent(List<PointerInputChange> changes) {
        this(changes, null);
        Intrinsics.checkNotNullParameter(changes, "changes");
    }

    /* renamed from: calculatePointerEventType-7fucELk, reason: not valid java name */
    private final int m3220calculatePointerEventType7fucELk() {
        MotionEvent motionEvent$ui_release = getMotionEvent$ui_release();
        if (motionEvent$ui_release != null) {
            int actionMasked = motionEvent$ui_release.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        switch (actionMasked) {
                        }
                        return PointerEventType.INSTANCE.m3237getPress7fucELk();
                    }
                    return PointerEventType.INSTANCE.m3236getMove7fucELk();
                }
                return PointerEventType.INSTANCE.m3238getRelease7fucELk();
            }
            return PointerEventType.INSTANCE.m3237getPress7fucELk();
        }
        List<PointerInputChange> list = this.changes;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PointerInputChange pointerInputChange = list.get(i);
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                return PointerEventType.INSTANCE.m3238getRelease7fucELk();
            }
            if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
                return PointerEventType.INSTANCE.m3237getPress7fucELk();
            }
        }
        return PointerEventType.INSTANCE.m3236getMove7fucELk();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.input.pointer.PointerEvent copy(java.util.List<androidx.compose.ui.input.pointer.PointerInputChange> r30, android.view.MotionEvent r31) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r2 = r31
            java.lang.String r3 = "changes"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            if (r2 != 0) goto L15
            androidx.compose.ui.input.pointer.PointerEvent r2 = new androidx.compose.ui.input.pointer.PointerEvent
            r3 = 0
            r2.<init>(r1, r3)
            goto Lcb
        L15:
            android.view.MotionEvent r3 = r29.getMotionEvent$ui_release()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L28
            androidx.compose.ui.input.pointer.PointerEvent r2 = new androidx.compose.ui.input.pointer.PointerEvent
            androidx.compose.ui.input.pointer.InternalPointerEvent r3 = r0.internalPointerEvent
            r2.<init>(r1, r3)
            goto Lcb
        L28:
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>()
            java.util.Map r3 = (java.util.Map) r3
            int r4 = r30.size()
            r6 = 0
        L34:
            if (r6 >= r4) goto L4a
            java.lang.Object r7 = r1.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            long r8 = r7.getId()
            androidx.compose.ui.input.pointer.PointerId r8 = androidx.compose.ui.input.pointer.PointerId.m3260boximpl(r8)
            r3.put(r8, r7)
            int r6 = r6 + 1
            goto L34
        L4a:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r6 = r30.size()
            r4.<init>(r6)
            int r6 = r30.size()
            r7 = 0
        L58:
            if (r7 >= r6) goto Lb5
            java.lang.Object r8 = r1.get(r7)
            r9 = r4
            java.util.Collection r9 = (java.util.Collection) r9
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            androidx.compose.ui.input.pointer.PointerInputEventData r15 = new androidx.compose.ui.input.pointer.PointerInputEventData
            long r11 = r8.getId()
            long r13 = r8.getUptimeMillis()
            long r16 = r8.getPosition()
            long r18 = r8.getPosition()
            boolean r20 = r8.getPressed()
            float r21 = r8.getPressure()
            int r22 = r8.getType()
            androidx.compose.ui.input.pointer.InternalPointerEvent r10 = r0.internalPointerEvent
            r28 = r6
            if (r10 == 0) goto L93
            long r5 = r8.getId()
            boolean r5 = r10.m3210issuesEnterExitEvent0FcD4WY(r5)
            r6 = 1
            if (r5 != r6) goto L93
            goto L94
        L93:
            r6 = 0
        L94:
            r23 = 0
            r24 = 0
            r26 = 768(0x300, float:1.076E-42)
            r27 = 0
            r10 = r15
            r5 = r15
            r15 = r16
            r17 = r18
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r6
            r10.<init>(r11, r13, r15, r17, r19, r20, r21, r22, r23, r24, r26, r27)
            r9.add(r5)
            int r7 = r7 + 1
            r6 = r28
            goto L58
        Lb5:
            java.util.List r4 = (java.util.List) r4
            androidx.compose.ui.input.pointer.PointerInputEvent r5 = new androidx.compose.ui.input.pointer.PointerInputEvent
            long r6 = r31.getEventTime()
            r5.<init>(r6, r4, r2)
            androidx.compose.ui.input.pointer.InternalPointerEvent r2 = new androidx.compose.ui.input.pointer.InternalPointerEvent
            r2.<init>(r3, r5)
            androidx.compose.ui.input.pointer.PointerEvent r3 = new androidx.compose.ui.input.pointer.PointerEvent
            r3.<init>(r1, r2)
            r2 = r3
        Lcb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerEvent.copy(java.util.List, android.view.MotionEvent):androidx.compose.ui.input.pointer.PointerEvent");
    }
}
