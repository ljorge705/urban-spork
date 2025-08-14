package com.nimbusds.jose.util;

import java.io.IOException;
import java.net.URL;

/* loaded from: classes2.dex */
public interface ResourceRetriever {
    Resource retrieveResource(URL url) throws IOException;
}
