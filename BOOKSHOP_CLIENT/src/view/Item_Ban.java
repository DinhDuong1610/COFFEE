package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import service.Service;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_Ban extends JPanel{
	
	private JLabel lb_ban;
	private boolean status;
	private int so;

	public Item_Ban(int so) {
		this.so = so;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(status) {
					off();
					Service.getInstance().getMain().getBody().tang[so-1] = false;
				}
				else {
					on();
					Service.getInstance().getMain().getBody().tang[so-1] = true;
				}
				Service.getInstance().tang(Service.getInstance().getMain().getMenuLeft().getQuay());
			}
		});
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(new Color(253, 239, 213));
		setSize(90, 90);
		setLayout(null);
		
		JLabel lb_so = new JLabel(so+"");
		lb_so.setForeground(new Color(139, 69, 19));
		lb_so.setHorizontalAlignment(SwingConstants.CENTER);
		lb_so.setFont(new Font("Tahoma", Font.BOLD, 40));
		lb_so.setBounds(10, 10, 70, 37);
		add(lb_so);
		status = true;
		
		lb_ban = new JLabel("");
		lb_ban.setIcon(new ImageIcon(Item_Ban.class.getResource("/images/table_on.png")));
		lb_ban.setBounds(10, 10, 70, 70);
		add(lb_ban);
		
	}
	
	public void on() {
		lb_ban.setIcon(new ImageIcon(Item_Ban.class.getResource("/images/table_on.png")));
		status = true;
	}
	
	public void off() {
		lb_ban.setIcon(new ImageIcon(Item_Ban.class.getResource("/images/table_off.png")));
		status = false;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSo() {
		return so;
	}

	public void setSo(int so) {
		this.so = so;
	}

	public JLabel getLb_ban() {
		return lb_ban;
	}

	public void setLb_ban(JLabel lb_ban) {
		this.lb_ban = lb_ban;
	}
	
	
	
}
