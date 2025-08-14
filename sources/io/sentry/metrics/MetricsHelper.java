package io.sentry.metrics;

import com.clevertap.android.sdk.Constants;
import io.sentry.MeasurementUnit;
import io.sentry.util.Random;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public final class MetricsHelper {
    public static final long FLUSHER_SLEEP_TIME_MS = 5000;
    public static final int MAX_TOTAL_WEIGHT = 100000;
    private static final int ROLLUP_IN_SECONDS = 10;
    private static final char TAGS_ESCAPE_CHAR = '\\';
    private static final char TAGS_KEY_VALUE_DELIMITER = '=';
    private static final char TAGS_PAIR_DELIMITER = ',';
    private static final Pattern UNIT_PATTERN = Pattern.compile("\\W+");
    private static final Pattern NAME_PATTERN = Pattern.compile("[^\\w\\-.]+");
    private static final Pattern TAG_KEY_PATTERN = Pattern.compile("[^\\w\\-./]+");
    private static long FLUSH_SHIFT_MS = (long) (new Random().nextFloat() * 10000.0f);

    public static long getCutoffTimestampMs(long j) {
        return (j - 10000) - FLUSH_SHIFT_MS;
    }

    public static void setFlushShiftMs(long j) {
        FLUSH_SHIFT_MS = j;
    }

    public static long getTimeBucketKey(long j) {
        long j2 = ((j / 1000) / 10) * 10;
        return j >= 0 ? j2 : j2 - 1;
    }

    public static String sanitizeUnit(String str) {
        return UNIT_PATTERN.matcher(str).replaceAll("");
    }

    public static String sanitizeName(String str) {
        return NAME_PATTERN.matcher(str).replaceAll("_");
    }

    public static String sanitizeTagKey(String str) {
        return TAG_KEY_PATTERN.matcher(str).replaceAll("");
    }

    public static String sanitizeTagValue(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                sb.append("\\n");
            } else if (cCharAt == '\r') {
                sb.append("\\r");
            } else if (cCharAt == '\t') {
                sb.append("\\t");
            } else if (cCharAt == '\\') {
                sb.append("\\\\");
            } else if (cCharAt == '|') {
                sb.append("\\u{7c}");
            } else if (cCharAt == ',') {
                sb.append("\\u{2c}");
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String getMetricBucketKey(MetricType metricType, String str, MeasurementUnit measurementUnit, Map<String, String> map) {
        return String.format("%s_%s_%s_%s", metricType.statsdCode, str, getUnitName(measurementUnit), getTagsKey(map));
    }

    private static String getUnitName(MeasurementUnit measurementUnit) {
        return measurementUnit != null ? measurementUnit.apiName() : "none";
    }

    private static String getTagsKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strEscapeString = escapeString(entry.getKey());
            String strEscapeString2 = escapeString(entry.getValue());
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(strEscapeString).append(TAGS_KEY_VALUE_DELIMITER).append(strEscapeString2);
        }
        return sb.toString();
    }

    private static String escapeString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ',' || cCharAt == '=') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String getExportKey(MetricType metricType, String str, MeasurementUnit measurementUnit) {
        return String.format("%s:%s@%s", metricType.statsdCode, str, getUnitName(measurementUnit));
    }

    /* renamed from: io.sentry.metrics.MetricsHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$MeasurementUnit$Duration;

        static {
            int[] iArr = new int[MeasurementUnit.Duration.values().length];
            $SwitchMap$io$sentry$MeasurementUnit$Duration = iArr;
            try {
                iArr[MeasurementUnit.Duration.NANOSECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.MICROSECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.MILLISECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.SECOND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.MINUTE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.HOUR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.DAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$sentry$MeasurementUnit$Duration[MeasurementUnit.Duration.WEEK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static double convertNanosTo(MeasurementUnit.Duration duration, long j) {
        switch (AnonymousClass1.$SwitchMap$io$sentry$MeasurementUnit$Duration[duration.ordinal()]) {
            case 1:
                return j;
            case 2:
                return j / 1000.0d;
            case 3:
                return j / 1000000.0d;
            case 4:
                return j / 1.0E9d;
            case 5:
                return j / 6.0E10d;
            case 6:
                return j / 3.6E12d;
            case 7:
                return j / 8.64E13d;
            case 8:
                return (j / 8.64E13d) / 7.0d;
            default:
                throw new IllegalArgumentException("Unknown Duration unit: " + duration.name());
        }
    }

    public static void encodeMetrics(long j, Collection<Metric> collection, StringBuilder sb) {
        for (Metric metric : collection) {
            sb.append(sanitizeName(metric.getKey()));
            sb.append("@");
            sb.append(sanitizeUnit(getUnitName(metric.getUnit())));
            for (Object obj : metric.serialize()) {
                sb.append(":");
                sb.append(obj);
            }
            sb.append("|");
            sb.append(metric.getType().statsdCode);
            Map<String, String> tags = metric.getTags();
            if (tags != null) {
                sb.append("|#");
                boolean z = true;
                for (Map.Entry<String, String> entry : tags.entrySet()) {
                    String strSanitizeTagKey = sanitizeTagKey(entry.getKey());
                    if (z) {
                        z = false;
                    } else {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                    sb.append(strSanitizeTagKey);
                    sb.append(":");
                    sb.append(sanitizeTagValue(entry.getValue()));
                }
            }
            sb.append("|T");
            sb.append(j);
            sb.append("\n");
        }
    }

    public static Map<String, String> mergeTags(Map<String, String> map, Map<String, String> map2) {
        if (map == null) {
            return Collections.unmodifiableMap(map2);
        }
        HashMap map3 = new HashMap(map);
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            if (!map3.containsKey(key)) {
                map3.put(key, entry.getValue());
            }
        }
        return map3;
    }
}
