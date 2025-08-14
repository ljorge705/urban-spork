package com.facebook.react;

import agency.flexible.react.modules.email.EmailPackage;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import ca.jaysoo.extradimensions.ExtraDimensionsPackage;
import cl.json.RNSharePackage;
import com.BV.LinearGradient.LinearGradientPackage;
import com.RNRSA.RNRSAPackage;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilPackage;
import com.airbnb.android.react.lottie.LottiePackage;
import com.asterinet.react.bgactions.BackgroundActionsPackage;
import com.bebnev.RNUserAgentPackage;
import com.clevertap.react.CleverTapPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.fastopenpgp.FastOpenpgpPackage;
import com.henninghall.date_picker.DatePickerPackage;
import com.horcrux.svg.SvgPackage;
import com.ibits.react_native_in_app_review.AppReviewPackage;
import com.imagepicker.ImagePickerPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.microsoft.codepush.react.CodePush;
import com.mkuczera.RNReactNativeHapticFeedbackPackage;
import com.oblador.keychain.KeychainPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.onfido.reactnative.sdk.OnfidoSdkPackage;
import com.ptc.reactnative.paygilant.PaygilantPackage;
import com.reactcommunity.rndatetimepicker.RNDateTimePickerPackage;
import com.reactnativecommunity.art.ARTPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.clipboard.ClipboardPackage;
import com.reactnativecommunity.cookies.CookieManagerPackage;
import com.reactnativecommunity.geolocation.GeolocationPackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.reactnativecommunity.picker.RNCPickerPackage;
import com.reactnativecommunity.slider.ReactSliderPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.reactnativedocumentpicker.RNDocumentPickerPackage;
import com.reactnativegooglesignin.RNGoogleSigninPackage;
import com.rncamerakit.RNCameraKitPackage;
import com.rnfingerprint.FingerprintAuthPackage;
import com.rnfs.RNFSPackage;
import com.rnmaps.maps.MapsPackage;
import com.rt2zz.reactnativecontacts.ReactNativeContacts;
import com.sdkmobilereactnative.SdkMobileReactNativePackage;
import com.sha256lib.Sha256Package;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.uxcam.RNUxcamPackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import fr.greweb.reactnativeviewshot.RNViewShotPackage;
import io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.config.ReactNativeFirebaseConfigPackage;
import io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksPackage;
import io.invertase.firebase.firestore.ReactNativeFirebaseFirestorePackage;
import io.invertase.firebase.installations.ReactNativeFirebaseInstallationsPackage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingPackage;
import io.invertase.firebase.storage.ReactNativeFirebaseStoragePackage;
import io.invertase.notifee.NotifeePackage;
import io.sentry.react.RNSentryPackage;
import java.util.ArrayList;
import java.util.Arrays;
import net.singular.react_native.SingularBridgePackage;
import org.devio.rn.splashscreen.SplashScreenReactPackage;
import org.reactnative.maskedview.RNCMaskedViewPackage;
import org.wonday.pdf.RNPDFPackage;

/* loaded from: classes5.dex */
public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, (MainPackageConfig) null);
    }

    public PackageList(Application application) {
        this(application, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application;
        this.mConfig = mainPackageConfig;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        return reactNativeHost == null ? this.application : reactNativeHost.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(this.mConfig), new SdkMobileReactNativePackage(), new NotifeePackage(), new OnfidoSdkPackage(), new AsyncStoragePackage(), new ClipboardPackage(), new ARTPackage(), new RNDateTimePickerPackage(), new GeolocationPackage(), new NetInfoPackage(), new ReactSliderPackage(), new CookieManagerPackage(), new ReactNativeFirebaseAnalyticsPackage(), new ReactNativeFirebaseAppPackage(), new ReactNativeFirebaseDynamicLinksPackage(), new ReactNativeFirebaseFirestorePackage(), new ReactNativeFirebaseInstallationsPackage(), new ReactNativeFirebaseMessagingPackage(), new ReactNativeFirebaseConfigPackage(), new ReactNativeFirebaseStoragePackage(), new RNGoogleSigninPackage(), new RNCMaskedViewPackage(), new RNCPickerPackage(), new RNSentryPackage(), new CleverTapPackage(), new LottiePackage(), new BackgroundActionsPackage(), new BackgroundTimerPackage(), new ReactNativeBlobUtilPackage(), new RNCameraKitPackage(), new CodePush(getResources().getString(com.krealo.tenpo.R.string.CodePushDeploymentKey), getApplicationContext(), false), new ReactNativeContacts(), new DatePickerPackage(), new RNDeviceInfo(), new RNDocumentPickerPackage(), new EmailPackage(), new ExtraDimensionsPackage(), new FastOpenpgpPackage(), new RNFSPackage(), new RNGestureHandlerPackage(), new RNReactNativeHapticFeedbackPackage(), new ImagePickerPackage(), new AppReviewPackage(), new KeychainPackage(), new LinearGradientPackage(), new MapsPackage(), new PaygilantPackage(), new RNPDFPackage(), new RNPermissionsPackage(), new ReanimatedPackage(), new RNRSAPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new Sha256Package(), new RNSharePackage(), new SplashScreenReactPackage(), new SvgPackage(), new FingerprintAuthPackage(), new RNUserAgentPackage(), new RNUxcamPackage(), new RNViewShotPackage(), new RNCWebViewPackage(), new SingularBridgePackage()));
    }
}
