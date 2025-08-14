package org.spongycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
interface CMSSecureReadable {
    InputStream getInputStream() throws IOException, CMSException;
}
