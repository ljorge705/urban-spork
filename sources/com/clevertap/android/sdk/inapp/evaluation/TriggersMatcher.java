package com.clevertap.android.sdk.inapp.evaluation;

import android.location.Location;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: TriggersMatcher.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\nJ\u001d\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\fJ\u001e\u0010\r\u001a\u00020\u00062\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0002J%\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0014J%\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0018H\u0001¢\u0006\u0002\b\u0019J\u001d\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\u001bJ%\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u001c\u0010&\u001a\u00020\u00062\f\u0010'\u001a\b\u0012\u0004\u0012\u00020!0\u000f2\u0006\u0010\"\u001a\u00020#J\u0010\u0010(\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0003J\u001d\u0010)\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b*J\u0018\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggersMatcher;", "", "localDataStore", "Lcom/clevertap/android/sdk/LocalDataStore;", "(Lcom/clevertap/android/sdk/LocalDataStore;)V", "actualContainsExpected", "", "expected", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerValue;", "actual", "actualContainsExpected$clevertap_core_release", "actualIsInRangeOfExpected", "actualIsInRangeOfExpected$clevertap_core_release", "checkGivenElementEqualsAnyElementInList", "list", "", "elementToCheckForEquality", "evaluate", "op", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerOperator;", "evaluate$clevertap_core_release", "evaluateDistance", Constants.KEY_RADIUS, "", "Landroid/location/Location;", "evaluateDistance$clevertap_core_release", "expectedValueEqualsActual", "expectedValueEqualsActual$clevertap_core_release", "expectedValueLessThanGreaterThanActual", "isLessThan", "expectedValueLessThanGreaterThanActual$clevertap_core_release", "match", "trigger", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerAdapter;", "event", "Lcom/clevertap/android/sdk/inapp/evaluation/EventAdapter;", "match$clevertap_core_release", "matchChargedItemConditions", "matchEvent", Constants.INAPP_WHEN_TRIGGERS, "matchFirstTimeOnly", "matchGeoRadius", "matchGeoRadius$clevertap_core_release", "matchPropertyConditions", "triggerAdapter", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class TriggersMatcher {
    private final LocalDataStore localDataStore;

    /* compiled from: TriggersMatcher.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TriggerOperator.values().length];
            try {
                iArr[TriggerOperator.Set.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TriggerOperator.LessThan.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TriggerOperator.GreaterThan.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TriggerOperator.Equals.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TriggerOperator.NotEquals.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TriggerOperator.Between.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TriggerOperator.Contains.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TriggerOperator.NotContains.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TriggerOperator.NotSet.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TriggersMatcher(LocalDataStore localDataStore) {
        Intrinsics.checkNotNullParameter(localDataStore, "localDataStore");
        this.localDataStore = localDataStore;
    }

    public final boolean matchEvent(List<TriggerAdapter> whenTriggers, EventAdapter event) {
        Intrinsics.checkNotNullParameter(whenTriggers, "whenTriggers");
        Intrinsics.checkNotNullParameter(event, "event");
        List<TriggerAdapter> list = whenTriggers;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (match$clevertap_core_release((TriggerAdapter) it.next(), event)) {
                return true;
            }
        }
        return false;
    }

    public final boolean match$clevertap_core_release(TriggerAdapter trigger, EventAdapter event) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        Intrinsics.checkNotNullParameter(event, "event");
        if ((!Utils.areNamesNormalizedEqual(event.getEventName(), trigger.getEventName()) && (event.getProfileAttrName() == null || !Utils.areNamesNormalizedEqual(event.getProfileAttrName(), trigger.getProfileAttrName()))) || !matchPropertyConditions(trigger, event) || !matchFirstTimeOnly(trigger)) {
            return false;
        }
        if (!event.isChargedEvent() || matchChargedItemConditions(trigger, event)) {
            return trigger.getGeoRadiusCount() <= 0 || matchGeoRadius$clevertap_core_release(event, trigger);
        }
        return false;
    }

    private final boolean matchFirstTimeOnly(TriggerAdapter trigger) {
        if (!trigger.getFirstTimeOnly()) {
            return true;
        }
        String profileAttrName = trigger.getProfileAttrName();
        if (profileAttrName == null) {
            profileAttrName = trigger.getEventName();
        }
        return this.localDataStore.isUserEventLogFirstTime(profileAttrName);
    }

    private final boolean matchPropertyConditions(TriggerAdapter triggerAdapter, EventAdapter event) {
        IntRange intRangeUntil = RangesKt.until(0, triggerAdapter.getPropertyCount());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            TriggerCondition triggerConditionPropertyAtIndex = triggerAdapter.propertyAtIndex(((IntIterator) it).nextInt());
            if (triggerConditionPropertyAtIndex != null) {
                arrayList.add(triggerConditionPropertyAtIndex);
            }
        }
        ArrayList<TriggerCondition> arrayList2 = arrayList;
        if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
            for (TriggerCondition triggerCondition : arrayList2) {
                if (!evaluate$clevertap_core_release(triggerCondition.getOp(), triggerCondition.getValue(), event.getPropertyValue(triggerCondition.getPropertyName()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private final boolean matchChargedItemConditions(TriggerAdapter trigger, EventAdapter event) {
        IntRange intRangeUntil = RangesKt.until(0, trigger.getItemsCount());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            TriggerCondition triggerConditionItemAtIndex = trigger.itemAtIndex(((IntIterator) it).nextInt());
            if (triggerConditionItemAtIndex != null) {
                arrayList.add(triggerConditionItemAtIndex);
            }
        }
        ArrayList<TriggerCondition> arrayList2 = arrayList;
        if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
            for (TriggerCondition triggerCondition : arrayList2) {
                List<TriggerValue> itemValue = event.getItemValue(triggerCondition.getPropertyName());
                if ((itemValue instanceof Collection) && itemValue.isEmpty()) {
                    return false;
                }
                Iterator<T> it2 = itemValue.iterator();
                while (it2.hasNext()) {
                    if (evaluate$clevertap_core_release(triggerCondition.getOp(), triggerCondition.getValue(), (TriggerValue) it2.next())) {
                        break;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final boolean matchGeoRadius$clevertap_core_release(EventAdapter event, TriggerAdapter trigger) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        if (event.getUserLocation() != null && CTXtensions.isValid(event.getUserLocation())) {
            int geoRadiusCount = trigger.getGeoRadiusCount();
            for (int i = 0; i < geoRadiusCount; i++) {
                TriggerGeoRadius triggerGeoRadiusGeoRadiusAtIndex = trigger.geoRadiusAtIndex(i);
                Location location = new Location("");
                Intrinsics.checkNotNull(triggerGeoRadiusGeoRadiusAtIndex);
                location.setLatitude(triggerGeoRadiusGeoRadiusAtIndex.getLatitude());
                location.setLongitude(triggerGeoRadiusGeoRadiusAtIndex.getLongitude());
                try {
                } catch (Exception e) {
                    Logger.d("Error matching GeoRadius triggers for event named " + event.getEventName() + ". Reason: " + e.getLocalizedMessage());
                }
                if (evaluateDistance$clevertap_core_release(triggerGeoRadiusGeoRadiusAtIndex.getRadius(), location, event.getUserLocation())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean evaluate$clevertap_core_release(TriggerOperator op, TriggerValue expected, TriggerValue actual) {
        Intrinsics.checkNotNullParameter(op, "op");
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(actual, "actual");
        if (actual.getValue() == null) {
            return op == TriggerOperator.NotSet;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[op.ordinal()]) {
            case 1:
                break;
            case 2:
                return expectedValueLessThanGreaterThanActual$clevertap_core_release(expected, actual, true);
            case 3:
                return expectedValueLessThanGreaterThanActual$clevertap_core_release(expected, actual, false);
            case 4:
                return expectedValueEqualsActual$clevertap_core_release(expected, actual);
            case 5:
                if (expectedValueEqualsActual$clevertap_core_release(expected, actual)) {
                    return false;
                }
                break;
            case 6:
                return actualIsInRangeOfExpected$clevertap_core_release(expected, actual);
            case 7:
                return actualContainsExpected$clevertap_core_release(expected, actual);
            case 8:
                if (actualContainsExpected$clevertap_core_release(expected, actual)) {
                    return false;
                }
                break;
            case 9:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return true;
    }

    public final boolean evaluateDistance$clevertap_core_release(double radius, Location expected, Location actual) {
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(actual, "actual");
        return Utils.haversineDistance(expected, actual) <= radius;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0096, code lost:
    
        if (r6.doubleValue() == r3) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00be, code lost:
    
        if (r6.doubleValue() == r3) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:?, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean expectedValueEqualsActual$clevertap_core_release(com.clevertap.android.sdk.inapp.evaluation.TriggerValue r6, com.clevertap.android.sdk.inapp.evaluation.TriggerValue r7) {
        /*
            r5 = this;
            java.lang.String r0 = "expected"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "actual"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r6.isList()
            if (r0 == 0) goto L36
            boolean r0 = r7.isList()
            if (r0 == 0) goto L36
            java.util.List r6 = r6.listValueWithCleanedStringIfPresent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.HashSet r6 = kotlin.collections.CollectionsKt.toHashSet(r6)
            java.util.List r7 = r7.listValueWithCleanedStringIfPresent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.HashSet r7 = kotlin.collections.CollectionsKt.toHashSet(r7)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            goto Ld6
        L36:
            boolean r0 = r7.isList()
            if (r0 == 0) goto L4d
            java.util.List r7 = r7.listValueWithCleanedStringIfPresent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.Object r6 = r6.getValue()
            boolean r6 = r5.checkGivenElementEqualsAnyElementInList(r7, r6)
            goto Ld6
        L4d:
            boolean r0 = r6.isList()
            if (r0 == 0) goto L64
            java.util.List r6 = r6.listValueWithCleanedStringIfPresent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.Object r7 = r7.getValue()
            boolean r6 = r5.checkGivenElementEqualsAnyElementInList(r6, r7)
            goto Ld6
        L64:
            java.lang.Number r0 = r6.getNumberValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L9b
            java.lang.Number r0 = r7.getNumberValue()
            if (r0 == 0) goto L77
            double r3 = r0.doubleValue()
            goto L89
        L77:
            java.lang.String r7 = r7.getStringValueCleaned()
            if (r7 == 0) goto L82
            java.lang.Double r7 = kotlin.text.StringsKt.toDoubleOrNull(r7)
            goto L83
        L82:
            r7 = 0
        L83:
            if (r7 == 0) goto L9a
            double r3 = r7.doubleValue()
        L89:
            java.lang.Number r6 = r6.getNumberValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            double r6 = r6.doubleValue()
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 != 0) goto Ld5
        L98:
            r6 = r1
            goto Ld6
        L9a:
            return r2
        L9b:
            java.lang.Number r0 = r7.getNumberValue()
            if (r0 == 0) goto Lc2
            java.lang.String r6 = r6.getStringValueCleaned()
            if (r6 == 0) goto Lc1
            java.lang.Double r6 = kotlin.text.StringsKt.toDoubleOrNull(r6)
            if (r6 == 0) goto Lc1
            double r3 = r6.doubleValue()
            java.lang.Number r6 = r7.getNumberValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            double r6 = r6.doubleValue()
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 != 0) goto Ld5
            goto L98
        Lc1:
            return r2
        Lc2:
            java.lang.String r0 = r7.getStringValue()
            if (r0 == 0) goto Ld5
            java.lang.String r6 = r6.getStringValueCleaned()
            java.lang.String r7 = r7.getStringValueCleaned()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            goto Ld6
        Ld5:
            r6 = r2
        Ld6:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher.expectedValueEqualsActual$clevertap_core_release(com.clevertap.android.sdk.inapp.evaluation.TriggerValue, com.clevertap.android.sdk.inapp.evaluation.TriggerValue):boolean");
    }

    public final boolean expectedValueLessThanGreaterThanActual$clevertap_core_release(TriggerValue expected, TriggerValue actual, boolean isLessThan) {
        double dDoubleValue;
        double dDoubleValue2;
        Object objFirstOrNull;
        Double dValueOf;
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(actual, "actual");
        Number numberValue = actual.getNumberValue();
        if (numberValue != null) {
            dDoubleValue = numberValue.doubleValue();
        } else {
            String stringValue = actual.getStringValue();
            Double doubleOrNull = stringValue != null ? StringsKt.toDoubleOrNull(stringValue) : null;
            if (doubleOrNull == null) {
                return false;
            }
            dDoubleValue = doubleOrNull.doubleValue();
        }
        List<?> listListValue = expected.listValue();
        if (listListValue != null && (objFirstOrNull = CollectionsKt.firstOrNull((List<? extends Object>) listListValue)) != null) {
            if (objFirstOrNull instanceof String) {
                dValueOf = StringsKt.toDoubleOrNull((String) objFirstOrNull);
            } else {
                dValueOf = objFirstOrNull instanceof Number ? Double.valueOf(((Number) objFirstOrNull).doubleValue()) : null;
            }
            if (dValueOf != null) {
                double dDoubleValue3 = dValueOf.doubleValue();
                if (isLessThan) {
                    if (dDoubleValue >= dDoubleValue3) {
                        return false;
                    }
                } else if (dDoubleValue <= dDoubleValue3) {
                    return false;
                }
                return true;
            }
        }
        Number numberValue2 = expected.getNumberValue();
        if (numberValue2 != null) {
            dDoubleValue2 = numberValue2.doubleValue();
        } else {
            String stringValue2 = expected.getStringValue();
            Double doubleOrNull2 = stringValue2 != null ? StringsKt.toDoubleOrNull(stringValue2) : null;
            if (doubleOrNull2 == null) {
                return false;
            }
            dDoubleValue2 = doubleOrNull2.doubleValue();
        }
        if (isLessThan) {
            if (dDoubleValue >= dDoubleValue2) {
                return false;
            }
        } else if (dDoubleValue <= dDoubleValue2) {
            return false;
        }
        return true;
    }

    public final boolean actualContainsExpected$clevertap_core_release(TriggerValue expected, TriggerValue actual) {
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(actual, "actual");
        if (actual.getStringValue() != null && expected.getStringValue() != null) {
            String stringValueCleaned = actual.getStringValueCleaned();
            Intrinsics.checkNotNull(stringValueCleaned);
            String stringValueCleaned2 = expected.getStringValueCleaned();
            Intrinsics.checkNotNull(stringValueCleaned2);
            return StringsKt.contains$default((CharSequence) stringValueCleaned, (CharSequence) stringValueCleaned2, false, 2, (Object) null);
        }
        if (!expected.isList() || actual.getStringValue() == null) {
            if (expected.isList() && actual.isList()) {
                List<?> listListValueWithCleanedStringIfPresent = actual.listValueWithCleanedStringIfPresent();
                Intrinsics.checkNotNull(listListValueWithCleanedStringIfPresent);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listListValueWithCleanedStringIfPresent) {
                    if (obj instanceof String) {
                        arrayList.add(obj);
                    }
                }
                Set set = CollectionsKt.toSet(arrayList);
                List<?> listListValueWithCleanedStringIfPresent2 = expected.listValueWithCleanedStringIfPresent();
                Intrinsics.checkNotNull(listListValueWithCleanedStringIfPresent2);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : listListValueWithCleanedStringIfPresent2) {
                    if (obj2 instanceof String) {
                        arrayList2.add(obj2);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
                    return false;
                }
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    if (set.contains((String) it.next())) {
                    }
                }
                return false;
            }
            if (!actual.isList() || expected.getStringValue() == null) {
                return false;
            }
            List<?> listListValueWithCleanedStringIfPresent3 = actual.listValueWithCleanedStringIfPresent();
            Intrinsics.checkNotNull(listListValueWithCleanedStringIfPresent3);
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : listListValueWithCleanedStringIfPresent3) {
                if (obj3 instanceof String) {
                    arrayList4.add(obj3);
                }
            }
            return CollectionsKt.contains(CollectionsKt.toSet(arrayList4), expected.getStringValueCleaned());
        }
        List<?> listListValueWithCleanedStringIfPresent4 = expected.listValueWithCleanedStringIfPresent();
        Intrinsics.checkNotNull(listListValueWithCleanedStringIfPresent4);
        Sequence<String> sequenceFilter = SequencesKt.filter(SequencesKt.filterNotNull(CollectionsKt.asSequence(listListValueWithCleanedStringIfPresent4)), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$actualContainsExpected$$inlined$filterIsInstance$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Object obj4) {
                return Boolean.valueOf(obj4 instanceof String);
            }
        });
        Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        for (String str : sequenceFilter) {
            String stringValueCleaned3 = actual.getStringValueCleaned();
            Intrinsics.checkNotNull(stringValueCleaned3);
            if (StringsKt.contains$default((CharSequence) stringValueCleaned3, (CharSequence) str, false, 2, (Object) null)) {
            }
        }
        return false;
        return true;
    }

    public final boolean actualIsInRangeOfExpected$clevertap_core_release(TriggerValue expected, TriggerValue actual) {
        List listTake;
        double dDoubleValue;
        Double dValueOf;
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(actual, "actual");
        List<?> listListValue = expected.listValue();
        if (listListValue == null) {
            return false;
        }
        if (listListValue.size() < 2) {
            listListValue = null;
        }
        if (listListValue == null || (listTake = CollectionsKt.take(listListValue, 2)) == null) {
            return false;
        }
        List list = listTake;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Object obj : list) {
            if (obj instanceof String) {
                dValueOf = StringsKt.toDoubleOrNull((String) obj);
            } else {
                dValueOf = obj instanceof Number ? Double.valueOf(((Number) obj).doubleValue()) : null;
            }
            arrayList.add(dValueOf);
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.contains(null)) {
            return false;
        }
        Number numberValue = actual.getNumberValue();
        if (numberValue != null) {
            dDoubleValue = numberValue.doubleValue();
        } else {
            String stringValue = actual.getStringValue();
            Double doubleOrNull = stringValue != null ? StringsKt.toDoubleOrNull(stringValue) : null;
            if (doubleOrNull == null) {
                return false;
            }
            dDoubleValue = doubleOrNull.doubleValue();
        }
        Object obj2 = arrayList2.get(0);
        Intrinsics.checkNotNull(obj2);
        double dDoubleValue2 = ((Number) obj2).doubleValue();
        Object obj3 = arrayList2.get(1);
        Intrinsics.checkNotNull(obj3);
        return dDoubleValue <= ((Number) obj3).doubleValue() && dDoubleValue2 <= dDoubleValue;
    }

    private final boolean checkGivenElementEqualsAnyElementInList(List<?> list, Object elementToCheckForEquality) {
        if (elementToCheckForEquality instanceof String) {
            List<?> list2 = list;
            Sequence<String> sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(list2), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$checkGivenElementEqualsAnyElementInList$$inlined$filterIsInstance$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof String);
                }
            });
            Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            for (String str : sequenceFilter) {
                String lowerCase = StringsKt.trim((CharSequence) elementToCheckForEquality).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(str, lowerCase)) {
                    return true;
                }
            }
            Sequence sequenceFilter2 = SequencesKt.filter(CollectionsKt.asSequence(list2), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$checkGivenElementEqualsAnyElementInList$$inlined$filterIsInstance$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof Number);
                }
            });
            Intrinsics.checkNotNull(sequenceFilter2, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            Iterator it = sequenceFilter2.iterator();
            while (it.hasNext()) {
                double dDoubleValue = ((Number) it.next()).doubleValue();
                String lowerCase2 = StringsKt.trim((CharSequence) elementToCheckForEquality).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(dDoubleValue, StringsKt.toDoubleOrNull(lowerCase2))) {
                    return true;
                }
            }
            return false;
        }
        if (elementToCheckForEquality instanceof Number) {
            double dDoubleValue2 = ((Number) elementToCheckForEquality).doubleValue();
            List<?> list3 = list;
            Sequence sequenceFilter3 = SequencesKt.filter(CollectionsKt.asSequence(list3), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$checkGivenElementEqualsAnyElementInList$$inlined$filterIsInstance$3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof Number);
                }
            });
            Intrinsics.checkNotNull(sequenceFilter3, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            Iterator it2 = sequenceFilter3.iterator();
            while (it2.hasNext()) {
                if (((Number) it2.next()).doubleValue() == dDoubleValue2) {
                    return true;
                }
            }
            Sequence sequenceFilter4 = SequencesKt.filter(CollectionsKt.asSequence(list3), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$checkGivenElementEqualsAnyElementInList$$inlined$filterIsInstance$4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof String);
                }
            });
            Intrinsics.checkNotNull(sequenceFilter4, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            Iterator it3 = sequenceFilter4.iterator();
            while (it3.hasNext()) {
                String lowerCase3 = StringsKt.trim((CharSequence) it3.next()).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(StringsKt.toDoubleOrNull(lowerCase3), dDoubleValue2)) {
                    return true;
                }
            }
            return false;
        }
        if (!(elementToCheckForEquality instanceof Boolean)) {
            return false;
        }
        Sequence sequenceFilter5 = SequencesKt.filter(CollectionsKt.asSequence(list), new Function1<Object, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher$checkGivenElementEqualsAnyElementInList$$inlined$filterIsInstance$5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Object obj) {
                return Boolean.valueOf(obj instanceof String);
            }
        });
        Intrinsics.checkNotNull(sequenceFilter5, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        Iterator it4 = sequenceFilter5.iterator();
        while (it4.hasNext()) {
            if (Intrinsics.areEqual((String) it4.next(), String.valueOf(((Boolean) elementToCheckForEquality).booleanValue()))) {
                return true;
            }
        }
        return false;
    }
}
