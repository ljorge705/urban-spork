package org.bouncycastle.pqc.crypto.hqc;

import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.facebook.imageutils.JfifUtil;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jmrtd.PassportService;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes4.dex */
class GFCalculator {
    static int[] exp = {1, 2, 4, 8, 16, 32, 64, 128, 29, 58, 116, 232, 205, 135, 19, 38, 76, 152, 45, 90, 180, LDSFile.EF_DG2_TAG, 234, 201, 143, 3, 6, 12, 24, 48, 96, 192, 157, 39, 78, 156, 37, 74, 148, 53, 106, Mp4VideoDirectory.TAG_OPCOLOR, 181, 119, 238, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 159, 35, 70, 140, 5, 10, 20, 40, 80, 160, 93, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 105, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, 185, 111, 222, 161, 95, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 97, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 153, 47, 94, 188, 101, 202, 137, 15, 30, 60, 120, FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH, 253, 231, 211, 187, 107, Mp4VideoDirectory.TAG_FRAME_RATE, 177, 127, 254, JfifUtil.MARKER_APP1, PassportService.DEFAULT_MAX_BLOCKSIZE, 163, 91, 182, 113, 226, JfifUtil.MARKER_EOI, 175, 67, 134, 17, 34, 68, 136, 13, 26, 52, 104, 208, 189, 103, 206, 129, 31, 62, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, 248, 237, 199, 147, 59, LDSFile.EF_DG4_TAG, 236, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 151, 51, 102, 204, 133, 23, 46, 92, 184, 109, JfifUtil.MARKER_SOS, 169, 79, 158, 33, 66, 132, 21, 42, 84, 168, 77, 154, 41, 82, 164, 85, 170, 73, 146, 57, 114, 228, Mp4VideoDirectory.TAG_COLOR_TABLE, 183, ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, 230, Mp4VideoDirectory.TAG_DEPTH, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 99, 198, 145, 63, WebSocketProtocol.PAYLOAD_SHORT, 252, 229, JfifUtil.MARKER_RST7, 179, 123, 246, 241, 255, 227, 219, 171, 75, 150, 49, 98, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 149, 55, LDSFile.EF_DG14_TAG, 220, 165, 87, 174, 65, 130, 25, 50, 100, 200, 141, 7, 14, 28, 56, 112, 224, 221, 167, 83, 166, 81, 162, 89, 178, PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, 242, 249, 239, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 155, 43, 86, 172, 69, 138, 9, 18, 36, 72, 144, 61, 122, 244, 245, 247, 243, 251, 235, 203, 139, 11, 22, 44, 88, 176, ISO781611.SMT_TAG, 250, 233, 207, 131, 27, 54, 108, JfifUtil.MARKER_SOI, 173, 71, 142, 1, 2, 4};
    static int[] log = {0, 0, 1, 25, 2, 50, 26, 198, 3, PassportService.DEFAULT_MAX_BLOCKSIZE, 51, 238, 27, 104, 199, 75, 4, 100, 224, 14, 52, 141, 239, 129, 28, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 105, 248, 200, 8, 76, 113, 5, 138, 101, 47, JfifUtil.MARKER_APP1, 36, 15, 33, 53, 147, 142, JfifUtil.MARKER_SOS, FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH, 18, 130, 69, 29, 181, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, ISO781611.SMT_TAG, 106, 39, 249, 185, 201, 154, 9, 120, 77, 228, 114, 166, 6, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 139, 98, 102, 221, 48, 253, 226, 152, 37, 179, 16, 145, 34, 136, 54, 208, 148, 206, 143, 150, 219, 189, 241, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, 19, 92, 131, 56, 70, 64, 30, 66, 182, 163, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 72, WebSocketProtocol.PAYLOAD_SHORT, LDSFile.EF_DG14_TAG, 107, 58, 40, 84, 250, 133, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 61, 202, 94, 155, 159, 10, 21, PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, 43, 78, Mp4VideoDirectory.TAG_OPCOLOR, 229, 172, ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, 243, 167, 87, 7, 112, 192, 247, 140, 128, 99, 13, 103, 74, 222, 237, 49, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 254, 24, 227, 165, 153, 119, 38, 184, 180, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, 17, 68, 146, JfifUtil.MARKER_EOI, 35, 32, 137, 46, 55, 63, Mp4VideoDirectory.TAG_DEPTH, 91, 149, 188, 207, 205, 144, 135, 151, 178, 220, 252, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 97, 242, 86, 211, 171, 20, 42, 93, 158, 132, 60, 57, 83, 71, 109, 65, 162, 31, 45, 67, JfifUtil.MARKER_SOI, 183, 123, 164, LDSFile.EF_DG4_TAG, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 23, 73, 236, 127, 12, 111, 246, 108, 161, 59, 82, 41, 157, 85, 170, 251, 96, 134, 177, 187, 204, 62, 90, 203, 89, 95, 176, 156, 169, 160, 81, 11, 245, 22, 235, 122, LDSFile.EF_DG2_TAG, 44, JfifUtil.MARKER_RST7, 79, 174, Mp4VideoDirectory.TAG_COLOR_TABLE, 233, 230, 231, 173, 232, 116, Mp4VideoDirectory.TAG_FRAME_RATE, 244, 234, 168, 80, 88, 175};

    GFCalculator() {
    }

    static int inverse(int i) {
        return exp[255 - log[i]] & Utils.toUnsigned16Bits((-i) >> 31);
    }

    static int mod(int i) {
        int unsigned16Bits = Utils.toUnsigned16Bits(i - 255);
        return Utils.toUnsigned16Bits(unsigned16Bits + (Utils.toUnsigned8bits(-(unsigned16Bits >> 15)) & 255));
    }

    static int mult(int i, int i2) {
        int unsigned16Bits = Utils.toUnsigned16Bits((-i) >> 31) & Utils.toUnsigned16Bits((-i2) >> 31);
        int[] iArr = exp;
        int[] iArr2 = log;
        return Utils.toUnsigned16Bits(iArr[mod(iArr2[i] + iArr2[i2])] & unsigned16Bits);
    }
}
