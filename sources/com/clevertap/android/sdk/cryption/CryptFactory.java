package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.cryption.CryptHandler;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptFactory.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptFactory;", "", "()V", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CryptFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final Crypt getCrypt(CryptHandler.EncryptionAlgorithm encryptionAlgorithm) {
        return INSTANCE.getCrypt(encryptionAlgorithm);
    }

    /* compiled from: CryptFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptFactory$Companion;", "", "()V", "getCrypt", "Lcom/clevertap/android/sdk/cryption/Crypt;", "type", "Lcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionAlgorithm;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* compiled from: CryptFactory.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CryptHandler.EncryptionAlgorithm.values().length];
                try {
                    iArr[CryptHandler.EncryptionAlgorithm.AES.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Crypt getCrypt(CryptHandler.EncryptionAlgorithm type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1) {
                return new AESCrypt();
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
