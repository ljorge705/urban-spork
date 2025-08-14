package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.Constants;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: CryptHandler.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00162\u00020\u0001:\u0003\u0016\u0017\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0007J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptHandler;", "", Constants.KEY_ENCRYPTION_LEVEL, "", "encryptionType", "Lcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionAlgorithm;", "accountID", "", "(ILcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionAlgorithm;Ljava/lang/String;)V", "crypt", "Lcom/clevertap/android/sdk/cryption/Crypt;", Constants.KEY_ENCRYPTION_FLAG_STATUS, "getEncryptionFlagStatus", "()I", "setEncryptionFlagStatus", "(I)V", "Lcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionLevel;", "decrypt", "cipherText", Constants.KEY_KEY, "encrypt", "plainText", "Companion", "EncryptionAlgorithm", "EncryptionLevel", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CryptHandler {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String accountID;
    private Crypt crypt;
    private int encryptionFlagStatus;
    private EncryptionLevel encryptionLevel;
    private EncryptionAlgorithm encryptionType;

    /* compiled from: CryptHandler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionAlgorithm;", "", "(Ljava/lang/String;I)V", CipherStorageKeystoreAesCbc.ALGORITHM_AES, "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum EncryptionAlgorithm {
        AES
    }

    /* compiled from: CryptHandler.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EncryptionLevel.values().length];
            try {
                iArr[EncryptionLevel.MEDIUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final boolean isTextEncrypted(String str) {
        return INSTANCE.isTextEncrypted(str);
    }

    public final int getEncryptionFlagStatus() {
        return this.encryptionFlagStatus;
    }

    public final void setEncryptionFlagStatus(int i) {
        this.encryptionFlagStatus = i;
    }

    public CryptHandler(int i, EncryptionAlgorithm encryptionType, String accountID) {
        Intrinsics.checkNotNullParameter(encryptionType, "encryptionType");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        this.encryptionLevel = EncryptionLevel.values()[i];
        this.encryptionType = encryptionType;
        this.accountID = accountID;
        this.encryptionFlagStatus = 0;
        this.crypt = CryptFactory.INSTANCE.getCrypt(encryptionType);
    }

    /* compiled from: CryptHandler.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptHandler$EncryptionLevel;", "", "value", "", "(Ljava/lang/String;II)V", "intValue", "NONE", "MEDIUM", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum EncryptionLevel {
        NONE(0),
        MEDIUM(1);

        private final int value;

        /* renamed from: intValue, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        EncryptionLevel(int i) {
            this.value = i;
        }
    }

    public final String encrypt(String plainText, String key) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        Intrinsics.checkNotNullParameter(key, "key");
        return (WhenMappings.$EnumSwitchMapping$0[this.encryptionLevel.ordinal()] == 1 && Constants.MEDIUM_CRYPT_KEYS.contains(key) && !INSTANCE.isTextEncrypted(plainText)) ? this.crypt.encryptInternal(plainText, this.accountID) : plainText;
    }

    public final String encrypt(String plainText) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        return this.crypt.encryptInternal(plainText, this.accountID);
    }

    public final String decrypt(String cipherText, String key) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!INSTANCE.isTextEncrypted(cipherText)) {
            return cipherText;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.encryptionLevel.ordinal()] == 1) {
            return Constants.MEDIUM_CRYPT_KEYS.contains(key) ? this.crypt.decryptInternal(cipherText, this.accountID) : cipherText;
        }
        return this.crypt.decryptInternal(cipherText, this.accountID);
    }

    public final String decrypt(String cipherText) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        return this.crypt.decryptInternal(cipherText, this.accountID);
    }

    /* compiled from: CryptHandler.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptHandler$Companion;", "", "()V", "isTextEncrypted", "", "plainText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean isTextEncrypted(String plainText) {
            Intrinsics.checkNotNullParameter(plainText, "plainText");
            String str = plainText;
            return StringsKt.startsWith$default((CharSequence) str, AbstractJsonLexerKt.BEGIN_LIST, false, 2, (Object) null) && StringsKt.endsWith$default((CharSequence) str, AbstractJsonLexerKt.END_LIST, false, 2, (Object) null);
        }
    }
}
