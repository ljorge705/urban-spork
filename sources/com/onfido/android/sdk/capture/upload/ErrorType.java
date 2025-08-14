package com.onfido.android.sdk.capture.upload;

import com.clevertap.android.sdk.Constants;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \t2\u00020\u0001:\u0011\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0010\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'¨\u0006("}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType;", "", Constants.KEY_KEY, "", "(Ljava/lang/String;)V", "getKey$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "Barcode", "Blur", "Companion", "Cutoff", "Document", "Generic", "Geoblocked", "Glare", "InvalidCertificate", "MultipleFaces", "Network", "NoFace", "PhotoOfScreen", "Photocopy", "Scan", "Screenshot", "TokenExpired", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Barcode;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Blur;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Cutoff;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Document;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Generic;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Geoblocked;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Glare;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$InvalidCertificate;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$MultipleFaces;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Network;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$NoFace;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$PhotoOfScreen;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Photocopy;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Scan;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$Screenshot;", "Lcom/onfido/android/sdk/capture/upload/ErrorType$TokenExpired;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class ErrorType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String key;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Barcode;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Barcode extends ErrorType {
        public static final Barcode INSTANCE = new Barcode();

        private Barcode() {
            super(OptionalModuleUtils.BARCODE, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Blur;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Blur extends ErrorType {
        public static final Blur INSTANCE = new Blur();

        private Blur() {
            super("blur", null);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Companion;", "", "()V", "of", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", Constants.KEY_KEY, "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final ErrorType of(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Network network = Network.INSTANCE;
            if (Intrinsics.areEqual(key, network.getKey())) {
                return network;
            }
            Document document = Document.INSTANCE;
            if (Intrinsics.areEqual(key, document.getKey())) {
                return document;
            }
            NoFace noFace = NoFace.INSTANCE;
            if (Intrinsics.areEqual(key, noFace.getKey())) {
                return noFace;
            }
            MultipleFaces multipleFaces = MultipleFaces.INSTANCE;
            if (Intrinsics.areEqual(key, multipleFaces.getKey())) {
                return multipleFaces;
            }
            Cutoff cutoff = Cutoff.INSTANCE;
            if (Intrinsics.areEqual(key, cutoff.getKey())) {
                return cutoff;
            }
            Glare glare = Glare.INSTANCE;
            if (Intrinsics.areEqual(key, glare.getKey())) {
                return glare;
            }
            Blur blur = Blur.INSTANCE;
            if (Intrinsics.areEqual(key, blur.getKey())) {
                return blur;
            }
            Barcode barcode = Barcode.INSTANCE;
            if (Intrinsics.areEqual(key, barcode.getKey())) {
                return barcode;
            }
            PhotoOfScreen photoOfScreen = PhotoOfScreen.INSTANCE;
            if (Intrinsics.areEqual(key, photoOfScreen.getKey())) {
                return photoOfScreen;
            }
            Screenshot screenshot = Screenshot.INSTANCE;
            if (Intrinsics.areEqual(key, screenshot.getKey())) {
                return screenshot;
            }
            Photocopy photocopy = Photocopy.INSTANCE;
            if (Intrinsics.areEqual(key, photocopy.getKey())) {
                return photocopy;
            }
            Scan scan = Scan.INSTANCE;
            if (Intrinsics.areEqual(key, scan.getKey())) {
                return scan;
            }
            Generic generic = Generic.INSTANCE;
            if (Intrinsics.areEqual(key, generic.getKey())) {
                return generic;
            }
            TokenExpired tokenExpired = TokenExpired.INSTANCE;
            if (Intrinsics.areEqual(key, tokenExpired.getKey())) {
                return tokenExpired;
            }
            Geoblocked geoblocked = Geoblocked.INSTANCE;
            if (Intrinsics.areEqual(key, geoblocked.getKey())) {
                return geoblocked;
            }
            if (Intrinsics.areEqual(key, new InvalidCertificate("").getKey())) {
                return new InvalidCertificate("");
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Cutoff;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Cutoff extends ErrorType {
        public static final Cutoff INSTANCE = new Cutoff();

        private Cutoff() {
            super("cutoff", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Document;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Document extends ErrorType {
        public static final Document INSTANCE = new Document();

        private Document() {
            super("document", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Generic;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Generic extends ErrorType {
        public static final Generic INSTANCE = new Generic();

        private Generic() {
            super("generic", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Geoblocked;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Geoblocked extends ErrorType {
        public static final Geoblocked INSTANCE = new Geoblocked();

        private Geoblocked() {
            super("geoblocked_request", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Glare;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Glare extends ErrorType {
        public static final Glare INSTANCE = new Glare();

        private Glare() {
            super("glare", null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$InvalidCertificate;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class InvalidCertificate extends ErrorType {
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InvalidCertificate(String message) {
            super("invalid_certificate", null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        public static /* synthetic */ InvalidCertificate copy$default(InvalidCertificate invalidCertificate, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = invalidCertificate.message;
            }
            return invalidCertificate.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final InvalidCertificate copy(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new InvalidCertificate(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof InvalidCertificate) && Intrinsics.areEqual(this.message, ((InvalidCertificate) other).message);
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return "InvalidCertificate(message=" + this.message + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$MultipleFaces;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MultipleFaces extends ErrorType {
        public static final MultipleFaces INSTANCE = new MultipleFaces();

        private MultipleFaces() {
            super("multiple_faces", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Network;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Network extends ErrorType {
        public static final Network INSTANCE = new Network();

        private Network() {
            super("network", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$NoFace;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoFace extends ErrorType {
        public static final NoFace INSTANCE = new NoFace();

        private NoFace() {
            super("no_face", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$PhotoOfScreen;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PhotoOfScreen extends ErrorType {
        public static final PhotoOfScreen INSTANCE = new PhotoOfScreen();

        private PhotoOfScreen() {
            super("odp-photo_of_screen", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Photocopy;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Photocopy extends ErrorType {
        public static final Photocopy INSTANCE = new Photocopy();

        private Photocopy() {
            super("odp-document_on_printed_paper", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Scan;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Scan extends ErrorType {
        public static final Scan INSTANCE = new Scan();

        private Scan() {
            super("odp-scan", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$Screenshot;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Screenshot extends ErrorType {
        public static final Screenshot INSTANCE = new Screenshot();

        private Screenshot() {
            super("odp-screenshot", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/ErrorType$TokenExpired;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class TokenExpired extends ErrorType {
        public static final TokenExpired INSTANCE = new TokenExpired();

        private TokenExpired() {
            super("token_expired", null);
        }
    }

    private ErrorType(String str) {
        this.key = str;
    }

    /* renamed from: getKey$onfido_capture_sdk_core_release, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public /* synthetic */ ErrorType(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
