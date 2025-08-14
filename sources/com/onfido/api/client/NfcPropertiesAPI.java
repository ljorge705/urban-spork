package com.onfido.api.client;

import com.onfido.api.client.data.MRZDocument;
import com.onfido.api.client.data.NfcProperties;
import com.onfido.api.client.data.NfcPropertiesRequest;
import io.reactivex.rxjava3.core.Single;
import java.util.List;

/* loaded from: classes6.dex */
public class NfcPropertiesAPI {
    private final OnfidoService onfidoService;

    NfcPropertiesAPI(OnfidoService onfidoService) {
        this.onfidoService = onfidoService;
    }

    Single<NfcProperties> get(List<String> list, MRZDocument mRZDocument) {
        return this.onfidoService.getNfcProperties(new NfcPropertiesRequest(list, mRZDocument));
    }
}
