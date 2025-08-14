package com.onfido.android.sdk.capture.antifraud;

import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/SignalFactory;", "", "uuidProvider", "Lcom/onfido/android/sdk/capture/utils/UuidProvider;", "(Lcom/onfido/android/sdk/capture/utils/UuidProvider;)V", "extractSignal", "Lcom/onfido/android/sdk/capture/antifraud/ExtractedSignal;", "signal", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SignalFactory {
    private final UuidProvider uuidProvider;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Signal.values().length];
            try {
                iArr[Signal.API_LEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Signal.ANDROID_VERSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Signal.BOARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Signal.BOOTLOADER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Signal.BRAND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Signal.DEVICE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Signal.DISPLAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Signal.FINGERPRINT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[Signal.HARDWARE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[Signal.BUILD_ID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[Signal.MANUFACTURER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[Signal.MODEL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[Signal.ODM_SKU.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[Signal.PRODUCT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[Signal.SERIAL.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[Signal.SKU.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[Signal.SOC_MANUFACTURER.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[Signal.SOC_MODEL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[Signal.SUPPORTED_ABIS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[Signal.LOCAL_UUID.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public SignalFactory(UuidProvider uuidProvider) {
        Intrinsics.checkNotNullParameter(uuidProvider, "uuidProvider");
        this.uuidProvider = uuidProvider;
    }

    public final ExtractedSignal extractSignal(Signal signal) {
        Intrinsics.checkNotNullParameter(signal, "signal");
        switch (WhenMappings.$EnumSwitchMapping$0[signal.ordinal()]) {
            case 1:
                return ApiLevel.INSTANCE;
            case 2:
                return AndroidVersion.INSTANCE;
            case 3:
                return Board.INSTANCE;
            case 4:
                return Bootloader.INSTANCE;
            case 5:
                return Brand.INSTANCE;
            case 6:
                return Device.INSTANCE;
            case 7:
                return Display.INSTANCE;
            case 8:
                return Fingerprint.INSTANCE;
            case 9:
                return Hardware.INSTANCE;
            case 10:
                return BuildId.INSTANCE;
            case 11:
                return Manufacturer.INSTANCE;
            case 12:
                return Model.INSTANCE;
            case 13:
                return OdmSku.INSTANCE;
            case 14:
                return Product.INSTANCE;
            case 15:
                return Serial.INSTANCE;
            case 16:
                return Sku.INSTANCE;
            case 17:
                return SocManufacturer.INSTANCE;
            case 18:
                return SocModel.INSTANCE;
            case 19:
                return SupportedAbis.INSTANCE;
            case 20:
                return new LocalUuid(this.uuidProvider.getPersistedUuid());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
