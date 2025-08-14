package net.time4j.i18n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.time4j.Weekday;
import net.time4j.base.ResourceLoader;
import net.time4j.format.WeekdataProvider;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class WeekdataProviderSPI implements WeekdataProvider {
    private final Set<String> countriesWithMinDays4;
    private final Map<String, Weekday> endOfWeekend;
    private final Map<String, Weekday> firstDayOfWeek;
    private final String source;
    private final Map<String, Weekday> startOfWeekend;

    public WeekdataProviderSPI() throws IOException {
        String strSubstring;
        Weekday weekday;
        HashMap map;
        URI uriLocate = ResourceLoader.getInstance().locate("i18n", WeekdataProviderSPI.class, "data/week.data");
        InputStream inputStreamLoad = ResourceLoader.getInstance().load(uriLocate, true);
        if (inputStreamLoad == null) {
            try {
                inputStreamLoad = ResourceLoader.getInstance().load(WeekdataProviderSPI.class, "data/week.data", true);
            } catch (IOException unused) {
            }
        }
        if (inputStreamLoad != null) {
            this.source = "@" + uriLocate;
            HashSet hashSet = new HashSet();
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            HashMap map4 = new HashMap();
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamLoad, "US-ASCII"));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            if (!line.startsWith("#")) {
                                int iIndexOf = line.indexOf(61);
                                int i = 0;
                                String strTrim = line.substring(0, iIndexOf).trim();
                                String[] strArrSplit = line.substring(iIndexOf + 1).split(StringUtils.SPACE);
                                if (strTrim.equals("minDays-4")) {
                                    int length = strArrSplit.length;
                                    while (i < length) {
                                        String upperCase = strArrSplit[i].trim().toUpperCase(Locale.US);
                                        if (!upperCase.isEmpty()) {
                                            hashSet.add(upperCase);
                                        }
                                        i++;
                                    }
                                } else {
                                    if (strTrim.startsWith("start-")) {
                                        strSubstring = strTrim.substring(6);
                                        weekday = Weekday.SATURDAY;
                                        map = map3;
                                    } else if (strTrim.startsWith("end-")) {
                                        strSubstring = strTrim.substring(4);
                                        weekday = Weekday.SUNDAY;
                                        map = map4;
                                    } else if (strTrim.startsWith("first-")) {
                                        strSubstring = strTrim.substring(6);
                                        weekday = Weekday.MONDAY;
                                        map = map2;
                                    } else {
                                        throw new IllegalStateException("Unexpected format: " + this.source);
                                    }
                                    if (strSubstring.equals("sun")) {
                                        weekday = Weekday.SUNDAY;
                                    } else if (strSubstring.equals("sat")) {
                                        weekday = Weekday.SATURDAY;
                                    } else if (strSubstring.equals("fri")) {
                                        weekday = Weekday.FRIDAY;
                                    } else if (strSubstring.equals("thu")) {
                                        weekday = Weekday.THURSDAY;
                                    } else if (strSubstring.equals("wed")) {
                                        weekday = Weekday.WEDNESDAY;
                                    } else if (strSubstring.equals("tue")) {
                                        weekday = Weekday.TUESDAY;
                                    } else if (strSubstring.equals("mon")) {
                                        weekday = Weekday.MONDAY;
                                    }
                                    int length2 = strArrSplit.length;
                                    while (i < length2) {
                                        String upperCase2 = strArrSplit[i].trim().toUpperCase(Locale.US);
                                        if (!upperCase2.isEmpty()) {
                                            map.put(upperCase2, weekday);
                                        }
                                        i++;
                                    }
                                }
                            }
                        } else {
                            this.countriesWithMinDays4 = Collections.unmodifiableSet(hashSet);
                            this.firstDayOfWeek = Collections.unmodifiableMap(map2);
                            this.startOfWeekend = Collections.unmodifiableMap(map3);
                            this.endOfWeekend = Collections.unmodifiableMap(map4);
                            try {
                                inputStreamLoad.close();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace(System.err);
                                return;
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e2) {
                    throw new AssertionError(e2);
                } catch (Exception e3) {
                    throw new IllegalStateException("Unexpected format: " + this.source, e3);
                }
            } catch (Throwable th) {
                try {
                    inputStreamLoad.close();
                } catch (IOException e4) {
                    e4.printStackTrace(System.err);
                }
                throw th;
            }
        } else {
            this.source = "@STATIC";
            this.countriesWithMinDays4 = Collections.emptySet();
            this.firstDayOfWeek = Collections.emptyMap();
            this.startOfWeekend = Collections.emptyMap();
            this.endOfWeekend = Collections.emptyMap();
            System.err.println("Warning: File \"data/week.data\" not found.");
        }
    }

    @Override // net.time4j.format.WeekdataProvider
    public int getFirstDayOfWeek(Locale locale) {
        if (this.firstDayOfWeek.isEmpty()) {
            int firstDayOfWeek = new GregorianCalendar(locale).getFirstDayOfWeek();
            if (firstDayOfWeek == 1) {
                return 7;
            }
            return firstDayOfWeek - 1;
        }
        String country = locale.getCountry();
        Weekday weekday = Weekday.MONDAY;
        if (this.firstDayOfWeek.containsKey(country)) {
            weekday = this.firstDayOfWeek.get(country);
        }
        return weekday.getValue();
    }

    @Override // net.time4j.format.WeekdataProvider
    public int getMinimalDaysInFirstWeek(Locale locale) {
        if (this.countriesWithMinDays4.isEmpty()) {
            return new GregorianCalendar(locale).getMinimalDaysInFirstWeek();
        }
        String country = locale.getCountry();
        return ((country.isEmpty() && locale.getLanguage().isEmpty()) || this.countriesWithMinDays4.contains(country)) ? 4 : 1;
    }

    @Override // net.time4j.format.WeekdataProvider
    public int getStartOfWeekend(Locale locale) {
        String country = locale.getCountry();
        Weekday weekday = Weekday.SATURDAY;
        if (this.startOfWeekend.containsKey(country)) {
            weekday = this.startOfWeekend.get(country);
        }
        return weekday.getValue();
    }

    @Override // net.time4j.format.WeekdataProvider
    public int getEndOfWeekend(Locale locale) {
        String country = locale.getCountry();
        Weekday weekday = Weekday.SUNDAY;
        if (this.endOfWeekend.containsKey(country)) {
            weekday = this.endOfWeekend.get(country);
        }
        return weekday.getValue();
    }

    public String toString() {
        return getClass().getName() + this.source;
    }
}
