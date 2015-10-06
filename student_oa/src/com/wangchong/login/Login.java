package com.wangchong.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ʵ�ֵ�¼����
 * 
 * @author ASUS
 * 
 */
public class Login {
	private final static JFrame login_frame = new JFrame("��¼");

	private final static JLabel login_label1 = new JLabel("�˺ţ�(���16λ)");
	private final static JLabel login_label2 = new JLabel("���룺(���16λ)");
	private final static JTextField login_text1 = new JTextField(16);
	private final static JTextField login_text2 = new JTextField(16);
	private final static JButton login_b1 = new JButton("��¼");
	private final static JButton login_b2 = new JButton("ע��");

	public static void main(String[] args) {

		/*
		 * ע�ᰴť����¼�
		 */
		login_b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Register();
			}
		});

		/*
		 * ��¼��ť����¼�
		 */
		login_b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					select_sql();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		login_frame.setSize(350, 150);
		login_frame.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		login_frame.setLocationRelativeTo(null);
		// login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		login_frame.add(login_label1);
		login_frame.add(login_text1);
		login_frame.add(login_label2);
		login_frame.add(login_text2);
		login_frame.add(login_b1);
		login_frame.add(login_b2);

		login_frame.setVisible(true);
	}

	/*
	 * �������ݿ⣬��ʵ�ֵ�¼��֤
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void select_sql() throws SQLException, ClassNotFoundException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");

		// ����һ������
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "wc19941110");
		System.out.println("database connection");

		// ����һ��statement
		Statement statement = connection.createStatement();

		// ��ȡusername
		String username = login_text1.getText();
		// ��ȡpassword
		String password = login_text2.getText();

		// ����һ��sql��ѯ���
		String select_sql = "select password from user where username=" + "\""
				+ username + "\"";
		System.out.println(select_sql);

		// ִ��sql��ѯ���
		ResultSet rs = statement.executeQuery(select_sql);

		if (rs.next()) {
			if (rs.getString(1).equals(password)) {
				JOptionPane.showMessageDialog(null, "��¼�ɹ���", "��¼�ɹ�",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "�������", "�������",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "�˺Ų����ڣ�", "�˺Ų�����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
