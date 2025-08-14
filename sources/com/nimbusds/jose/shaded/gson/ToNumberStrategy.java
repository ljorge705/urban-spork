package com.nimbusds.jose.shaded.gson;

import com.nimbusds.jose.shaded.gson.stream.JsonReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface ToNumberStrategy {
    Number readNumber(JsonReader jsonReader) throws IOException;
}
