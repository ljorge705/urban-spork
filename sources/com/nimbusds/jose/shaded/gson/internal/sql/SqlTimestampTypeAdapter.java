package com.nimbusds.jose.shaded.gson.internal.sql;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.TypeAdapter;
import com.nimbusds.jose.shaded.gson.TypeAdapterFactory;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import com.nimbusds.jose.shaded.gson.stream.JsonReader;
import com.nimbusds.jose.shaded.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* loaded from: classes2.dex */
class SqlTimestampTypeAdapter extends TypeAdapter<Timestamp> {
    static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.nimbusds.jose.shaded.gson.internal.sql.SqlTimestampTypeAdapter.1
        @Override // com.nimbusds.jose.shaded.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Timestamp.class) {
                return new SqlTimestampTypeAdapter(gson.getAdapter(Date.class));
            }
            return null;
        }
    };
    private final TypeAdapter<Date> dateTypeAdapter;

    private SqlTimestampTypeAdapter(TypeAdapter<Date> typeAdapter) {
        this.dateTypeAdapter = typeAdapter;
    }

    @Override // com.nimbusds.jose.shaded.gson.TypeAdapter
    /* renamed from: read, reason: avoid collision after fix types in other method */
    public Timestamp read2(JsonReader jsonReader) throws IOException {
        Date date = this.dateTypeAdapter.read2(jsonReader);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override // com.nimbusds.jose.shaded.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
        this.dateTypeAdapter.write(jsonWriter, timestamp);
    }
}
