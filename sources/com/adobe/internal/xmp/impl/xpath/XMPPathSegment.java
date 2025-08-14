package com.adobe.internal.xmp.impl.xpath;

/* loaded from: classes5.dex */
public class XMPPathSegment {
    private boolean alias;
    private int aliasForm;
    private int kind;
    private String name;

    public int getAliasForm() {
        return this.aliasForm;
    }

    public int getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAlias() {
        return this.alias;
    }

    public void setAlias(boolean z) {
        this.alias = z;
    }

    public void setAliasForm(int i) {
        this.aliasForm = i;
    }

    public void setKind(int i) {
        this.kind = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        switch (this.kind) {
        }
        return this.name;
    }

    public XMPPathSegment(String str) {
        this.name = str;
    }

    public XMPPathSegment(String str, int i) {
        this.name = str;
        this.kind = i;
    }
}
