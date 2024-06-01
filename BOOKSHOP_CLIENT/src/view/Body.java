package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.Model_DonMua;
import model.Model_Nuoc;
import net.miginfocom.swing.MigLayout;
import swing.ImageRenderer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.ImageUtil;

public class Body extends JPanel{
	
	private JLayeredPane listSach;
	private JTable table;
	private DefaultTableModel table_model;
	private JPanel listBan;
	public boolean[] tang;

	public Body() {
		setBackground(new Color(249, 238, 231));
		setSize(1240, 840);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(Body.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		listSach = new JLayeredPane();
		listSach.setBackground(new Color(255, 255, 255));
		listSach.setOpaque(true);
//		layeredPane.setBounds(56, 346, 1100, 439);
		JScrollPane scroll = new JScrollPane(listSach);
		scroll.setBounds(350, 346, 850, 439);
		add(scroll);
		
		listSach.setLayout(new MigLayout("wrap 5, fillx", "10[150]10[150]10[150]10[150]10[150]10", "10[200]10"));
		
		table_model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"M\u00E3", "", "Đồ uống", "S\u1ED1 l\u01B0\u1EE3ng"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(550);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		Font headerFont = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "H\u00D3A \u0110\u01A0N", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(350, 83, 850, 242);
		add(scrollPane);
		
		listBan = new JPanel();
		listBan.setBackground(new Color(189, 156, 145));
		listBan.setBounds(20, 83, 320, 702);
		add(listBan);
		listBan.setLayout(new MigLayout("wrap 3, fillx", "10[90]10[90]10[90]10", "10[90]10"));		
	}
	
	public void addSach(Model_Nuoc sach) {
		listSach.add(new Item_Sach(sach), "width 150:150:150, height 200:200:200");
		listSach.repaint();
		listSach.revalidate();
	}
	
	public void addBan(boolean[] status) {
		for(int i = 0; i <= 20; i++) {
			Item_Ban item = new Item_Ban(i+1);
			listBan.add(item, "width 90:90:90, height 90:90:90");
			if(!status[i]) {
				item.off();
			}
			listBan.repaint();
			listBan.revalidate();
		}
	}
	
	public void themDonMua(Model_DonMua donmua, byte[] hinhAnh) {
		ImageIcon image = ImageUtil.bytesToImageIcon(hinhAnh, 80, 80);
        Object[] newRow = {donmua.getMaDonMua(), image, donmua.getTenSach(), donmua.getSoluong(), donmua.getNgayMua()};
        table_model.addRow(newRow);
	}

	public DefaultTableModel getTable_model() {
		return table_model;
	}

	public JLayeredPane getListSach() {
		return listSach;
	}

	public void setListSach(JLayeredPane listSach) {
		this.listSach = listSach;
	}
}
