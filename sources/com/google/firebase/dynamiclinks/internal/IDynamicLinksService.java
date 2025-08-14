package com.google.firebase.dynamiclinks.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks;

/* loaded from: classes5.dex */
public interface IDynamicLinksService extends IInterface {

    public static class Default implements IDynamicLinksService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksService
        public void createShortDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, Bundle bundle) throws RemoteException {
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksService
        public void getDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, String str) throws RemoteException {
        }
    }

    void createShortDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, Bundle bundle) throws RemoteException;

    void getDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicLinksService {
        private static final String DESCRIPTOR = "com.google.firebase.dynamiclinks.internal.IDynamicLinksService";
        static final int TRANSACTION_createShortDynamicLink = 2;
        static final int TRANSACTION_getDynamicLink = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
        }

        public static IDynamicLinksService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDynamicLinksService)) {
                return (IDynamicLinksService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
                getDynamicLink(IDynamicLinksCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
            createShortDynamicLink(IDynamicLinksCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDynamicLinksService {
            public static IDynamicLinksService sDefaultImpl;
            private IBinder mRemote;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.google.firebase.dynamiclinks.internal.IDynamicLinksService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksService
            public void getDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
                    parcelObtain.writeStrongBinder(iDynamicLinksCallbacks != null ? iDynamicLinksCallbacks.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDynamicLink(iDynamicLinksCallbacks, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksService
            public void createShortDynamicLink(IDynamicLinksCallbacks iDynamicLinksCallbacks, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
                    parcelObtain.writeStrongBinder(iDynamicLinksCallbacks != null ? iDynamicLinksCallbacks.asBinder() : null);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createShortDynamicLink(iDynamicLinksCallbacks, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDynamicLinksService iDynamicLinksService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDynamicLinksService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDynamicLinksService;
            return true;
        }

        public static IDynamicLinksService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
