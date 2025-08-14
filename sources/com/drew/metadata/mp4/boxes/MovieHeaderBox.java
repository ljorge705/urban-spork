package com.drew.metadata.mp4.boxes;

import com.drew.lang.Rational;
import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4Directory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes5.dex */
public class MovieHeaderBox extends FullBox {
    protected long creationTime;
    protected long duration;
    protected int[] matrix;
    protected long modificationTime;
    protected long nextTrackID;
    protected int rate;
    protected long timescale;
    protected int volume;

    public MovieHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        if (this.version == 1) {
            this.creationTime = sequentialReader.getInt64();
            this.modificationTime = sequentialReader.getInt64();
            this.timescale = sequentialReader.getUInt32();
            this.duration = sequentialReader.getInt64();
        } else {
            this.creationTime = sequentialReader.getUInt32();
            this.modificationTime = sequentialReader.getUInt32();
            this.timescale = sequentialReader.getUInt32();
            this.duration = sequentialReader.getUInt32();
        }
        this.rate = sequentialReader.getInt32();
        this.volume = sequentialReader.getInt16();
        sequentialReader.skip(2L);
        sequentialReader.skip(8L);
        this.matrix = new int[]{sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32(), sequentialReader.getInt32()};
        sequentialReader.skip(24L);
        this.nextTrackID = sequentialReader.getUInt32();
    }

    public void addMetadata(Mp4Directory mp4Directory) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1904, 0, 1, 0, 0, 0);
        long time = calendar.getTime().getTime();
        mp4Directory.setDate(256, new Date((this.creationTime * 1000) + time));
        mp4Directory.setDate(257, new Date((this.modificationTime * 1000) + time));
        mp4Directory.setLong(259, this.duration);
        mp4Directory.setLong(258, this.timescale);
        mp4Directory.setRational(260, new Rational(this.duration, this.timescale));
        mp4Directory.setIntArray(271, this.matrix);
        int i = this.rate;
        mp4Directory.setDouble(261, (((-65536) & i) >> 16) + ((i & 65535) / Math.pow(2.0d, 4.0d)));
        int i2 = this.volume;
        mp4Directory.setDouble(262, ((65280 & i2) >> 8) + ((i2 & 255) / Math.pow(2.0d, 2.0d)));
        mp4Directory.setLong(270, this.nextTrackID);
    }
}
