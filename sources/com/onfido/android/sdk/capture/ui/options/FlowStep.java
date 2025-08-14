package com.onfido.android.sdk.capture.ui.options;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowStep.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "Ljava/io/Serializable;", Constants.KEY_ACTION, "Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "(Lcom/onfido/android/sdk/capture/ui/options/FlowAction;)V", "getAction", "()Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "getOptions", "()Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "setOptions", "(Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class FlowStep implements Serializable {
    private final FlowAction action;
    private BaseOptions options;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final FlowStep WELCOME = new FlowStep(FlowAction.WELCOME);
    public static final FlowStep CAPTURE_DOCUMENT = new FlowStep(FlowAction.CAPTURE_DOCUMENT);
    public static final FlowStep CAPTURE_FACE = new FlowStep(FlowAction.CAPTURE_FACE);
    public static final FlowStep FINAL = new FlowStep(FlowAction.FINAL);
    public static final FlowStep PROOF_OF_ADDRESS = new FlowStep(FlowAction.PROOF_OF_ADDRESS);
    public static final FlowStep DYNAMIC_CONTENT = new FlowStep(FlowAction.DYNAMIC_CONTENT);

    @JvmStatic
    public static final List<FlowStep> getDefaultFlow() {
        return INSTANCE.getDefaultFlow();
    }

    @JvmStatic
    public static final List<FlowStep> getNonDuplicable() {
        return INSTANCE.getNonDuplicable();
    }

    public final FlowAction getAction() {
        return this.action;
    }

    public final BaseOptions getOptions() {
        return this.options;
    }

    public final void setOptions(BaseOptions baseOptions) {
        this.options = baseOptions;
    }

    public FlowStep(FlowAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.action = action;
    }

    public String toString() {
        BaseOptions baseOptions = this.options;
        if (baseOptions != null) {
            String str = this.action.name() + " withOptions: " + baseOptions;
            if (str != null) {
                return str;
            }
        }
        return this.action.name();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowStep)) {
            return false;
        }
        FlowStep flowStep = (FlowStep) other;
        return this.action == flowStep.action && Intrinsics.areEqual(this.options, flowStep.options);
    }

    public int hashCode() {
        int iHashCode = this.action.hashCode() * 31;
        BaseOptions baseOptions = this.options;
        return iHashCode + (baseOptions != null ? baseOptions.hashCode() : 0);
    }

    /* compiled from: FlowStep.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0007J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/FlowStep$Companion;", "", "()V", "CAPTURE_DOCUMENT", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "CAPTURE_FACE", "DYNAMIC_CONTENT", "getDYNAMIC_CONTENT$annotations", "FINAL", "PROOF_OF_ADDRESS", EventNames.PublicNames.Other.WELCOME, "getDefaultFlow", "", "getNonDuplicable", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getDYNAMIC_CONTENT$annotations() {
        }

        private Companion() {
        }

        @JvmStatic
        public final List<FlowStep> getDefaultFlow() {
            FlowAction[] defaultFlow = FlowAction.INSTANCE.getDefaultFlow();
            ArrayList arrayList = new ArrayList(defaultFlow.length);
            for (FlowAction flowAction : defaultFlow) {
                arrayList.add(new FlowStep(flowAction));
            }
            List<FlowStep> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(...)");
            return listUnmodifiableList;
        }

        @JvmStatic
        public final List<FlowStep> getNonDuplicable() {
            FlowAction[] nonDuplicable = FlowAction.INSTANCE.getNonDuplicable();
            ArrayList arrayList = new ArrayList(nonDuplicable.length);
            for (FlowAction flowAction : nonDuplicable) {
                arrayList.add(new FlowStep(flowAction));
            }
            List<FlowStep> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(...)");
            return listUnmodifiableList;
        }
    }
}
