package com.clevertap.android.sdk.inbox;

import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnFailureListener;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class CTInboxController {
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final CTLockManager ctLockManager;
    private final DBAdapter dbAdapter;
    private ArrayList<CTMessageDAO> messages;
    private final Object messagesLock = new Object();
    private final String userId;
    private final boolean videoSupported;

    public CTInboxController(CleverTapInstanceConfig cleverTapInstanceConfig, String str, DBAdapter dBAdapter, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, boolean z) {
        this.userId = str;
        this.dbAdapter = dBAdapter;
        this.messages = dBAdapter.getMessages(str);
        this.videoSupported = z;
        this.ctLockManager = cTLockManager;
        this.callbackManager = baseCallbackManager;
        this.config = cleverTapInstanceConfig;
    }

    public int count() {
        return getMessages().size();
    }

    public void deleteInboxMessage(final CTInboxMessage cTInboxMessage) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("deleteInboxMessage", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (CTInboxController.this.ctLockManager.getInboxControllerLock()) {
                    if (CTInboxController.this._deleteMessageWithId(cTInboxMessage.getMessageId())) {
                        CTInboxController.this.callbackManager._notifyInboxMessagesDidUpdate();
                    }
                }
                return null;
            }
        });
    }

    public void deleteInboxMessagesForIDs(final ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("deleteInboxMessagesForIDs", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.2
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (CTInboxController.this.ctLockManager.getInboxControllerLock()) {
                    if (CTInboxController.this._deleteMessagesForIds(arrayList)) {
                        CTInboxController.this.callbackManager._notifyInboxMessagesDidUpdate();
                    }
                }
                return null;
            }
        });
    }

    public CTMessageDAO getMessageForId(String str) {
        return findMessageById(str);
    }

    public ArrayList<CTMessageDAO> getMessages() {
        ArrayList<CTMessageDAO> arrayList;
        synchronized (this.messagesLock) {
            trimMessages();
            arrayList = this.messages;
        }
        return arrayList;
    }

    public ArrayList<CTMessageDAO> getUnreadMessages() {
        ArrayList<CTMessageDAO> arrayList = new ArrayList<>();
        synchronized (this.messagesLock) {
            Iterator<CTMessageDAO> it = getMessages().iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (next.isRead() == 0) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public void markReadInboxMessage(final CTInboxMessage cTInboxMessage) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("markReadInboxMessage", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (CTInboxController.this.ctLockManager.getInboxControllerLock()) {
                    if (CTInboxController.this._markReadForMessageWithId(cTInboxMessage.getMessageId())) {
                        CTInboxController.this.callbackManager._notifyInboxMessagesDidUpdate();
                    }
                }
                return null;
            }
        });
    }

    public void markReadInboxMessagesForIDs(final ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("markReadInboxMessagesForIDs", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.4
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (CTInboxController.this.ctLockManager.getInboxControllerLock()) {
                    if (CTInboxController.this._markReadForMessagesWithIds(arrayList)) {
                        CTInboxController.this.callbackManager._notifyInboxMessagesDidUpdate();
                    }
                }
                return null;
            }
        });
    }

    public int unreadCount() {
        return getUnreadMessages().size();
    }

    public boolean updateMessages(JSONArray jSONArray) {
        Logger.v("CTInboxController:updateMessages() called");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                CTMessageDAO cTMessageDAOInitWithJSON = CTMessageDAO.initWithJSON(jSONArray.getJSONObject(i), this.userId);
                if (cTMessageDAOInitWithJSON != null) {
                    if (!this.videoSupported && cTMessageDAOInitWithJSON.containsVideoOrAudio()) {
                        Logger.d("Dropping inbox message containing video/audio as app does not support video. For more information checkout CleverTap documentation.");
                    } else {
                        arrayList.add(cTMessageDAOInitWithJSON);
                        Logger.v("Inbox Message for message id - " + cTMessageDAOInitWithJSON.getId() + " added");
                    }
                }
            } catch (JSONException e) {
                Logger.d("Unable to update notification inbox messages - " + e.getLocalizedMessage());
            }
        }
        if (arrayList.size() <= 0) {
            return false;
        }
        this.dbAdapter.upsertMessages(arrayList);
        Logger.v("New Notification Inbox messages added");
        synchronized (this.messagesLock) {
            this.messages = this.dbAdapter.getMessages(this.userId);
            trimMessages();
        }
        return true;
    }

    boolean _deleteMessageWithId(final String str) {
        CTMessageDAO cTMessageDAOFindMessageById = findMessageById(str);
        if (cTMessageDAOFindMessageById == null) {
            return false;
        }
        synchronized (this.messagesLock) {
            this.messages.remove(cTMessageDAOFindMessageById);
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("RunDeleteMessage", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                CTInboxController.this.dbAdapter.deleteMessageForId(str, CTInboxController.this.userId);
                return null;
            }
        });
        return true;
    }

    boolean _deleteMessagesForIds(final ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            CTMessageDAO cTMessageDAOFindMessageById = findMessageById(it.next());
            if (cTMessageDAOFindMessageById != null) {
                arrayList2.add(cTMessageDAOFindMessageById);
            }
        }
        if (arrayList2.isEmpty()) {
            return false;
        }
        synchronized (this.messagesLock) {
            this.messages.removeAll(arrayList2);
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("RunDeleteMessagesForIDs", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.6
            @Override // java.util.concurrent.Callable
            public Void call() {
                CTInboxController.this.dbAdapter.deleteMessagesForIDs(arrayList, CTInboxController.this.userId);
                return null;
            }
        });
        return true;
    }

    boolean _markReadForMessageWithId(final String str) {
        CTMessageDAO cTMessageDAOFindMessageById = findMessageById(str);
        if (cTMessageDAOFindMessageById == null) {
            return false;
        }
        synchronized (this.messagesLock) {
            cTMessageDAOFindMessageById.setRead(1);
        }
        Task taskPostAsyncSafelyTask = CTExecutorFactory.executors(this.config).postAsyncSafelyTask();
        taskPostAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.inbox.CTInboxController$$ExternalSyntheticLambda0
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.m4800x2c9bbe28((Void) obj);
            }
        });
        taskPostAsyncSafelyTask.addOnFailureListener(new OnFailureListener() { // from class: com.clevertap.android.sdk.inbox.CTInboxController$$ExternalSyntheticLambda1
            @Override // com.clevertap.android.sdk.task.OnFailureListener
            public final void onFailure(Object obj) {
                Logger.d("Failed to update message read state for id:" + str, (Exception) obj);
            }
        });
        taskPostAsyncSafelyTask.execute("RunMarkMessageRead", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.7
            @Override // java.util.concurrent.Callable
            public Void call() {
                CTInboxController.this.dbAdapter.markReadMessageForId(str, CTInboxController.this.userId);
                return null;
            }
        });
        return true;
    }

    /* renamed from: lambda$_markReadForMessageWithId$0$com-clevertap-android-sdk-inbox-CTInboxController, reason: not valid java name */
    /* synthetic */ void m4800x2c9bbe28(Void r1) {
        this.callbackManager._notifyInboxMessagesDidUpdate();
    }

    boolean _markReadForMessagesWithIds(final ArrayList<String> arrayList) {
        Boolean bool = false;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            CTMessageDAO cTMessageDAOFindMessageById = findMessageById(it.next());
            if (cTMessageDAOFindMessageById != null) {
                bool = true;
                synchronized (this.messagesLock) {
                    cTMessageDAOFindMessageById.setRead(1);
                }
            }
        }
        if (!bool.booleanValue()) {
            return false;
        }
        Task taskPostAsyncSafelyTask = CTExecutorFactory.executors(this.config).postAsyncSafelyTask();
        taskPostAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.inbox.CTInboxController$$ExternalSyntheticLambda2
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.m4801xb0fd8d68((Void) obj);
            }
        });
        taskPostAsyncSafelyTask.addOnFailureListener(new OnFailureListener() { // from class: com.clevertap.android.sdk.inbox.CTInboxController$$ExternalSyntheticLambda3
            @Override // com.clevertap.android.sdk.task.OnFailureListener
            public final void onFailure(Object obj) {
                Logger.d("Failed to update message read state for ids:" + arrayList, (Exception) obj);
            }
        });
        taskPostAsyncSafelyTask.execute("RunMarkMessagesReadForIDs", new Callable<Void>() { // from class: com.clevertap.android.sdk.inbox.CTInboxController.8
            @Override // java.util.concurrent.Callable
            public Void call() {
                CTInboxController.this.dbAdapter.markReadMessagesForIds(arrayList, CTInboxController.this.userId);
                return null;
            }
        });
        return true;
    }

    /* renamed from: lambda$_markReadForMessagesWithIds$2$com-clevertap-android-sdk-inbox-CTInboxController, reason: not valid java name */
    /* synthetic */ void m4801xb0fd8d68(Void r1) {
        this.callbackManager._notifyInboxMessagesDidUpdate();
    }

    private CTMessageDAO findMessageById(String str) {
        synchronized (this.messagesLock) {
            Iterator<CTMessageDAO> it = this.messages.iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (next.getId().equals(str)) {
                    return next;
                }
            }
            Logger.v("Inbox Message for message id - " + str + " not found");
            return null;
        }
    }

    private void trimMessages() {
        Logger.v("CTInboxController:trimMessages() called");
        ArrayList arrayList = new ArrayList();
        synchronized (this.messagesLock) {
            Iterator<CTMessageDAO> it = this.messages.iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (!this.videoSupported && next.containsVideoOrAudio()) {
                    Logger.d("Removing inbox message containing video/audio as app does not support video. For more information checkout CleverTap documentation.");
                    arrayList.add(next);
                } else {
                    long expires = next.getExpires();
                    if (expires > 0 && System.currentTimeMillis() / 1000 > expires) {
                        Logger.v("Inbox Message: " + next.getId() + " is expired - removing");
                        arrayList.add(next);
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                _deleteMessageWithId(((CTMessageDAO) it2.next()).getId());
            }
        }
    }
}
