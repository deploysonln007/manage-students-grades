package com.example.quanlysv.servlet.dao.impl;

import com.example.quanlysv.servlet.dao.IWatchGradeDAO;
import com.example.quanlysv.servlet.entity.GradeEntity;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class WatchGradeDaoImpl  extends AbstractDao<GradeEntity>  implements IWatchGradeDAO {



    public Double ConvertDiemTB( Double x) {
        if(Double.compare(4.0,x)>0)return 0.0;
        else if(Double.compare(5.0,x)>0)return 1.0;
        else if(Double.compare(5.5,x)>0)return 1.5;
        else if(Double.compare(6.5,x)>0)return 2.0;
        else if(Double.compare(7.0,x)>0)return 2.5;
        else if(Double.compare(8.0,x)>0)return 3.0;
        else if(Double.compare(8.5,x)>0)return 3.5;
        else if(Double.compare(9.0,x)>0)return 3.7;
        return 4.0;
    }

    public String ConvertTrangThai ( Double x ){
        if(Double.compare(1.0,x)>0)return "Trượt môn";
        return "Qua môn";
    }

    public String ConvertDiemChu (Double x){
        Double tolerance = 0.001;
        if(Math.abs( 0.0 - x ) <= tolerance) return "F";
        else if(Math.abs( 1.0 - x ) <= tolerance)return "D";
        else if(Math.abs( 1.5 - x ) <= tolerance)return "D+";
        else if(Math.abs( 2.0 - x ) <= tolerance)return "C";
        else if(Math.abs( 2.5 - x ) <= tolerance)return "C+";
        else if(Math.abs( 3.0 - x ) <= tolerance)return "B";
        else if(Math.abs( 3.5 - x ) <= tolerance)return "B+";
        else if(Math.abs( 3.7 - x ) <= tolerance)return "A";
        return "A+";
    }
    ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    @Override
    public List<WacthGradeEntity> getGradeByStudentId(String studentId) {
        List<WacthGradeEntity> grades = new ArrayList<>();

        String url = resourceBundle.getString("url");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        String query = "SELECT DISTINCT  ten_mon_hoc,tin_chi, diem_cc, diem_bt, diem_kt,diem_thi, chuyen_can, bai_tap,kiem_tra, thi  FROM diem, monhoc, taikhoan , thanhphan WHERE diem.id_sv =  ? and diem.id_mh = monhoc.id_mh and diem.id_diem = thanhphan.id_tp";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            Integer stt = 1;
            while (resultSet.next()) {
                WacthGradeEntity grade = new WacthGradeEntity();
                 String tenMonHoc = resultSet.getString("ten_mon_hoc");
                 Double diemBaiTap = resultSet.getDouble("diem_bt");
                 Double diemChuyenCan = resultSet.getDouble("diem_cc");
                 Double diemKiemTra = resultSet.getDouble("diem_kt");
                 Double diemThi = resultSet.getDouble("diem_thi");

                Integer soTinChi = resultSet.getInt("tin_chi");
                Integer ptChuyenCan = resultSet.getInt("chuyen_can");
                Integer ptBaiTap = resultSet.getInt("bai_tap");
                Integer ptKiemTra = resultSet.getInt("kiem_tra");
                Integer ptThi = resultSet.getInt("thi");

                Double diemTBthang10 = (diemChuyenCan*ptChuyenCan + diemBaiTap * ptBaiTap + diemThi * ptThi + diemKiemTra * ptKiemTra) /100;
                Double diemTBthang4 = ConvertDiemTB(diemTBthang10);
                String diemTBChu = ConvertDiemChu(diemTBthang4);
                String trangThai = ConvertTrangThai(diemTBthang4);


                grade.setStt(stt);
                grade.setTenMonHoc(tenMonHoc);
                grade.setSoTinChi(soTinChi);
                grade.setPtCC(ptChuyenCan);
                grade.setPtBT(ptBaiTap);
                grade.setPtKT(ptKiemTra);
                grade.setPtThi(ptThi);
                grade.setDiemCC(diemChuyenCan);
                grade.setDiemBT(diemBaiTap);
                grade.setDiemKT(diemKiemTra);
                grade.setDiemThi(diemThi);
                grade.setDiemTBthang10(diemTBthang10);
                grade.setDiemTBdangChu(diemTBChu);
                grade.setDiemTBthang4(diemTBthang4);
                grade.setTrangThai(trangThai);


                stt++;
//                System.out.println(grade.toString());

                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

}
