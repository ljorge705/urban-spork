package com.rnmaps.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

/* loaded from: classes6.dex */
public class MapLocalTile extends MapFeature {
    private String pathTemplate;
    private TileOverlay tileOverlay;
    private TileOverlayOptions tileOverlayOptions;
    private AIRMapLocalTileProvider tileProvider;
    private float tileSize;
    private boolean useAssets;
    private float zIndex;

    @Override // com.rnmaps.maps.MapFeature
    public Object getFeature() {
        return this.tileOverlay;
    }

    public void setUseAssets(boolean z) {
        this.useAssets = z;
    }

    class AIRMapLocalTileProvider implements TileProvider {
        private static final int BUFFER_SIZE = 16384;
        private String pathTemplate;
        private int tileSize;
        private final boolean useAssets;

        public void setPathTemplate(String str) {
            this.pathTemplate = str;
        }

        public void setTileSize(int i) {
            this.tileSize = i;
        }

        public AIRMapLocalTileProvider(int i, String str, boolean z) {
            this.tileSize = i;
            this.pathTemplate = str;
            this.useAssets = z;
        }

        @Override // com.google.android.gms.maps.model.TileProvider
        public Tile getTile(int i, int i2, int i3) throws Throwable {
            byte[] tileImage = readTileImage(i, i2, i3);
            if (tileImage == null) {
                return TileProvider.NO_TILE;
            }
            int i4 = this.tileSize;
            return new Tile(i4, i4, tileImage);
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private byte[] readTileImage(int r7, int r8, int r9) throws java.lang.Throwable {
            /*
                r6 = this;
                java.lang.String r7 = r6.getTileFilename(r7, r8, r9)
                r8 = 0
                boolean r9 = r6.useAssets     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                if (r9 == 0) goto L18
                com.rnmaps.maps.MapLocalTile r9 = com.rnmaps.maps.MapLocalTile.this     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                android.content.Context r9 = r9.getContext()     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                android.content.res.AssetManager r9 = r9.getAssets()     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                java.io.InputStream r7 = r9.open(r7)     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                goto L1e
            L18:
                java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                r9.<init>(r7)     // Catch: java.lang.Throwable -> L5e java.lang.OutOfMemoryError -> L61 java.io.IOException -> L63
                r7 = r9
            L1e:
                java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L50 java.lang.OutOfMemoryError -> L56 java.io.IOException -> L58
                r9.<init>()     // Catch: java.lang.Throwable -> L50 java.lang.OutOfMemoryError -> L56 java.io.IOException -> L58
                r0 = 16384(0x4000, float:2.2959E-41)
                byte[] r1 = new byte[r0]     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            L27:
                r2 = 0
                int r3 = r7.read(r1, r2, r0)     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                r4 = -1
                if (r3 == r4) goto L33
                r9.write(r1, r2, r3)     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                goto L27
            L33:
                r9.flush()     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                byte[] r8 = r9.toByteArray()     // Catch: java.lang.Throwable -> L43 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
                if (r7 == 0) goto L3f
                r7.close()     // Catch: java.lang.Exception -> L3f
            L3f:
                r9.close()     // Catch: java.lang.Exception -> L42
            L42:
                return r8
            L43:
                r8 = move-exception
                r5 = r8
                r8 = r7
                r7 = r5
                goto L77
            L48:
                r0 = move-exception
                goto L4b
            L4a:
                r0 = move-exception
            L4b:
                r5 = r9
                r9 = r7
                r7 = r0
                r0 = r5
                goto L66
            L50:
                r9 = move-exception
                r5 = r8
                r8 = r7
                r7 = r9
                r9 = r5
                goto L77
            L56:
                r9 = move-exception
                goto L59
            L58:
                r9 = move-exception
            L59:
                r0 = r8
                r5 = r9
                r9 = r7
                r7 = r5
                goto L66
            L5e:
                r7 = move-exception
                r9 = r8
                goto L77
            L61:
                r7 = move-exception
                goto L64
            L63:
                r7 = move-exception
            L64:
                r9 = r8
                r0 = r9
            L66:
                r7.printStackTrace()     // Catch: java.lang.Throwable -> L74
                if (r9 == 0) goto L6e
                r9.close()     // Catch: java.lang.Exception -> L6e
            L6e:
                if (r0 == 0) goto L73
                r0.close()     // Catch: java.lang.Exception -> L73
            L73:
                return r8
            L74:
                r7 = move-exception
                r8 = r9
                r9 = r0
            L77:
                if (r8 == 0) goto L7c
                r8.close()     // Catch: java.lang.Exception -> L7c
            L7c:
                if (r9 == 0) goto L81
                r9.close()     // Catch: java.lang.Exception -> L81
            L81:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.rnmaps.maps.MapLocalTile.AIRMapLocalTileProvider.readTileImage(int, int, int):byte[]");
        }

        private String getTileFilename(int i, int i2, int i3) {
            return this.pathTemplate.replace("{x}", Integer.toString(i)).replace("{y}", Integer.toString(i2)).replace("{z}", Integer.toString(i3));
        }
    }

    public MapLocalTile(Context context) {
        super(context);
    }

    public void setPathTemplate(String str) {
        this.pathTemplate = str;
        AIRMapLocalTileProvider aIRMapLocalTileProvider = this.tileProvider;
        if (aIRMapLocalTileProvider != null) {
            aIRMapLocalTileProvider.setPathTemplate(str);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f);
        }
    }

    public void setTileSize(float f) {
        this.tileSize = f;
        AIRMapLocalTileProvider aIRMapLocalTileProvider = this.tileProvider;
        if (aIRMapLocalTileProvider != null) {
            aIRMapLocalTileProvider.setTileSize((int) f);
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.tileOverlayOptions == null) {
            this.tileOverlayOptions = createTileOverlayOptions();
        }
        return this.tileOverlayOptions;
    }

    private TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        AIRMapLocalTileProvider aIRMapLocalTileProvider = new AIRMapLocalTileProvider((int) this.tileSize, this.pathTemplate, this.useAssets);
        this.tileProvider = aIRMapLocalTileProvider;
        tileOverlayOptions.tileProvider(aIRMapLocalTileProvider);
        return tileOverlayOptions;
    }

    @Override // com.rnmaps.maps.MapFeature
    public void addToMap(Object obj) {
        this.tileOverlay = ((GoogleMap) obj).addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.rnmaps.maps.MapFeature
    public void removeFromMap(Object obj) {
        this.tileOverlay.remove();
    }
}
