package com.clevertap.android.sdk.inapp.evaluation;

import com.clevertap.android.sdk.variables.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* compiled from: TriggerValue.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0012\b\u0002\u0010\u0003\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\f\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004J\f\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nR\u0018\u0010\u0003\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggerValue;", "", "value", "listValue", "", "(Ljava/lang/Object;Ljava/util/List;)V", "listValueWithCleanedStringIfPresent", "numberValue", "", "stringValue", "", "stringValueCleaned", "getValue", "()Ljava/lang/Object;", "isList", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class TriggerValue {
    private List<? extends Object> listValue;
    private List<? extends Object> listValueWithCleanedStringIfPresent;
    private Number numberValue;
    private String stringValue;
    private String stringValueCleaned;
    private final Object value;

    /* JADX WARN: Multi-variable type inference failed */
    public TriggerValue() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final Object getValue() {
        return this.value;
    }

    public final boolean isList() {
        return this.listValue != null;
    }

    public final List<?> listValue() {
        return this.listValue;
    }

    public final List<?> listValueWithCleanedStringIfPresent() {
        return this.listValueWithCleanedStringIfPresent;
    }

    /* renamed from: numberValue, reason: from getter */
    public final Number getNumberValue() {
        return this.numberValue;
    }

    /* renamed from: stringValue, reason: from getter */
    public final String getStringValue() {
        return this.stringValue;
    }

    /* renamed from: stringValueCleaned, reason: from getter */
    public final String getStringValueCleaned() {
        return this.stringValueCleaned;
    }

    public TriggerValue(Object obj, List<? extends Object> list) {
        ArrayList arrayList;
        this.value = obj;
        this.listValue = list;
        if (obj instanceof String) {
            this.stringValue = (String) obj;
            String lowerCase = StringsKt.trim((CharSequence) obj).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            this.stringValueCleaned = lowerCase;
            return;
        }
        if (obj instanceof Boolean) {
            this.stringValue = String.valueOf(((Boolean) obj).booleanValue());
            String lowerCase2 = StringsKt.trim((CharSequence) String.valueOf(((Boolean) obj).booleanValue())).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            this.stringValueCleaned = lowerCase2;
            return;
        }
        if (obj instanceof Number) {
            this.numberValue = (Number) obj;
            return;
        }
        if (obj instanceof List) {
            this.listValue = (List) obj;
            Iterable iterable = (Iterable) obj;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Object lowerCase3 : iterable) {
                if (lowerCase3 instanceof String) {
                    lowerCase3 = StringsKt.trim((CharSequence) lowerCase3).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                }
                arrayList2.add(lowerCase3);
            }
            this.listValueWithCleanedStringIfPresent = arrayList2;
            return;
        }
        if (obj instanceof JSONArray) {
            List<? extends Object> listListFromJson = JsonUtil.listFromJson((JSONArray) obj);
            this.listValue = listListFromJson;
            if (listListFromJson != null) {
                List<? extends Object> list2 = listListFromJson;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (Object lowerCase4 : list2) {
                    if (lowerCase4 instanceof String) {
                        lowerCase4 = StringsKt.trim((CharSequence) lowerCase4).toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    }
                    arrayList3.add(lowerCase4);
                }
                arrayList = arrayList3;
            } else {
                arrayList = null;
            }
            this.listValueWithCleanedStringIfPresent = arrayList;
        }
    }

    public /* synthetic */ TriggerValue(Object obj, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj, (i & 2) != 0 ? null : list);
    }
}
