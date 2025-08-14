package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.vector.PathNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: PathNode.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\b2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020!0&H\u0082\b\u001a\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020!0 *\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"ArcToKey", "", "CloseKey", "CurveToKey", "HorizontalToKey", "LineToKey", "MoveToKey", "NUM_ARC_TO_ARGS", "", "NUM_CURVE_TO_ARGS", "NUM_HORIZONTAL_TO_ARGS", "NUM_LINE_TO_ARGS", "NUM_MOVE_TO_ARGS", "NUM_QUAD_TO_ARGS", "NUM_REFLECTIVE_CURVE_TO_ARGS", "NUM_REFLECTIVE_QUAD_TO_ARGS", "NUM_VERTICAL_TO_ARGS", "QuadToKey", "ReflectiveCurveToKey", "ReflectiveQuadToKey", "RelativeArcToKey", "RelativeCloseKey", "RelativeCurveToKey", "RelativeHorizontalToKey", "RelativeLineToKey", "RelativeMoveToKey", "RelativeQuadToKey", "RelativeReflectiveCurveToKey", "RelativeReflectiveQuadToKey", "RelativeVerticalToKey", "VerticalToKey", "pathNodesFromArgs", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "args", "", "numArgs", "nodeFor", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "subArray", "toPathNodes", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PathNodeKt {
    private static final char ArcToKey = 'A';
    private static final char CloseKey = 'Z';
    private static final char CurveToKey = 'C';
    private static final char HorizontalToKey = 'H';
    private static final char LineToKey = 'L';
    private static final char MoveToKey = 'M';
    private static final int NUM_ARC_TO_ARGS = 7;
    private static final int NUM_CURVE_TO_ARGS = 6;
    private static final int NUM_HORIZONTAL_TO_ARGS = 1;
    private static final int NUM_LINE_TO_ARGS = 2;
    private static final int NUM_MOVE_TO_ARGS = 2;
    private static final int NUM_QUAD_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_CURVE_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_QUAD_TO_ARGS = 2;
    private static final int NUM_VERTICAL_TO_ARGS = 1;
    private static final char QuadToKey = 'Q';
    private static final char ReflectiveCurveToKey = 'S';
    private static final char ReflectiveQuadToKey = 'T';
    private static final char RelativeArcToKey = 'a';
    private static final char RelativeCloseKey = 'z';
    private static final char RelativeCurveToKey = 'c';
    private static final char RelativeHorizontalToKey = 'h';
    private static final char RelativeLineToKey = 'l';
    private static final char RelativeMoveToKey = 'm';
    private static final char RelativeQuadToKey = 'q';
    private static final char RelativeReflectiveCurveToKey = 's';
    private static final char RelativeReflectiveQuadToKey = 't';
    private static final char RelativeVerticalToKey = 'v';
    private static final char VerticalToKey = 'V';

    public static final List<PathNode> toPathNodes(char c, float[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        if (c == 'z' || c == 'Z') {
            return CollectionsKt.listOf(PathNode.Close.INSTANCE);
        }
        if (c == 'm') {
            IntProgression intProgressionStep = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep, 10));
            Iterator<Integer> it = intProgressionStep.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                float[] fArrCopyOfRange = ArraysKt.copyOfRange(args, iNextInt, iNextInt + 2);
                PathNode relativeMoveTo = new PathNode.RelativeMoveTo(fArrCopyOfRange[0], fArrCopyOfRange[1]);
                if ((relativeMoveTo instanceof PathNode.MoveTo) && iNextInt > 0) {
                    relativeMoveTo = new PathNode.LineTo(fArrCopyOfRange[0], fArrCopyOfRange[1]);
                } else if (iNextInt > 0) {
                    relativeMoveTo = new PathNode.RelativeLineTo(fArrCopyOfRange[0], fArrCopyOfRange[1]);
                }
                arrayList.add(relativeMoveTo);
            }
            return arrayList;
        }
        if (c == 'M') {
            IntProgression intProgressionStep2 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep2, 10));
            Iterator<Integer> it2 = intProgressionStep2.iterator();
            while (it2.hasNext()) {
                int iNextInt2 = ((IntIterator) it2).nextInt();
                float[] fArrCopyOfRange2 = ArraysKt.copyOfRange(args, iNextInt2, iNextInt2 + 2);
                PathNode moveTo = new PathNode.MoveTo(fArrCopyOfRange2[0], fArrCopyOfRange2[1]);
                if (iNextInt2 > 0) {
                    moveTo = new PathNode.LineTo(fArrCopyOfRange2[0], fArrCopyOfRange2[1]);
                } else if ((moveTo instanceof PathNode.RelativeMoveTo) && iNextInt2 > 0) {
                    moveTo = new PathNode.RelativeLineTo(fArrCopyOfRange2[0], fArrCopyOfRange2[1]);
                }
                arrayList2.add(moveTo);
            }
            return arrayList2;
        }
        if (c == 'l') {
            IntProgression intProgressionStep3 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep3, 10));
            Iterator<Integer> it3 = intProgressionStep3.iterator();
            while (it3.hasNext()) {
                int iNextInt3 = ((IntIterator) it3).nextInt();
                float[] fArrCopyOfRange3 = ArraysKt.copyOfRange(args, iNextInt3, iNextInt3 + 2);
                PathNode relativeLineTo = new PathNode.RelativeLineTo(fArrCopyOfRange3[0], fArrCopyOfRange3[1]);
                if ((relativeLineTo instanceof PathNode.MoveTo) && iNextInt3 > 0) {
                    relativeLineTo = new PathNode.LineTo(fArrCopyOfRange3[0], fArrCopyOfRange3[1]);
                } else if ((relativeLineTo instanceof PathNode.RelativeMoveTo) && iNextInt3 > 0) {
                    relativeLineTo = new PathNode.RelativeLineTo(fArrCopyOfRange3[0], fArrCopyOfRange3[1]);
                }
                arrayList3.add(relativeLineTo);
            }
            return arrayList3;
        }
        if (c == 'L') {
            IntProgression intProgressionStep4 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep4, 10));
            Iterator<Integer> it4 = intProgressionStep4.iterator();
            while (it4.hasNext()) {
                int iNextInt4 = ((IntIterator) it4).nextInt();
                float[] fArrCopyOfRange4 = ArraysKt.copyOfRange(args, iNextInt4, iNextInt4 + 2);
                PathNode lineTo = new PathNode.LineTo(fArrCopyOfRange4[0], fArrCopyOfRange4[1]);
                if ((lineTo instanceof PathNode.MoveTo) && iNextInt4 > 0) {
                    lineTo = new PathNode.LineTo(fArrCopyOfRange4[0], fArrCopyOfRange4[1]);
                } else if ((lineTo instanceof PathNode.RelativeMoveTo) && iNextInt4 > 0) {
                    lineTo = new PathNode.RelativeLineTo(fArrCopyOfRange4[0], fArrCopyOfRange4[1]);
                }
                arrayList4.add(lineTo);
            }
            return arrayList4;
        }
        if (c == 'h') {
            IntProgression intProgressionStep5 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep5, 10));
            Iterator<Integer> it5 = intProgressionStep5.iterator();
            while (it5.hasNext()) {
                int iNextInt5 = ((IntIterator) it5).nextInt();
                float[] fArrCopyOfRange5 = ArraysKt.copyOfRange(args, iNextInt5, iNextInt5 + 1);
                PathNode relativeHorizontalTo = new PathNode.RelativeHorizontalTo(fArrCopyOfRange5[0]);
                if ((relativeHorizontalTo instanceof PathNode.MoveTo) && iNextInt5 > 0) {
                    relativeHorizontalTo = new PathNode.LineTo(fArrCopyOfRange5[0], fArrCopyOfRange5[1]);
                } else if ((relativeHorizontalTo instanceof PathNode.RelativeMoveTo) && iNextInt5 > 0) {
                    relativeHorizontalTo = new PathNode.RelativeLineTo(fArrCopyOfRange5[0], fArrCopyOfRange5[1]);
                }
                arrayList5.add(relativeHorizontalTo);
            }
            return arrayList5;
        }
        if (c == 'H') {
            IntProgression intProgressionStep6 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep6, 10));
            Iterator<Integer> it6 = intProgressionStep6.iterator();
            while (it6.hasNext()) {
                int iNextInt6 = ((IntIterator) it6).nextInt();
                float[] fArrCopyOfRange6 = ArraysKt.copyOfRange(args, iNextInt6, iNextInt6 + 1);
                PathNode horizontalTo = new PathNode.HorizontalTo(fArrCopyOfRange6[0]);
                if ((horizontalTo instanceof PathNode.MoveTo) && iNextInt6 > 0) {
                    horizontalTo = new PathNode.LineTo(fArrCopyOfRange6[0], fArrCopyOfRange6[1]);
                } else if ((horizontalTo instanceof PathNode.RelativeMoveTo) && iNextInt6 > 0) {
                    horizontalTo = new PathNode.RelativeLineTo(fArrCopyOfRange6[0], fArrCopyOfRange6[1]);
                }
                arrayList6.add(horizontalTo);
            }
            return arrayList6;
        }
        if (c == 'v') {
            IntProgression intProgressionStep7 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep7, 10));
            Iterator<Integer> it7 = intProgressionStep7.iterator();
            while (it7.hasNext()) {
                int iNextInt7 = ((IntIterator) it7).nextInt();
                float[] fArrCopyOfRange7 = ArraysKt.copyOfRange(args, iNextInt7, iNextInt7 + 1);
                PathNode relativeVerticalTo = new PathNode.RelativeVerticalTo(fArrCopyOfRange7[0]);
                if ((relativeVerticalTo instanceof PathNode.MoveTo) && iNextInt7 > 0) {
                    relativeVerticalTo = new PathNode.LineTo(fArrCopyOfRange7[0], fArrCopyOfRange7[1]);
                } else if ((relativeVerticalTo instanceof PathNode.RelativeMoveTo) && iNextInt7 > 0) {
                    relativeVerticalTo = new PathNode.RelativeLineTo(fArrCopyOfRange7[0], fArrCopyOfRange7[1]);
                }
                arrayList7.add(relativeVerticalTo);
            }
            return arrayList7;
        }
        if (c == 'V') {
            IntProgression intProgressionStep8 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep8, 10));
            Iterator<Integer> it8 = intProgressionStep8.iterator();
            while (it8.hasNext()) {
                int iNextInt8 = ((IntIterator) it8).nextInt();
                float[] fArrCopyOfRange8 = ArraysKt.copyOfRange(args, iNextInt8, iNextInt8 + 1);
                PathNode verticalTo = new PathNode.VerticalTo(fArrCopyOfRange8[0]);
                if ((verticalTo instanceof PathNode.MoveTo) && iNextInt8 > 0) {
                    verticalTo = new PathNode.LineTo(fArrCopyOfRange8[0], fArrCopyOfRange8[1]);
                } else if ((verticalTo instanceof PathNode.RelativeMoveTo) && iNextInt8 > 0) {
                    verticalTo = new PathNode.RelativeLineTo(fArrCopyOfRange8[0], fArrCopyOfRange8[1]);
                }
                arrayList8.add(verticalTo);
            }
            return arrayList8;
        }
        if (c == 'c') {
            IntProgression intProgressionStep9 = RangesKt.step(new IntRange(0, args.length - 6), 6);
            ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep9, 10));
            Iterator<Integer> it9 = intProgressionStep9.iterator();
            while (it9.hasNext()) {
                int iNextInt9 = ((IntIterator) it9).nextInt();
                float[] fArrCopyOfRange9 = ArraysKt.copyOfRange(args, iNextInt9, iNextInt9 + 6);
                PathNode relativeCurveTo = new PathNode.RelativeCurveTo(fArrCopyOfRange9[0], fArrCopyOfRange9[1], fArrCopyOfRange9[2], fArrCopyOfRange9[3], fArrCopyOfRange9[4], fArrCopyOfRange9[5]);
                if ((relativeCurveTo instanceof PathNode.MoveTo) && iNextInt9 > 0) {
                    relativeCurveTo = new PathNode.LineTo(fArrCopyOfRange9[0], fArrCopyOfRange9[1]);
                } else if ((relativeCurveTo instanceof PathNode.RelativeMoveTo) && iNextInt9 > 0) {
                    relativeCurveTo = new PathNode.RelativeLineTo(fArrCopyOfRange9[0], fArrCopyOfRange9[1]);
                }
                arrayList9.add(relativeCurveTo);
            }
            return arrayList9;
        }
        if (c == 'C') {
            IntProgression intProgressionStep10 = RangesKt.step(new IntRange(0, args.length - 6), 6);
            ArrayList arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep10, 10));
            Iterator<Integer> it10 = intProgressionStep10.iterator();
            while (it10.hasNext()) {
                int iNextInt10 = ((IntIterator) it10).nextInt();
                float[] fArrCopyOfRange10 = ArraysKt.copyOfRange(args, iNextInt10, iNextInt10 + 6);
                PathNode curveTo = new PathNode.CurveTo(fArrCopyOfRange10[0], fArrCopyOfRange10[1], fArrCopyOfRange10[2], fArrCopyOfRange10[3], fArrCopyOfRange10[4], fArrCopyOfRange10[5]);
                if ((curveTo instanceof PathNode.MoveTo) && iNextInt10 > 0) {
                    curveTo = new PathNode.LineTo(fArrCopyOfRange10[0], fArrCopyOfRange10[1]);
                } else if ((curveTo instanceof PathNode.RelativeMoveTo) && iNextInt10 > 0) {
                    curveTo = new PathNode.RelativeLineTo(fArrCopyOfRange10[0], fArrCopyOfRange10[1]);
                }
                arrayList10.add(curveTo);
            }
            return arrayList10;
        }
        if (c == 's') {
            IntProgression intProgressionStep11 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList11 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep11, 10));
            Iterator<Integer> it11 = intProgressionStep11.iterator();
            while (it11.hasNext()) {
                int iNextInt11 = ((IntIterator) it11).nextInt();
                float[] fArrCopyOfRange11 = ArraysKt.copyOfRange(args, iNextInt11, iNextInt11 + 4);
                PathNode relativeReflectiveCurveTo = new PathNode.RelativeReflectiveCurveTo(fArrCopyOfRange11[0], fArrCopyOfRange11[1], fArrCopyOfRange11[2], fArrCopyOfRange11[3]);
                if ((relativeReflectiveCurveTo instanceof PathNode.MoveTo) && iNextInt11 > 0) {
                    relativeReflectiveCurveTo = new PathNode.LineTo(fArrCopyOfRange11[0], fArrCopyOfRange11[1]);
                } else if ((relativeReflectiveCurveTo instanceof PathNode.RelativeMoveTo) && iNextInt11 > 0) {
                    relativeReflectiveCurveTo = new PathNode.RelativeLineTo(fArrCopyOfRange11[0], fArrCopyOfRange11[1]);
                }
                arrayList11.add(relativeReflectiveCurveTo);
            }
            return arrayList11;
        }
        if (c == 'S') {
            IntProgression intProgressionStep12 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep12, 10));
            Iterator<Integer> it12 = intProgressionStep12.iterator();
            while (it12.hasNext()) {
                int iNextInt12 = ((IntIterator) it12).nextInt();
                float[] fArrCopyOfRange12 = ArraysKt.copyOfRange(args, iNextInt12, iNextInt12 + 4);
                PathNode reflectiveCurveTo = new PathNode.ReflectiveCurveTo(fArrCopyOfRange12[0], fArrCopyOfRange12[1], fArrCopyOfRange12[2], fArrCopyOfRange12[3]);
                if ((reflectiveCurveTo instanceof PathNode.MoveTo) && iNextInt12 > 0) {
                    reflectiveCurveTo = new PathNode.LineTo(fArrCopyOfRange12[0], fArrCopyOfRange12[1]);
                } else if ((reflectiveCurveTo instanceof PathNode.RelativeMoveTo) && iNextInt12 > 0) {
                    reflectiveCurveTo = new PathNode.RelativeLineTo(fArrCopyOfRange12[0], fArrCopyOfRange12[1]);
                }
                arrayList12.add(reflectiveCurveTo);
            }
            return arrayList12;
        }
        if (c == 'q') {
            IntProgression intProgressionStep13 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList13 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep13, 10));
            Iterator<Integer> it13 = intProgressionStep13.iterator();
            while (it13.hasNext()) {
                int iNextInt13 = ((IntIterator) it13).nextInt();
                float[] fArrCopyOfRange13 = ArraysKt.copyOfRange(args, iNextInt13, iNextInt13 + 4);
                PathNode relativeQuadTo = new PathNode.RelativeQuadTo(fArrCopyOfRange13[0], fArrCopyOfRange13[1], fArrCopyOfRange13[2], fArrCopyOfRange13[3]);
                if ((relativeQuadTo instanceof PathNode.MoveTo) && iNextInt13 > 0) {
                    relativeQuadTo = new PathNode.LineTo(fArrCopyOfRange13[0], fArrCopyOfRange13[1]);
                } else if ((relativeQuadTo instanceof PathNode.RelativeMoveTo) && iNextInt13 > 0) {
                    relativeQuadTo = new PathNode.RelativeLineTo(fArrCopyOfRange13[0], fArrCopyOfRange13[1]);
                }
                arrayList13.add(relativeQuadTo);
            }
            return arrayList13;
        }
        if (c == 'Q') {
            IntProgression intProgressionStep14 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList14 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep14, 10));
            Iterator<Integer> it14 = intProgressionStep14.iterator();
            while (it14.hasNext()) {
                int iNextInt14 = ((IntIterator) it14).nextInt();
                float[] fArrCopyOfRange14 = ArraysKt.copyOfRange(args, iNextInt14, iNextInt14 + 4);
                PathNode quadTo = new PathNode.QuadTo(fArrCopyOfRange14[0], fArrCopyOfRange14[1], fArrCopyOfRange14[2], fArrCopyOfRange14[3]);
                if ((quadTo instanceof PathNode.MoveTo) && iNextInt14 > 0) {
                    quadTo = new PathNode.LineTo(fArrCopyOfRange14[0], fArrCopyOfRange14[1]);
                } else if ((quadTo instanceof PathNode.RelativeMoveTo) && iNextInt14 > 0) {
                    quadTo = new PathNode.RelativeLineTo(fArrCopyOfRange14[0], fArrCopyOfRange14[1]);
                }
                arrayList14.add(quadTo);
            }
            return arrayList14;
        }
        if (c == 't') {
            IntProgression intProgressionStep15 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList15 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep15, 10));
            Iterator<Integer> it15 = intProgressionStep15.iterator();
            while (it15.hasNext()) {
                int iNextInt15 = ((IntIterator) it15).nextInt();
                float[] fArrCopyOfRange15 = ArraysKt.copyOfRange(args, iNextInt15, iNextInt15 + 2);
                PathNode relativeReflectiveQuadTo = new PathNode.RelativeReflectiveQuadTo(fArrCopyOfRange15[0], fArrCopyOfRange15[1]);
                if ((relativeReflectiveQuadTo instanceof PathNode.MoveTo) && iNextInt15 > 0) {
                    relativeReflectiveQuadTo = new PathNode.LineTo(fArrCopyOfRange15[0], fArrCopyOfRange15[1]);
                } else if ((relativeReflectiveQuadTo instanceof PathNode.RelativeMoveTo) && iNextInt15 > 0) {
                    relativeReflectiveQuadTo = new PathNode.RelativeLineTo(fArrCopyOfRange15[0], fArrCopyOfRange15[1]);
                }
                arrayList15.add(relativeReflectiveQuadTo);
            }
            return arrayList15;
        }
        if (c == 'T') {
            IntProgression intProgressionStep16 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList16 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep16, 10));
            Iterator<Integer> it16 = intProgressionStep16.iterator();
            while (it16.hasNext()) {
                int iNextInt16 = ((IntIterator) it16).nextInt();
                float[] fArrCopyOfRange16 = ArraysKt.copyOfRange(args, iNextInt16, iNextInt16 + 2);
                PathNode reflectiveQuadTo = new PathNode.ReflectiveQuadTo(fArrCopyOfRange16[0], fArrCopyOfRange16[1]);
                if ((reflectiveQuadTo instanceof PathNode.MoveTo) && iNextInt16 > 0) {
                    reflectiveQuadTo = new PathNode.LineTo(fArrCopyOfRange16[0], fArrCopyOfRange16[1]);
                } else if ((reflectiveQuadTo instanceof PathNode.RelativeMoveTo) && iNextInt16 > 0) {
                    reflectiveQuadTo = new PathNode.RelativeLineTo(fArrCopyOfRange16[0], fArrCopyOfRange16[1]);
                }
                arrayList16.add(reflectiveQuadTo);
            }
            return arrayList16;
        }
        if (c == 'a') {
            IntProgression intProgressionStep17 = RangesKt.step(new IntRange(0, args.length - 7), 7);
            ArrayList arrayList17 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep17, 10));
            Iterator<Integer> it17 = intProgressionStep17.iterator();
            while (it17.hasNext()) {
                int iNextInt17 = ((IntIterator) it17).nextInt();
                float[] fArrCopyOfRange17 = ArraysKt.copyOfRange(args, iNextInt17, iNextInt17 + 7);
                PathNode relativeArcTo = new PathNode.RelativeArcTo(fArrCopyOfRange17[0], fArrCopyOfRange17[1], fArrCopyOfRange17[2], Float.compare(fArrCopyOfRange17[3], 0.0f) != 0, Float.compare(fArrCopyOfRange17[4], 0.0f) != 0, fArrCopyOfRange17[5], fArrCopyOfRange17[6]);
                if ((relativeArcTo instanceof PathNode.MoveTo) && iNextInt17 > 0) {
                    relativeArcTo = new PathNode.LineTo(fArrCopyOfRange17[0], fArrCopyOfRange17[1]);
                } else if ((relativeArcTo instanceof PathNode.RelativeMoveTo) && iNextInt17 > 0) {
                    relativeArcTo = new PathNode.RelativeLineTo(fArrCopyOfRange17[0], fArrCopyOfRange17[1]);
                }
                arrayList17.add(relativeArcTo);
            }
            return arrayList17;
        }
        if (c != 'A') {
            throw new IllegalArgumentException("Unknown command for: " + c);
        }
        IntProgression intProgressionStep18 = RangesKt.step(new IntRange(0, args.length - 7), 7);
        ArrayList arrayList18 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep18, 10));
        Iterator<Integer> it18 = intProgressionStep18.iterator();
        while (it18.hasNext()) {
            int iNextInt18 = ((IntIterator) it18).nextInt();
            float[] fArrCopyOfRange18 = ArraysKt.copyOfRange(args, iNextInt18, iNextInt18 + 7);
            PathNode arcTo = new PathNode.ArcTo(fArrCopyOfRange18[0], fArrCopyOfRange18[1], fArrCopyOfRange18[2], Float.compare(fArrCopyOfRange18[3], 0.0f) != 0, Float.compare(fArrCopyOfRange18[4], 0.0f) != 0, fArrCopyOfRange18[5], fArrCopyOfRange18[6]);
            if ((arcTo instanceof PathNode.MoveTo) && iNextInt18 > 0) {
                arcTo = new PathNode.LineTo(fArrCopyOfRange18[0], fArrCopyOfRange18[1]);
            } else if ((arcTo instanceof PathNode.RelativeMoveTo) && iNextInt18 > 0) {
                arcTo = new PathNode.RelativeLineTo(fArrCopyOfRange18[0], fArrCopyOfRange18[1]);
            }
            arrayList18.add(arcTo);
        }
        return arrayList18;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [float[], java.lang.Object] */
    private static final List<PathNode> pathNodesFromArgs(float[] fArr, int i, Function1<? super float[], ? extends PathNode> function1) {
        IntProgression intProgressionStep = RangesKt.step(new IntRange(0, fArr.length - i), i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep, 10));
        Iterator<Integer> it = intProgressionStep.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            ?? CopyOfRange = ArraysKt.copyOfRange(fArr, iNextInt, iNextInt + i);
            PathNode.RelativeLineTo relativeLineToInvoke = function1.invoke(CopyOfRange);
            if ((relativeLineToInvoke instanceof PathNode.MoveTo) && iNextInt > 0) {
                relativeLineToInvoke = new PathNode.LineTo(CopyOfRange[0], CopyOfRange[1]);
            } else if ((relativeLineToInvoke instanceof PathNode.RelativeMoveTo) && iNextInt > 0) {
                relativeLineToInvoke = new PathNode.RelativeLineTo(CopyOfRange[0], CopyOfRange[1]);
            }
            arrayList.add(relativeLineToInvoke);
        }
        return arrayList;
    }
}
