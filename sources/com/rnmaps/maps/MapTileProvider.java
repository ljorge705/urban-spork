package com.rnmaps.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class MapTileProvider implements TileProvider {
    protected static final int BUFFER_SIZE = 16384;
    protected static final int TARGET_TILE_SIZE = 512;
    protected Context context;
    protected boolean customMode;
    protected boolean doubleTileSize;
    protected boolean flipY;
    protected int maximumNativeZ;
    protected int maximumZ;
    protected int minimumZ;
    protected boolean offlineMode;
    protected int tileCacheMaxAge;
    protected String tileCachePath;
    protected UrlTileProvider tileProvider;
    protected int tileSize;
    protected String urlTemplate;

    public void setCustomMode() {
    }

    public void setDoubleTileSize(boolean z) {
        this.doubleTileSize = z;
    }

    public void setFlipY(boolean z) {
        this.flipY = z;
    }

    public void setMaximumNativeZ(int i) {
        this.maximumNativeZ = i;
    }

    public void setMaximumZ(int i) {
        this.maximumZ = i;
    }

    public void setMinimumZ(int i) {
        this.minimumZ = i;
    }

    public void setOfflineMode(boolean z) {
        this.offlineMode = z;
    }

    public void setTileCacheMaxAge(int i) {
        this.tileCacheMaxAge = i;
    }

    public void setTileCachePath(String str) {
        this.tileCachePath = str;
    }

    class AIRMapUrlTileProvider extends UrlTileProvider {
        private String urlTemplate;

        public void setUrlTemplate(String str) {
            this.urlTemplate = str;
        }

        public AIRMapUrlTileProvider(int i, int i2, String str) {
            super(i, i2);
            this.urlTemplate = str;
        }

        @Override // com.google.android.gms.maps.model.UrlTileProvider
        public URL getTileUrl(int i, int i2, int i3) {
            if (MapTileProvider.this.flipY) {
                i2 = ((1 << i3) - i2) - 1;
            }
            String strReplace = this.urlTemplate.replace("{x}", Integer.toString(i)).replace("{y}", Integer.toString(i2)).replace("{z}", Integer.toString(i3));
            if (MapTileProvider.this.maximumZ > 0 && i3 > MapTileProvider.this.maximumZ) {
                return null;
            }
            if (MapTileProvider.this.minimumZ > 0 && i3 < MapTileProvider.this.minimumZ) {
                return null;
            }
            try {
                return new URL(strReplace);
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
        }
    }

    public MapTileProvider(int i, boolean z, String str, int i2, int i3, int i4, boolean z2, String str2, int i5, boolean z3, Context context, boolean z4) {
        this.tileProvider = new AIRMapUrlTileProvider(i, i, str);
        this.tileSize = i;
        this.doubleTileSize = z;
        this.urlTemplate = str;
        this.maximumZ = i2;
        this.maximumNativeZ = i3;
        this.minimumZ = i4;
        this.flipY = z2;
        this.tileCachePath = str2;
        this.tileCacheMaxAge = i5;
        this.offlineMode = z3;
        this.context = context;
        this.customMode = z4;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public Tile getTile(int i, int i2, int i3) throws Throwable {
        byte[] bArrScaleLowerZoomTile;
        int i4;
        if (!this.customMode) {
            return this.tileProvider.getTile(i, i2, i3);
        }
        int i5 = this.maximumZ;
        if (i5 <= 0) {
            i5 = Integer.MAX_VALUE;
        }
        if (this.tileSize != 256 || !this.doubleTileSize || (i4 = i3 + 1) > this.maximumNativeZ || i4 > i5) {
            bArrScaleLowerZoomTile = null;
        } else {
            Log.d("urlTile", "pullTilesFromHigherZoom");
            bArrScaleLowerZoomTile = pullTilesFromHigherZoom(i, i2, i3);
        }
        if (i3 > this.maximumNativeZ) {
            Log.d("urlTile", "scaleLowerZoomTile");
            bArrScaleLowerZoomTile = scaleLowerZoomTile(i, i2, i3, this.maximumNativeZ);
        }
        if (bArrScaleLowerZoomTile == null && i3 <= i5) {
            Log.d("urlTile", "getTileImage");
            bArrScaleLowerZoomTile = getTileImage(i, i2, i3);
        }
        if (bArrScaleLowerZoomTile == null && this.tileCachePath != null && this.offlineMode) {
            Log.d("urlTile", "findLowerZoomTileForScaling");
            int i6 = this.maximumNativeZ;
            int iMax = Math.max(this.minimumZ, i3 - 3);
            for (int i7 = i3 > i6 ? i6 - 1 : i3 - 1; i7 >= iMax; i7--) {
                bArrScaleLowerZoomTile = scaleLowerZoomTile(i, i2, i3, i7);
                if (bArrScaleLowerZoomTile != null) {
                    break;
                }
            }
        }
        if (bArrScaleLowerZoomTile == null) {
            return null;
        }
        int i8 = this.tileSize;
        return new Tile(i8, i8, bArrScaleLowerZoomTile);
    }

    byte[] getTileImage(int i, int i2, int i3) throws Throwable {
        byte[] bArrFetchTile;
        if (this.tileCachePath != null) {
            bArrFetchTile = readTileImage(i, i2, i3);
            if (bArrFetchTile != null) {
                Log.d("urlTile", "tile cache HIT for " + i3 + "/" + i + "/" + i2);
            } else {
                Log.d("urlTile", "tile cache MISS for " + i3 + "/" + i + "/" + i2);
            }
            if (bArrFetchTile != null && !this.offlineMode) {
                checkForRefresh(i, i2, i3);
            }
        } else {
            bArrFetchTile = null;
        }
        if (bArrFetchTile == null && !this.offlineMode && this.tileCachePath != null) {
            String tileFilename = getTileFilename(i, i2, i3);
            OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(MapTileWorker.class).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).addTag(tileFilename).setInputData(new Data.Builder().putString("url", getTileUrl(i, i2, i3).toString()).putString("filename", tileFilename).putInt("maxAge", -1).build()).build();
            WorkManager workManager = WorkManager.getInstance(this.context.getApplicationContext());
            try {
                workManager.enqueueUniqueWork(tileFilename, ExistingWorkPolicy.KEEP, oneTimeWorkRequestBuild).getResult().get(1L, TimeUnit.SECONDS);
                Thread.sleep(500L);
                Log.d("urlTile: ", workManager.getWorkInfosByTag(tileFilename).get(1L, TimeUnit.SECONDS).get(0).toString());
                if (this.tileCachePath != null) {
                    bArrFetchTile = readTileImage(i, i2, i3);
                    if (bArrFetchTile != null) {
                        Log.d("urlTile", "tile cache fetch HIT for " + i3 + "/" + i + "/" + i2);
                    } else {
                        Log.d("urlTile", "tile cache fetch MISS for " + i3 + "/" + i + "/" + i2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (bArrFetchTile == null && !this.offlineMode) {
            Log.d("urlTile", "Normal fetch");
            bArrFetchTile = fetchTile(i, i2, i3);
            if (bArrFetchTile == null) {
                Log.d("urlTile", "tile fetch TIMEOUT / FAIL for " + i3 + "/" + i + "/" + i2);
            }
        }
        return bArrFetchTile;
    }

    byte[] pullTilesFromHigherZoom(int i, int i2, int i3) throws Throwable {
        Bitmap newBitmap = getNewBitmap();
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        int i4 = i * 2;
        int i5 = i2 * 2;
        int i6 = i3 + 1;
        byte[] tileImage = getTileImage(i4, i5, i6);
        int i7 = i5 + 1;
        byte[] tileImage2 = getTileImage(i4, i7, i6);
        int i8 = i4 + 1;
        byte[] tileImage3 = getTileImage(i8, i5, i6);
        byte[] tileImage4 = getTileImage(i8, i7, i6);
        if (tileImage == null || tileImage2 == null || tileImage3 == null || tileImage4 == null) {
            return null;
        }
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(tileImage, 0, tileImage.length);
        canvas.drawBitmap(bitmapDecodeByteArray, 0.0f, 0.0f, paint);
        bitmapDecodeByteArray.recycle();
        Bitmap bitmapDecodeByteArray2 = BitmapFactory.decodeByteArray(tileImage2, 0, tileImage2.length);
        canvas.drawBitmap(bitmapDecodeByteArray2, 0.0f, 256.0f, paint);
        bitmapDecodeByteArray2.recycle();
        Bitmap bitmapDecodeByteArray3 = BitmapFactory.decodeByteArray(tileImage3, 0, tileImage3.length);
        canvas.drawBitmap(bitmapDecodeByteArray3, 256.0f, 0.0f, paint);
        bitmapDecodeByteArray3.recycle();
        Bitmap bitmapDecodeByteArray4 = BitmapFactory.decodeByteArray(tileImage4, 0, tileImage4.length);
        canvas.drawBitmap(bitmapDecodeByteArray4, 256.0f, 256.0f, paint);
        bitmapDecodeByteArray4.recycle();
        byte[] bArrBitmapToByteArray = bitmapToByteArray(newBitmap);
        newBitmap.recycle();
        return bArrBitmapToByteArray;
    }

    Bitmap getNewBitmap() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.eraseColor(0);
        return bitmapCreateBitmap;
    }

    byte[] bitmapToByteArray(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    byte[] scaleLowerZoomTile(int i, int i2, int i3, int i4) throws Throwable {
        int i5 = i3 - i4;
        int i6 = 1 << i5;
        int i7 = i >> i5;
        int i8 = i2 >> i5;
        int i9 = i3 - i5;
        int i10 = i % i6;
        int i11 = i2 % i6;
        Bitmap newBitmap = getNewBitmap();
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        byte[] tileImage = getTileImage(i7, i8, i9);
        if (tileImage == null) {
            return null;
        }
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(tileImage, 0, tileImage.length);
        int i12 = this.tileSize / i6;
        int i13 = i10 * i12;
        int i14 = i11 * i12;
        canvas.drawBitmap(bitmapDecodeByteArray, new Rect(i13, i14, i13 + i12, i12 + i14), new Rect(0, 0, 512, 512), paint);
        bitmapDecodeByteArray.recycle();
        byte[] bArrBitmapToByteArray = bitmapToByteArray(newBitmap);
        newBitmap.recycle();
        return bArrBitmapToByteArray;
    }

    void checkForRefresh(int i, int i2, int i3) {
        String tileFilename = getTileFilename(i, i2, i3);
        if ((System.currentTimeMillis() - new File(tileFilename).lastModified()) / 1000 > this.tileCacheMaxAge) {
            Log.d("urlTile", "Refreshing");
            WorkManager.getInstance(this.context.getApplicationContext()).enqueueUniqueWork(tileFilename, ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(MapTileWorker.class).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).addTag(tileFilename).setInputData(new Data.Builder().putString("url", getTileUrl(i, i2, i3).toString()).putString("filename", tileFilename).putInt("maxAge", this.tileCacheMaxAge).build()).build());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    byte[] fetchTile(int r7, int r8, int r9) throws java.lang.Throwable {
        /*
            r6 = this;
            java.net.URL r7 = r6.getTileUrl(r7, r8, r9)
            r8 = 0
            java.net.URLConnection r7 = r7.openConnection()     // Catch: java.lang.Throwable -> L40 java.lang.OutOfMemoryError -> L45 java.io.IOException -> L47
            java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L40 java.lang.OutOfMemoryError -> L45 java.io.IOException -> L47
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L36 java.lang.OutOfMemoryError -> L3b java.io.IOException -> L3d
            r9.<init>()     // Catch: java.lang.Throwable -> L36 java.lang.OutOfMemoryError -> L3b java.io.IOException -> L3d
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r0]     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L58
        L16:
            r2 = 0
            int r3 = r7.read(r1, r2, r0)     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L58
            r4 = -1
            if (r3 == r4) goto L22
            r9.write(r1, r2, r3)     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L58
            goto L16
        L22:
            r9.flush()     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L58
            byte[] r8 = r9.toByteArray()     // Catch: java.lang.OutOfMemoryError -> L32 java.io.IOException -> L34 java.lang.Throwable -> L58
            if (r7 == 0) goto L2e
            r7.close()     // Catch: java.lang.Exception -> L2e
        L2e:
            r9.close()     // Catch: java.lang.Exception -> L31
        L31:
            return r8
        L32:
            r0 = move-exception
            goto L4a
        L34:
            r0 = move-exception
            goto L4a
        L36:
            r9 = move-exception
            r5 = r9
            r9 = r8
            r8 = r5
            goto L59
        L3b:
            r0 = move-exception
            goto L3e
        L3d:
            r0 = move-exception
        L3e:
            r9 = r8
            goto L4a
        L40:
            r7 = move-exception
            r9 = r8
            r8 = r7
            r7 = r9
            goto L59
        L45:
            r0 = move-exception
            goto L48
        L47:
            r0 = move-exception
        L48:
            r7 = r8
            r9 = r7
        L4a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L58
            if (r7 == 0) goto L52
            r7.close()     // Catch: java.lang.Exception -> L52
        L52:
            if (r9 == 0) goto L57
            r9.close()     // Catch: java.lang.Exception -> L57
        L57:
            return r8
        L58:
            r8 = move-exception
        L59:
            if (r7 == 0) goto L5e
            r7.close()     // Catch: java.lang.Exception -> L5e
        L5e:
            if (r9 == 0) goto L63
            r9.close()     // Catch: java.lang.Exception -> L63
        L63:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnmaps.maps.MapTileProvider.fetchTile(int, int, int):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.FileInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    byte[] readTileImage(int r7, int r8, int r9) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r7 = r6.getTileFilename(r7, r8, r9)
            r8 = 0
            if (r7 != 0) goto L8
            return r8
        L8:
            java.io.File r9 = new java.io.File
            r9.<init>(r7)
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d java.lang.OutOfMemoryError -> L52 java.io.IOException -> L54
            r7.<init>(r9)     // Catch: java.lang.Throwable -> L4d java.lang.OutOfMemoryError -> L52 java.io.IOException -> L54
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L44 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            r0.<init>()     // Catch: java.lang.Throwable -> L44 java.lang.OutOfMemoryError -> L48 java.io.IOException -> L4a
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r1]     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
        L1b:
            r3 = 0
            int r4 = r7.read(r2, r3, r1)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            r5 = -1
            if (r4 == r5) goto L27
            r0.write(r2, r3, r4)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            goto L1b
        L27:
            r0.flush()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            int r1 = r6.tileCacheMaxAge     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            if (r1 != 0) goto L35
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            r9.setLastModified(r1)     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
        L35:
            byte[] r8 = r0.toByteArray()     // Catch: java.lang.OutOfMemoryError -> L40 java.io.IOException -> L42 java.lang.Throwable -> L65
            r7.close()     // Catch: java.lang.Exception -> L3c
        L3c:
            r0.close()     // Catch: java.lang.Exception -> L3f
        L3f:
            return r8
        L40:
            r9 = move-exception
            goto L57
        L42:
            r9 = move-exception
            goto L57
        L44:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L66
        L48:
            r9 = move-exception
            goto L4b
        L4a:
            r9 = move-exception
        L4b:
            r0 = r8
            goto L57
        L4d:
            r7 = move-exception
            r0 = r8
            r8 = r7
            r7 = r0
            goto L66
        L52:
            r9 = move-exception
            goto L55
        L54:
            r9 = move-exception
        L55:
            r7 = r8
            r0 = r7
        L57:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L65
            if (r7 == 0) goto L5f
            r7.close()     // Catch: java.lang.Exception -> L5f
        L5f:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.lang.Exception -> L64
        L64:
            return r8
        L65:
            r8 = move-exception
        L66:
            if (r7 == 0) goto L6b
            r7.close()     // Catch: java.lang.Exception -> L6b
        L6b:
            if (r0 == 0) goto L70
            r0.close()     // Catch: java.lang.Exception -> L70
        L70:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnmaps.maps.MapTileProvider.readTileImage(int, int, int):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean writeTileImage(byte[] r2, int r3, int r4, int r5) throws java.lang.Throwable {
        /*
            r1 = this;
            java.lang.String r3 = r1.getTileFilename(r3, r4, r5)
            r4 = 0
            if (r3 != 0) goto L8
            return r4
        L8:
            r5 = 0
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            java.io.File r3 = r0.getParentFile()     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.mkdirs()     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L2a java.lang.OutOfMemoryError -> L2c java.io.IOException -> L2e
            r3.write(r2)     // Catch: java.lang.Throwable -> L22 java.lang.OutOfMemoryError -> L25 java.io.IOException -> L27
            r3.close()     // Catch: java.lang.Exception -> L20
        L20:
            r2 = 1
            return r2
        L22:
            r2 = move-exception
            r5 = r3
            goto L38
        L25:
            r2 = move-exception
            goto L28
        L27:
            r2 = move-exception
        L28:
            r5 = r3
            goto L2f
        L2a:
            r2 = move-exception
            goto L38
        L2c:
            r2 = move-exception
            goto L2f
        L2e:
            r2 = move-exception
        L2f:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L2a
            if (r5 == 0) goto L37
            r5.close()     // Catch: java.lang.Exception -> L37
        L37:
            return r4
        L38:
            if (r5 == 0) goto L3d
            r5.close()     // Catch: java.lang.Exception -> L3d
        L3d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnmaps.maps.MapTileProvider.writeTileImage(byte[], int, int, int):boolean");
    }

    String getTileFilename(int i, int i2, int i3) {
        if (this.tileCachePath == null) {
            return null;
        }
        return this.tileCachePath + '/' + i3 + "/" + i + "/" + i2;
    }

    protected URL getTileUrl(int i, int i2, int i3) {
        return this.tileProvider.getTileUrl(i, i2, i3);
    }

    public void setUrlTemplate(String str) {
        if (this.urlTemplate != str) {
            int i = this.tileSize;
            this.tileProvider = new AIRMapUrlTileProvider(i, i, str);
        }
        this.urlTemplate = str;
    }

    public void setTileSize(int i) {
        if (this.tileSize != i) {
            this.tileProvider = new AIRMapUrlTileProvider(i, i, this.urlTemplate);
        }
        this.tileSize = i;
    }
}
