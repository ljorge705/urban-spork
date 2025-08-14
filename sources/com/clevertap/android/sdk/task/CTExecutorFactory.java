package com.clevertap.android.sdk.task;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CTExecutorFactory {
    private static final String TAG_RESOURCE_DOWNLOADER = "Resource Downloader";
    private static final Map<String, CTExecutors> executorMap = Collections.synchronizedMap(new HashMap());

    public static CTExecutors executors(CleverTapInstanceConfig cleverTapInstanceConfig) {
        if (cleverTapInstanceConfig == null) {
            throw new IllegalArgumentException("Can't create task for null config");
        }
        Map<String, CTExecutors> map = executorMap;
        CTExecutors cTExecutors = map.get(cleverTapInstanceConfig.getAccountId());
        if (cTExecutors == null) {
            synchronized (CTExecutorFactory.class) {
                cTExecutors = map.get(cleverTapInstanceConfig.getAccountId());
                if (cTExecutors == null) {
                    cTExecutors = new CTExecutors(cleverTapInstanceConfig);
                    map.put(cleverTapInstanceConfig.getAccountId(), cTExecutors);
                }
            }
        }
        return cTExecutors;
    }

    public static CTExecutors executorResourceDownloader() {
        return executorResourceDownloader(8);
    }

    public static CTExecutors executorResourceDownloader(int i) {
        Map<String, CTExecutors> map = executorMap;
        CTExecutors cTExecutors = map.get(TAG_RESOURCE_DOWNLOADER);
        if (cTExecutors == null) {
            synchronized (CTExecutorFactory.class) {
                cTExecutors = map.get(TAG_RESOURCE_DOWNLOADER);
                if (cTExecutors == null) {
                    cTExecutors = new CTExecutors(i);
                    map.put(TAG_RESOURCE_DOWNLOADER, cTExecutors);
                }
            }
        }
        return cTExecutors;
    }
}
