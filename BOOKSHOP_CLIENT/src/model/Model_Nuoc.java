package model;

import org.json.JSONObject;

public class Model_Nuoc {
	private int maNuoc;
	private String ten;
	private String loai;
	private int donGia;
	private byte[] hinhAnh;
	
	public Model_Nuoc(int maNuoc, String ten, String loai, int donGia, byte[] hinhAnh) {
		this.maNuoc = maNuoc;
		this.ten = ten;
		this.loai = loai;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
	}

	public Model_Nuoc(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	maNuoc = obj.getInt("maNuoc");
        	ten = obj.getString("ten");
        	loai = obj.getString("loai");
        	donGia = obj.getInt("donGia");
        	hinhAnh = convertHexStringToByteArray(obj.getString("hinhAnh"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("maNuoc", maNuoc);
			json.put("ten", ten);
			json.put("loai", loai);
			json.put("donGia", donGia);
			json.put("hinhAnh", convertByteArrayToHexString(hinhAnh));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    private String convertByteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private byte[] convertHexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

	public int getMaNuoc() {
		return maNuoc;
	}

	public void setMaNuoc(int maNuoc) {
		this.maNuoc = maNuoc;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

    


}