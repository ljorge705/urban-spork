package io.sentry;

import io.sentry.SentryOptions;
import io.sentry.metrics.CounterMetric;
import io.sentry.metrics.DistributionMetric;
import io.sentry.metrics.EncodedMetrics;
import io.sentry.metrics.GaugeMetric;
import io.sentry.metrics.IMetricsClient;
import io.sentry.metrics.LocalMetricsAggregator;
import io.sentry.metrics.Metric;
import io.sentry.metrics.MetricType;
import io.sentry.metrics.MetricsHelper;
import io.sentry.metrics.SetMetric;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CRC32;

/* loaded from: classes6.dex */
public final class MetricsAggregator implements IMetricsAggregator, Runnable, Closeable {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final SentryOptions.BeforeEmitMetricCallback beforeEmitCallback;
    private final NavigableMap<Long, Map<String, Metric>> buckets;
    private final IMetricsClient client;
    private final SentryDateProvider dateProvider;
    private volatile ISentryExecutorService executorService;
    private volatile boolean flushScheduled;
    private volatile boolean isClosed;
    private final ILogger logger;
    private final int maxWeight;
    private final AtomicInteger totalBucketsWeight;

    public MetricsAggregator(SentryOptions sentryOptions, IMetricsClient iMetricsClient) {
        this(iMetricsClient, sentryOptions.getLogger(), sentryOptions.getDateProvider(), 100000, sentryOptions.getBeforeEmitMetricCallback(), NoOpSentryExecutorService.getInstance());
    }

    public MetricsAggregator(IMetricsClient iMetricsClient, ILogger iLogger, SentryDateProvider sentryDateProvider, int i, SentryOptions.BeforeEmitMetricCallback beforeEmitMetricCallback, ISentryExecutorService iSentryExecutorService) {
        this.isClosed = false;
        this.flushScheduled = false;
        this.buckets = new ConcurrentSkipListMap();
        this.totalBucketsWeight = new AtomicInteger();
        this.client = iMetricsClient;
        this.logger = iLogger;
        this.dateProvider = sentryDateProvider;
        this.maxWeight = i;
        this.beforeEmitCallback = beforeEmitMetricCallback;
        this.executorService = iSentryExecutorService;
    }

    @Override // io.sentry.IMetricsAggregator
    public void increment(String str, double d, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        add(MetricType.Counter, str, d, measurementUnit, map, j, localMetricsAggregator);
    }

    @Override // io.sentry.IMetricsAggregator
    public void gauge(String str, double d, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        add(MetricType.Gauge, str, d, measurementUnit, map, j, localMetricsAggregator);
    }

    @Override // io.sentry.IMetricsAggregator
    public void distribution(String str, double d, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        add(MetricType.Distribution, str, d, measurementUnit, map, j, localMetricsAggregator);
    }

    @Override // io.sentry.IMetricsAggregator
    public void set(String str, int i, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        add(MetricType.Set, str, i, measurementUnit, map, j, localMetricsAggregator);
    }

    @Override // io.sentry.IMetricsAggregator
    public void set(String str, String str2, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        byte[] bytes = str2.getBytes(UTF8);
        new CRC32().update(bytes, 0, bytes.length);
        add(MetricType.Set, str, (int) r1.getValue(), measurementUnit, map, j, localMetricsAggregator);
    }

    private void add(MetricType metricType, String str, double d, MeasurementUnit measurementUnit, Map<String, String> map, long j, LocalMetricsAggregator localMetricsAggregator) {
        Metric counterMetric;
        Metric setMetric;
        int weight;
        double d2 = d;
        if (this.isClosed) {
            return;
        }
        SentryOptions.BeforeEmitMetricCallback beforeEmitMetricCallback = this.beforeEmitCallback;
        if (beforeEmitMetricCallback != null) {
            try {
                if (!beforeEmitMetricCallback.execute(str, map)) {
                    return;
                }
            } catch (Throwable th) {
                this.logger.log(SentryLevel.ERROR, "The beforeEmit callback threw an exception.", th);
            }
        }
        Map<String, Metric> orAddTimeBucket = getOrAddTimeBucket(MetricsHelper.getTimeBucketKey(j));
        String metricBucketKey = MetricsHelper.getMetricBucketKey(metricType, str, measurementUnit, map);
        synchronized (orAddTimeBucket) {
            Metric metric = orAddTimeBucket.get(metricBucketKey);
            if (metric != null) {
                int weight2 = metric.getWeight();
                metric.add(d2);
                weight = metric.getWeight() - weight2;
            } else {
                int i = AnonymousClass1.$SwitchMap$io$sentry$metrics$MetricType[metricType.ordinal()];
                if (i == 1) {
                    counterMetric = new CounterMetric(str, d, measurementUnit, map);
                } else if (i == 2) {
                    counterMetric = new GaugeMetric(str, d, measurementUnit, map);
                } else if (i == 3) {
                    counterMetric = new DistributionMetric(str, d, measurementUnit, map);
                } else if (i == 4) {
                    setMetric = new SetMetric(str, measurementUnit, map);
                    setMetric.add((int) d2);
                    weight = setMetric.getWeight();
                    orAddTimeBucket.put(metricBucketKey, setMetric);
                } else {
                    throw new IllegalArgumentException("Unknown MetricType: " + metricType.name());
                }
                setMetric = counterMetric;
                weight = setMetric.getWeight();
                orAddTimeBucket.put(metricBucketKey, setMetric);
            }
            this.totalBucketsWeight.addAndGet(weight);
        }
        if (localMetricsAggregator != null) {
            if (metricType == MetricType.Set) {
                d2 = weight;
            }
            localMetricsAggregator.add(metricBucketKey, metricType, str, d2, measurementUnit, map);
        }
        boolean zIsOverWeight = isOverWeight();
        if (this.isClosed) {
            return;
        }
        if (zIsOverWeight || !this.flushScheduled) {
            synchronized (this) {
                if (!this.isClosed) {
                    if (this.executorService instanceof NoOpSentryExecutorService) {
                        this.executorService = new SentryExecutorService();
                    }
                    this.flushScheduled = true;
                    this.executorService.schedule(this, zIsOverWeight ? 0L : 5000L);
                }
            }
        }
    }

