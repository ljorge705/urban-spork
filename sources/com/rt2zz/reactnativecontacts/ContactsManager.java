package com.rt2zz.reactnativecontacts;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.provider.ContactsContract;
import androidx.core.app.ActivityCompat;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import io.sentry.protocol.Geo;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: classes6.dex */
public class ContactsManager extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String PERMISSION_AUTHORIZED = "authorized";
    private static final String PERMISSION_DENIED = "denied";
    private static final String PERMISSION_READ_CONTACTS = "android.permission.READ_CONTACTS";
    private static final int PERMISSION_REQUEST_CODE = 888;
    private static final int REQUEST_OPEN_CONTACT_FORM = 52941;
    private static final int REQUEST_OPEN_EXISTING_CONTACT = 52942;
    private static Promise requestPromise;
    private static Promise updateContactPromise;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "Contacts";
    }

    @ReactMethod
    public void iosEnableNotesUsage(boolean z) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public ContactsManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void getAll(Promise promise) {
        getAllContacts(promise);
    }

    @ReactMethod
    public void getAllWithoutPhotos(Promise promise) {
        getAllContacts(promise);
    }

    private void getAllContacts(final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContacts());
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getCount(final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsCount());
                    return null;
                } catch (Exception e) {
                    promise.reject(e);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsMatchingString(final String str, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsMatchingString(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsByPhoneNumber(final String str, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsByPhoneNumber(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsByEmailAddress(final String str, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsByEmailAddress(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getPhotoForId(final String str, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getPhotoUriFromContactId(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactById(final String str, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactById(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void writePhotoToPath(final String str, final String str2, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.rt2zz.reactnativecontacts.ContactsManager.8
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [long] */
            /* JADX WARN: Type inference failed for: r1v10 */
            /* JADX WARN: Type inference failed for: r1v3 */
            /* JADX WARN: Type inference failed for: r1v4 */
            /* JADX WARN: Type inference failed for: r1v8 */
            /* JADX WARN: Type inference failed for: r1v9 */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004f -> B:30:0x0052). Please report as a decompilation issue!!! */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) throws Throwable {
                Throwable th;
                FileOutputStream fileOutputStream;
                ContentResolver contentResolver = ContactsManager.this.getReactApplicationContext().getContentResolver();
                Uri uri = ContactsContract.Contacts.CONTENT_URI;
                ?? r1 = Long.parseLong(str);
                InputStream inputStreamOpenContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, ContentUris.withAppendedId(uri, r1));
                OutputStream outputStream = null;
                try {
                    try {
                        try {
                            fileOutputStream = new FileOutputStream(str2);
                            try {
                                BitmapFactory.decodeStream(inputStreamOpenContactPhotoInputStream).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                                promise.resolve(true);
                                fileOutputStream.close();
                                r1 = fileOutputStream;
                            } catch (FileNotFoundException e) {
                                e = e;
                                promise.reject(e.toString());
                                fileOutputStream.close();
                                r1 = fileOutputStream;
                                inputStreamOpenContactPhotoInputStream.close();
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            outputStream = r1;
                            try {
                                outputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream.close();
                        throw th;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                    r1 = e4;
                }
                try {
                    inputStreamOpenContactPhotoInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    private Bitmap getThumbnailBitmap(String str) throws IOException {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile != null) {
            return bitmapDecodeFile;
        }
        try {
            InputStream inputStreamOpen = getReactApplicationContext().getAssets().open(str);
            bitmapDecodeFile = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
            return bitmapDecodeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return bitmapDecodeFile;
        }
    }

    @ReactMethod
    public void openContactForm(ReadableMap readableMap, Promise promise) throws IOException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        int size;
        String[] strArr;
        Integer[] numArr;
        String[] strArr2;
        String[] strArr3;
        int size2;
        String[] strArr4;
        int i;
        Integer[] numArr2;
        String[] strArr5;
        String[] strArr6;
        int size3;
        Integer[] numArr3;
        String[] strArr7;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        int i2;
        int i3;
        Integer[] numArr4;
        String[] strArr8;
        int size4;
        Integer[] numArr5;
        String[] strArr9;
        String[] strArr10;
        String[] strArr11;
        String[] strArr12;
        String[] strArr13;
        String[] strArr14;
        String[] strArr15;
        int size5;
        String[] strArr16;
        String[] strArr17;
        String string = readableMap.hasKey("givenName") ? readableMap.getString("givenName") : null;
        String string2 = readableMap.hasKey("middleName") ? readableMap.getString("middleName") : null;
        String string3 = readableMap.hasKey("displayName") ? readableMap.getString("displayName") : null;
        String string4 = readableMap.hasKey("familyName") ? readableMap.getString("familyName") : null;
        String string5 = readableMap.hasKey("prefix") ? readableMap.getString("prefix") : null;
        String string6 = readableMap.hasKey(DynamicLink.Builder.KEY_SUFFIX) ? readableMap.getString(DynamicLink.Builder.KEY_SUFFIX) : null;
        String string7 = readableMap.hasKey("company") ? readableMap.getString("company") : null;
        String string8 = readableMap.hasKey("jobTitle") ? readableMap.getString("jobTitle") : null;
        String string9 = readableMap.hasKey("department") ? readableMap.getString("department") : null;
        String string10 = readableMap.hasKey("note") ? readableMap.getString("note") : null;
        String string11 = readableMap.hasKey("thumbnailPath") ? readableMap.getString("thumbnailPath") : null;
        ReadableArray array = readableMap.hasKey("phoneNumbers") ? readableMap.getArray("phoneNumbers") : null;
        if (array != null) {
            size = array.size();
            str = string3;
            strArr = new String[size];
            str5 = string11;
            strArr2 = new String[size];
            str4 = string10;
            numArr = new Integer[size];
            str3 = string9;
            int i4 = 0;
            while (true) {
                int i5 = size;
                if (i4 >= size) {
                    break;
                }
                strArr[i4] = array.getMap(i4).getString(CTVariableUtils.NUMBER);
                String string12 = array.getMap(i4).getString("label");
                strArr2[i4] = string12;
                numArr[i4] = Integer.valueOf(mapStringToPhoneType(string12));
                i4++;
                size = i5;
                string8 = string8;
            }
            str2 = string8;
        } else {
            str = string3;
            str2 = string8;
            str3 = string9;
            str4 = string10;
            str5 = string11;
            size = 0;
            strArr = null;
            numArr = null;
            strArr2 = null;
        }
        ReadableArray array2 = readableMap.hasKey("urlAddresses") ? readableMap.getArray("urlAddresses") : null;
        if (array2 != null) {
            size2 = array2.size();
            strArr4 = new String[size2];
            strArr3 = strArr;
            int i6 = 0;
            while (true) {
                int i7 = size2;
                if (i6 >= size2) {
                    break;
                }
                strArr4[i6] = array2.getMap(i6).getString("url");
                i6++;
                size2 = i7;
                array2 = array2;
            }
        } else {
            strArr3 = strArr;
            size2 = 0;
            strArr4 = null;
        }
        ReadableArray array3 = readableMap.hasKey("emailAddresses") ? readableMap.getArray("emailAddresses") : null;
        if (array3 != null) {
            size3 = array3.size();
            strArr5 = strArr2;
            strArr7 = new String[size3];
            numArr2 = numArr;
            numArr3 = new Integer[size3];
            i = size;
            int i8 = 0;
            while (true) {
                int i9 = size3;
                if (i8 >= size3) {
                    break;
                }
                strArr7[i8] = array3.getMap(i8).getString("email");
                numArr3[i8] = Integer.valueOf(mapStringToEmailType(array3.getMap(i8).getString("label")));
                i8++;
                size3 = i9;
                strArr4 = strArr4;
            }
            strArr6 = strArr4;
        } else {
            i = size;
            numArr2 = numArr;
            strArr5 = strArr2;
            strArr6 = strArr4;
            size3 = 0;
            numArr3 = null;
            strArr7 = null;
        }
        ReadableArray array4 = readableMap.hasKey("postalAddresses") ? readableMap.getArray("postalAddresses") : null;
        if (array4 != null) {
            size4 = array4.size();
            strArr15 = new String[size4];
            strArr8 = strArr7;
            strArr14 = new String[size4];
            numArr4 = numArr3;
            String[] strArr18 = new String[size4];
            i2 = size3;
            strArr12 = new String[size4];
            i3 = size2;
            strArr13 = new String[size4];
            str11 = string7;
            strArr11 = new String[size4];
            str10 = string6;
            strArr10 = new String[size4];
            str9 = string5;
            strArr9 = new String[size4];
            str7 = string2;
            numArr5 = new Integer[size4];
            str8 = string4;
            int i10 = 0;
            while (i10 < size4) {
                strArr15[i10] = array4.getMap(i10).getString("street");
                strArr14[i10] = array4.getMap(i10).getString(Geo.JsonKeys.CITY);
                strArr18[i10] = array4.getMap(i10).getString("state");
                strArr12[i10] = array4.getMap(i10).getString(Geo.JsonKeys.REGION);
                strArr13[i10] = array4.getMap(i10).getString("postCode");
                strArr11[i10] = array4.getMap(i10).getString(MediaCallbackResultReceiver.KEY_COUNTRY);
                strArr10[i10] = array4.getMap(i10).getString("formattedAddress");
                strArr9[i10] = array4.getMap(i10).getString("label");
                numArr5[i10] = Integer.valueOf(mapStringToPostalAddressType(array4.getMap(i10).getString("label")));
                i10++;
                size4 = size4;
                string = string;
            }
            str6 = string;
        } else {
            str6 = string;
            str7 = string2;
            str8 = string4;
            str9 = string5;
            str10 = string6;
            str11 = string7;
            i2 = size3;
            i3 = size2;
            numArr4 = numArr3;
            strArr8 = strArr7;
            size4 = 0;
            numArr5 = null;
            strArr9 = null;
            strArr10 = null;
            strArr11 = null;
            strArr12 = null;
            strArr13 = null;
            strArr14 = null;
            strArr15 = null;
        }
        ReadableArray array5 = readableMap.hasKey("imAddresses") ? readableMap.getArray("imAddresses") : null;
        if (array5 != null) {
            size5 = array5.size();
            strArr16 = new String[size5];
            String[] strArr19 = new String[size5];
            int i11 = 0;
            while (i11 < size5) {
                strArr16[i11] = array5.getMap(i11).getString("username");
                strArr19[i11] = array5.getMap(i11).getString("service");
                i11++;
                size5 = size5;
            }
            strArr17 = strArr19;
        } else {
            size5 = 0;
            strArr16 = null;
            strArr17 = null;
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mimetype", "vnd.android.cursor.item/identity");
        contentValues.put("data2", str6);
        String[] strArr20 = strArr16;
        contentValues.put("data3", str8);
        String str12 = "data5";
        int i12 = size5;
        contentValues.put("data5", str7);
        String str13 = "data4";
        Integer[] numArr6 = numArr5;
        contentValues.put("data4", str9);
        String[] strArr21 = strArr9;
        contentValues.put("data6", str10);
        arrayList.add(contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("mimetype", "vnd.android.cursor.item/organization");
        contentValues2.put("data1", str11);
        contentValues2.put("data4", str2);
        contentValues2.put("data5", str3);
        arrayList.add(contentValues2);
        int i13 = 0;
        while (true) {
            int i14 = i3;
            if (i13 >= i14) {
                break;
            }
            i3 = i14;
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("mimetype", "vnd.android.cursor.item/website");
            contentValues3.put("data1", strArr6[i13]);
            arrayList.add(contentValues3);
            i13++;
            str12 = str12;
        }
        String str14 = str12;
        int i15 = 0;
        while (true) {
            int i16 = i2;
            if (i15 >= i16) {
                break;
            }
            ContentValues contentValues4 = new ContentValues();
            i2 = i16;
            contentValues4.put("mimetype", "vnd.android.cursor.item/email_v2");
            contentValues4.put("data2", numArr4[i15]);
            contentValues4.put("data1", strArr8[i15]);
            arrayList.add(contentValues4);
            i15++;
        }
        int i17 = 0;
        while (true) {
            int i18 = i;
            if (i17 >= i18) {
                break;
            }
            ContentValues contentValues5 = new ContentValues();
            i = i18;
            contentValues5.put("mimetype", "vnd.android.cursor.item/phone_v2");
            contentValues5.put("data2", numArr2[i17]);
            contentValues5.put("data3", strArr5[i17]);
            contentValues5.put("data1", strArr3[i17]);
            arrayList.add(contentValues5);
            i17++;
        }
        int i19 = 0;
        while (i19 < size4) {
            ContentValues contentValues6 = new ContentValues();
            contentValues6.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
            contentValues6.put(str13, strArr15[i19]);
            contentValues6.put("data7", strArr14[i19]);
            contentValues6.put("data8", strArr12[i19]);
            contentValues6.put("data10", strArr11[i19]);
            contentValues6.put("data9", strArr13[i19]);
            contentValues6.put("data1", strArr10[i19]);
            contentValues6.put("data3", strArr21[i19]);
            contentValues6.put("data2", numArr6[i19]);
            arrayList.add(contentValues6);
            i19++;
            str13 = str13;
        }
        int i20 = 0;
        while (true) {
            int i21 = i12;
            if (i20 >= i21) {
                break;
            }
            ContentValues contentValues7 = new ContentValues();
            contentValues7.put("mimetype", "vnd.android.cursor.item/im");
            contentValues7.put("data1", strArr20[i20]);
            contentValues7.put("data2", (Integer) 1);
            contentValues7.put(str14, (Integer) (-1));
            contentValues7.put("data6", strArr17[i20]);
            arrayList.add(contentValues7);
            i20++;
            i12 = i21;
        }
        if (str4 != null) {
            ContentValues contentValues8 = new ContentValues();
            contentValues8.put("mimetype", "vnd.android.cursor.item/note");
            contentValues8.put("data1", str4);
            arrayList.add(contentValues8);
        }
        if (str5 != null && !str5.isEmpty()) {
            Bitmap thumbnailBitmap = getThumbnailBitmap(str5);
            if (thumbnailBitmap != null) {
                ContentValues contentValues9 = new ContentValues();
                contentValues9.put("raw_contact_id", (Integer) 0);
                contentValues9.put("is_super_primary", (Integer) 1);
                contentValues9.put("data15", toByteArray(thumbnailBitmap));
                contentValues9.put("mimetype", "vnd.android.cursor.item/photo");
                arrayList.add(contentValues9);
            }
        }
        Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("name", str);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        intent.putParcelableArrayListExtra("data", arrayList);
        updateContactPromise = promise;
        getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_CONTACT_FORM, Bundle.EMPTY);
    }

    @ReactMethod
    public void openExistingContact(ReadableMap readableMap, Promise promise) {
        try {
            Uri uriWithAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setDataAndType(uriWithAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void viewExistingContact(ReadableMap readableMap, Promise promise) {
        try {
            Uri uriWithAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uriWithAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void editExistingContact(ReadableMap readableMap, Promise promise) {
        int size;
        Integer[] numArr;
        String[] strArr = null;
        try {
            Uri uriWithAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            ReadableArray array = readableMap.hasKey("phoneNumbers") ? readableMap.getArray("phoneNumbers") : null;
            if (array != null) {
                size = array.size();
                strArr = new String[size];
                numArr = new Integer[size];
                for (int i = 0; i < size; i++) {
                    strArr[i] = array.getMap(i).getString(CTVariableUtils.NUMBER);
                    numArr[i] = Integer.valueOf(mapStringToPhoneType(array.getMap(i).getString("label")));
                }
            } else {
                size = 0;
                numArr = null;
            }
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < size; i2++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
                contentValues.put("data2", numArr[i2]);
                contentValues.put("data1", strArr[i2]);
                arrayList.add(contentValues);
            }
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setDataAndType(uriWithAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            intent.putParcelableArrayListExtra("data", arrayList);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void addContact(ReadableMap readableMap, Promise promise) throws IOException, RemoteException, OperationApplicationException {
        String str;
        String str2;
        String str3;
        String str4;
        String[] strArr;
        int size;
        String[] strArr2;
        Integer[] numArr;
        String[] strArr3;
        int i;
        String[] strArr4;
        String[] strArr5;
        int i2;
        int i3;
        Integer[] numArr2;
        String[] strArr6;
        int i4;
        Integer[] numArr3;
        String[] strArr7;
        String[] strArr8;
        String str5;
        int size2;
        String[] strArr9;
        String[] strArr10;
        ContactsManager contactsManager;
        Promise promise2;
        int i5;
        if (readableMap == null) {
            promise.reject("New contact cannot be null.");
            return;
        }
        String string = readableMap.hasKey("givenName") ? readableMap.getString("givenName") : null;
        String string2 = readableMap.hasKey("middleName") ? readableMap.getString("middleName") : null;
        String string3 = readableMap.hasKey("familyName") ? readableMap.getString("familyName") : null;
        String string4 = readableMap.hasKey("prefix") ? readableMap.getString("prefix") : null;
        String string5 = readableMap.hasKey(DynamicLink.Builder.KEY_SUFFIX) ? readableMap.getString(DynamicLink.Builder.KEY_SUFFIX) : null;
        String string6 = readableMap.hasKey("company") ? readableMap.getString("company") : null;
        String string7 = readableMap.hasKey("jobTitle") ? readableMap.getString("jobTitle") : null;
        String string8 = readableMap.hasKey("department") ? readableMap.getString("department") : null;
        String string9 = readableMap.hasKey("note") ? readableMap.getString("note") : null;
        String string10 = readableMap.hasKey("thumbnailPath") ? readableMap.getString("thumbnailPath") : null;
        ReadableArray array = readableMap.hasKey("phoneNumbers") ? readableMap.getArray("phoneNumbers") : null;
        if (array != null) {
            size = array.size();
            strArr = new String[size];
            str4 = string10;
            numArr = new Integer[size];
            str3 = string8;
            strArr2 = new String[size];
            str2 = string7;
            int i6 = 0;
            while (true) {
                int i7 = size;
                if (i6 >= size) {
                    break;
                }
                strArr[i6] = array.getMap(i6).getString(CTVariableUtils.NUMBER);
                String string11 = array.getMap(i6).getString("label");
                numArr[i6] = Integer.valueOf(mapStringToPhoneType(string11));
                strArr2[i6] = string11;
                i6++;
                size = i7;
                string6 = string6;
            }
            str = string6;
        } else {
            str = string6;
            str2 = string7;
            str3 = string8;
            str4 = string10;
            strArr = null;
            size = 0;
            strArr2 = null;
            numArr = null;
        }
        ReadableArray array2 = readableMap.hasKey("urlAddresses") ? readableMap.getArray("urlAddresses") : null;
        if (array2 != null) {
            int size3 = array2.size();
            strArr4 = new String[size3];
            strArr3 = strArr2;
            int i8 = 0;
            while (true) {
                i5 = size3;
                if (i8 >= size3) {
                    break;
                }
                strArr4[i8] = array2.getMap(i8).getString("url");
                i8++;
                size3 = i5;
                array2 = array2;
            }
            i = i5;
        } else {
            strArr3 = strArr2;
            i = 0;
            strArr4 = null;
        }
        ReadableArray array3 = readableMap.hasKey("emailAddresses") ? readableMap.getArray("emailAddresses") : null;
        if (array3 != null) {
            int size4 = array3.size();
            strArr6 = strArr4;
            strArr8 = new String[size4];
            i3 = i;
            numArr3 = new Integer[size4];
            numArr2 = numArr;
            strArr7 = new String[size4];
            strArr5 = strArr;
            int i9 = 0;
            while (i9 < size4) {
                int i10 = size;
                strArr8[i9] = array3.getMap(i9).getString("email");
                String string12 = array3.getMap(i9).getString("label");
                numArr3[i9] = Integer.valueOf(mapStringToEmailType(string12));
                strArr7[i9] = string12;
                i9++;
                size4 = size4;
                size = i10;
            }
            i2 = size;
            i4 = size4;
        } else {
            strArr5 = strArr;
            i2 = size;
            i3 = i;
            numArr2 = numArr;
            strArr6 = strArr4;
            i4 = 0;
            numArr3 = null;
            strArr7 = null;
            strArr8 = null;
        }
        ReadableArray array4 = readableMap.hasKey("imAddresses") ? readableMap.getArray("imAddresses") : null;
        if (array4 != null) {
            size2 = array4.size();
            strArr9 = new String[size2];
            str5 = "label";
            strArr10 = new String[size2];
            int i11 = 0;
            while (true) {
                int i12 = size2;
                if (i11 >= size2) {
                    break;
                }
                strArr9[i11] = array4.getMap(i11).getString("username");
                strArr10[i11] = array4.getMap(i11).getString("service");
                i11++;
                size2 = i12;
            }
        } else {
            str5 = "label";
            size2 = 0;
            strArr9 = null;
            strArr10 = null;
        }
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        String[] strArr11 = strArr10;
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        String[] strArr12 = strArr9;
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", string).withValue("data5", string2).withValue("data3", string3).withValue("data4", string4).withValue("data6", string5).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", string9).build());
        ContentProviderOperation.Builder builderWithValue = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data1", str).withValue("data4", str2).withValue("data5", str3);
        arrayList.add(builderWithValue.build());
        builderWithValue.withYieldAllowed(true);
        int i13 = 0;
        for (int i14 = i2; i13 < i14; i14 = i14) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", strArr5[i13]).withValue("data2", numArr2[i13]).withValue("data3", strArr3[i13]).build());
            i13++;
        }
        int i15 = 0;
        for (int i16 = i3; i15 < i16; i16 = i16) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", strArr6[i15]).build());
            i15++;
        }
        for (int i17 = 0; i17 < i4; i17++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", strArr8[i17]).withValue("data2", numArr3[i17]).withValue("data3", strArr7[i17]).build());
        }
        if (str4 == null || str4.isEmpty()) {
            contactsManager = this;
        } else {
            contactsManager = this;
            Bitmap thumbnailBitmap = contactsManager.getThumbnailBitmap(str4);
            if (thumbnailBitmap != null) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", contactsManager.toByteArray(thumbnailBitmap)).build());
            }
        }
        ReadableArray array5 = readableMap.hasKey("postalAddresses") ? readableMap.getArray("postalAddresses") : null;
        if (array5 != null) {
            int i18 = 0;
            while (i18 < array5.size()) {
                ReadableMap map = array5.getMap(i18);
                String str6 = str5;
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(contactsManager.mapStringToPostalAddressType(map.getString(str6)))).withValue("data3", map.getString(str6)).withValue("data4", map.getString("street")).withValue("data7", map.getString(Geo.JsonKeys.CITY)).withValue("data8", map.getString("state")).withValue("data9", map.getString("postCode")).withValue("data10", map.getString(MediaCallbackResultReceiver.KEY_COUNTRY)).build());
                i18++;
                contactsManager = this;
            }
        }
        for (int i19 = 0; i19 < size2; i19++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", strArr12[i19]).withValue("data2", 1).withValue("data5", -1).withValue("data6", strArr11[i19]).build());
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] contentProviderResultArrApplyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (contentProviderResultArrApplyBatch == null || contentProviderResultArrApplyBatch.length <= 0) {
                return;
            }
            WritableMap contactByRawId = new ContactsProvider(contentResolver).getContactByRawId(String.valueOf(ContentUris.parseId(contentProviderResultArrApplyBatch[0].uri)));
            promise2 = promise;
            try {
                promise2.resolve(contactByRawId);
            } catch (Exception e) {
                e = e;
                promise2.reject(e.toString());
            }
        } catch (Exception e2) {
            e = e2;
            promise2 = promise;
        }
    }

    public byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void updateContact(ReadableMap readableMap, Promise promise) throws RemoteException, OperationApplicationException {
        String str;
        ReadableArray readableArray;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String[] strArr;
        String[] strArr2;
        Integer[] numArr;
        int size;
        String[] strArr3;
        int size2;
        String[] strArr4;
        String[] strArr5;
        String[] strArr6;
        ReadableArray readableArray2;
        int i;
        String[] strArr7;
        String[] strArr8;
        Integer[] numArr2;
        Integer[] numArr3;
        String[] strArr9;
        int size3;
        String[] strArr10;
        String str7;
        String str8;
        String str9;
        String str10;
        Integer[] numArr4;
        String[] strArr11;
        int i2;
        String[] strArr12;
        int i3;
        int size4;
        String[] strArr13;
        Integer[] numArr5;
        String[] strArr14;
        String[] strArr15;
        String[] strArr16;
        String[] strArr17;
        String[] strArr18;
        ReadableArray readableArray3;
        String[] strArr19;
        int size5;
        String[] strArr20;
        String[] strArr21;
        String str11;
        String[] strArr22;
        String str12;
        int i4;
        Promise promise2;
        Bitmap thumbnailBitmap;
        int i5;
        String[] strArr23;
        ContentProviderOperation.Builder builderWithValue;
        String string = readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null;
        String string2 = readableMap.hasKey("rawContactId") ? readableMap.getString("rawContactId") : null;
        if (string2 == null || string == null) {
            promise.reject("Invalid recordId or rawContactId");
            return;
        }
        String string3 = readableMap.hasKey("givenName") ? readableMap.getString("givenName") : null;
        String string4 = readableMap.hasKey("middleName") ? readableMap.getString("middleName") : null;
        String string5 = readableMap.hasKey("familyName") ? readableMap.getString("familyName") : null;
        String string6 = readableMap.hasKey("prefix") ? readableMap.getString("prefix") : null;
        String string7 = readableMap.hasKey(DynamicLink.Builder.KEY_SUFFIX) ? readableMap.getString(DynamicLink.Builder.KEY_SUFFIX) : null;
        String string8 = readableMap.hasKey("company") ? readableMap.getString("company") : null;
        String string9 = readableMap.hasKey("jobTitle") ? readableMap.getString("jobTitle") : null;
        String string10 = readableMap.hasKey("department") ? readableMap.getString("department") : null;
        String string11 = readableMap.hasKey("note") ? readableMap.getString("note") : null;
        String string12 = readableMap.hasKey("thumbnailPath") ? readableMap.getString("thumbnailPath") : null;
        ReadableArray array = readableMap.hasKey("phoneNumbers") ? readableMap.getArray("phoneNumbers") : null;
        String str13 = "label";
        String str14 = string12;
        String str15 = string11;
        if (array != null) {
            size = array.size();
            str = string2;
            strArr = new String[size];
            str6 = string10;
            numArr = new Integer[size];
            str5 = string9;
            strArr2 = new String[size];
            str4 = string8;
            String[] strArr24 = new String[size];
            str3 = string7;
            int i6 = 0;
            while (i6 < size) {
                int i7 = size;
                ReadableMap map = array.getMap(i6);
                ReadableArray readableArray4 = array;
                String string13 = map.getString(CTVariableUtils.NUMBER);
                String str16 = string6;
                String string14 = map.getString("label");
                String string15 = map.hasKey("id") ? map.getString("id") : null;
                strArr[i6] = string13;
                numArr[i6] = Integer.valueOf(mapStringToPhoneType(string14));
                strArr2[i6] = string14;
                strArr24[i6] = string15;
                i6++;
                size = i7;
                array = readableArray4;
                string6 = str16;
            }
            readableArray = array;
            str2 = string6;
        } else {
            str = string2;
            readableArray = array;
            str2 = string6;
            str3 = string7;
            str4 = string8;
            str5 = string9;
            str6 = string10;
            strArr = null;
            strArr2 = null;
            numArr = null;
            size = 0;
        }
        ReadableArray array2 = readableMap.hasKey("urlAddresses") ? readableMap.getArray("urlAddresses") : null;
        if (array2 != null) {
            size2 = array2.size();
            strArr4 = new String[size2];
            strArr5 = new String[size2];
            strArr3 = strArr2;
            int i8 = 0;
            while (true) {
                int i9 = size2;
                if (i8 >= size2) {
                    break;
                }
                ReadableMap map2 = array2.getMap(i8);
                ReadableArray readableArray5 = array2;
                strArr4[i8] = map2.getString("url");
                strArr5[i8] = map2.hasKey("id") ? map2.getString("id") : null;
                i8++;
                size2 = i9;
                array2 = readableArray5;
            }
        } else {
            strArr3 = strArr2;
            size2 = 0;
            strArr4 = null;
            strArr5 = null;
        }
        ReadableArray array3 = readableMap.hasKey("emailAddresses") ? readableMap.getArray("emailAddresses") : null;
        if (array3 != null) {
            size3 = array3.size();
            strArr7 = strArr4;
            strArr9 = new String[size3];
            strArr8 = strArr5;
            String[] strArr25 = new String[size3];
            i = size2;
            numArr3 = new Integer[size3];
            numArr2 = numArr;
            strArr10 = new String[size3];
            strArr6 = strArr;
            int i10 = 0;
            while (i10 < size3) {
                int i11 = size3;
                ReadableMap map3 = array3.getMap(i10);
                ReadableArray readableArray6 = array3;
                strArr9[i10] = map3.getString("email");
                String string16 = map3.getString("label");
                numArr3[i10] = Integer.valueOf(mapStringToEmailType(string16));
                strArr10[i10] = string16;
                strArr25[i10] = map3.hasKey("id") ? map3.getString("id") : null;
                i10++;
                size3 = i11;
                array3 = readableArray6;
            }
            readableArray2 = array3;
        } else {
            strArr6 = strArr;
            readableArray2 = array3;
            i = size2;
            strArr7 = strArr4;
            strArr8 = strArr5;
            numArr2 = numArr;
            numArr3 = null;
            strArr9 = null;
            size3 = 0;
            strArr10 = null;
        }
        ReadableArray array4 = readableMap.hasKey("postalAddresses") ? readableMap.getArray("postalAddresses") : null;
        if (array4 != null) {
            size4 = array4.size();
            strArr15 = new String[size4];
            strArr12 = strArr10;
            strArr17 = new String[size4];
            numArr4 = numArr3;
            strArr14 = new String[size4];
            strArr11 = strArr9;
            String[] strArr26 = new String[size4];
            i2 = size3;
            strArr16 = new String[size4];
            i3 = size;
            strArr18 = new String[size4];
            str10 = string5;
            numArr5 = new Integer[size4];
            str9 = string4;
            strArr13 = new String[size4];
            str8 = string3;
            int i12 = 0;
            while (i12 < size4) {
                int i13 = size4;
                String valueFromKey = getValueFromKey(array4.getMap(i12), str13);
                strArr15[i12] = getValueFromKey(array4.getMap(i12), "street");
                strArr17[i12] = getValueFromKey(array4.getMap(i12), Geo.JsonKeys.CITY);
                strArr14[i12] = getValueFromKey(array4.getMap(i12), "state");
                strArr26[i12] = getValueFromKey(array4.getMap(i12), Geo.JsonKeys.REGION);
                strArr16[i12] = getValueFromKey(array4.getMap(i12), "postCode");
                strArr18[i12] = getValueFromKey(array4.getMap(i12), MediaCallbackResultReceiver.KEY_COUNTRY);
                numArr5[i12] = Integer.valueOf(mapStringToPostalAddressType(valueFromKey));
                strArr13[i12] = valueFromKey;
                i12++;
                size4 = i13;
                str13 = str13;
                string = string;
            }
            str7 = string;
        } else {
            str7 = string;
            str8 = string3;
            str9 = string4;
            str10 = string5;
            numArr4 = numArr3;
            strArr11 = strArr9;
            i2 = size3;
            strArr12 = strArr10;
            i3 = size;
            size4 = 0;
            strArr13 = null;
            numArr5 = null;
            strArr14 = null;
            strArr15 = null;
            strArr16 = null;
            strArr17 = null;
            strArr18 = null;
        }
        ReadableArray array5 = readableMap.hasKey("imAddresses") ? readableMap.getArray("imAddresses") : null;
        if (array5 != null) {
            size5 = array5.size();
            String[] strArr27 = new String[size5];
            strArr20 = new String[size5];
            String[] strArr28 = new String[size5];
            strArr19 = strArr18;
            int i14 = 0;
            while (i14 < size5) {
                int i15 = size5;
                ReadableMap map4 = array5.getMap(i14);
                ReadableArray readableArray7 = array5;
                strArr27[i14] = map4.getString("username");
                strArr20[i14] = map4.getString("service");
                strArr28[i14] = map4.hasKey("id") ? map4.getString("id") : null;
                i14++;
                size5 = i15;
                array5 = readableArray7;
            }
            readableArray3 = array5;
            strArr21 = strArr27;
        } else {
            readableArray3 = array5;
            strArr19 = strArr18;
            size5 = 0;
            strArr20 = null;
            strArr21 = null;
        }
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        String[] strArr29 = strArr20;
        ContentProviderOperation.Builder builderWithValue2 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(str7)}).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", str8).withValue("data5", str9);
        int i16 = size5;
        ContentProviderOperation.Builder builderWithValue3 = builderWithValue2.withValue("data3", str10);
        String[] strArr30 = strArr16;
        ContentProviderOperation.Builder builderWithValue4 = builderWithValue3.withValue("data4", str2);
        String[] strArr31 = strArr14;
        arrayList.add(builderWithValue4.withValue("data6", str3).build());
        ContentProviderOperation.Builder builderWithValue5 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(str7), "vnd.android.cursor.item/organization"}).withValue("data1", str4).withValue("data4", str5).withValue("data5", str6);
        arrayList.add(builderWithValue5.build());
        builderWithValue5.withYieldAllowed(true);
        if (readableArray != null) {
            str12 = "data5";
            strArr22 = strArr17;
            str11 = "data4";
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/phone_v2", String.valueOf(str)}).build());
            int i17 = 0;
            for (int i18 = i3; i17 < i18; i18 = i18) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", strArr6[i17]).withValue("data2", numArr2[i17]).withValue("data3", strArr3[i17]).build());
                i17++;
            }
        } else {
            str11 = "data4";
            strArr22 = strArr17;
            str12 = "data5";
        }
        int i19 = i;
        int i20 = 0;
        while (i20 < i19) {
            if (strArr8[i20] == null) {
                i5 = i19;
                builderWithValue = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", strArr7[i20]);
                strArr23 = strArr15;
            } else {
                i5 = i19;
                strArr23 = strArr15;
                builderWithValue = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("_id=?", new String[]{String.valueOf(strArr8[i20])}).withValue("data1", strArr7[i20]);
            }
            arrayList.add(builderWithValue.build());
            i20++;
            strArr15 = strArr23;
            i19 = i5;
        }
        String[] strArr32 = strArr15;
        if (readableArray2 != null) {
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/email_v2", String.valueOf(str)}).build());
            int i21 = i2;
            for (int i22 = 0; i22 < i21; i22++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", strArr11[i22]).withValue("data2", numArr4[i22]).withValue("data3", strArr12[i22]).build());
            }
        }
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/note", String.valueOf(str)}).build());
        if (str15 != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", str15).build());
        }
        if (str14 == null || str14.isEmpty() || (thumbnailBitmap = getThumbnailBitmap(str14)) == null) {
            i4 = 0;
        } else {
            i4 = 0;
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", toByteArray(thumbnailBitmap)).build());
        }
        if (array4 != null) {
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/postal-address_v2", String.valueOf(str)}).build());
            for (int i23 = i4; i23 < size4; i23++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", numArr5[i23]).withValue("data3", strArr13[i23]).withValue(str11, strArr32[i23]).withValue("data7", strArr22[i23]).withValue("data8", strArr31[i23]).withValue("data9", strArr30[i23]).withValue("data10", strArr19[i23]).build());
            }
        }
        if (readableArray3 != null) {
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/im", String.valueOf(str)}).build());
            for (int i24 = 0; i24 < i16; i24++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", String.valueOf(str)).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", strArr21[i24]).withValue("data2", 1).withValue(str12, -1).withValue("data6", strArr29[i24]).build());
            }
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] contentProviderResultArrApplyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (contentProviderResultArrApplyBatch == null || contentProviderResultArrApplyBatch.length <= 0) {
                return;
            }
            WritableMap contactById = new ContactsProvider(contentResolver).getContactById(str7);
            promise2 = promise;
            try {
                promise2.resolve(contactById);
            } catch (Exception e) {
                e = e;
                promise2.reject(e.toString());
            }
        } catch (Exception e2) {
            e = e2;
            promise2 = promise;
        }
    }

    @ReactMethod
    public void deleteContact(ReadableMap readableMap, Promise promise) {
        String string = readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null;
        try {
            if (getReactApplicationContext().getContentResolver().delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, string), null, null) > 0) {
                promise.resolve(string);
            } else {
                promise.resolve(null);
            }
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void checkPermission(Promise promise) {
        promise.resolve(isPermissionGranted());
    }

    @ReactMethod
    public void requestPermission(Promise promise) {
        requestReadContactsPermission(promise);
    }

    private void requestReadContactsPermission(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(PERMISSION_DENIED);
        } else if (isPermissionGranted().equals(PERMISSION_AUTHORIZED)) {
            promise.resolve(PERMISSION_AUTHORIZED);
        } else {
            requestPromise = promise;
            ActivityCompat.requestPermissions(currentActivity, new String[]{PERMISSION_READ_CONTACTS}, PERMISSION_REQUEST_CODE);
        }
    }

    protected static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Promise promise = requestPromise;
        if (promise == null) {
            return;
        }
        if (i != PERMISSION_REQUEST_CODE) {
            promise.resolve(PERMISSION_DENIED);
            return;
        }
        Hashtable hashtable = new Hashtable();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            hashtable.put(strArr[i2], Boolean.valueOf(iArr[i2] == 0));
        }
        if (hashtable.containsKey(PERMISSION_READ_CONTACTS) && ((Boolean) hashtable.get(PERMISSION_READ_CONTACTS)).booleanValue()) {
            requestPromise.resolve(PERMISSION_AUTHORIZED);
        } else {
            requestPromise.resolve(PERMISSION_DENIED);
        }
        requestPromise = null;
    }

    private String getValueFromKey(ReadableMap readableMap, String str) {
        return readableMap.hasKey(str) ? readableMap.getString(str) : "";
    }

    private String isPermissionGranted() {
        return getReactApplicationContext().checkSelfPermission(PERMISSION_READ_CONTACTS) == 0 ? PERMISSION_AUTHORIZED : PERMISSION_DENIED;
    }

    private int mapStringToPhoneType(String str) {
        str.hashCode();
        switch (str) {
            case "mobile":
            case "cell":
                return 2;
            case "work_pager":
                return 18;
            case "home fax":
                return 5;
            case "work_mobile":
                return 17;
            case "home":
                return 1;
            case "main":
                return 12;
            case "work":
                return 3;
            case "work fax":
                return 4;
            case "other":
                return 7;
            case "pager":
                return 6;
            default:
                return 0;
        }
    }

    private int mapStringToEmailType(String str) {
        str.hashCode();
        switch (str) {
            case "mobile":
                return 4;
            case "home":
            case "personal":
                return 1;
            case "work":
                return 2;
            case "other":
                return 3;
            default:
                return 0;
        }
    }

    private int mapStringToPostalAddressType(String str) {
        str.hashCode();
        if (str.equals("home")) {
            return 1;
        }
        return !str.equals("work") ? 0 : 2;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Promise promise;
        Uri data;
        if ((i == REQUEST_OPEN_CONTACT_FORM || i == REQUEST_OPEN_EXISTING_CONTACT) && (promise = updateContactPromise) != null) {
            if (i2 != -1) {
                promise.resolve(null);
                updateContactPromise = null;
                return;
            }
            if (intent == null) {
                promise.reject("Error received activity result with no data!");
                updateContactPromise = null;
                return;
            }
            try {
                data = intent.getData();
            } catch (Exception e) {
                updateContactPromise.reject(e.getMessage());
            }
            if (data == null) {
                updateContactPromise.reject("Error wrong data. No content uri found!");
                updateContactPromise = null;
            } else {
                updateContactPromise.resolve(new ContactsProvider(getReactApplicationContext().getContentResolver()).getContactById(data.getLastPathSegment()));
                updateContactPromise = null;
            }
        }
    }
}
