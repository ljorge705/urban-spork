package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.oblador.keychain.KeychainModule;

/* loaded from: classes5.dex */
public class QuickTimeVideoDescriptor extends QuickTimeDescriptor {
    public QuickTimeVideoDescriptor(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        super(quickTimeVideoDirectory);
    }

    @Override // com.drew.metadata.mov.QuickTimeDescriptor, com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 4 || i == 5) {
            return getPixelDescription(i);
        }
        if (i == 9) {
            return getDepthDescription(i);
        }
        if (i == 11) {
            return getGraphicsModeDescription();
        }
        if (i == 13) {
            return getColorTableDescription(i);
        }
        return super.getDescription(i);
    }

    private String getPixelDescription(int i) {
        String string = ((QuickTimeDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        return string + " pixels";
    }

    private String getDepthDescription(int i) {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 1 || iIntValue == 2 || iIntValue == 4 || iIntValue == 8 || iIntValue == 16 || iIntValue == 24 || iIntValue == 32) {
            return integer + "-bit color";
        }
        if (iIntValue == 34 || iIntValue == 36 || iIntValue == 40) {
            return (integer.intValue() - 32) + "-bit grayscale";
        }
        return "Unknown (" + integer + ")";
    }

    private String getColorTableDescription(int i) {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != -1 ? iIntValue != 0 ? "Unknown (" + integer + ")" : "Color table within file" : ((QuickTimeDirectory) this._directory).getInteger(9).intValue() < 16 ? "Default" : KeychainModule.AccessControl.NONE;
    }

    private String getGraphicsModeDescription() {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(11);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Copy";
        }
        if (iIntValue == 32) {
            return "Blend";
        }
        if (iIntValue == 36) {
            return "Transparent";
        }
        if (iIntValue == 64) {
            return "Dither copy";
        }
        switch (iIntValue) {
            case 256:
                return "Straight alpha";
            case 257:
                return "Premul white alpha";
            case 258:
                return "Premul black alpha";
            case 259:
                return "Composition (dither copy)";
            case 260:
                return "Straight alpha blend";
            default:
                return "Unknown (" + integer + ")";
        }
    }
}
