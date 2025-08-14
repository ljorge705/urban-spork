package net.time4j.format.expert;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.Moment;
import net.time4j.ZonalDateTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.Chronology;

/* loaded from: classes4.dex */
final class CustomizedProcessor<V> implements FormatProcessor<V> {
    private static final ChronoFunction<ChronoDisplay, Void> NO_RESULT = new ChronoFunction<ChronoDisplay, Void>() { // from class: net.time4j.format.expert.CustomizedProcessor.1
        @Override // net.time4j.engine.ChronoFunction
        public Void apply(ChronoDisplay chronoDisplay) {
            return null;
        }
    };
    private final ChronoElement<V> element;
    private boolean optParser;
    private boolean optPrinter;
    private final ChronoParser<V> parser;
    private final boolean passThroughZDT;
    private final ChronoPrinter<V> printer;
    private boolean singleStepMode;

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<V> getElement() {
        return this.element;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return false;
    }

    boolean isSingleStepMode() {
        return this.singleStepMode;
    }

    CustomizedProcessor(ChronoElement<V> chronoElement, ChronoPrinter<V> chronoPrinter, ChronoParser<V> chronoParser) {
        this(chronoElement, chronoPrinter, chronoParser, false, false, false);
    }

    private CustomizedProcessor(ChronoElement<V> chronoElement, ChronoPrinter<V> chronoPrinter, ChronoParser<V> chronoParser, boolean z, boolean z2, boolean z3) {
        if (chronoElement == null) {
            throw new NullPointerException("Missing element.");
        }
        if (chronoPrinter == null) {
            throw new NullPointerException("Missing printer.");
        }
        if (chronoParser == null) {
            throw new NullPointerException("Missing parser.");
        }
        this.element = chronoElement;
        this.printer = chronoPrinter;
        this.parser = chronoParser;
        this.passThroughZDT = (chronoPrinter instanceof ChronoFormatter) && chronoElement.getType() == Moment.class;
        this.optPrinter = z;
        this.optParser = z2;
        this.singleStepMode = z3;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public int print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, Set<ElementPosition> set, boolean z) throws IOException {
        if (z && this.optPrinter) {
            attributeQuery = ((ChronoFormatter) ChronoFormatter.class.cast(this.printer)).getAttributes();
        }
        if (this.passThroughZDT && (chronoDisplay instanceof ZonalDateTime) && set == null) {
            ((ChronoFormatter) this.printer).print(chronoDisplay, appendable, attributeQuery, false);
            return Integer.MAX_VALUE;
        }
        Object obj = chronoDisplay.get(this.element);
        StringBuilder sb = new StringBuilder();
        if ((appendable instanceof CharSequence) && set != null) {
            int length = ((CharSequence) appendable).length();
            ChronoPrinter<V> chronoPrinter = this.printer;
            if (chronoPrinter instanceof ChronoFormatter) {
                Set<ElementPosition> setPrint = print((ChronoFormatter) ChronoFormatter.class.cast(chronoPrinter), obj, sb, attributeQuery);
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (ElementPosition elementPosition : setPrint) {
                    linkedHashSet.add(new ElementPosition(elementPosition.getElement(), elementPosition.getStartIndex() + length, elementPosition.getEndIndex() + length));
                }
                set.addAll(linkedHashSet);
            } else {
                chronoPrinter.print(obj, sb, attributeQuery, NO_RESULT);
            }
            set.add(new ElementPosition(this.element, length, sb.length() + length));
        } else {
            this.printer.print(obj, sb, attributeQuery, NO_RESULT);
        }
        appendable.append(sb);
        return sb.length();
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public void parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, ParsedEntity<?> parsedEntity, boolean z) {
        int position = parseLog.getPosition();
        if (z) {
            try {
                if (this.optParser) {
                    attributeQuery = ((ChronoFormatter) ChronoFormatter.class.cast(this.parser)).getAttributes();
                }
            } catch (IndexOutOfBoundsException e) {
                parseLog.setError(position, e.getMessage());
                return;
            }
        }
        V v = this.parser.parse(charSequence, parseLog, attributeQuery);
        if (v == null) {
            parseLog.setError(position, parseLog.getErrorMessage());
            return;
        }
        if (this.singleStepMode && (parsedEntity instanceof ParsedValue)) {
            parsedEntity.setResult(v);
            return;
        }
        ChronoEntity<?> rawValues = parseLog.getRawValues();
        for (ChronoElement<?> chronoElement : rawValues.getRegisteredElements()) {
            if (chronoElement.getType() == Integer.class) {
                parsedEntity.put(chronoElement, rawValues.getInt(chronoElement));
            } else {
                parsedEntity.put(chronoElement, rawValues.get(chronoElement));
            }
        }
        parsedEntity.put((ChronoElement<?>) this.element, (Object) v);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomizedProcessor)) {
            return false;
        }
        CustomizedProcessor customizedProcessor = (CustomizedProcessor) obj;
        return this.element.equals(customizedProcessor.element) && this.printer.equals(customizedProcessor.printer) && this.parser.equals(customizedProcessor.parser);
    }

    public int hashCode() {
        return (this.element.hashCode() * 7) + (this.printer.hashCode() * 31) + (this.parser.hashCode() * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append("[element=");
        sb.append(this.element.name());
        sb.append(", printer=");
        sb.append(this.printer);
        sb.append(", parser=");
        sb.append(this.parser);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<V> withElement(ChronoElement<V> chronoElement) {
        return this.element == chronoElement ? this : new CustomizedProcessor(chronoElement, this.printer, this.parser);
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<V> quickPath(ChronoFormatter<?> chronoFormatter, AttributeQuery attributeQuery, int i) {
        ChronoPrinter<V> chronoPrinterWith;
        boolean z;
        boolean z2;
        ChronoParser<V> chronoParserWith;
        boolean z3 = chronoFormatter.isSingleStepOptimizationPossible() && this.element.getType().equals(chronoFormatter.getChronology().getChronoType());
        if (!(attributeQuery instanceof AttributeSet)) {
            return (this.optPrinter || this.optParser) ? new CustomizedProcessor(this.element, this.printer, this.parser) : this;
        }
        ChronoPrinter<V> chronoPrinter = this.printer;
        ChronoParser<V> chronoParser = this.parser;
        Map<ChronoElement<?>, Object> defaults = chronoFormatter.getDefaults();
        AttributeSet attributeSet = (AttributeSet) attributeQuery;
        ChronoPrinter<V> chronoPrinter2 = this.printer;
        if (chronoPrinter2 instanceof ChronoFormatter) {
            ChronoFormatter chronoFormatter2 = (ChronoFormatter) ChronoFormatter.class.cast(chronoPrinter2);
            chronoPrinterWith = chronoFormatter2.with(adjust(defaults, chronoFormatter2), attributeSet);
            z = true;
        } else {
            chronoPrinterWith = chronoPrinter;
            z = false;
        }
        ChronoParser<V> chronoParser2 = this.parser;
        if (chronoParser2 instanceof ChronoFormatter) {
            ChronoFormatter chronoFormatter3 = (ChronoFormatter) ChronoFormatter.class.cast(chronoParser2);
            chronoParserWith = chronoFormatter3.with(adjust(defaults, chronoFormatter3), attributeSet);
            z2 = true;
        } else {
            z2 = false;
            chronoParserWith = chronoParser;
        }
        return new CustomizedProcessor(this.element, chronoPrinterWith, chronoParserWith, z, z2, z3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Map<ChronoElement<?>, Object> adjust(Map<ChronoElement<?>, Object> map, ChronoFormatter<?> chronoFormatter) {
        Chronology<?> chronology = chronoFormatter.getChronology();
        HashMap map2 = new HashMap();
        for (ChronoElement<?> chronoElement : map.keySet()) {
            if (chronology.isSupported(chronoElement)) {
                map2.put(chronoElement, map.get(chronoElement));
            }
        }
        return map2;
    }

    private static <T> Set<ElementPosition> print(ChronoFormatter<T> chronoFormatter, Object obj, StringBuilder sb, AttributeQuery attributeQuery) throws IOException {
        return chronoFormatter.print(chronoFormatter.getChronology().getChronoType().cast(obj), sb, attributeQuery);
    }
}
