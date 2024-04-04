package com.heliwr.appbandogiadung.Model;

import java.io.Serializable;

public class ALLSANPHAM implements Serializable {
    int masanpham;
    public String  tensanpham, dongia,mota,imgsanpham, macdsanpham, cuahangcon, xuatxu, baohanh;
    int soluong;

    public ALLSANPHAM(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public ALLSANPHAM(int masanpham, String tensanpham, String dongia, String mota, String imgsanpham, String macdsanpham, String cuahangcon, String xuatxu, String baohanh) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.dongia = dongia;
        this.mota = mota;
        this.imgsanpham = imgsanpham;
        this.macdsanpham = macdsanpham;
        this.cuahangcon = cuahangcon;
        this.xuatxu = xuatxu;
        this.baohanh = baohanh;
    }

    public ALLSANPHAM() {
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getImgsanpham() {
        return imgsanpham;
    }

    public void setImgsanpham(String imgsanpham) {
        this.imgsanpham = imgsanpham;
    }

    public String getMacdsanpham() {
        return macdsanpham;
    }

    public void setMacdsanpham(String macdsanpham) {
        this.macdsanpham = macdsanpham;
    }

    public String getCuahangcon() {
        return cuahangcon;
    }

    public void setCuahangcon(String cuahangcon) {
        this.cuahangcon = cuahangcon;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getBaohanh() {
        return baohanh;
    }

    public void setBaohanh(String baohanh) {
        this.baohanh = baohanh;
    }
}
