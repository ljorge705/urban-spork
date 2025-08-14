package com.clevertap.android.sdk.db;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: QueueData.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\b\u0010\u001b\u001a\u00020\u000fH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0004¨\u0006\u001c"}, d2 = {"Lcom/clevertap/android/sdk/db/QueueData;", "", "table", "Lcom/clevertap/android/sdk/db/Table;", "(Lcom/clevertap/android/sdk/db/Table;)V", "data", "Lorg/json/JSONArray;", "getData", "()Lorg/json/JSONArray;", "setData", "(Lorg/json/JSONArray;)V", "isEmpty", "", "()Z", "lastId", "", "getLastId", "()Ljava/lang/String;", "setLastId", "(Ljava/lang/String;)V", "getTable", "()Lcom/clevertap/android/sdk/db/Table;", "setTable", "setDataFromDbObject", "", "dbObject", "Lorg/json/JSONObject;", "toString", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class QueueData {
    private JSONArray data;
    private String lastId;
    private Table table;

    public final JSONArray getData() {
        return this.data;
    }

    public final String getLastId() {
        return this.lastId;
    }

    public final Table getTable() {
        return this.table;
    }

    public final void setData(JSONArray jSONArray) {
        this.data = jSONArray;
    }

    public final void setLastId(String str) {
        this.lastId = str;
    }

    public final void setTable(Table table) {
        Intrinsics.checkNotNullParameter(table, "<set-?>");
        this.table = table;
    }

    public QueueData(Table table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
    }

    public final boolean isEmpty() {
        JSONArray jSONArray = this.data;
        return this.lastId == null || jSONArray == null || jSONArray.length() <= 0;
    }

    public final void setDataFromDbObject(JSONObject dbObject) {
        if (dbObject == null) {
            return;
        }
        Iterator<String> itKeys = dbObject.keys();
        if (itKeys.hasNext()) {
            String next = itKeys.next();
            this.lastId = next;
            try {
                this.data = dbObject.getJSONArray(next);
            } catch (JSONException unused) {
                this.lastId = null;
                this.data = null;
            }
        }
    }

    public String toString() {
        JSONArray jSONArray = this.data;
        int length = jSONArray != null ? jSONArray.length() : 0;
        if (isEmpty()) {
            return "table: " + this.table + " | numItems: " + length;
        }
        return "table: " + this.table + " | lastId: " + this.lastId + " | numItems: " + length + " | items: " + this.data;
    }
}
