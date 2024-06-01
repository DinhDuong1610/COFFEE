package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.ImageUtil;
import EnCode.XMLExporter;
import dao.DBNhanVien;
import dao.DBNuoc;
import model.Model_NhanVien;
import model.Model_Nuoc;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;
import view.component.ThemNuoc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_Nuoc extends JPanel{
	private JTextField tf_loc_dongia_from;
	private JTextField tf_loc_dongia_to;
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_donGia;
	private JTable table;
	private DefaultTableModel table_model;
	private JComboBox cb_loai;
	private JLabel lb_hinhAnh;
	private JButton bt_luu;
	private JComboBox tf_loc_loai;
	private PlaceholderTextField tf_loc_ten;
	
	public QL_Nuoc() {
		setBackground(new Color(238, 228, 221));
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);	
		
		table_model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"M\u00E3 N\u01B0\u1EDBc", "T\u00EAn", "Lo\u1EA1i", "\u0110\u01A1n gi\u00E1"
				}
			);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(530);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(270);
		
		Font headerFont = new Font("Arial", Font.BOLD, 18);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 429, 1200, 368);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(15, 83, 392, 317);
		add(panel);
		
		JLabel lblTn = new JLabel("Tên");
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTn.setBounds(10, 41, 92, 30);
		panel.add(lblTn);
		
		JLabel lblSt_1 = new JLabel("Thể loại");
		lblSt_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1.setBounds(10, 92, 92, 30);
		panel.add(lblSt_1);
		
		tf_loc_ten = new PlaceholderTextField("Nhập tên đồ uống...");
		tf_loc_ten.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiem();
            }
            public void removeUpdate(DocumentEvent e) {
                timkiem();
            }
            public void changedUpdate(DocumentEvent e) {
                timkiem();
            }
        });
		tf_loc_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_ten.setColumns(10);
		tf_loc_ten.setBounds(112, 41, 268, 30);
		panel.add(tf_loc_ten);
		
		JButton bt_loc = new JButton("LỌC");
		bt_loc.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/filter.png")));
		bt_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		bt_loc.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_loc.setBounds(112, 245, 150, 39);
		panel.add(bt_loc);
		
		String[] itemTheLoaiLoc = {"Tất cả", "Cà phê", "Freeze", "Trà"};
		tf_loc_loai = new JComboBox<>(itemTheLoaiLoc);
		tf_loc_loai.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_loai.setBounds(112, 92, 268, 30);
		panel.add(tf_loc_loai);
		
		JLabel lblSt_1_1 = new JLabel("Đơn giá");
		lblSt_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1.setBounds(10, 143, 92, 30);
		panel.add(lblSt_1_1);
		
		tf_loc_dongia_from = new JTextField();
		tf_loc_dongia_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_dongia_from.setColumns(10);
		tf_loc_dongia_from.setBounds(10, 179, 150, 30);
		panel.add(tf_loc_dongia_from);
		
		tf_loc_dongia_to = new JTextField();
		tf_loc_dongia_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_dongia_to.setColumns(10);
		tf_loc_dongia_to.setBounds(230, 179, 150, 30);
		panel.add(tf_loc_dongia_to);
		
		JLabel lblSt_1_1_1 = new JLabel("đến");
		lblSt_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1.setBounds(173, 179, 47, 30);
		panel.add(lblSt_1_1_1);
		
		JLabel lblMSch = new JLabel("Mã nước");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMSch.setBounds(462, 83, 145, 30);
		add(lblMSch);
		
		JLabel lblTnSch = new JLabel("Tên nước");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnSch.setBounds(462, 129, 145, 30);
		add(lblTnSch);
		
		JLabel lblThLoi = new JLabel("Loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThLoi.setBounds(462, 175, 145, 30);
		add(lblThLoi);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblnGi.setBounds(462, 225, 145, 30);
		add(lblnGi);
		
		tf_ma = new JTextField();
		tf_ma.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ma.setColumns(10);
		tf_ma.setBounds(589, 83, 318, 30);
		add(tf_ma);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(589, 129, 318, 30);
		add(tf_ten);
		
		tf_donGia = new JTextField();
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(589, 225, 318, 30);
		add(tf_donGia);
		
		String[] itemTheLoai = {"Cà phê", "Freeze", "Trà"};
		cb_loai = new JComboBox<>(itemTheLoai);
		cb_loai.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_loai.setBounds(589, 175, 318, 30);
		add(cb_loai);
		
		lb_hinhAnh = new JLabel("");
		lb_hinhAnh.setBackground(new Color(238, 228, 221));
		lb_hinhAnh.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/icon_image2.png")));
		lb_hinhAnh.setOpaque(true);
		lb_hinhAnh.setBounds(945, 83, 270, 270);
		add(lb_hinhAnh);
		
		JLabel bt_them = new JLabel("THÊM");
		bt_them.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/icon_add.png")));
		bt_them.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemNuoc them = new ThemNuoc(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(1000, 550);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_them.setOpaque(true);
		bt_them.setHorizontalAlignment(SwingConstants.CENTER);
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_them.setBackground(new Color(2, 119, 185));
		bt_them.setBounds(533, 304, 153, 45);
		add(bt_them);
		
		JLabel bt_sua = new JLabel("SỬA");
		bt_sua.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/edit.png")));
		bt_sua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_ma.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_sua.setOpaque(true);
		bt_sua.setHorizontalAlignment(SwingConstants.CENTER);
		bt_sua.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_sua.setBackground(new Color(188, 219, 0));
		bt_sua.setBounds(692, 304, 157, 45);
		add(bt_sua);
		
		JLabel bt_xoa = new JLabel("XÓA");
		bt_xoa.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/icon_delete (2).png")));
		bt_xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_ma.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaSach(Integer.parseInt(tf_ma.getText()));
					reset();
					loadSach();
				}

			}
		});
		bt_xoa.setOpaque(true);
		bt_xoa.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_xoa.setBackground(new Color(210, 25, 13));
		bt_xoa.setBounds(533, 355, 153, 45);
		add(bt_xoa);
		
		JLabel bt_excel = new JLabel("XML");
		bt_excel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportSachListToXML(DBNuoc.getInstance().loadSach());
			}
		});
		bt_excel.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/icon_export.png")));
		bt_excel.setOpaque(true);
		bt_excel.setHorizontalAlignment(SwingConstants.CENTER);
		bt_excel.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_excel.setBackground(new Color(0, 185, 7));
		bt_excel.setBounds(692, 355, 157, 45);
		add(bt_excel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(462, 281, 442, 2);
		add(separator);
		
		bt_luu = new JButton("LƯU");
		bt_luu.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/save (2).png")));
		bt_luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadSach();
			}
		});
		bt_luu.setVisible(false);
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_luu.setBounds(1035, 363, 111, 37);
		add(bt_luu);
		
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { 
                        tf_ma.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_ten.setText(table.getValueAt(selectedRow, 1).toString());
                        cb_loai.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
                        tf_donGia.setText(table.getValueAt(selectedRow, 3).toString());
                    
                        ImageUtil.setImageLabelFromBytes(DBNuoc.getInstance().getImage(Integer.parseInt(table.getValueAt(selectedRow, 0).toString())), lb_hinhAnh);
                    }
                }
            }
        });
        reset();
		
	}
	
	public void loadSach() {
		ArrayList<Model_Nuoc> list = DBNuoc.getInstance().loadSach();
		table_model.setRowCount(0);
		for(Model_Nuoc sach : list) {
	        Object[] newRow = {sach.getMaNuoc(), sach.getTen(), sach.getLoai(), sach.getDonGia()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themSach(Model_Nuoc sach) {
		Model_Nuoc sachMoi = DBNuoc.getInstance().themSach(sach);
        Object[] newRow = {sach.getMaNuoc(), sach.getTen(), sach.getLoai(), sach.getDonGia()};
        table_model.addRow(newRow);
	}
	
	public void suaThongTin() {
		int ma = Integer.parseInt(tf_ma.getText());
		String ten = tf_ten.getText();
		String theLoai = cb_loai.getSelectedItem().toString();
		int dongia = Integer.parseInt(tf_donGia.getText());

		Model_Nuoc sach = new Model_Nuoc(ma, ten, theLoai, dongia, null);
		DBNuoc.getInstance().suaThongTin(sach);
	}
	
	public void xoaSach(int maSach) {
		DBNuoc.getInstance().xoaSach(maSach);
	}
	
	public void reset() {
		tf_ma.setText("");
		tf_ten.setText("");
		cb_loai.setSelectedIndex(0);
		tf_donGia.setText("");
		
		tf_ma.setEditable(false);
		tf_ten.setEditable(false);
		cb_loai.setEnabled(false);
		tf_donGia.setEditable(false);
		
		lb_hinhAnh.setIcon(new ImageIcon(QL_Nuoc.class.getResource("/images/icon_image2.png")));
		
	}
	
	public void unreset() {
		tf_ten.setEditable(true);
		cb_loai.setEnabled(true);
		tf_donGia.setEditable(true);
	}
	
	public void loc() {
		String dieukien = "1=1";
		if(!tf_loc_loai.getSelectedItem().toString().equals("Tất cả")) {
			dieukien += " AND loai='" + tf_loc_loai.getSelectedItem().toString() + "'";
		}
		if(!tf_loc_dongia_from.getText().isEmpty()) {
			dieukien += " AND dongia>=" + tf_loc_dongia_from.getText();
		}
		if(!tf_loc_dongia_to.getText().isEmpty()) {
			dieukien += " AND dongia<=" + tf_loc_dongia_to.getText();
		}
		
		ArrayList<Model_Nuoc> list = DBNuoc.getInstance().locSach(dieukien);
		table_model.setRowCount(0);
		for(Model_Nuoc sach : list) {
	        Object[] newRow = {sach.getMaNuoc(), sach.getTen(), sach.getLoai(), sach.getDonGia()};
	        table_model.addRow(newRow);
		}
		
		reset();
	}
	
	public void timkiem() {
		String tensach = tf_loc_ten.getText();
		if(tensach.isEmpty() || tensach.equals("Nhập tên đồ uống...")) {
			loadSach();
		}
		else {
			ArrayList<Model_Nuoc> list = DBNuoc.getInstance().timkiem("%" + tensach + "%");
			table_model.setRowCount(0);
			for(Model_Nuoc sach : list) {
		        Object[] newRow = {sach.getMaNuoc(), sach.getTen(), sach.getLoai(), sach.getDonGia()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}
}
