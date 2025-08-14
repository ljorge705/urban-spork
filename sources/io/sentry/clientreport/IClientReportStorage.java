package io.sentry.clientreport;

import java.util.List;

/* loaded from: classes6.dex */
public interface IClientReportStorage {
    void addCount(ClientReportKey clientReportKey, Long l);

    List<DiscardedEvent> resetCountsAndGet();
}
