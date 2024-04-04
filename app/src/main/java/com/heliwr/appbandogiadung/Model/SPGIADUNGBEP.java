package com.heliwr.appbandogiadung.Model;

import java.io.Serializable;

public class SPGIADUNGBEP implements Serializable {
    public String macdsanpham, tensanpham, imgsanpham;

    public SPGIADUNGBEP(String macdgiadung, String tensanpham, String imgsanpham) {
        this.macdsanpham = macdgiadung;
        this.tensanpham = tensanpham;
        this.imgsanpham = imgsanpham;
    }

    public SPGIADUNGBEP() {
    }
}
