package com.singular.sdk.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes6.dex */
class ExternalAIFAHelper {
    private static final SingularLog logger = SingularLog.getLogger("ExternalAIFAHelper");

    ExternalAIFAHelper() {
    }

    public static String getAIFA(Context context) {
        try {
            String strQueryAdvertisingIdFromService = queryAdvertisingIdFromService(context);
            logger.debug("Got AIFA by querying Google Play service");
            return strQueryAdvertisingIdFromService;
        } catch (Throwable unused) {
            logger.debug("Could not determine AIFA");
            return null;
        }
    }

    static String queryAdvertisingIdFromService(Context context) {
        GoogleAdvertisingServiceConnection googleAdvertisingServiceConnection = new GoogleAdvertisingServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (!context.bindService(intent, googleAdvertisingServiceConnection, 1)) {
            return "";
        }
        try {
            String id = GoogleAdvertisingInfo.GoogleAdvertisingInfoBinder.Create(googleAdvertisingServiceConnection.getBinder()).getId();
            context.unbindService(googleAdvertisingServiceConnection);
            return id;
        } catch (Throwable unused) {
            context.unbindService(googleAdvertisingServiceConnection);
            return "";
        }
    }

    private static class GoogleAdvertisingServiceConnection implements ServiceConnection {
        private final BlockingQueue<IBinder> _binderQueue;
        boolean _consumed;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdvertisingServiceConnection() {
            this._consumed = false;
            this._binderQueue = new LinkedBlockingQueue();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                this._binderQueue.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (this._consumed) {
                throw new IllegalStateException();
            }
            this._consumed = true;
            return this._binderQueue.take();
        }
    }

    private interface GoogleAdvertisingInfo extends IInterface {
        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {
            public static GoogleAdvertisingInfo Create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return (GoogleAdvertisingInfo) iInterfaceQueryLocalInterface;
                }
                return new GoogleAdvertisingInfoImplementation(iBinder);
            }

            @Override // android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                if (i == 1) {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    String id = getId();
                    parcel2.writeNoException();
                    parcel2.writeString(id);
                    return true;
                }
                if (i == 2) {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    boolean enabled = getEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(enabled ? 1 : 0);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private IBinder _binder;

                @Override // android.os.IInterface
                public IBinder asBinder() {
                    return this._binder;
                }

                GoogleAdvertisingInfoImplementation(IBinder iBinder) {
                    this._binder = iBinder;
                }

                @Override // com.singular.sdk.internal.ExternalAIFAHelper.GoogleAdvertisingInfo
                public String getId() throws RemoteException {
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this._binder.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } finally {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                }

                @Override // com.singular.sdk.internal.ExternalAIFAHelper.GoogleAdvertisingInfo
                public boolean getEnabled(boolean z) throws RemoteException {
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        parcelObtain.writeInt(z ? 1 : 0);
                        this._binder.transact(2, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readInt() != 0;
                    } finally {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                }
            }
        }
    }
}
