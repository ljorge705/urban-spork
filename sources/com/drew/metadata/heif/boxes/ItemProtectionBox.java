package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ItemProtectionBox extends FullBox {
    int protectionCount;
    ArrayList<ProtectionSchemeInfoBox> protectionSchemes;

    public ItemProtectionBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.protectionCount = sequentialReader.getUInt16();
        this.protectionSchemes = new ArrayList<>(this.protectionCount);
        for (int i = 1; i <= this.protectionCount; i++) {
            this.protectionSchemes.add(new ProtectionSchemeInfoBox(sequentialReader, box));
        }
    }

    class ProtectionSchemeInfoBox extends Box {
        public ProtectionSchemeInfoBox(SequentialReader sequentialReader, Box box) throws IOException {
            super(box);
        }

        class OriginalFormatBox extends Box {
            String dataFormat;

            public OriginalFormatBox(SequentialReader sequentialReader, Box box) throws IOException {
                super(sequentialReader);
                this.dataFormat = sequentialReader.getString(4);
            }
        }
    }
}
