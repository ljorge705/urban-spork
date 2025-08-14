package com.uxcam.internals;

import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes6.dex */
public final class gm {

    /* renamed from: a, reason: collision with root package name */
    public static final long f179a;

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TimeZones.GMT_ID));
        calendar.set(1904, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        f179a = calendar.getTimeInMillis();
    }

    public static int a(long j) {
        return (int) ((j - f179a) / 1000);
    }
}
