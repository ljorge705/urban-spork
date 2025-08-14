package net.time4j.format.expert;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.ChronoElement;

/* loaded from: classes4.dex */
public final class ElementPosition {
    private final ChronoElement<?> element;
    private final int endIndex;
    private final int startIndex;

    public ChronoElement<?> getElement() {
        return this.element;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public ElementPosition(ChronoElement<?> chronoElement, int i, int i2) {
        if (chronoElement == null) {
            throw new NullPointerException("Missing chronological element.");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Negative start index: " + i + " (" + chronoElement.name() + ")");
        }
        if (i2 <= i) {
            throw new IllegalArgumentException("End index " + i2 + " must be greater than start index " + i + " (" + chronoElement.name() + ")");
        }
        this.element = chronoElement;
        this.startIndex = i;
        this.endIndex = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElementPosition)) {
            return false;
        }
        ElementPosition elementPosition = (ElementPosition) obj;
        return this.element.equals(elementPosition.element) && this.startIndex == elementPosition.startIndex && this.endIndex == elementPosition.endIndex;
    }

    public int hashCode() {
        return this.element.hashCode() + ((this.startIndex | (this.endIndex << 16)) * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append(getClass().getName());
        sb.append("[element=");
        sb.append(this.element.name());
        sb.append(",start-index=");
        sb.append(this.startIndex);
        sb.append(",end-index=");
        sb.append(this.endIndex);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
