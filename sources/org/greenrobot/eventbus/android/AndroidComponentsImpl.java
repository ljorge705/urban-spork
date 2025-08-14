package org.greenrobot.eventbus.android;

/* loaded from: classes4.dex */
public class AndroidComponentsImpl extends AndroidComponents {
    public AndroidComponentsImpl() {
        super(new AndroidLogger("EventBus"), new DefaultAndroidMainThreadSupport());
    }
}
