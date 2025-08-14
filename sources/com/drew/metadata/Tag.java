package com.drew.metadata;

/* loaded from: classes5.dex */
public class Tag {
    private final Directory _directory;
    private final int _tagType;

    public int getTagType() {
        return this._tagType;
    }

    public Tag(int i, Directory directory) {
        this._tagType = i;
        this._directory = directory;
    }

    public String getTagTypeHex() {
        return String.format("0x%04x", Integer.valueOf(this._tagType));
    }

    public String getDescription() {
        return this._directory.getDescription(this._tagType);
    }

    public boolean hasTagName() {
        return this._directory.hasTagName(this._tagType);
    }

    public String getTagName() {
        return this._directory.getTagName(this._tagType);
    }

    public String getDirectoryName() {
        return this._directory.getName();
    }

    public String toString() {
        String description = getDescription();
        if (description == null) {
            description = this._directory.getString(getTagType()) + " (unable to formulate description)";
        }
        return "[" + this._directory.getName() + "] " + getTagName() + " - " + description;
    }
}
