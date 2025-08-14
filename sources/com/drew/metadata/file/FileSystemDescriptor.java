package com.drew.metadata.file;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class FileSystemDescriptor extends TagDescriptor<FileSystemDirectory> {
    public FileSystemDescriptor(FileSystemDirectory fileSystemDirectory) {
        super(fileSystemDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 2) {
            return getFileSizeDescription();
        }
        return super.getDescription(i);
    }

    private String getFileSizeDescription() {
        Long longObject = ((FileSystemDirectory) this._directory).getLongObject(2);
        if (longObject == null) {
            return null;
        }
        return Long.toString(longObject.longValue()) + " bytes";
    }
}
