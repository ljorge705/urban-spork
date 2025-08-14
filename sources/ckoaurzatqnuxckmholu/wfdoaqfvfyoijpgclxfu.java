package ckoaurzatqnuxckmholu;

import android.content.Context;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class wfdoaqfvfyoijpgclxfu {
    private Context efmnkxwvqeqnaehsyess;
    private String yvrzbryuycempgkdhpvj;
    ExecutorService dbuymyhwehsdoxafsfpy = Executors.newSingleThreadExecutor();
    ExecutorService uusbetktgiikylwfbevs = Executors.newSingleThreadExecutor();
    ExecutorService ctfsaqlysacfjtqixtmr = Executors.newSingleThreadExecutor();
    ExecutorService vjgujdxqyzpnlimdrvvt = Executors.newSingleThreadExecutor();
    ExecutorService mxodkqpwhcryvsgsvabl = Executors.newSingleThreadExecutor();

    public wfdoaqfvfyoijpgclxfu(Context context, String str) {
        this.efmnkxwvqeqnaehsyess = context;
        this.yvrzbryuycempgkdhpvj = str;
    }

    public void ctfsaqlysacfjtqixtmr(omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        new zwlltpaufqribmleigux(this.efmnkxwvqeqnaehsyess, "sendSessionData", fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(), fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()).yvrzbryuycempgkdhpvj(this.mxodkqpwhcryvsgsvabl, this.yvrzbryuycempgkdhpvj + "pmfd-med/3.0/SendSessionData/", fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), Boolean.TRUE, oacciftezlubzxpkwvycVar, UUID.randomUUID().toString());
    }

    public void dbuymyhwehsdoxafsfpy(omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        new zwlltpaufqribmleigux(this.efmnkxwvqeqnaehsyess, "getSdkConfiguration", fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(), fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()).yvrzbryuycempgkdhpvj(this.uusbetktgiikylwfbevs, this.yvrzbryuycempgkdhpvj + "pmfd-med/3.0/getSdkConfiguration/", fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), Boolean.TRUE, oacciftezlubzxpkwvycVar, UUID.randomUUID().toString());
    }

    public void uusbetktgiikylwfbevs(omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        new zwlltpaufqribmleigux(this.efmnkxwvqeqnaehsyess, "sendCrashData", fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(), fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()).yvrzbryuycempgkdhpvj(this.ctfsaqlysacfjtqixtmr, this.yvrzbryuycempgkdhpvj + "pmfd-med/3.0/CrashLogging/", fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), Boolean.TRUE, oacciftezlubzxpkwvycVar, UUID.randomUUID().toString());
    }

    public void vjgujdxqyzpnlimdrvvt(omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        new zwlltpaufqribmleigux(this.efmnkxwvqeqnaehsyess, "sendTracing", fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(), fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()).yvrzbryuycempgkdhpvj(this.vjgujdxqyzpnlimdrvvt, this.yvrzbryuycempgkdhpvj + "pmfd-med/3.0/TracingData/", fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), Boolean.TRUE, oacciftezlubzxpkwvycVar, UUID.randomUUID().toString());
    }

    public void yvrzbryuycempgkdhpvj(omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        new zwlltpaufqribmleigux(this.efmnkxwvqeqnaehsyess, "getDeviceID", fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(), fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()).yvrzbryuycempgkdhpvj(this.dbuymyhwehsdoxafsfpy, this.yvrzbryuycempgkdhpvj + "pmfd-med/3.0/GetDeviceID/", fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), Boolean.TRUE, oacciftezlubzxpkwvycVar, UUID.randomUUID().toString());
    }
}
