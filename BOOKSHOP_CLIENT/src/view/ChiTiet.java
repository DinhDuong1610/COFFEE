package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_DonMua;
import model.Model_Nuoc;
import service.Service;
import java.awt.SystemColor;

public class ChiTiet extends JPanel{
	private JDialog dialog;
	private JTextField tf_ten;
	private JTextField tf_donGia;
	private JLabel lb_anh;
	private JButton bt_them;
	private byte[] hinhAnh;
	private JTextField tf_soluong;
	private Model_Nuoc sach;
	private JTextField tf_theloai;
	
	public ChiTiet(JDialog dialog, Model_Nuoc sach) {
		this.sach = sach;
		this.dialog = dialog;
		setBackground(new Color(189, 156, 145));
		setOpaque(true);
		setSize(950, 450);
		setLayout(null);
		
		JLabel lblTnSch = new JLabel("Đồ uống");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTnSch.setBounds(352, 77, 145, 40);
		add(lblTnSch);
		
		tf_ten = new JTextField(sach.getTen());
		tf_ten.setBorder(null);
		tf_ten.setBackground(new Color(255, 255, 255));
		tf_ten.setEditable(false);
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 26));
		tf_ten.setColumns(10);
		tf_ten.setBounds(490, 77, 403, 47);
		add(tf_ten);
		
		JLabel lblThLoi = new JLabel("Loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblThLoi.setBounds(352, 157, 145, 36);
		add(lblThLoi);
				
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblnGi.setBounds(352, 236, 145, 38);
		add(lblnGi);
		
		tf_donGia = new JTextField(sach.getDonGia()+ "");
		tf_donGia.setBorder(null);
		tf_donGia.setBackground(new Color(255, 255, 255));
		tf_donGia.setEditable(false);
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 26));
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(490, 234, 403, 47);
		add(tf_donGia);
		
		lb_anh = new JLabel("");
        ImageUtil.setImageLabelFromBytes(sach.getHinhAnh(), lb_anh, 250, 250);
		lb_anh.setBackground(SystemColor.activeCaption);
		lb_anh.setOpaque(true);
		lb_anh.setBounds(70, 77, 250, 250);
		add(lb_anh);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_soluong.getText()) > 0) {
			        long millis = System.currentTimeMillis();
			        Date currentDate = new Date(millis);
					Model_DonMua donmua = new Model_DonMua(sach.getMaNuoc(), 0, sach.getMaNuoc(), sach.getTen(), sach.getDonGia(),Integer.parseInt(tf_soluong.getText()), currentDate);
					Service.getInstance().getMain().getBody().themDonMua(donmua, sach.getHinhAnh());
					Service.getInstance().getMain().getMenuLeft().themDonMua(donmua);
					Service.getInstance().getMain().getMenuLeft().getDonmuaList().add(donmua);
					dialog.dispose();
				}
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 28));
		bt_them.setBounds(523, 321, 235, 56);
		add(bt_them);
		
		JButton bt_tru = new JButton("");
		bt_tru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_soluong.getText()) > 0) {
					tf_soluong.setText((Integer.parseInt(tf_soluong.getText())-1) + "" );
				}
			}
		});
		bt_tru.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_tru.png")));
		bt_tru.setFont(new Font("Tahoma", Font.BOLD, 10));
		bt_tru.setBounds(70, 337, 40, 40);
		add(bt_tru);
		
		JButton bt_cong = new JButton("");
		bt_cong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					tf_soluong.setText((Integer.parseInt(tf_soluong.getText())+1) + "" );
			}
		});
		bt_cong.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_cong.png")));
		bt_cong.setFont(new Font("Tahoma", Font.BOLD, 10));
		bt_cong.setBounds(280, 337, 40, 40);
		add(bt_cong);
		
		tf_soluong = new JTextField();
		tf_soluong.setBorder(null);
		tf_soluong.setHorizontalAlignment(SwingConstants.CENTER);
		tf_soluong.setText("1");
		tf_soluong.setBackground(new Color(255, 255, 255));
		tf_soluong.setEditable(false);
		tf_soluong.setFont(new Font("Tahoma", Font.BOLD, 33));
		tf_soluong.setBounds(123, 337, 145, 40);
		add(tf_soluong);
		tf_soluong.setColumns(10);
		
		tf_theloai = new JTextField(sach.getLoai());
		tf_theloai.setBorder(null);
		tf_theloai.setBackground(new Color(255, 255, 255));
		tf_theloai.setEditable(false);
		tf_theloai.setFont(new Font("Tahoma", Font.BOLD, 26));
		tf_theloai.setColumns(10);
		tf_theloai.setBounds(490, 153, 403, 47);
		add(tf_theloai);
		
	}

	public Model_Nuoc getSach() {
		return sach;
	}
	
	
}
