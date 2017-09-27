import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class visual6 extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
    private JLabel jl;
    public static JTextField textField;
    public static JTextField textField_1;
    public static JTextField textField_2;
    public static JTextField textField_3;
    public static JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual6 frame = new visual6();
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
	public visual6() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 430);
		setTitle("区块链电力交易平台");
		Image image=new ImageIcon("e:/timg.jpg").getImage();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("节点一");
		btnNewButton_1.setBounds(105, 102, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("交易量");
		btnNewButton.setBounds(105, 219, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnC = new JButton("交易单价");
		btnC.setBounds(267, 219, 93, 23);
		contentPane.add(btnC);
		
		JButton button = new JButton("节点二");
		button.setBounds(267, 102, 93, 23);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(105, 166, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 272, 93, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(267, 166, 93, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(267, 273, 93, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button_1 = new JButton("输入");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			test2 GG=new test2();
			GG.insert();
			dispose();
			new visual3().setVisible(true);
			}
		});
		button_1.setBounds(181, 329, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5E8F\u53F7");
		button_2.setBounds(144, 25, 93, 23);
		contentPane.add(button_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(247, 26, 35, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		jl=new JLabel();
		jl.setIcon(new ImageIcon(image));
		jl.setBounds(0, 0, 500, 430);
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