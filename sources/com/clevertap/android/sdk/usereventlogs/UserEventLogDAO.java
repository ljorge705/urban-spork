package com.clevertap.android.sdk.usereventlogs;

import com.clevertap.android.sdk.db.Column;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: UserEventLogDAO.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH'J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J \u0010\u000f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000bH'J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J*\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0018\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u001a0\u0019H'¨\u0006\u001b"}, d2 = {"Lcom/clevertap/android/sdk/usereventlogs/UserEventLogDAO;", "", "allEvents", "", "Lcom/clevertap/android/sdk/usereventlogs/UserEventLog;", "allEventsByDeviceID", Column.DEVICE_ID, "", "cleanUpExtraEvents", "", "rowsThreshold", "", "numberOfRowsToCleanup", "eventExistsByDeviceIdAndNormalizedEventName", Column.NORMALIZED_EVENT_NAME, "eventExistsByDeviceIdAndNormalizedEventNameAndCount", "count", "insertEvent", "", "eventName", "readEventByDeviceIdAndNormalizedEventName", "readEventCountByDeviceIdAndNormalizedEventName", "updateEventByDeviceIdAndNormalizedEventName", "upsertEventsByDeviceIdAndNormalizedEventName", "setOfActualAndNormalizedEventNamePair", "", "Lkotlin/Pair;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface UserEventLogDAO {
    List<UserEventLog> allEvents();

    List<UserEventLog> allEventsByDeviceID(String deviceID);

    boolean cleanUpExtraEvents(int rowsThreshold, int numberOfRowsToCleanup);

    boolean eventExistsByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName);

    boolean eventExistsByDeviceIdAndNormalizedEventNameAndCount(String deviceID, String normalizedEventName, int count);

    long insertEvent(String deviceID, String eventName, String normalizedEventName);

    UserEventLog readEventByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName);

    int readEventCountByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName);

    boolean updateEventByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName);

    boolean upsertEventsByDeviceIdAndNormalizedEventName(String deviceID, Set<Pair<String, String>> setOfActualAndNormalizedEventNamePair);
}
