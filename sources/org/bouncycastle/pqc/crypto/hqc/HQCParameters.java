package org.bouncycastle.pqc.crypto.hqc;

import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.facebook.imageutils.JfifUtil;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import org.bouncycastle.pqc.crypto.KEMParameters;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes4.dex */
public class HQCParameters implements KEMParameters {
    static final int GF_MUL_ORDER = 255;
    static final int PARAM_M = 8;
    public static final HQCParameters hqc128 = new HQCParameters("hqc-128", 17669, 46, 384, 16, 31, 15, 66, 75, 75, 16767881, 4, new int[]{89, 69, 153, 116, 176, LDSFile.EF_DG2_TAG, 111, 75, 73, 233, 242, 233, 65, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, 21, 139, 103, 173, 67, LDSFile.EF_DG4_TAG, 105, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, 174, LDSFile.EF_DG14_TAG, 74, 69, 228, 82, 255, 181, 1});
    public static final HQCParameters hqc192 = new HQCParameters("hqc-192", 35851, 56, OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, 24, 33, 16, 100, 114, 114, 16742417, 5, new int[]{45, JfifUtil.MARKER_SOI, 239, 24, 253, 104, 27, 40, 107, 50, 163, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, 227, 134, 224, 158, 119, 13, 158, 1, 238, 164, 82, 43, 15, 232, 246, 142, 50, 189, 29, 232, 1});
    public static final HQCParameters hqc256 = new HQCParameters("hqc-256", 57637, 90, OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, 32, 59, 29, 131, 149, 149, 16772367, 5, new int[]{49, 167, 49, 39, 200, PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, 91, FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH, 63, 148, 71, 150, 123, 87, 101, 32, JfifUtil.MARKER_RST7, 159, 71, 201, ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, 97, Mp4VideoDirectory.TAG_COMPRESSION_TYPE, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 183, 141, JfifUtil.MARKER_EOI, 123, 12, 31, 243, 180, 219, 152, 239, 99, 141, 4, 246, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 144, 8, 232, 47, 27, 141, 178, 130, 64, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, 47, 39, 188, JfifUtil.MARKER_SOI, 48, 199, 187, 1});
    private int delta;
    private int fft;
    private int g;
    private int[] generatorPoly;
    private HQCEngine hqcEngine;
    private int k;

    /* renamed from: n, reason: collision with root package name */
    private int f351n;
    private int n1;
    private int n2;
    private final String name;
    private int utilRejectionThreshold;
    private int w;
    private int we;
    private int wr;

    private HQCParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        this.name = str;
        this.f351n = i;
        this.n1 = i2;
        this.n2 = i3;
        this.k = i4;
        this.delta = i6;
        this.w = i7;
        this.wr = i8;
        this.we = i9;
        this.generatorPoly = iArr;
        this.g = i5;
        this.utilRejectionThreshold = i10;
        this.fft = i11;
        this.hqcEngine = new HQCEngine(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, iArr);
    }

    int getDelta() {
        return this.delta;
    }

    HQCEngine getEngine() {
        return this.hqcEngine;
    }

    int getK() {
        return this.k;
    }

    int getN() {
        return this.f351n;
    }

    int getN1() {
        return this.n1;
    }

    int getN1N2_BYTES() {
        return ((this.n1 * this.n2) + 7) / 8;
    }

    int getN2() {
        return this.n2;
    }

    int getN_BYTES() {
        return (this.f351n + 7) / 8;
    }

    public String getName() {
        return this.name;
    }

    int getSALT_SIZE_BYTES() {
        return 16;
    }

    int getSHA512_BYTES() {
        return 64;
    }

    public int getSessionKeySize() {
        return this.k * 8;
    }

    int getW() {
        return this.w;
    }

    int getWe() {
        return this.we;
    }

    int getWr() {
        return this.wr;
    }
}
