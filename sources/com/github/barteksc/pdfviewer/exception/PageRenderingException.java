package com.github.barteksc.pdfviewer.exception;

/* loaded from: classes5.dex */
public class PageRenderingException extends Exception {
    private final int page;

    public int getPage() {
        return this.page;
    }

    public PageRenderingException(int i, Throwable th) {
        super(th);
        this.page = i;
    }
}
