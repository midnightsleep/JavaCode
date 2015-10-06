package com.wangchong.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * ʵ��ע�Ṧ��
 * 
 * @author ASUS
 * 
 */
public class Register {
	private final JFrame register_frame = new JFrame("ע��");
	private final JLabel register_label1 = new JLabel("�˺ţ�(���16λ)");
	private final JLabel register_label2 = new JLabel("���룺(���16λ)");
	private final JTextField register_text1 = new JTextField(16);
	private final JTextField register_text2 = new JTextField(16);
	// JButton register_b1 = new JButton("��¼");
	private final JButton register_b2 = new JButton("ע��");
	private String username;
	private String password;

	/*
	 * ���캯��
	 */
	public Register() {

		/*
		 * ע�ᰴť�ĵ���¼�
		 */
		register_b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					insert_sql();
					register_frame.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		register_frame.setSize(350, 150);
		register_frame.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 2));
		register_frame.setLocationRelativeTo(null);
		// register_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		register_frame.add(register_label1);
		register_frame.add(register_text1);
		register_frame.add(register_label2);
		register_frame.add(register_text2);
		register_frame.add(register_b2);

		register_frame.setVisible(true);
	}

	/*
	 * �������ݿ⣬�������û���ӵ����ݿ���
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */
	public void insert_sql() throws SQLException, ClassNotFoundException {
		// ��ȡ������û���
		username = register_text1.getText();
		System.out.println(username);
		// ��ȡ���������
		password = register_text2.getText();

		// �ж��û����Ƿ����
		if (username.trim().equals("") || username.equals("null")
				|| username.length() > 16) {
			JOptionPane.showMessageDialog(null, "�û���Υ�棡", "������ʾ",
					JOptionPane.ERROR_MESSAGE);
		} else if (password.trim().equals("") || password.equals("null")
				|| password.length() > 16) {
			JOptionPane.showMessageDialog(null, "����Υ�棡", "������ʾ",
					JOptionPane.ERROR_MESSAGE);
		} else {
			// ����mysql����
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");

			// ����һ������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "wc19941110");
			System.out.println("database connected");

			// ����һ��statement
			Statement statement = connection.createStatement();

			// ����������
			String insert_sql = "insert into user (username,password)values(\'"
					+ username + "\',\'" + password + "\')";

			System.out.println(insert_sql);
			// ִ��sql���
			try {
				int rs = statement.executeUpdate(insert_sql);
				System.out.println("insert successfully");
			} catch (MySQLIntegrityConstraintViolationException e) {
				// TODO: handle exception
				// System.out.println("�û����ظ����뻻һ��");
				JOptionPane.showMessageDialog(null, "�û����ѱ�ռ�ã�", "������ʾ",
						JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog(null, "ע��ɹ���", "ע��ɹ�",
					JOptionPane.INFORMATION_MESSAGE);
			// �Ͽ�����
			connection.close();
		}
	}

}
