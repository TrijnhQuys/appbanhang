package com.heliwr.appbandogiadung.Model;

import java.io.Serializable;

public class GIOHANG implements Serializable {
    public  int  soluong, masanpham;
    public  long dongia;
    public String  tensanpham,mota,imgsanpham, macdsanpham, cuahangcon, xuatxu, baohanh;

    public GIOHANG() {
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int  masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getDongia() {
        return dongia;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
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
