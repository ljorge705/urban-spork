package com.drew.metadata.bmp;

import com.drew.metadata.TagDescriptor;
import com.drew.metadata.bmp.BmpHeaderDirectory;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class BmpHeaderDescriptor extends TagDescriptor<BmpHeaderDirectory> {
    public BmpHeaderDescriptor(BmpHeaderDirectory bmpHeaderDirectory) {
        super(bmpHeaderDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == -2) {
            return getBitmapTypeDescription();
        }
        if (i == 5) {
            return getCompressionDescription();
        }
        switch (i) {
            case 10:
                return getRenderingDescription();
            case 11:
                return getColorEncodingDescription();
            case 12:
            case 13:
            case 14:
            case 15:
                return formatHex(((BmpHeaderDirectory) this._directory).getLongObject(i), 8);
            case 16:
                return getColorSpaceTypeDescription();
            case 17:
            case 18:
            case 19:
                return formatFixed1616(((BmpHeaderDirectory) this._directory).getLongObject(i));
            case 20:
                return getRenderingIntentDescription();
            default:
                return super.getDescription(i);
        }
    }

    public String getBitmapTypeDescription() {
        BmpHeaderDirectory.BitmapType bitmapType = ((BmpHeaderDirectory) this._directory).getBitmapType();
        if (bitmapType == null) {
            return null;
        }
        return bitmapType.toString();
    }

    public String getCompressionDescription() {
        BmpHeaderDirectory.Compression compression = ((BmpHeaderDirectory) this._directory).getCompression();
        if (compression != null) {
            return compression.toString();
        }
        Integer integer = ((BmpHeaderDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        return "Illegal value 0x" + Integer.toHexString(integer.intValue());
    }

    public String getRenderingDescription() {
        BmpHeaderDirectory.RenderingHalftoningAlgorithm rendering = ((BmpHeaderDirectory) this._directory).getRendering();
        if (rendering == null) {
            return null;
        }
        return rendering.toString();
    }

    public String getColorEncodingDescription() {
        BmpHeaderDirectory.ColorEncoding colorEncoding = ((BmpHeaderDirectory) this._directory).getColorEncoding();
        if (colorEncoding == null) {
            return null;
        }
        return colorEncoding.toString();
    }

    public String getColorSpaceTypeDescription() {
        BmpHeaderDirectory.ColorSpaceType colorSpaceType = ((BmpHeaderDirectory) this._directory).getColorSpaceType();
        if (colorSpaceType == null) {
            return null;
        }
        return colorSpaceType.toString();
    }

    public String getRenderingIntentDescription() {
        BmpHeaderDirectory.RenderingIntent renderingIntent = ((BmpHeaderDirectory) this._directory).getRenderingIntent();
        if (renderingIntent == null) {
            return null;
        }
        return renderingIntent.toString();
    }

    public static String formatHex(Integer num, int i) {
        if (num == null) {
            return null;
        }
        return formatHex(num.intValue() & 4294967295L, i);
    }

    public static String formatHex(int i, int i2) {
        return formatHex(i & 4294967295L, i2);
    }

    public static String formatHex(Long l, int i) {
        if (l == null) {
            return null;
        }
        return formatHex(l.longValue(), i);
    }

    public static String formatHex(long j, int i) {
        return String.format("0x%0" + i + "X", Long.valueOf(j));
    }

    public static String formatFixed1616(Integer num) {
        if (num == null) {
            return null;
        }
        return formatFixed1616(num.intValue() & 4294967295L);
    }

    public static String formatFixed1616(int i) {
        return formatFixed1616(i & 4294967295L);
    }

    public static String formatFixed1616(Long l) {
        if (l == null) {
            return null;
        }
        return formatFixed1616(l.longValue());
    }

    public static String formatFixed1616(long j) {
        return new DecimalFormat("0.###").format(Double.valueOf(j / 65536.0d));
    }
}
