package io.sentry;

import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.TransactionNameSource;
import io.sentry.protocol.User;
import io.sentry.util.SampleRateUtils;
import io.sentry.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes6.dex */
public final class Baggage {
    static final String CHARSET = "UTF-8";
    static final String SENTRY_BAGGAGE_PREFIX = "sentry-";
    final Map<String, String> keyValues;
    final ILogger logger;
    private boolean mutable;
    final String thirdPartyHeader;
    static final Integer MAX_BAGGAGE_STRING_LENGTH = 8192;
    static final Integer MAX_BAGGAGE_LIST_MEMBER_COUNT = 64;

    public static final class DSCKeys {
        public static final String TRACE_ID = "sentry-trace_id";
        public static final String PUBLIC_KEY = "sentry-public_key";
        public static final String RELEASE = "sentry-release";
        public static final String USER_ID = "sentry-user_id";
        public static final String ENVIRONMENT = "sentry-environment";
        public static final String USER_SEGMENT = "sentry-user_segment";
        public static final String TRANSACTION = "sentry-transaction";
        public static final String SAMPLE_RATE = "sentry-sample_rate";
        public static final String SAMPLED = "sentry-sampled";
        public static final String REPLAY_ID = "sentry-replay_id";
        public static final List<String> ALL = Arrays.asList(TRACE_ID, PUBLIC_KEY, RELEASE, USER_ID, ENVIRONMENT, USER_SEGMENT, TRANSACTION, SAMPLE_RATE, SAMPLED, REPLAY_ID);
    }

    public void freeze() {
        this.mutable = false;
    }

    public String getThirdPartyHeader() {
        return this.thirdPartyHeader;
    }

    public boolean isMutable() {
        return this.mutable;
    }

    public static Baggage fromHeader(String str) {
        return fromHeader(str, false, HubAdapter.getInstance().getOptions().getLogger());
    }

    public static Baggage fromHeader(List<String> list) {
        return fromHeader(list, false, HubAdapter.getInstance().getOptions().getLogger());
    }

    public static Baggage fromHeader(String str, ILogger iLogger) {
        return fromHeader(str, false, iLogger);
    }

    public static Baggage fromHeader(List<String> list, ILogger iLogger) {
        return fromHeader(list, false, iLogger);
    }

    public static Baggage fromHeader(List<String> list, boolean z, ILogger iLogger) {
        if (list != null) {
            return fromHeader(StringUtils.join(Constants.SEPARATOR_COMMA, list), z, iLogger);
        }
        return fromHeader((String) null, z, iLogger);
    }

