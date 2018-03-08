package com.gy.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.gy.user.entity.UserEntity;
import com.gy.utils.MD5;
import com.gy.utils.http.HttpUtils;
import com.gy.utils.result.ResultDto;
public class UserLogin {

	private JFrame frame;
	private JTextField userName;
	private JPasswordField passWord;
	private JButton loginBtn;
	private JButton resetBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					UserLogin window = new UserLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param client2 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		frame.setBounds(100, 100, 726, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 22));
		lblNewLabel.setBounds(107, 79, 154, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("密码");
		label.setFont(new Font("楷体", Font.BOLD, 22));
		label.setBounds(107, 163, 154, 36);
		frame.getContentPane().add(label);
		
		userName = new JTextField();
		userName.setBounds(219, 79, 318, 36);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		passWord = new JPasswordField();
		passWord.setBounds(219, 163, 318, 36);
		frame.getContentPane().add(passWord);
		//登陆
		loginBtn = new JButton("登录");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameStr = userName.getText();
				if (userNameStr.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "            请输入用户名！    ", "钢铁研究总院焊接所",
							JOptionPane.DEFAULT_OPTION);
				} else if (passWord.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "            请输入密码！   ", "钢铁研究总院焊接所",
							JOptionPane.DEFAULT_OPTION);
				}
				// JOptionPane.showMessageDialog(frame, " 您输入得用户名或密码错误，请重新输入！ ",
				// "钢铁研究总院焊接所", JOptionPane.DEFAULT_OPTION);
				// 查询用户
				String postString = null;
				try {
					postString = HttpUtils.postJson("http://localhost:8080/usersLogin/findUsers", userNameStr, "utf-8");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UserEntity userEntity = null;
				if (postString != null
						&& (userEntity = JSON.parseObject(JSON.parseObject(postString).getString("successData"),
								UserEntity.class)) != null) {
					String returnStr = MD5.md5Salt(new String(passWord.getPassword()));
					if (returnStr != null && returnStr.equals(userEntity.getPassword())) {
						// 验证成功
						JOptionPane.showMessageDialog(frame, "            验证成功！   ", "钢铁研究总院焊接所",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(frame, "            密码错误！   ", "钢铁研究总院焊接所",
								JOptionPane.DEFAULT_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "            用户名错误！   ", "钢铁研究总院焊接所",
							JOptionPane.DEFAULT_OPTION);
				}
			}

		});
		
		loginBtn.setFont(new Font("楷体", Font.BOLD, 20));
		loginBtn.setBounds(136, 289, 168, 45);
		frame.getContentPane().add(loginBtn);
		//重置
		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setText("");
				passWord.setText("");
			}
		});
		resetBtn.setFont(new Font("楷体", Font.BOLD, 20));
		resetBtn.setBounds(408, 289, 168, 45);
		frame.getContentPane().add(resetBtn);
	}
}
