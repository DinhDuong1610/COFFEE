package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_NhanVien;
import model.Model_Sach;

public class DBSach {
	private final Connection con;
	private static DBSach instance;
	private final String INSERT_SACH = "INSERT INTO sach (ten, TheLoai, TacGia, slTonKho, slDaBan, DonGia, HinhAnh) VALUES (?,?,?,?,?,?,?)";
	private final String SELECT_SACH = "SELECT masach, ten, TheLoai, TacGia, slTonKho, slDaBan, DonGia, hinhAnh FROM sach";
	private final String UPDATE_THONGTIN = "UPDATE sach SET ten=?, theloai=?, tacgia=?,sltonkho=?, sldaban=?, dongia=? WHERE masach=?";
	private final String DELETE_SACH = "DELETE FROM sach WHERE maSach=?";
	private final String SELECT_IMAGE = "SELECT hinhanh FROM sach WHERE masach=?";
	private final String SELECT_TIMKIEM_SACH = "SELECT masach, ten, TheLoai, TacGia, slTonKho, slDaBan, DonGia FROM sach WHERE ten LIKE ?";
	private final String UPDATE_SOLUONG = "UPDATE sach SET sltonkho=?, sldaban=? WHERE masach=?";
	private final String SELECT_TIMKIEM_MASACH = "SELECT masach, ten, TheLoai, TacGia, slTonKho, slDaBan, DonGia FROM sach WHERE masach=?";
	
	public static DBSach getInstance() {
		if(instance == null) {
			instance = new DBSach();
		}
		return instance;
	}
	
	public DBSach() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_Sach> loadSach() {
		ArrayList<Model_Sach> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_SACH);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	String tacgia = r.getString(4);
            	int tonkho = r.getInt(5);
            	int daban = r.getInt(6);
            	int dongia = r.getInt(7);
                Blob blob = r.getBlob(8);
                byte[] hinhAnh = blob.getBytes(1, (int) blob.length());
            	
				Model_Sach sach = new Model_Sach(ma, ten, theloai, tacgia, tonkho, daban, dongia, hinhAnh);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Sach themSach(Model_Sach sach) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_SACH, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, sach.getTen());
            p.setString(2, sach.getTheLoai());
            p.setString(3, sach.getTacGia());
            p.setInt(4, sach.getSlTonKho());
            p.setInt(5, sach.getSlDaBan());
            p.setInt(6, sach.getDonGia());
            p.setBytes(7, sach.getHinhAnh());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maSach = r.getInt(1);
            sach.setMaSach(maSach);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm sách thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return sach;
	}
	
	public Model_Sach suaThongTin(Model_Sach sach) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, sach.getTen());
            p.setString(2, sach.getTheLoai());
            p.setString(3, sach.getTacGia());
            p.setInt(4, sach.getSlTonKho());
            p.setInt(5, sach.getSlDaBan());
            p.setInt(6, sach.getDonGia());
            p.setInt(7, sach.getMaSach());
                        
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
	
	public ArrayList<Model_Sach> locSach(String dieukien) {
		ArrayList<Model_Sach> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_SACH + " WHERE " + dieukien);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	String tacgia = r.getString(4);
            	int tonkho = r.getInt(5);
            	int daban = r.getInt(6);
            	int dongia = r.getInt(7);
            	
				Model_Sach sach = new Model_Sach(ma, ten, theloai, tacgia, tonkho, daban, dongia, null);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_Sach> timkiem(String tenSach) {
		ArrayList<Model_Sach> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_SACH);
            p.setString(1, tenSach);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	String tacgia = r.getString(4);
            	int tonkho = r.getInt(5);
            	int daban = r.getInt(6);
            	int dongia = r.getInt(7);
            	
				Model_Sach sach = new Model_Sach(ma, ten, theloai, tacgia, tonkho, daban, dongia, null);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Sach timkiemMaSach(int maSach) {
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_MASACH);
            p.setInt(1, maSach);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	String tacgia = r.getString(4);
            	int tonkho = r.getInt(5);
            	int daban = r.getInt(6);
            	int dongia = r.getInt(7);
            	
				Model_Sach sach = new Model_Sach(ma, ten, theloai, tacgia, tonkho, daban, dongia, null);
				return sach;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public Model_Sach updateSoLuong(int ma, int soluong) {
		Model_Sach sach = timkiemMaSach(ma);
		int tonkho = sach.getSlTonKho();
		int daban = sach.getSlDaBan();
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_SOLUONG);
            p.setInt(1, tonkho-soluong);
            p.setInt(2, daban+soluong);
            p.setInt(3, ma);
                        
            p.execute();
            p.close();
         } catch (SQLException e) {
          	e.printStackTrace();
          }
        return sach;
	}
}
