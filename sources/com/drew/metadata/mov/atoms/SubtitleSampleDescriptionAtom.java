package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeSubtitleDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SubtitleSampleDescriptionAtom extends SampleDescriptionAtom<SubtitleSampleDescription> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.drew.metadata.mov.atoms.SampleDescriptionAtom
    public SubtitleSampleDescription getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return null;
    }

    public SubtitleSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    class SubtitleSampleDescription extends SampleDescription {
        long defaultTextBox;
        int displayFlags;
        int fontFace;
        int fontIdentifier;
        int fontSize;
        int[] foregroundColor;

        public SubtitleSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.displayFlags = sequentialReader.getInt32();
            sequentialReader.skip(1L);
            sequentialReader.skip(1L);
            sequentialReader.skip(4L);
            this.defaultTextBox = sequentialReader.getInt64();
            sequentialReader.skip(4L);
            this.fontIdentifier = sequentialReader.getInt16();
            this.fontFace = sequentialReader.getInt8();
            this.fontSize = sequentialReader.getInt8();
            this.foregroundColor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
        }
    }

    public void addMetadata(QuickTimeSubtitleDirectory quickTimeSubtitleDirectory) {
        SubtitleSampleDescription subtitleSampleDescription = (SubtitleSampleDescription) this.sampleDescriptions.get(0);
        quickTimeSubtitleDirectory.setBoolean(1, (subtitleSampleDescription.displayFlags & 536870912) == 536870912);
        quickTimeSubtitleDirectory.setBoolean(2, (subtitleSampleDescription.displayFlags & 1073741824) == 1073741824);
        quickTimeSubtitleDirectory.setBoolean(3, (subtitleSampleDescription.displayFlags & (-1073741824)) == -1073741824);
        quickTimeSubtitleDirectory.setLong(4, subtitleSampleDescription.defaultTextBox);
        quickTimeSubtitleDirectory.setInt(5, subtitleSampleDescription.fontIdentifier);
        int i = subtitleSampleDescription.fontFace;
        if (i == 1) {
            quickTimeSubtitleDirectory.setString(6, "Bold");
        } else if (i == 2) {
            quickTimeSubtitleDirectory.setString(6, "Italic");
        } else if (i == 4) {
            quickTimeSubtitleDirectory.setString(6, "Underline");
        }
        quickTimeSubtitleDirectory.setInt(7, subtitleSampleDescription.fontSize);
        quickTimeSubtitleDirectory.setIntArray(8, subtitleSampleDescription.foregroundColor);
    }
}
