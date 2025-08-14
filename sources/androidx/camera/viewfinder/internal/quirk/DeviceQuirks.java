package androidx.camera.viewfinder.internal.quirk;

/* loaded from: classes.dex */
public class DeviceQuirks {
    private static final Quirks QUIRKS = new Quirks(DeviceQuirksLoader.loadQuirks());

    private DeviceQuirks() {
    }

    public static <T extends Quirk> T get(Class<T> cls) {
        return (T) QUIRKS.get(cls);
    }
}
