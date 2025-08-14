package net.time4j.format.expert;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.CalendarUnit;
import net.time4j.DayPeriod;
import net.time4j.GeneralTimestamp;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.base.TimeSource;
import net.time4j.base.UnixTime;
import net.time4j.engine.AttributeKey;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.BridgeChronology;
import net.time4j.engine.CalendarFamily;
import net.time4j.engine.CalendarVariant;
import net.time4j.engine.Calendrical;
import net.time4j.engine.ChronoCondition;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoExtension;
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.ChronoMerger;
import net.time4j.engine.Chronology;
import net.time4j.engine.DisplayStyle;
import net.time4j.engine.FlagElement;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.TimeAxis;
import net.time4j.engine.TimePoint;
import net.time4j.engine.ValidationElement;
import net.time4j.engine.VariantSource;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.format.Leniency;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.format.PluralCategory;
import net.time4j.format.RawValues;
import net.time4j.format.TemporalFormatter;
import net.time4j.format.TextElement;
import net.time4j.format.TextWidth;
import net.time4j.history.ChronoHistory;
import net.time4j.history.internal.HistoricAttribute;
import net.time4j.tz.NameStyle;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.OverlapResolver;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionStrategy;
import net.time4j.tz.ZonalOffset;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
public final class ChronoFormatter<T> implements ChronoPrinter<T>, ChronoParser<T>, TemporalFormatter<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final ChronoFormatter<Moment> RFC_1123 = rfc1123();
    private final Chronology<T> chronology;
    private final int countOfElements;
    private final Chronology<?> deepestParser;
    private final Map<ChronoElement<?>, Object> defaults;
    private final FractionProcessor fracproc;
    private final AttributeSet globalAttributes;
    private final boolean hasOptionals;
    private final boolean hasOrMarkers;
    private final boolean indexable;
    private final Leniency leniency;
    private final boolean needsExtensions;
    private final boolean noPreparser;
    private final OverrideHandler<?> overrideHandler;
    private final boolean singleStepMode;
    private final int stepCount;
    private final List<FormatStep> steps;
    private final boolean trailing;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) {
        return obj;
    }

    @Override // net.time4j.format.TemporalFormatter
    public AttributeQuery getAttributes() {
        return this.globalAttributes;
    }

    AttributeSet getAttributes0() {
        return this.globalAttributes;
    }

    public Chronology<T> getChronology() {
        return this.chronology;
    }

    Map<ChronoElement<?>, Object> getDefaults() {
        return this.defaults;
    }

    boolean isSingleStepOptimizationPossible() {
        return this.stepCount == 1 && !this.hasOptionals;
    }

    boolean isToleratingTrailingChars() {
        return this.trailing;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ChronoFormatter(Chronology<T> chronology, Chronology<?> chronology2, Locale locale, List<FormatStep> list, Map<ChronoElement<?>, Object> map, Attributes attributes, Chronology<?> chronology3) {
        if (chronology == null) {
            throw new NullPointerException("Missing chronology.");
        }
        if (list.isEmpty()) {
            throw new IllegalStateException("No format processors defined.");
        }
        this.chronology = chronology;
        this.overrideHandler = OverrideHandler.of(chronology2);
        this.deepestParser = chronology3;
        AttributeSet attributeSetCreateDefaults = AttributeSet.createDefaults(chronology2 == 0 ? chronology : chronology2, attributes, locale);
        this.globalAttributes = attributeSetCreateDefaults;
        this.leniency = (Leniency) attributeSetCreateDefaults.get(Attributes.LENIENCY, Leniency.SMART);
        this.defaults = Collections.unmodifiableMap(map);
        FractionProcessor fractionProcessor = null;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        boolean zNeedsExtension = false;
        int i = 0;
        for (FormatStep formatStep : list) {
            z3 = formatStep.isNewOrBlockStarted() ? true : z3;
            if (fractionProcessor == null && (formatStep.getProcessor() instanceof FractionProcessor)) {
                fractionProcessor = (FractionProcessor) FractionProcessor.class.cast(formatStep.getProcessor());
            }
            if (!z2 && formatStep.getLevel() > 0) {
                z2 = true;
            }
            ChronoElement<?> element = formatStep.getProcessor().getElement();
            if (element != null) {
                i++;
                if (z && !ParsedValues.isIndexed(element)) {
                    z = false;
                }
                if (!zNeedsExtension) {
                    zNeedsExtension = needsExtension(chronology, chronology2, element);
                }
            }
        }
        this.fracproc = fractionProcessor;
        this.hasOptionals = z2;
        this.hasOrMarkers = z3;
        this.needsExtensions = zNeedsExtension;
        this.countOfElements = i;
        this.indexable = z;
        this.trailing = ((Boolean) this.globalAttributes.get(Attributes.TRAILING_CHARACTERS, Boolean.FALSE)).booleanValue();
        this.noPreparser = hasNoPreparser();
        this.stepCount = list.size();
        this.steps = freeze(list);
        this.singleStepMode = getSingleStepMode();
    }

    private ChronoFormatter(ChronoFormatter<T> chronoFormatter, Attributes attributes) {
        this(chronoFormatter, chronoFormatter.globalAttributes.withAttributes(attributes), (ChronoHistory) null);
    }

    private ChronoFormatter(ChronoFormatter<T> chronoFormatter, AttributeSet attributeSet) {
        this(chronoFormatter, attributeSet, (ChronoHistory) null);
    }

    private ChronoFormatter(ChronoFormatter<T> chronoFormatter, AttributeSet attributeSet, ChronoHistory chronoHistory) {
        ChronoElement<Integer> chronoElementMonth;
        if (attributeSet == null) {
            throw new NullPointerException("Missing global format attributes.");
        }
        this.chronology = chronoFormatter.chronology;
        this.overrideHandler = chronoFormatter.overrideHandler;
        this.deepestParser = chronoFormatter.deepestParser;
        this.globalAttributes = attributeSet;
        this.leniency = (Leniency) attributeSet.get(Attributes.LENIENCY, Leniency.SMART);
        this.defaults = Collections.unmodifiableMap(new NonAmbivalentMap(chronoFormatter.defaults));
        this.fracproc = chronoFormatter.fracproc;
        this.hasOptionals = chronoFormatter.hasOptionals;
        this.hasOrMarkers = chronoFormatter.hasOrMarkers;
        this.needsExtensions = chronoFormatter.needsExtensions || chronoHistory != null;
        this.countOfElements = chronoFormatter.countOfElements;
        int size = chronoFormatter.steps.size();
        ArrayList arrayList = new ArrayList(chronoFormatter.steps);
        boolean z = chronoFormatter.indexable;
        for (int i = 0; i < size; i++) {
            FormatStep formatStep = arrayList.get(i);
            ChronoElement<?> element = formatStep.getProcessor().getElement();
            Chronology chronologyPreparser = this.chronology;
            while (chronologyPreparser instanceof BridgeChronology) {
                chronologyPreparser = chronologyPreparser.preparser();
            }
            chronologyPreparser = chronologyPreparser == Moment.axis() ? chronologyPreparser.preparser() : chronologyPreparser;
            if (element != null && !chronologyPreparser.isRegistered(element)) {
                Iterator<ChronoExtension> it = chronologyPreparser.getExtensions().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ChronoExtension next = it.next();
                    if (next.getElements(chronoFormatter.getLocale(), chronoFormatter.globalAttributes).contains(element)) {
                        Iterator<ChronoElement<?>> it2 = next.getElements(attributeSet.getLocale(), attributeSet).iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            ChronoElement<?> next2 = it2.next();
                            if (next2.name().equals(element.name())) {
                                if (next2 != element) {
                                    arrayList.set(i, formatStep.updateElement(next2));
                                    z = false;
                                }
                            }
                        }
                    }
                }
            }
            if (chronoHistory != null) {
                if (element == PlainDate.YEAR) {
                    chronoElementMonth = chronoHistory.yearOfEra();
                } else if (element == PlainDate.MONTH_OF_YEAR || element == PlainDate.MONTH_AS_NUMBER) {
                    chronoElementMonth = chronoHistory.month();
                } else if (element == PlainDate.DAY_OF_MONTH) {
                    chronoElementMonth = chronoHistory.dayOfMonth();
                } else {
                    chronoElementMonth = element == PlainDate.DAY_OF_YEAR ? chronoHistory.dayOfYear() : null;
                }
                if (chronoElementMonth != null) {
                    arrayList.set(i, formatStep.updateElement(chronoElementMonth));
                }
                z = false;
            }
        }
        this.indexable = z;
        this.trailing = ((Boolean) this.globalAttributes.get(Attributes.TRAILING_CHARACTERS, Boolean.FALSE)).booleanValue();
        this.noPreparser = hasNoPreparser();
        this.stepCount = arrayList.size();
        this.steps = freeze(arrayList);
        this.singleStepMode = getSingleStepMode();
    }

    private ChronoFormatter(ChronoFormatter<T> chronoFormatter, Map<ChronoElement<?>, Object> map) {
        OverrideHandler<?> overrideHandler = chronoFormatter.overrideHandler;
        Chronology<?> calendarOverride = overrideHandler == null ? null : overrideHandler.getCalendarOverride();
        Iterator<ChronoElement<?>> it = map.keySet().iterator();
        while (it.hasNext()) {
            checkElement(chronoFormatter.chronology, calendarOverride, it.next());
        }
        this.chronology = chronoFormatter.chronology;
        this.overrideHandler = chronoFormatter.overrideHandler;
        this.deepestParser = chronoFormatter.deepestParser;
        this.globalAttributes = chronoFormatter.globalAttributes;
        this.leniency = chronoFormatter.leniency;
        this.fracproc = chronoFormatter.fracproc;
        this.hasOptionals = chronoFormatter.hasOptionals;
        this.hasOrMarkers = chronoFormatter.hasOrMarkers;
        this.needsExtensions = chronoFormatter.needsExtensions;
        this.countOfElements = chronoFormatter.countOfElements;
        this.trailing = chronoFormatter.trailing;
        HashMap map2 = new HashMap(chronoFormatter.defaults);
        boolean z = chronoFormatter.indexable;
        for (ChronoElement<?> chronoElement : map.keySet()) {
            Object obj = map.get(chronoElement);
            if (obj == null) {
                map2.remove(chronoElement);
            } else {
                map2.put(chronoElement, obj);
                z = z && ParsedValues.isIndexed(chronoElement);
            }
        }
        this.defaults = Collections.unmodifiableMap(map2);
        this.indexable = z;
        this.noPreparser = hasNoPreparser();
        this.stepCount = chronoFormatter.stepCount;
        this.steps = freeze(chronoFormatter.steps);
        this.singleStepMode = getSingleStepMode();
    }

    public Locale getLocale() {
        return this.globalAttributes.getLocale();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getPattern() {
        /*
            r2 = this;
            boolean r0 = r2.isSingleStepOptimizationPossible()
            if (r0 == 0) goto L24
            java.util.List<net.time4j.format.expert.FormatStep> r0 = r2.steps
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            net.time4j.format.expert.FormatStep r0 = (net.time4j.format.expert.FormatStep) r0
            net.time4j.format.expert.FormatProcessor r0 = r0.getProcessor()
            boolean r1 = r0 instanceof net.time4j.format.expert.StyleProcessor
            if (r1 == 0) goto L24
            java.lang.Class<net.time4j.format.expert.StyleProcessor> r1 = net.time4j.format.expert.StyleProcessor.class
            java.lang.Object r0 = r1.cast(r0)
            net.time4j.format.expert.StyleProcessor r0 = (net.time4j.format.expert.StyleProcessor) r0
            java.lang.String r0 = r0.getGeneratedPattern()
            goto L26
        L24:
            java.lang.String r0 = ""
        L26:
            java.lang.String r0 = r2.getPattern0(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.ChronoFormatter.getPattern():java.lang.String");
    }

    @Override // net.time4j.format.TemporalFormatter
    public String print(T t) {
        return format0(display(t, this.globalAttributes));
    }

    @Override // net.time4j.format.TemporalFormatter
    public String format(T t) {
        return print(t);
    }

    public String format(GeneralTimestamp<?> generalTimestamp) {
        return format0(generalTimestamp);
    }

    public Set<ElementPosition> print(T t, StringBuilder sb) {
        try {
            return print(display(t, this.globalAttributes), (Appendable) sb, (AttributeQuery) this.globalAttributes, true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<ElementPosition> print(T t, Appendable appendable, AttributeQuery attributeQuery) throws IOException {
        return print(display(t, attributeQuery), appendable, attributeQuery, true);
    }

    @Override // net.time4j.format.expert.ChronoPrinter
    public <R> R print(T t, Appendable appendable, AttributeQuery attributeQuery, ChronoFunction<ChronoDisplay, R> chronoFunction) throws IOException {
        ChronoDisplay chronoDisplayDisplay = display(t, attributeQuery);
        print(chronoDisplayDisplay, appendable, attributeQuery, false);
        return chronoFunction.apply(chronoDisplayDisplay);
    }

    Set<ElementPosition> print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, boolean z) throws IOException {
        LinkedList linkedList;
        int iPrint;
        LinkedList linkedList2;
        LinkedList linkedList3;
        int iSkipTrailingOrBlocks;
        int i;
        if (appendable == null) {
            throw new NullPointerException("Missing text result buffer.");
        }
        int size = this.steps.size();
        int i2 = 0;
        boolean z2 = attributeQuery == this.globalAttributes;
        Set<ElementPosition> linkedHashSet = z ? new LinkedHashSet<>(size) : null;
        if (this.hasOrMarkers) {
            LinkedList linkedList4 = new LinkedList();
            linkedList4.push(new StringBuilder(size << 2));
            if (z) {
                LinkedList linkedList5 = new LinkedList();
                linkedList5.push(linkedHashSet);
                linkedList = linkedList5;
            } else {
                linkedList = null;
            }
            int i3 = 0;
            while (i3 < size) {
                FormatStep formatStep = this.steps.get(i3);
                int level = formatStep.getLevel();
                int i4 = level;
                while (i4 > i2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append((CharSequence) linkedList4.peek());
                    linkedList4.push(sb);
                    if (z) {
                        linkedHashSet = new LinkedHashSet<>();
                        linkedHashSet.addAll((Collection) linkedList.peek());
                        linkedList.push(linkedHashSet);
                    }
                    i4--;
                }
                while (i4 < i2) {
                    StringBuilder sb2 = (StringBuilder) linkedList4.pop();
                    linkedList4.pop();
                    linkedList4.push(sb2);
                    if (z) {
                        linkedHashSet = (Set) linkedList.pop();
                        linkedList.pop();
                        linkedList.push(linkedHashSet);
                    }
                    i4++;
                }
                StringBuilder sb3 = (StringBuilder) linkedList4.peek();
                if (z) {
                    linkedHashSet = (Set) linkedList.peek();
                }
                Set<ElementPosition> set = linkedHashSet;
                int i5 = i3;
                LinkedList linkedList6 = linkedList;
                LinkedList linkedList7 = linkedList4;
                try {
                    iPrint = formatStep.print(chronoDisplay, sb3, attributeQuery, set, z2);
                    e = null;
                } catch (IllegalArgumentException | ChronoException e) {
                    e = e;
                    iPrint = -1;
                }
                if (iPrint == -1) {
                    int section = formatStep.getSection();
                    if (!formatStep.isNewOrBlockStarted()) {
                        i = i5;
                        iSkipTrailingOrBlocks = i + 1;
                        while (iSkipTrailingOrBlocks < size) {
                            FormatStep formatStep2 = this.steps.get(iSkipTrailingOrBlocks);
                            if (formatStep2.isNewOrBlockStarted() && formatStep2.getSection() == section) {
                                break;
                            }
                            iSkipTrailingOrBlocks++;
                        }
                    } else {
                        i = i5;
                    }
                    iSkipTrailingOrBlocks = i;
                    if (iSkipTrailingOrBlocks <= i && !formatStep.isNewOrBlockStarted()) {
                        if (e == null) {
                            throw new IllegalArgumentException("Not formattable: " + chronoDisplay);
                        }
                        throw new IllegalArgumentException("Not formattable: " + chronoDisplay, e);
                    }
                    linkedList7.pop();
                    StringBuilder sb4 = new StringBuilder();
                    if (!linkedList7.isEmpty()) {
                        sb4.append((CharSequence) linkedList7.peek());
                    }
                    linkedList3 = linkedList7;
                    linkedList3.push(sb4);
                    if (z) {
                        linkedList6.pop();
                        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                        if (!linkedList6.isEmpty()) {
                            linkedHashSet2.addAll((Collection) linkedList6.peek());
                        }
                        linkedList2 = linkedList6;
                        linkedList2.push(linkedHashSet2);
                    } else {
                        linkedList2 = linkedList6;
                    }
                } else {
                    linkedList2 = linkedList6;
                    linkedList3 = linkedList7;
                    iSkipTrailingOrBlocks = formatStep.isNewOrBlockStarted() ? formatStep.skipTrailingOrBlocks() : i5;
                }
                i3 = iSkipTrailingOrBlocks + 1;
                linkedList4 = linkedList3;
                linkedList = linkedList2;
                linkedHashSet = set;
                i2 = level;
            }
            LinkedList linkedList8 = linkedList;
            LinkedList linkedList9 = linkedList4;
            StringBuilder sb5 = (StringBuilder) linkedList9.peek();
            linkedList9.clear();
            appendable.append(sb5);
            if (z) {
                linkedHashSet = (Set) linkedList8.peek();
                linkedList8.clear();
            }
        } else {
            int iSkipTrailingOrBlocks2 = 0;
            while (iSkipTrailingOrBlocks2 < size) {
                try {
                    FormatStep formatStep3 = this.steps.get(iSkipTrailingOrBlocks2);
                    formatStep3.print(chronoDisplay, appendable, attributeQuery, linkedHashSet, z2);
                    if (formatStep3.isNewOrBlockStarted()) {
                        iSkipTrailingOrBlocks2 = formatStep3.skipTrailingOrBlocks();
                    }
                    iSkipTrailingOrBlocks2++;
                } catch (ChronoException e2) {
                    throw new IllegalArgumentException("Not formattable: " + chronoDisplay, e2);
                }
            }
        }
        if (z) {
            return Collections.unmodifiableSet(linkedHashSet);
        }
        return null;
    }

    @Override // net.time4j.format.TemporalFormatter
    public T parse(CharSequence charSequence) throws ParseException {
        ParseLog parseLog = new ParseLog();
        T t = parse(charSequence, parseLog);
        if (t == null) {
            throw new ParseException(parseLog.getErrorMessage(), parseLog.getErrorIndex());
        }
        int position = parseLog.getPosition();
        if (this.trailing || position >= charSequence.length()) {
            return t;
        }
        throw new ParseException("Unparsed trailing characters: " + sub(position, charSequence), position);
    }

    @Override // net.time4j.format.TemporalFormatter
    public T parse(CharSequence charSequence, RawValues rawValues) throws ParseException {
        ParseLog parseLog = new ParseLog();
        T t = parse(charSequence, parseLog);
        rawValues.accept(parseLog.getRawValues());
        if (parseLog.isError()) {
            throw new ParseException(parseLog.getErrorMessage(), parseLog.getErrorIndex());
        }
        if (t != null) {
            return t;
        }
        throw new ParseException("Cannot parse: \"" + ((Object) charSequence) + "\"", 0);
    }

    public T parse(CharSequence charSequence, ParseLog parseLog) {
        if (this.noPreparser) {
            Chronology<T> chronology = this.chronology;
            return (T) parse(this, chronology, chronology.getExtensions(), charSequence, parseLog, this.globalAttributes, this.leniency, false, true);
        }
        return parse(charSequence, parseLog, this.globalAttributes);
    }

    @Override // net.time4j.format.expert.ChronoParser
    public T parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery) {
        Leniency leniency;
        boolean z;
        AttributeQuery attributeQuery2;
        TZID timezone;
        Moment momentIn;
        Leniency leniency2 = this.leniency;
        if (attributeQuery != this.globalAttributes) {
            MergedAttributes mergedAttributes = new MergedAttributes(attributeQuery, this.globalAttributes);
            attributeQuery2 = mergedAttributes;
            leniency = (Leniency) mergedAttributes.get(Attributes.LENIENCY, Leniency.SMART);
            z = false;
        } else {
            leniency = leniency2;
            z = true;
            attributeQuery2 = attributeQuery;
        }
        OverrideHandler<?> overrideHandler = this.overrideHandler;
        if (overrideHandler != null) {
            List<ChronoExtension> extensions = overrideHandler.getExtensions();
            OverrideHandler<?> overrideHandler2 = this.overrideHandler;
            GeneralTimestamp generalTimestamp = (GeneralTimestamp) parse(this, overrideHandler2, extensions, charSequence, parseLog, attributeQuery2, leniency, true, z);
            if (parseLog.isError()) {
                return null;
            }
            ChronoEntity<?> rawValues0 = parseLog.getRawValues0();
            if (rawValues0.hasTimezone()) {
                timezone = rawValues0.getTimezone();
            } else {
                timezone = attributeQuery2.contains(Attributes.TIMEZONE_ID) ? (TZID) attributeQuery2.get(Attributes.TIMEZONE_ID) : null;
            }
            if (timezone != null) {
                StartOfDay startOfDay = (StartOfDay) attributeQuery.get(Attributes.START_OF_DAY, overrideHandler2.getDefaultStartOfDay());
                if (rawValues0.contains(FlagElement.DAYLIGHT_SAVING)) {
                    momentIn = generalTimestamp.in(Timezone.of(timezone).with(((TransitionStrategy) attributeQuery2.get(Attributes.TRANSITION_STRATEGY, Timezone.DEFAULT_CONFLICT_STRATEGY)).using(((Boolean) rawValues0.get(FlagElement.DAYLIGHT_SAVING)).booleanValue() ? OverlapResolver.EARLIER_OFFSET : OverlapResolver.LATER_OFFSET)), startOfDay);
                } else if (attributeQuery2.contains(Attributes.TRANSITION_STRATEGY)) {
                    momentIn = generalTimestamp.in(Timezone.of(timezone).with((TransitionStrategy) attributeQuery2.get(Attributes.TRANSITION_STRATEGY)), startOfDay);
                } else {
                    momentIn = generalTimestamp.in(Timezone.of(timezone), startOfDay);
                }
            } else {
                momentIn = null;
            }
            if (momentIn == null) {
                parseLog.setError(charSequence.length(), "Missing timezone or offset.");
                return null;
            }
            rawValues0.with((ChronoElement<ChronoElement>) Moment.axis().element(), (ChronoElement) momentIn);
            T t = (T) cast(momentIn);
            if (leniency.isStrict()) {
                checkConsistency(rawValues0, t, charSequence, parseLog);
            }
            return t;
        }
        return (T) parse(this, this.chronology, 0, charSequence, parseLog, attributeQuery2, leniency, z);
    }

    public ChronoEntity<?> parseRaw(String str) {
        return parseRaw(str, 0);
    }

    public ChronoEntity<?> parseRaw(CharSequence charSequence, int i) {
        if (i >= charSequence.length()) {
            return new ParsedValues(0, false);
        }
        ParseLog parseLog = new ParseLog(i);
        ChronoEntity<?> elements = null;
        try {
            elements = parseElements(charSequence, parseLog, this.globalAttributes, true, this.countOfElements);
            parseLog.setRawValues(elements);
        } catch (AmbivalentValueException e) {
            if (!parseLog.isError()) {
                parseLog.setError(parseLog.getPosition(), e.getMessage());
            }
        }
        if (elements == null || parseLog.isError()) {
            return new ParsedValues(0, false);
        }
        for (ChronoElement<?> chronoElement : this.defaults.keySet()) {
            if (!elements.contains(chronoElement)) {
                setValue(elements, chronoElement, this.defaults.get(chronoElement));
            }
        }
        return elements;
    }

    @Override // net.time4j.format.TemporalFormatter
    public ChronoFormatter<T> with(Locale locale) {
        return locale.equals(this.globalAttributes.getLocale()) ? this : new ChronoFormatter<>(this, this.globalAttributes.withLocale(locale));
    }

    @Override // net.time4j.format.TemporalFormatter
    public ChronoFormatter<T> with(Leniency leniency) {
        return with((AttributeKey<AttributeKey<Leniency>>) Attributes.LENIENCY, (AttributeKey<Leniency>) leniency);
    }

    public ChronoFormatter<T> withAlternativeEraNames() {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set(HistoricAttribute.COMMON_ERA, true).set(HistoricAttribute.LATIN_ERA, false).build());
    }

    public ChronoFormatter<T> withLatinEraNames() {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set(HistoricAttribute.COMMON_ERA, false).set(HistoricAttribute.LATIN_ERA, true).build());
    }

    public ChronoFormatter<T> withGregorianCutOver(PlainDate plainDate) {
        return with(ChronoHistory.ofGregorianReform(plainDate));
    }

    public ChronoFormatter<T> with(ChronoHistory chronoHistory) {
        if (chronoHistory == null) {
            throw new NullPointerException("Missing calendar history.");
        }
        return new ChronoFormatter<>(this, this.globalAttributes.withInternal(HistoricAttribute.CALENDAR_HISTORY, chronoHistory).withAttributes(new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).setCalendarVariant(chronoHistory.getVariant()).build()), chronoHistory);
    }

    public ChronoFormatter<T> with(Timezone timezone) {
        if (timezone == null) {
            throw new NullPointerException("Missing timezone id.");
        }
        return new ChronoFormatter<>(this, this.globalAttributes.withAttributes(new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).setTimezone(timezone.getID()).build()).withInternal(Attributes.TRANSITION_STRATEGY, timezone.getStrategy()));
    }

    @Override // net.time4j.format.TemporalFormatter
    public ChronoFormatter<T> withTimezone(TZID tzid) {
        return with(Timezone.of(tzid));
    }

    @Override // net.time4j.format.TemporalFormatter
    public ChronoFormatter<T> withTimezone(String str) {
        return with(Timezone.of(str));
    }

    public ChronoFormatter<T> withStdTimezone() {
        return with(Timezone.ofSystem());
    }

    public ChronoFormatter<T> withCalendarVariant(String str) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).setCalendarVariant(str).build());
    }

    public ChronoFormatter<T> withCalendarVariant(VariantSource variantSource) {
        return withCalendarVariant(variantSource.getVariant());
    }

    public ChronoFormatter<T> with(StartOfDay startOfDay) {
        if (startOfDay == null) {
            throw new NullPointerException("Missing start of day.");
        }
        return new ChronoFormatter<>(this, this.globalAttributes.withInternal(Attributes.START_OF_DAY, startOfDay));
    }

    public <V> ChronoFormatter<T> withDefault(ChronoElement<V> chronoElement, V v) {
        if (chronoElement == null) {
            throw new NullPointerException("Missing element.");
        }
        HashMap map = new HashMap();
        map.put(chronoElement, v);
        return new ChronoFormatter<>(this, map);
    }

    public ChronoFormatter<T> with(AttributeKey<Boolean> attributeKey, boolean z) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set(attributeKey, z).build());
    }

    public ChronoFormatter<T> with(AttributeKey<Integer> attributeKey, int i) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set(attributeKey, i).build());
    }

    public ChronoFormatter<T> with(AttributeKey<Character> attributeKey, char c) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set(attributeKey, c).build());
    }

    public <A extends Enum<A>> ChronoFormatter<T> with(AttributeKey<A> attributeKey, A a2) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).set((AttributeKey<AttributeKey<A>>) attributeKey, (AttributeKey<A>) a2).build());
    }

    public ChronoFormatter<T> with(Attributes attributes) {
        return new ChronoFormatter<>(this, new Attributes.Builder().setAll(this.globalAttributes.getAttributes()).setAll(attributes).build());
    }

    ChronoFormatter<T> with(Map<ChronoElement<?>, Object> map, AttributeSet attributeSet) {
        AttributeSet attributeSetMerge = AttributeSet.merge(attributeSet, this.globalAttributes);
        return new ChronoFormatter<>(new ChronoFormatter(this, map), attributeSetMerge, (ChronoHistory) attributeSetMerge.get(HistoricAttribute.CALENDAR_HISTORY, null));
    }

    public Format toFormat() {
        return new TraditionalFormat(this);
    }

    public static ChronoFormatter<PlainDate> ofDatePattern(String str, PatternType patternType, Locale locale) {
        Builder builder = new Builder(PlainDate.axis(), locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ChronoFormatter<PlainTime> ofTimePattern(String str, PatternType patternType, Locale locale) {
        Builder builder = new Builder(PlainTime.axis(), locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ChronoFormatter<PlainTimestamp> ofTimestampPattern(String str, PatternType patternType, Locale locale) {
        Builder builder = new Builder(PlainTimestamp.axis(), locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ChronoParser<Moment> ofMomentPattern(String str, PatternType patternType, Locale locale) {
        Builder builder = new Builder(Moment.axis(), locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ChronoFormatter<Moment> ofMomentPattern(String str, PatternType patternType, Locale locale, TZID tzid) {
        Builder builder = new Builder(Moment.axis(), locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build().withTimezone(tzid);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> ChronoFormatter<T> ofPattern(String str, PatternType patternType, Locale locale, Chronology<T> chronology) {
        Builder builder = new Builder(chronology, locale);
        addPattern(builder, str, patternType);
        try {
            return builder.build();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ChronoFormatter<PlainDate> ofDateStyle(DisplayMode displayMode, Locale locale) {
        Builder builder = new Builder(PlainDate.axis(), locale);
        builder.addProcessor(new StyleProcessor(displayMode, displayMode));
        return builder.build();
    }

    public static ChronoFormatter<PlainTime> ofTimeStyle(DisplayMode displayMode, Locale locale) {
        Builder builder = new Builder(PlainTime.axis(), locale);
        builder.addProcessor(new StyleProcessor(displayMode, displayMode));
        return builder.build();
    }

    public static ChronoFormatter<PlainTimestamp> ofTimestampStyle(DisplayMode displayMode, DisplayMode displayMode2, Locale locale) {
        Builder builder = new Builder(PlainTimestamp.axis(), locale);
        builder.addProcessor(new StyleProcessor(displayMode, displayMode2));
        return builder.build();
    }

    public static ChronoFormatter<Moment> ofMomentStyle(DisplayMode displayMode, DisplayMode displayMode2, Locale locale, TZID tzid) {
        Builder builder = new Builder(Moment.axis(), locale);
        builder.addProcessor(new StyleProcessor(displayMode, displayMode2));
        return builder.build().withTimezone(tzid);
    }

    public static <T extends LocalizedPatternSupport> ChronoFormatter<T> ofStyle(DisplayStyle displayStyle, Locale locale, Chronology<T> chronology) {
        if (LocalizedPatternSupport.class.isAssignableFrom(chronology.getChronoType())) {
            Builder builder = new Builder(chronology, locale);
            builder.addProcessor(new StyleProcessor(displayStyle, displayStyle));
            return builder.build();
        }
        if (chronology.equals(Moment.axis())) {
            throw new UnsupportedOperationException("Timezone required, use 'ofMomentStyle()' instead.");
        }
        throw new UnsupportedOperationException("Localized format patterns not available: " + chronology);
    }

    public static <T extends ChronoEntity<T>> Builder<T> setUp(Class<T> cls, Locale locale) throws ClassNotFoundException {
        if (cls == null) {
            throw new NullPointerException("Missing chronological type.");
        }
        Chronology chronologyLookup = Chronology.lookup(cls);
        if (chronologyLookup == null) {
            throw new IllegalArgumentException("Not formattable: " + cls);
        }
        return new Builder<>(chronologyLookup, locale);
    }

    public static <T> Builder<T> setUp(Chronology<T> chronology, Locale locale) {
        return new Builder<>(chronology, locale);
    }

    public static <C extends CalendarVariant<C>> Builder<Moment> setUpWithOverride(Locale locale, CalendarFamily<C> calendarFamily) {
        if (calendarFamily == null) {
            throw new NullPointerException("Missing override calendar.");
        }
        return new Builder<>(Moment.axis(), locale, calendarFamily);
    }

    public static <C extends Calendrical<?, C>> Builder<Moment> setUpWithOverride(Locale locale, Chronology<C> chronology) {
        if (chronology == null) {
            throw new NullPointerException("Missing override calendar.");
        }
        return new Builder<>(Moment.axis(), locale, chronology);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoFormatter)) {
            return false;
        }
        ChronoFormatter chronoFormatter = (ChronoFormatter) obj;
        return this.chronology.equals(chronoFormatter.chronology) && isEqual(this.overrideHandler, chronoFormatter.overrideHandler) && this.globalAttributes.equals(chronoFormatter.globalAttributes) && this.defaults.equals(chronoFormatter.defaults) && this.steps.equals(chronoFormatter.steps);
    }

    public int hashCode() {
        return (this.chronology.hashCode() * 7) + (this.globalAttributes.hashCode() * 31) + (this.steps.hashCode() * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("net.time4j.format.ChronoFormatter[chronology=");
        sb.append(this.chronology.getChronoType().getName());
        if (this.overrideHandler != null) {
            sb.append(", override=");
            sb.append(this.overrideHandler);
        }
        sb.append(", default-attributes=");
        sb.append(this.globalAttributes);
        sb.append(", default-values=");
        sb.append(this.defaults);
        sb.append(", processors=");
        boolean z = true;
        for (FormatStep formatStep : this.steps) {
            if (z) {
                sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
                z = false;
            } else {
                sb.append('|');
            }
            sb.append(formatStep);
        }
        sb.append("}]");
        return sb.toString();
    }

    private boolean getSingleStepMode() {
        boolean zIsSingleStepOptimizationPossible = isSingleStepOptimizationPossible();
        if (!zIsSingleStepOptimizationPossible) {
            return zIsSingleStepOptimizationPossible;
        }
        FormatProcessor<?> processor = this.steps.get(0).getProcessor();
        if (processor instanceof CustomizedProcessor) {
            return ((CustomizedProcessor) CustomizedProcessor.class.cast(processor)).isSingleStepMode();
        }
        if (processor instanceof StyleProcessor) {
            return zIsSingleStepOptimizationPossible;
        }
        return false;
    }

    private String getPattern0(String str) {
        return (String) this.globalAttributes.get(Attributes.FORMAT_PATTERN, str);
    }

    private String format0(ChronoDisplay chronoDisplay) {
        StringBuilder sb = new StringBuilder(this.steps.size() * 8);
        try {
            print(chronoDisplay, (Appendable) sb, (AttributeQuery) this.globalAttributes, false);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static ChronoFormatter<Moment> rfc1123() throws ClassNotFoundException {
        Builder up = setUp(Moment.class, Locale.ENGLISH);
        rfc1123DateTime(up);
        up.addTimezoneOffset(DisplayMode.MEDIUM, false, Arrays.asList(TimeZones.GMT_ID, "UT", "Z"));
        up.or();
        rfc1123DateTime(up);
        final HashMap map = new HashMap();
        map.put("EST", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 5));
        map.put("EDT", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 4));
        map.put("CST", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 6));
        map.put("CDT", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 5));
        map.put("MST", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 7));
        map.put("MDT", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 6));
        map.put("PST", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 8));
        map.put("PDT", ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 7));
        up.addProcessor(new CustomizedProcessor(TimezoneElement.TIMEZONE_OFFSET, new ChronoPrinter<TZID>() { // from class: net.time4j.format.expert.ChronoFormatter.1
            @Override // net.time4j.format.expert.ChronoPrinter
            public <R> R print(TZID tzid, Appendable appendable, AttributeQuery attributeQuery, ChronoFunction<ChronoDisplay, R> chronoFunction) throws IOException {
                return null;
            }
        }, new ChronoParser<TZID>() { // from class: net.time4j.format.expert.ChronoFormatter.2
            @Override // net.time4j.format.expert.ChronoParser
            public TZID parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery) {
                int position = parseLog.getPosition();
                int i = position + 3;
                if (i > charSequence.length()) {
                    return null;
                }
                TZID tzid = (TZID) map.get(charSequence.subSequence(position, i).toString());
                if (tzid != null) {
                    parseLog.setPosition(i);
                    return tzid;
                }
                parseLog.setError(position, "No time zone information found.");
                return null;
            }
        }));
        return up.build().withTimezone((TZID) ZonalOffset.UTC);
    }

    private static void rfc1123DateTime(Builder<Moment> builder) {
        builder.startOptionalSection().startSection((AttributeKey<AttributeKey<TextWidth>>) Attributes.TEXT_WIDTH, (AttributeKey<TextWidth>) TextWidth.ABBREVIATED).addText(PlainDate.DAY_OF_WEEK).endSection().addLiteral(", ").endSection().addInteger(PlainDate.DAY_OF_MONTH, 1, 2).addLiteral(' ').startSection((AttributeKey<AttributeKey<TextWidth>>) Attributes.TEXT_WIDTH, (AttributeKey<TextWidth>) TextWidth.ABBREVIATED).addText(PlainDate.MONTH_OF_YEAR).endSection().addLiteral(' ').addFixedInteger(PlainDate.YEAR, 4).addLiteral(' ').addFixedInteger(PlainTime.DIGITAL_HOUR_OF_DAY, 2).addLiteral(AbstractJsonLexerKt.COLON).addFixedInteger(PlainTime.MINUTE_OF_HOUR, 2).startOptionalSection().addLiteral(AbstractJsonLexerKt.COLON).addFixedInteger(PlainTime.SECOND_OF_MINUTE, 2).endSection().addLiteral(' ');
    }

    private ChronoDisplay display(T t, AttributeQuery attributeQuery) {
        GeneralTimestamp generalTimestamp;
        OverrideHandler<?> overrideHandler = this.overrideHandler;
        if (overrideHandler == null) {
            return this.chronology.preformat(t, attributeQuery);
        }
        try {
            Class<?> chronoType = overrideHandler.getCalendarOverride().getChronoType();
            StartOfDay startOfDay = (StartOfDay) attributeQuery.get(Attributes.START_OF_DAY, this.overrideHandler.getDefaultStartOfDay());
            Moment moment = (Moment) Moment.class.cast(t);
            TZID tzid = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID);
            String str = "";
            if (CalendarVariant.class.isAssignableFrom(chronoType)) {
                CalendarFamily calendarFamily = (CalendarFamily) cast(this.overrideHandler.getCalendarOverride());
                str = (String) attributeQuery.get(Attributes.CALENDAR_VARIANT);
                generalTimestamp = moment.toGeneralTimestamp(calendarFamily, str, tzid, startOfDay);
            } else if (Calendrical.class.isAssignableFrom(chronoType)) {
                generalTimestamp = moment.toGeneralTimestamp(this.overrideHandler.getCalendarOverride(), tzid, startOfDay);
            } else {
                throw new IllegalStateException("Unexpected calendar override: " + chronoType);
            }
            return new ZonalDisplay(generalTimestamp, str, tzid);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Not formattable: " + t, e);
        } catch (NoSuchElementException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }

    private static <C> C parse(ChronoFormatter<?> chronoFormatter, Chronology<C> chronology, int i, CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, Leniency leniency, boolean z) {
        Chronology<?> chronology2;
        Object obj;
        C cCreateFrom;
        Chronology<?> chronologyPreparser = chronology.preparser();
        if (chronologyPreparser == null || chronology == (chronology2 = ((ChronoFormatter) chronoFormatter).deepestParser)) {
            return (C) parse(chronoFormatter, chronology, chronology.getExtensions(), charSequence, parseLog, attributeQuery, leniency, i > 0, z);
        }
        if (chronologyPreparser == chronology2) {
            obj = parse(chronoFormatter, chronologyPreparser, chronologyPreparser.getExtensions(), charSequence, parseLog, attributeQuery, leniency, true, z);
        } else {
            obj = parse(chronoFormatter, chronologyPreparser, i + 1, charSequence, parseLog, attributeQuery, leniency, z);
        }
        if (parseLog.isError()) {
            return null;
        }
        if (obj == null) {
            ChronoEntity<?> rawValues = parseLog.getRawValues();
            parseLog.setError(charSequence.length(), getReason(rawValues) + getDescription(rawValues));
            return null;
        }
        ChronoEntity<?> rawValues0 = parseLog.getRawValues0();
        try {
            if (chronologyPreparser instanceof TimeAxis) {
                updateSelf(rawValues0, ((TimeAxis) TimeAxis.class.cast(chronologyPreparser)).element(), obj);
                cCreateFrom = chronology.createFrom(rawValues0, attributeQuery, leniency.isLax(), false);
            } else if (chronology instanceof BridgeChronology) {
                cCreateFrom = chronology.createFrom((ChronoEntity) ChronoEntity.class.cast(obj), Attributes.empty(), false, false);
            } else {
                try {
                    throw new IllegalStateException("Unsupported chronology or preparser: " + chronology);
                } catch (RuntimeException e) {
                    e = e;
                    parseLog.setError(charSequence.length(), e.getMessage() + getDescription(rawValues0));
                    return null;
                }
            }
            if (cCreateFrom != null) {
                return leniency.isStrict() ? (C) checkConsistency(rawValues0, cCreateFrom, charSequence, parseLog) : cCreateFrom;
            }
            if (!parseLog.isError()) {
                parseLog.setError(charSequence.length(), getReason(rawValues0) + getDescription(rawValues0));
            }
            return null;
        } catch (RuntimeException e2) {
            e = e2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void updateSelf(ChronoEntity<?> chronoEntity, ChronoElement<T> chronoElement, Object obj) {
        chronoEntity.with((ChronoElement<ChronoElement<T>>) chronoElement, (ChronoElement<T>) chronoElement.getType().cast(obj));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> T parse(net.time4j.format.expert.ChronoFormatter<?> r15, net.time4j.engine.ChronoMerger<T> r16, java.util.List<net.time4j.engine.ChronoExtension> r17, java.lang.CharSequence r18, net.time4j.format.expert.ParseLog r19, net.time4j.engine.AttributeQuery r20, net.time4j.format.Leniency r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.ChronoFormatter.parse(net.time4j.format.expert.ChronoFormatter, net.time4j.engine.ChronoMerger, java.util.List, java.lang.CharSequence, net.time4j.format.expert.ParseLog, net.time4j.engine.AttributeQuery, net.time4j.format.Leniency, boolean, boolean):java.lang.Object");
    }

    private static <V> void setValue(ChronoEntity<?> chronoEntity, ChronoElement<V> chronoElement, Object obj) {
        chronoEntity.with((ChronoElement<ChronoElement<V>>) chronoElement, (ChronoElement<V>) chronoElement.getType().cast(obj));
    }

    private static String getReason(ChronoEntity<?> chronoEntity) {
        if (!chronoEntity.contains(ValidationElement.ERROR_MESSAGE)) {
            return "Insufficient data:";
        }
        String str = "Validation failed => " + ((String) chronoEntity.get(ValidationElement.ERROR_MESSAGE));
        chronoEntity.with(ValidationElement.ERROR_MESSAGE, (ValidationElement) null);
        return str;
    }

    private static boolean isEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private static <T> void addPattern(Builder<T> builder, String str, PatternType patternType) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\'') {
                int i2 = i + 1;
                boolean z = str.charAt(i2) == 'Z';
                while (i2 < length) {
                    if (str.charAt(i2) == '\'') {
                        int i3 = i2 + 1;
                        if (i3 >= length || str.charAt(i3) != '\'') {
                            if (z && i2 == i + 2 && Builder.hasUnixChronology(((Builder) builder).chronology)) {
                                throw new IllegalArgumentException("Z-literal (=UTC+00) should not be escaped: " + str);
                            }
                            i = i2;
                        } else {
                            i2 = i3;
                        }
                    }
                    i2++;
                }
                i = i2;
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        String string = sb.toString();
        int i4 = AnonymousClass3.$SwitchMap$net$time4j$format$expert$PatternType[patternType.ordinal()];
        if (i4 == 1 || i4 == 2 || i4 == 3 || i4 == 4) {
            if ((string.contains("h") || string.contains("K")) && !string.contains("a") && !string.contains("b") && !string.contains("B")) {
                throw new IllegalArgumentException("12-hour-clock requires am/pm-marker or dayperiod: " + str);
            }
            if (string.contains("Y") && ((string.contains("M") || string.contains("L")) && !string.contains(Constants.INAPP_WINDOW))) {
                throw new IllegalArgumentException("Y as week-based-year requires a week-date-format: " + str);
            }
            if (string.contains("D") && ((string.contains("M") || string.contains("L")) && !string.contains("d"))) {
                throw new IllegalArgumentException("D is the day of year but not the day of month: " + str);
            }
        }
        builder.addPattern(str, patternType);
    }

    /* renamed from: net.time4j.format.expert.ChronoFormatter$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$format$expert$PatternType;

        static {
            int[] iArr = new int[PatternType.values().length];
            $SwitchMap$net$time4j$format$expert$PatternType = iArr;
            try {
                iArr[PatternType.CLDR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$format$expert$PatternType[PatternType.CLDR_24.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$format$expert$PatternType[PatternType.CLDR_DATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$format$expert$PatternType[PatternType.SIMPLE_DATE_FORMAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static <T> T checkConsistency(ChronoEntity<?> chronoEntity, T t, CharSequence charSequence, ParseLog parseLog) {
        Object objValueOf;
        boolean zEquals;
        if (t instanceof UnixTime) {
            UnixTime unixTime = (UnixTime) UnixTime.class.cast(t);
            if (chronoEntity.contains(TimezoneElement.TIMEZONE_ID) && chronoEntity.contains(TimezoneElement.TIMEZONE_OFFSET)) {
                TZID tzid = (TZID) chronoEntity.get(TimezoneElement.TIMEZONE_ID);
                TZID tzid2 = (TZID) chronoEntity.get(TimezoneElement.TIMEZONE_OFFSET);
                if (!Timezone.of(tzid).getOffset(unixTime).equals(tzid2)) {
                    parseLog.setError(charSequence.length(), "Ambivalent offset information: " + tzid + " versus " + tzid2);
                    return null;
                }
            }
            if (!chronoEntity.contains(FlagElement.DAYLIGHT_SAVING)) {
                return t;
            }
            try {
                boolean zIsDaylightSaving = Timezone.of(chronoEntity.getTimezone()).isDaylightSaving(unixTime);
                if (zIsDaylightSaving == ((Boolean) chronoEntity.get(FlagElement.DAYLIGHT_SAVING)).booleanValue()) {
                    return t;
                }
                StringBuilder sb = new StringBuilder(256);
                sb.append("Conflict found: Parsed entity is ");
                if (!zIsDaylightSaving) {
                    sb.append("not ");
                }
                sb.append("daylight-saving, but timezone name has not the appropriate form in {");
                sb.append(charSequence.toString());
                sb.append("}.");
                parseLog.setError(charSequence.length(), sb.toString());
                return null;
            } catch (IllegalArgumentException e) {
                StringBuilder sb2 = new StringBuilder(256);
                sb2.append("Unable to check timezone name: ");
                sb2.append(e.getMessage());
                parseLog.setError(charSequence.length(), sb2.toString());
                return null;
            }
        }
        if (!(t instanceof ChronoDisplay)) {
            return t;
        }
        ChronoDisplay chronoDisplayMidnightAtEndOfDay = (ChronoDisplay) t;
        TimePoint timePointMinus = ((t instanceof PlainTimestamp) && ((PlainTimestamp) PlainTimestamp.class.cast(t)).getHour() == 0 && (chronoEntity.getInt(PlainTime.HOUR_FROM_0_TO_24) == 24 || (chronoEntity.contains(PlainTime.COMPONENT) && ((PlainTime) chronoEntity.get(PlainTime.COMPONENT)).getHour() == 24))) ? ((PlainTimestamp) PlainTimestamp.class.cast(t)).toDate().minus(1L, CalendarUnit.DAYS) : null;
        for (ChronoElement<?> chronoElement : chronoEntity.getRegisteredElements()) {
            if (chronoElement != PlainTime.SECOND_OF_MINUTE || chronoEntity.getInt(PlainTime.SECOND_OF_MINUTE) != 60) {
                if (timePointMinus != null) {
                    if (chronoElement.isDateElement()) {
                        chronoDisplayMidnightAtEndOfDay = timePointMinus;
                    } else if (chronoElement.isTimeElement()) {
                        chronoDisplayMidnightAtEndOfDay = PlainTime.midnightAtEndOfDay();
                    }
                }
                if (chronoDisplayMidnightAtEndOfDay.contains(chronoElement)) {
                    if (chronoElement.getType() == Integer.class) {
                        ChronoElement<Integer> chronoElement2 = (ChronoElement) cast(chronoElement);
                        int i = chronoEntity.getInt(chronoElement2);
                        if (chronoDisplayMidnightAtEndOfDay.getInt(chronoElement2) != i) {
                            objValueOf = Integer.valueOf(i);
                            zEquals = false;
                        } else {
                            zEquals = true;
                            objValueOf = null;
                        }
                    } else {
                        objValueOf = chronoEntity.get(chronoElement);
                        zEquals = chronoDisplayMidnightAtEndOfDay.get(chronoElement).equals(objValueOf);
                    }
                    if (!zEquals) {
                        StringBuilder sb3 = new StringBuilder(256);
                        sb3.append("Conflict found: Text {");
                        sb3.append(charSequence.toString());
                        sb3.append("} with element ");
                        sb3.append(chronoElement.name());
                        sb3.append(" {");
                        sb3.append(objValueOf);
                        sb3.append("}, but parsed entity has element value {");
                        sb3.append(chronoDisplayMidnightAtEndOfDay.get(chronoElement));
                        sb3.append("}.");
                        parseLog.setError(charSequence.length(), sb3.toString());
                        return null;
                    }
                } else {
                    continue;
                }
            }
        }
        return t;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private net.time4j.engine.ChronoEntity<?> parseElements(java.lang.CharSequence r19, net.time4j.format.expert.ParseLog r20, net.time4j.engine.AttributeQuery r21, boolean r22, int r23) {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.ChronoFormatter.parseElements(java.lang.CharSequence, net.time4j.format.expert.ParseLog, net.time4j.engine.AttributeQuery, boolean, int):net.time4j.engine.ChronoEntity");
    }

    private static String sub(int i, CharSequence charSequence) {
        int length = charSequence.length();
        if (length - i <= 10) {
            return charSequence.subSequence(i, length).toString();
        }
        return charSequence.subSequence(i, i + 10).toString() + "...";
    }

    private static String getDescription(ChronoEntity<?> chronoEntity) {
        Set<ChronoElement<?>> registeredElements = chronoEntity.getRegisteredElements();
        StringBuilder sb = new StringBuilder(registeredElements.size() * 16);
        sb.append(" [parsed={");
        boolean z = true;
        for (ChronoElement<?> chronoElement : registeredElements) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(chronoElement.name());
            sb.append('=');
            sb.append(chronoEntity.get(chronoElement));
        }
        sb.append("}]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Chronology<?> checkElement(Chronology<?> chronology, Chronology<?> chronology2, ChronoElement<?> chronoElement) {
        if (chronology.isSupported(chronoElement)) {
            return chronology;
        }
        if (chronology2 == null) {
            do {
                chronology = chronology.preparser();
                if (chronology != null) {
                }
            } while (!chronology.isSupported(chronoElement));
            return chronology;
        }
        if (chronoElement.isDateElement() && chronology2.isSupported(chronoElement)) {
            return chronology2;
        }
        if (chronoElement.isTimeElement() && PlainTime.axis().isSupported(chronoElement)) {
            return PlainTime.axis();
        }
        throw new IllegalArgumentException("Unsupported element: " + chronoElement.name());
    }

    private static boolean needsExtension(Chronology<?> chronology, Chronology<?> chronology2, ChronoElement<?> chronoElement) {
        Iterator<ChronoExtension> it = chronology.getExtensions().iterator();
        while (it.hasNext()) {
            if (it.next().canResolve(chronoElement)) {
                return true;
            }
        }
        if (chronology2 != null) {
            if (chronoElement.isDateElement()) {
                while (chronology2 instanceof BridgeChronology) {
                    chronology2 = chronology2.preparser();
                }
                Iterator<ChronoExtension> it2 = chronology2.getExtensions().iterator();
                while (it2.hasNext()) {
                    if (it2.next().canResolve(chronoElement)) {
                        return true;
                    }
                }
                return false;
            }
            if (!chronoElement.isTimeElement() || !PlainTime.axis().isSupported(chronoElement)) {
                return false;
            }
            Iterator<ChronoExtension> it3 = PlainTime.axis().getExtensions().iterator();
            while (it3.hasNext()) {
                if (it3.next().canResolve(chronoElement)) {
                    return true;
                }
            }
            return false;
        }
        while (true) {
            chronology = chronology.preparser();
            if (chronology == null) {
                return false;
            }
            Iterator<ChronoExtension> it4 = chronology.getExtensions().iterator();
            while (it4.hasNext()) {
                if (it4.next().canResolve(chronoElement)) {
                    return true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDepth(Chronology<?> chronology, Chronology<?> chronology2, Chronology<?> chronology3) {
        if (chronology3 != null) {
            return -1;
        }
        int i = 0;
        if (chronology.equals(chronology2)) {
            return 0;
        }
        do {
            chronology2 = chronology2.preparser();
            if (chronology2 == null) {
                return Integer.MAX_VALUE;
            }
            i++;
        } while (!chronology.equals(chronology2));
        return i;
    }

    private List<FormatStep> freeze(List<FormatStep> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<FormatStep> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().quickPath(this));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private boolean hasNoPreparser() {
        return this.chronology.preparser() == null && this.overrideHandler == null;
    }

    public static final class Builder<T> {
        private static final AttributeKey<DayPeriod> CUSTOM_DAY_PERIOD = Attributes.createKey("CUSTOM_DAY_PERIOD", DayPeriod.class);
        private final Chronology<T> chronology;
        private DayPeriod dayPeriod;
        private Chronology<?> deepestParser;
        private Map<ChronoElement<?>, Object> defaultMap;
        private int depthOfParser;
        private int leftPadWidth;
        private final Locale locale;
        private final Chronology<?> override;
        private String pattern;
        private int reservedIndex;
        private int sectionID;
        private LinkedList<AttributeSet> stack;
        private List<FormatStep> steps;

        private static boolean isSymbol(char c) {
            return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
        }

        private void resetPadding() {
            this.leftPadWidth = 0;
        }

        public Chronology<?> getChronology() {
            Chronology<?> chronology = this.override;
            return chronology == null ? this.chronology : chronology;
        }

        private Builder(Chronology<T> chronology, Locale locale) {
            this(chronology, locale, (Chronology<?>) null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Builder(Chronology<T> chronology, Locale locale, Chronology<?> chronology2) {
            if (chronology == 0) {
                throw new NullPointerException("Missing chronology.");
            }
            if (locale == null) {
                throw new NullPointerException("Missing locale.");
            }
            this.chronology = chronology;
            this.override = chronology2;
            this.locale = locale;
            this.steps = new ArrayList();
            this.stack = new LinkedList<>();
            this.sectionID = 0;
            this.reservedIndex = -1;
            this.leftPadWidth = 0;
            this.pattern = null;
            this.dayPeriod = null;
            this.defaultMap = new HashMap();
            this.deepestParser = chronology;
            this.depthOfParser = 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<T> addInteger(ChronoElement<Integer> chronoElement, int i, int i2) {
            return addNumber(chronoElement, false, i, i2, SignPolicy.SHOW_NEVER);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<T> addInteger(ChronoElement<Integer> chronoElement, int i, int i2, SignPolicy signPolicy) {
            return addNumber(chronoElement, false, i, i2, signPolicy);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<T> addLongNumber(ChronoElement<Long> chronoElement, int i, int i2, SignPolicy signPolicy) {
            return addNumber(chronoElement, false, i, i2, signPolicy);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<T> addFixedInteger(ChronoElement<Integer> chronoElement, int i) {
            return addNumber(chronoElement, true, i, i, SignPolicy.SHOW_NEVER);
        }

        public <V extends Enum<V>> Builder<T> addNumerical(ChronoElement<V> chronoElement, int i, int i2) {
            return addNumber(chronoElement, false, i, i2, SignPolicy.SHOW_NEVER);
        }

        public <V extends Enum<V>> Builder<T> addFixedNumerical(ChronoElement<V> chronoElement, int i) {
            return addNumber(chronoElement, true, i, i, SignPolicy.SHOW_NEVER);
        }

        public Builder<T> addFraction(ChronoElement<Integer> chronoElement, int i, int i2, boolean z) {
            checkElement(chronoElement);
            boolean z2 = !z && i == i2;
            ensureOnlyOneFractional(z2, z);
            FractionProcessor fractionProcessor = new FractionProcessor(chronoElement, i, i2, z);
            int i3 = this.reservedIndex;
            if (i3 != -1 && z2) {
                FormatStep formatStep = this.steps.get(i3);
                addProcessor(fractionProcessor);
                List<FormatStep> list = this.steps;
                if (formatStep.getSection() == list.get(list.size() - 1).getSection()) {
                    this.reservedIndex = i3;
                    this.steps.set(i3, formatStep.reserve(i));
                }
            } else {
                addProcessor(fractionProcessor);
            }
            return this;
        }

        public Builder<T> addFixedDecimal(ChronoElement<BigDecimal> chronoElement) {
            return addFixedDecimal(chronoElement, 11, 9);
        }

        public Builder<T> addFixedDecimal(ChronoElement<BigDecimal> chronoElement, int i, int i2) {
            checkElement(chronoElement);
            ensureDecimalDigitsOnlyOnce();
            DecimalProcessor decimalProcessor = new DecimalProcessor(chronoElement, i, i2);
            int i3 = this.reservedIndex;
            if (i3 != -1) {
                FormatStep formatStep = this.steps.get(i3);
                addProcessor(decimalProcessor);
                if (formatStep.getSection() == this.steps.get(r0.size() - 1).getSection()) {
                    this.reservedIndex = i3;
                    this.steps.set(i3, formatStep.reserve(i - i2));
                }
            } else {
                addProcessor(decimalProcessor);
            }
            return this;
        }

        public Builder<T> addEnglishOrdinal(ChronoElement<Integer> chronoElement) {
            return addOrdinalProcessor(chronoElement, null);
        }

        public Builder<T> addOrdinal(ChronoElement<Integer> chronoElement, Map<PluralCategory, String> map) {
            if (map == null) {
                throw new NullPointerException("Missing ordinal indicators.");
            }
            return addOrdinalProcessor(chronoElement, map);
        }

        public Builder<T> addLiteral(char c) {
            return addLiteral(String.valueOf(c));
        }

        public Builder<T> addLiteral(char c, char c2) {
            addProcessor(new LiteralProcessor(c, c2));
            return this;
        }

        public Builder<T> addLiteral(String str) {
            int i;
            FormatStep formatStep;
            LiteralProcessor literalProcessor = new LiteralProcessor(str);
            int prefixedDigitArea = literalProcessor.getPrefixedDigitArea();
            if (prefixedDigitArea > 0) {
                if (this.steps.isEmpty()) {
                    formatStep = null;
                } else {
                    formatStep = this.steps.get(r1.size() - 1);
                }
                if (formatStep != null && formatStep.isDecimal() && !formatStep.isNewOrBlockStarted()) {
                    throw new IllegalStateException("Numerical literal can't be inserted after an element with decimal digits.");
                }
            }
            if (prefixedDigitArea == 0 || (i = this.reservedIndex) == -1) {
                addProcessor(literalProcessor);
            } else {
                FormatStep formatStep2 = this.steps.get(i);
                addProcessor(literalProcessor);
                if (formatStep2.getSection() == this.steps.get(r3.size() - 1).getSection()) {
                    this.reservedIndex = i;
                    this.steps.set(i, formatStep2.reserve(prefixedDigitArea));
                }
            }
            return this;
        }

        public Builder<T> addLiteral(AttributeKey<Character> attributeKey) {
            addProcessor(new LiteralProcessor(attributeKey));
            return this;
        }

        public Builder<T> addIgnorableWhitespace() {
            addProcessor(IgnorableWhitespaceProcessor.SINGLETON);
            return this;
        }

        public Builder<T> skipUnknown(int i) {
            addProcessor(new SkipProcessor(i));
            return this;
        }

        public Builder<T> skipUnknown(ChronoCondition<Character> chronoCondition, int i) {
            addProcessor(new SkipProcessor(chronoCondition, i));
            return this;
        }

        public Builder<T> addPattern(String str, PatternType patternType) {
            if (patternType == null) {
                throw new NullPointerException("Missing pattern type.");
            }
            Map<ChronoElement<?>, ChronoElement<?>> mapEmptyMap = Collections.emptyMap();
            int length = str.length();
            Locale locale = this.locale;
            StringBuilder sb = new StringBuilder();
            if (!this.stack.isEmpty()) {
                locale = this.stack.getLast().getLocale();
            }
            int i = 0;
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (isSymbol(cCharAt)) {
                    addLiteralChars(sb);
                    int i2 = i + 1;
                    while (i2 < length && str.charAt(i2) == cCharAt) {
                        i2++;
                    }
                    Map<ChronoElement<?>, ChronoElement<?>> mapRegisterSymbol = patternType.registerSymbol(this, locale, cCharAt, i2 - i);
                    if (!mapRegisterSymbol.isEmpty()) {
                        if (mapEmptyMap.isEmpty()) {
                            mapEmptyMap = mapRegisterSymbol;
                        } else {
                            HashMap map = new HashMap(mapEmptyMap);
                            map.putAll(mapRegisterSymbol);
                            mapEmptyMap = map;
                        }
                    }
                    i = i2 - 1;
                } else if (cCharAt == '\'') {
                    addLiteralChars(sb);
                    int i3 = i + 1;
                    int i4 = i3;
                    while (i4 < length) {
                        if (str.charAt(i4) == '\'') {
                            int i5 = i4 + 1;
                            if (i5 >= length || str.charAt(i5) != '\'') {
                                break;
                            }
                            i4 = i5;
                        }
                        i4++;
                    }
                    if (i4 >= length) {
                        throw new IllegalArgumentException("String literal in pattern not closed: " + str);
                    }
                    if (i3 == i4) {
                        addLiteral('\'');
                    } else {
                        addLiteral(str.substring(i3, i4).replace("''", "'"));
                    }
                    i = i4;
                } else if (cCharAt == '[') {
                    addLiteralChars(sb);
                    startOptionalSection();
                } else if (cCharAt == ']') {
                    addLiteralChars(sb);
                    endSection();
                } else if (cCharAt == '|') {
                    try {
                        addLiteralChars(sb);
                        or();
                    } catch (IllegalStateException e) {
                        throw new IllegalArgumentException(e);
                    }
                } else {
                    if (cCharAt == '#' || cCharAt == '{' || cCharAt == '}') {
                        throw new IllegalArgumentException("Pattern contains reserved character: '" + cCharAt + "'");
                    }
                    sb.append(cCharAt);
                }
                i++;
            }
            addLiteralChars(sb);
            if (!mapEmptyMap.isEmpty()) {
                int size = this.steps.size();
                for (int i6 = 0; i6 < size; i6++) {
                    FormatStep formatStep = this.steps.get(i6);
                    ChronoElement<?> element = formatStep.getProcessor().getElement();
                    if (mapEmptyMap.containsKey(element)) {
                        this.steps.set(i6, formatStep.updateElement(mapEmptyMap.get(element)));
                    }
                }
            }
            if (this.pattern != null) {
                str = "";
            }
            this.pattern = str;
            return this;
        }

        public Builder<T> addText(TextElement<?> textElement) {
            checkElement(textElement);
            addProcessor(TextProcessor.create(textElement));
            return this;
        }

        public <V extends Enum<V>> Builder<T> addText(ChronoElement<V> chronoElement) {
            checkElement(chronoElement);
            if (chronoElement instanceof TextElement) {
                addProcessor(TextProcessor.create((TextElement) TextElement.class.cast(chronoElement)));
            } else {
                HashMap map = new HashMap();
                for (V v : chronoElement.getType().getEnumConstants()) {
                    map.put(v, v.toString());
                }
                addProcessor(new LookupProcessor(chronoElement, map));
            }
            return this;
        }

        public <V> Builder<T> addText(ChronoElement<V> chronoElement, Map<V, String> map) {
            checkElement(chronoElement);
            addProcessor(new LookupProcessor(chronoElement, map));
            return this;
        }

        public Builder<T> addDayPeriodFixed() {
            return addText(findDayPeriodElement(true, null));
        }

        public Builder<T> addDayPeriodApproximate() {
            return addText(findDayPeriodElement(false, null));
        }

        public Builder<T> addDayPeriod(Map<PlainTime, String> map) {
            if (this.dayPeriod != null) {
                throw new IllegalStateException("Cannot add custom day period more than once.");
            }
            DayPeriod dayPeriodOf = DayPeriod.of(map);
            TextElement<?> textElementFindDayPeriodElement = findDayPeriodElement(false, dayPeriodOf);
            this.dayPeriod = dayPeriodOf;
            addProcessor(TextProcessor.createProtected(textElementFindDayPeriodElement));
            return this;
        }

        public <V extends ChronoEntity<V>> Builder<T> addCustomized(ChronoElement<V> chronoElement, ChronoFormatter<V> chronoFormatter) {
            return addCustomized(chronoElement, chronoFormatter, chronoFormatter);
        }

        public <V> Builder<T> addCustomized(ChronoElement<V> chronoElement, ChronoPrinter<V> chronoPrinter, ChronoParser<V> chronoParser) {
            checkElement(chronoElement);
            addProcessor(new CustomizedProcessor(chronoElement, chronoPrinter, chronoParser));
            return this;
        }

        public Builder<T> addTwoDigitYear(ChronoElement<Integer> chronoElement) {
            checkElement(chronoElement);
            checkAfterDecimalDigits(chronoElement);
            TwoDigitYearProcessor twoDigitYearProcessor = new TwoDigitYearProcessor(chronoElement);
            int i = this.reservedIndex;
            if (i == -1) {
                addProcessor(twoDigitYearProcessor);
                this.reservedIndex = this.steps.size() - 1;
            } else {
                FormatStep formatStep = this.steps.get(i);
                startSection((AttributeKey<AttributeKey<Leniency>>) Attributes.LENIENCY, (AttributeKey<Leniency>) Leniency.STRICT);
                addProcessor(twoDigitYearProcessor);
                endSection();
                if (formatStep.getSection() == this.steps.get(r0.size() - 1).getSection()) {
                    this.reservedIndex = i;
                    this.steps.set(i, formatStep.reserve(2));
                }
            }
            return this;
        }

        public Builder<T> addTimezoneID() {
            if (hasUnixChronology(this.chronology)) {
                addProcessor(TimezoneIDProcessor.INSTANCE);
                return this;
            }
            throw new IllegalStateException("Only unix timestamps can have a timezone id.");
        }

        public Builder<T> addShortTimezoneName() {
            checkMomentChrono();
            addProcessor(new TimezoneNameProcessor(true));
            return this;
        }

        public Builder<T> addLongTimezoneName() {
            checkMomentChrono();
            addProcessor(new TimezoneNameProcessor(false));
            return this;
        }

        public Builder<T> addShortTimezoneName(Set<TZID> set) {
            checkMomentChrono();
            addProcessor(new TimezoneNameProcessor(true, set));
            return this;
        }

        public Builder<T> addLongTimezoneName(Set<TZID> set) {
            checkMomentChrono();
            addProcessor(new TimezoneNameProcessor(false, set));
            return this;
        }

        public Builder<T> addTimezoneName(NameStyle nameStyle) {
            addProcessor(new TimezoneGenericProcessor(nameStyle));
            return this;
        }

        public Builder<T> addTimezoneName(NameStyle nameStyle, Set<TZID> set) {
            addProcessor(new TimezoneGenericProcessor(nameStyle, set));
            return this;
        }

        public Builder<T> addTimezoneOffset() {
            return addTimezoneOffset(DisplayMode.MEDIUM, true, Collections.singletonList("Z"));
        }

        public Builder<T> addTimezoneOffset(DisplayMode displayMode, boolean z, List<String> list) {
            addProcessor(new TimezoneOffsetProcessor(displayMode, z, list));
            return this;
        }

        public Builder<T> addShortLocalizedOffset() {
            addProcessor(new LocalizedGMTProcessor(true));
            return this;
        }

        public Builder<T> addLongLocalizedOffset() {
            addProcessor(new LocalizedGMTProcessor(false));
            return this;
        }

        public Builder<T> padNext(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Negative pad width: " + i);
            }
            if (i > 0) {
                this.leftPadWidth = i;
            }
            return this;
        }

        public Builder<T> padPrevious(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Negative pad width: " + i);
            }
            if (!this.steps.isEmpty() && i > 0) {
                int size = this.steps.size() - 1;
                FormatStep formatStep = this.steps.get(size);
                if ((!this.stack.isEmpty() ? this.stack.getLast().getSection() : 0) == formatStep.getSection() && !formatStep.isNewOrBlockStarted()) {
                    this.steps.set(size, formatStep.pad(0, i));
                }
            }
            return this;
        }

        public Builder<T> startOptionalSection() {
            return startOptionalSection(null);
        }

        public Builder<T> startOptionalSection(final ChronoCondition<ChronoDisplay> chronoCondition) {
            AttributeSet last;
            final ChronoCondition<ChronoDisplay> condition;
            ChronoCondition<ChronoDisplay> chronoCondition2;
            resetPadding();
            Attributes.Builder builder = new Attributes.Builder();
            if (this.stack.isEmpty()) {
                last = null;
                condition = null;
            } else {
                last = this.stack.getLast();
                builder.setAll(last.getAttributes());
                condition = last.getCondition();
            }
            int level = getLevel(last) + 1;
            int i = this.sectionID + 1;
            this.sectionID = i;
            if (chronoCondition != null) {
                chronoCondition2 = condition == null ? chronoCondition : new ChronoCondition<ChronoDisplay>() { // from class: net.time4j.format.expert.ChronoFormatter.Builder.1
                    @Override // net.time4j.engine.ChronoCondition
                    public boolean test(ChronoDisplay chronoDisplay) {
                        return condition.test(chronoDisplay) && chronoCondition.test(chronoDisplay);
                    }
                };
            } else {
                chronoCondition2 = condition;
            }
            this.stack.addLast(new AttributeSet(builder.build(), this.locale, level, i, chronoCondition2));
            return this;
        }

        public Builder<T> startSection(AttributeKey<Boolean> attributeKey, boolean z) {
            AttributeSet attributeSetWithAttributes;
            checkAttribute(attributeKey);
            resetPadding();
            if (this.stack.isEmpty()) {
                attributeSetWithAttributes = new AttributeSet(new Attributes.Builder().set(attributeKey, z).build(), this.locale);
            } else {
                AttributeSet last = this.stack.getLast();
                Attributes.Builder builder = new Attributes.Builder();
                builder.setAll(last.getAttributes());
                builder.set(attributeKey, z);
                attributeSetWithAttributes = last.withAttributes(builder.build());
            }
            this.stack.addLast(attributeSetWithAttributes);
            return this;
        }

        public Builder<T> startSection(AttributeKey<Integer> attributeKey, int i) {
            AttributeSet attributeSetWithAttributes;
            checkAttribute(attributeKey);
            resetPadding();
            if (this.stack.isEmpty()) {
                attributeSetWithAttributes = new AttributeSet(new Attributes.Builder().set(attributeKey, i).build(), this.locale);
            } else {
                AttributeSet last = this.stack.getLast();
                Attributes.Builder builder = new Attributes.Builder();
                builder.setAll(last.getAttributes());
                builder.set(attributeKey, i);
                attributeSetWithAttributes = last.withAttributes(builder.build());
            }
            this.stack.addLast(attributeSetWithAttributes);
            return this;
        }

        public Builder<T> startSection(AttributeKey<Character> attributeKey, char c) {
            AttributeSet attributeSetWithAttributes;
            checkAttribute(attributeKey);
            resetPadding();
            if (this.stack.isEmpty()) {
                attributeSetWithAttributes = new AttributeSet(new Attributes.Builder().set(attributeKey, c).build(), this.locale);
            } else {
                AttributeSet last = this.stack.getLast();
                Attributes.Builder builder = new Attributes.Builder();
                builder.setAll(last.getAttributes());
                builder.set(attributeKey, c);
                attributeSetWithAttributes = last.withAttributes(builder.build());
            }
            this.stack.addLast(attributeSetWithAttributes);
            return this;
        }

        public <A extends Enum<A>> Builder<T> startSection(AttributeKey<A> attributeKey, A a2) {
            AttributeSet attributeSetWithAttributes;
            checkAttribute(attributeKey);
            resetPadding();
            if (this.stack.isEmpty()) {
                attributeSetWithAttributes = new AttributeSet(new Attributes.Builder().set((AttributeKey<AttributeKey<A>>) attributeKey, (AttributeKey<A>) a2).build(), this.locale);
            } else {
                AttributeSet last = this.stack.getLast();
                Attributes.Builder builder = new Attributes.Builder();
                builder.setAll(last.getAttributes());
                builder.set((AttributeKey<AttributeKey<A>>) attributeKey, (AttributeKey<A>) a2);
                attributeSetWithAttributes = last.withAttributes(builder.build());
            }
            this.stack.addLast(attributeSetWithAttributes);
            return this;
        }

        public Builder<T> endSection() {
            this.stack.removeLast();
            resetPadding();
            return this;
        }

        public Builder<T> or() {
            FormatStep formatStep;
            int size;
            int section;
            int section2 = !this.stack.isEmpty() ? this.stack.getLast().getSection() : 0;
            if (this.steps.isEmpty()) {
                formatStep = null;
                size = -1;
                section = -1;
            } else {
                size = this.steps.size() - 1;
                formatStep = this.steps.get(size);
                section = formatStep.getSection();
            }
            if (section2 == section) {
                this.steps.set(size, formatStep.startNewOrBlock());
                resetPadding();
                this.reservedIndex = -1;
                return this;
            }
            throw new IllegalStateException("Cannot start or-block without any previous step in current section.");
        }

        public <V> Builder<T> setDefault(ChronoElement<V> chronoElement, V v) {
            if (v == null) {
                throw new NullPointerException("Missing default value.");
            }
            checkElement(chronoElement);
            this.defaultMap.put(chronoElement, v);
            return this;
        }

        public ChronoFormatter<T> build() {
            return build(Attributes.empty());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ChronoFormatter<T> build(Attributes attributes) {
            boolean z;
            if (attributes == null) {
                throw new NullPointerException("Missing format attributes.");
            }
            int size = this.steps.size();
            HashMap map = null;
            for (int i = 0; i < size; i++) {
                FormatStep formatStep = this.steps.get(i);
                if (formatStep.isNewOrBlockStarted()) {
                    int section = formatStep.getSection();
                    int i2 = size - 1;
                    while (true) {
                        if (i2 <= i) {
                            z = false;
                            break;
                        }
                        if (this.steps.get(i2).getSection() == section) {
                            if (map == null) {
                                map = new HashMap();
                            }
                            map.put(Integer.valueOf(i), formatStep.markLastOrBlock(i2));
                            z = true;
                        } else {
                            i2--;
                        }
                    }
                    if (!z) {
                        throw new IllegalStateException("Missing format processor after or-operator.");
                    }
                }
            }
            if (map != null) {
                for (Integer num : map.keySet()) {
                    this.steps.set(num.intValue(), map.get(num));
                }
            }
            ChronoFormatter<T> chronoFormatter = new ChronoFormatter<>(this.chronology, this.override, this.locale, this.steps, this.defaultMap, attributes, this.deepestParser);
            String str = this.pattern;
            if (str == null) {
                str = "";
            }
            if (this.dayPeriod == null && str.isEmpty()) {
                return chronoFormatter;
            }
            AttributeSet attributeSetWithInternal = ((ChronoFormatter) chronoFormatter).globalAttributes;
            if (!str.isEmpty()) {
                attributeSetWithInternal = attributeSetWithInternal.withInternal(Attributes.FORMAT_PATTERN, str);
            }
            DayPeriod dayPeriod = this.dayPeriod;
            if (dayPeriod != null) {
                attributeSetWithInternal = attributeSetWithInternal.withInternal(CUSTOM_DAY_PERIOD, dayPeriod);
            }
            return new ChronoFormatter<>(attributeSetWithInternal);
        }

        /* JADX WARN: Multi-variable type inference failed */
        Builder<T> addYear(ChronoElement<Integer> chronoElement, int i, boolean z) {
            FormatStep formatStep;
            if (this.steps.isEmpty()) {
                formatStep = null;
            } else {
                formatStep = this.steps.get(r0.size() - 1);
            }
            if (formatStep == null || formatStep.isNewOrBlockStarted() || !formatStep.isNumerical() || i != 4) {
                return addNumber(chronoElement, false, i, 10, SignPolicy.SHOW_WHEN_NEGATIVE, z);
            }
            return addNumber(chronoElement, true, 4, 4, SignPolicy.SHOW_NEVER, z);
        }

        private <V> Builder<T> addNumber(ChronoElement<V> chronoElement, boolean z, int i, int i2, SignPolicy signPolicy) {
            return addNumber(chronoElement, z, i, i2, signPolicy, false);
        }

        private <V> Builder<T> addNumber(ChronoElement<V> chronoElement, boolean z, int i, int i2, SignPolicy signPolicy, boolean z2) {
            checkElement(chronoElement);
            FormatStep formatStepCheckAfterDecimalDigits = checkAfterDecimalDigits(chronoElement);
            NumberProcessor numberProcessor = new NumberProcessor(chronoElement, z, i, i2, signPolicy, z2);
            if (z) {
                int i3 = this.reservedIndex;
                if (i3 == -1) {
                    addProcessor(numberProcessor);
                } else {
                    FormatStep formatStep = this.steps.get(i3);
                    addProcessor(numberProcessor);
                    if (formatStep.getSection() == this.steps.get(r13.size() - 1).getSection()) {
                        this.reservedIndex = i3;
                        this.steps.set(i3, formatStep.reserve(i));
                    }
                }
            } else {
                if (formatStepCheckAfterDecimalDigits != null && formatStepCheckAfterDecimalDigits.isNumerical() && !formatStepCheckAfterDecimalDigits.isNewOrBlockStarted()) {
                    throw new IllegalStateException("Numerical element with variable width can't be inserted after another numerical element. Consider \"addFixedXXX()\" instead.");
                }
                addProcessor(numberProcessor);
                this.reservedIndex = this.steps.size() - 1;
            }
            return this;
        }

        private Builder<T> addOrdinalProcessor(ChronoElement<Integer> chronoElement, Map<PluralCategory, String> map) {
            checkElement(chronoElement);
            FormatStep formatStepCheckAfterDecimalDigits = checkAfterDecimalDigits(chronoElement);
            OrdinalProcessor ordinalProcessor = new OrdinalProcessor(chronoElement, map);
            if (formatStepCheckAfterDecimalDigits != null && formatStepCheckAfterDecimalDigits.isNumerical() && !formatStepCheckAfterDecimalDigits.isNewOrBlockStarted()) {
                throw new IllegalStateException("Ordinal element with variable width can't be inserted after another numerical element.");
            }
            addProcessor(ordinalProcessor);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProcessor(FormatProcessor<?> formatProcessor) {
            AttributeSet last;
            int level;
            int section;
            this.reservedIndex = -1;
            if (this.stack.isEmpty()) {
                last = null;
                level = 0;
                section = 0;
            } else {
                last = this.stack.getLast();
                level = last.getLevel();
                section = last.getSection();
            }
            FormatStep formatStep = new FormatStep(formatProcessor, level, section, last);
            int i = this.leftPadWidth;
            if (i > 0) {
                formatStep = formatStep.pad(i, 0);
                this.leftPadWidth = 0;
            }
            this.steps.add(formatStep);
        }

        private TextElement<?> findDayPeriodElement(boolean z, DayPeriod dayPeriod) {
            AttributeSet last;
            Attributes attributesBuild = new Attributes.Builder(getChronology()).build();
            AttributeQuery attributeQueryWithInternal = attributesBuild;
            if (dayPeriod != null) {
                if (this.stack.isEmpty()) {
                    last = new AttributeSet(attributesBuild, this.locale);
                } else {
                    last = this.stack.getLast();
                }
                attributeQueryWithInternal = last.withInternal(CUSTOM_DAY_PERIOD, dayPeriod);
            }
            Iterator<ChronoExtension> it = PlainTime.axis().getExtensions().iterator();
            while (it.hasNext()) {
                for (ChronoElement<?> chronoElement : it.next().getElements(this.locale, attributeQueryWithInternal)) {
                    if (z && chronoElement.getSymbol() == 'b' && isDayPeriodSupported(chronoElement)) {
                        return (TextElement) ChronoFormatter.cast(chronoElement);
                    }
                    if (!z && chronoElement.getSymbol() == 'B' && isDayPeriodSupported(chronoElement)) {
                        return (TextElement) ChronoFormatter.cast(chronoElement);
                    }
                }
            }
            throw new IllegalStateException("Day periods are not supported: " + getChronology().getChronoType());
        }

        private boolean isDayPeriodSupported(ChronoElement<?> chronoElement) {
            if (!chronoElement.name().endsWith("_DAY_PERIOD")) {
                return false;
            }
            if (this.override != null || this.chronology.isSupported(chronoElement)) {
                return true;
            }
            Chronology<T> chronology = this.chronology;
            do {
                chronology = (Chronology<T>) chronology.preparser();
                if (chronology == null) {
                    return false;
                }
            } while (!chronology.isSupported(chronoElement));
            return true;
        }

        private static int getLevel(AttributeSet attributeSet) {
            if (attributeSet == null) {
                return 0;
            }
            return attributeSet.getLevel();
        }

        private static void checkAttribute(AttributeKey<?> attributeKey) {
            if (attributeKey.name().charAt(0) == '_') {
                throw new IllegalArgumentException("Internal attribute not allowed: " + attributeKey.name());
            }
        }

        private void checkMomentChrono() {
            if (!hasUnixChronology(this.chronology)) {
                throw new IllegalStateException("Timezone names in specific non-location format can only be reliably combined with instant-like types, for example \"Moment\".");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean hasUnixChronology(Chronology<?> chronology) {
            while (!UnixTime.class.isAssignableFrom(chronology.getChronoType())) {
                chronology = chronology.preparser();
                if (chronology == null) {
                    return false;
                }
            }
            return true;
        }

        private void checkElement(ChronoElement<?> chronoElement) {
            Chronology<?> chronologyCheckElement = ChronoFormatter.checkElement(this.chronology, this.override, chronoElement);
            int depth = ChronoFormatter.getDepth(chronologyCheckElement, this.chronology, this.override);
            if (depth >= this.depthOfParser) {
                this.deepestParser = chronologyCheckElement;
                this.depthOfParser = depth;
            }
        }

        private void ensureDecimalDigitsOnlyOnce() {
            for (int size = this.steps.size() - 1; size >= 0; size--) {
                FormatStep formatStep = this.steps.get(size);
                if (formatStep.isNewOrBlockStarted()) {
                    return;
                }
                if (formatStep.isDecimal()) {
                    throw new IllegalArgumentException("Cannot define more than one element with decimal digits.");
                }
            }
        }

        private void ensureOnlyOneFractional(boolean z, boolean z2) {
            ensureDecimalDigitsOnlyOnce();
            if (!z && !z2 && this.reservedIndex != -1) {
                throw new IllegalArgumentException("Cannot add fractional element with variable width after another numerical element with variable width.");
            }
        }

        private FormatStep checkAfterDecimalDigits(ChronoElement<?> chronoElement) {
            FormatStep formatStep;
            if (this.steps.isEmpty()) {
                formatStep = null;
            } else {
                formatStep = this.steps.get(r0.size() - 1);
            }
            if (formatStep == null) {
                return null;
            }
            if (!formatStep.isDecimal() || formatStep.isNewOrBlockStarted()) {
                return formatStep;
            }
            throw new IllegalStateException(chronoElement.name() + " can't be inserted after an element with decimal digits.");
        }

        private void addLiteralChars(StringBuilder sb) {
            if (sb.length() > 0) {
                addLiteral(sb.toString());
                sb.setLength(0);
            }
        }
    }

    private static class TraditionalFormat<T> extends Format {
        private static final Map<String, DateFormat.Field> FIELD_MAP;
        private final ChronoFormatter<T> formatter;

        static {
            HashMap map = new HashMap();
            map.put("YEAR", DateFormat.Field.YEAR);
            map.put("YEAR_OF_ERA", DateFormat.Field.YEAR);
            map.put("YEAR_OF_WEEKDATE", DateFormat.Field.YEAR);
            map.put("WEEK_OF_YEAR", DateFormat.Field.WEEK_OF_YEAR);
            map.put("WEEK_OF_MONTH", DateFormat.Field.WEEK_OF_MONTH);
            map.put("BOUNDED_WEEK_OF_YEAR", DateFormat.Field.WEEK_OF_YEAR);
            map.put("BOUNDED_WEEK_OF_MONTH", DateFormat.Field.WEEK_OF_MONTH);
            map.put("MONTH_OF_YEAR", DateFormat.Field.MONTH);
            map.put("MONTH_AS_NUMBER", DateFormat.Field.MONTH);
            map.put("HISTORIC_MONTH", DateFormat.Field.MONTH);
            map.put("WEEKDAY_IN_MONTH", DateFormat.Field.DAY_OF_WEEK_IN_MONTH);
            map.put("SECOND_OF_MINUTE", DateFormat.Field.SECOND);
            map.put("MINUTE_OF_HOUR", DateFormat.Field.MINUTE);
            map.put("MILLI_OF_SECOND", DateFormat.Field.MILLISECOND);
            map.put("DIGITAL_HOUR_OF_DAY", DateFormat.Field.HOUR_OF_DAY0);
            map.put("DIGITAL_HOUR_OF_AMPM", DateFormat.Field.HOUR0);
            map.put("CLOCK_HOUR_OF_DAY", DateFormat.Field.HOUR_OF_DAY1);
            map.put("CLOCK_HOUR_OF_AMPM", DateFormat.Field.HOUR1);
            map.put("AM_PM_OF_DAY", DateFormat.Field.AM_PM);
            map.put("DAY_OF_MONTH", DateFormat.Field.DAY_OF_MONTH);
            map.put("HISTORIC_DAY_OF_MONTH", DateFormat.Field.DAY_OF_MONTH);
            map.put("DAY_OF_WEEK", DateFormat.Field.DAY_OF_WEEK);
            map.put("LOCAL_DAY_OF_WEEK", DateFormat.Field.DAY_OF_WEEK);
            map.put("DAY_OF_YEAR", DateFormat.Field.DAY_OF_YEAR);
            map.put("TIMEZONE_ID", DateFormat.Field.TIME_ZONE);
            map.put("ERA", DateFormat.Field.ERA);
            FIELD_MAP = Collections.unmodifiableMap(map);
        }

        TraditionalFormat(ChronoFormatter<T> chronoFormatter) {
            this.formatter = chronoFormatter;
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            fieldPosition.setBeginIndex(0);
            fieldPosition.setEndIndex(0);
            try {
                AttributeSet attributeSet = ((ChronoFormatter) this.formatter).globalAttributes;
                String str = (String) attributeSet.get(Attributes.CALENDAR_TYPE, CalendarText.ISO_CALENDAR_TYPE);
                Set<ElementPosition> setPrint = this.formatter.print(this.formatter.getChronology().getChronoType().cast(obj), stringBuffer, attributeSet);
                if (str.equals(CalendarText.ISO_CALENDAR_TYPE)) {
                    for (ElementPosition elementPosition : setPrint) {
                        DateFormat.Field field = toField(elementPosition.getElement());
                        if (field != null && (field.equals(fieldPosition.getFieldAttribute()) || ((field.getCalendarField() == fieldPosition.getField() && fieldPosition.getField() != -1) || ((field.equals(DateFormat.Field.TIME_ZONE) && fieldPosition.getField() == 17) || ((field.equals(DateFormat.Field.HOUR_OF_DAY1) && fieldPosition.getField() == 4) || (field.equals(DateFormat.Field.HOUR1) && fieldPosition.getField() == 15)))))) {
                            fieldPosition.setBeginIndex(elementPosition.getStartIndex());
                            fieldPosition.setEndIndex(elementPosition.getEndIndex());
                            break;
                        }
                    }
                }
                return stringBuffer;
            } catch (IOException e) {
                throw new IllegalArgumentException("Cannot print object: " + obj, e);
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException("Not formattable: " + obj, e2);
            }
        }

        @Override // java.text.Format
        public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
            if (((String) ((ChronoFormatter) this.formatter).globalAttributes.get(Attributes.CALENDAR_TYPE, CalendarText.ISO_CALENDAR_TYPE)).equals(CalendarText.ISO_CALENDAR_TYPE)) {
                try {
                    StringBuilder sb = new StringBuilder();
                    Set<ElementPosition> setPrint = this.formatter.print(this.formatter.getChronology().getChronoType().cast(obj), sb);
                    AttributedString attributedString = new AttributedString(sb.toString());
                    for (ElementPosition elementPosition : setPrint) {
                        DateFormat.Field field = toField(elementPosition.getElement());
                        if (field != null) {
                            attributedString.addAttribute(field, field, elementPosition.getStartIndex(), elementPosition.getEndIndex());
                        }
                    }
                    return attributedString.getIterator();
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Not formattable: " + obj, e);
                }
            }
            return super.formatToCharacterIterator(obj);
        }

        @Override // java.text.Format
        public Object parseObject(String str, ParsePosition parsePosition) {
            ParseLog parseLog = new ParseLog(parsePosition.getIndex());
            T t = this.formatter.parse(str, parseLog);
            if (t == null) {
                parsePosition.setErrorIndex(parseLog.getErrorIndex());
            } else {
                parsePosition.setIndex(parseLog.getPosition());
            }
            return t;
        }

        private static DateFormat.Field toField(ChronoElement<?> chronoElement) {
            return FIELD_MAP.get(chronoElement.name());
        }
    }

    private static class OverrideHandler<C> implements ChronoMerger<GeneralTimestamp<C>> {
        private final List<ChronoExtension> extensions;
        private final Chronology<C> override;

        public Chronology<?> getCalendarOverride() {
            return this.override;
        }

        public List<ChronoExtension> getExtensions() {
            return this.extensions;
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ Object createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ Object createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        private OverrideHandler(Chronology<C> chronology) {
            this.override = chronology;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(chronology.getExtensions());
            arrayList.addAll(PlainTime.axis().getExtensions());
            this.extensions = Collections.unmodifiableList(arrayList);
        }

        static <C> OverrideHandler<C> of(Chronology<C> chronology) {
            if (chronology == null) {
                return null;
            }
            return new OverrideHandler<>(chronology);
        }

        @Override // net.time4j.engine.ChronoMerger
        public GeneralTimestamp<C> createFrom(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            GeneralTimestamp generalTimestampOf;
            C cCreateFrom = this.override.createFrom(chronoEntity, attributeQuery, z, z2);
            PlainTime plainTime = (PlainTime) PlainTime.axis().createFrom(chronoEntity, attributeQuery, z, z2);
            if (cCreateFrom instanceof CalendarVariant) {
                generalTimestampOf = GeneralTimestamp.of((CalendarVariant) CalendarVariant.class.cast(cCreateFrom), plainTime);
            } else if (cCreateFrom instanceof Calendrical) {
                generalTimestampOf = GeneralTimestamp.of((Calendrical) Calendrical.class.cast(cCreateFrom), plainTime);
            } else {
                throw new IllegalStateException("Cannot determine calendar type: " + cCreateFrom);
            }
            return (GeneralTimestamp) ChronoFormatter.cast(generalTimestampOf);
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return this.override.getDefaultStartOfDay();
        }

        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return this.override.getDefaultPivotYear();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof OverrideHandler) {
                return this.override.equals(((OverrideHandler) obj).override);
            }
            return false;
        }

        public int hashCode() {
            return this.override.hashCode();
        }

        public String toString() {
            return this.override.getChronoType().getName();
        }

        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(GeneralTimestamp<C> generalTimestamp, AttributeQuery attributeQuery) {
            throw new UnsupportedOperationException("Not used.");
        }

        @Override // net.time4j.engine.ChronoMerger
        public Chronology<?> preparser() {
            throw new UnsupportedOperationException("Not used.");
        }

        @Override // net.time4j.engine.ChronoMerger
        public GeneralTimestamp<C> createFrom(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
            throw new UnsupportedOperationException("Not used.");
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            throw new UnsupportedOperationException("Not used.");
        }
    }

    private static class ZonalDisplay implements ChronoDisplay, VariantSource, UnixTime {
        private final GeneralTimestamp<?> tsp;
        private final TZID tzid;
        private final String variant;

        @Override // net.time4j.engine.ChronoDisplay
        public TZID getTimezone() {
            return this.tzid;
        }

        @Override // net.time4j.engine.VariantSource
        public String getVariant() {
            return this.variant;
        }

        @Override // net.time4j.engine.ChronoDisplay
        public boolean hasTimezone() {
            return true;
        }

        private ZonalDisplay(GeneralTimestamp<?> generalTimestamp, String str, TZID tzid) {
            this.tsp = generalTimestamp;
            this.variant = str;
            this.tzid = tzid;
        }

        @Override // net.time4j.engine.ChronoDisplay
        public boolean contains(ChronoElement<?> chronoElement) {
            return this.tsp.contains(chronoElement);
        }

        @Override // net.time4j.engine.ChronoDisplay
        public <V> V get(ChronoElement<V> chronoElement) {
            return (V) this.tsp.get(chronoElement);
        }

        @Override // net.time4j.engine.ChronoDisplay
        public int getInt(ChronoElement<Integer> chronoElement) {
            return this.tsp.getInt(chronoElement);
        }

        @Override // net.time4j.engine.ChronoDisplay
        public <V> V getMinimum(ChronoElement<V> chronoElement) {
            return (V) this.tsp.getMinimum(chronoElement);
        }

        @Override // net.time4j.engine.ChronoDisplay
        public <V> V getMaximum(ChronoElement<V> chronoElement) {
            return (V) this.tsp.getMaximum(chronoElement);
        }

        @Override // net.time4j.base.UnixTime
        public long getPosixTime() {
            return getUnixTime().getPosixTime();
        }

        @Override // net.time4j.base.UnixTime
        public int getNanosecond() {
            return getUnixTime().getNanosecond();
        }

        private UnixTime getUnixTime() {
            StartOfDay defaultStartOfDay;
            try {
                defaultStartOfDay = Chronology.lookup(this.tsp.toDate().getClass()).getDefaultStartOfDay();
            } catch (RuntimeException unused) {
                defaultStartOfDay = StartOfDay.MIDNIGHT;
            }
            return this.tsp.in(Timezone.of(this.tzid), defaultStartOfDay);
        }
    }
}
