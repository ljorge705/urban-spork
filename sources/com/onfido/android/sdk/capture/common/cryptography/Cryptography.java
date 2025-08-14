package com.onfido.android.sdk.capture.common.cryptography;

import com.clevertap.android.sdk.Constants;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.javax.inject.Inject;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;", "", "helper", "Lcom/onfido/android/sdk/capture/common/cryptography/CryptographyHelper;", "(Lcom/onfido/android/sdk/capture/common/cryptography/CryptographyHelper;)V", "hmacSha256", "", "data", Constants.KEY_KEY, "sign", "Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography$Result;", "payload", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "Result", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Cryptography {
    private final CryptographyHelper helper;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography$Result;", "", "signature", "", "clientNonce", "([B[B)V", "getClientNonce", "()[B", "getSignature", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Result {
        private final byte[] clientNonce;
        private final byte[] signature;

        public Result(byte[] signature, byte[] clientNonce) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            Intrinsics.checkNotNullParameter(clientNonce, "clientNonce");
            this.signature = signature;
            this.clientNonce = clientNonce;
        }

        public static /* synthetic */ Result copy$default(Result result, byte[] bArr, byte[] bArr2, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = result.signature;
            }
            if ((i & 2) != 0) {
                bArr2 = result.clientNonce;
            }
            return result.copy(bArr, bArr2);
        }

        /* renamed from: component1, reason: from getter */
        public final byte[] getSignature() {
            return this.signature;
        }

        /* renamed from: component2, reason: from getter */
        public final byte[] getClientNonce() {
            return this.clientNonce;
        }

        public final Result copy(byte[] signature, byte[] clientNonce) {
            Intrinsics.checkNotNullParameter(signature, "signature");
            Intrinsics.checkNotNullParameter(clientNonce, "clientNonce");
            return new Result(signature, clientNonce);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.signature, result.signature) && Intrinsics.areEqual(this.clientNonce, result.clientNonce);
        }

        public final byte[] getClientNonce() {
            return this.clientNonce;
        }

        public final byte[] getSignature() {
            return this.signature;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.signature) * 31) + Arrays.hashCode(this.clientNonce);
        }

        public String toString() {
            return "Result(signature=" + Arrays.toString(this.signature) + ", clientNonce=" + Arrays.toString(this.clientNonce) + ')';
        }
    }

    @Inject
    public Cryptography(CryptographyHelper helper) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.helper = helper;
    }

    public final byte[] hmacSha256(byte[] data, byte[] key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(key, "key");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] bArrDoFinal = mac.doFinal(data);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "run(...)");
        return bArrDoFinal;
    }

    public final Result sign(byte[] payload, ApplicantId applicantId) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        String uuid = applicantId.getUuid();
        Charset charset = Charsets.UTF_8;
        byte[] bytes = uuid.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrPlus = ArraysKt.plus(bytes, this.helper.getStaticMacKeyPart());
        byte[] bytes2 = (uuid + this.helper.getTimestampInSeconds()).getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        return new Result(hmacSha256(ArraysKt.plus(payload, bytes2), bArrPlus), bytes2);
    }
}
