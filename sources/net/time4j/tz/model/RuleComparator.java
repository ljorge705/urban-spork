package net.time4j.tz.model;

import java.util.Comparator;

/* loaded from: classes4.dex */
enum RuleComparator implements Comparator<DaylightSavingRule> {
    INSTANCE;

    @Override // java.util.Comparator
    public int compare(DaylightSavingRule daylightSavingRule, DaylightSavingRule daylightSavingRule2) {
        int iCompareTo = daylightSavingRule.getDate(2000).compareTo(daylightSavingRule2.getDate(2000));
        return iCompareTo == 0 ? daylightSavingRule.getTimeOfDay().compareTo(daylightSavingRule2.getTimeOfDay()) : iCompareTo;
    }
}
