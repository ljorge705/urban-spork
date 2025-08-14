package com.drew.metadata.mov.metadata;

import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;

/* loaded from: classes5.dex */
public class QuickTimeMetadataDescriptor extends QuickTimeDescriptor {
    public QuickTimeMetadataDescriptor(QuickTimeDirectory quickTimeDirectory) {
        super(quickTimeDirectory);
    }

    @Override // com.drew.metadata.mov.QuickTimeDescriptor, com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1282) {
            return getArtworkDescription();
        }
        if (i == 1303) {
            return getLocationRoleDescription();
        }
        return super.getDescription(i);
    }

    private String getArtworkDescription() {
        return getByteLengthDescription(1282);
    }

    private String getLocationRoleDescription() {
        return getIndexedDescription(QuickTimeMetadataDirectory.TAG_LOCATION_ROLE, 0, "Shooting location", "Real location", "Fictional location");
    }
}
