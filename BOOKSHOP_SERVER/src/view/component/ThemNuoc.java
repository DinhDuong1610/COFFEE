package view.component;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_KhachHang;
import model.Model_Nuoc;
import service.Service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemNuoc extends JPanel{
	private JDialog dialog;
	private JTextField tf_ten;
	private JTextField tf_donGia;
	private JComboBox cb_loai;
	private JLabel lb_anh;
	private JButton bt_them;
	private JLabel lblNewLabel;
	private byte[] hinhAnh;
	
	public ThemNuoc(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(1000, 500);
		setLayout(null);
		
		JLabel lblTnSch = new JLabel("Tên nước");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTnSch.setBounds(386, 124, 145, 30);
		add(lblTnSch);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 28));
		tf_ten.setText("");
		tf_ten.setColumns(10);
		tf_ten.setBounds(560, 123, 353, 40);
		add(tf_ten);
		
		JLabel lblThLoi = new JLabel("Loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblThLoi.setBounds(386, 187, 145, 30);
		add(lblThLoi);
		
		String[] itemTheLoai = {"Cà phê", "Freeze", "Trà"};
		cb_loai = new JComboBox<>(itemTheLoai);
		cb_loai.setFont(new Font("Tahoma", Font.BOLD, 28));
		cb_loai.setSelectedIndex(0);
		cb_loai.setBounds(560, 185, 353, 40);
		add(cb_loai);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblnGi.setBounds(386, 248, 145, 30);
		add(lblnGi);
		
		tf_donGia = new JTextField();
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 28));
		tf_donGia.setText("");
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(560, 247, 353, 40);
		add(tf_donGia);
		
		lb_anh = new JLabel("");
		lb_anh.setIcon(new ImageIcon(ThemNuoc.class.getResource("/images/icon_image.png")));
		lb_anh.setBounds(71, 124, 250, 250);
		add(lb_anh);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinhAnh = ImageUtil.imageToBytes(dialog, lb_anh);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ThemNuoc.class.getResource("/images/icon_camera.png")));
		btnNewButton.setBounds(71, 374, 250, 30);
		add(btnNewButton);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_ten.getText();
				String theLoai = cb_loai.getSelectedItem().toString();
				int donGia = Integer.parseInt(tf_donGia.getText());
				
				
				Model_Nuoc sach = new Model_Nuoc(0, ten, theLoai, donGia, hinhAnh);
				Service.getInstance().getMain().getBody().getKhosach().themSach(sach);
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 28));
		bt_them.setBounds(510, 351, 235, 53);
		add(bt_them);
		
		lblNewLabel = new JLabel("THÊM SÁCH");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(263, 31, 449, 46);
		add(lblNewLabel);
	}
}
