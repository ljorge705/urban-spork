package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzhe {
    private static final zzhb zza;

    static {
        if (zzgz.zzx() && zzgz.zzy()) {
            int i = zzcn.zza;
        }
        zza = new zzhc();
    }

    static /* synthetic */ int zzc(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            if (b > -12) {
                return -1;
            }
            return b;
        }
        if (i3 == 1) {
            return zzj(b, bArr[i]);
        }
        if (i3 == 2) {
            return zzk(b, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    static int zzd(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        char cCharAt;
        int length = charSequence.length();
        int i5 = i2 + i;
        int i6 = 0;
        while (i6 < length && (i4 = i6 + i) < i5 && (cCharAt = charSequence.charAt(i6)) < 128) {
            bArr[i4] = (byte) cCharAt;
            i6++;
        }
        if (i6 == length) {
            return i + length;
        }
        int i7 = i + i6;
        while (i6 < length) {
            char cCharAt2 = charSequence.charAt(i6);
            if (cCharAt2 < 128 && i7 < i5) {
                bArr[i7] = (byte) cCharAt2;
                i7++;
            } else if (cCharAt2 < 2048 && i7 <= i5 - 2) {
                int i8 = i7 + 1;
                bArr[i7] = (byte) ((cCharAt2 >>> 6) | 960);
                i7 += 2;
                bArr[i8] = (byte) ((cCharAt2 & '?') | 128);
            } else {
                if ((cCharAt2 >= 55296 && cCharAt2 <= 57343) || i7 > i5 - 3) {
                    if (i7 > i5 - 4) {
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343 && ((i3 = i6 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                            throw new zzhd(i6, length);
                        }
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Failed writing ");
                        sb.append(cCharAt2);
                        sb.append(" at index ");
                        sb.append(i7);
                        throw new ArrayIndexOutOfBoundsException(sb.toString());
                    }
                    int i9 = i6 + 1;
                    if (i9 != charSequence.length()) {
                        char cCharAt3 = charSequence.charAt(i9);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            bArr[i7] = (byte) ((codePoint >>> 18) | FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH);
                            bArr[i7 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i10 = i7 + 3;
                            bArr[i7 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 += 4;
                            bArr[i10] = (byte) ((codePoint & 63) | 128);
                            i6 = i9;
                        } else {
                            i6 = i9;
                        }
                    }
                    throw new zzhd(i6 - 1, length);
                }
                bArr[i7] = (byte) ((cCharAt2 >>> '\f') | DefaultFrameSampler.DESIRED_FRAME_WIDTH);
                int i11 = i7 + 2;
                bArr[i7 + 1] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                i7 += 3;
                bArr[i11] = (byte) ((cCharAt2 & '?') | 128);
            }
            i6++;
        }
        return i7;
    }

    static int zze(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new zzhd(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(i3 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    static int zzf(int i, byte[] bArr, int i2, int i3) {
        return zza.zza(i, bArr, i2, i3);
    }

    static String zzg(byte[] bArr, int i, int i2) throws zzen {
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte b = bArr[i];
            if (!zzha.zzd(b)) {
                break;
            }
            i++;
            cArr[i4] = (char) b;
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            if (zzha.zzd(b2)) {
                cArr[i5] = (char) b2;
                i5++;
                i = i6;
                while (i < i3) {
                    byte b3 = bArr[i];
                    if (!zzha.zzd(b3)) {
                        break;
                    }
                    i++;
                    cArr[i5] = (char) b3;
                    i5++;
                }
            } else if (b2 < -32) {
                if (i6 >= i3) {
                    throw zzen.zzc();
                }
                i += 2;
                zzha.zzb(b2, bArr[i6], cArr, i5);
                i5++;
            } else if (b2 < -16) {
                if (i6 >= i3 - 1) {
                    throw zzen.zzc();
                }
                int i7 = i + 2;
                i += 3;
                zzha.zzc(b2, bArr[i6], bArr[i7], cArr, i5);
                i5++;
            } else {
                if (i6 >= i3 - 2) {
                    throw zzen.zzc();
                }
                int i8 = i + 2;
                int i9 = i + 3;
                i += 4;
                zzha.zza(b2, bArr[i6], bArr[i8], bArr[i9], cArr, i5);
                i5 += 2;
            }
        }
        return new String(cArr, 0, i5);
    }

    static boolean zzh(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    static boolean zzi(byte[] bArr, int i, int i2) {
        return zza.zzb(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzj(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzk(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }
}
