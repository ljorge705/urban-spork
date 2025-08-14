package kotlinx.serialization.internal;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Platform.common.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/internal/InternalHexConverter;", "", "()V", "hexCode", "", "hexToInt", "", "ch", "", "parseHexBinary", "", "s", "printHexBinary", "data", "lowerCase", "", "toHexString", "n", "kotlinx-serialization-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class InternalHexConverter {
    public static final InternalHexConverter INSTANCE = new InternalHexConverter();
    private static final String hexCode = "0123456789ABCDEF";

    private final int hexToInt(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        }
        if ('A' <= ch && ch <= 'F') {
            return ch - '7';
        }
        if ('a' > ch || ch > 'f') {
            return -1;
        }
        return ch - 'W';
    }

    private InternalHexConverter() {
    }

    public final byte[] parseHexBinary(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        int length = s.length();
        if (!(length % 2 == 0)) {
            throw new IllegalArgumentException("HexBinary string must be even length".toString());
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int iHexToInt = hexToInt(s.charAt(i));
            int i2 = i + 1;
            int iHexToInt2 = hexToInt(s.charAt(i2));
            if (!((iHexToInt == -1 || iHexToInt2 == -1) ? false : true)) {
                throw new IllegalArgumentException(("Invalid hex chars: " + s.charAt(i) + s.charAt(i2)).toString());
            }
            bArr[i / 2] = (byte) ((iHexToInt << 4) + iHexToInt2);
        }
        return bArr;
    }

    public static /* synthetic */ String printHexBinary$default(InternalHexConverter internalHexConverter, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return internalHexConverter.printHexBinary(bArr, z);
    }

    public final String printHexBinary(byte[] data, boolean lowerCase) {
        Intrinsics.checkNotNullParameter(data, "data");
        StringBuilder sb = new StringBuilder(data.length * 2);
        int length = data.length;
        int i = 0;
        while (i < length) {
            byte b = data[i];
            i++;
            sb.append(hexCode.charAt((b >> 4) & 15));
            sb.append(hexCode.charAt(b & 15));
        }
        if (!lowerCase) {
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "r.toString()");
            return string;
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "r.toString()");
        String lowerCase2 = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
        return lowerCase2;
    }

    public final String toHexString(int n2) {
        byte[] bArr = new byte[4];
        int i = 0;
        while (true) {
            int i2 = i + 1;
            bArr[i] = (byte) (n2 >> (24 - (i * 8)));
            if (i2 >= 4) {
                break;
            }
            i = i2;
        }
        String strTrimStart = StringsKt.trimStart(printHexBinary(bArr, true), '0');
        if (strTrimStart.length() <= 0) {
            strTrimStart = null;
        }
        return strTrimStart == null ? "0" : strTrimStart;
    }
}
