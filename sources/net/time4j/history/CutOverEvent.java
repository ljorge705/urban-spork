package net.time4j.history;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.PlainDate;
import net.time4j.engine.EpochDays;

/* loaded from: classes4.dex */
final class CutOverEvent {
    final CalendarAlgorithm algorithm;
    final HistoricDate dateAtCutOver;
    final HistoricDate dateBeforeCutOver;
    final long start;

    public int hashCode() {
        long j = this.start;
        return (int) (j ^ (j >>> 32));
    }

    CutOverEvent(long j, CalendarAlgorithm calendarAlgorithm, CalendarAlgorithm calendarAlgorithm2) {
        this.start = j;
        this.algorithm = calendarAlgorithm2;
        if (j == Long.MIN_VALUE) {
            HistoricDate historicDate = new HistoricDate(HistoricEra.BC, 1000000000, 1, 1);
            this.dateAtCutOver = historicDate;
            this.dateBeforeCutOver = historicDate;
        } else {
            this.dateAtCutOver = calendarAlgorithm2.fromMJD(j);
            this.dateBeforeCutOver = calendarAlgorithm.fromMJD(j - 1);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CutOverEvent)) {
            return false;
        }
        CutOverEvent cutOverEvent = (CutOverEvent) obj;
        return this.start == cutOverEvent.start && this.algorithm == cutOverEvent.algorithm && this.dateBeforeCutOver.equals(cutOverEvent.dateBeforeCutOver);
    }

    public String toString() {
        return getClass().getName() + "[start=" + this.start + " (" + PlainDate.of(this.start, EpochDays.MODIFIED_JULIAN_DATE) + "),algorithm=" + this.algorithm + ",date-before-cutover=" + this.dateBeforeCutOver + ",date-at-cutover=" + this.dateAtCutOver + AbstractJsonLexerKt.END_LIST;
    }
}
