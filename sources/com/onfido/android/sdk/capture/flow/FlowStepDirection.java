package com.onfido.android.sdk.capture.flow;

import android.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/flow/FlowStepDirection;", "", "slideInAnimation", "", "slideOutAnimation", "(Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;)V", "getSlideInAnimation", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSlideOutAnimation", "LEFT_TO_RIGHT", "RIGHT_TO_LEFT", "NO_DIRECTION", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FlowStepDirection {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FlowStepDirection[] $VALUES;
    private final Integer slideInAnimation;
    private final Integer slideOutAnimation;
    public static final FlowStepDirection LEFT_TO_RIGHT = new FlowStepDirection("LEFT_TO_RIGHT", 0, Integer.valueOf(R.anim.slide_in_left), Integer.valueOf(R.anim.slide_out_right));
    public static final FlowStepDirection RIGHT_TO_LEFT = new FlowStepDirection("RIGHT_TO_LEFT", 1, Integer.valueOf(com.onfido.android.sdk.capture.R.anim.onfido_slide_in_right), Integer.valueOf(com.onfido.android.sdk.capture.R.anim.onfido_slide_out_left));
    public static final FlowStepDirection NO_DIRECTION = new FlowStepDirection("NO_DIRECTION", 2, null, null, 3, null);

    private static final /* synthetic */ FlowStepDirection[] $values() {
        return new FlowStepDirection[]{LEFT_TO_RIGHT, RIGHT_TO_LEFT, NO_DIRECTION};
    }

    static {
        FlowStepDirection[] flowStepDirectionArr$values = $values();
        $VALUES = flowStepDirectionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(flowStepDirectionArr$values);
    }

    private FlowStepDirection(String str, int i, Integer num, Integer num2) {
        this.slideInAnimation = num;
        this.slideOutAnimation = num2;
    }

    public static EnumEntries<FlowStepDirection> getEntries() {
        return $ENTRIES;
    }

    public static FlowStepDirection valueOf(String str) {
        return (FlowStepDirection) Enum.valueOf(FlowStepDirection.class, str);
    }

    public static FlowStepDirection[] values() {
        return (FlowStepDirection[]) $VALUES.clone();
    }

    public final Integer getSlideInAnimation() {
        return this.slideInAnimation;
    }

    public final Integer getSlideOutAnimation() {
        return this.slideOutAnimation;
    }

    /* synthetic */ FlowStepDirection(String str, int i, Integer num, Integer num2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 1) != 0 ? null : num, (i2 & 2) != 0 ? null : num2);
    }
}
