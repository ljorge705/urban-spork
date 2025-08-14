package org.spongycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.spongycastle.i18n.filter.Filter;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.i18n.filter.UntrustedUrlInput;

/* loaded from: classes7.dex */
public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected String encoding;
    protected FilteredArguments extraArgs;
    protected Filter filter;
    protected final String id;
    protected ClassLoader loader;
    protected final String resource;

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public String getId() {
        return this.id;
    }

    public String getResource() {
        return this.resource;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public LocalizedMessage(String str, String str2) throws NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str == null || str2 == null) {
            throw null;
        }
        this.id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(this);
    }

    public LocalizedMessage(String str, String str2, String str3) throws UnsupportedEncodingException, NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str == null || str2 == null) {
            throw null;
        }
        this.id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(this);
        if (!Charset.isSupported(str3)) {
            throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
        }
        this.encoding = str3;
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) throws NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str == null || str2 == null || objArr == null) {
            throw null;
        }
        this.id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
    }

    public LocalizedMessage(String str, String str2, String str3, Object[] objArr) throws UnsupportedEncodingException, NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str == null || str2 == null || objArr == null) {
            throw null;
        }
        this.id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
        if (!Charset.isSupported(str3)) {
            throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
        }
        this.encoding = str3;
    }

    public String getEntry(String str, Locale locale, TimeZone timeZone) throws MissingEntryException {
        ResourceBundle bundle;
        String str2 = this.id;
        if (str != null) {
            str2 = str2 + "." + str;
        }
        String str3 = str2;
        try {
            ClassLoader classLoader = this.loader;
            if (classLoader == null) {
                bundle = ResourceBundle.getBundle(this.resource, locale);
            } else {
                bundle = ResourceBundle.getBundle(this.resource, locale, classLoader);
            }
            String string = bundle.getString(str3);
            if (!this.encoding.equals("ISO-8859-1")) {
                string = new String(string.getBytes("ISO-8859-1"), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                string = formatWithTimeZone(string, this.arguments.getFilteredArgs(locale), locale, timeZone);
            }
            return addExtraArgs(string, locale);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MissingResourceException unused) {
            String str4 = "Can't find entry " + str3 + " in resource file " + this.resource + ".";
            String str5 = this.resource;
            ClassLoader classLoader2 = this.loader;
            if (classLoader2 == null) {
                classLoader2 = getClassLoader();
            }
            throw new MissingEntryException(str4, str5, str3, locale, classLoader2);
        }
    }

    protected String formatWithTimeZone(String str, Object[] objArr, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(StringUtils.SPACE);
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(str);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formats = messageFormat.getFormats();
            for (int i = 0; i < formats.length; i++) {
                Format format = formats[i];
                if (format instanceof DateFormat) {
                    DateFormat dateFormat = (DateFormat) format;
                    dateFormat.setTimeZone(timeZone);
                    messageFormat.setFormat(i, dateFormat);
                }
            }
        }
        return messageFormat.format(objArr);
    }

    protected String addExtraArgs(String str, Locale locale) {
        if (this.extraArgs == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        Object[] filteredArgs = this.extraArgs.getFilteredArgs(locale);
        for (Object obj : filteredArgs) {
            stringBuffer.append(obj);
        }
        return stringBuffer.toString();
    }

    public void setFilter(Filter filter) {
        this.arguments.setFilter(filter);
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null) {
            filteredArguments.setFilter(filter);
        }
        this.filter = filter;
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public void setExtraArgument(Object obj) {
        setExtraArguments(new Object[]{obj});
    }

    public void setExtraArguments(Object[] objArr) {
        if (objArr == null) {
            this.extraArgs = null;
            return;
        }
        FilteredArguments filteredArguments = new FilteredArguments(objArr);
        this.extraArgs = filteredArguments;
        filteredArguments.setFilter(this.filter);
    }

    public Object[] getExtraArgs() {
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments == null) {
            return null;
        }
        return filteredArguments.getArguments();
    }

    protected class FilteredArguments {
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected static final int NO_FILTER = 0;
        protected int[] argFilterType;
        protected Object[] arguments;
        protected Filter filter;
        protected Object[] filteredArgs;
        protected boolean[] isLocaleSpecific;
        protected Object[] unpackedArgs;

        public Object[] getArguments() {
            return this.arguments;
        }

        public Filter getFilter() {
            return this.filter;
        }

        FilteredArguments(LocalizedMessage localizedMessage) {
            this(new Object[0]);
        }

        FilteredArguments(Object[] objArr) {
            this.filter = null;
            this.arguments = objArr;
            this.unpackedArgs = new Object[objArr.length];
            this.filteredArgs = new Object[objArr.length];
            this.isLocaleSpecific = new boolean[objArr.length];
            this.argFilterType = new int[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj instanceof TrustedInput) {
                    this.unpackedArgs[i] = ((TrustedInput) obj).getInput();
                    this.argFilterType[i] = 0;
                } else if (obj instanceof UntrustedInput) {
                    this.unpackedArgs[i] = ((UntrustedInput) obj).getInput();
                    if (objArr[i] instanceof UntrustedUrlInput) {
                        this.argFilterType[i] = 2;
                    } else {
                        this.argFilterType[i] = 1;
                    }
                } else {
                    this.unpackedArgs[i] = obj;
                    this.argFilterType[i] = 1;
                }
                this.isLocaleSpecific[i] = this.unpackedArgs[i] instanceof LocaleString;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object[] objArr = new Object[this.unpackedArgs.length];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.unpackedArgs;
                if (i >= objArr2.length) {
                    return objArr;
                }
                Object objFilter = this.filteredArgs[i];
                if (objFilter == null) {
                    Object obj = objArr2[i];
                    if (this.isLocaleSpecific[i]) {
                        objFilter = filter(this.argFilterType[i], ((LocaleString) obj).getLocaleString(locale));
                    } else {
                        objFilter = filter(this.argFilterType[i], obj);
                        this.filteredArgs[i] = objFilter;
                    }
                }
                objArr[i] = objFilter;
                i++;
            }
        }

        private Object filter(int i, Object obj) {
            Filter filter = this.filter;
            if (filter != null) {
                if (obj == null) {
                    obj = "null";
                }
                if (i != 0) {
                    if (i == 1) {
                        return filter.doFilter(obj.toString());
                    }
                    if (i != 2) {
                        return null;
                    }
                    return filter.doFilterUrl(obj.toString());
                }
            }
            return obj;
        }

        public void setFilter(Filter filter) {
            if (filter != this.filter) {
                for (int i = 0; i < this.unpackedArgs.length; i++) {
                    this.filteredArgs[i] = null;
                }
            }
            this.filter = filter;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Resource: \"");
        stringBuffer.append(this.resource);
        stringBuffer.append("\" Id: \"").append(this.id).append("\" Arguments: ");
        stringBuffer.append(this.arguments.getArguments().length).append(" normal");
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null && filteredArguments.getArguments().length > 0) {
            stringBuffer.append(", ").append(this.extraArgs.getArguments().length).append(" extra");
        }
        stringBuffer.append(" Encoding: ").append(this.encoding);
        stringBuffer.append(" ClassLoader: ").append(this.loader);
        return stringBuffer.toString();
    }
}
