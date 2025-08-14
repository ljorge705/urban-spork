package dialoobnhfjfdhmrftlq;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class cptipfepeuixrxtabhzr implements Serializable {
    private Double ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private Long uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public Long ctfsaqlysacfjtqixtmr() {
        return this.uusbetktgiikylwfbevs;
    }

    public Double dbuymyhwehsdoxafsfpy() {
        return this.ctfsaqlysacfjtqixtmr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cptipfepeuixrxtabhzr)) {
            return false;
        }
        cptipfepeuixrxtabhzr cptipfepeuixrxtabhzrVar = (cptipfepeuixrxtabhzr) obj;
        if (!cptipfepeuixrxtabhzrVar.yvrzbryuycempgkdhpvj(this)) {
            return false;
        }
        Long lCtfsaqlysacfjtqixtmr = ctfsaqlysacfjtqixtmr();
        Long lCtfsaqlysacfjtqixtmr2 = cptipfepeuixrxtabhzrVar.ctfsaqlysacfjtqixtmr();
        if (lCtfsaqlysacfjtqixtmr != null ? !lCtfsaqlysacfjtqixtmr.equals(lCtfsaqlysacfjtqixtmr2) : lCtfsaqlysacfjtqixtmr2 != null) {
            return false;
        }
        Double dDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        Double dDbuymyhwehsdoxafsfpy2 = cptipfepeuixrxtabhzrVar.dbuymyhwehsdoxafsfpy();
        if (dDbuymyhwehsdoxafsfpy != null ? !dDbuymyhwehsdoxafsfpy.equals(dDbuymyhwehsdoxafsfpy2) : dDbuymyhwehsdoxafsfpy2 != null) {
            return false;
        }
        String strVjgujdxqyzpnlimdrvvt = vjgujdxqyzpnlimdrvvt();
        String strVjgujdxqyzpnlimdrvvt2 = cptipfepeuixrxtabhzrVar.vjgujdxqyzpnlimdrvvt();
        if (strVjgujdxqyzpnlimdrvvt != null ? !strVjgujdxqyzpnlimdrvvt.equals(strVjgujdxqyzpnlimdrvvt2) : strVjgujdxqyzpnlimdrvvt2 != null) {
            return false;
        }
        String strUusbetktgiikylwfbevs = uusbetktgiikylwfbevs();
        String strUusbetktgiikylwfbevs2 = cptipfepeuixrxtabhzrVar.uusbetktgiikylwfbevs();
        return strUusbetktgiikylwfbevs != null ? strUusbetktgiikylwfbevs.equals(strUusbetktgiikylwfbevs2) : strUusbetktgiikylwfbevs2 == null;
    }

    public int hashCode() {
        Long lCtfsaqlysacfjtqixtmr = ctfsaqlysacfjtqixtmr();
        int iHashCode = lCtfsaqlysacfjtqixtmr == null ? 43 : lCtfsaqlysacfjtqixtmr.hashCode();
        Double dDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy();
        int iHashCode2 = ((iHashCode + 59) * 59) + (dDbuymyhwehsdoxafsfpy == null ? 43 : dDbuymyhwehsdoxafsfpy.hashCode());
        String strVjgujdxqyzpnlimdrvvt = vjgujdxqyzpnlimdrvvt();
        int iHashCode3 = (iHashCode2 * 59) + (strVjgujdxqyzpnlimdrvvt == null ? 43 : strVjgujdxqyzpnlimdrvvt.hashCode());
        String strUusbetktgiikylwfbevs = uusbetktgiikylwfbevs();
        return (iHashCode3 * 59) + (strUusbetktgiikylwfbevs != null ? strUusbetktgiikylwfbevs.hashCode() : 43);
    }

    public String toString() {
        return "{\n\t \"userId\": \"" + this.yvrzbryuycempgkdhpvj + "\",\n\t\"destinationId\": \"" + this.dbuymyhwehsdoxafsfpy + "\",\n\t\"amount\": " + this.ctfsaqlysacfjtqixtmr + ",\n\t\"lastTransTimestamp\": \"" + this.uusbetktgiikylwfbevs + "\"\n}";
    }

    public String uusbetktgiikylwfbevs() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public String vjgujdxqyzpnlimdrvvt() {
        return this.yvrzbryuycempgkdhpvj;
    }

    protected boolean yvrzbryuycempgkdhpvj(Object obj) {
        return obj instanceof cptipfepeuixrxtabhzr;
    }
}