    /* renamed from: io.sentry.MetricsAggregator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$metrics$MetricType;

        static {
            int[] iArr = new int[MetricType.values().length];
            $SwitchMap$io$sentry$metrics$MetricType = iArr;
            try {
                iArr[MetricType.Counter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$sentry$metrics$MetricType[MetricType.Gauge.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$sentry$metrics$MetricType[MetricType.Distribution.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$sentry$metrics$MetricType[MetricType.Set.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.sentry.IMetricsAggregator
    public void flush(boolean z) {
        if (!z && isOverWeight()) {
            this.logger.log(SentryLevel.INFO, "Metrics: total weight exceeded, flushing all buckets", new Object[0]);
            z = true;
        }
        this.flushScheduled = false;
        Set<Long> flushableBuckets = getFlushableBuckets(z);
        if (flushableBuckets.isEmpty()) {
            this.logger.log(SentryLevel.DEBUG, "Metrics: nothing to flush", new Object[0]);
            return;
        }
        this.logger.log(SentryLevel.DEBUG, "Metrics: flushing " + flushableBuckets.size() + " buckets", new Object[0]);
        HashMap map = new HashMap();
        Iterator<Long> it = flushableBuckets.iterator();
        int size = 0;
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            Map map2 = (Map) this.buckets.remove(Long.valueOf(jLongValue));
            if (map2 != null) {
                synchronized (map2) {
                    this.totalBucketsWeight.addAndGet(-getBucketWeight(map2));
                    size += map2.size();
                    map.put(Long.valueOf(jLongValue), map2);
                }
            }
        }
        if (size == 0) {
            this.logger.log(SentryLevel.DEBUG, "Metrics: only empty buckets found", new Object[0]);
        } else {
            this.logger.log(SentryLevel.DEBUG, "Metrics: capturing metrics", new Object[0]);
            this.client.captureMetrics(new EncodedMetrics(map));
        }
    }

    private boolean isOverWeight() {
        return this.buckets.size() + this.totalBucketsWeight.get() >= this.maxWeight;
    }

    private static int getBucketWeight(Map<String, Metric> map) {
        Iterator<Metric> it = map.values().iterator();
        int weight = 0;
        while (it.hasNext()) {
            weight += it.next().getWeight();
        }
        return weight;
    }

    private Set<Long> getFlushableBuckets(boolean z) {
        if (z) {
            return this.buckets.keySet();
        }
        return this.buckets.headMap(Long.valueOf(MetricsHelper.getTimeBucketKey(MetricsHelper.getCutoffTimestampMs(nowMillis()))), true).keySet();
    }

    private Map<String, Metric> getOrAddTimeBucket(long j) {
        Map<String, Metric> map = (Map) this.buckets.get(Long.valueOf(j));
        if (map == null) {
            synchronized (this.buckets) {
                map = (Map) this.buckets.get(Long.valueOf(j));
                if (map == null) {
                    map = new HashMap<>();
                    this.buckets.put(Long.valueOf(j), map);
                }
            }
        }
        return map;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            this.isClosed = true;
            this.executorService.close(0L);
        }
        flush(true);
    }

    @Override // java.lang.Runnable
    public void run() {
        flush(false);
        synchronized (this) {
            if (!this.isClosed && !this.buckets.isEmpty()) {
                this.executorService.schedule(this, 5000L);
            }
        }
    }

    private long nowMillis() {
        return TimeUnit.NANOSECONDS.toMillis(this.dateProvider.now().nanoTimestamp());
    }
}
