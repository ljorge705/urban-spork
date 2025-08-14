package com.adobe.internal.xmp.options;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes5.dex */
public final class ParseOptions extends Options {
    public static final int ACCEPT_LATIN_1 = 16;
    public static final int DISALLOW_DOCTYPE = 64;
    public static final int FIX_CONTROL_CHARS = 8;
    public static final int OMIT_NORMALIZATION = 32;
    public static final int REQUIRE_XMP_META = 1;
    public static final int STRICT_ALIASING = 4;
    private Map<String, Integer> mXMPNodesToLimit = new HashMap();

    @Override // com.adobe.internal.xmp.options.Options
    protected String defineOptionName(int i) {
        if (i == 1) {
            return "REQUIRE_XMP_META";
        }
        if (i == 4) {
            return "STRICT_ALIASING";
        }
        if (i == 8) {
            return "FIX_CONTROL_CHARS";
        }
        if (i == 16) {
            return "ACCEPT_LATIN_1";
        }
        if (i == 32) {
            return "OMIT_NORMALIZATION";
        }
        if (i != 64) {
            return null;
        }
        return "DISALLOW_DOCTYPE";
    }

    @Override // com.adobe.internal.xmp.options.Options
    protected int getValidOptions() {
        return ISO781611.SMT_TAG;
    }

    public ParseOptions() {
        setOption(88, true);
    }

    public boolean getRequireXMPMeta() {
        return getOption(1);
    }

    public ParseOptions setRequireXMPMeta(boolean z) {
        setOption(1, z);
        return this;
    }

    public boolean getStrictAliasing() {
        return getOption(4);
    }

    public ParseOptions setStrictAliasing(boolean z) {
        setOption(4, z);
        return this;
    }

    public boolean getFixControlChars() {
        return getOption(8);
    }

    public ParseOptions setFixControlChars(boolean z) {
        setOption(8, z);
        return this;
    }

    public boolean getAcceptLatin1() {
        return getOption(16);
    }

    public ParseOptions setOmitNormalization(boolean z) {
        setOption(32, z);
        return this;
    }

    public boolean getOmitNormalization() {
        return getOption(32);
    }

    public ParseOptions setDisallowDoctype(boolean z) {
        setOption(64, z);
        return this;
    }

    public boolean getDisallowDoctype() {
        return getOption(64);
    }

    public ParseOptions setAcceptLatin1(boolean z) {
        setOption(16, z);
        return this;
    }

    public boolean areXMPNodesLimited() {
        return this.mXMPNodesToLimit.size() > 0;
    }

    public ParseOptions setXMPNodesToLimit(Map<String, Integer> map) {
        this.mXMPNodesToLimit.putAll(map);
        return this;
    }

    public Map<String, Integer> getXMPNodesToLimit() {
        return Collections.unmodifiableMap(this.mXMPNodesToLimit);
    }
}
