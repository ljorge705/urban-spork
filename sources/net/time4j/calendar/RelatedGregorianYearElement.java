package net.time4j.calendar;

import com.clevertap.android.sdk.Constants;
import java.io.ObjectStreamException;
import net.time4j.engine.BasicElement;

/* loaded from: classes4.dex */
final class RelatedGregorianYearElement extends BasicElement<Integer> {
    static final RelatedGregorianYearElement SINGLETON = new RelatedGregorianYearElement();
    private static final long serialVersionUID = -1117064522468823402L;

    @Override // net.time4j.engine.BasicElement, net.time4j.engine.ChronoElement
    public char getSymbol() {
        return Constants.INAPP_POSITION_RIGHT;
    }

    @Override // net.time4j.engine.ChronoElement
    public boolean isDateElement() {
        return true;
    }

    @Override // net.time4j.engine.BasicElement
    protected boolean isSingleton() {
        return true;
    }

    @Override // net.time4j.engine.ChronoElement
    public boolean isTimeElement() {
        return false;
    }

    protected Object readResolve() throws ObjectStreamException {
        return SINGLETON;
    }

    private RelatedGregorianYearElement() {
        super("RELATED_GREGORIAN_YEAR");
    }

    @Override // net.time4j.engine.ChronoElement
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override // net.time4j.engine.ChronoElement
    public Integer getDefaultMinimum() {
        return -999999999;
    }

    @Override // net.time4j.engine.ChronoElement
    public Integer getDefaultMaximum() {
        return 999999999;
    }
}
