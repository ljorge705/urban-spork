package com.drew.metadata.mp3;

import com.adobe.internal.xmp.XMPConst;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;

/* loaded from: classes5.dex */
public class Mp3Reader {
    public static int getSyncSafeSize(int i) {
        int i2 = i & 255;
        int i3 = (i >> 8) & 255;
        int i4 = (i >> 16) & 255;
        return (((i >> 24) & 255) << 21) | i2 | (i3 << 7) | (i4 << 14);
    }

    public void extract(InputStream inputStream, Metadata metadata) throws ImageProcessingException, IOException {
        double d;
        Mp3Directory mp3Directory = new Mp3Directory();
        metadata.addDirectory(mp3Directory);
        try {
            inputStream.reset();
            int int32 = new StreamReader(inputStream).getInt32();
            int i = (1572864 & int32) >> 19;
            if (i == 0) {
                mp3Directory.setString(1, "MPEG-2.5");
                throw new ImageProcessingException("MPEG-2.5 not supported.");
            }
            if (i == 2) {
                mp3Directory.setString(1, "MPEG-2");
                d = 2.0d;
            } else if (i != 3) {
                d = 0.0d;
            } else {
                mp3Directory.setString(1, "MPEG-1");
                d = 1.0d;
            }
            int i2 = (393216 & int32) >> 17;
            if (i2 == 0) {
                mp3Directory.setString(2, "Not defined");
            } else if (i2 == 1) {
                mp3Directory.setString(2, "Layer III");
            } else if (i2 == 2) {
                mp3Directory.setString(2, "Layer II");
            } else if (i2 == 3) {
                mp3Directory.setString(2, "Layer I");
            }
            int i3 = (61440 & int32) >> 12;
            if (i3 != 0 && i3 != 15) {
                mp3Directory.setInt(3, setBitrate(i3, i2, d));
            }
            int i4 = (int32 & CipherStorageKeystoreRsaEcb.ENCRYPTION_KEY_SIZE) >> 10;
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, 3);
            int[] iArr2 = {MicAvailabilityHelper.SAMPLE_RATE_HZ, 48000, 32000};
            iArr[0] = iArr2;
            int[] iArr3 = {22050, 24000, 16000};
            iArr[1] = iArr3;
            if (d == 2.0d) {
                mp3Directory.setInt(4, iArr3[i4]);
                i4 = iArr[1][i4];
            } else if (d == 1.0d) {
                mp3Directory.setInt(4, iArr2[i4]);
                i4 = iArr[0][i4];
            }
            int i5 = (int32 & 192) >> 6;
            if (i5 == 0) {
                mp3Directory.setString(5, "Stereo");
            } else if (i5 == 1) {
                mp3Directory.setString(5, "Joint stereo");
            } else if (i5 == 2) {
                mp3Directory.setString(5, "Dual channel");
            } else if (i5 == 3) {
                mp3Directory.setString(5, "Mono");
            }
            int i6 = (int32 & 8) >> 3;
            if (i6 == 0) {
                mp3Directory.setString(7, XMPConst.FALSESTR);
            } else if (i6 == 1) {
                mp3Directory.setString(7, XMPConst.TRUESTR);
            }
            int i7 = int32 & 3;
            if (i7 == 0) {
                mp3Directory.setString(6, "none");
            } else if (i7 == 1) {
                mp3Directory.setString(6, "50/15ms");
            } else if (i7 == 3) {
                mp3Directory.setString(6, "CCITT j.17");
            }
            mp3Directory.setString(8, ((setBitrate(i3, i2, d) * 144000) / i4) + " bytes");
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public int setBitrate(int i, int i2, double d) {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 14, 6);
        char c = 0;
        iArr[0] = new int[]{32, 32, 32, 32, 32, 8};
        iArr[1] = new int[]{64, 48, 40, 64, 48, 16};
        iArr[2] = new int[]{96, 56, 48, 96, 56, 24};
        iArr[3] = new int[]{128, 64, 56, 128, 64, 32};
        iArr[4] = new int[]{160, 80, 64, 160, 80, 64};
        iArr[5] = new int[]{192, 96, 80, 192, 96, 80};
        iArr[6] = new int[]{224, 112, 96, 224, 112, 56};
        iArr[7] = new int[]{256, 128, 112, 256, 128, 64};
        iArr[8] = new int[]{288, 160, 128, 28, 160, 128};
        iArr[9] = new int[]{320, 192, 160, 320, 192, 160};
        iArr[10] = new int[]{352, 224, 192, 352, 224, 112};
        iArr[11] = new int[]{384, 256, 224, 384, 256, 128};
        iArr[12] = new int[]{DilithiumEngine.DilithiumPolyT0PackedBytes, 320, 256, DilithiumEngine.DilithiumPolyT0PackedBytes, 320, 256};
        iArr[13] = new int[]{CaptureActivity.RESULT_EXIT_USER_FLOW, 384, 320, CaptureActivity.RESULT_EXIT_USER_FLOW, 384, 320};
        int i3 = i - 1;
        if (d == 2.0d) {
            if (i2 == 1) {
                c = 5;
            } else if (i2 == 2) {
                c = 4;
            } else if (i2 == 3) {
                c = 3;
            }
        } else if (d == 1.0d) {
            if (i2 == 1) {
                c = 2;
            } else if (i2 == 2) {
                c = 1;
            }
        }
        return iArr[i3][c];
    }
}
