package androidx.compose.material;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B°\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001e\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0096\u0002J\b\u0010\"\u001a\u00020#H\u0016J.\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0017ø\u0001\u0000¢\u0006\u0002\u0010'J.\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0017ø\u0001\u0000¢\u0006\u0002\u0010'J&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010*J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0017ø\u0001\u0000¢\u0006\u0002\u0010*R\u0019\u0010\u0011\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\n\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0014\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\f\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0017\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u000f\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\t\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0015\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\r\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0010\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0012\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u000b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0016\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u000e\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019R\u0019\u0010\u0013\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0019\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"}, d2 = {"Landroidx/compose/material/DefaultTextFieldColors;", "Landroidx/compose/material/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "cursorColor", "errorCursorColor", "focusedIndicatorColor", "unfocusedIndicatorColor", "errorIndicatorColor", "disabledIndicatorColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", ViewProps.BACKGROUND_COLOR, "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "(JJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "Landroidx/compose/runtime/State;", ViewProps.ENABLED, "", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "isError", "equals", "other", "", "hashCode", "", "indicatorColor", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "labelColor", "error", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class DefaultTextFieldColors implements TextFieldColors {
    private final long backgroundColor;
    private final long cursorColor;
    private final long disabledIndicatorColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledPlaceholderColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long errorCursorColor;
    private final long errorIndicatorColor;
    private final long errorLabelColor;
    private final long errorLeadingIconColor;
    private final long errorTrailingIconColor;
    private final long focusedIndicatorColor;
    private final long focusedLabelColor;
    private final long leadingIconColor;
    private final long placeholderColor;
    private final long textColor;
    private final long trailingIconColor;
    private final long unfocusedIndicatorColor;
    private final long unfocusedLabelColor;

    public /* synthetic */ DefaultTextFieldColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21);
    }

    private DefaultTextFieldColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21) {
        this.textColor = j;
        this.disabledTextColor = j2;
        this.cursorColor = j3;
        this.errorCursorColor = j4;
        this.focusedIndicatorColor = j5;
        this.unfocusedIndicatorColor = j6;
        this.errorIndicatorColor = j7;
        this.disabledIndicatorColor = j8;
        this.leadingIconColor = j9;
        this.disabledLeadingIconColor = j10;
        this.errorLeadingIconColor = j11;
        this.trailingIconColor = j12;
        this.disabledTrailingIconColor = j13;
        this.errorTrailingIconColor = j14;
        this.backgroundColor = j15;
        this.focusedLabelColor = j16;
        this.unfocusedLabelColor = j17;
        this.disabledLabelColor = j18;
        this.errorLabelColor = j19;
        this.placeholderColor = j20;
        this.disabledPlaceholderColor = j21;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> leadingIconColor(boolean z, boolean z2, Composer composer, int i) {
        composer.startReplaceableGroup(-1018452720);
        ComposerKt.sourceInformation(composer, "C(leadingIconColor)301@13144L207:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(!z ? this.disabledLeadingIconColor : z2 ? this.errorLeadingIconColor : this.leadingIconColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> trailingIconColor(boolean z, boolean z2, Composer composer, int i) {
        composer.startReplaceableGroup(-2025569462);
        ComposerKt.sourceInformation(composer, "C(trailingIconColor)312@13477L210:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(!z ? this.disabledTrailingIconColor : z2 ? this.errorTrailingIconColor : this.trailingIconColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> indicatorColor(boolean z, boolean z2, InteractionSource interactionSource, Composer composer, int i) {
        long j;
        State<Color> stateRememberUpdatedState;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        composer.startReplaceableGroup(-2054210020);
        ComposerKt.sourceInformation(composer, "C(indicatorColor)P(!1,2)327@13904L25:TextFieldDefaults.kt#jmzs0o");
        State<Boolean> stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i >> 6) & 14);
        if (!z) {
            j = this.disabledIndicatorColor;
        } else if (z2) {
            j = this.errorIndicatorColor;
        } else {
            j = m1277indicatorColor$lambda0(stateCollectIsFocusedAsState) ? this.focusedIndicatorColor : this.unfocusedIndicatorColor;
        }
        long j2 = j;
        if (z) {
            composer.startReplaceableGroup(-2054209563);
            ComposerKt.sourceInformation(composer, "336@14195L75");
            stateRememberUpdatedState = SingleValueAnimationKt.m343animateColorAsStateKTwxG1Y(j2, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, composer, 48, 4);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-2054209458);
            ComposerKt.sourceInformation(composer, "338@14300L33");
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(j2), composer, 0);
            composer.endReplaceableGroup();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> backgroundColor(boolean z, Composer composer, int i) {
        composer.startReplaceableGroup(163022307);
        ComposerKt.sourceInformation(composer, "C(backgroundColor)344@14449L37:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(this.backgroundColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> placeholderColor(boolean z, Composer composer, int i) {
        composer.startReplaceableGroup(-853665633);
        ComposerKt.sourceInformation(composer, "C(placeholderColor)349@14593L81:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(z ? this.placeholderColor : this.disabledPlaceholderColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> labelColor(boolean z, boolean z2, InteractionSource interactionSource, Composer composer, int i) {
        long j;
        State<Color> stateRememberUpdatedState;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        composer.startReplaceableGroup(863333660);
        ComposerKt.sourceInformation(composer, "C(labelColor)358@14885L25:TextFieldDefaults.kt#jmzs0o");
        State<Boolean> stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i >> 6) & 14);
        if (!z) {
            j = this.disabledLabelColor;
        } else if (z2) {
            j = this.errorLabelColor;
        } else {
            j = m1278labelColor$lambda1(stateCollectIsFocusedAsState) ? this.focusedLabelColor : this.unfocusedLabelColor;
        }
        long j2 = j;
        if (z) {
            composer.startReplaceableGroup(863334093);
            ComposerKt.sourceInformation(composer, "367@15158L75");
            stateRememberUpdatedState = SingleValueAnimationKt.m343animateColorAsStateKTwxG1Y(j2, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, composer, 48, 4);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(863334198);
            ComposerKt.sourceInformation(composer, "369@15263L33");
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(j2), composer, 0);
            composer.endReplaceableGroup();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> textColor(boolean z, Composer composer, int i) {
        composer.startReplaceableGroup(-509862043);
        ComposerKt.sourceInformation(composer, "C(textColor)375@15406L67:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(z ? this.textColor : this.disabledTextColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material.TextFieldColors
    public State<Color> cursorColor(boolean z, Composer composer, int i) {
        composer.startReplaceableGroup(-1692278667);
        ComposerKt.sourceInformation(composer, "C(cursorColor)380@15575L68:TextFieldDefaults.kt#jmzs0o");
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(z ? this.errorCursorColor : this.cursorColor), composer, 0);
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(getClass()), Reflection.getOrCreateKotlinClass(other.getClass()))) {
            return false;
        }
        DefaultTextFieldColors defaultTextFieldColors = (DefaultTextFieldColors) other;
        return Color.m1878equalsimpl0(this.textColor, defaultTextFieldColors.textColor) && Color.m1878equalsimpl0(this.disabledTextColor, defaultTextFieldColors.disabledTextColor) && Color.m1878equalsimpl0(this.cursorColor, defaultTextFieldColors.cursorColor) && Color.m1878equalsimpl0(this.errorCursorColor, defaultTextFieldColors.errorCursorColor) && Color.m1878equalsimpl0(this.focusedIndicatorColor, defaultTextFieldColors.focusedIndicatorColor) && Color.m1878equalsimpl0(this.unfocusedIndicatorColor, defaultTextFieldColors.unfocusedIndicatorColor) && Color.m1878equalsimpl0(this.errorIndicatorColor, defaultTextFieldColors.errorIndicatorColor) && Color.m1878equalsimpl0(this.disabledIndicatorColor, defaultTextFieldColors.disabledIndicatorColor) && Color.m1878equalsimpl0(this.leadingIconColor, defaultTextFieldColors.leadingIconColor) && Color.m1878equalsimpl0(this.disabledLeadingIconColor, defaultTextFieldColors.disabledLeadingIconColor) && Color.m1878equalsimpl0(this.errorLeadingIconColor, defaultTextFieldColors.errorLeadingIconColor) && Color.m1878equalsimpl0(this.trailingIconColor, defaultTextFieldColors.trailingIconColor) && Color.m1878equalsimpl0(this.disabledTrailingIconColor, defaultTextFieldColors.disabledTrailingIconColor) && Color.m1878equalsimpl0(this.errorTrailingIconColor, defaultTextFieldColors.errorTrailingIconColor) && Color.m1878equalsimpl0(this.backgroundColor, defaultTextFieldColors.backgroundColor) && Color.m1878equalsimpl0(this.focusedLabelColor, defaultTextFieldColors.focusedLabelColor) && Color.m1878equalsimpl0(this.unfocusedLabelColor, defaultTextFieldColors.unfocusedLabelColor) && Color.m1878equalsimpl0(this.disabledLabelColor, defaultTextFieldColors.disabledLabelColor) && Color.m1878equalsimpl0(this.errorLabelColor, defaultTextFieldColors.errorLabelColor) && Color.m1878equalsimpl0(this.placeholderColor, defaultTextFieldColors.placeholderColor) && Color.m1878equalsimpl0(this.disabledPlaceholderColor, defaultTextFieldColors.disabledPlaceholderColor);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((Color.m1884hashCodeimpl(this.textColor) * 31) + Color.m1884hashCodeimpl(this.disabledTextColor)) * 31) + Color.m1884hashCodeimpl(this.cursorColor)) * 31) + Color.m1884hashCodeimpl(this.errorCursorColor)) * 31) + Color.m1884hashCodeimpl(this.focusedIndicatorColor)) * 31) + Color.m1884hashCodeimpl(this.unfocusedIndicatorColor)) * 31) + Color.m1884hashCodeimpl(this.errorIndicatorColor)) * 31) + Color.m1884hashCodeimpl(this.disabledIndicatorColor)) * 31) + Color.m1884hashCodeimpl(this.leadingIconColor)) * 31) + Color.m1884hashCodeimpl(this.disabledLeadingIconColor)) * 31) + Color.m1884hashCodeimpl(this.errorLeadingIconColor)) * 31) + Color.m1884hashCodeimpl(this.trailingIconColor)) * 31) + Color.m1884hashCodeimpl(this.disabledTrailingIconColor)) * 31) + Color.m1884hashCodeimpl(this.errorTrailingIconColor)) * 31) + Color.m1884hashCodeimpl(this.backgroundColor)) * 31) + Color.m1884hashCodeimpl(this.focusedLabelColor)) * 31) + Color.m1884hashCodeimpl(this.unfocusedLabelColor)) * 31) + Color.m1884hashCodeimpl(this.disabledLabelColor)) * 31) + Color.m1884hashCodeimpl(this.errorLabelColor)) * 31) + Color.m1884hashCodeimpl(this.placeholderColor)) * 31) + Color.m1884hashCodeimpl(this.disabledPlaceholderColor);
    }

    /* renamed from: indicatorColor$lambda-0, reason: not valid java name */
    private static final boolean m1277indicatorColor$lambda0(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* renamed from: labelColor$lambda-1, reason: not valid java name */
    private static final boolean m1278labelColor$lambda1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
