package com.clevertap.android.sdk.inapp.evaluation;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TriggerAdapter.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggerOperator;", "", "operatorValue", "", "(Ljava/lang/String;II)V", "getOperatorValue", "()I", "GreaterThan", "Equals", "LessThan", "Contains", "Between", "NotEquals", "Set", "NotSet", "NotContains", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum TriggerOperator {
    GreaterThan(0),
    Equals(1),
    LessThan(2),
    Contains(3),
    Between(4),
    NotEquals(15),
    Set(26),
    NotSet(27),
    NotContains(28);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int operatorValue;

    public final int getOperatorValue() {
        return this.operatorValue;
    }

    TriggerOperator(int i) {
        this.operatorValue = i;
    }

    /* compiled from: TriggerAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggerOperator$Companion;", "", "()V", "fromOperatorValue", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerOperator;", "operatorValue", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TriggerOperator fromOperatorValue(int operatorValue) {
            TriggerOperator triggerOperator;
            TriggerOperator[] triggerOperatorArrValues = TriggerOperator.values();
            int length = triggerOperatorArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    triggerOperator = null;
                    break;
                }
                triggerOperator = triggerOperatorArrValues[i];
                if (triggerOperator.getOperatorValue() == operatorValue) {
                    break;
                }
                i++;
            }
            return triggerOperator == null ? TriggerOperator.Equals : triggerOperator;
        }
    }
}
