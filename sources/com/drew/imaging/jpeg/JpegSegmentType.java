package com.drew.imaging.jpeg;

import java.util.ArrayList;
import java.util.Collection;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes5.dex */
public enum JpegSegmentType {
    APP0(ISO7816.INS_CREATE_FILE, true),
    APP1((byte) -31, true),
    APP2(ISO7816.INS_APPEND_RECORD, true),
    APP3((byte) -29, true),
    APP4(ISO7816.INS_DELETE_FILE, true),
    APP5((byte) -27, true),
    APP6((byte) -26, true),
    APP7((byte) -25, true),
    APP8((byte) -24, true),
    APP9((byte) -23, true),
    APPA((byte) -22, true),
    APPB((byte) -21, true),
    APPC((byte) -20, true),
    APPD((byte) -19, true),
    APPE((byte) -18, true),
    APPF((byte) -17, true),
    SOI(ISO7816.INS_LOAD_KEY_FILE, false),
    DQT((byte) -37, false),
    DNL(ISO7816.INS_UPDATE_RECORD, false),
    DRI((byte) -35, false),
    DHP((byte) -34, false),
    EXP((byte) -33, false),
    DHT((byte) -60, false),
    DAC((byte) -52, false),
    SOF0(ISO7816.INS_GET_RESPONSE, true),
    SOF1((byte) -63, true),
    SOF2(ISO7816.INS_ENVELOPE, true),
    SOF3((byte) -61, true),
    SOF5((byte) -59, true),
    SOF6((byte) -58, true),
    SOF7((byte) -57, true),
    JPG((byte) -56, true),
    SOF9((byte) -55, true),
    SOF10(ISO7816.INS_GET_DATA, true),
    SOF11((byte) -53, true),
    SOF13((byte) -51, true),
    SOF14((byte) -50, true),
    SOF15((byte) -49, true),
    COM((byte) -2, true);

    public static final Collection<JpegSegmentType> canContainMetadataTypes;
    public final byte byteValue;
    public final boolean canContainMetadata;

    static {
        ArrayList arrayList = new ArrayList();
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.canContainMetadata) {
                arrayList.add(jpegSegmentType);
            }
        }
        canContainMetadataTypes = arrayList;
    }

    JpegSegmentType(byte b, boolean z) {
        this.byteValue = b;
        this.canContainMetadata = z;
    }

    public static JpegSegmentType fromByte(byte b) {
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.byteValue == b) {
                return jpegSegmentType;
            }
        }
        return null;
    }
}
