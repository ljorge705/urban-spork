package com.airbnb.lottie.parser;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes5.dex */
class ShapeFillParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of(Constants.NOTIF_MSG, "c", "o", "fillEnabled", "r", "hd");

    private ShapeFillParser() {
    }

    static ShapeFill parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        AnimatableIntegerValue animatableIntegerValue = null;
        String strNextString = null;
        AnimatableColorValue color = null;
        boolean zNextBoolean = false;
        boolean zNextBoolean2 = false;
        int iNextInt = 1;
        while (jsonReader.hasNext()) {
            int iSelectName = jsonReader.selectName(NAMES);
            if (iSelectName == 0) {
                strNextString = jsonReader.nextString();
            } else if (iSelectName == 1) {
                color = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
            } else if (iSelectName == 2) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (iSelectName == 3) {
                zNextBoolean = jsonReader.nextBoolean();
            } else if (iSelectName == 4) {
                iNextInt = jsonReader.nextInt();
            } else if (iSelectName == 5) {
                zNextBoolean2 = jsonReader.nextBoolean();
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        if (animatableIntegerValue == null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100)));
        }
        return new ShapeFill(strNextString, zNextBoolean, iNextInt == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, color, animatableIntegerValue, zNextBoolean2);
    }
}
