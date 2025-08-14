package androidx.profileinstaller;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;
    private boolean mDeviceSupportsAotProfile = false;
    private final byte[] mDesiredVersion = desiredVersion();

    private static boolean requiresMetadata() {
        int i = Build.VERSION.SDK_INT;
        if (i != 24 && i != 25) {
            switch (i) {
                case 31:
                case 32:
                case 33:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void result(final int i, final Object obj) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.profileinstaller.DeviceProfileWriter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m4697lambda$result$0$androidxprofileinstallerDeviceProfileWriter(i, obj);
            }
        });
    }

    /* renamed from: lambda$result$0$androidx-profileinstaller-DeviceProfileWriter, reason: not valid java name */
    /* synthetic */ void m4697lambda$result$0$androidxprofileinstallerDeviceProfileWriter(int i, Object obj) {
        this.mDiagnostics.onResultReceived(i, obj);
    }

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, String str2, String str3, File file) {
        this.mAssetManager = assetManager;
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
        this.mApkName = str;
        this.mProfileSourceLocation = str2;
        this.mProfileMetaSourceLocation = str3;
        this.mCurProfile = file;
    }

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if (this.mDesiredVersion == null) {
            result(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (this.mCurProfile.canWrite()) {
            this.mDeviceSupportsAotProfile = true;
            return true;
        }
        result(4, null);
        return false;
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if (!this.mDeviceSupportsAotProfile) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    public DeviceProfileWriter read() throws IOException {
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        if (this.mDesiredVersion == null) {
            return this;
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenFd = this.mAssetManager.openFd(this.mProfileSourceLocation);
            try {
                FileInputStream fileInputStreamCreateInputStream = assetFileDescriptorOpenFd.createInputStream();
                try {
                    this.mProfile = ProfileTranscoder.readProfile(fileInputStreamCreateInputStream, ProfileTranscoder.readHeader(fileInputStreamCreateInputStream, ProfileTranscoder.MAGIC_PROF), this.mApkName);
                    if (fileInputStreamCreateInputStream != null) {
                        fileInputStreamCreateInputStream.close();
                    }
                    if (assetFileDescriptorOpenFd != null) {
                        assetFileDescriptorOpenFd.close();
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (assetFileDescriptorOpenFd != null) {
                    try {
                        assetFileDescriptorOpenFd.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e) {
            this.mDiagnostics.onResultReceived(6, e);
        } catch (IOException e2) {
            this.mDiagnostics.onResultReceived(7, e2);
        } catch (IllegalStateException e3) {
            this.mDiagnostics.onResultReceived(8, e3);
        }
        DexProfileData[] dexProfileDataArr = this.mProfile;
        if (dexProfileDataArr != null && requiresMetadata()) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd2 = this.mAssetManager.openFd(this.mProfileMetaSourceLocation);
                try {
                    FileInputStream fileInputStreamCreateInputStream2 = assetFileDescriptorOpenFd2.createInputStream();
                    try {
                        this.mProfile = ProfileTranscoder.readMeta(fileInputStreamCreateInputStream2, ProfileTranscoder.readHeader(fileInputStreamCreateInputStream2, ProfileTranscoder.MAGIC_PROFM), this.mDesiredVersion, dexProfileDataArr);
                        if (fileInputStreamCreateInputStream2 != null) {
                            fileInputStreamCreateInputStream2.close();
                        }
                        if (assetFileDescriptorOpenFd2 != null) {
                            assetFileDescriptorOpenFd2.close();
                        }
                        return this;
                    } finally {
                    }
                } finally {
                }
            } catch (FileNotFoundException e4) {
                this.mDiagnostics.onResultReceived(9, e4);
            } catch (IOException e5) {
                this.mDiagnostics.onResultReceived(7, e5);
            } catch (IllegalStateException e6) {
                this.mProfile = null;
                this.mDiagnostics.onResultReceived(8, e6);
            }
        }
        return this;
    }

    public DeviceProfileWriter transcodeIfNeeded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        DexProfileData[] dexProfileDataArr = this.mProfile;
        byte[] bArr = this.mDesiredVersion;
        if (dexProfileDataArr != null && bArr != null) {
            assertDeviceAllowsProfileInstallerAotWritesCalled();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    ProfileTranscoder.writeHeader(byteArrayOutputStream, bArr);
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
            } catch (IllegalStateException e2) {
                this.mDiagnostics.onResultReceived(8, e2);
            }
            if (!ProfileTranscoder.transcodeAndWriteBody(byteArrayOutputStream, bArr, dexProfileDataArr)) {
                this.mDiagnostics.onResultReceived(5, null);
                this.mProfile = null;
                byteArrayOutputStream.close();
                return this;
            }
            this.mTranscodedProfile = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            this.mProfile = null;
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean write() {
        byte[] bArr = this.mTranscodedProfile;
        if (bArr == null) {
            return false;
        }
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        try {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.mCurProfile);
                    try {
                        Encoding.writeAll(byteArrayInputStream, fileOutputStream);
                        result(1, null);
                        fileOutputStream.close();
                        byteArrayInputStream.close();
                        return true;
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } finally {
                this.mTranscodedProfile = null;
                this.mProfile = null;
            }
        } catch (FileNotFoundException e) {
            result(6, e);
            return false;
        } catch (IOException e2) {
            result(7, e2);
            return false;
        }
    }

    private static byte[] desiredVersion() {
        switch (Build.VERSION.SDK_INT) {
            case 24:
            case 25:
                return ProfileVersion.V001_N;
            case 26:
                return ProfileVersion.V005_O;
            case 27:
                return ProfileVersion.V009_O_MR1;
            case 28:
            case 29:
            case 30:
                return ProfileVersion.V010_P;
            case 31:
            case 32:
            case 33:
                return ProfileVersion.V015_S;
            default:
                return null;
        }
    }
}
