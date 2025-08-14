package com.onfido.android.sdk.capture.ui.nfc.model;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments;", "Ljava/io/Serializable;", "CAN", "Empty", "WrongCAN", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$CAN;", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$Empty;", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$WrongCAN;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface NFCAdapterArguments extends Serializable {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$CAN;", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments;", CTVariableUtils.NUMBER, "", "(Ljava/lang/Number;)V", "getNumber", "()Ljava/lang/Number;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CAN implements NFCAdapterArguments {
        private final Number number;

        public CAN(Number number) {
            this.number = number;
        }

        public static /* synthetic */ CAN copy$default(CAN can, Number number, int i, Object obj) {
            if ((i & 1) != 0) {
                number = can.number;
            }
            return can.copy(number);
        }

        /* renamed from: component1, reason: from getter */
        public final Number getNumber() {
            return this.number;
        }

        public final CAN copy(Number number) {
            return new CAN(number);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CAN) && Intrinsics.areEqual(this.number, ((CAN) other).number);
        }

        public final Number getNumber() {
            return this.number;
        }

        public int hashCode() {
            Number number = this.number;
            if (number == null) {
                return 0;
            }
            return number.hashCode();
        }

        public String toString() {
            return "CAN(number=" + this.number + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$Empty;", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Empty implements NFCAdapterArguments {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments$WrongCAN;", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments;", CTVariableUtils.NUMBER, "", "(Ljava/lang/Number;)V", "getNumber", "()Ljava/lang/Number;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class WrongCAN implements NFCAdapterArguments {
        private final Number number;

        public WrongCAN(Number number) {
            this.number = number;
        }

        public static /* synthetic */ WrongCAN copy$default(WrongCAN wrongCAN, Number number, int i, Object obj) {
            if ((i & 1) != 0) {
                number = wrongCAN.number;
            }
            return wrongCAN.copy(number);
        }

        /* renamed from: component1, reason: from getter */
        public final Number getNumber() {
            return this.number;
        }

        public final WrongCAN copy(Number number) {
            return new WrongCAN(number);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WrongCAN) && Intrinsics.areEqual(this.number, ((WrongCAN) other).number);
        }

        public final Number getNumber() {
            return this.number;
        }

        public int hashCode() {
            Number number = this.number;
            if (number == null) {
                return 0;
            }
            return number.hashCode();
        }

        public String toString() {
            return "WrongCAN(number=" + this.number + ')';
        }
    }
}
