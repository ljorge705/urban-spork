package net.time4j.i18n;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.format.OutputContext;
import net.time4j.format.TextProvider;
import net.time4j.format.TextWidth;
import net.time4j.format.internal.ExtendedPatterns;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public final class IsoTextProviderSPI implements TextProvider, ExtendedPatterns {
    private static final String ISO_PATH = "calendar/names/iso8601/iso8601";
    private static final Set<String> LANGUAGES;
    private static final Set<Locale> LOCALES;

    static Set<String> getPrimaryLanguages() {
        return LANGUAGES;
    }

    public String toString() {
        return "IsoTextProviderSPI";
    }

    static {
        String[] strArrSplit = PropertyBundle.load(ISO_PATH, Locale.ROOT).getString("languages").split(StringUtils.SPACE);
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, strArrSplit);
        Set<String> setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        LANGUAGES = setUnmodifiableSet;
        HashSet hashSet2 = new HashSet();
        Iterator<String> it = setUnmodifiableSet.iterator();
        while (it.hasNext()) {
            hashSet2.add(new Locale(it.next()));
        }
        for (LanguageMatch languageMatch : LanguageMatch.values()) {
            hashSet2.add(new Locale(languageMatch.name()));
        }
        LOCALES = Collections.unmodifiableSet(hashSet2);
    }

    @Override // net.time4j.format.TextProvider
    public boolean supportsCalendarType(String str) {
        return CalendarText.ISO_CALENDAR_TYPE.equals(str);
    }

    @Override // net.time4j.format.TextProvider
    public boolean supportsLanguage(Locale locale) {
        return LANGUAGES.contains(LanguageMatch.getAlias(locale));
    }

    @Override // net.time4j.format.TextProvider
    public String[] getSupportedCalendarTypes() {
        return new String[]{CalendarText.ISO_CALENDAR_TYPE};
    }

    @Override // net.time4j.format.TextProvider
    public Locale[] getAvailableLocales() {
        Set<Locale> set = LOCALES;
        return (Locale[]) set.toArray(new Locale[set.size()]);
    }

    @Override // net.time4j.format.TextProvider
    public String[] months(String str, Locale locale, TextWidth textWidth, OutputContext outputContext, boolean z) {
        return months(locale, textWidth, outputContext);
    }

    @Override // net.time4j.format.TextProvider
    public String[] quarters(String str, Locale locale, TextWidth textWidth, OutputContext outputContext) {
        return quarters(locale, textWidth, outputContext);
    }

    @Override // net.time4j.format.TextProvider
    public String[] weekdays(String str, Locale locale, TextWidth textWidth, OutputContext outputContext) {
        return weekdays(locale, textWidth, outputContext);
    }

    @Override // net.time4j.format.TextProvider
    public String[] eras(String str, Locale locale, TextWidth textWidth) {
        return eras(locale, textWidth);
    }

    @Override // net.time4j.format.TextProvider
    public String[] meridiems(String str, Locale locale, TextWidth textWidth, OutputContext outputContext) {
        return meridiems(locale, textWidth, outputContext);
    }

    @Override // net.time4j.format.FormatPatternProvider
    public String getDatePattern(DisplayMode displayMode, Locale locale) {
        return getBundle(locale).getString("F(" + toChar(displayMode) + ")_d");
    }

    @Override // net.time4j.format.FormatPatternProvider
    public String getTimePattern(DisplayMode displayMode, Locale locale) {
        return getTimePattern(displayMode, locale, false);
    }

    @Override // net.time4j.format.internal.ExtendedPatterns
    public String getTimePattern(DisplayMode displayMode, Locale locale, boolean z) {
        String str;
        if (z && displayMode == DisplayMode.FULL) {
            str = "F(alt)";
        } else {
            str = "F(" + toChar(displayMode) + ")_t";
        }
        return getBundle(locale).getString(str);
    }

    @Override // net.time4j.format.FormatPatternProvider
    public String getDateTimePattern(DisplayMode displayMode, DisplayMode displayMode2, Locale locale) {
        if (displayMode.compareTo(displayMode2) < 0) {
            displayMode = displayMode2;
        }
        return getBundle(locale).getString("F(" + toChar(displayMode) + ")_dt");
    }

    @Override // net.time4j.format.FormatPatternProvider
    public String getIntervalPattern(Locale locale) {
        return getBundle(locale).getString("I");
    }

    private static String[] months(Locale locale, TextWidth textWidth, OutputContext outputContext) throws MissingResourceException {
        String[] strArrLookupBundle;
        PropertyBundle bundle = getBundle(locale);
        if (bundle != null) {
            if (textWidth == TextWidth.SHORT) {
                textWidth = TextWidth.ABBREVIATED;
            }
            strArrLookupBundle = lookupBundle(bundle, 12, getKey(bundle, "MONTH_OF_YEAR"), textWidth, null, outputContext, 1);
            if (strArrLookupBundle == null) {
                if (outputContext == OutputContext.STANDALONE) {
                    if (textWidth != TextWidth.NARROW) {
                        strArrLookupBundle = months(locale, textWidth, OutputContext.FORMAT);
                    }
                } else if (textWidth == TextWidth.ABBREVIATED) {
                    strArrLookupBundle = months(locale, TextWidth.WIDE, OutputContext.FORMAT);
                } else if (textWidth == TextWidth.NARROW) {
                    strArrLookupBundle = months(locale, textWidth, OutputContext.STANDALONE);
                }
            }
        } else {
            strArrLookupBundle = null;
        }
        if (strArrLookupBundle != null) {
            return strArrLookupBundle;
        }
        throw new MissingResourceException("Cannot find ISO-8601-month for locale: " + locale, IsoTextProviderSPI.class.getName(), locale.toString());
    }

    private static String[] quarters(Locale locale, TextWidth textWidth, OutputContext outputContext) throws MissingResourceException {
        String[] strArrLookupBundle;
        PropertyBundle bundle = getBundle(locale);
        if (bundle != null) {
            if (textWidth == TextWidth.SHORT) {
                textWidth = TextWidth.ABBREVIATED;
            }
            strArrLookupBundle = lookupBundle(bundle, 4, getKey(bundle, "QUARTER_OF_YEAR"), textWidth, null, outputContext, 1);
            if (strArrLookupBundle == null) {
                if (outputContext == OutputContext.STANDALONE) {
                    if (textWidth != TextWidth.NARROW) {
                        strArrLookupBundle = quarters(locale, textWidth, OutputContext.FORMAT);
                    }
                } else if (textWidth == TextWidth.ABBREVIATED) {
                    strArrLookupBundle = quarters(locale, TextWidth.WIDE, OutputContext.FORMAT);
                } else if (textWidth == TextWidth.NARROW) {
                    strArrLookupBundle = quarters(locale, textWidth, OutputContext.STANDALONE);
                }
            }
        } else {
            strArrLookupBundle = null;
        }
        if (strArrLookupBundle != null) {
            return strArrLookupBundle;
        }
        throw new MissingResourceException("Cannot find ISO-8601-quarter-of-year for locale: " + locale, IsoTextProviderSPI.class.getName(), locale.toString());
    }

    private static String[] weekdays(Locale locale, TextWidth textWidth, OutputContext outputContext) throws MissingResourceException {
        String[] strArrLookupBundle;
        PropertyBundle bundle = getBundle(locale);
        if (bundle != null) {
            strArrLookupBundle = lookupBundle(bundle, 7, getKey(bundle, "DAY_OF_WEEK"), textWidth, null, outputContext, 1);
            if (strArrLookupBundle == null) {
                if (outputContext == OutputContext.STANDALONE) {
                    if (textWidth != TextWidth.NARROW) {
                        strArrLookupBundle = weekdays(locale, textWidth, OutputContext.FORMAT);
                    }
                } else if (textWidth == TextWidth.ABBREVIATED) {
                    strArrLookupBundle = weekdays(locale, TextWidth.WIDE, OutputContext.FORMAT);
                } else if (textWidth == TextWidth.SHORT) {
                    strArrLookupBundle = weekdays(locale, TextWidth.ABBREVIATED, OutputContext.FORMAT);
                } else if (textWidth == TextWidth.NARROW) {
                    strArrLookupBundle = weekdays(locale, textWidth, OutputContext.STANDALONE);
                }
            }
        } else {
            strArrLookupBundle = null;
        }
        if (strArrLookupBundle != null) {
            return strArrLookupBundle;
        }
        throw new MissingResourceException("Cannot find ISO-8601-day-of-week for locale: " + locale, IsoTextProviderSPI.class.getName(), locale.toString());
    }

    private static String[] eras(Locale locale, TextWidth textWidth) throws MissingResourceException {
        PropertyBundle bundle = getBundle(locale);
        String[] strArrLookupBundle = null;
        if (bundle != null) {
            if (textWidth == TextWidth.SHORT) {
                textWidth = TextWidth.ABBREVIATED;
            }
            strArrLookupBundle = lookupBundle(bundle, 5, getKey(bundle, "ERA"), textWidth, textWidth == TextWidth.NARROW ? TextWidth.ABBREVIATED : null, OutputContext.FORMAT, 0);
            if (strArrLookupBundle == null && textWidth != TextWidth.ABBREVIATED) {
                strArrLookupBundle = eras(locale, TextWidth.ABBREVIATED);
            }
        }
        if (strArrLookupBundle != null) {
            return strArrLookupBundle;
        }
        throw new MissingResourceException("Cannot find ISO-8601-resource for era and locale: " + locale, IsoTextProviderSPI.class.getName(), locale.toString());
    }

    private static String[] meridiems(Locale locale, TextWidth textWidth, OutputContext outputContext) throws MissingResourceException {
        PropertyBundle bundle = getBundle(locale);
        if (bundle != null) {
            if (textWidth == TextWidth.SHORT) {
                textWidth = TextWidth.ABBREVIATED;
            }
            String strMeridiemKey = meridiemKey("am", textWidth, outputContext);
            String strMeridiemKey2 = meridiemKey("pm", textWidth, outputContext);
            if (bundle.containsKey(strMeridiemKey) && bundle.containsKey(strMeridiemKey2)) {
                return new String[]{bundle.getString(strMeridiemKey), bundle.getString(strMeridiemKey2)};
            }
            if (outputContext == OutputContext.STANDALONE) {
                if (textWidth == TextWidth.ABBREVIATED) {
                    return meridiems(locale, textWidth, OutputContext.FORMAT);
                }
                return meridiems(locale, TextWidth.ABBREVIATED, outputContext);
            }
            if (textWidth != TextWidth.ABBREVIATED) {
                return meridiems(locale, TextWidth.ABBREVIATED, outputContext);
            }
        }
        throw new MissingResourceException("Cannot find ISO-8601-resource for am/pm and locale: " + locale, IsoTextProviderSPI.class.getName(), locale.toString());
    }

    private static PropertyBundle getBundle(Locale locale) throws MissingResourceException {
        return PropertyBundle.load(ISO_PATH, locale);
    }

    private static char toChar(DisplayMode displayMode) {
        return Character.toLowerCase(displayMode.name().charAt(0));
    }

    private static String[] lookupBundle(PropertyBundle propertyBundle, int i, String str, TextWidth textWidth, TextWidth textWidth2, OutputContext outputContext, int i2) {
        String[] strArrLookupBundle;
        String[] strArr = new String[i];
        boolean z = str.length() == 1;
        for (int i3 = 0; i3 < i; i3++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('(');
            if (z) {
                char cCharAt = textWidth.name().charAt(0);
                if (outputContext != OutputContext.STANDALONE) {
                    cCharAt = Character.toLowerCase(cCharAt);
                }
                sb.append(cCharAt);
            } else {
                sb.append(textWidth.name());
                if (outputContext == OutputContext.STANDALONE) {
                    sb.append('|');
                    sb.append(outputContext.name());
                }
            }
            sb.append(")_");
            sb.append(i3 + i2);
            String string = sb.toString();
            if (propertyBundle.containsKey(string)) {
                strArr[i3] = propertyBundle.getString(string);
            } else {
                if (textWidth2 == null || (strArrLookupBundle = lookupBundle(propertyBundle, i, str, textWidth2, null, outputContext, i2)) == null) {
                    return null;
                }
                strArr[i3] = strArrLookupBundle[i3];
            }
        }
        return strArr;
    }

    private static String getKey(PropertyBundle propertyBundle, String str) {
        return (propertyBundle.containsKey("useShortKeys") && "true".equals(propertyBundle.getString("useShortKeys"))) ? str.substring(0, 1) : str;
    }

    private static String meridiemKey(String str, TextWidth textWidth, OutputContext outputContext) {
        char cCharAt = textWidth.name().charAt(0);
        if (outputContext == OutputContext.FORMAT) {
            cCharAt = Character.toLowerCase(cCharAt);
        }
        return "P(" + String.valueOf(cCharAt) + ")_" + str;
    }
}
