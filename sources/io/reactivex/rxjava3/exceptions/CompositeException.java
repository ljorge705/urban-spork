package io.reactivex.rxjava3.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes6.dex */
public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    public List<Throwable> getExceptions() {
        return this.exceptions;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public CompositeException(Throwable... exceptions) {
        this(exceptions == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(exceptions));
    }

    public CompositeException(Iterable<? extends Throwable> errors) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (errors != null) {
            for (Throwable th : errors) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        List<Throwable> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(linkedHashSet));
        this.exceptions = listUnmodifiableList;
        this.message = listUnmodifiableList.size() + " exceptions occurred. ";
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        int i;
        if (this.cause == null) {
            String property = System.getProperty("line.separator");
            if (this.exceptions.size() > 1) {
                IdentityHashMap identityHashMap = new IdentityHashMap();
                StringBuilder sb = new StringBuilder();
                sb.append("Multiple exceptions (").append(this.exceptions.size()).append(")").append(property);
                for (Throwable cause : this.exceptions) {
                    int i2 = 0;
                    while (true) {
                        if (cause != null) {
                            for (int i3 = 0; i3 < i2; i3++) {
                                sb.append("  ");
                            }
                            sb.append("|-- ");
                            sb.append(cause.getClass().getCanonicalName()).append(": ");
                            String message = cause.getMessage();
                            if (message != null && message.contains(property)) {
                                sb.append(property);
                                for (String str : message.split(property)) {
                                    for (int i4 = 0; i4 < i2 + 2; i4++) {
                                        sb.append("  ");
                                    }
                                    sb.append(str).append(property);
                                }
                            } else {
                                sb.append(message);
                                sb.append(property);
                            }
                            int i5 = 0;
                            while (true) {
                                i = i2 + 2;
                                if (i5 >= i) {
                                    break;
                                }
                                sb.append("  ");
                                i5++;
                            }
                            StackTraceElement[] stackTrace = cause.getStackTrace();
                            if (stackTrace.length > 0) {
                                sb.append("at ").append(stackTrace[0]).append(property);
                            }
                            if (!identityHashMap.containsKey(cause)) {
                                identityHashMap.put(cause, true);
                                cause = cause.getCause();
                                i2++;
                            } else {
                                Throwable cause2 = cause.getCause();
                                if (cause2 != null) {
                                    for (int i6 = 0; i6 < i; i6++) {
                                        sb.append("  ");
                                    }
                                    sb.append("|-- ");
                                    sb.append("(cause not expanded again) ");
                                    sb.append(cause2.getClass().getCanonicalName()).append(": ");
                                    sb.append(cause2.getMessage());
                                    sb.append(property);
                                }
                            }
                        }
                    }
                }
                this.cause = new ExceptionOverview(sb.toString().trim());
            } else {
                this.cause = this.exceptions.get(0);
            }
        }
        return this.cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream s) {
        printStackTrace(new WrappedPrintStream(s));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter s) {
        printStackTrace(new WrappedPrintWriter(s));
    }

    private void printStackTrace(PrintStreamOrWriter output) {
        output.append(this).append("\n");
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            output.append("\tat ").append(stackTraceElement).append("\n");
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            output.append("  ComposedException ").append(Integer.valueOf(i)).append(" :\n");
            appendStackTrace(output, th, "\t");
            i++;
        }
        output.append("\n");
    }

    private void appendStackTrace(PrintStreamOrWriter output, Throwable ex, String prefix) {
        output.append(prefix).append(ex).append('\n');
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            output.append("\t\tat ").append(stackTraceElement).append('\n');
        }
        if (ex.getCause() != null) {
            output.append("\tCaused by: ");
            appendStackTrace(output, ex.getCause(), "");
        }
    }

    static abstract class PrintStreamOrWriter {
        abstract PrintStreamOrWriter append(Object o);

        PrintStreamOrWriter() {
        }
    }

    static final class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            this.printStream = printStream;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.reactivex.rxjava3.exceptions.CompositeException.PrintStreamOrWriter
        public WrappedPrintStream append(Object o) {
            this.printStream.print(o);
            return this;
        }
    }

    static final class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.reactivex.rxjava3.exceptions.CompositeException.PrintStreamOrWriter
        public WrappedPrintWriter append(Object o) {
            this.printWriter.print(o);
            return this;
        }
    }

    static final class ExceptionOverview extends RuntimeException {
        private static final long serialVersionUID = 3875212506787802066L;

        ExceptionOverview(String message) {
            super(message);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    public int size() {
        return this.exceptions.size();
    }
}
