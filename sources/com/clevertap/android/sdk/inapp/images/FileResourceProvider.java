package com.clevertap.android.sdk.inapp.images;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.memory.FileMemoryAccessObject;
import com.clevertap.android.sdk.inapp.images.memory.InAppGifMemoryAccessObjectV1;
import com.clevertap.android.sdk.inapp.images.memory.InAppImageMemoryAccessObjectV1;
import com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject;
import com.clevertap.android.sdk.inapp.images.memory.MemoryCreator;
import com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImplKt;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.utils.CTCaches;
import io.sentry.protocol.DebugMeta;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileResourceProvider.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 ?2\u00020\u0001:\u0001?B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B[\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0012\u0010!\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0012\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010$\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0002J9\u0010*\u001a\u0004\u0018\u0001H+\"\u0004\b\u0000\u0010+2\u0014\u0010,\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0004\u0012\u00020\u00180'2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H+0.H\u0002¢\u0006\u0002\u0010/Jo\u00100\u001a\u0004\u0018\u0001H+\"\u0004\b\u0000\u0010+2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00180'2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H+0\u001a2\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u0001H+042 \u00105\u001a\u001c\u0012\u0004\u0012\u00020)\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020\u001c\u0018\u00010'04H\u0002¢\u0006\u0002\u00106J\u0010\u00107\u001a\u0004\u0018\u00010\u001c2\u0006\u00108\u001a\u00020\u001eJ\u0010\u00109\u001a\u0004\u0018\u00010\u001c2\u0006\u00108\u001a\u00020\u001eJ\u0010\u0010:\u001a\u0004\u0018\u00010#2\u0006\u00108\u001a\u00020\u001eJ\u000e\u0010;\u001a\u00020<2\u0006\u00108\u001a\u00020\u001eJ8\u0010=\u001a\u00020%\"\u0004\b\u0000\u0010+2\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020\u001c0'2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H+0\u001aH\u0002R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u00190\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "", "context", "Landroid/content/Context;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "(Landroid/content/Context;Lcom/clevertap/android/sdk/ILogger;)V", DebugMeta.JsonKeys.IMAGES, "Ljava/io/File;", "gifs", "allFileTypesDir", "inAppRemoteSource", "Lcom/clevertap/android/sdk/inapp/images/FileFetchApiContract;", "ctCaches", "Lcom/clevertap/android/sdk/utils/CTCaches;", "imageMAO", "Lcom/clevertap/android/sdk/inapp/images/memory/InAppImageMemoryAccessObjectV1;", "gifMAO", "Lcom/clevertap/android/sdk/inapp/images/memory/InAppGifMemoryAccessObjectV1;", "fileMAO", "Lcom/clevertap/android/sdk/inapp/images/memory/FileMemoryAccessObject;", "(Ljava/io/File;Ljava/io/File;Ljava/io/File;Lcom/clevertap/android/sdk/ILogger;Lcom/clevertap/android/sdk/inapp/images/FileFetchApiContract;Lcom/clevertap/android/sdk/utils/CTCaches;Lcom/clevertap/android/sdk/inapp/images/memory/InAppImageMemoryAccessObjectV1;Lcom/clevertap/android/sdk/inapp/images/memory/InAppGifMemoryAccessObjectV1;Lcom/clevertap/android/sdk/inapp/images/memory/FileMemoryAccessObject;)V", "mapOfMAO", "", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryAccessObject;", "cachedFileInBytes", "", "cacheKey", "", "cachedFileInstance", "cachedFilePath", "cachedInAppGifV1", "cachedInAppImageV1", "Landroid/graphics/Bitmap;", "deleteData", "", "downloadedBytesFromApi", "Lkotlin/Pair;", "downloadedBitmap", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "fetchCachedData", ExifInterface.GPS_DIRECTION_TRUE, "cacheKeyAndType", "transformationType", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "(Lkotlin/Pair;Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;)Ljava/lang/Object;", "fetchData", "urlMeta", "mao", "cachedDataFetcherBlock", "Lkotlin/Function1;", "dataToSaveBlock", "(Lkotlin/Pair;Lcom/clevertap/android/sdk/inapp/images/memory/MemoryAccessObject;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "fetchFile", "url", "fetchInAppGifV1", "fetchInAppImageV1", "isFileCached", "", "saveData", "data", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FileResourceProvider {
    private static final String ALL_FILE_TYPES_DIRECTORY_NAME = "CleverTap.Files.";
    private static final String GIF_DIRECTORY_NAME = "CleverTap.Gif.";
    private static final String IMAGE_DIRECTORY_NAME = "CleverTap.Images.";
    private final FileMemoryAccessObject fileMAO;
    private final InAppGifMemoryAccessObjectV1 gifMAO;
    private final InAppImageMemoryAccessObjectV1 imageMAO;
    private final FileFetchApiContract inAppRemoteSource;
    private final ILogger logger;
    private final Map<CtCacheType, List<MemoryAccessObject<?>>> mapOfMAO;

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadedBitmap.Status.values().length];
            try {
                iArr[DownloadedBitmap.Status.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FileResourceProvider(File images, File gifs, File allFileTypesDir, ILogger iLogger, FileFetchApiContract inAppRemoteSource, CTCaches ctCaches, InAppImageMemoryAccessObjectV1 imageMAO, InAppGifMemoryAccessObjectV1 gifMAO, FileMemoryAccessObject fileMAO) {
        Intrinsics.checkNotNullParameter(images, "images");
        Intrinsics.checkNotNullParameter(gifs, "gifs");
        Intrinsics.checkNotNullParameter(allFileTypesDir, "allFileTypesDir");
        Intrinsics.checkNotNullParameter(inAppRemoteSource, "inAppRemoteSource");
        Intrinsics.checkNotNullParameter(ctCaches, "ctCaches");
        Intrinsics.checkNotNullParameter(imageMAO, "imageMAO");
        Intrinsics.checkNotNullParameter(gifMAO, "gifMAO");
        Intrinsics.checkNotNullParameter(fileMAO, "fileMAO");
        this.logger = iLogger;
        this.inAppRemoteSource = inAppRemoteSource;
        this.imageMAO = imageMAO;
        this.gifMAO = gifMAO;
        this.fileMAO = fileMAO;
        this.mapOfMAO = MapsKt.mapOf(TuplesKt.to(CtCacheType.IMAGE, CollectionsKt.listOf((Object[]) new MemoryAccessObject[]{imageMAO, fileMAO, gifMAO})), TuplesKt.to(CtCacheType.GIF, CollectionsKt.listOf((Object[]) new MemoryAccessObject[]{gifMAO, fileMAO, imageMAO})), TuplesKt.to(CtCacheType.FILES, CollectionsKt.listOf((Object[]) new MemoryAccessObject[]{fileMAO, imageMAO, gifMAO})));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FileResourceProvider(File file, File file2, File file3, ILogger iLogger, FileFetchApiContract fileFetchApiContract, CTCaches cTCaches, InAppImageMemoryAccessObjectV1 inAppImageMemoryAccessObjectV1, InAppGifMemoryAccessObjectV1 inAppGifMemoryAccessObjectV1, FileMemoryAccessObject fileMemoryAccessObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ILogger iLogger2 = (i & 8) != 0 ? null : iLogger;
        FileFetchApiContract fileFetchApi = (i & 16) != 0 ? new FileFetchApi() : fileFetchApiContract;
        CTCaches cTCachesInstance = (i & 32) != 0 ? CTCaches.INSTANCE.instance(MemoryCreator.INSTANCE.createInAppImageMemoryV1(file, iLogger2), MemoryCreator.INSTANCE.createInAppGifMemoryV1(file2, iLogger2), MemoryCreator.INSTANCE.createFileMemoryV2(file3, iLogger2)) : cTCaches;
        this(file, file2, file3, iLogger2, fileFetchApi, cTCachesInstance, (i & 64) != 0 ? new InAppImageMemoryAccessObjectV1(cTCachesInstance, iLogger2) : inAppImageMemoryAccessObjectV1, (i & 128) != 0 ? new InAppGifMemoryAccessObjectV1(cTCachesInstance, iLogger2) : inAppGifMemoryAccessObjectV1, (i & 256) != 0 ? new FileMemoryAccessObject(cTCachesInstance, iLogger2) : fileMemoryAccessObject);
    }

    public /* synthetic */ FileResourceProvider(Context context, ILogger iLogger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : iLogger);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FileResourceProvider(Context context, ILogger iLogger) {
        Intrinsics.checkNotNullParameter(context, "context");
        File dir = context.getDir(IMAGE_DIRECTORY_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(dir, "context.getDir(IMAGE_DIR…ME, Context.MODE_PRIVATE)");
        File dir2 = context.getDir(GIF_DIRECTORY_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(dir2, "context.getDir(GIF_DIREC…ME, Context.MODE_PRIVATE)");
        File dir3 = context.getDir(ALL_FILE_TYPES_DIRECTORY_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(dir3, "context.getDir(ALL_FILE_…ME, Context.MODE_PRIVATE)");
        this(dir, dir2, dir3, iLogger, null, null, null, null, null, 496, null);
    }

    private final <T> void saveData(String cacheKey, Pair<? extends T, byte[]> data, MemoryAccessObject<T> mao) {
        mao.saveInMemory(cacheKey, new Pair<>(data.getFirst(), mao.saveDiskMemory(cacheKey, data.getSecond())));
    }

    public final boolean isFileCached(String url) {
        Pair pairFetchInMemory;
        Intrinsics.checkNotNullParameter(url, "url");
        List<MemoryAccessObject<?>> list = this.mapOfMAO.get(CtCacheType.FILES);
        Serializable serializable = null;
        if (list != null) {
            List<MemoryAccessObject<?>> list2 = list;
            Iterator<T> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    pairFetchInMemory = null;
                    break;
                }
                pairFetchInMemory = ((MemoryAccessObject) it.next()).fetchInMemory(url);
                if (pairFetchInMemory != null) {
                    break;
                }
            }
            if (pairFetchInMemory != null) {
                serializable = pairFetchInMemory;
            } else {
                Iterator<T> it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    File fileFetchDiskMemory = ((MemoryAccessObject) it2.next()).fetchDiskMemory(url);
                    if (fileFetchDiskMemory != null) {
                        serializable = fileFetchDiskMemory;
                        break;
                    }
                }
                serializable = serializable;
            }
        }
        return serializable != null;
    }

    public final Bitmap cachedInAppImageV1(String cacheKey) {
        return (Bitmap) fetchCachedData(new Pair<>(cacheKey, CtCacheType.IMAGE), MemoryDataTransformationType.ToBitmap.INSTANCE);
    }

    public final byte[] cachedInAppGifV1(String cacheKey) {
        return (byte[]) fetchCachedData(new Pair<>(cacheKey, CtCacheType.GIF), MemoryDataTransformationType.ToByteArray.INSTANCE);
    }

    public final byte[] cachedFileInBytes(String cacheKey) {
        return (byte[]) fetchCachedData(new Pair<>(cacheKey, CtCacheType.FILES), MemoryDataTransformationType.ToByteArray.INSTANCE);
    }

    public final String cachedFilePath(String cacheKey) {
        File fileCachedFileInstance = cachedFileInstance(cacheKey);
        if (fileCachedFileInstance != null) {
            return fileCachedFileInstance.getAbsolutePath();
        }
        return null;
    }

    public final File cachedFileInstance(String cacheKey) {
        return (File) fetchCachedData(new Pair<>(cacheKey, CtCacheType.FILES), MemoryDataTransformationType.ToFile.INSTANCE);
    }

    public final Bitmap fetchInAppImageV1(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return (Bitmap) fetchData(new Pair<>(url, CtCacheType.IMAGE), this.imageMAO, new C05281(this), new Function1<DownloadedBitmap, Pair<? extends Bitmap, ? extends byte[]>>() { // from class: com.clevertap.android.sdk.inapp.images.FileResourceProvider.fetchInAppImageV1.2

            /* compiled from: FileResourceProvider.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchInAppImageV1$2$WhenMappings */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[DownloadedBitmap.Status.values().length];
                    try {
                        iArr[DownloadedBitmap.Status.SUCCESS.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public final Pair<Bitmap, byte[]> invoke(DownloadedBitmap downloadedBitmap) {
                Intrinsics.checkNotNullParameter(downloadedBitmap, "downloadedBitmap");
                if (WhenMappings.$EnumSwitchMapping$0[downloadedBitmap.getStatus().ordinal()] == 1) {
                    Bitmap bitmap = downloadedBitmap.getBitmap();
                    Intrinsics.checkNotNull(bitmap);
                    byte[] bytes = downloadedBitmap.getBytes();
                    Intrinsics.checkNotNull(bytes);
                    return new Pair<>(bitmap, bytes);
                }
                return null;
            }
        });
    }

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchInAppImageV1$1, reason: invalid class name and case insensitive filesystem */
    /* synthetic */ class C05281 extends FunctionReferenceImpl implements Function1<String, Bitmap> {
        C05281(Object obj) {
            super(1, obj, FileResourceProvider.class, "cachedInAppImageV1", "cachedInAppImageV1(Ljava/lang/String;)Landroid/graphics/Bitmap;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Bitmap invoke(String str) {
            return ((FileResourceProvider) this.receiver).cachedInAppImageV1(str);
        }
    }

    public final byte[] fetchInAppGifV1(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return (byte[]) fetchData(new Pair<>(url, CtCacheType.GIF), this.gifMAO, new C05261(this), new C05272(this));
    }

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchInAppGifV1$1, reason: invalid class name and case insensitive filesystem */
    /* synthetic */ class C05261 extends FunctionReferenceImpl implements Function1<String, byte[]> {
        C05261(Object obj) {
            super(1, obj, FileResourceProvider.class, "cachedInAppGifV1", "cachedInAppGifV1(Ljava/lang/String;)[B", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final byte[] invoke(String str) {
            return ((FileResourceProvider) this.receiver).cachedInAppGifV1(str);
        }
    }

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchInAppGifV1$2, reason: invalid class name and case insensitive filesystem */
    /* synthetic */ class C05272 extends FunctionReferenceImpl implements Function1<DownloadedBitmap, Pair<? extends byte[], ? extends byte[]>> {
        C05272(Object obj) {
            super(1, obj, FileResourceProvider.class, "downloadedBytesFromApi", "downloadedBytesFromApi(Lcom/clevertap/android/sdk/network/DownloadedBitmap;)Lkotlin/Pair;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Pair<byte[], byte[]> invoke(DownloadedBitmap p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((FileResourceProvider) this.receiver).downloadedBytesFromApi(p0);
        }
    }

    public final byte[] fetchFile(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return (byte[]) fetchData(new Pair<>(url, CtCacheType.FILES), this.fileMAO, new AnonymousClass1(this), new AnonymousClass2(this));
    }

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchFile$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, byte[]> {
        AnonymousClass1(Object obj) {
            super(1, obj, FileResourceProvider.class, "cachedFileInBytes", "cachedFileInBytes(Ljava/lang/String;)[B", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final byte[] invoke(String str) {
            return ((FileResourceProvider) this.receiver).cachedFileInBytes(str);
        }
    }

    /* compiled from: FileResourceProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.images.FileResourceProvider$fetchFile$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<DownloadedBitmap, Pair<? extends byte[], ? extends byte[]>> {
        AnonymousClass2(Object obj) {
            super(1, obj, FileResourceProvider.class, "downloadedBytesFromApi", "downloadedBytesFromApi(Lcom/clevertap/android/sdk/network/DownloadedBitmap;)Lkotlin/Pair;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Pair<byte[], byte[]> invoke(DownloadedBitmap p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((FileResourceProvider) this.receiver).downloadedBytesFromApi(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<byte[], byte[]> downloadedBytesFromApi(DownloadedBitmap downloadedBitmap) {
        if (WhenMappings.$EnumSwitchMapping$0[downloadedBitmap.getStatus().ordinal()] == 1) {
            byte[] bytes = downloadedBitmap.getBytes();
            Intrinsics.checkNotNull(bytes);
            return new Pair<>(bytes, downloadedBitmap.getBytes());
        }
        return null;
    }

    public final void deleteData(String cacheKey) {
        String str;
        ILogger iLogger;
        ILogger iLogger2;
        Intrinsics.checkNotNullParameter(cacheKey, "cacheKey");
        List<MemoryAccessObject<?>> list = this.mapOfMAO.get(CtCacheType.IMAGE);
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                MemoryAccessObject memoryAccessObject = (MemoryAccessObject) it.next();
                if (memoryAccessObject instanceof InAppImageMemoryAccessObjectV1) {
                    str = CtCacheType.IMAGE;
                } else if (memoryAccessObject instanceof InAppGifMemoryAccessObjectV1) {
                    str = CtCacheType.GIF;
                } else {
                    str = memoryAccessObject instanceof FileMemoryAccessObject ? CtCacheType.FILES : "";
                }
                if (memoryAccessObject.removeInMemory(cacheKey) != null && (iLogger2 = this.logger) != null) {
                    iLogger2.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, cacheKey + " was present in " + str + " in-memory cache is successfully removed");
                }
                if (memoryAccessObject.removeDiskMemory(cacheKey) && (iLogger = this.logger) != null) {
                    iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, cacheKey + " was present in " + str + " disk-memory cache is successfully removed");
                }
            }
        }
    }

    private final <T> T fetchCachedData(Pair<String, ? extends CtCacheType> cacheKeyAndType, MemoryDataTransformationType<T> transformationType) {
        Object objFetchInMemoryAndTransform;
        String first = cacheKeyAndType.getFirst();
        CtCacheType second = cacheKeyAndType.getSecond();
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, second.name() + " data for key " + first + " requested");
        }
        if (first == null) {
            ILogger iLogger2 = this.logger;
            if (iLogger2 != null) {
                iLogger2.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, second.name() + " data for null key requested");
            }
            return null;
        }
        List<MemoryAccessObject<?>> list = this.mapOfMAO.get(second);
        if (list == null) {
            return null;
        }
        List<MemoryAccessObject<?>> list2 = list;
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                objFetchInMemoryAndTransform = null;
                break;
            }
            objFetchInMemoryAndTransform = ((MemoryAccessObject) it.next()).fetchInMemoryAndTransform(first, transformationType);
            if (objFetchInMemoryAndTransform != null) {
                break;
            }
        }
        if (objFetchInMemoryAndTransform != null) {
            return (T) objFetchInMemoryAndTransform;
        }
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            T t = (T) ((MemoryAccessObject) it2.next()).fetchDiskMemoryAndTransform(first, transformationType);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    private final <T> T fetchData(Pair<String, ? extends CtCacheType> urlMeta, MemoryAccessObject<T> mao, Function1<? super String, ? extends T> cachedDataFetcherBlock, Function1<? super DownloadedBitmap, ? extends Pair<? extends T, byte[]>> dataToSaveBlock) {
        T tInvoke = cachedDataFetcherBlock.invoke(urlMeta.getFirst());
        if (tInvoke != null) {
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "Returning requested " + urlMeta.getFirst() + ' ' + urlMeta.getSecond().name() + " from cache");
            }
            return tInvoke;
        }
        DownloadedBitmap downloadedBitmapMakeApiCallForFile = this.inAppRemoteSource.makeApiCallForFile(urlMeta);
        if (WhenMappings.$EnumSwitchMapping$0[downloadedBitmapMakeApiCallForFile.getStatus().ordinal()] != 1) {
            ILogger iLogger2 = this.logger;
            if (iLogger2 != null) {
                iLogger2.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "There was a problem fetching data for " + urlMeta.getSecond().name() + ", status: " + downloadedBitmapMakeApiCallForFile.getStatus());
            }
            return null;
        }
        Pair<? extends T, byte[]> pairInvoke = dataToSaveBlock.invoke(downloadedBitmapMakeApiCallForFile);
        Intrinsics.checkNotNull(pairInvoke);
        Pair<? extends T, byte[]> pair = pairInvoke;
        saveData(urlMeta.getFirst(), pair, mao);
        ILogger iLogger3 = this.logger;
        if (iLogger3 != null) {
            iLogger3.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "Returning requested " + urlMeta.getFirst() + ' ' + urlMeta.getSecond().name() + " with network, saved in cache");
        }
        return pair.getFirst();
    }
}
