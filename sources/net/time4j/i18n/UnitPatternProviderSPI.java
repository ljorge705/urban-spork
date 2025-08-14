package net.time4j.i18n;

import com.facebook.react.uimanager.ViewProps;
import java.util.Locale;
import java.util.MissingResourceException;
import net.time4j.Weekday;
import net.time4j.format.PluralCategory;
import net.time4j.format.RelativeTimeProvider;
import net.time4j.format.TextWidth;

/* loaded from: classes4.dex */
public final class UnitPatternProviderSPI implements RelativeTimeProvider {
    @Override // net.time4j.format.UnitPatternProvider
    public String getYearPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'Y', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMonthPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'M', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getWeekPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'W', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getDayPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'D', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getHourPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'H', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMinutePattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'N', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getSecondPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, 'S', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMilliPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, '3', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMicroPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, '6', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getNanoPattern(Locale locale, TextWidth textWidth, PluralCategory pluralCategory) {
        return getUnitPattern(locale, '9', textWidth, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getYearPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'Y', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMonthPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'M', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getWeekPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'W', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getDayPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'D', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getHourPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'H', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getMinutePattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'N', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getSecondPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'S', z, pluralCategory);
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getNowWord(Locale locale) {
        return getPattern(locale, "reltime/relpattern", "now", null, PluralCategory.OTHER);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortYearPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'y', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortMonthPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'm', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortWeekPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'w', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortDayPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'd', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortHourPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'h', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortMinutePattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 'n', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getShortSecondPattern(Locale locale, boolean z, PluralCategory pluralCategory) {
        return getRelativePattern(locale, 's', z, pluralCategory);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getYesterdayWord(Locale locale) {
        return getPattern(locale, "reltime/relpattern", "yesterday", null, PluralCategory.OTHER);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getTodayWord(Locale locale) {
        return getPattern(locale, "reltime/relpattern", "today", null, PluralCategory.OTHER);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String getTomorrowWord(Locale locale) {
        return getPattern(locale, "reltime/relpattern", "tomorrow", null, PluralCategory.OTHER);
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String labelForLast(Weekday weekday, Locale locale) {
        return getLabel(locale, weekday.name().substring(0, 3).toLowerCase() + "-");
    }

    @Override // net.time4j.format.RelativeTimeProvider
    public String labelForNext(Weekday weekday, Locale locale) {
        return getLabel(locale, weekday.name().substring(0, 3).toLowerCase() + "+");
    }

    @Override // net.time4j.format.UnitPatternProvider
    public String getListPattern(Locale locale, TextWidth textWidth, int i) throws Throwable {
        int i2;
        if (i < 2) {
            throw new IllegalArgumentException("Size must be greater than 1.");
        }
        PropertyBundle propertyBundleLoad = PropertyBundle.load("i18n/units/upattern", locale);
        String strBuildListKey = buildListKey(textWidth, String.valueOf(i));
        if (propertyBundleLoad.containsKey(strBuildListKey)) {
            return propertyBundleLoad.getString(strBuildListKey);
        }
        String string = propertyBundleLoad.getString(buildListKey(textWidth, ViewProps.END));
        if (i == 2) {
            return string;
        }
        String string2 = propertyBundleLoad.getString(buildListKey(textWidth, ViewProps.START));
        String string3 = propertyBundleLoad.getString(buildListKey(textWidth, "middle"));
        String strReplace = replace(replace(string, '1', i - 1), '0', i - 2);
        int i3 = i - 3;
        String strReplace2 = strReplace;
        while (i3 >= 0) {
            String str = i3 == 0 ? string2 : string3;
            int length = str.length();
            int i4 = length - 1;
            while (true) {
                if (i4 < 0) {
                    i2 = -1;
                    break;
                }
                if (i4 >= 2 && str.charAt(i4) == '}' && str.charAt(i4 - 1) == '1') {
                    i2 = i4 - 2;
                    if (str.charAt(i2) == '{') {
                        break;
                    }
                }
                i4--;
            }
            if (i2 > -1) {
                strReplace = str.substring(0, i2) + strReplace2;
                if (i2 < length - 3) {
                    strReplace = strReplace + str.substring(i2 + 3);
                }
            }
            if (i3 > 0) {
                strReplace2 = replace(strReplace, '0', i3);
            }
            i3--;
        }
        return strReplace;
    }

    private String getUnitPattern(Locale locale, char c, TextWidth textWidth, PluralCategory pluralCategory) {
        return getPattern(locale, "units/upattern", buildKey(c, textWidth, pluralCategory), buildKey(c, textWidth, PluralCategory.OTHER), pluralCategory);
    }

    private String getRelativePattern(Locale locale, char c, boolean z, PluralCategory pluralCategory) {
        return getPattern(locale, "reltime/relpattern", buildKey(c, z, pluralCategory), buildKey(c, z, PluralCategory.OTHER), pluralCategory);
    }

    private String getPattern(Locale locale, String str, String str2, String str3, PluralCategory pluralCategory) {
        boolean z = true;
        PropertyBundle propertyBundle = null;
        for (Locale locale2 : PropertyBundle.getCandidateLocales(locale)) {
            PropertyBundle propertyBundleLoad = (!z || propertyBundle == null) ? PropertyBundle.load("i18n/" + str, locale2) : propertyBundle;
            if (z) {
                if (locale2.equals(propertyBundleLoad.getLocale())) {
                    z = false;
                } else {
                    propertyBundle = propertyBundleLoad;
                }
            }
            if (propertyBundleLoad.getInternalKeys().contains(str2)) {
                return propertyBundleLoad.getString(str2);
            }
            if (pluralCategory != PluralCategory.OTHER && propertyBundleLoad.getInternalKeys().contains(str3)) {
                return propertyBundleLoad.getString(str3);
            }
        }
        throw new MissingResourceException("Can't find resource for bundle " + str + ".properties, key " + str2, str + ".properties", str2);
    }

    private String getLabel(Locale locale, String str) {
        boolean z = true;
        PropertyBundle propertyBundle = null;
        for (Locale locale2 : PropertyBundle.getCandidateLocales(locale)) {
            PropertyBundle propertyBundleLoad = (!z || propertyBundle == null) ? PropertyBundle.load("i18n/reltime/relpattern", locale2) : propertyBundle;
            if (z) {
                if (locale2.equals(propertyBundleLoad.getLocale())) {
                    z = false;
                } else {
                    propertyBundle = propertyBundleLoad;
                }
            }
            if (propertyBundleLoad.getInternalKeys().contains(str)) {
                return propertyBundleLoad.getString(str);
            }
        }
        return "";
    }

    private static String buildKey(char c, TextWidth textWidth, PluralCategory pluralCategory) {
        StringBuilder sb = new StringBuilder(3);
        sb.append(c);
        int i = AnonymousClass1.$SwitchMap$net$time4j$format$TextWidth[textWidth.ordinal()];
        if (i == 1) {
            sb.append('w');
        } else if (i == 2 || i == 3) {
            sb.append('s');
        } else if (i == 4) {
            sb.append('n');
        } else {
            throw new UnsupportedOperationException(textWidth.name());
        }
        return sb.append(pluralCategory.ordinal()).toString();
    }

    /* renamed from: net.time4j.i18n.UnitPatternProviderSPI$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$format$TextWidth;

        static {
            int[] iArr = new int[TextWidth.values().length];
            $SwitchMap$net$time4j$format$TextWidth = iArr;
            try {
                iArr[TextWidth.WIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$format$TextWidth[TextWidth.ABBREVIATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$format$TextWidth[TextWidth.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$format$TextWidth[TextWidth.NARROW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static String buildKey(char c, boolean z, PluralCategory pluralCategory) {
        StringBuilder sb = new StringBuilder(3);
        sb.append(c);
        sb.append(z ? '+' : '-');
        return sb.append(pluralCategory.ordinal()).toString();
    }

    private static String buildListKey(TextWidth textWidth, String str) {
        StringBuilder sb = new StringBuilder("L");
        int i = AnonymousClass1.$SwitchMap$net$time4j$format$TextWidth[textWidth.ordinal()];
        if (i == 1) {
            sb.append('w');
        } else if (i == 2 || i == 3) {
            sb.append('s');
        } else if (i == 4) {
            sb.append('n');
        } else {
            throw new UnsupportedOperationException(textWidth.name());
        }
        return sb.append('-').append(str).toString();
    }

    private static String replace(String str, char c, int i) {
        int length = str.length();
        int i2 = length - 2;
        for (int i3 = 0; i3 < i2; i3++) {
            if (str.charAt(i3) == '{') {
                int i4 = i3 + 1;
                if (str.charAt(i4) == c) {
                    int i5 = i3 + 2;
                    if (str.charAt(i5) == '}') {
                        StringBuilder sb = new StringBuilder(length + 8);
                        sb.append(str);
                        sb.replace(i4, i5, String.valueOf(i));
                        return sb.toString();
                    }
                } else {
                    continue;
                }
            }
        }
        return str;
    }
}
