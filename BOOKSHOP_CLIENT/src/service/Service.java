package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Model_DonMua;
import model.Model_KhachHang;
import model.Model_Register;
import model.Model_Nuoc;
import view.Main;

public class Service {
	private static Service instance;
	private Socket client;
	private final int PORT_NUMBER = 1610;
	private final String IP = "localhost";
	private BufferedReader in;
	private DataOutputStream out;
	private Main main;
	
	public static Service getInstance(Main main) {
		if(instance == null) {
			instance = new Service(main);
		}
		return instance;
	}
	
    public static Service getInstance() {
    	return instance;
    }
	
	private Service(Main main) {
		this.main = main;
	}
	
    public void startClient(){
    	try {
        	client = new Socket(IP, PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(client.getInputStream() , StandardCharsets.UTF_8));
            out = new DataOutputStream(client.getOutputStream());
		} catch (Exception e2) {
			
		}
        new Thread(() -> {
            while (true) {
                try {
                    String message;
                    synchronized (in) {
                        message = in.readLine();
                    }                  
                    if (message != null) {
                        System.out.println("client: " + message + "\n");
                        listen(message);
                    } else {
                        System.out.println("Client disconnected");
                        break;
                    }
                } catch (Exception e) {

                }
            }
        }).start();    
    }
    
    public void listen(String newdata) {
    	JSONObject jsonData;
    	String data=new String(newdata);
		try {
			jsonData = new JSONObject(data);
			
	    	if(jsonData.getString("type").equals("register")) {
	    		main.getLogin().checkRegister(jsonData.getBoolean("check"));
	    	}
	    	else if(jsonData.getString("type").equals("login")) {
	    		main.getLogin().checkLogin(jsonData.getBoolean("check"));
	    	}
	    	else if(jsonData.getString("type").equals("listSach")) {
	    		main.getBody().getListSach().removeAll();
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Nuoc sach = new Model_Nuoc(json);
	                main.getBody().addSach(sach);
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("update")) {
	    		String nhanVien = jsonData.getString("nhanVien");
	    		int quay = jsonData.getInt("quay");
	    		main.getMenuLeft().update(nhanVien, quay);
	    	}
	    	else if(jsonData.getString("type").equals("tracuu_true")) {
	    		Model_KhachHang khachhang = new Model_KhachHang(jsonData);
	    		main.getMenuLeft().tracuu(khachhang);
	    	}
	    	else if(jsonData.getString("type").equals("tracuu_false")) {
	            JOptionPane.showMessageDialog(null, "Khách hàng chưa là thành viên của shop!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    	else if(jsonData.getString("type").equals("themThanhVien")) {
	    		Model_KhachHang khachhang = new Model_KhachHang(jsonData);
	    		main.getMenuLeft().tracuu(khachhang);
	    	}
	    	else if(jsonData.getString("type").equals("tang")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("array");
	            boolean[] tang = new boolean[jsonArray.length()];
	            for (int i = 0; i < jsonArray.length(); i++) {
	                tang[i] = jsonArray.getBoolean(i);
	            }
	            main.getBody().tang = tang;
	            main.getBody().addBan(tang);
	    	}
	    	
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void register(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void login(JSONObject jsonData, int quay) {
    	try {
			jsonData.put("quay", quay);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void themNhanVien(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void tracuu(String sdt) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "tracuu");
			json.put("sdt", sdt);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void themThanhVien(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void xuatHoaDonKhachHang(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void xuatHoaDonSach(ArrayList<Model_DonMua> donmuaList) {
        JSONArray jsonArray = new JSONArray();
        for(Model_DonMua donmua : donmuaList) { 
        	jsonArray.put(donmua.toJsonObject("xuatHoaDonSach"));
        }
        JSONObject jsonData = new JSONObject();
        try {
			jsonData.put("type", "xuatHoaDonSach");
			jsonData.put("jsonArray", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void tang(int tang) {
        JSONArray jsonArray3 = new JSONArray();
        for (boolean value : main.getBody().tang) {
            jsonArray3.put(value);
        }
        JSONObject jsonData = new JSONObject();
        try {
        	jsonData.put("type", "tang" + tang);
        	jsonData.put("array", jsonArray3);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    
 
    

	public Socket getClient() {
		return client;
	}

	public Main getMain() {
		return main;
	}
	
}
