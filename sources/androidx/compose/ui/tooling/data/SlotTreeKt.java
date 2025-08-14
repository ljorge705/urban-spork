package androidx.compose.ui.tooling.data;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SlotTree.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u0002\u001a(\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0%2\b\u0010)\u001a\u0004\u0018\u00010*H\u0003\u001a\u0014\u0010+\u001a\u0004\u0018\u00010\u00062\b\u0010,\u001a\u0004\u0018\u00010(H\u0003\u001a\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020.0%2\u0006\u0010/\u001a\u00020\u0006H\u0002\u001a\u001e\u00100\u001a\u0004\u0018\u00010*2\u0006\u00101\u001a\u00020\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u00010*H\u0003\u001a\u001a\u00103\u001a\u0004\u0018\u000104*\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u00020\u0006H\u0002\u001a\f\u00107\u001a\u00020\u0019*\u000208H\u0007\u001a\f\u00109\u001a\u00020\u0006*\u00020\u0015H\u0002\u001a\u001e\u0010:\u001a\b\u0012\u0004\u0012\u00020&0%*\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=H\u0007\u001a\u0016\u0010>\u001a\u00020\u0019*\u00020;2\b\u0010?\u001a\u0004\u0018\u00010*H\u0003\u001a\f\u0010@\u001a\u00020\u0014*\u00020\u0015H\u0002\u001a\u0014\u0010A\u001a\u00020\u0014*\u00020\u00152\u0006\u0010B\u001a\u00020\u0006H\u0002\u001a\f\u0010C\u001a\u00020\u0014*\u00020\u0015H\u0002\u001a\f\u0010D\u001a\u00020\u0014*\u00020\u0015H\u0002\u001a\f\u0010E\u001a\u00020\u0014*\u00020\u0015H\u0002\u001aK\u0010F\u001a\u0004\u0018\u0001HG\"\u0004\b\u0000\u0010G*\u0002082&\u0010H\u001a\"\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020J\u0012\n\u0012\b\u0012\u0004\u0012\u0002HG0%\u0012\u0006\u0012\u0004\u0018\u0001HG0I2\b\b\u0002\u0010<\u001a\u00020=H\u0007¢\u0006\u0002\u0010K\u001a\f\u0010L\u001a\u00020\u0001*\u00020\u0015H\u0002\u001a\f\u0010M\u001a\u00020\u0001*\u00020\u0006H\u0002\u001a\u0014\u0010M\u001a\u00020\u0001*\u00020\u00062\u0006\u0010N\u001a\u00020\u0001H\u0002\u001a\u001c\u0010O\u001a\u00020\u0006*\u00020\u00062\u0006\u0010P\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u0006H\u0002\u001a\u0014\u0010R\u001a\u00020\t*\u00020\t2\u0006\u0010S\u001a\u00020\tH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0016\"\u0018\u0010\u0017\u001a\u00020\u0014*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016\" \u0010\u0018\u001a\u0004\u0018\u00010\u0006*\u00020\u00198GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0018\u0010\u001e\u001a\u00020\u0006*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006T"}, d2 = {"BITS_PER_SLOT", "", "SLOT_MASK", "STABLE_BITS", "STATIC_BITS", "changedFieldName", "", "defaultFieldName", "emptyBox", "Landroidx/compose/ui/unit/IntRect;", "getEmptyBox", "()Landroidx/compose/ui/unit/IntRect;", "internalFieldPrefix", "jacocoDataField", "parameterPrefix", "parametersInformationTokenizer", "Lkotlin/text/Regex;", "recomposeScopeNameSuffix", "tokenizer", "isANumber", "", "Lkotlin/text/MatchResult;", "(Lkotlin/text/MatchResult;)Z", "isClassName", ViewProps.POSITION, "Landroidx/compose/ui/tooling/data/Group;", "getPosition$annotations", "(Landroidx/compose/ui/tooling/data/Group;)V", "getPosition", "(Landroidx/compose/ui/tooling/data/Group;)Ljava/lang/String;", "text", "getText", "(Lkotlin/text/MatchResult;)Ljava/lang/String;", "boundsOfLayoutNode", "node", "Landroidx/compose/ui/layout/LayoutInfo;", "extractParameterInfo", "", "Landroidx/compose/ui/tooling/data/ParameterInformation;", "data", "", "context", "Landroidx/compose/ui/tooling/data/SourceInformationContext;", "keyPosition", Constants.KEY_KEY, "parseParameters", "Landroidx/compose/ui/tooling/data/Parameter;", DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS, "sourceInformationContextOf", "information", "parent", "accessibleField", "Ljava/lang/reflect/Field;", "Ljava/lang/Class;", "name", "asTree", "Landroidx/compose/runtime/tooling/CompositionData;", "callName", "findParameters", "Landroidx/compose/runtime/tooling/CompositionGroup;", "cache", "Landroidx/compose/ui/tooling/data/ContextCache;", "getGroup", "parentContext", "isCallWithName", "isChar", "c", "isFileName", "isNumber", "isParameterInformation", "mapTree", ExifInterface.GPS_DIRECTION_TRUE, "factory", "Lkotlin/Function3;", "Landroidx/compose/ui/tooling/data/SourceContext;", "(Landroidx/compose/runtime/tooling/CompositionData;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/tooling/data/ContextCache;)Ljava/lang/Object;", CTVariableUtils.NUMBER, "parseToInt", "radix", "replacePrefix", "prefix", "replacement", "union", "other", "ui-tooling-data_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SlotTreeKt {
    private static final int BITS_PER_SLOT = 3;
    private static final int SLOT_MASK = 7;
    private static final int STABLE_BITS = 4;
    private static final int STATIC_BITS = 3;
    private static final String changedFieldName = "$$changed";
    private static final String defaultFieldName = "$$default";
    private static final String internalFieldPrefix = "$$";
    private static final String jacocoDataField = "$jacoco";
    private static final String parameterPrefix = "$";
    private static final String recomposeScopeNameSuffix = ".RecomposeScopeImpl";
    private static final IntRect emptyBox = new IntRect(0, 0, 0, 0);
    private static final Regex tokenizer = new Regex("(\\d+)|([,])|([*])|([:])|L|(P\\([^)]*\\))|(C(\\(([^)]*)\\))?)|@");
    private static final Regex parametersInformationTokenizer = new Regex("(\\d+)|,|[!P()]|:([^,!)]+)");

    public static final IntRect getEmptyBox() {
        return emptyBox;
    }

    public static /* synthetic */ void getPosition$annotations(Group group) {
    }

    private static final boolean isNumber(MatchResult matchResult) {
        return matchResult.getGroups().get(1) != null;
    }

    private static final int number(MatchResult matchResult) {
        return parseToInt(matchResult.getGroupValues().get(1));
    }

    private static final String getText(MatchResult matchResult) {
        return matchResult.getGroupValues().get(0);
    }

    private static final boolean isChar(MatchResult matchResult, String str) {
        return Intrinsics.areEqual(getText(matchResult), str);
    }

    private static final boolean isFileName(MatchResult matchResult) {
        return matchResult.getGroups().get(4) != null;
    }

    private static final boolean isParameterInformation(MatchResult matchResult) {
        return matchResult.getGroups().get(5) != null;
    }

    private static final boolean isCallWithName(MatchResult matchResult) {
        return matchResult.getGroups().get(6) != null;
    }

    private static final String callName(MatchResult matchResult) {
        return matchResult.getGroupValues().get(8);
    }

    private static final boolean isANumber(MatchResult matchResult) {
        return matchResult.getGroups().get(1) != null;
    }

    private static final boolean isClassName(MatchResult matchResult) {
        return matchResult.getGroups().get(2) != null;
    }

    private static final int parseToInt(String str) throws ParseError {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new ParseError();
        }
    }

    private static final int parseToInt(String str, int i) throws ParseError {
        try {
            return Integer.parseInt(str, CharsKt.checkRadix(i));
        } catch (NumberFormatException unused) {
            throw new ParseError();
        }
    }

    /* JADX WARN: Type inference failed for: r11v2, types: [T, kotlin.text.MatchResult] */
    private static final List<Parameter> parseParameters(String str) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Regex.find$default(parametersInformationTokenizer, str, 0, 2, null);
        List listMutableListOf = CollectionsKt.mutableListOf(0, 1, 2, 3);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = listMutableListOf.size() - 1;
        ArrayList arrayList = new ArrayList();
        try {
            parseParameters$expect(objectRef, "P");
            parseParameters$expect(objectRef, "(");
            while (!parseParameters$isChar(objectRef, ")")) {
                if (parseParameters$isChar(objectRef, "!")) {
                    parseParameters$next(objectRef);
                    int parameters$expectNumber = parseParameters$expectNumber(objectRef);
                    parseParameters$ensureIndexes(intRef, listMutableListOf, arrayList.size() + parameters$expectNumber);
                    for (int i = 0; i < parameters$expectNumber; i++) {
                        arrayList.add(new Parameter(((Number) CollectionsKt.first(listMutableListOf)).intValue(), null, 2, null));
                        listMutableListOf.remove(0);
                    }
                } else if (parseParameters$isChar(objectRef, Constants.SEPARATOR_COMMA)) {
                    parseParameters$next(objectRef);
                } else {
                    int parameters$expectNumber2 = parseParameters$expectNumber(objectRef);
                    arrayList.add(new Parameter(parameters$expectNumber2, parseParameters$isClassName(objectRef) ? parseParameters$expectClassName(objectRef) : null));
                    parseParameters$ensureIndexes(intRef, listMutableListOf, parameters$expectNumber2);
                    listMutableListOf.remove(Integer.valueOf(parameters$expectNumber2));
                }
            }
            parseParameters$expect(objectRef, ")");
            while (listMutableListOf.size() > 0) {
                arrayList.add(new Parameter(((Number) CollectionsKt.first(listMutableListOf)).intValue(), null, 2, null));
                listMutableListOf.remove(0);
            }
            return arrayList;
        } catch (ParseError unused) {
            return CollectionsKt.emptyList();
        } catch (NumberFormatException unused2) {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlin.text.MatchResult] */
    private static final MatchResult parseParameters$next(Ref.ObjectRef<MatchResult> objectRef) {
        MatchResult matchResult = objectRef.element;
        if (matchResult != null) {
            objectRef.element = matchResult.next();
        }
        return objectRef.element;
    }

    private static final int parseParameters$expectNumber(Ref.ObjectRef<MatchResult> objectRef) throws ParseError {
        MatchResult matchResult = objectRef.element;
        if (matchResult == null || !isANumber(matchResult)) {
            throw new ParseError();
        }
        parseParameters$next(objectRef);
        return parseToInt(getText(matchResult));
    }

    private static final String parseParameters$expectClassName(Ref.ObjectRef<MatchResult> objectRef) throws ParseError {
        MatchResult matchResult = objectRef.element;
        if (matchResult == null || !isClassName(matchResult)) {
            throw new ParseError();
        }
        parseParameters$next(objectRef);
        String strSubstring = getText(matchResult).substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return replacePrefix(strSubstring, "c#", "androidx.compose.");
    }

    private static final void parseParameters$expect(Ref.ObjectRef<MatchResult> objectRef, String str) throws ParseError {
        MatchResult matchResult = objectRef.element;
        if (matchResult == null || !Intrinsics.areEqual(getText(matchResult), str)) {
            throw new ParseError();
        }
        parseParameters$next(objectRef);
    }

    private static final boolean parseParameters$isChar(Ref.ObjectRef<MatchResult> objectRef, String str) {
        MatchResult matchResult = objectRef.element;
        return matchResult == null || Intrinsics.areEqual(getText(matchResult), str);
    }

    private static final boolean parseParameters$isClassName(Ref.ObjectRef<MatchResult> objectRef) {
        MatchResult matchResult = objectRef.element;
        return matchResult != null && isClassName(matchResult);
    }

    private static final void parseParameters$ensureIndexes(Ref.IntRef intRef, List<Integer> list, int i) {
        int i2 = i - intRef.element;
        if (i2 > 0) {
            if (i2 < 4) {
                i2 = 4;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                list.add(Integer.valueOf(intRef.element + i3 + 1));
            }
            intRef.element += i2;
        }
    }

    static /* synthetic */ SourceInformationContext sourceInformationContextOf$default(String str, SourceInformationContext sourceInformationContext, int i, Object obj) {
        if ((i & 2) != 0) {
            sourceInformationContext = null;
        }
        return sourceInformationContextOf(str, sourceInformationContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fd  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, kotlin.text.MatchResult] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final androidx.compose.ui.tooling.data.SourceInformationContext sourceInformationContextOf(java.lang.String r16, androidx.compose.ui.tooling.data.SourceInformationContext r17) throws androidx.compose.ui.tooling.data.ParseError {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.data.SlotTreeKt.sourceInformationContextOf(java.lang.String, androidx.compose.ui.tooling.data.SourceInformationContext):androidx.compose.ui.tooling.data.SourceInformationContext");
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlin.text.MatchResult] */
    private static final MatchResult sourceInformationContextOf$next$4(Ref.ObjectRef<MatchResult> objectRef) {
        MatchResult matchResult = objectRef.element;
        if (matchResult != null) {
            objectRef.element = matchResult.next();
        }
        return objectRef.element;
    }

    private static final SourceLocationInfo sourceInformationContextOf$parseLocation(Ref.ObjectRef<MatchResult> objectRef) {
        Integer numValueOf;
        Integer numValueOf2;
        Integer numValueOf3;
        try {
            MatchResult matchResultSourceInformationContextOf$next$4 = objectRef.element;
            if (matchResultSourceInformationContextOf$next$4 == null || !isNumber(matchResultSourceInformationContextOf$next$4)) {
                numValueOf = null;
            } else {
                numValueOf = Integer.valueOf(number(matchResultSourceInformationContextOf$next$4) + 1);
                matchResultSourceInformationContextOf$next$4 = sourceInformationContextOf$next$4(objectRef);
            }
            if (matchResultSourceInformationContextOf$next$4 != null && isChar(matchResultSourceInformationContextOf$next$4, "@")) {
                MatchResult matchResultSourceInformationContextOf$next$42 = sourceInformationContextOf$next$4(objectRef);
                if (matchResultSourceInformationContextOf$next$42 != null && isNumber(matchResultSourceInformationContextOf$next$42)) {
                    numValueOf3 = Integer.valueOf(number(matchResultSourceInformationContextOf$next$42));
                    MatchResult matchResultSourceInformationContextOf$next$43 = sourceInformationContextOf$next$4(objectRef);
                    if (matchResultSourceInformationContextOf$next$43 != null && isChar(matchResultSourceInformationContextOf$next$43, "L")) {
                        MatchResult matchResultSourceInformationContextOf$next$44 = sourceInformationContextOf$next$4(objectRef);
                        if (matchResultSourceInformationContextOf$next$44 != null && isNumber(matchResultSourceInformationContextOf$next$44)) {
                            numValueOf2 = Integer.valueOf(number(matchResultSourceInformationContextOf$next$44));
                        }
                        return null;
                    }
                    numValueOf2 = null;
                }
                return null;
            }
            numValueOf2 = null;
            numValueOf3 = null;
            if (numValueOf != null && numValueOf3 != null && numValueOf2 != null) {
                return new SourceLocationInfo(numValueOf, numValueOf3, numValueOf2);
            }
        } catch (ParseError unused) {
        }
        return null;
    }

    private static final Group getGroup(CompositionGroup compositionGroup, SourceInformationContext sourceInformationContext) {
        List<ModifierInfo> listEmptyList;
        IntRect intRectBoundsOfLayoutNode;
        Object key = compositionGroup.getKey();
        String sourceInfo = compositionGroup.getSourceInfo();
        SourceInformationContext sourceInformationContextSourceInformationContextOf = sourceInfo != null ? sourceInformationContextOf(sourceInfo, sourceInformationContext) : null;
        Object node = compositionGroup.getNode();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = arrayList;
        CollectionsKt.addAll(arrayList3, compositionGroup.getData());
        Iterator<CompositionGroup> it = compositionGroup.getCompositionGroups().iterator();
        while (it.hasNext()) {
            arrayList2.add(getGroup(it.next(), sourceInformationContextSourceInformationContextOf));
        }
        boolean z = node instanceof LayoutInfo;
        if (z) {
            listEmptyList = ((LayoutInfo) node).getModifierInfo();
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (z) {
            intRectBoundsOfLayoutNode = boundsOfLayoutNode((LayoutInfo) node);
        } else if (arrayList2.isEmpty()) {
            intRectBoundsOfLayoutNode = emptyBox;
        } else {
            ArrayList arrayList4 = arrayList2;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                arrayList5.add(((Group) it2.next()).getBox());
            }
            Iterator it3 = arrayList5.iterator();
            if (!it3.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = it3.next();
            while (it3.hasNext()) {
                next = union((IntRect) it3.next(), (IntRect) next);
            }
            intRectBoundsOfLayoutNode = (IntRect) next;
        }
        SourceLocation sourceLocationNextSourceLocation = (sourceInformationContextSourceInformationContextOf == null || !sourceInformationContextSourceInformationContextOf.getIsCall() || sourceInformationContext == null) ? null : sourceInformationContext.nextSourceLocation();
        if (node != null) {
            return new NodeGroup(key, node, intRectBoundsOfLayoutNode, arrayList3, listEmptyList, arrayList2);
        }
        String name = sourceInformationContextSourceInformationContextOf != null ? sourceInformationContextSourceInformationContextOf.getName() : null;
        String name2 = sourceInformationContextSourceInformationContextOf != null ? sourceInformationContextSourceInformationContextOf.getName() : null;
        return new CallGroup(key, name, intRectBoundsOfLayoutNode, sourceLocationNextSourceLocation, (name2 == null || name2.length() == 0 || (intRectBoundsOfLayoutNode.getBottom() - intRectBoundsOfLayoutNode.getTop() <= 0 && intRectBoundsOfLayoutNode.getRight() - intRectBoundsOfLayoutNode.getLeft() <= 0)) ? null : compositionGroup.getIdentity(), extractParameterInfo(arrayList, sourceInformationContextSourceInformationContextOf), arrayList3, arrayList2, sourceInformationContextSourceInformationContextOf != null && sourceInformationContextSourceInformationContextOf.getIsInline());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect boundsOfLayoutNode(LayoutInfo layoutInfo) {
        if (!layoutInfo.isAttached()) {
            return new IntRect(0, 0, layoutInfo.getWidth(), layoutInfo.getHeight());
        }
        long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(layoutInfo.getCoordinates());
        long jMo3401getSizeYbymL2g = layoutInfo.getCoordinates().mo3401getSizeYbymL2g();
        int iRoundToInt = MathKt.roundToInt(Offset.m1639getXimpl(jPositionInWindow));
        int iRoundToInt2 = MathKt.roundToInt(Offset.m1640getYimpl(jPositionInWindow));
        return new IntRect(iRoundToInt, iRoundToInt2, IntSize.m4550getWidthimpl(jMo3401getSizeYbymL2g) + iRoundToInt, IntSize.m4549getHeightimpl(jMo3401getSizeYbymL2g) + iRoundToInt2);
    }

    public static /* synthetic */ Object mapTree$default(CompositionData compositionData, Function3 function3, ContextCache contextCache, int i, Object obj) {
        if ((i & 2) != 0) {
            contextCache = new ContextCache();
        }
        return mapTree(compositionData, function3, contextCache);
    }

    public static final <T> T mapTree(CompositionData compositionData, Function3<? super CompositionGroup, ? super SourceContext, ? super List<? extends T>, ? extends T> factory, ContextCache cache) {
        Intrinsics.checkNotNullParameter(compositionData, "<this>");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(cache, "cache");
        CompositionGroup compositionGroup = (CompositionGroup) CollectionsKt.firstOrNull(compositionData.getCompositionGroups());
        if (compositionGroup == null) {
            return null;
        }
        CompositionCallStack compositionCallStack = new CompositionCallStack(factory, cache.getContexts$ui_tooling_data_release());
        ArrayList arrayList = new ArrayList();
        compositionCallStack.convert(compositionGroup, 0, arrayList);
        return (T) CollectionsKt.firstOrNull((List) arrayList);
    }

    public static /* synthetic */ List findParameters$default(CompositionGroup compositionGroup, ContextCache contextCache, int i, Object obj) {
        if ((i & 1) != 0) {
            contextCache = null;
        }
        return findParameters(compositionGroup, contextCache);
    }

    public static final List<ParameterInformation> findParameters(CompositionGroup compositionGroup, ContextCache contextCache) {
        Intrinsics.checkNotNullParameter(compositionGroup, "<this>");
        String sourceInfo = compositionGroup.getSourceInfo();
        if (sourceInfo == null) {
            return CollectionsKt.emptyList();
        }
        SourceInformationContext sourceInformationContextSourceInformationContextOf$default = null;
        if (contextCache == null) {
            sourceInformationContextSourceInformationContextOf$default = sourceInformationContextOf$default(sourceInfo, null, 2, null);
        } else {
            Map<String, Object> contexts$ui_tooling_data_release = contextCache.getContexts$ui_tooling_data_release();
            Object objSourceInformationContextOf$default = contexts$ui_tooling_data_release.get(sourceInfo);
            if (objSourceInformationContextOf$default == null) {
                objSourceInformationContextOf$default = sourceInformationContextOf$default(sourceInfo, null, 2, null);
                contexts$ui_tooling_data_release.put(sourceInfo, objSourceInformationContextOf$default);
            }
            if (objSourceInformationContextOf$default instanceof SourceInformationContext) {
                sourceInformationContextSourceInformationContextOf$default = (SourceInformationContext) objSourceInformationContextOf$default;
            }
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, compositionGroup.getData());
        return extractParameterInfo(arrayList, sourceInformationContextSourceInformationContextOf$default);
    }

    public static final Group asTree(CompositionData compositionData) {
        Group group;
        Intrinsics.checkNotNullParameter(compositionData, "<this>");
        CompositionGroup compositionGroup = (CompositionGroup) CollectionsKt.firstOrNull(compositionData.getCompositionGroups());
        return (compositionGroup == null || (group = getGroup(compositionGroup, null)) == null) ? EmptyGroup.INSTANCE : group;
    }

    public static final IntRect union(IntRect intRect, IntRect other) {
        Intrinsics.checkNotNullParameter(intRect, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        IntRect intRect2 = emptyBox;
        if (Intrinsics.areEqual(intRect, intRect2)) {
            return other;
        }
        if (Intrinsics.areEqual(other, intRect2)) {
            return intRect;
        }
        return new IntRect(Math.min(intRect.getLeft(), other.getLeft()), Math.min(intRect.getTop(), other.getTop()), Math.max(intRect.getRight(), other.getRight()), Math.max(intRect.getBottom(), other.getBottom()));
    }

    private static final String keyPosition(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof JoinedKey)) {
            return null;
        }
        JoinedKey joinedKey = (JoinedKey) obj;
        String strKeyPosition = keyPosition(joinedKey.getLeft());
        return strKeyPosition == null ? keyPosition(joinedKey.getRight()) : strKeyPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<ParameterInformation> extractParameterInfo(List<? extends Object> list, SourceInformationContext sourceInformationContext) {
        int i;
        Object next;
        Object obj;
        int iIntValue;
        int iIntValue2;
        List<Parameter> listEmptyList;
        if (!list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                i = 2;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next != null) {
                    String name = next.getClass().getName();
                    Intrinsics.checkNotNullExpressionValue(name, "it.javaClass.name");
                    if (StringsKt.endsWith$default(name, recomposeScopeNameSuffix, false, 2, (Object) null)) {
                        break;
                    }
                }
            }
            if (next != null) {
                try {
                    Field fieldAccessibleField = accessibleField(next.getClass(), "block");
                    if (fieldAccessibleField != null && (obj = fieldAccessibleField.get(next)) != null) {
                        Class<?> cls = obj.getClass();
                        Field fieldAccessibleField2 = accessibleField(cls, defaultFieldName);
                        Field fieldAccessibleField3 = accessibleField(cls, changedFieldName);
                        if (fieldAccessibleField2 != null) {
                            Object obj2 = fieldAccessibleField2.get(obj);
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                            iIntValue = ((Integer) obj2).intValue();
                        } else {
                            iIntValue = 0;
                        }
                        if (fieldAccessibleField3 != null) {
                            Object obj3 = fieldAccessibleField3.get(obj);
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                            iIntValue2 = ((Integer) obj3).intValue();
                        } else {
                            iIntValue2 = 0;
                        }
                        Field[] declaredFields = cls.getDeclaredFields();
                        Intrinsics.checkNotNullExpressionValue(declaredFields, "blockClass.declaredFields");
                        ArrayList arrayList = new ArrayList();
                        for (Field field : declaredFields) {
                            Field field2 = field;
                            String name2 = field2.getName();
                            Intrinsics.checkNotNullExpressionValue(name2, "it.name");
                            if (StringsKt.startsWith$default(name2, parameterPrefix, false, 2, (Object) null)) {
                                String name3 = field2.getName();
                                Intrinsics.checkNotNullExpressionValue(name3, "it.name");
                                if (!StringsKt.startsWith$default(name3, internalFieldPrefix, false, 2, (Object) null)) {
                                    String name4 = field2.getName();
                                    Intrinsics.checkNotNullExpressionValue(name4, "it.name");
                                    if (!StringsKt.startsWith$default(name4, jacocoDataField, false, 2, (Object) null)) {
                                        arrayList.add(field);
                                    }
                                }
                            }
                        }
                        List listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: androidx.compose.ui.tooling.data.SlotTreeKt$extractParameterInfo$$inlined$sortedBy$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                return ComparisonsKt.compareValues(((Field) t).getName(), ((Field) t2).getName());
                            }
                        });
                        ArrayList arrayList2 = new ArrayList();
                        if (sourceInformationContext == null || (listEmptyList = sourceInformationContext.getParameters()) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        int size = listSortedWith.size();
                        int i2 = 0;
                        while (i2 < size) {
                            Parameter parameter = i2 < listEmptyList.size() ? listEmptyList.get(i2) : new Parameter(i2, null, i, null);
                            if (parameter.getSortedIndex() < listSortedWith.size()) {
                                Field field3 = (Field) listSortedWith.get(parameter.getSortedIndex());
                                field3.setAccessible(true);
                                Object obj4 = field3.get(obj);
                                boolean z = ((1 << i2) & iIntValue) != 0;
                                int i3 = (i2 * 3) + 1;
                                int i4 = ((7 << i3) & iIntValue2) >> i3;
                                int i5 = i4 & 3;
                                boolean z2 = i5 == 3;
                                boolean z3 = i5 == 0;
                                boolean z4 = (i4 & 4) == 0;
                                String name5 = field3.getName();
                                Intrinsics.checkNotNullExpressionValue(name5, "field.name");
                                String strSubstring = name5.substring(1);
                                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                                arrayList2.add(new ParameterInformation(strSubstring, obj4, z, z2, z3 && !z, parameter.getInlineClass(), z4));
                            }
                            i2++;
                            i = 2;
                        }
                        return arrayList2;
                    }
                } catch (Throwable unused) {
                }
            }
        }
        return CollectionsKt.emptyList();
    }

    public static final String getPosition(Group group) {
        Intrinsics.checkNotNullParameter(group, "<this>");
        return keyPosition(group.getKey());
    }

    private static final Field accessibleField(Class<?> cls, String str) {
        Field field;
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
        Field[] fieldArr = declaredFields;
        int length = fieldArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                field = null;
                break;
            }
            field = fieldArr[i];
            if (Intrinsics.areEqual(field.getName(), str)) {
                break;
            }
            i++;
        }
        Field field2 = field;
        if (field2 == null) {
            return null;
        }
        field2.setAccessible(true);
        return field2;
    }

    private static final String replacePrefix(String str, String str2, String str3) {
        if (!StringsKt.startsWith$default(str, str2, false, 2, (Object) null)) {
            return str;
        }
        StringBuilder sbAppend = new StringBuilder().append(str3);
        String strSubstring = str.substring(str2.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return sbAppend.append(strSubstring).toString();
    }
}
