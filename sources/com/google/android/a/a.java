package com.google.android.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: BaseProxy.java */
/* loaded from: classes.dex */
public class a implements IInterface {

    /* renamed from: a, reason: collision with root package name */
    private final IBinder f58a;
    private final String b = "com.google.android.finsky.externalreferrer.IGetInstallReferrerService";

    protected a(IBinder iBinder) {
        this.f58a = iBinder;
    }

    protected final Parcel a() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.b);
        return parcelObtain;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f58a;
    }

    protected final Parcel b(Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.f58a.transact(1, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e) {
                parcelObtain.recycle();
                throw e;
            }
        } finally {
            parcel.recycle();
        }
    }
}
