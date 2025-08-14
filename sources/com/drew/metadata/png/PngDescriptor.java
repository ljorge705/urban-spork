package com.drew.metadata.png;

import androidx.exifinterface.media.ExifInterface;
import com.drew.imaging.png.PngColorType;
import com.drew.lang.KeyValuePair;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;
import java.util.List;

/* loaded from: classes5.dex */
public class PngDescriptor extends TagDescriptor<PngDirectory> {
    public PngDescriptor(PngDirectory pngDirectory) {
        super(pngDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 4) {
            return getColorTypeDescription();
        }
        if (i == 5) {
            return getCompressionTypeDescription();
        }
        if (i == 6) {
            return getFilterMethodDescription();
        }
        if (i == 7) {
            return getInterlaceMethodDescription();
        }
        if (i == 9) {
            return getPaletteHasTransparencyDescription();
        }
        if (i == 10) {
            return getIsSrgbColorSpaceDescription();
        }
        if (i == 13) {
            return getTextualDataDescription();
        }
        if (i == 15) {
            return getBackgroundColorDescription();
        }
        if (i == 18) {
            return getUnitSpecifierDescription();
        }
        return super.getDescription(i);
    }

    public String getColorTypeDescription() {
        PngColorType pngColorTypeFromNumericValue;
        Integer integer = ((PngDirectory) this._directory).getInteger(4);
        if (integer == null || (pngColorTypeFromNumericValue = PngColorType.fromNumericValue(integer.intValue())) == null) {
            return null;
        }
        return pngColorTypeFromNumericValue.getDescription();
    }

    public String getCompressionTypeDescription() {
        return getIndexedDescription(5, "Deflate");
    }

    public String getFilterMethodDescription() {
        return getIndexedDescription(6, "Adaptive");
    }

    public String getInterlaceMethodDescription() {
        return getIndexedDescription(7, "No Interlace", "Adam7 Interlace");
    }

    public String getPaletteHasTransparencyDescription() {
        return getIndexedDescription(9, null, "Yes");
    }

    public String getIsSrgbColorSpaceDescription() {
        return getIndexedDescription(10, "Perceptual", "Relative Colorimetric", ExifInterface.TAG_SATURATION, "Absolute Colorimetric");
    }

    public String getUnitSpecifierDescription() {
        return getIndexedDescription(18, "Unspecified", "Metres");
    }

    public String getTextualDataDescription() {
        Object object = ((PngDirectory) this._directory).getObject(13);
        if (object == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (KeyValuePair keyValuePair : (List) object) {
            if (sb.length() != 0) {
                sb.append('\n');
            }
            sb.append(String.format("%s: %s", keyValuePair.getKey(), keyValuePair.getValue()));
        }
        return sb.toString();
    }

    public String getBackgroundColorDescription() {
        byte[] byteArray = ((PngDirectory) this._directory).getByteArray(15);
        if (byteArray == null) {
            return null;
        }
        SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(byteArray);
        try {
            int length = byteArray.length;
            if (length == 1) {
                return String.format("Palette Index %d", Short.valueOf(sequentialByteArrayReader.getUInt8()));
            }
            if (length == 2) {
                return String.format("Greyscale Level %d", Integer.valueOf(sequentialByteArrayReader.getUInt16()));
            }
            if (length != 6) {
                return null;
            }
            return String.format("R %d, G %d, B %d", Integer.valueOf(sequentialByteArrayReader.getUInt16()), Integer.valueOf(sequentialByteArrayReader.getUInt16()), Integer.valueOf(sequentialByteArrayReader.getUInt16()));
        } catch (IOException unused) {
            return null;
        }
    }
}
