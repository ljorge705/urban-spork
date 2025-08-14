package com.drew.metadata.iptc;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public final class Iso2022Converter {
    private static final int DOT = 14844066;
    private static final byte ESC = 27;
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final byte LATIN_CAPITAL_A = 65;
    private static final byte LATIN_CAPITAL_G = 71;
    private static final byte PERCENT_SIGN = 37;
    private static final String UTF_8 = "UTF-8";

    public static String convertISO2022CharsetToJavaCharset(byte[] bArr) {
        if (bArr.length > 2 && bArr[0] == 27 && bArr[1] == 37 && bArr[2] == 71) {
            return "UTF-8";
        }
        if (bArr.length > 3 && bArr[0] == 27 && ((bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16)) == DOT && bArr[4] == 65) {
            return "ISO-8859-1";
        }
        return null;
    }

    static Charset guessCharSet(byte[] bArr) throws CharacterCodingException {
        String[] strArr = {"UTF-8", System.getProperty("file.encoding"), "ISO-8859-1"};
        for (int i = 0; i < 3; i++) {
            Charset charsetForName = Charset.forName(strArr[i]);
            try {
                charsetForName.newDecoder().decode(ByteBuffer.wrap(bArr));
                return charsetForName;
            } catch (CharacterCodingException unused) {
            }
        }
        return null;
    }

    private Iso2022Converter() {
    }
}
