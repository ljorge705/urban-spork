package io.sentry.clientreport;

import io.sentry.DataCategory;
import io.sentry.util.LazyEvaluator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes6.dex */
final class AtomicClientReportStorage implements IClientReportStorage {
    private final LazyEvaluator<Map<ClientReportKey, AtomicLong>> lostEventCounts = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.clientreport.AtomicClientReportStorage$$ExternalSyntheticLambda0
        @Override // io.sentry.util.LazyEvaluator.Evaluator
        public final Object evaluate() {
            return AtomicClientReportStorage.lambda$new$0();
        }
    });

    static /* synthetic */ Map lambda$new$0() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (DiscardReason discardReason : DiscardReason.values()) {
            for (DataCategory dataCategory : DataCategory.values()) {
                concurrentHashMap.put(new ClientReportKey(discardReason.getReason(), dataCategory.getCategory()), new AtomicLong(0L));
            }
        }
        return Collections.unmodifiableMap(concurrentHashMap);
    }

    @Override // io.sentry.clientreport.IClientReportStorage
    public void addCount(ClientReportKey clientReportKey, Long l) {
        AtomicLong atomicLong = this.lostEventCounts.getValue().get(clientReportKey);
        if (atomicLong != null) {
            atomicLong.addAndGet(l.longValue());
        }
    }

    @Override // io.sentry.clientreport.IClientReportStorage
    public List<DiscardedEvent> resetCountsAndGet() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<ClientReportKey, AtomicLong> entry : this.lostEventCounts.getValue().entrySet()) {
            Long lValueOf = Long.valueOf(entry.getValue().getAndSet(0L));
            if (lValueOf.longValue() > 0) {
                arrayList.add(new DiscardedEvent(entry.getKey().getReason(), entry.getKey().getCategory(), lValueOf));
            }
        }
        return arrayList;
    }
}
