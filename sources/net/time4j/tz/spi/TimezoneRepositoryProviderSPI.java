package net.time4j.tz.spi;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.time4j.PlainDate;
import net.time4j.base.GregorianDate;
import net.time4j.scale.LeapSecondProvider;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZoneModelProvider;
import net.time4j.tz.ZoneNameProvider;

/* loaded from: classes4.dex */
public class TimezoneRepositoryProviderSPI implements ZoneModelProvider, LeapSecondProvider {
    private final Map<String, String> aliases;
    private final Map<String, byte[]> data;
    private final PlainDate expires;
    private final Map<GregorianDate, Integer> leapsecs;
    private final String location;
    private final String version;

    @Override // net.time4j.tz.ZoneModelProvider
    public Map<String, String> getAliases() {
        return this.aliases;
    }

    @Override // net.time4j.scale.LeapSecondProvider
    public PlainDate getDateOfExpiration() {
        return this.expires;
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getFallback() {
        return "";
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getLocation() {
        return this.location;
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getName() {
        return "TZDB";
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public ZoneNameProvider getSpecificZoneNameRepository() {
        return null;
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getVersion() {
        return this.version;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0214 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TimezoneRepositoryProviderSPI() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.tz.spi.TimezoneRepositoryProviderSPI.<init>():void");
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public Set<String> getAvailableIDs() {
        return this.data.keySet();
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public TransitionHistory load(String str) {
        try {
            byte[] bArr = this.data.get(str);
            if (bArr != null) {
                return (TransitionHistory) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // net.time4j.scale.LeapSecondProvider
    public Map<GregorianDate, Integer> getLeapSecondTable() {
        return Collections.unmodifiableMap(this.leapsecs);
    }

    @Override // net.time4j.scale.LeapSecondProvider
    public boolean supportsNegativeLS() {
        return !this.leapsecs.isEmpty();
    }

    @Override // net.time4j.scale.LeapSecondProvider
    public PlainDate getDateOfEvent(int i, int i2, int i3) {
        return PlainDate.of(i, i2, i3);
    }

    public String toString() {
        return "TZ-REPOSITORY(" + this.version + ")";
    }

    private static void checkMagicLabel(DataInputStream dataInputStream, String str) throws IOException {
        byte b = dataInputStream.readByte();
        byte b2 = dataInputStream.readByte();
        byte b3 = dataInputStream.readByte();
        byte b4 = dataInputStream.readByte();
        byte b5 = dataInputStream.readByte();
        byte b6 = dataInputStream.readByte();
        if (b != 116 || b2 != 122 || b3 != 114 || b4 != 101 || b5 != 112 || b6 != 111) {
            throw new IOException("Invalid tz-repository: " + str);
        }
    }

    private static Class<?> getReference() {
        if (!Boolean.getBoolean("test.environment")) {
            return TimezoneRepositoryProviderSPI.class;
        }
        try {
            return Class.forName("net.time4j.tz.spi.RepositoryTest");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }
}
