package com.nimbusds.jwt.util;

import java.util.Date;

/* loaded from: classes2.dex */
public class DateUtils {
    public static Date nowWithSecondsPrecision() {
        return fromSecondsSinceEpoch(toSecondsSinceEpoch(new Date()));
    }

    public static long toSecondsSinceEpoch(Date date) {
        return date.getTime() / 1000;
    }

    public static Date fromSecondsSinceEpoch(long j) {
        return new Date(j * 1000);
    }

    public static boolean isAfter(Date date, Date date2, long j) {
        return new Date(date.getTime() + (j * 1000)).after(date2);
    }

    public static boolean isBefore(Date date, Date date2, long j) {
        return new Date(date.getTime() - (j * 1000)).before(date2);
    }

    public static boolean isWithin(Date date, Date date2, long j) {
        long j2 = j * 1000;
        return date.getTime() > date2.getTime() - j2 && date.getTime() < date2.getTime() + j2;
    }

    private DateUtils() {
    }
}
