/*
 * MainFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.gy.frontFrame.weldingDataBase;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gy.frontFrame.MainFrame;
import com.gy.utils.date.DateChooser;

/**
 *
 * @author  __USER__
 */
public class WeldingDataBaseFrame extends javax.swing.JFrame {

	/** Creates new form MainFrame */
	public WeldingDataBaseFrame() {
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jDesktopPane2 = new javax.swing.JDesktopPane();
		jLabel6 = new javax.swing.JLabel();
		jSeparator3 = new javax.swing.JSeparator();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		ssas = new javax.swing.JLabel();
		endDate = new javax.swing.JFormattedTextField();
		startDate = new javax.swing.JFormattedTextField();
		jSeparator7 = new javax.swing.JSeparator();
		jSeparator4 = new javax.swing.JSeparator();
		jSeparator1 = new javax.swing.JSeparator();
		searchText = new javax.swing.JFormattedTextField();
		searchTextName = new javax.swing.JFormattedTextField();
		jComboBox1 = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel10 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel11 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jSeparator2 = new javax.swing.JSeparator();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("WeldingDataBase");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}

			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));
		jDesktopPane2.setAutoscrolls(true);

		jLabel6.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel6.setText("\u5206\u7c7b");
		jLabel6.setBounds(570, 100, 90, 30);
		jDesktopPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jSeparator3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jSeparator3MouseClicked(evt);
			}
		});
		jSeparator3.setBounds(180, 210, 1150, 20);
		jDesktopPane2.add(jSeparator3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel7.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel7.setText("\u67e5\u8be2\u6761\u4ef6");
		jLabel7.setBounds(100, 50, 90, 30);
		jDesktopPane2.add(jLabel7, javax.swing.JLayeredPane.PALETTE_LAYER);

		jLabel8.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel8.setText("\u65e5\u671f");
		jLabel8.setBounds(100, 100, 90, 30);
		jDesktopPane2.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel9.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel9.setText("\u6863\u6848\u53f7");
		jLabel9.setBounds(100, 160, 90, 30);
		jDesktopPane2.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

		ssas.setFont(new java.awt.Font("新宋体", 1, 18));
		ssas.setText("\u8d23\u4efb\u8005");
		ssas.setBounds(570, 160, 80, 30);
		jDesktopPane2.add(ssas, javax.swing.JLayeredPane.DEFAULT_LAYER);

		endDate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1,
				1, new java.awt.Color(204, 0, 0)));
		endDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		endDate.setText("\u7ec8\u6b62\u65e5\u671f");
		endDate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				endDateMouseClicked(evt);
			}
		});
		endDate.setBounds(390, 100, 90, 30);
		jDesktopPane2.add(endDate, javax.swing.JLayeredPane.DEFAULT_LAYER);

		startDate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1,
				1, 1, new java.awt.Color(255, 0, 0)));
		startDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		startDate.setText("\u8d77\u59cb\u65e5\u671f");
		startDate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				startDateMouseClicked(evt);
			}
		});
		startDate.setBounds(180, 100, 90, 30);
		jDesktopPane2.add(startDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
		jSeparator7.setBounds(290, 110, 80, 20);
		jDesktopPane2.add(jSeparator7, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jSeparator4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jSeparator4MouseClicked(evt);
			}
		});
		jSeparator4.setBounds(100, 90, 1310, 20);
		jDesktopPane2.add(jSeparator4, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator1.setBounds(990, 100, 30, 100);
		jDesktopPane2.add(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		searchText.setText("\u4e3b\u9898\u8bcd");
		searchText.setBounds(680, 160, 270, 30);
		jDesktopPane2.add(searchText, javax.swing.JLayeredPane.DEFAULT_LAYER);

		searchTextName.setText("\u9898\u540d");
		searchTextName.setBounds(180, 160, 270, 30);
		jDesktopPane2.add(searchTextName,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4", "Item 4" }));
		jComboBox1.setBounds(680, 100, 270, 30);
		jDesktopPane2.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4",
						"Title 5", "Title 6", "Title 7" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jScrollPane1.setBounds(100, 230, 1310, 480);
		jDesktopPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel10.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel10.setText("\u5bc6\u7ea7");
		jLabel10.setBounds(1040, 100, 90, 30);
		jDesktopPane2.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4", "Item 4" }));
		jComboBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}
		});
		jComboBox2.setBounds(1140, 100, 270, 30);
		jDesktopPane2.add(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel11.setFont(new java.awt.Font("新宋体", 1, 18));
		jLabel11.setText("\u4fdd\u7ba1\u671f\u9650");
		jLabel11.setBounds(1040, 160, 90, 30);
		jDesktopPane2.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4", "Item 4" }));
		jComboBox3.setBounds(1140, 160, 270, 30);
		jDesktopPane2.add(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator2.setBounds(530, 100, 30, 100);
		jDesktopPane2.add(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		getContentPane()
				.add(jDesktopPane2,
						new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0,
								-1, -1));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jSeparator4MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void endDateMouseClicked(java.awt.event.MouseEvent evt) {
		final JFrame frame = new JFrame("起始日期选择");
		frame.setSize(400, 300);

		final DateChooser datePicker = new DateChooser();
		datePicker.addActionListener(new ActionListener() {// 事件捕获

					public void actionPerformed(ActionEvent e) {
						endDate.setValue(datePicker.getSelectedDate());
						frame.setVisible(false);
						search();
					}

				});
		frame.getContentPane().add(datePicker);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void jSeparator3MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void startDateMouseClicked(java.awt.event.MouseEvent evt) {
		final JFrame frame = new JFrame("起始日期选择");
		frame.setSize(400, 300);

		final DateChooser datePicker = new DateChooser();
		datePicker.addActionListener(new ActionListener() {// 事件捕获

					public void actionPerformed(ActionEvent e) {
						startDate.setValue(datePicker.getSelectedDate());
						frame.setVisible(false);
					}
				});
		frame.getContentPane().add(datePicker);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		int result = JOptionPane.showConfirmDialog(null, "是否退出系统！");
		if (result == 0) {
			this.dispose();
		} else {
			new WeldingDataBaseFrame().setVisible(true);
		}
	}

	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new WeldingDataBaseFrame().setVisible(true);
			}
		});
	}

	//搜索使用
	private void search() {
		//日期
		String startDateStr = (String) startDate.getValue();
		String endDateStr = (String) endDate.getValue();
		if (startDateStr != null) {
			Date startDate = transDate(startDateStr);
			Date endDate = transDate(endDateStr);
		}
		//分类
		//关键词search
		String name = (String) searchTextName.getValue();
		String text2 = (String) searchText.getValue();

		// TODO Auto-generated method stub

	}

	private Date transDate(String startDateStr) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = df.parse(startDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JFormattedTextField endDate;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JDesktopPane jDesktopPane2;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JSeparator jSeparator7;
	private javax.swing.JTable jTable1;
	private javax.swing.JFormattedTextField searchText;
	private javax.swing.JFormattedTextField searchTextName;
	private javax.swing.JLabel ssas;
	private javax.swing.JFormattedTextField startDate;
	// End of variables declaration//GEN-END:variables

}