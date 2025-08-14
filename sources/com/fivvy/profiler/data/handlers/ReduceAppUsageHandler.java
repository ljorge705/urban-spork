package com.fivvy.profiler.data.handlers;

import com.fivvy.profiler.domain.models.AppUsageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ReduceAppUsageHandler {
    public static List<AppUsageInfo> reduceAppUsage(List<AppUsageInfo> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<AppUsageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPackageName());
        }
        String appName = "";
        for (String str : filterDuplicates(arrayList)) {
            double usage = 0.0d;
            for (AppUsageInfo appUsageInfo : list) {
                if (appUsageInfo.getPackageName().equals(str)) {
                    usage += appUsageInfo.getUsage();
                    appName = appUsageInfo.getAppName();
                }
            }
            arrayList2.add(new AppUsageInfo(appName, str, usage));
        }
        return arrayList2;
    }

    private static List<String> filterDuplicates(List<String> list) {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (hashSet.add(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
