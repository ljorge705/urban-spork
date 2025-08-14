package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class AuxiliaryTypeProperty extends FullBox {
    int[] auxSubtype;
    String auxType;

    public AuxiliaryTypeProperty(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.auxType = getZeroTerminatedString(((int) box.size) - 12, sequentialReader);
    }

    private String getZeroTerminatedString(int i, SequentialReader sequentialReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append((char) sequentialReader.getByte());
            if (sb.charAt(sb.length() - 1) == 0) {
                break;
            }
        }
        return sb.toString().trim();
    }
}
