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
 * 实现注册功能
 * 
 * @author ASUS
 * 
 */
public class Register {
	private final JFrame register_frame = new JFrame("注册");
	private final JLabel register_label1 = new JLabel("账号：(最多16位)");
	private final JLabel register_label2 = new JLabel("密码：(最多16位)");
	private final JTextField register_text1 = new JTextField(16);
	private final JTextField register_text2 = new JTextField(16);
	// JButton register_b1 = new JButton("登录");
	private final JButton register_b2 = new JButton("注册");
	private String username;
	private String password;

	/*
	 * 构造函数
	 */
	public Register() {

		/*
		 * 注册按钮的点击事件
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
	 * 连接数据库，并将新用户添加到数据库中
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */
	public void insert_sql() throws SQLException, ClassNotFoundException {
		// 获取输入的用户名
		username = register_text1.getText();
		System.out.println(username);
		// 获取输入的密码
		password = register_text2.getText();

		// 判断用户名是否合理
		if (username.trim().equals("") || username.equals("null")
				|| username.length() > 16) {
			JOptionPane.showMessageDialog(null, "用户名违规！", "错误提示",
					JOptionPane.ERROR_MESSAGE);
		} else if (password.trim().equals("") || password.equals("null")
				|| password.length() > 16) {
			JOptionPane.showMessageDialog(null, "密码违规！", "错误提示",
					JOptionPane.ERROR_MESSAGE);
		} else {
			// 加载mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");

			// 建立一个连接
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "wc19941110");
			System.out.println("database connected");

			// 建立一个statement
			Statement statement = connection.createStatement();

			// 定义插入语句
			String insert_sql = "insert into user (username,password)values(\'"
					+ username + "\',\'" + password + "\')";

			System.out.println(insert_sql);
			// 执行sql语句
			try {
				int rs = statement.executeUpdate(insert_sql);
				System.out.println("insert successfully");
			} catch (MySQLIntegrityConstraintViolationException e) {
				// TODO: handle exception
				// System.out.println("用户名重复，请换一个");
				JOptionPane.showMessageDialog(null, "用户名已被占用！", "错误提示",
						JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog(null, "注册成功！", "注册成功",
					JOptionPane.INFORMATION_MESSAGE);
			// 断开连接
			connection.close();
		}
	}

}
