import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.color.*;
import javax.swing.JEditorPane;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class visual extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
    private JLabel jl;
    private JTextField textField;
    private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual frame = new visual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public visual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 430);
		setTitle("区块链电力交易平台");
		Image image=new ImageIcon("e:/timg.jpg").getImage();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(534, 156, 54, 15);
		label_1.setFont(new Font("宋体",Font.BOLD,16));
		contentPane.add(label_1);
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setBounds(534, 106, 54, 15);
		label.setFont(new Font("宋体",Font.BOLD,16));
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(576, 101, 125, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(576, 151, 125, 25);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("记住密码");
		chckbxNewCheckBox.setBounds(579, 196, 81, 25);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("自动登录");
		chckbxNewCheckBox_1.setBounds(579, 241, 81, 25);
		contentPane.add(chckbxNewCheckBox_1);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 dispose();
				 new visual2().setVisible(true);
			}
		});
		btnNewButton.setBounds(579, 309, 93, 23);
		contentPane.add(btnNewButton);
		jl=new JLabel();
		jl.setIcon(new ImageIcon(image));
		jl.setBounds(0, 0, 800, 430);
		contentPane.add(jl);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
