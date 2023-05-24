package com.example.quanlysv.servlet.dto.request.lop;

import com.example.quanlysv.servlet.dto.request.BaseRequest;

public class LopFilter {
    private BaseRequest baseRequest;
    private String idKhoa,tenLop;

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public String getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(String idKhoa) {
        this.idKhoa = idKhoa;
    }
}
