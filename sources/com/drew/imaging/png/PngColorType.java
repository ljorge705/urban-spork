package com.drew.imaging.png;

/* loaded from: classes5.dex */
public enum PngColorType {
    Greyscale(0, "Greyscale", 1, 2, 4, 8, 16),
    TrueColor(2, "True Color", 8, 16),
    IndexedColor(3, "Indexed Color", 1, 2, 4, 8),
    GreyscaleWithAlpha(4, "Greyscale with Alpha", 8, 16),
    TrueColorWithAlpha(6, "True Color with Alpha", 8, 16);

    private final int[] _allowedBitDepths;
    private final String _description;
    private final int _numericValue;

    public int[] getAllowedBitDepths() {
        return this._allowedBitDepths;
    }

    public String getDescription() {
        return this._description;
    }

    public int getNumericValue() {
        return this._numericValue;
    }

    public static PngColorType fromNumericValue(int i) {
        for (PngColorType pngColorType : (PngColorType[]) PngColorType.class.getEnumConstants()) {
            if (pngColorType.getNumericValue() == i) {
                return pngColorType;
            }
        }
        return null;
    }

    PngColorType(int i, String str, int... iArr) {
        this._numericValue = i;
        this._description = str;
        this._allowedBitDepths = iArr;
    }
}
