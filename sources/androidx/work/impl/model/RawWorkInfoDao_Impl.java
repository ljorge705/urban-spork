package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes5.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(final SupportSQLiteQuery query) {
        WorkInfo.State stateIntToState;
        Data dataFromByteArray;
        BackoffPolicy backoffPolicyIntToBackoffPolicy;
        long j;
        int i;
        NetworkType networkTypeIntToNetworkType;
        boolean z;
        boolean z2;
        boolean z3;
        Set<Constraints.ContentUriTrigger> setByteArrayToSetOfTriggers;
        int i2;
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, query, true, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
            int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
            int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
            int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "initial_delay");
            int columnIndex5 = CursorUtil.getColumnIndex(cursorQuery, "interval_duration");
            int columnIndex6 = CursorUtil.getColumnIndex(cursorQuery, "flex_duration");
            int columnIndex7 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
            int columnIndex8 = CursorUtil.getColumnIndex(cursorQuery, "backoff_policy");
            int columnIndex9 = CursorUtil.getColumnIndex(cursorQuery, "backoff_delay_duration");
            int columnIndex10 = CursorUtil.getColumnIndex(cursorQuery, "last_enqueue_time");
            int columnIndex11 = CursorUtil.getColumnIndex(cursorQuery, "period_count");
            int columnIndex12 = CursorUtil.getColumnIndex(cursorQuery, "generation");
            int columnIndex13 = CursorUtil.getColumnIndex(cursorQuery, "next_schedule_time_override");
            int columnIndex14 = CursorUtil.getColumnIndex(cursorQuery, "stop_reason");
            int columnIndex15 = CursorUtil.getColumnIndex(cursorQuery, "required_network_type");
            int columnIndex16 = CursorUtil.getColumnIndex(cursorQuery, "requires_charging");
            int columnIndex17 = CursorUtil.getColumnIndex(cursorQuery, "requires_device_idle");
            int columnIndex18 = CursorUtil.getColumnIndex(cursorQuery, "requires_battery_not_low");
            int columnIndex19 = CursorUtil.getColumnIndex(cursorQuery, "requires_storage_not_low");
            int columnIndex20 = CursorUtil.getColumnIndex(cursorQuery, "trigger_content_update_delay");
            int columnIndex21 = CursorUtil.getColumnIndex(cursorQuery, "trigger_max_content_delay");
            int columnIndex22 = CursorUtil.getColumnIndex(cursorQuery, "content_uri_triggers");
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            int i3 = columnIndex13;
            HashMap<String, ArrayList<Data>> map2 = new HashMap<>();
            while (cursorQuery.moveToNext()) {
                int i4 = columnIndex12;
                String string = cursorQuery.getString(columnIndex);
                if (map.get(string) == null) {
                    i2 = columnIndex11;
                    map.put(string, new ArrayList<>());
                } else {
                    i2 = columnIndex11;
                }
                String string2 = cursorQuery.getString(columnIndex);
                if (map2.get(string2) == null) {
                    map2.put(string2, new ArrayList<>());
                }
                columnIndex12 = i4;
                columnIndex11 = i2;
            }
            int i5 = columnIndex11;
            int i6 = columnIndex12;
            cursorQuery.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(map);
            __fetchRelationshipWorkProgressAsandroidxWorkData(map2);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string3 = (columnIndex == -1 || cursorQuery.isNull(columnIndex)) ? null : cursorQuery.getString(columnIndex);
                if (columnIndex2 == -1) {
                    stateIntToState = null;
                } else {
                    int i7 = cursorQuery.getInt(columnIndex2);
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    stateIntToState = WorkTypeConverters.intToState(i7);
                }
                if (columnIndex3 == -1) {
                    dataFromByteArray = null;
                } else {
                    dataFromByteArray = Data.fromByteArray(cursorQuery.isNull(columnIndex3) ? null : cursorQuery.getBlob(columnIndex3));
                }
                long j2 = columnIndex4 == -1 ? 0L : cursorQuery.getLong(columnIndex4);
                long j3 = columnIndex5 == -1 ? 0L : cursorQuery.getLong(columnIndex5);
                long j4 = columnIndex6 == -1 ? 0L : cursorQuery.getLong(columnIndex6);
                boolean z4 = false;
                int i8 = columnIndex7 == -1 ? 0 : cursorQuery.getInt(columnIndex7);
                if (columnIndex8 == -1) {
                    backoffPolicyIntToBackoffPolicy = null;
                } else {
                    int i9 = cursorQuery.getInt(columnIndex8);
                    WorkTypeConverters workTypeConverters2 = WorkTypeConverters.INSTANCE;
                    backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(i9);
                }
                long j5 = columnIndex9 == -1 ? 0L : cursorQuery.getLong(columnIndex9);
                if (columnIndex10 == -1) {
                    i = i5;
                    j = 0;
                } else {
                    j = cursorQuery.getLong(columnIndex10);
                    i = i5;
                }
                int i10 = i == -1 ? 0 : cursorQuery.getInt(i);
                int i11 = i6;
                int i12 = i;
                int i13 = i11 == -1 ? 0 : cursorQuery.getInt(i11);
                int i14 = i3;
                long j6 = i14 == -1 ? 0L : cursorQuery.getLong(i14);
                int i15 = columnIndex14;
                int i16 = i15 == -1 ? 0 : cursorQuery.getInt(i15);
                int i17 = columnIndex15;
                if (i17 == -1) {
                    networkTypeIntToNetworkType = null;
                } else {
                    int i18 = cursorQuery.getInt(i17);
                    WorkTypeConverters workTypeConverters3 = WorkTypeConverters.INSTANCE;
                    networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(i18);
                }
                int i19 = columnIndex16;
                if (i19 == -1) {
                    z = false;
                } else {
                    z = cursorQuery.getInt(i19) != 0;
                }
                int i20 = columnIndex17;
                if (i20 == -1) {
                    z2 = false;
                } else {
                    z2 = cursorQuery.getInt(i20) != 0;
                }
                int i21 = columnIndex18;
                if (i21 == -1) {
                    z3 = false;
                } else {
                    z3 = cursorQuery.getInt(i21) != 0;
                }
                int i22 = columnIndex19;
                if (i22 != -1 && cursorQuery.getInt(i22) != 0) {
                    z4 = true;
                }
                boolean z5 = z4;
                int i23 = columnIndex20;
                long j7 = i23 == -1 ? 0L : cursorQuery.getLong(i23);
                int i24 = columnIndex21;
                long j8 = i24 != -1 ? cursorQuery.getLong(i24) : 0L;
                int i25 = columnIndex22;
                if (i25 == -1) {
                    setByteArrayToSetOfTriggers = null;
                } else {
                    byte[] blob = cursorQuery.isNull(i25) ? null : cursorQuery.getBlob(i25);
                    WorkTypeConverters workTypeConverters4 = WorkTypeConverters.INSTANCE;
                    setByteArrayToSetOfTriggers = WorkTypeConverters.byteArrayToSetOfTriggers(blob);
                }
                Constraints constraints = new Constraints(networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, setByteArrayToSetOfTriggers);
                ArrayList<String> arrayList2 = map.get(cursorQuery.getString(columnIndex));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<String> arrayList3 = arrayList2;
                ArrayList<Data> arrayList4 = map2.get(cursorQuery.getString(columnIndex));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, j2, j3, j4, constraints, i8, backoffPolicyIntToBackoffPolicy, j5, j, i10, i13, j6, i16, arrayList3, arrayList4));
                i5 = i12;
                i6 = i11;
                i3 = i14;
                columnIndex14 = i15;
                columnIndex15 = i17;
                columnIndex16 = i19;
                columnIndex17 = i20;
                columnIndex18 = i21;
                columnIndex19 = i22;
                columnIndex20 = i23;
                columnIndex21 = i24;
                columnIndex22 = i25;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkInfo.State stateIntToState;
                Data dataFromByteArray;
                BackoffPolicy backoffPolicyIntToBackoffPolicy;
                long j;
                int i;
                NetworkType networkTypeIntToNetworkType;
                boolean z;
                boolean z2;
                boolean z3;
                Set<Constraints.ContentUriTrigger> setByteArrayToSetOfTriggers;
                int i2;
                Cursor cursorQuery = DBUtil.query(RawWorkInfoDao_Impl.this.__db, query, true, null);
                try {
                    int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
                    int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
                    int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
                    int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "initial_delay");
                    int columnIndex5 = CursorUtil.getColumnIndex(cursorQuery, "interval_duration");
                    int columnIndex6 = CursorUtil.getColumnIndex(cursorQuery, "flex_duration");
                    int columnIndex7 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
                    int columnIndex8 = CursorUtil.getColumnIndex(cursorQuery, "backoff_policy");
                    int columnIndex9 = CursorUtil.getColumnIndex(cursorQuery, "backoff_delay_duration");
                    int columnIndex10 = CursorUtil.getColumnIndex(cursorQuery, "last_enqueue_time");
                    int columnIndex11 = CursorUtil.getColumnIndex(cursorQuery, "period_count");
                    int columnIndex12 = CursorUtil.getColumnIndex(cursorQuery, "generation");
                    int columnIndex13 = CursorUtil.getColumnIndex(cursorQuery, "next_schedule_time_override");
                    int columnIndex14 = CursorUtil.getColumnIndex(cursorQuery, "stop_reason");
                    int columnIndex15 = CursorUtil.getColumnIndex(cursorQuery, "required_network_type");
                    int columnIndex16 = CursorUtil.getColumnIndex(cursorQuery, "requires_charging");
                    int columnIndex17 = CursorUtil.getColumnIndex(cursorQuery, "requires_device_idle");
                    int columnIndex18 = CursorUtil.getColumnIndex(cursorQuery, "requires_battery_not_low");
                    int columnIndex19 = CursorUtil.getColumnIndex(cursorQuery, "requires_storage_not_low");
                    int columnIndex20 = CursorUtil.getColumnIndex(cursorQuery, "trigger_content_update_delay");
                    int columnIndex21 = CursorUtil.getColumnIndex(cursorQuery, "trigger_max_content_delay");
                    int columnIndex22 = CursorUtil.getColumnIndex(cursorQuery, "content_uri_triggers");
                    HashMap map = new HashMap();
                    int i3 = columnIndex13;
                    HashMap map2 = new HashMap();
                    while (cursorQuery.moveToNext()) {
                        int i4 = columnIndex12;
                        String string = cursorQuery.getString(columnIndex);
                        if (((ArrayList) map.get(string)) == null) {
                            i2 = columnIndex11;
                            map.put(string, new ArrayList());
                        } else {
                            i2 = columnIndex11;
                        }
                        String string2 = cursorQuery.getString(columnIndex);
                        if (((ArrayList) map2.get(string2)) == null) {
                            map2.put(string2, new ArrayList());
                        }
                        columnIndex12 = i4;
                        columnIndex11 = i2;
                    }
                    int i5 = columnIndex11;
                    int i6 = columnIndex12;
                    cursorQuery.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(map);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(map2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string3 = (columnIndex == -1 || cursorQuery.isNull(columnIndex)) ? null : cursorQuery.getString(columnIndex);
                        if (columnIndex2 == -1) {
                            stateIntToState = null;
                        } else {
                            int i7 = cursorQuery.getInt(columnIndex2);
                            WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                            stateIntToState = WorkTypeConverters.intToState(i7);
                        }
                        if (columnIndex3 == -1) {
                            dataFromByteArray = null;
                        } else {
                            dataFromByteArray = Data.fromByteArray(cursorQuery.isNull(columnIndex3) ? null : cursorQuery.getBlob(columnIndex3));
                        }
                        long j2 = columnIndex4 == -1 ? 0L : cursorQuery.getLong(columnIndex4);
                        long j3 = columnIndex5 == -1 ? 0L : cursorQuery.getLong(columnIndex5);
                        long j4 = columnIndex6 == -1 ? 0L : cursorQuery.getLong(columnIndex6);
                        boolean z4 = false;
                        int i8 = columnIndex7 == -1 ? 0 : cursorQuery.getInt(columnIndex7);
                        if (columnIndex8 == -1) {
                            backoffPolicyIntToBackoffPolicy = null;
                        } else {
                            int i9 = cursorQuery.getInt(columnIndex8);
                            WorkTypeConverters workTypeConverters2 = WorkTypeConverters.INSTANCE;
                            backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(i9);
                        }
                        long j5 = columnIndex9 == -1 ? 0L : cursorQuery.getLong(columnIndex9);
                        if (columnIndex10 == -1) {
                            i = i5;
                            j = 0;
                        } else {
                            j = cursorQuery.getLong(columnIndex10);
                            i = i5;
                        }
                        int i10 = i == -1 ? 0 : cursorQuery.getInt(i);
                        int i11 = i6;
                        int i12 = i;
                        int i13 = i11 == -1 ? 0 : cursorQuery.getInt(i11);
                        int i14 = i3;
                        long j6 = i14 == -1 ? 0L : cursorQuery.getLong(i14);
                        int i15 = columnIndex14;
                        int i16 = i15 == -1 ? 0 : cursorQuery.getInt(i15);
                        int i17 = columnIndex15;
                        if (i17 == -1) {
                            networkTypeIntToNetworkType = null;
                        } else {
                            int i18 = cursorQuery.getInt(i17);
                            WorkTypeConverters workTypeConverters3 = WorkTypeConverters.INSTANCE;
                            networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(i18);
                        }
                        int i19 = columnIndex16;
                        if (i19 == -1) {
                            z = false;
                        } else {
                            z = cursorQuery.getInt(i19) != 0;
                        }
                        int i20 = columnIndex17;
                        if (i20 == -1) {
                            z2 = false;
                        } else {
                            z2 = cursorQuery.getInt(i20) != 0;
                        }
                        int i21 = columnIndex18;
                        if (i21 == -1) {
                            z3 = false;
                        } else {
                            z3 = cursorQuery.getInt(i21) != 0;
                        }
                        int i22 = columnIndex19;
                        if (i22 != -1 && cursorQuery.getInt(i22) != 0) {
                            z4 = true;
                        }
                        boolean z5 = z4;
                        int i23 = columnIndex20;
                        long j7 = i23 == -1 ? 0L : cursorQuery.getLong(i23);
                        int i24 = columnIndex21;
                        long j8 = i24 != -1 ? cursorQuery.getLong(i24) : 0L;
                        int i25 = columnIndex22;
                        if (i25 == -1) {
                            setByteArrayToSetOfTriggers = null;
                        } else {
                            byte[] blob = cursorQuery.isNull(i25) ? null : cursorQuery.getBlob(i25);
                            WorkTypeConverters workTypeConverters4 = WorkTypeConverters.INSTANCE;
                            setByteArrayToSetOfTriggers = WorkTypeConverters.byteArrayToSetOfTriggers(blob);
                        }
                        Constraints constraints = new Constraints(networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, setByteArrayToSetOfTriggers);
                        ArrayList arrayList2 = (ArrayList) map.get(cursorQuery.getString(columnIndex));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = arrayList2;
                        ArrayList arrayList4 = (ArrayList) map2.get(cursorQuery.getString(columnIndex));
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                        }
                        arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, j2, j3, j4, constraints, i8, backoffPolicyIntToBackoffPolicy, j5, j, i10, i13, j6, i16, arrayList3, arrayList4));
                        i5 = i12;
                        i6 = i11;
                        i3 = i14;
                        columnIndex14 = i15;
                        columnIndex15 = i17;
                        columnIndex16 = i19;
                        columnIndex17 = i20;
                        columnIndex18 = i21;
                        columnIndex19 = i22;
                        columnIndex20 = i23;
                        columnIndex21 = i24;
                        columnIndex22 = i25;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public Flow<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosFlow(final SupportSQLiteQuery query) {
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.2
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkInfo.State stateIntToState;
                Data dataFromByteArray;
                BackoffPolicy backoffPolicyIntToBackoffPolicy;
                long j;
                int i;
                NetworkType networkTypeIntToNetworkType;
                boolean z;
                boolean z2;
                boolean z3;
                Set<Constraints.ContentUriTrigger> setByteArrayToSetOfTriggers;
                int i2;
                Cursor cursorQuery = DBUtil.query(RawWorkInfoDao_Impl.this.__db, query, true, null);
                try {
                    int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
                    int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
                    int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
                    int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "initial_delay");
                    int columnIndex5 = CursorUtil.getColumnIndex(cursorQuery, "interval_duration");
                    int columnIndex6 = CursorUtil.getColumnIndex(cursorQuery, "flex_duration");
                    int columnIndex7 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
                    int columnIndex8 = CursorUtil.getColumnIndex(cursorQuery, "backoff_policy");
                    int columnIndex9 = CursorUtil.getColumnIndex(cursorQuery, "backoff_delay_duration");
                    int columnIndex10 = CursorUtil.getColumnIndex(cursorQuery, "last_enqueue_time");
                    int columnIndex11 = CursorUtil.getColumnIndex(cursorQuery, "period_count");
                    int columnIndex12 = CursorUtil.getColumnIndex(cursorQuery, "generation");
                    int columnIndex13 = CursorUtil.getColumnIndex(cursorQuery, "next_schedule_time_override");
                    int columnIndex14 = CursorUtil.getColumnIndex(cursorQuery, "stop_reason");
                    int columnIndex15 = CursorUtil.getColumnIndex(cursorQuery, "required_network_type");
                    int columnIndex16 = CursorUtil.getColumnIndex(cursorQuery, "requires_charging");
                    int columnIndex17 = CursorUtil.getColumnIndex(cursorQuery, "requires_device_idle");
                    int columnIndex18 = CursorUtil.getColumnIndex(cursorQuery, "requires_battery_not_low");
                    int columnIndex19 = CursorUtil.getColumnIndex(cursorQuery, "requires_storage_not_low");
                    int columnIndex20 = CursorUtil.getColumnIndex(cursorQuery, "trigger_content_update_delay");
                    int columnIndex21 = CursorUtil.getColumnIndex(cursorQuery, "trigger_max_content_delay");
                    int columnIndex22 = CursorUtil.getColumnIndex(cursorQuery, "content_uri_triggers");
                    HashMap map = new HashMap();
                    int i3 = columnIndex13;
                    HashMap map2 = new HashMap();
                    while (cursorQuery.moveToNext()) {
                        int i4 = columnIndex12;
                        String string = cursorQuery.getString(columnIndex);
                        if (((ArrayList) map.get(string)) == null) {
                            i2 = columnIndex11;
                            map.put(string, new ArrayList());
                        } else {
                            i2 = columnIndex11;
                        }
                        String string2 = cursorQuery.getString(columnIndex);
                        if (((ArrayList) map2.get(string2)) == null) {
                            map2.put(string2, new ArrayList());
                        }
                        columnIndex12 = i4;
                        columnIndex11 = i2;
                    }
                    int i5 = columnIndex11;
                    int i6 = columnIndex12;
                    cursorQuery.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(map);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(map2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string3 = (columnIndex == -1 || cursorQuery.isNull(columnIndex)) ? null : cursorQuery.getString(columnIndex);
                        if (columnIndex2 == -1) {
                            stateIntToState = null;
                        } else {
                            int i7 = cursorQuery.getInt(columnIndex2);
                            WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                            stateIntToState = WorkTypeConverters.intToState(i7);
                        }
                        if (columnIndex3 == -1) {
                            dataFromByteArray = null;
                        } else {
                            dataFromByteArray = Data.fromByteArray(cursorQuery.isNull(columnIndex3) ? null : cursorQuery.getBlob(columnIndex3));
                        }
                        long j2 = columnIndex4 == -1 ? 0L : cursorQuery.getLong(columnIndex4);
                        long j3 = columnIndex5 == -1 ? 0L : cursorQuery.getLong(columnIndex5);
                        long j4 = columnIndex6 == -1 ? 0L : cursorQuery.getLong(columnIndex6);
                        boolean z4 = false;
                        int i8 = columnIndex7 == -1 ? 0 : cursorQuery.getInt(columnIndex7);
                        if (columnIndex8 == -1) {
                            backoffPolicyIntToBackoffPolicy = null;
                        } else {
                            int i9 = cursorQuery.getInt(columnIndex8);
                            WorkTypeConverters workTypeConverters2 = WorkTypeConverters.INSTANCE;
                            backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(i9);
                        }
                        long j5 = columnIndex9 == -1 ? 0L : cursorQuery.getLong(columnIndex9);
                        if (columnIndex10 == -1) {
                            i = i5;
                            j = 0;
                        } else {
                            j = cursorQuery.getLong(columnIndex10);
                            i = i5;
                        }
                        int i10 = i == -1 ? 0 : cursorQuery.getInt(i);
                        int i11 = i6;
                        int i12 = i;
                        int i13 = i11 == -1 ? 0 : cursorQuery.getInt(i11);
                        int i14 = i3;
                        long j6 = i14 == -1 ? 0L : cursorQuery.getLong(i14);
                        int i15 = columnIndex14;
                        int i16 = i15 == -1 ? 0 : cursorQuery.getInt(i15);
                        int i17 = columnIndex15;
                        if (i17 == -1) {
                            networkTypeIntToNetworkType = null;
                        } else {
                            int i18 = cursorQuery.getInt(i17);
                            WorkTypeConverters workTypeConverters3 = WorkTypeConverters.INSTANCE;
                            networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(i18);
                        }
                        int i19 = columnIndex16;
                        if (i19 == -1) {
                            z = false;
                        } else {
                            z = cursorQuery.getInt(i19) != 0;
                        }
                        int i20 = columnIndex17;
                        if (i20 == -1) {
                            z2 = false;
                        } else {
                            z2 = cursorQuery.getInt(i20) != 0;
                        }
                        int i21 = columnIndex18;
                        if (i21 == -1) {
                            z3 = false;
                        } else {
                            z3 = cursorQuery.getInt(i21) != 0;
                        }
                        int i22 = columnIndex19;
                        if (i22 != -1 && cursorQuery.getInt(i22) != 0) {
                            z4 = true;
                        }
                        boolean z5 = z4;
                        int i23 = columnIndex20;
                        long j7 = i23 == -1 ? 0L : cursorQuery.getLong(i23);
                        int i24 = columnIndex21;
                        long j8 = i24 != -1 ? cursorQuery.getLong(i24) : 0L;
                        int i25 = columnIndex22;
                        if (i25 == -1) {
                            setByteArrayToSetOfTriggers = null;
                        } else {
                            byte[] blob = cursorQuery.isNull(i25) ? null : cursorQuery.getBlob(i25);
                            WorkTypeConverters workTypeConverters4 = WorkTypeConverters.INSTANCE;
                            setByteArrayToSetOfTriggers = WorkTypeConverters.byteArrayToSetOfTriggers(blob);
                        }
                        Constraints constraints = new Constraints(networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, setByteArrayToSetOfTriggers);
                        ArrayList arrayList2 = (ArrayList) map.get(cursorQuery.getString(columnIndex));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = arrayList2;
                        ArrayList arrayList4 = (ArrayList) map2.get(cursorQuery.getString(columnIndex));
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                        }
                        arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, j2, j3, j4, constraints, i8, backoffPolicyIntToBackoffPolicy, j5, j, i10, i13, j6, i16, arrayList3, arrayList4));
                        i5 = i12;
                        i6 = i11;
                        i3 = i14;
                        columnIndex14 = i15;
                        columnIndex15 = i17;
                        columnIndex16 = i19;
                        columnIndex17 = i20;
                        columnIndex18 = i21;
                        columnIndex19 = i22;
                        columnIndex20 = i23;
                        columnIndex21 = i24;
                        columnIndex22 = i25;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final HashMap<String, ArrayList<String>> _map) {
        int i;
        Set<String> setKeySet = _map.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            HashMap<String, ArrayList<String>> map = new HashMap<>(999);
            loop0: while (true) {
                i = 0;
                for (String str : setKeySet) {
                    map.put(str, _map.get(str));
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkTagAsjavaLangString(map);
                map = new HashMap<>(999);
            }
            if (i > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(map);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i2 = 1;
        for (String str2 : setKeySet) {
            if (str2 == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str2);
            }
            i2++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList<String> arrayList = _map.get(cursorQuery.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(cursorQuery.isNull(0) ? null : cursorQuery.getString(0));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final HashMap<String, ArrayList<Data>> _map) {
        int i;
        Set<String> setKeySet = _map.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            HashMap<String, ArrayList<Data>> map = new HashMap<>(999);
            loop0: while (true) {
                i = 0;
                for (String str : setKeySet) {
                    map.put(str, _map.get(str));
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkProgressAsandroidxWorkData(map);
                map = new HashMap<>(999);
            }
            if (i > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(map);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i2 = 1;
        for (String str2 : setKeySet) {
            if (str2 == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str2);
            }
            i2++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList<Data> arrayList = _map.get(cursorQuery.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(Data.fromByteArray(cursorQuery.isNull(0) ? null : cursorQuery.getBlob(0)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }
}
