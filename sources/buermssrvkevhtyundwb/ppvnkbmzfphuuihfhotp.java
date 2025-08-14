package buermssrvkevhtyundwb;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class ppvnkbmzfphuuihfhotp implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Double ctfsaqlysacfjtqixtmr;
    private final String cwzojhoqdsccekmlpbcq;
    private final String danumarvmgpbarrzqyrz;
    private Double dbuymyhwehsdoxafsfpy;
    private Map dyrapphjndqarxdhyvgv;
    private Map efmnkxwvqeqnaehsyess;
    private final String ldeiitcdqlqzkidvrbjy;
    private lmqztflyazmtuunswyyl mxodkqpwhcryvsgsvabl;
    private final String ooztjhejjvpgrdhjnyju;
    private final String vikftlgmuszlvyjnlikz;
    private lmqztflyazmtuunswyyl vjgujdxqyzpnlimdrvvt;
    private Integer yvrzbryuycempgkdhpvj = 0;
    private Integer uusbetktgiikylwfbevs = 0;

    protected ppvnkbmzfphuuihfhotp(String str) {
        Double dValueOf = Double.valueOf(0.0d);
        this.dbuymyhwehsdoxafsfpy = dValueOf;
        this.ctfsaqlysacfjtqixtmr = dValueOf;
        this.vjgujdxqyzpnlimdrvvt = new lmqztflyazmtuunswyyl();
        this.mxodkqpwhcryvsgsvabl = new lmqztflyazmtuunswyyl();
        this.cwzojhoqdsccekmlpbcq = str;
        this.ooztjhejjvpgrdhjnyju = String.format("transactions_type_%s_count", str);
        this.vikftlgmuszlvyjnlikz = String.format("transactions_%s_count", str);
        this.danumarvmgpbarrzqyrz = String.format("transactions_%s_sum_amount", str);
        this.ldeiitcdqlqzkidvrbjy = String.format("transactions_type_%s_sum_amount", str);
        this.dyrapphjndqarxdhyvgv = new HashMap();
        this.efmnkxwvqeqnaehsyess = new HashMap();
    }
}
