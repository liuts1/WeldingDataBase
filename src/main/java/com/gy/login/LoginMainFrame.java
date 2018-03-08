/*
 * qweq.java
 *
 * Created on __DATE__, __TIME__
 */

package com.gy.login;

import java.net.SocketTimeoutException;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.apache.http.conn.ConnectTimeoutException;

import com.alibaba.fastjson.JSON;
import com.gy.frontFrame.MainFrame;
import com.gy.user.entity.UserEntity;
import com.gy.utils.MD5;
import com.gy.utils.http.HttpUtils;

/**
 *
 * @author  __USER__
 */
public class LoginMainFrame extends javax.swing.JFrame {

	/** Creates new form qweq */
	public LoginMainFrame() {
		initComponents();
	}

//GEN-BEGIN:initComponents
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

jLabel1 = new javax.swing.JLabel();
jLabel3 = new javax.swing.JLabel();
userName = new javax.swing.JFormattedTextField();
passWord = new javax.swing.JPasswordField();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
setTitle("WeldingDatabase");
setBackground(new java.awt.Color(204, 255, 51));
setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
setForeground(java.awt.Color.lightGray);
setLocationByPlatform(true);

jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel1.setText("\u7528\u6237\u540d");

jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel3.setText("\u5bc6\u7801");

userName.setName("userName");

passWord.setName("passWord");

jButton1.setText("\u767b\u5f55");
jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
jButton1MouseClicked(evt);
}
});

jButton2.setText("\u9000\u51fa");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(140, 140, 140)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(layout.createSequentialGroup()
.addGap(22, 22, 22)
.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(userName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
.addComponent(passWord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGap(106, 106, 106)
.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
.addGap(180, 180, 180))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addContainerGap(125, Short.MAX_VALUE)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(passWord, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(40, 40, 40)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(85, 85, 85))
);

pack();
}// </editor-fold>
//GEN-END:initComponents

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt){
		String userNameStr = userName.getText();
		if (userNameStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "            请输入用户名！    ", "钢铁研究总院焊接所",
					JOptionPane.DEFAULT_OPTION);
		} else if (passWord.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "            请输入密码！   ", "钢铁研究总院焊接所",
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
		if(postString == null){
			//过滤器未通过
			// 验证成功
			JOptionPane.showMessageDialog(null, "            用户未登录失效！   ", "钢铁研究总院焊接所",
					JOptionPane.DEFAULT_OPTION);
		}else{
			UserEntity userEntity = null;
			if ( (userEntity = JSON.parseObject(JSON.parseObject(postString).getString("successData"),
							UserEntity.class)) != null) {
				String returnStr = MD5.md5Salt(new String(passWord.getPassword()));
				if (returnStr != null && returnStr.equals(userEntity.getPassword())) {
					String token = MD5.md5Salt(UUID.randomUUID().toString());
					PublicStaticSta.getinstance().setStatus(token);
					try {  
						//send the message which had logined
						HttpUtils.postJson("http://localhost:8080/usersLogin/LoginSuccess", token, "utf-8");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// 验证成功
					this.dispose();
					new MainFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "            密码错误！   ", "钢铁研究总院焊接所",
							JOptionPane.DEFAULT_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(null, "            用户名错误！   ", "钢铁研究总院焊接所",
						JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginMainFrame().setVisible(true);
			}
		});
	}

//GEN-BEGIN:variables
// Variables declaration - do not modify
private javax.swing.JButton jButton1;
private javax.swing.JButton jButton2;
private javax.swing.JLabel jLabel1;
private javax.swing.JLabel jLabel3;
private javax.swing.JPasswordField passWord;
private javax.swing.JFormattedTextField userName;
// End of variables declaration//GEN-END:variables

}