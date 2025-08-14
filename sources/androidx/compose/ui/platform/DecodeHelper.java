package androidx.compose.ui.platform;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidClipboardManager.android.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u000f\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u001aø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001b\u0010\u0018J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\bH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0006\u0010!\u001a\u00020\"J\n\u0010#\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020'H\u0002J\u0016\u0010(\u001a\u00020)ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b*\u0010\u0012J\u0018\u0010+\u001a\u00020,H\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b-\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006."}, d2 = {"Landroidx/compose/ui/platform/DecodeHelper;", "", CTVariableUtils.STRING, "", "(Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "dataAvailable", "", "decodeBaselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "decodeBaselineShift-y9eOQZs", "()F", "decodeByte", "", "decodeColor", "Landroidx/compose/ui/graphics/Color;", "decodeColor-0d7_KjU", "()J", "decodeFloat", "", "decodeFontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "decodeFontStyle-_-LCdwA", "()I", "decodeFontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "decodeFontSynthesis-GVVA2EU", "decodeFontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "decodeInt", "decodeShadow", "Landroidx/compose/ui/graphics/Shadow;", "decodeSpanStyle", "Landroidx/compose/ui/text/SpanStyle;", "decodeString", "decodeTextDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "decodeTextGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "decodeTextUnit", "Landroidx/compose/ui/unit/TextUnit;", "decodeTextUnit-XSAIIZE", "decodeULong", "Lkotlin/ULong;", "decodeULong-s-VKNKU", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DecodeHelper {
    private final Parcel parcel;

    public DecodeHelper(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        Parcel parcelObtain = Parcel.obtain();
        Intrinsics.checkNotNullExpressionValue(parcelObtain, "obtain()");
        this.parcel = parcelObtain;
        byte[] bArrDecode = Base64.decode(string, 0);
        parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
        parcelObtain.setDataPosition(0);
    }

    public final SpanStyle decodeSpanStyle() {
        MutableSpanStyle mutableSpanStyle;
        MutableSpanStyle mutableSpanStyle2 = mutableSpanStyle;
        MutableSpanStyle mutableSpanStyle3 = new MutableSpanStyle(0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, 16383, null);
        while (this.parcel.dataAvail() > 1) {
            byte bDecodeByte = decodeByte();
            if (bDecodeByte != 1) {
                mutableSpanStyle = mutableSpanStyle2;
                if (bDecodeByte == 2) {
                    if (dataAvailable() < 5) {
                        break;
                    }
                    mutableSpanStyle.m3715setFontSizeR2X_6o(m3687decodeTextUnitXSAIIZE());
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte == 3) {
                    if (dataAvailable() < 4) {
                        break;
                    }
                    mutableSpanStyle.setFontWeight(decodeFontWeight());
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte == 4) {
                    if (dataAvailable() < 1) {
                        break;
                    }
                    mutableSpanStyle.m3716setFontStylemLjRB2g(FontStyle.m3987boximpl(m3685decodeFontStyle_LCdwA()));
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte != 5) {
                    if (bDecodeByte != 6) {
                        if (bDecodeByte != 7) {
                            if (bDecodeByte != 8) {
                                if (bDecodeByte != 9) {
                                    if (bDecodeByte != 10) {
                                        if (bDecodeByte != 11) {
                                            if (bDecodeByte == 12) {
                                                if (dataAvailable() < 20) {
                                                    break;
                                                }
                                                mutableSpanStyle.setShadow(decodeShadow());
                                            }
                                        } else {
                                            if (dataAvailable() < 4) {
                                                break;
                                            }
                                            mutableSpanStyle.setTextDecoration(decodeTextDecoration());
                                        }
                                    } else {
                                        if (dataAvailable() < 8) {
                                            break;
                                        }
                                        mutableSpanStyle.m3712setBackground8_81llA(m3684decodeColor0d7_KjU());
                                    }
                                } else {
                                    if (dataAvailable() < 8) {
                                        break;
                                    }
                                    mutableSpanStyle.setTextGeometricTransform(decodeTextGeometricTransform());
                                }
                            } else {
                                if (dataAvailable() < 4) {
                                    break;
                                }
                                mutableSpanStyle.m3713setBaselineShift_isdbwI(BaselineShift.m4151boximpl(m3682decodeBaselineShifty9eOQZs()));
                            }
                        } else {
                            if (dataAvailable() < 5) {
                                break;
                            }
                            mutableSpanStyle.m3718setLetterSpacingR2X_6o(m3687decodeTextUnitXSAIIZE());
                        }
                    } else {
                        mutableSpanStyle.setFontFeatureSettings(decodeString());
                    }
                    mutableSpanStyle2 = mutableSpanStyle;
                } else {
                    if (dataAvailable() < 1) {
                        break;
                    }
                    mutableSpanStyle.m3717setFontSynthesistDdu0R4(FontSynthesis.m3996boximpl(m3686decodeFontSynthesisGVVA2EU()));
                    mutableSpanStyle2 = mutableSpanStyle;
                }
            } else {
                if (dataAvailable() < 8) {
                    break;
                }
                mutableSpanStyle2.m3714setColor8_81llA(m3684decodeColor0d7_KjU());
            }
        }
        mutableSpanStyle = mutableSpanStyle2;
        return mutableSpanStyle.toSpanStyle();
    }

    /* renamed from: decodeColor-0d7_KjU, reason: not valid java name */
    public final long m3684decodeColor0d7_KjU() {
        return Color.m1873constructorimpl(m3683decodeULongsVKNKU());
    }

    /* renamed from: decodeTextUnit-XSAIIZE, reason: not valid java name */
    public final long m3687decodeTextUnitXSAIIZE() {
        long jM4605getUnspecifiedUIouoOA;
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 1) {
            jM4605getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m4604getSpUIouoOA();
        } else if (bDecodeByte == 2) {
            jM4605getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m4603getEmUIouoOA();
        } else {
            jM4605getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m4605getUnspecifiedUIouoOA();
        }
        if (TextUnitType.m4599equalsimpl0(jM4605getUnspecifiedUIouoOA, TextUnitType.INSTANCE.m4605getUnspecifiedUIouoOA())) {
            return TextUnit.INSTANCE.m4582getUnspecifiedXSAIIZE();
        }
        return TextUnitKt.m4583TextUnitanM5pPY(decodeFloat(), jM4605getUnspecifiedUIouoOA);
    }

    public final FontWeight decodeFontWeight() {
        return new FontWeight(decodeInt());
    }

    /* renamed from: decodeFontStyle-_-LCdwA, reason: not valid java name */
    public final int m3685decodeFontStyle_LCdwA() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontStyle.INSTANCE.m3995getNormal_LCdwA();
        }
        if (bDecodeByte == 1) {
            return FontStyle.INSTANCE.m3994getItalic_LCdwA();
        }
        return FontStyle.INSTANCE.m3995getNormal_LCdwA();
    }

    /* renamed from: decodeFontSynthesis-GVVA2EU, reason: not valid java name */
    public final int m3686decodeFontSynthesisGVVA2EU() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontSynthesis.INSTANCE.m4006getNoneGVVA2EU();
        }
        if (bDecodeByte == 1) {
            return FontSynthesis.INSTANCE.m4005getAllGVVA2EU();
        }
        if (bDecodeByte == 3) {
            return FontSynthesis.INSTANCE.m4007getStyleGVVA2EU();
        }
        if (bDecodeByte == 2) {
            return FontSynthesis.INSTANCE.m4008getWeightGVVA2EU();
        }
        return FontSynthesis.INSTANCE.m4006getNoneGVVA2EU();
    }

    /* renamed from: decodeBaselineShift-y9eOQZs, reason: not valid java name */
    private final float m3682decodeBaselineShifty9eOQZs() {
        return BaselineShift.m4152constructorimpl(decodeFloat());
    }

    private final TextGeometricTransform decodeTextGeometricTransform() {
        return new TextGeometricTransform(decodeFloat(), decodeFloat());
    }

    private final TextDecoration decodeTextDecoration() {
        int iDecodeInt = decodeInt();
        boolean z = (TextDecoration.INSTANCE.getLineThrough().getMask() & iDecodeInt) != 0;
        boolean z2 = (iDecodeInt & TextDecoration.INSTANCE.getUnderline().getMask()) != 0;
        if (z && z2) {
            return TextDecoration.INSTANCE.combine(CollectionsKt.listOf((Object[]) new TextDecoration[]{TextDecoration.INSTANCE.getLineThrough(), TextDecoration.INSTANCE.getUnderline()}));
        }
        if (z) {
            return TextDecoration.INSTANCE.getLineThrough();
        }
        if (z2) {
            return TextDecoration.INSTANCE.getUnderline();
        }
        return TextDecoration.INSTANCE.getNone();
    }

    private final Shadow decodeShadow() {
        return new Shadow(m3684decodeColor0d7_KjU(), OffsetKt.Offset(decodeFloat(), decodeFloat()), decodeFloat(), null);
    }

    private final byte decodeByte() {
        return this.parcel.readByte();
    }

    private final int decodeInt() {
        return this.parcel.readInt();
    }

    /* renamed from: decodeULong-s-VKNKU, reason: not valid java name */
    private final long m3683decodeULongsVKNKU() {
        return ULong.m6270constructorimpl(this.parcel.readLong());
    }

    private final float decodeFloat() {
        return this.parcel.readFloat();
    }

    private final String decodeString() {
        return this.parcel.readString();
    }

    private final int dataAvailable() {
        return this.parcel.dataAvail();
    }
}
