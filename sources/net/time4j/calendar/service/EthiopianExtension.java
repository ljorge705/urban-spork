package net.time4j.calendar.service;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import net.time4j.PlainTime;
import net.time4j.calendar.EthiopianTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;

/* loaded from: classes4.dex */
public class EthiopianExtension implements ChronoExtension {
    @Override // net.time4j.engine.ChronoExtension
    public boolean accept(Class<?> cls) {
        return false;
    }

    @Override // net.time4j.engine.ChronoExtension
    public Set<ChronoElement<?>> getElements(Locale locale, AttributeQuery attributeQuery) {
        return Collections.singleton(EthiopianTime.ETHIOPIAN_HOUR);
    }

    @Override // net.time4j.engine.ChronoExtension
    public ChronoEntity<?> resolve(ChronoEntity<?> chronoEntity, Locale locale, AttributeQuery attributeQuery) {
        if (!chronoEntity.contains(EthiopianTime.ETHIOPIAN_HOUR)) {
            return chronoEntity;
        }
        int iIntValue = ((Integer) chronoEntity.get(EthiopianTime.ETHIOPIAN_HOUR)).intValue();
        if (iIntValue == 12) {
            iIntValue = 0;
        }
        int i = iIntValue + 6;
        if (i >= 12) {
            i = iIntValue - 6;
        }
        return chronoEntity.with((ChronoElement<Integer>) PlainTime.DIGITAL_HOUR_OF_AMPM, i);
    }

    @Override // net.time4j.engine.ChronoExtension
    public boolean canResolve(ChronoElement<?> chronoElement) {
        return EthiopianTime.ETHIOPIAN_HOUR.equals(chronoElement);
    }
}
