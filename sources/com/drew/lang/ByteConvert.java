package com.drew.lang;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;

/* loaded from: classes5.dex */
public class ByteConvert {
    public static int toInt32BigEndian(byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & 65280);
    }

    public static int toInt32LittleEndian(byte[] bArr) {
        return ((bArr[3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680);
    }
}
