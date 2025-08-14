package com.oblador.keychain;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.oblador.keychain.PrefsStorage;
import com.oblador.keychain.cipherStorage.CipherStorage;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import com.oblador.keychain.cipherStorage.CipherStorageFacebookConceal;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import com.oblador.keychain.decryptionHandler.DecryptionResultHandler;
import com.oblador.keychain.decryptionHandler.DecryptionResultHandlerProvider;
import com.oblador.keychain.exceptions.CryptoFailedException;
import com.oblador.keychain.exceptions.EmptyParameterException;
import com.oblador.keychain.exceptions.KeyStoreAccessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class KeychainModule extends ReactContextBaseJavaModule {
    public static final String EMPTY_STRING = "";
    public static final String FACE_SUPPORTED_NAME = "Face";
    public static final String FINGERPRINT_SUPPORTED_NAME = "Fingerprint";
    public static final String IRIS_SUPPORTED_NAME = "Iris";
    public static final String KEYCHAIN_MODULE = "RNKeychainManager";
    private static final String LOG_TAG = "KeychainModule";
    public static final String WARMING_UP_ALIAS = "warmingUp";
    private final Map<String, CipherStorage> cipherStorageMap;
    private final PrefsStorage prefsStorage;

    @interface AccessControl {
        public static final String APPLICATION_PASSWORD = "ApplicationPassword";
        public static final String BIOMETRY_ANY = "BiometryAny";
        public static final String BIOMETRY_ANY_OR_DEVICE_PASSCODE = "BiometryAnyOrDevicePasscode";
        public static final String BIOMETRY_CURRENT_SET = "BiometryCurrentSet";
        public static final String BIOMETRY_CURRENT_SET_OR_DEVICE_PASSCODE = "BiometryCurrentSetOrDevicePasscode";
        public static final String DEVICE_PASSCODE = "DevicePasscode";
        public static final String NONE = "None";
        public static final String USER_PRESENCE = "UserPresence";
    }

    @interface AuthPromptOptions {
        public static final String CANCEL = "cancel";
        public static final String DESCRIPTION = "description";
        public static final String SUBTITLE = "subtitle";
        public static final String TITLE = "title";
    }

    @interface Errors {
        public static final String E_CRYPTO_FAILED = "E_CRYPTO_FAILED";
        public static final String E_EMPTY_PARAMETERS = "E_EMPTY_PARAMETERS";
        public static final String E_KEYSTORE_ACCESS_ERROR = "E_KEYSTORE_ACCESS_ERROR";
        public static final String E_SUPPORTED_BIOMETRY_ERROR = "E_SUPPORTED_BIOMETRY_ERROR";
        public static final String E_UNKNOWN_ERROR = "E_UNKNOWN_ERROR";
    }

    public @interface KnownCiphers {
        public static final String AES = "KeystoreAESCBC";
        public static final String FB = "FacebookConceal";
        public static final String RSA = "KeystoreRSAECB";
    }

    @interface Maps {
        public static final String ACCESSIBLE = "accessible";
        public static final String ACCESS_CONTROL = "accessControl";
        public static final String ACCESS_GROUP = "accessGroup";
        public static final String AUTH_PROMPT = "authenticationPrompt";
        public static final String AUTH_TYPE = "authenticationType";
        public static final String PASSWORD = "password";
        public static final String RULES = "rules";
        public static final String SECURITY_LEVEL = "securityLevel";
        public static final String SERVICE = "service";
        public static final String STORAGE = "storage";
        public static final String USERNAME = "username";
    }

    @interface Rules {
        public static final String AUTOMATIC_UPGRADE = "automaticUpgradeToMoreSecuredStorage";
        public static final String NONE = "none";
    }

    private static String getAliasOrDefault(String str) {
        return str == null ? "" : str;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return KEYCHAIN_MODULE;
    }

    public KeychainModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.cipherStorageMap = new HashMap();
        this.prefsStorage = new PrefsStorage(reactApplicationContext);
        addCipherStorageToMap(new CipherStorageFacebookConceal(reactApplicationContext));
        addCipherStorageToMap(new CipherStorageKeystoreAesCbc());
        addCipherStorageToMap(new CipherStorageKeystoreRsaEcb());
    }

    public static KeychainModule withWarming(ReactApplicationContext reactApplicationContext) {
        final KeychainModule keychainModule = new KeychainModule(reactApplicationContext);
        Objects.requireNonNull(keychainModule);
        Thread thread = new Thread(new Runnable() { // from class: com.oblador.keychain.KeychainModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.internalWarmingBestCipher();
            }
        }, "keychain-warming-up");
        thread.setDaemon(true);
        thread.start();
        return keychainModule;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalWarmingBestCipher() {
        try {
            long jNanoTime = System.nanoTime();
            Log.v(KEYCHAIN_MODULE, "warming up started at " + jNanoTime);
            CipherStorageBase cipherStorageBase = (CipherStorageBase) getCipherStorageForCurrentAPILevel();
            cipherStorageBase.getCachedInstance();
            cipherStorageBase.generateKeyAndStoreUnderAlias(WARMING_UP_ALIAS, cipherStorageBase.supportsSecureHardware() ? SecurityLevel.SECURE_HARDWARE : SecurityLevel.SECURE_SOFTWARE);
            cipherStorageBase.getKeyStoreAndLoad();
            Log.v(KEYCHAIN_MODULE, "warming up takes: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime) + " ms");
        } catch (Throwable th) {
            Log.e(KEYCHAIN_MODULE, "warming up failed!", th);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put(SecurityLevel.ANY.jsName(), SecurityLevel.ANY.name());
        map.put(SecurityLevel.SECURE_SOFTWARE.jsName(), SecurityLevel.SECURE_SOFTWARE.name());
        map.put(SecurityLevel.SECURE_HARDWARE.jsName(), SecurityLevel.SECURE_HARDWARE.name());
        return map;
    }

    protected void setGenericPassword(String str, String str2, String str3, ReadableMap readableMap, Promise promise) {
        try {
            throwIfEmptyLoginPassword(str2, str3);
            SecurityLevel securityLevelOrDefault = getSecurityLevelOrDefault(readableMap);
            CipherStorage selectedStorage = getSelectedStorage(readableMap);
            throwIfInsufficientLevel(selectedStorage, securityLevelOrDefault);
            this.prefsStorage.storeEncryptedEntry(str, selectedStorage.encrypt(str, str2, str3, securityLevelOrDefault));
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("service", str);
            writableMapCreateMap.putString(Maps.STORAGE, selectedStorage.getCipherStorageName());
            promise.resolve(writableMapCreateMap);
        } catch (CryptoFailedException e) {
            Log.e(KEYCHAIN_MODULE, e.getMessage(), e);
            promise.reject(Errors.E_CRYPTO_FAILED, e);
        } catch (EmptyParameterException e2) {
            Log.e(KEYCHAIN_MODULE, e2.getMessage(), e2);
            promise.reject(Errors.E_EMPTY_PARAMETERS, e2);
        } catch (Throwable th) {
            Log.e(KEYCHAIN_MODULE, th.getMessage(), th);
            promise.reject(Errors.E_UNKNOWN_ERROR, th);
        }
    }

    @ReactMethod
    public void setGenericPasswordForOptions(ReadableMap readableMap, String str, String str2, Promise promise) {
        setGenericPassword(getServiceOrDefault(readableMap), str, str2, readableMap, promise);
    }

    private CipherStorage getSelectedStorage(ReadableMap readableMap) throws CryptoFailedException {
        boolean useBiometry = getUseBiometry(getAccessControlOrDefault(readableMap));
        String specificStorageOrDefault = getSpecificStorageOrDefault(readableMap);
        CipherStorage cipherStorageByName = specificStorageOrDefault != null ? getCipherStorageByName(specificStorageOrDefault) : null;
        return cipherStorageByName == null ? getCipherStorageForCurrentAPILevel(useBiometry) : cipherStorageByName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void getGenericPassword(String str, ReadableMap readableMap, Promise promise) {
        CipherStorage cipherStorageByName;
        try {
            PrefsStorage.ResultSet encryptedEntry = this.prefsStorage.getEncryptedEntry(str);
            if (encryptedEntry == null) {
                Log.e(KEYCHAIN_MODULE, "No entry found for service: " + str);
                promise.resolve(false);
                return;
            }
            String str2 = encryptedEntry.cipherStorageName;
            String securityRulesOrDefault = getSecurityRulesOrDefault(readableMap);
            BiometricPrompt.PromptInfo promptInfo = getPromptInfo(readableMap);
            if (securityRulesOrDefault.equals(Rules.AUTOMATIC_UPGRADE) && str2.equals(KnownCiphers.FB)) {
                cipherStorageByName = getCipherStorageForCurrentAPILevel(getUseBiometry(getAccessControlOrDefault(readableMap)));
            } else {
                cipherStorageByName = getCipherStorageByName(str2);
            }
            CipherStorage.DecryptionResult decryptionResultDecryptCredentials = decryptCredentials(str, cipherStorageByName, encryptedEntry, securityRulesOrDefault, promptInfo);
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("service", str);
            writableMapCreateMap.putString("username", (String) decryptionResultDecryptCredentials.username);
            writableMapCreateMap.putString("password", (String) decryptionResultDecryptCredentials.password);
            writableMapCreateMap.putString(Maps.STORAGE, cipherStorageByName.getCipherStorageName());
            promise.resolve(writableMapCreateMap);
        } catch (CryptoFailedException e) {
            Log.e(KEYCHAIN_MODULE, e.getMessage());
            promise.reject(Errors.E_CRYPTO_FAILED, e);
        } catch (KeyStoreAccessException e2) {
            Log.e(KEYCHAIN_MODULE, e2.getMessage());
            promise.reject(Errors.E_KEYSTORE_ACCESS_ERROR, e2);
        } catch (Throwable th) {
            Log.e(KEYCHAIN_MODULE, th.getMessage(), th);
            promise.reject(Errors.E_UNKNOWN_ERROR, th);
        }
    }

    @ReactMethod
    public void getAllGenericPasswordServices(Promise promise) {
        try {
            promise.resolve(Arguments.makeNativeArray(doGetAllGenericPasswordServices().toArray()));
        } catch (KeyStoreAccessException e) {
            promise.reject(Errors.E_KEYSTORE_ACCESS_ERROR, e);
        }
    }

    private Collection<String> doGetAllGenericPasswordServices() throws KeyStoreAccessException {
        Set<String> usedCipherNames = this.prefsStorage.getUsedCipherNames();
        ArrayList arrayList = new ArrayList(usedCipherNames.size());
        Iterator<String> it = usedCipherNames.iterator();
        while (it.hasNext()) {
            arrayList.add(getCipherStorageByName(it.next()));
        }
        HashSet hashSet = new HashSet();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            for (String str : ((CipherStorage) it2.next()).getAllKeys()) {
                if (!str.equals(WARMING_UP_ALIAS)) {
                    hashSet.add(str);
                }
            }
        }
        return hashSet;
    }

    @ReactMethod
    public void getGenericPasswordForOptions(ReadableMap readableMap, Promise promise) {
        getGenericPassword(getServiceOrDefault(readableMap), readableMap, promise);
    }

    protected void resetGenericPassword(String str, Promise promise) {
        CipherStorage cipherStorageByName;
        try {
            PrefsStorage.ResultSet encryptedEntry = this.prefsStorage.getEncryptedEntry(str);
            if (encryptedEntry != null && (cipherStorageByName = getCipherStorageByName(encryptedEntry.cipherStorageName)) != null) {
                cipherStorageByName.removeKey(str);
            }
            this.prefsStorage.removeEntry(str);
            promise.resolve(true);
        } catch (KeyStoreAccessException e) {
            Log.e(KEYCHAIN_MODULE, e.getMessage());
            promise.reject(Errors.E_KEYSTORE_ACCESS_ERROR, e);
        } catch (Throwable th) {
            Log.e(KEYCHAIN_MODULE, th.getMessage(), th);
            promise.reject(Errors.E_UNKNOWN_ERROR, th);
        }
    }

    @ReactMethod
    public void resetGenericPasswordForOptions(ReadableMap readableMap, Promise promise) {
        resetGenericPassword(getServiceOrDefault(readableMap), promise);
    }

    @ReactMethod
    public void hasInternetCredentialsForServer(String str, Promise promise) {
        String aliasOrDefault = getAliasOrDefault(str);
        PrefsStorage.ResultSet encryptedEntry = this.prefsStorage.getEncryptedEntry(aliasOrDefault);
        if (encryptedEntry == null) {
            Log.e(KEYCHAIN_MODULE, "No entry found for service: " + aliasOrDefault);
            promise.resolve(false);
        } else {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("service", aliasOrDefault);
            writableMapCreateMap.putString(Maps.STORAGE, encryptedEntry.cipherStorageName);
            promise.resolve(writableMapCreateMap);
        }
    }

    @ReactMethod
    public void setInternetCredentialsForServer(String str, String str2, String str3, ReadableMap readableMap, Promise promise) {
        setGenericPassword(str, str2, str3, readableMap, promise);
    }

    @ReactMethod
    public void getInternetCredentialsForServer(String str, ReadableMap readableMap, Promise promise) {
        getGenericPassword(str, readableMap, promise);
    }

    @ReactMethod
    public void resetInternetCredentialsForServer(String str, Promise promise) {
        resetGenericPassword(str, promise);
    }

    @ReactMethod
    public void getSupportedBiometryType(Promise promise) {
        try {
            Object obj = null;
            if (DeviceAvailability.isStrongBiometricAuthAvailable(getReactApplicationContext())) {
                if (isFingerprintAuthAvailable()) {
                    obj = FINGERPRINT_SUPPORTED_NAME;
                } else if (isFaceAuthAvailable()) {
                    obj = FACE_SUPPORTED_NAME;
                } else if (isIrisAuthAvailable()) {
                    obj = IRIS_SUPPORTED_NAME;
                }
            }
            promise.resolve(obj);
        } catch (Exception e) {
            Log.e(KEYCHAIN_MODULE, e.getMessage(), e);
            promise.reject(Errors.E_SUPPORTED_BIOMETRY_ERROR, e);
        } catch (Throwable th) {
            Log.e(KEYCHAIN_MODULE, th.getMessage(), th);
            promise.reject(Errors.E_UNKNOWN_ERROR, th);
        }
    }

    @ReactMethod
    public void getSecurityLevel(ReadableMap readableMap, Promise promise) {
        promise.resolve(getSecurityLevel(getUseBiometry(getAccessControlOrDefault(readableMap))).name());
    }

    private static String getServiceOrDefault(ReadableMap readableMap) {
        return getAliasOrDefault((readableMap == null || !readableMap.hasKey("service")) ? null : readableMap.getString("service"));
    }

    private static String getSecurityRulesOrDefault(ReadableMap readableMap) {
        return getSecurityRulesOrDefault(readableMap, "none");
    }

    private static String getSecurityRulesOrDefault(ReadableMap readableMap, String str) {
        String string = (readableMap == null || !readableMap.hasKey(Maps.RULES)) ? null : readableMap.getString(Maps.RULES);
        return string == null ? str : string;
    }

    private static String getSpecificStorageOrDefault(ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey(Maps.STORAGE)) {
            return null;
        }
        return readableMap.getString(Maps.STORAGE);
    }

    private static String getAccessControlOrDefault(ReadableMap readableMap) {
        return getAccessControlOrDefault(readableMap, AccessControl.NONE);
    }

    private static String getAccessControlOrDefault(ReadableMap readableMap, String str) {
        String string = (readableMap == null || !readableMap.hasKey(Maps.ACCESS_CONTROL)) ? null : readableMap.getString(Maps.ACCESS_CONTROL);
        return string == null ? str : string;
    }

    private static SecurityLevel getSecurityLevelOrDefault(ReadableMap readableMap) {
        return getSecurityLevelOrDefault(readableMap, SecurityLevel.ANY.name());
    }

    private static SecurityLevel getSecurityLevelOrDefault(ReadableMap readableMap, String str) {
        String string = (readableMap == null || !readableMap.hasKey(Maps.SECURITY_LEVEL)) ? null : readableMap.getString(Maps.SECURITY_LEVEL);
        if (string != null) {
            str = string;
        }
        return SecurityLevel.valueOf(str);
    }

    public static boolean getUseBiometry(String str) {
        return AccessControl.BIOMETRY_ANY.equals(str) || AccessControl.BIOMETRY_CURRENT_SET.equals(str) || AccessControl.BIOMETRY_ANY_OR_DEVICE_PASSCODE.equals(str) || AccessControl.BIOMETRY_CURRENT_SET_OR_DEVICE_PASSCODE.equals(str);
    }

    private void addCipherStorageToMap(CipherStorage cipherStorage) {
        this.cipherStorageMap.put(cipherStorage.getCipherStorageName(), cipherStorage);
    }

    private static BiometricPrompt.PromptInfo getPromptInfo(ReadableMap readableMap) {
        ReadableMap map = (readableMap == null || !readableMap.hasKey(Maps.AUTH_PROMPT)) ? null : readableMap.getMap(Maps.AUTH_PROMPT);
        BiometricPrompt.PromptInfo.Builder builder = new BiometricPrompt.PromptInfo.Builder();
        if (map != null && map.hasKey("title")) {
            builder.setTitle(map.getString("title"));
        }
        if (map != null && map.hasKey(AuthPromptOptions.SUBTITLE)) {
            builder.setSubtitle(map.getString(AuthPromptOptions.SUBTITLE));
        }
        if (map != null && map.hasKey("description")) {
            builder.setDescription(map.getString("description"));
        }
        if (map != null && map.hasKey(AuthPromptOptions.CANCEL)) {
            builder.setNegativeButtonText(map.getString(AuthPromptOptions.CANCEL));
        }
        builder.setAllowedAuthenticators(15);
        builder.setConfirmationRequired(false);
        return builder.build();
    }

    private CipherStorage.DecryptionResult decryptCredentials(String str, CipherStorage cipherStorage, PrefsStorage.ResultSet resultSet, String str2, BiometricPrompt.PromptInfo promptInfo) throws CryptoFailedException, KeyStoreAccessException {
        String str3 = resultSet.cipherStorageName;
        if (str3.equals(cipherStorage.getCipherStorageName())) {
            return decryptToResult(str, cipherStorage, resultSet, promptInfo);
        }
        CipherStorage cipherStorageByName = getCipherStorageByName(str3);
        if (cipherStorageByName == null) {
            throw new KeyStoreAccessException("Wrong cipher storage name '" + str3 + "' or cipher not available");
        }
        CipherStorage.DecryptionResult decryptionResultDecryptToResult = decryptToResult(str, cipherStorageByName, resultSet, promptInfo);
        if (Rules.AUTOMATIC_UPGRADE.equals(str2)) {
            try {
                migrateCipherStorage(str, cipherStorage, cipherStorageByName, decryptionResultDecryptToResult);
            } catch (CryptoFailedException unused) {
                Log.w(KEYCHAIN_MODULE, "Migrating to a less safe storage is not allowed. Keeping the old one");
            }
        }
        return decryptionResultDecryptToResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CipherStorage.DecryptionResult decryptToResult(String str, CipherStorage cipherStorage, PrefsStorage.ResultSet resultSet, BiometricPrompt.PromptInfo promptInfo) throws CryptoFailedException {
        DecryptionResultHandler interactiveHandler = getInteractiveHandler(cipherStorage, promptInfo);
        cipherStorage.decrypt(interactiveHandler, str, (byte[]) resultSet.username, (byte[]) resultSet.password, SecurityLevel.ANY);
        CryptoFailedException.reThrowOnError(interactiveHandler.getError());
        if (interactiveHandler.getResult() == null) {
            throw new CryptoFailedException("No decryption results and no error. Something deeply wrong!");
        }
        return interactiveHandler.getResult();
    }

    protected DecryptionResultHandler getInteractiveHandler(CipherStorage cipherStorage, BiometricPrompt.PromptInfo promptInfo) {
        return DecryptionResultHandlerProvider.getHandler(getReactApplicationContext(), cipherStorage, promptInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void migrateCipherStorage(String str, CipherStorage cipherStorage, CipherStorage cipherStorage2, CipherStorage.DecryptionResult decryptionResult) throws CryptoFailedException, KeyStoreAccessException {
        this.prefsStorage.storeEncryptedEntry(str, cipherStorage.encrypt(str, (String) decryptionResult.username, (String) decryptionResult.password, decryptionResult.getSecurityLevel()));
        cipherStorage2.removeKey(str);
    }

    CipherStorage getCipherStorageForCurrentAPILevel() throws CryptoFailedException {
        return getCipherStorageForCurrentAPILevel(true);
    }

    CipherStorage getCipherStorageForCurrentAPILevel(boolean z) throws CryptoFailedException {
        int i = Build.VERSION.SDK_INT;
        boolean z2 = z && (isFingerprintAuthAvailable() || isFaceAuthAvailable() || isIrisAuthAvailable());
        CipherStorage cipherStorage = null;
        for (CipherStorage cipherStorage2 : this.cipherStorageMap.values()) {
            Log.d(KEYCHAIN_MODULE, "Probe cipher storage: " + cipherStorage2.getClass().getSimpleName());
            int minSupportedApiLevel = cipherStorage2.getMinSupportedApiLevel();
            int capabilityLevel = cipherStorage2.getCapabilityLevel();
            if (minSupportedApiLevel <= i && (cipherStorage == null || capabilityLevel >= cipherStorage.getCapabilityLevel())) {
                if (!cipherStorage2.isBiometrySupported() || z2) {
                    cipherStorage = cipherStorage2;
                }
            }
        }
        if (cipherStorage == null) {
            throw new CryptoFailedException("Unsupported Android SDK " + Build.VERSION.SDK_INT);
        }
        Log.d(KEYCHAIN_MODULE, "Selected storage: " + cipherStorage.getClass().getSimpleName());
        return cipherStorage;
    }

    public static void throwIfEmptyLoginPassword(String str, String str2) throws EmptyParameterException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new EmptyParameterException("you passed empty or null username/password");
        }
    }

    public static void throwIfInsufficientLevel(CipherStorage cipherStorage, SecurityLevel securityLevel) throws CryptoFailedException {
        if (!cipherStorage.securityLevel().satisfiesSafetyThreshold(securityLevel)) {
            throw new CryptoFailedException(String.format("Cipher Storage is too weak. Required security level is: %s, but only %s is provided", securityLevel.name(), cipherStorage.securityLevel().name()));
        }
    }

    CipherStorage getCipherStorageByName(String str) {
        return this.cipherStorageMap.get(str);
    }

    boolean isFingerprintAuthAvailable() {
        return DeviceAvailability.isStrongBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFingerprintAuthAvailable(getReactApplicationContext());
    }

    boolean isFaceAuthAvailable() {
        return DeviceAvailability.isStrongBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFaceAuthAvailable(getReactApplicationContext());
    }

    boolean isIrisAuthAvailable() {
        return DeviceAvailability.isStrongBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isIrisAuthAvailable(getReactApplicationContext());
    }

    boolean isSecureHardwareAvailable() {
        try {
            return getCipherStorageForCurrentAPILevel().supportsSecureHardware();
        } catch (CryptoFailedException unused) {
            return false;
        }
    }

    private SecurityLevel getSecurityLevel(boolean z) {
        try {
            CipherStorage cipherStorageForCurrentAPILevel = getCipherStorageForCurrentAPILevel(z);
            if (!cipherStorageForCurrentAPILevel.securityLevel().satisfiesSafetyThreshold(SecurityLevel.SECURE_SOFTWARE)) {
                return SecurityLevel.ANY;
            }
            if (cipherStorageForCurrentAPILevel.supportsSecureHardware()) {
                return SecurityLevel.SECURE_HARDWARE;
            }
            return SecurityLevel.SECURE_SOFTWARE;
        } catch (CryptoFailedException e) {
            Log.w(KEYCHAIN_MODULE, "Security Level Exception: " + e.getMessage(), e);
            return SecurityLevel.ANY;
        }
    }
}
