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
 * 实现登录功能
 * 
 * @author ASUS
 * 
 */
public class Login {
	private final static JFrame login_frame = new JFrame("登录");

	private final static JLabel login_label1 = new JLabel("账号：(最多16位)");
	private final static JLabel login_label2 = new JLabel("密码：(最多16位)");
	private final static JTextField login_text1 = new JTextField(16);
	private final static JTextField login_text2 = new JTextField(16);
	private final static JButton login_b1 = new JButton("登录");
	private final static JButton login_b2 = new JButton("注册");

	public static void main(String[] args) {

		/*
		 * 注册按钮点击事件
		 */
		login_b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Register();
			}
		});

		/*
		 * 登录按钮点击事件
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
	 * 连接数据库，并实现登录验证
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void select_sql() throws SQLException, ClassNotFoundException {
		// 加载mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");

		// 建立一个连接
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "wc19941110");
		System.out.println("database connection");

		// 建立一个statement
		Statement statement = connection.createStatement();

		// 获取username
		String username = login_text1.getText();
		// 获取password
		String password = login_text2.getText();

		// 定义一个sql查询语句
		String select_sql = "select password from user where username=" + "\""
				+ username + "\"";
		System.out.println(select_sql);

		// 执行sql查询语句
		ResultSet rs = statement.executeQuery(select_sql);

		if (rs.next()) {
			if (rs.getString(1).equals(password)) {
				JOptionPane.showMessageDialog(null, "登录成功！", "登录成功",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "密码错误！", "密码错误",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "账号不存在！", "账号不存在",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
