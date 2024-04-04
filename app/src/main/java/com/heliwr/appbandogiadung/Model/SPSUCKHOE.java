package com.heliwr.appbandogiadung.Model;

import java.io.Serializable;

public class SPSUCKHOE implements Serializable {
    public String macdsanpham, tensanpham, imgsanpham;

    public SPSUCKHOE(String macdsuckhoe, String tensanpham, String imgsanpham) {
        this.macdsanpham = macdsuckhoe;
        this.tensanpham = tensanpham;
        this.imgsanpham = imgsanpham;
    }

    public SPSUCKHOE() {
    }
}
