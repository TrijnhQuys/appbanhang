package com.heliwr.appbandogiadung.Model;

import java.io.Serializable;

public class SPTHIETBI implements Serializable {
    public String macdsanpham, tensanpham, imgsanpham;

    public SPTHIETBI(String macdthietbi, String tensanpham, String imgsanpham) {
        this.macdsanpham = macdthietbi;
        this.tensanpham = tensanpham;
        this.imgsanpham = imgsanpham;
    }

    public SPTHIETBI() {
    }
}
