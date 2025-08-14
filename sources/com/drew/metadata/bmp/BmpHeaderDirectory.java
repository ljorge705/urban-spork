package com.drew.metadata.bmp;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import com.oblador.keychain.KeychainModule;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class BmpHeaderDirectory extends Directory {
    public static final int TAG_ALPHA_MASK = 15;
    public static final int TAG_BITMAP_TYPE = -2;
    public static final int TAG_BITS_PER_PIXEL = 4;
    public static final int TAG_BLUE_MASK = 14;
    public static final int TAG_COLOR_ENCODING = 11;
    public static final int TAG_COLOR_SPACE_TYPE = 16;
    public static final int TAG_COLOUR_PLANES = 3;
    public static final int TAG_COMPRESSION = 5;
    public static final int TAG_GAMMA_BLUE = 19;
    public static final int TAG_GAMMA_GREEN = 18;
    public static final int TAG_GAMMA_RED = 17;
    public static final int TAG_GREEN_MASK = 13;
    public static final int TAG_HEADER_SIZE = -1;
    public static final int TAG_IMAGE_HEIGHT = 1;
    public static final int TAG_IMAGE_WIDTH = 2;
    public static final int TAG_IMPORTANT_COLOUR_COUNT = 9;
    public static final int TAG_INTENT = 20;
    public static final int TAG_LINKED_PROFILE = 21;
    public static final int TAG_PALETTE_COLOUR_COUNT = 8;
    public static final int TAG_RED_MASK = 12;
    public static final int TAG_RENDERING = 10;
    public static final int TAG_X_PIXELS_PER_METER = 6;
    public static final int TAG_Y_PIXELS_PER_METER = 7;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "BMP Header";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(-2, "Bitmap type");
        map.put(-1, "Header Size");
        map.put(1, "Image Height");
        map.put(2, "Image Width");
        map.put(3, "Planes");
        map.put(4, "Bits Per Pixel");
        map.put(5, ExifInterface.TAG_COMPRESSION);
        map.put(6, "X Pixels per Meter");
        map.put(7, "Y Pixels per Meter");
        map.put(8, "Palette Colour Count");
        map.put(9, "Important Colour Count");
        map.put(10, "Rendering");
        map.put(11, "Color Encoding");
        map.put(12, "Red Mask");
        map.put(13, "Green Mask");
        map.put(14, "Blue Mask");
        map.put(15, "Alpha Mask");
        map.put(16, "Color Space Type");
        map.put(17, "Red Gamma Curve");
        map.put(18, "Green Gamma Curve");
        map.put(19, "Blue Gamma Curve");
        map.put(20, "Rendering Intent");
        map.put(21, "Linked Profile File Name");
    }

    public BmpHeaderDirectory() {
        setDescriptor(new BmpHeaderDescriptor(this));
    }

    public BitmapType getBitmapType() {
        Integer integer = getInteger(-2);
        if (integer == null) {
            return null;
        }
        return BitmapType.typeOf(integer.intValue());
    }

    public Compression getCompression() {
        return Compression.typeOf(this);
    }

    public RenderingHalftoningAlgorithm getRendering() {
        Integer integer = getInteger(10);
        if (integer == null) {
            return null;
        }
        return RenderingHalftoningAlgorithm.typeOf(integer.intValue());
    }

    public ColorEncoding getColorEncoding() {
        Integer integer = getInteger(11);
        if (integer == null) {
            return null;
        }
        return ColorEncoding.typeOf(integer.intValue());
    }

    public ColorSpaceType getColorSpaceType() {
        Long longObject = getLongObject(16);
        if (longObject == null) {
            return null;
        }
        return ColorSpaceType.typeOf(longObject.longValue());
    }

    public RenderingIntent getRenderingIntent() {
        if (getInteger(20) == null) {
            return null;
        }
        return RenderingIntent.typeOf(r0.intValue());
    }

    public enum BitmapType {
        BITMAP(BmpReader.BITMAP),
        OS2_BITMAP_ARRAY(BmpReader.OS2_BITMAP_ARRAY),
        OS2_ICON(BmpReader.OS2_ICON),
        OS2_COLOR_ICON(BmpReader.OS2_COLOR_ICON),
        OS2_COLOR_POINTER(BmpReader.OS2_COLOR_POINTER),
        OS2_POINTER(BmpReader.OS2_POINTER);

        private final int value;

        public int getValue() {
            return this.value;
        }

        BitmapType(int i) {
            this.value = i;
        }

        public static BitmapType typeOf(int i) {
            for (BitmapType bitmapType : values()) {
                if (bitmapType.value == i) {
                    return bitmapType;
                }
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[ordinal()]) {
                case 1:
                    return "Standard";
                case 2:
                    return "Bitmap Array";
                case 3:
                    return "Color Icon";
                case 4:
                    return "Color Pointer";
                case 5:
                    return "Monochrome Icon";
                case 6:
                    return "Monochrome Pointer";
                default:
                    throw new IllegalStateException("Unimplemented bitmap type " + super.toString());
            }
        }
    }

    public enum Compression {
        BI_RGB(0),
        BI_RLE8(1),
        BI_RLE4(2),
        BI_BITFIELDS(3),
        BI_HUFFMAN_1D(3),
        BI_JPEG(4),
        BI_RLE24(4),
        BI_PNG(5),
        BI_ALPHABITFIELDS(6),
        BI_CMYK(11),
        BI_CMYKRLE8(12),
        BI_CMYKRLE4(13);

        private final int value;

        public static Compression typeOf(int i, int i2) {
            switch (i) {
                case 0:
                    return BI_RGB;
                case 1:
                    return BI_RLE8;
                case 2:
                    return BI_RLE4;
                case 3:
                    return i2 == 64 ? BI_HUFFMAN_1D : BI_BITFIELDS;
                case 4:
                    return i2 == 64 ? BI_RLE24 : BI_JPEG;
                case 5:
                    return BI_PNG;
                case 6:
                    return BI_ALPHABITFIELDS;
                case 7:
                case 8:
                case 9:
                case 10:
                default:
                    return null;
                case 11:
                    return BI_CMYK;
                case 12:
                    return BI_CMYKRLE8;
                case 13:
                    return BI_CMYKRLE4;
            }
        }

        public int getValue() {
            return this.value;
        }

        Compression(int i) {
            this.value = i;
        }

        public static Compression typeOf(BmpHeaderDirectory bmpHeaderDirectory) {
            Integer integer;
            Integer integer2 = bmpHeaderDirectory.getInteger(5);
            if (integer2 == null || (integer = bmpHeaderDirectory.getInteger(-1)) == null) {
                return null;
            }
            return typeOf(integer2.intValue(), integer.intValue());
        }

        @Override // java.lang.Enum
        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[ordinal()]) {
                case 1:
                    return KeychainModule.AccessControl.NONE;
                case 2:
                    return "RLE 8-bit/pixel";
                case 3:
                    return "RLE 4-bit/pixel";
                case 4:
                    return "Bit Fields";
                case 5:
                    return "Huffman 1D";
                case 6:
                    return "JPEG";
                case 7:
                    return "RLE 24-bit/pixel";
                case 8:
                    return "PNG";
                case 9:
                    return "RGBA Bit Fields";
                case 10:
                    return "CMYK Uncompressed";
                case 11:
                    return "CMYK RLE-8";
                case 12:
                    return "CMYK RLE-4";
                default:
                    throw new IllegalStateException("Unimplemented compression type " + super.toString());
            }
        }
    }

    public enum RenderingHalftoningAlgorithm {
        NONE(0),
        ERROR_DIFFUSION(1),
        PANDA(2),
        SUPER_CIRCLE(3);

        private final int value;

        public int getValue() {
            return this.value;
        }

        RenderingHalftoningAlgorithm(int i) {
            this.value = i;
        }

        public static RenderingHalftoningAlgorithm typeOf(int i) {
            for (RenderingHalftoningAlgorithm renderingHalftoningAlgorithm : values()) {
                if (renderingHalftoningAlgorithm.value == i) {
                    return renderingHalftoningAlgorithm;
                }
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[ordinal()];
            if (i == 1) {
                return "No Halftoning Algorithm";
            }
            if (i == 2) {
                return "Error Diffusion Halftoning";
            }
            if (i == 3) {
                return "Processing Algorithm for Noncoded Document Acquisition";
            }
            if (i == 4) {
                return "Super-circle Halftoning";
            }
            throw new IllegalStateException("Unimplemented rendering halftoning algorithm type " + super.toString());
        }
    }

    public enum ColorEncoding {
        RGB(0);

        private final int value;

        public static ColorEncoding typeOf(int i) {
            if (i == 0) {
                return RGB;
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }

        ColorEncoding(int i) {
            this.value = i;
        }
    }

    public enum ColorSpaceType {
        LCS_CALIBRATED_RGB(0),
        LCS_sRGB(1934772034),
        LCS_WINDOWS_COLOR_SPACE(1466527264),
        PROFILE_LINKED(1279872587),
        PROFILE_EMBEDDED(1296188740);

        private final long value;

        public long getValue() {
            return this.value;
        }

        ColorSpaceType(long j) {
            this.value = j;
        }

        public static ColorSpaceType typeOf(long j) {
            for (ColorSpaceType colorSpaceType : values()) {
                if (colorSpaceType.value == j) {
                    return colorSpaceType;
                }
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ordinal()];
            if (i == 1) {
                return "Calibrated RGB";
            }
            if (i == 2) {
                return "sRGB Color Space";
            }
            if (i == 3) {
                return "System Default Color Space, sRGB";
            }
            if (i == 4) {
                return "Linked Profile";
            }
            if (i == 5) {
                return "Embedded Profile";
            }
            throw new IllegalStateException("Unimplemented color space type " + super.toString());
        }
    }

    public enum RenderingIntent {
        LCS_GM_BUSINESS(1),
        LCS_GM_GRAPHICS(2),
        LCS_GM_IMAGES(4),
        LCS_GM_ABS_COLORIMETRIC(8);

        private final int value;

        public int getValue() {
            return this.value;
        }

        RenderingIntent(int i) {
            this.value = i;
        }

        public static RenderingIntent typeOf(long j) {
            for (RenderingIntent renderingIntent : values()) {
                if (renderingIntent.value == j) {
                    return renderingIntent;
                }
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[ordinal()];
            if (i == 1) {
                return "Graphic, Saturation";
            }
            if (i == 2) {
                return "Proof, Relative Colorimetric";
            }
            if (i == 3) {
                return "Picture, Perceptual";
            }
            if (i == 4) {
                return "Match, Absolute Colorimetric";
            }
            throw new IllegalStateException("Unimplemented rendering intent " + super.toString());
        }
    }

    /* renamed from: com.drew.metadata.bmp.BmpHeaderDirectory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent;

        static {
            int[] iArr = new int[RenderingIntent.values().length];
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent = iArr;
            try {
                iArr[RenderingIntent.LCS_GM_BUSINESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_GRAPHICS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_IMAGES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_ABS_COLORIMETRIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ColorSpaceType.values().length];
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType = iArr2;
            try {
                iArr2[ColorSpaceType.LCS_CALIBRATED_RGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.LCS_sRGB.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.LCS_WINDOWS_COLOR_SPACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.PROFILE_LINKED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.PROFILE_EMBEDDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[RenderingHalftoningAlgorithm.values().length];
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm = iArr3;
            try {
                iArr3[RenderingHalftoningAlgorithm.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.ERROR_DIFFUSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.PANDA.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.SUPER_CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr4 = new int[Compression.values().length];
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression = iArr4;
            try {
                iArr4[Compression.BI_RGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE8.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE4.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_BITFIELDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_HUFFMAN_1D.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_JPEG.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE24.ordinal()] = 7;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_PNG.ordinal()] = 8;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_ALPHABITFIELDS.ordinal()] = 9;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYK.ordinal()] = 10;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYKRLE8.ordinal()] = 11;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYKRLE4.ordinal()] = 12;
            } catch (NoSuchFieldError unused25) {
            }
            int[] iArr5 = new int[BitmapType.values().length];
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType = iArr5;
            try {
                iArr5[BitmapType.BITMAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_BITMAP_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_COLOR_ICON.ordinal()] = 3;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_COLOR_POINTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_ICON.ordinal()] = 5;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_POINTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }
}
