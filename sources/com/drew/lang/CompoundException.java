package com.drew.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes5.dex */
public class CompoundException extends Exception {
    private static final long serialVersionUID = -9207883813472069925L;
    private final Throwable _innerException;

    public Throwable getInnerException() {
        return this._innerException;
    }

    public CompoundException(String str) {
        this(str, null);
    }

    public CompoundException(Throwable th) {
        this(null, th);
    }

    public CompoundException(String str, Throwable th) {
        super(str);
        this._innerException = th;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this._innerException != null) {
            sb.append("\n--- inner exception ---\n");
            sb.append(this._innerException.toString());
        }
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this._innerException != null) {
            printStream.println("--- inner exception ---");
            this._innerException.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this._innerException != null) {
            printWriter.println("--- inner exception ---");
            this._innerException.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        super.printStackTrace();
        if (this._innerException != null) {
            System.err.println("--- inner exception ---");
            this._innerException.printStackTrace();
        }
    }
}