    public static Baggage fromHeader(String str, boolean z, ILogger iLogger) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        if (str != null) {
            try {
                for (String str2 : str.split(Constants.SEPARATOR_COMMA, -1)) {
                    if (str2.trim().startsWith(SENTRY_BAGGAGE_PREFIX)) {
                        try {
                            int iIndexOf = str2.indexOf("=");
                            map.put(decode(str2.substring(0, iIndexOf).trim()), decode(str2.substring(iIndexOf + 1).trim()));
                            z2 = false;
                        } catch (Throwable th) {
                            iLogger.log(SentryLevel.ERROR, th, "Unable to decode baggage key value pair %s", str2);
                        }
                    } else if (z) {
                        arrayList.add(str2.trim());
                    }
                }
            } catch (Throwable th2) {
                iLogger.log(SentryLevel.ERROR, th2, "Unable to decode baggage header %s", str);
            }
        }
        return new Baggage(map, arrayList.isEmpty() ? null : StringUtils.join(Constants.SEPARATOR_COMMA, arrayList), z2, iLogger);
    }

    public static Baggage fromEvent(SentryEvent sentryEvent, SentryOptions sentryOptions) {
        Baggage baggage = new Baggage(sentryOptions.getLogger());
        SpanContext trace = sentryEvent.getContexts().getTrace();
        baggage.setTraceId(trace != null ? trace.getTraceId().toString() : null);
        baggage.setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        baggage.setRelease(sentryEvent.getRelease());
        baggage.setEnvironment(sentryEvent.getEnvironment());
        User user = sentryEvent.getUser();
        baggage.setUserSegment(user != null ? getSegment(user) : null);
        baggage.setTransaction(sentryEvent.getTransaction());
        baggage.setSampleRate(null);
        baggage.setSampled(null);
        Object obj = sentryEvent.getContexts().get("replay_id");
        if (obj != null && !obj.toString().equals(SentryId.EMPTY_ID.toString())) {
            baggage.setReplayId(obj.toString());
            sentryEvent.getContexts().remove("replay_id");
        }
        baggage.freeze();
        return baggage;
    }

    public Baggage(ILogger iLogger) {
        this(new HashMap(), null, true, iLogger);
    }

    public Baggage(Baggage baggage) {
        this(baggage.keyValues, baggage.thirdPartyHeader, baggage.mutable, baggage.logger);
    }

    public Baggage(Map<String, String> map, String str, boolean z, ILogger iLogger) {
        this.keyValues = map;
        this.logger = iLogger;
        this.mutable = z;
        this.thirdPartyHeader = str;
    }

    public String toHeaderString(String str) {
        String str2;
        int iCountOf;
        StringBuilder sb = new StringBuilder();
        if (str == null || str.isEmpty()) {
            str2 = "";
            iCountOf = 0;
        } else {
            sb.append(str);
            iCountOf = StringUtils.countOf(str, AbstractJsonLexerKt.COMMA) + 1;
            str2 = Constants.SEPARATOR_COMMA;
        }
        for (String str3 : new TreeSet(this.keyValues.keySet())) {
            String str4 = this.keyValues.get(str3);
            if (str4 != null) {
                Integer num = MAX_BAGGAGE_LIST_MEMBER_COUNT;
                if (iCountOf >= num.intValue()) {
                    this.logger.log(SentryLevel.ERROR, "Not adding baggage value %s as the total number of list members would exceed the maximum of %s.", str3, num);
                } else {
                    try {
                        String str5 = str2 + encode(str3) + "=" + encode(str4);
                        int length = sb.length() + str5.length();
                        Integer num2 = MAX_BAGGAGE_STRING_LENGTH;
                        if (length > num2.intValue()) {
                            this.logger.log(SentryLevel.ERROR, "Not adding baggage value %s as the total header value length would exceed the maximum of %s.", str3, num2);
                        } else {
                            iCountOf++;
                            sb.append(str5);
                            str2 = Constants.SEPARATOR_COMMA;
                        }
                    } catch (Throwable th) {
                        this.logger.log(SentryLevel.ERROR, th, "Unable to encode baggage key value pair (key=%s,value=%s).", str3, str4);
                    }
                }
            }
        }
        return sb.toString();
    }

    private String encode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static String decode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, "UTF-8");
    }

    public String get(String str) {
        if (str == null) {
            return null;
        }
        return this.keyValues.get(str);
    }

    public String getTraceId() {
        return get(DSCKeys.TRACE_ID);
    }

    public void setTraceId(String str) {
        set(DSCKeys.TRACE_ID, str);
    }

    public String getPublicKey() {
        return get(DSCKeys.PUBLIC_KEY);
    }

    public void setPublicKey(String str) {
        set(DSCKeys.PUBLIC_KEY, str);
    }

    public String getEnvironment() {
        return get(DSCKeys.ENVIRONMENT);
    }

    public void setEnvironment(String str) {
        set(DSCKeys.ENVIRONMENT, str);
    }

    public String getRelease() {
        return get(DSCKeys.RELEASE);
    }

    public void setRelease(String str) {
        set(DSCKeys.RELEASE, str);
    }

    public String getUserId() {
        return get(DSCKeys.USER_ID);
    }

    public void setUserId(String str) {
        set(DSCKeys.USER_ID, str);
    }

    @Deprecated
    public String getUserSegment() {
        return get(DSCKeys.USER_SEGMENT);
    }

    @Deprecated
    public void setUserSegment(String str) {
        set(DSCKeys.USER_SEGMENT, str);
    }

    public String getTransaction() {
        return get(DSCKeys.TRANSACTION);
    }

    public void setTransaction(String str) {
        set(DSCKeys.TRANSACTION, str);
    }

    public String getSampleRate() {
        return get(DSCKeys.SAMPLE_RATE);
    }

    public String getSampled() {
        return get(DSCKeys.SAMPLED);
    }

    public void setSampleRate(String str) {
        set(DSCKeys.SAMPLE_RATE, str);
    }

    public void setSampled(String str) {
        set(DSCKeys.SAMPLED, str);
    }

    public String getReplayId() {
        return get(DSCKeys.REPLAY_ID);
    }

    public void setReplayId(String str) {
        set(DSCKeys.REPLAY_ID, str);
    }

    public void set(String str, String str2) {
        if (this.mutable) {
            this.keyValues.put(str, str2);
        }
    }

    public Map<String, Object> getUnknown() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry<String, String> entry : this.keyValues.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!DSCKeys.ALL.contains(key) && value != null) {
                concurrentHashMap.put(key.replaceFirst(SENTRY_BAGGAGE_PREFIX, ""), value);
            }
        }
        return concurrentHashMap;
    }

    public void setValuesFromTransaction(ITransaction iTransaction, User user, SentryId sentryId, SentryOptions sentryOptions, TracesSamplingDecision tracesSamplingDecision) {
        setTraceId(iTransaction.getSpanContext().getTraceId().toString());
        setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        setRelease(sentryOptions.getRelease());
        setEnvironment(sentryOptions.getEnvironment());
        setUserSegment(user != null ? getSegment(user) : null);
        setTransaction(isHighQualityTransactionName(iTransaction.getTransactionNameSource()) ? iTransaction.getName() : null);
        if (sentryId != null && !SentryId.EMPTY_ID.equals(sentryId)) {
            setReplayId(sentryId.toString());
        }
        setSampleRate(sampleRateToString(sampleRate(tracesSamplingDecision)));
        setSampled(StringUtils.toString(sampled(tracesSamplingDecision)));
    }

    public void setValuesFromScope(IScope iScope, SentryOptions sentryOptions) {
        PropagationContext propagationContext = iScope.getPropagationContext();
        User user = iScope.getUser();
        SentryId replayId = iScope.getReplayId();
        setTraceId(propagationContext.getTraceId().toString());
        setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        setRelease(sentryOptions.getRelease());
        setEnvironment(sentryOptions.getEnvironment());
        if (!SentryId.EMPTY_ID.equals(replayId)) {
            setReplayId(replayId.toString());
        }
        setUserSegment(user != null ? getSegment(user) : null);
        setTransaction(null);
        setSampleRate(null);
        setSampled(null);
    }

    @Deprecated
    private static String getSegment(User user) {
        if (user.getSegment() != null) {
            return user.getSegment();
        }
        Map<String, String> data = user.getData();
        if (data != null) {
            return data.get("segment");
        }
        return null;
    }

    private static Double sampleRate(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampleRate();
    }

    private static String sampleRateToString(Double d) {
        if (SampleRateUtils.isValidTracesSampleRate(d, false)) {
            return new DecimalFormat("#.################", DecimalFormatSymbols.getInstance(Locale.ROOT)).format(d);
        }
        return null;
    }

    private static Boolean sampled(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampled();
    }

    private static boolean isHighQualityTransactionName(TransactionNameSource transactionNameSource) {
        return (transactionNameSource == null || TransactionNameSource.URL.equals(transactionNameSource)) ? false : true;
    }

    public Double getSampleRateDouble() throws NumberFormatException {
        String sampleRate = getSampleRate();
        if (sampleRate != null) {
            try {
                double d = Double.parseDouble(sampleRate);
                if (SampleRateUtils.isValidTracesSampleRate(Double.valueOf(d), false)) {
                    return Double.valueOf(d);
                }
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public TraceContext toTraceContext() {
        String traceId = getTraceId();
        String replayId = getReplayId();
        String publicKey = getPublicKey();
        if (traceId == null || publicKey == null) {
            return null;
        }
        TraceContext traceContext = new TraceContext(new SentryId(traceId), publicKey, getRelease(), getEnvironment(), getUserId(), getUserSegment(), getTransaction(), getSampleRate(), getSampled(), replayId == null ? null : new SentryId(replayId));
        traceContext.setUnknown(getUnknown());
        return traceContext;
    }
}
