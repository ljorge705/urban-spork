package dialoobnhfjfdhmrftlq;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class vnufwshzeizvjmboyyju implements Serializable {
    private Set yvrzbryuycempgkdhpvj = new HashSet();

    public Set dbuymyhwehsdoxafsfpy() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vnufwshzeizvjmboyyju)) {
            return false;
        }
        vnufwshzeizvjmboyyju vnufwshzeizvjmboyyjuVar = (vnufwshzeizvjmboyyju) obj;
        if (!vnufwshzeizvjmboyyjuVar.yvrzbryuycempgkdhpvj(this)) {
            return false;
        }
        Set setDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        Set setDbuymyhwehsdoxafsfpy2 = vnufwshzeizvjmboyyjuVar.dbuymyhwehsdoxafsfpy();
        return setDbuymyhwehsdoxafsfpy != null ? setDbuymyhwehsdoxafsfpy.equals(setDbuymyhwehsdoxafsfpy2) : setDbuymyhwehsdoxafsfpy2 == null;
    }

    public int hashCode() {
        Set setDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        return (setDbuymyhwehsdoxafsfpy == null ? 43 : setDbuymyhwehsdoxafsfpy.hashCode()) + 59;
    }

    public String toString() {
        return "UserDevices(userDeviceList=" + dbuymyhwehsdoxafsfpy() + ")";
    }

    protected boolean yvrzbryuycempgkdhpvj(Object obj) {
        return obj instanceof vnufwshzeizvjmboyyju;
    }
}
