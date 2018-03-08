package com.gy.pdf;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import com.hg.xdoc.XDocViewer;

/**
 * XDocViewer嵌入
 * @author xdoc
 */
public class XDocViewerEmbed {
	private static XDocViewer viewer;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
            //实例化XDocViewer
            viewer = new XDocViewer();
            JFrame frame = new JFrame("XDocViewer嵌入测试");
            JToolBar bar = new JToolBar("工具条");
            JButton btn = new JButton("打开");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //打开
                    viewer.open();
                    //viewer.open("http://www.hgsql.com/down/XDocIntro.xdoc", XDocIO.READ_FORMAT_XDOC);
                }
            });
            bar.add(btn);
            bar.add(btn);
            btn = new JButton("保存");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //保存当前文件
                    viewer.save();
                }
            });
            bar.add(btn);
            btn = new JButton("打印");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.print();
                }
            });
            bar.add(btn);
            btn = new JButton("只读切换");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.setSaveEnable(!viewer.isSaveEnable());
                    viewer.setOpenEnable(!viewer.isOpenEnable());
                    viewer.setPrintEnable(!viewer.isPrintEnable());
                }
            });
            bar.add(btn);
            btn = new JButton("关于");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.about();
                }
            });
            bar.add(btn);
            Container content = frame.getContentPane();
            content.add(bar, BorderLayout.NORTH);
            content.add(viewer, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}