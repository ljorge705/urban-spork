package com.uxcam.env;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes6.dex */
public class Environment {
    private static final /* synthetic */ Environment[] $VALUES;
    public static final Environment ALPHA;
    public static final Environment BETA;
    public static final Environment RELEASE;

    public enum aa extends Environment {
        public aa() {
            super("ALPHA", 0, null);
        }

        @Override // java.lang.Enum
        public final String toString() {
            return ".alpha";
        }
    }

    static {
        aa aaVar = new aa();
        ALPHA = aaVar;
        Environment environment = new Environment() { // from class: com.uxcam.env.Environment.ab
            @Override // java.lang.Enum
            public final String toString() {
                return ".beta";
            }
        };
        BETA = environment;
        Environment environment2 = new Environment() { // from class: com.uxcam.env.Environment.ac
            @Override // java.lang.Enum
            public final String toString() {
                return ".release";
            }
        };
        RELEASE = environment2;
        $VALUES = new Environment[]{aaVar, environment, environment2};
    }

    private Environment(String str, int i) {
    }

    public static Environment valueOf(String str) {
        return (Environment) Enum.valueOf(Environment.class, str);
    }

    public static Environment[] values() {
        return (Environment[]) $VALUES.clone();
    }

    public /* synthetic */ Environment(String str, int i, aa aaVar) {
        this(str, i);
    }
}
