package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_NhanVien;
import model.Model_Nuoc;

public class DBNuoc {
	private final Connection con;
	private static DBNuoc instance;
	private final String INSERT_SACH = "INSERT INTO nuoc (ten, Loai, DonGia, HinhAnh) VALUES (?,?,?,?)";
	private final String SELECT_SACH = "SELECT maNuoc, ten, loai, DonGia, hinhAnh FROM nuoc";
	private final String UPDATE_THONGTIN = "UPDATE nuoc SET ten=?, loai=?, dongia=? WHERE maNuoc=?";
	private final String DELETE_SACH = "DELETE FROM nuoc WHERE maNuoc=?";
	private final String SELECT_IMAGE = "SELECT hinhanh FROM nuoc WHERE manuoc=?";
	private final String SELECT_TIMKIEM_SACH = "SELECT manuoc, ten, Loai, DonGia FROM nuoc WHERE ten LIKE ?";
	private final String SELECT_TIMKIEM_MASACH = "SELECT manuoc, ten, Loai, DonGia FROM nuoc WHERE maNuoc=?";
	
	public static DBNuoc getInstance() {
		if(instance == null) {
			instance = new DBNuoc();
		}
		return instance;
	}
	
	public DBNuoc() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_Nuoc> loadSach() {
		ArrayList<Model_Nuoc> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_SACH);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int dongia = r.getInt(4);
                Blob blob = r.getBlob(5);
                byte[] hinhAnh = blob.getBytes(1, (int) blob.length());
            	
				Model_Nuoc sach = new Model_Nuoc(ma, ten, theloai, dongia, hinhAnh);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Nuoc themSach(Model_Nuoc sach) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_SACH, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, sach.getTen());
            p.setString(2, sach.getLoai());
            p.setInt(3, sach.getDonGia());
            p.setBytes(4, sach.getHinhAnh());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maSach = r.getInt(1);
            sach.setMaNuoc(maSach);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm đồ uống thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm đồ uống thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return sach;
	}
	
	public Model_Nuoc suaThongTin(Model_Nuoc sach) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, sach.getTen());
            p.setString(2, sach.getLoai());
            p.setInt(3, sach.getDonGia());
            p.setInt(4, sach.getMaNuoc());
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return sach;
	}
	
	public void xoaSach(int maSach) {
        try {
            PreparedStatement p = con.prepareStatement(DELETE_SACH);
            p.setInt(1, maSach);
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa sách thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
	}
	
	public byte[] getImage(int maSach) {
        try {
            PreparedStatement p = con.prepareStatement(SELECT_IMAGE);
            p.setInt(1, maSach);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Blob blob = r.getBlob(1);
                return blob.getBytes(1, (int) blob.length());
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public ArrayList<Model_Nuoc> locSach(String dieukien) {
		ArrayList<Model_Nuoc> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_SACH + " WHERE " + dieukien);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int dongia = r.getInt(4);
            	
				Model_Nuoc sach = new Model_Nuoc(ma, ten, theloai, dongia, null);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_Nuoc> timkiem(String tenSach) {
		ArrayList<Model_Nuoc> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_SACH);
            p.setString(1, tenSach);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int dongia = r.getInt(4);
            	
				Model_Nuoc sach = new Model_Nuoc(ma, ten, theloai, dongia, null);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Nuoc timkiemMaSach(int maSach) {
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_MASACH);
            p.setInt(1, maSach);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int dongia = r.getInt(4);
            	
				Model_Nuoc sach = new Model_Nuoc(ma, ten, theloai, dongia, null);
				return sach;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
}
