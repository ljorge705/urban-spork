package com.drew.imaging.heif;

import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.boxes.Box;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;

/* loaded from: classes5.dex */
public class HeifReader {
    public void extract(Metadata metadata, InputStream inputStream, HeifHandler heifHandler) throws DataFormatException, IOException {
        StreamReader streamReader = new StreamReader(inputStream);
        streamReader.setMotorolaByteOrder(true);
        processBoxes(streamReader, -1L, heifHandler);
    }

    private void processBoxes(StreamReader streamReader, long j, HeifHandler heifHandler) {
        while (true) {
            if (j != -1) {
                try {
                    if (streamReader.getPosition() >= j) {
                        return;
                    }
                } catch (IOException unused) {
                    return;
                }
            }
            Box box = new Box(streamReader);
            if (heifHandler.shouldAcceptContainer(box)) {
                heifHandler.processContainer(box, streamReader);
                processBoxes(streamReader, (box.size + streamReader.getPosition()) - 8, heifHandler);
            } else if (heifHandler.shouldAcceptBox(box)) {
                heifHandler = heifHandler.processBox(box, streamReader.getBytes(((int) box.size) - 8));
            } else if (box.size > 1) {
                streamReader.skip(box.size - 8);
            } else if (box.size == -1) {
                return;
            }
        }
    }
}
