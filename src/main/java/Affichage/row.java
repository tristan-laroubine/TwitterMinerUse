package Affichage;

import javax.swing.text.TableView;

public class row {
    private String str1;
    private String str2;
    private Double freq;
    private Double conf;
    private Double lift;


    public row(String str1, String str2, Double conf,Double freq, Double lift) {
        this.str1 = str1;
        this.str2 = str2;
        this.conf = conf;
        this.lift = lift;
        this.freq = freq;
    }

    public String getStr1() {
        return str1;
    }

    public String getStr2() {
        return str2;
    }

    public Double getConf() {
        return conf;
    }

    public Double getLift() {
        return lift;
    }

    public Double getFreq() {
        return freq;
    }
}
