package androidx.compose.ui.graphics.vector.compat;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.TypedArrayUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.Session;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: XmlVectorParser.android.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a'\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\rH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a'\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u0013H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0011\u001a\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a*\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0000\u001a\f\u0010\"\u001a\u00020#*\u00020$H\u0000\u001a2\u0010%\u001a\u00020&*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010'\u001a\u00020\u001aH\u0000\u001a<\u0010(\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u000e\b\u0002\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u0001H\u0000\u001a2\u0010*\u001a\u00020&*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010'\u001a\u00020\u001aH\u0000\u001a2\u0010+\u001a\u00020&*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010'\u001a\u00020\u001aH\u0000\u001a\f\u0010,\u001a\u00020$*\u00020$H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006-"}, d2 = {"FILL_TYPE_WINDING", "", "LINECAP_BUTT", "LINECAP_ROUND", "LINECAP_SQUARE", "LINEJOIN_BEVEL", "LINEJOIN_MITER", "LINEJOIN_ROUND", "SHAPE_CLIP_PATH", "", "SHAPE_GROUP", "SHAPE_PATH", "getStrokeLineCap", "Landroidx/compose/ui/graphics/StrokeCap;", "id", "defValue", "getStrokeLineCap-CSYIeUk", "(II)I", "getStrokeLineJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "getStrokeLineJoin-kLtJ_vA", "obtainBrushFromComplexColor", "Landroidx/compose/ui/graphics/Brush;", "complexColor", "Landroidx/core/content/res/ComplexColorCompat;", "createVectorImageBuilder", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "Landroidx/compose/ui/graphics/vector/compat/AndroidVectorParser;", UriUtil.LOCAL_RESOURCE_SCHEME, "Landroid/content/res/Resources;", "theme", "Landroid/content/res/Resources$Theme;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "isAtEnd", "", "Lorg/xmlpull/v1/XmlPullParser;", "parseClipPath", "", "builder", "parseCurrentVectorNode", "nestedGroups", "parseGroup", "parsePath", "seekToStartTag", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class XmlVectorParser_androidKt {
    private static final int FILL_TYPE_WINDING = 0;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";

    /* renamed from: getStrokeLineCap-CSYIeUk$default, reason: not valid java name */
    static /* synthetic */ int m2520getStrokeLineCapCSYIeUk$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        }
        return m2519getStrokeLineCapCSYIeUk(i, i2);
    }

    /* renamed from: getStrokeLineCap-CSYIeUk, reason: not valid java name */
    private static final int m2519getStrokeLineCapCSYIeUk(int i, int i2) {
        if (i == 0) {
            return StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        }
        if (i != 1) {
            return i != 2 ? i2 : StrokeCap.INSTANCE.m2222getSquareKaPHkGw();
        }
        return StrokeCap.INSTANCE.m2221getRoundKaPHkGw();
    }

    /* renamed from: getStrokeLineJoin-kLtJ_vA$default, reason: not valid java name */
    static /* synthetic */ int m2522getStrokeLineJoinkLtJ_vA$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = StrokeJoin.INSTANCE.m2231getMiterLxFBmk8();
        }
        return m2521getStrokeLineJoinkLtJ_vA(i, i2);
    }

    /* renamed from: getStrokeLineJoin-kLtJ_vA, reason: not valid java name */
    private static final int m2521getStrokeLineJoinkLtJ_vA(int i, int i2) {
        if (i == 0) {
            return StrokeJoin.INSTANCE.m2231getMiterLxFBmk8();
        }
        if (i != 1) {
            return i != 2 ? i2 : StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        }
        return StrokeJoin.INSTANCE.m2232getRoundLxFBmk8();
    }

    public static final boolean isAtEnd(XmlPullParser xmlPullParser) {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        if (xmlPullParser.getEventType() != 1) {
            return xmlPullParser.getDepth() < 1 && xmlPullParser.getEventType() == 3;
        }
        return true;
    }

    public static /* synthetic */ int parseCurrentVectorNode$default(AndroidVectorParser androidVectorParser, Resources resources, AttributeSet attributeSet, Resources.Theme theme, ImageVector.Builder builder, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            theme = null;
        }
        return parseCurrentVectorNode(androidVectorParser, resources, attributeSet, theme, builder, i);
    }

    public static final int parseCurrentVectorNode(AndroidVectorParser androidVectorParser, Resources res, AttributeSet attrs, Resources.Theme theme, ImageVector.Builder builder, int i) throws XmlPullParserException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(androidVectorParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        int eventType = androidVectorParser.getXmlParser().getEventType();
        if (eventType == 2) {
            String name = androidVectorParser.getXmlParser().getName();
            if (name == null) {
                return i;
            }
            int iHashCode = name.hashCode();
            if (iHashCode == -1649314686) {
                if (!name.equals(SHAPE_CLIP_PATH)) {
                    return i;
                }
                parseClipPath(androidVectorParser, res, theme, attrs, builder);
                return i + 1;
            }
            if (iHashCode == 3433509) {
                if (!name.equals("path")) {
                    return i;
                }
                parsePath(androidVectorParser, res, theme, attrs, builder);
                return i;
            }
            if (iHashCode != 98629247 || !name.equals("group")) {
                return i;
            }
            parseGroup(androidVectorParser, res, theme, attrs, builder);
            return i;
        }
        if (eventType != 3 || !Intrinsics.areEqual("group", androidVectorParser.getXmlParser().getName())) {
            return i;
        }
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            builder.clearGroup();
        }
        return 0;
    }

    public static final XmlPullParser seekToStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        int next = xmlPullParser.next();
        while (next != 2 && next != 1) {
            next = xmlPullParser.next();
        }
        if (next == 2) {
            return xmlPullParser;
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static final ImageVector.Builder createVectorImageBuilder(AndroidVectorParser androidVectorParser, Resources res, Resources.Theme theme, AttributeSet attrs) throws XmlPullParserException {
        long jM1913getUnspecified0d7_KjU;
        int iM1819getSrcIn0nO6VwU;
        ColorStateList namedColorStateList;
        Intrinsics.checkNotNullParameter(androidVectorParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray typedArrayObtainAttributes = androidVectorParser.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY());
        boolean namedBoolean = androidVectorParser.getNamedBoolean(typedArrayObtainAttributes, "autoMirrored", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_AUTO_MIRRORED(), false);
        float namedFloat = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "viewportWidth", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_VIEWPORT_WIDTH(), 0.0f);
        float namedFloat2 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "viewportHeight", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_VIEWPORT_HEIGHT(), 0.0f);
        if (namedFloat <= 0.0f) {
            throw new XmlPullParserException(typedArrayObtainAttributes.getPositionDescription() + "<VectorGraphic> tag requires viewportWidth > 0");
        }
        if (namedFloat2 <= 0.0f) {
            throw new XmlPullParserException(typedArrayObtainAttributes.getPositionDescription() + "<VectorGraphic> tag requires viewportHeight > 0");
        }
        float dimension = androidVectorParser.getDimension(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_WIDTH(), 0.0f);
        float dimension2 = androidVectorParser.getDimension(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_HEIGHT(), 0.0f);
        if (typedArrayObtainAttributes.hasValue(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT())) {
            TypedValue typedValue = new TypedValue();
            typedArrayObtainAttributes.getValue(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT(), typedValue);
            if (typedValue.type != 2 && (namedColorStateList = androidVectorParser.getNamedColorStateList(typedArrayObtainAttributes, theme, "tint", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT())) != null) {
                jM1913getUnspecified0d7_KjU = ColorKt.Color(namedColorStateList.getDefaultColor());
            } else {
                jM1913getUnspecified0d7_KjU = Color.INSTANCE.m1913getUnspecified0d7_KjU();
            }
        } else {
            jM1913getUnspecified0d7_KjU = Color.INSTANCE.m1913getUnspecified0d7_KjU();
        }
        long j = jM1913getUnspecified0d7_KjU;
        int i = androidVectorParser.getInt(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT_MODE(), -1);
        if (i == -1) {
            iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1819getSrcIn0nO6VwU();
        } else if (i == 3) {
            iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1821getSrcOver0nO6VwU();
        } else if (i == 5) {
            iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1819getSrcIn0nO6VwU();
        } else if (i == 9) {
            iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1818getSrcAtop0nO6VwU();
        } else {
            switch (i) {
                case 14:
                    iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1810getModulate0nO6VwU();
                    break;
                case 15:
                    iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1815getScreen0nO6VwU();
                    break;
                case 16:
                    iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1813getPlus0nO6VwU();
                    break;
                default:
                    iM1819getSrcIn0nO6VwU = BlendMode.INSTANCE.m1819getSrcIn0nO6VwU();
                    break;
            }
        }
        int i2 = iM1819getSrcIn0nO6VwU;
        float fM4390constructorimpl = Dp.m4390constructorimpl(dimension / res.getDisplayMetrics().density);
        float fM4390constructorimpl2 = Dp.m4390constructorimpl(dimension2 / res.getDisplayMetrics().density);
        typedArrayObtainAttributes.recycle();
        return new ImageVector.Builder(null, fM4390constructorimpl, fM4390constructorimpl2, namedFloat, namedFloat2, j, i2, namedBoolean, 1, null);
    }

    public static final void parsePath(AndroidVectorParser androidVectorParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(androidVectorParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray typedArrayObtainAttributes = androidVectorParser.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH());
        if (!TypedArrayUtils.hasAttribute(androidVectorParser.getXmlParser(), "pathData")) {
            throw new IllegalArgumentException("No path data available");
        }
        String string = androidVectorParser.getString(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_NAME());
        if (string == null) {
            string = "";
        }
        String str = string;
        List<PathNode> listAddPathNodes = VectorKt.addPathNodes(androidVectorParser.getString(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_PATH_DATA()));
        ComplexColorCompat namedComplexColor = androidVectorParser.getNamedComplexColor(typedArrayObtainAttributes, theme, "fillColor", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_FILL_COLOR(), 0);
        float namedFloat = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "fillAlpha", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_FILL_ALPHA(), 1.0f);
        int iM2519getStrokeLineCapCSYIeUk = m2519getStrokeLineCapCSYIeUk(androidVectorParser.getNamedInt(typedArrayObtainAttributes, "strokeLineCap", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_CAP(), -1), StrokeCap.INSTANCE.m2220getButtKaPHkGw());
        int iM2521getStrokeLineJoinkLtJ_vA = m2521getStrokeLineJoinkLtJ_vA(androidVectorParser.getNamedInt(typedArrayObtainAttributes, "strokeLineJoin", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_JOIN(), -1), StrokeJoin.INSTANCE.m2230getBevelLxFBmk8());
        float namedFloat2 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "strokeMiterLimit", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_MITER_LIMIT(), 1.0f);
        ComplexColorCompat namedComplexColor2 = androidVectorParser.getNamedComplexColor(typedArrayObtainAttributes, theme, "strokeColor", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_COLOR(), 0);
        float namedFloat3 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "strokeAlpha", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_ALPHA(), 1.0f);
        float namedFloat4 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "strokeWidth", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_WIDTH(), 1.0f);
        float namedFloat5 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "trimPathEnd", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_END(), 1.0f);
        float namedFloat6 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "trimPathOffset", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_OFFSET(), 0.0f);
        float namedFloat7 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, "trimPathStart", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_START(), 0.0f);
        int namedInt = androidVectorParser.getNamedInt(typedArrayObtainAttributes, "fillType", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_FILLTYPE(), FILL_TYPE_WINDING);
        typedArrayObtainAttributes.recycle();
        Brush brushObtainBrushFromComplexColor = obtainBrushFromComplexColor(namedComplexColor);
        Brush brushObtainBrushFromComplexColor2 = obtainBrushFromComplexColor(namedComplexColor2);
        PathFillType.Companion companion = PathFillType.INSTANCE;
        builder.m2499addPathoIyEayM(listAddPathNodes, namedInt == 0 ? companion.m2151getNonZeroRgk1Os() : companion.m2150getEvenOddRgk1Os(), str, brushObtainBrushFromComplexColor, namedFloat, brushObtainBrushFromComplexColor2, namedFloat3, namedFloat4, iM2519getStrokeLineCapCSYIeUk, iM2521getStrokeLineJoinkLtJ_vA, namedFloat2, namedFloat7, namedFloat5, namedFloat6);
    }

    private static final Brush obtainBrushFromComplexColor(ComplexColorCompat complexColorCompat) {
        if (!complexColorCompat.willDraw()) {
            return null;
        }
        Shader shader = complexColorCompat.getShader();
        if (shader != null) {
            return BrushKt.ShaderBrush(shader);
        }
        return new SolidColor(ColorKt.Color(complexColorCompat.getColor()), null);
    }

    public static final void parseClipPath(AndroidVectorParser androidVectorParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) {
        Intrinsics.checkNotNullParameter(androidVectorParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray typedArrayObtainAttributes = androidVectorParser.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH());
        String string = androidVectorParser.getString(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_NAME());
        if (string == null) {
            string = "";
        }
        List<PathNode> listAddPathNodes = VectorKt.addPathNodes(androidVectorParser.getString(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_PATH_DATA()));
        typedArrayObtainAttributes.recycle();
        builder.addGroup((254 & 1) != 0 ? "" : string, (254 & 2) != 0 ? 0.0f : 0.0f, (254 & 4) != 0 ? 0.0f : 0.0f, (254 & 8) != 0 ? 0.0f : 0.0f, (254 & 16) != 0 ? 1.0f : 0.0f, (254 & 32) == 0 ? 0.0f : 1.0f, (254 & 64) != 0 ? 0.0f : 0.0f, (254 & 128) == 0 ? 0.0f : 0.0f, (254 & 256) != 0 ? VectorKt.getEmptyPath() : listAddPathNodes);
    }

    public static final void parseGroup(AndroidVectorParser androidVectorParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) {
        Intrinsics.checkNotNullParameter(androidVectorParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray typedArrayObtainAttributes = androidVectorParser.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP());
        float namedFloat = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, ViewProps.ROTATION, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_ROTATION(), 0.0f);
        float f = androidVectorParser.getFloat(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_X(), 0.0f);
        float f2 = androidVectorParser.getFloat(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_Y(), 0.0f);
        float namedFloat2 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, ViewProps.SCALE_X, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_X(), 1.0f);
        float namedFloat3 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, ViewProps.SCALE_Y, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_Y(), 1.0f);
        float namedFloat4 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, ViewProps.TRANSLATE_X, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_X(), 0.0f);
        float namedFloat5 = androidVectorParser.getNamedFloat(typedArrayObtainAttributes, ViewProps.TRANSLATE_Y, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_Y(), 0.0f);
        String string = androidVectorParser.getString(typedArrayObtainAttributes, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_NAME());
        if (string == null) {
            string = "";
        }
        typedArrayObtainAttributes.recycle();
        builder.addGroup(string, namedFloat, f, f2, namedFloat2, namedFloat3, namedFloat4, namedFloat5, VectorKt.getEmptyPath());
    }
}
