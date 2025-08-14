package com.drew.metadata.jpeg;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class JpegComponent implements Serializable {
    private static final long serialVersionUID = 61121257899091914L;
    private final int _componentId;
    private final int _quantizationTableNumber;
    private final int _samplingFactorByte;

    public int getComponentId() {
        return this._componentId;
    }

    public int getHorizontalSamplingFactor() {
        return (this._samplingFactorByte >> 4) & 15;
    }

    public int getQuantizationTableNumber() {
        return this._quantizationTableNumber;
    }

    public int getVerticalSamplingFactor() {
        return this._samplingFactorByte & 15;
    }

    public JpegComponent(int i, int i2, int i3) {
        this._componentId = i;
        this._samplingFactorByte = i2;
        this._quantizationTableNumber = i3;
    }

    public String getComponentName() {
        int i = this._componentId;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.format("Unknown (%s)", Integer.valueOf(i)) : "Q" : "I" : "Cr" : "Cb" : "Y";
    }

    public String toString() {
        return String.format("Quantization table %d, Sampling factors %d horiz/%d vert", Integer.valueOf(this._quantizationTableNumber), Integer.valueOf(getHorizontalSamplingFactor()), Integer.valueOf(getVerticalSamplingFactor()));
    }
}
