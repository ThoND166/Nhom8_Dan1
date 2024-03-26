package com.thondph16247.nhom8.DTO;

public class LoaiTraiCayDTO {
    int id;
    String tenLoai;

    public LoaiTraiCayDTO() {
    }

    public LoaiTraiCayDTO(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id     = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
