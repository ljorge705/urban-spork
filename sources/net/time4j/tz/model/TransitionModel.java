package net.time4j.tz.model;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.time4j.Moment;
import net.time4j.base.GregorianDate;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.base.WallTime;
import net.time4j.engine.EpochDays;
import net.time4j.scale.TimeScale;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZonalOffset;
import net.time4j.tz.ZonalTransition;

/* loaded from: classes4.dex */
public abstract class TransitionModel implements TransitionHistory, Serializable {
    static final String NEW_LINE = System.getProperty("line.separator");

    @Override // net.time4j.tz.TransitionHistory
    public boolean hasNegativeDST() {
        return false;
    }

    @Override // net.time4j.tz.TransitionHistory
    public boolean isEmpty() {
        return false;
    }

    TransitionModel() {
    }

    public static TransitionHistory of(List<ZonalTransition> list) {
        return new ArrayTransitionModel(list);
    }

    public static TransitionHistory of(ZonalOffset zonalOffset, List<DaylightSavingRule> list) {
        if (list.isEmpty()) {
            return new EmptyTransitionModel(zonalOffset);
        }
        return new RuleBasedTransitionModel(zonalOffset, list);
    }

    public static TransitionHistory of(ZonalOffset zonalOffset, List<ZonalTransition> list, List<DaylightSavingRule> list2) {
        return of(zonalOffset, list, list2, true, true);
    }

    static TransitionHistory of(ZonalOffset zonalOffset, List<ZonalTransition> list, List<DaylightSavingRule> list2, boolean z, boolean z2) {
        List<ZonalTransition> list3;
        List<DaylightSavingRule> arrayList;
        if (z) {
            ArrayList arrayList2 = new ArrayList(list);
            arrayList = new ArrayList<>(list2);
            Collections.sort(arrayList2);
            Collections.sort(arrayList, RuleComparator.INSTANCE);
            list3 = arrayList2;
        } else {
            list3 = list;
            arrayList = list2;
        }
        int size = list3.size();
        if (size == 0) {
            if (arrayList.isEmpty()) {
                return new EmptyTransitionModel(zonalOffset);
            }
            return new RuleBasedTransitionModel(zonalOffset, arrayList, false);
        }
        ZonalOffset zonalOffsetOfTotalSeconds = ZonalOffset.ofTotalSeconds(list3.get(0).getPreviousOffset());
        if (z2 && !zonalOffset.equals(zonalOffsetOfTotalSeconds)) {
            throw new IllegalArgumentException("Initial offset " + zonalOffset + " not equal to previous offset of first transition: " + zonalOffsetOfTotalSeconds);
        }
        if (arrayList.isEmpty()) {
            return new ArrayTransitionModel(list3, false, z2);
        }
        ZonalTransition zonalTransition = list3.get(size - 1);
        long posixTime = zonalTransition.getPosixTime() + 1;
        long futureMoment = getFutureMoment(1);
        if (posixTime < futureMoment) {
            list3.addAll(RuleBasedTransitionModel.getTransitions(zonalTransition, arrayList, posixTime, futureMoment));
        }
        return new CompositeTransitionModel(size, list3, arrayList, false, z2);
    }

    static List<ZonalOffset> toList(int i) {
        return Collections.singletonList(ZonalOffset.ofTotalSeconds(i));
    }

    static List<ZonalOffset> toList(int i, int i2) {
        ZonalOffset zonalOffsetOfTotalSeconds = ZonalOffset.ofTotalSeconds(i);
        ZonalOffset zonalOffsetOfTotalSeconds2 = ZonalOffset.ofTotalSeconds(i2);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(zonalOffsetOfTotalSeconds);
        arrayList.add(zonalOffsetOfTotalSeconds2);
        return Collections.unmodifiableList(arrayList);
    }

    static long toLocalSecs(GregorianDate gregorianDate, WallTime wallTime) {
        return MathUtils.safeMultiply(EpochDays.UNIX.transform(GregorianMath.toMJD(gregorianDate.getYear(), gregorianDate.getMonth(), gregorianDate.getDayOfMonth()), EpochDays.MODIFIED_JULIAN_DATE), 86400L) + (wallTime.getHour() * NikonType2MakernoteDirectory.TAG_NIKON_SCAN) + (wallTime.getMinute() * 60) + wallTime.getSecond();
    }

    static void dump(ZonalTransition zonalTransition, Appendable appendable) throws IOException {
        appendable.append(">>> Transition at: ").append(Moment.of(zonalTransition.getPosixTime(), TimeScale.POSIX).toString());
        appendable.append(" from ").append(format(zonalTransition.getPreviousOffset()));
        appendable.append(" to ").append(format(zonalTransition.getTotalOffset()));
        appendable.append(", DST=");
        appendable.append(format(zonalTransition.getDaylightSavingOffset()));
        appendable.append(NEW_LINE);
    }

    static long getFutureMoment(int i) {
        return (System.currentTimeMillis() / 1000) + ((long) (i * 3.1556952E7d));
    }

    private static String format(int i) {
        return ZonalOffset.ofTotalSeconds(i).toString();
    }
}
