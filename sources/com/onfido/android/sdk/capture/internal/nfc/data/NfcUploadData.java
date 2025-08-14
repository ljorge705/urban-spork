package com.onfido.android.sdk.capture.internal.nfc.data;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 /2\u00020\u0001:\u0002./B\u0085\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010BY\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0011J&\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,HÁ\u0001¢\u0006\u0002\b-R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0015R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\u0015R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0015R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0013\u001a\u0004\b%\u0010\u0015¨\u00060"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcUploadData;", "", "seen1", "", "dg1", "", "dg2", "dg11", "dg12", "dg13", "dg14", "dg15", "sod", "aaSignedChallenge", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(I[I[I[I[I[I[I[I[I[ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "([I[I[I[I[I[I[I[I[I)V", "getAaSignedChallenge$annotations", "()V", "getAaSignedChallenge", "()[I", "getDg1$annotations", "getDg1", "getDg11$annotations", "getDg11", "getDg12$annotations", "getDg12", "getDg13$annotations", "getDg13", "getDg14$annotations", "getDg14", "getDg15$annotations", "getDg15", "getDg2$annotations", "getDg2", "getSod$annotations", "getSod", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final class NfcUploadData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int[] aaSignedChallenge;
    private final int[] dg1;
    private final int[] dg11;
    private final int[] dg12;
    private final int[] dg13;
    private final int[] dg14;
    private final int[] dg15;
    private final int[] dg2;
    private final int[] sod;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcUploadData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcUploadData;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<NfcUploadData> serializer() {
            return NfcUploadData$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ NfcUploadData(int i, @SerialName("dg1") int[] iArr, @SerialName("dg2") int[] iArr2, @SerialName("dg11") int[] iArr3, @SerialName("dg12") int[] iArr4, @SerialName("dg13") int[] iArr5, @SerialName("dg14") int[] iArr6, @SerialName("dg15") int[] iArr7, @SerialName("sod") int[] iArr8, @SerialName("aa_signed_challenge") int[] iArr9, SerializationConstructorMarker serializationConstructorMarker) {
        if (511 != (i & FrameMetricsAggregator.EVERY_DURATION)) {
            PluginExceptionsKt.throwMissingFieldException(i, FrameMetricsAggregator.EVERY_DURATION, NfcUploadData$$serializer.INSTANCE.getDescriptor());
        }
        this.dg1 = iArr;
        this.dg2 = iArr2;
        this.dg11 = iArr3;
        this.dg12 = iArr4;
        this.dg13 = iArr5;
        this.dg14 = iArr6;
        this.dg15 = iArr7;
        this.sod = iArr8;
        this.aaSignedChallenge = iArr9;
    }

    @SerialName("aa_signed_challenge")
    public static /* synthetic */ void getAaSignedChallenge$annotations() {
    }

    @SerialName("dg1")
    public static /* synthetic */ void getDg1$annotations() {
    }

    @SerialName("dg11")
    public static /* synthetic */ void getDg11$annotations() {
    }

    @SerialName("dg12")
    public static /* synthetic */ void getDg12$annotations() {
    }

    @SerialName("dg13")
    public static /* synthetic */ void getDg13$annotations() {
    }

    @SerialName("dg14")
    public static /* synthetic */ void getDg14$annotations() {
    }

    @SerialName("dg15")
    public static /* synthetic */ void getDg15$annotations() {
    }

    @SerialName("dg2")
    public static /* synthetic */ void getDg2$annotations() {
    }

    @SerialName("sod")
    public static /* synthetic */ void getSod$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(NfcUploadData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        IntArraySerializer intArraySerializer = IntArraySerializer.INSTANCE;
        output.encodeSerializableElement(serialDesc, 0, intArraySerializer, self.dg1);
        output.encodeSerializableElement(serialDesc, 1, intArraySerializer, self.dg2);
        output.encodeNullableSerializableElement(serialDesc, 2, intArraySerializer, self.dg11);
        output.encodeNullableSerializableElement(serialDesc, 3, intArraySerializer, self.dg12);
        output.encodeNullableSerializableElement(serialDesc, 4, intArraySerializer, self.dg13);
        output.encodeNullableSerializableElement(serialDesc, 5, intArraySerializer, self.dg14);
        output.encodeNullableSerializableElement(serialDesc, 6, intArraySerializer, self.dg15);
        output.encodeSerializableElement(serialDesc, 7, intArraySerializer, self.sod);
        output.encodeNullableSerializableElement(serialDesc, 8, intArraySerializer, self.aaSignedChallenge);
    }

    public final int[] getAaSignedChallenge() {
        return this.aaSignedChallenge;
    }

    public final int[] getDg1() {
        return this.dg1;
    }

    public final int[] getDg11() {
        return this.dg11;
    }

    public final int[] getDg12() {
        return this.dg12;
    }

    public final int[] getDg13() {
        return this.dg13;
    }

    public final int[] getDg14() {
        return this.dg14;
    }

    public final int[] getDg15() {
        return this.dg15;
    }

    public final int[] getDg2() {
        return this.dg2;
    }

    public final int[] getSod() {
        return this.sod;
    }

    public NfcUploadData(int[] dg1, int[] dg2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] sod, int[] iArr6) {
        Intrinsics.checkNotNullParameter(dg1, "dg1");
        Intrinsics.checkNotNullParameter(dg2, "dg2");
        Intrinsics.checkNotNullParameter(sod, "sod");
        this.dg1 = dg1;
        this.dg2 = dg2;
        this.dg11 = iArr;
        this.dg12 = iArr2;
        this.dg13 = iArr3;
        this.dg14 = iArr4;
        this.dg15 = iArr5;
        this.sod = sod;
        this.aaSignedChallenge = iArr6;
    }
}
