package net.time4j.scale;

import net.time4j.base.GregorianDate;

/* loaded from: classes4.dex */
public interface LeapSecondEvent {
    GregorianDate getDate();

    int getShift();
}
