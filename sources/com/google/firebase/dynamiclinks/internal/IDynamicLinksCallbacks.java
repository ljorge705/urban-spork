package com.google.firebase.dynamiclinks.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes5.dex */
public interface IDynamicLinksCallbacks extends IInterface {

    public static class Default implements IDynamicLinksCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
        public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
        public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
        }
    }

    void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException;

    void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicLinksCallbacks {
        private static final String DESCRIPTOR = "com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks";
        static final int TRANSACTION_onCreateShortDynamicLink = 2;
        static final int TRANSACTION_onGetDynamicLink = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDynamicLinksCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDynamicLinksCallbacks)) {
                return (IDynamicLinksCallbacks) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetDynamicLink(parcel.readInt() != 0 ? Status.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? DynamicLinkData.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onCreateShortDynamicLink(parcel.readInt() != 0 ? Status.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ShortDynamicLinkImpl.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDynamicLinksCallbacks {
            public static IDynamicLinksCallbacks sDefaultImpl;
            private IBinder mRemote;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
            public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        parcelObtain.writeInt(1);
                        status.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (dynamicLinkData != null) {
                        parcelObtain.writeInt(1);
                        dynamicLinkData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetDynamicLink(status, dynamicLinkData);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
            public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        parcelObtain.writeInt(1);
                        status.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (shortDynamicLinkImpl != null) {
                        parcelObtain.writeInt(1);
                        shortDynamicLinkImpl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCreateShortDynamicLink(status, shortDynamicLinkImpl);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDynamicLinksCallbacks iDynamicLinksCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDynamicLinksCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDynamicLinksCallbacks;
            return true;
        }

        public static IDynamicLinksCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
