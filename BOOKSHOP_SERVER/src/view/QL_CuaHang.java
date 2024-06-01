package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Model_NhanVien;
import view.component.Item_khuSach;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;

public class QL_CuaHang extends JPanel{
	
	private JPanel panel;
	public static  boolean[] tang1 = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	public static  boolean[] tang2 = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	public static  boolean[] tang3 = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	
	public QL_CuaHang() {
		setBackground(new Color(238, 228, 221));
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		panel = new JPanel();
		panel.setBackground(new Color(238, 228, 221));
		panel.setBounds(165, 270, 940, 265);
		add(panel);
		panel.setLayout(new GridLayout(1, 3, 50, 50));
		
		Item_khuSach item1 = new Item_khuSach(1);
		item1.getLb_title().setText("TẦNG 1");
		item1.getPanel().setBackground(new Color(239, 163, 163));
		item1.getPanel().setOpaque(true);
		panel.add(item1);
		
		Item_khuSach item2 = new Item_khuSach(2);
		item2.getLb_title().setText("TẦNG 2");
		item2.getPanel().setBackground(new Color(255, 237, 172));
		item2.getPanel().setOpaque(true);
		panel.add(item2);
		
		Item_khuSach item3 = new Item_khuSach(3);
		item3.getLb_title().setText("TẦNG 3");
		item3.getPanel().setBackground(new Color(255, 183, 239));
		item3.getPanel().setOpaque(true);
		panel.add(item3);
			
	}
	
	public void online(Model_NhanVien nhanvien, int quay) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_khuSach) {
		        Item_khuSach item = (Item_khuSach) component;
		        if(item.getQuay() == quay) {
		        	item.online(nhanvien);
		        	panel.repaint();
		        	panel.revalidate();
		        	break;
		        }
		    }
		}
	}
	
	public void offline(int quay) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_khuSach) {
		        Item_khuSach item = (Item_khuSach) component;
		        if(item.getQuay() == quay) {
		        	item.offline();
		        	panel.repaint();
		        	panel.revalidate();
		        	break;
		        }
		    }
		}
	}
	
	public void loadBan() {
		Component[] components = panel.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_khuSach) {
		        Item_khuSach item = (Item_khuSach) component;
		        	item.getLb_ban().setText(soBan(item.getQuay()) + "");
		        	panel.repaint();
		        	panel.revalidate();
		    }
		}
	}
	
	public int soBan(int tang) {
		int count = 0;
		if(tang==1) {
			for(boolean x : tang1) {
				if(!x) count++;
			}
		}
		else if(tang==2) {
			for(boolean x : tang2) {
				if(!x) count++;
			}
		}
		else if(tang==3) {
			for(boolean x : tang3) {
				if(!x) count++;
			}
		}
		return count;
	}

	public static boolean[] getTang1() {
		return tang1;
	}

	public static boolean[] getTang2() {
		return tang2;
	}

	public static boolean[] getTang3() {
		return tang3;
	}

	public static void setTang1(boolean[] tang1) {
		QL_CuaHang.tang1 = tang1;
	}

	public static void setTang2(boolean[] tang2) {
		QL_CuaHang.tang2 = tang2;
	}

	public static void setTang3(boolean[] tang3) {
		QL_CuaHang.tang3 = tang3;
	}
	
	
	
}
