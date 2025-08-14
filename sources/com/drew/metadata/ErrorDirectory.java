package com.drew.metadata;

import java.util.HashMap;

/* loaded from: classes5.dex */
public final class ErrorDirectory extends Directory {
    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Error";
    }

    @Override // com.drew.metadata.Directory
    public String getTagName(int i) {
        return "";
    }

    @Override // com.drew.metadata.Directory
    public boolean hasTagName(int i) {
        return false;
    }

    public ErrorDirectory() {
    }

    public ErrorDirectory(String str) {
        super.addError(str);
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return new HashMap<>();
    }

    @Override // com.drew.metadata.Directory
    public void setObject(int i, Object obj) {
        throw new UnsupportedOperationException(String.format("Cannot add value to %s.", ErrorDirectory.class.getName()));
    }
}
