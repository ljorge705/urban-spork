package androidx.camera.video.internal.workaround;

import android.media.MediaCodec;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.text.TextUtils;
import androidx.camera.core.Logger;
import androidx.camera.video.internal.DebugUtils;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.camera.video.internal.compat.quirk.MediaCodecInfoReportIncorrectInfoQuirk;
import androidx.camera.video.internal.compat.quirk.MediaFormatMustNotUseFrameRateToFindEncoderQuirk;
import androidx.camera.video.internal.encoder.InvalidConfigException;
import java.io.IOException;

/* loaded from: classes.dex */
public class EncoderFinder {
    private static final String TAG = "EncoderFinder";
    private final boolean mShouldRemoveKeyFrameRate;

    public EncoderFinder() {
        this.mShouldRemoveKeyFrameRate = ((MediaFormatMustNotUseFrameRateToFindEncoderQuirk) DeviceQuirks.get(MediaFormatMustNotUseFrameRateToFindEncoderQuirk.class)) != null;
    }

    public MediaCodec findEncoder(MediaFormat mediaFormat) throws Throwable {
        MediaCodecList mediaCodecList = new MediaCodecList(1);
        String strFindEncoderForFormat = findEncoderForFormat(mediaFormat, mediaCodecList);
        try {
            if (TextUtils.isEmpty(strFindEncoderForFormat)) {
                String string = mediaFormat.getString("mime");
                MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(string);
                Logger.w(TAG, String.format("No encoder found that supports requested MediaFormat %s. Create encoder by MIME type. Dump codec info:\n%s", mediaFormat, DebugUtils.dumpCodecCapabilities(string, mediaCodecCreateEncoderByType, mediaFormat)));
                return mediaCodecCreateEncoderByType;
            }
            return MediaCodec.createByCodecName(strFindEncoderForFormat);
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            throw new InvalidConfigException("Encoder cannot created: " + strFindEncoderForFormat + ", isMediaFormatInQuirk: " + shouldCreateCodecByType(mediaFormat) + "\n" + DebugUtils.dumpMediaCodecListForFormat(mediaCodecList, mediaFormat), e);
        }
    }

    String findEncoderForFormat(MediaFormat mediaFormat, MediaCodecList mediaCodecList) throws Throwable {
        Integer num = null;
        try {
            if (this.mShouldRemoveKeyFrameRate && mediaFormat.containsKey("frame-rate")) {
                Integer numValueOf = Integer.valueOf(mediaFormat.getInteger("frame-rate"));
                try {
                    mediaFormat.setString("frame-rate", null);
                    num = numValueOf;
                } catch (Throwable th) {
                    th = th;
                    num = numValueOf;
                    if (num != null) {
                        mediaFormat.setInteger("frame-rate", num.intValue());
                    }
                    throw th;
                }
            }
            String strFindEncoderForFormat = mediaCodecList.findEncoderForFormat(mediaFormat);
            if (strFindEncoderForFormat == null) {
                strFindEncoderForFormat = findEncoderWithNearestCompatibleBitrate(mediaFormat, mediaCodecList.getCodecInfos());
            }
            if (num != null) {
                mediaFormat.setInteger("frame-rate", num.intValue());
            }
            return strFindEncoderForFormat;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009d A[PHI: r11
      0x009d: PHI (r11v2 java.lang.Integer) = (r11v1 java.lang.Integer), (r11v7 java.lang.Integer) binds: [B:39:0x009b, B:30:0x0089] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String findEncoderWithNearestCompatibleBitrate(android.media.MediaFormat r14, android.media.MediaCodecInfo[] r15) throws java.lang.Throwable {
        /*
            r13 = this;
            java.lang.String r0 = "bitrate"
            java.lang.String r1 = "mime"
            java.lang.String r1 = r14.getString(r1)
            java.lang.String r2 = "EncoderFinder"
            r3 = 0
            if (r1 != 0) goto L13
            java.lang.String r14 = "MediaFormat does not contain mime info."
            androidx.camera.core.Logger.w(r2, r14)
            return r3
        L13:
            int r4 = r15.length
            r5 = 0
            r6 = r5
        L16:
            if (r6 >= r4) goto La8
            r7 = r15[r6]
            boolean r8 = r7.isEncoder()
            if (r8 != 0) goto L22
            goto La4
        L22:
            android.media.MediaCodecInfo$CodecCapabilities r8 = r7.getCapabilitiesForType(r1)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            r9 = 1
            if (r8 == 0) goto L2b
            r10 = r9
            goto L2c
        L2b:
            r10 = r5
        L2c:
            java.lang.String r11 = "MIME type is not supported"
            androidx.core.util.Preconditions.checkArgument(r10, r11)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            boolean r10 = r14.containsKey(r0)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            if (r10 == 0) goto L5f
            android.media.MediaCodecInfo$VideoCapabilities r10 = r8.getVideoCapabilities()     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            if (r10 == 0) goto L3f
            r11 = r9
            goto L40
        L3f:
            r11 = r5
        L40:
            java.lang.String r12 = "Not video codec"
            androidx.core.util.Preconditions.checkArgument(r11, r12)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            int r11 = r14.getInteger(r0)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> L8f java.lang.IllegalArgumentException -> L9a
            android.util.Range r10 = r10.getBitrateRange()     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            java.lang.Comparable r10 = r10.clamp(r11)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            int r10 = r10.intValue()     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            r14.setInteger(r0, r10)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            goto L61
        L5f:
            r10 = -1
            r11 = r3
        L61:
            boolean r8 = r8.isFormatSupported(r14)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            if (r8 == 0) goto L89
            java.lang.String r8 = "No encoder found that supports requested bitrate. Adjusting bitrate to nearest supported bitrate [requested: %dbps, nearest: %dbps]"
            r12 = 2
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            r12[r5] = r11     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            r12[r9] = r10     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            java.lang.String r8 = java.lang.String.format(r8, r12)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            androidx.camera.core.Logger.w(r2, r8)     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            java.lang.String r15 = r7.getName()     // Catch: java.lang.Throwable -> L8c java.lang.IllegalArgumentException -> L9b
            if (r11 == 0) goto L88
            int r1 = r11.intValue()
            r14.setInteger(r0, r1)
        L88:
            return r15
        L89:
            if (r11 == 0) goto La4
            goto L9d
        L8c:
            r15 = move-exception
            r3 = r11
            goto L90
        L8f:
            r15 = move-exception
        L90:
            if (r3 == 0) goto L99
            int r1 = r3.intValue()
            r14.setInteger(r0, r1)
        L99:
            throw r15
        L9a:
            r11 = r3
        L9b:
            if (r11 == 0) goto La4
        L9d:
            int r7 = r11.intValue()
            r14.setInteger(r0, r7)
        La4:
            int r6 = r6 + 1
            goto L16
        La8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.workaround.EncoderFinder.findEncoderWithNearestCompatibleBitrate(android.media.MediaFormat, android.media.MediaCodecInfo[]):java.lang.String");
    }

    private boolean shouldCreateCodecByType(MediaFormat mediaFormat) {
        MediaCodecInfoReportIncorrectInfoQuirk mediaCodecInfoReportIncorrectInfoQuirk = (MediaCodecInfoReportIncorrectInfoQuirk) DeviceQuirks.get(MediaCodecInfoReportIncorrectInfoQuirk.class);
        if (mediaCodecInfoReportIncorrectInfoQuirk == null) {
            return false;
        }
        return mediaCodecInfoReportIncorrectInfoQuirk.isUnSupportMediaCodecInfo(mediaFormat);
    }
}
