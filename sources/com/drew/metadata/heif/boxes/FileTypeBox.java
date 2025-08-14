package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.heif.HeifDirectory;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class FileTypeBox extends Box {
    ArrayList<String> compatibleBrands;
    String majorBrand;
    long minorVersion;

    public ArrayList<String> getCompatibleBrands() {
        return this.compatibleBrands;
    }

    public FileTypeBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(box);
        this.majorBrand = sequentialReader.getString(4);
        this.minorVersion = sequentialReader.getUInt32();
        this.compatibleBrands = new ArrayList<>();
        for (int i = 16; i < this.size; i += 4) {
            this.compatibleBrands.add(sequentialReader.getString(4));
        }
    }

    public void addMetadata(HeifDirectory heifDirectory) {
        heifDirectory.setString(1, this.majorBrand);
        heifDirectory.setLong(2, this.minorVersion);
        ArrayList<String> arrayList = this.compatibleBrands;
        heifDirectory.setStringArray(3, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }
}
