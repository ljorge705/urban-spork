package ckoaurzatqnuxckmholu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
abstract class fdwipeifdmvqsbqrrpyp {
    public static byte[] yvrzbryuycempgkdhpvj(String str) {
        return str == null ? new byte[0] : yvrzbryuycempgkdhpvj(str.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] yvrzbryuycempgkdhpvj(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return null;
        }
    }
}
