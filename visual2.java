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

public class visual2 extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
    private JLabel jl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual2 frame = new visual2();
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
	public visual2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 430);
		setTitle("区块链电力交易平台");
		Image image=new ImageIcon("e:/timg.jpg").getImage();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u767B\u9646\u6210\u529F！");
		label.setBounds(312, 99, 170, 89);
		label.setFont(new Font("楷体",Font.BOLD,32));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BF7\u9009\u62E9\u6A21\u62DF\u7CFB\u7EDF\u8282\u70B9\u6570");
		label_1.setBounds(329, 219, 135, 15);
		contentPane.add(label_1);
		
		JButton button = new JButton("\u4E09\u8282\u70B9\u7CFB\u7EDF");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				 new visual3().setVisible(true);
			}
		});
		button.setBounds(240, 270, 127, 23);
		contentPane.add(button);
		
		JButton button_2 = new JButton("\u56DB\u8282\u70B9\u7CFB\u7EDF");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				 new visual4().setVisible(true);
			}
		});
		button_2.setBounds(410, 270, 114, 23);
		contentPane.add(button_2);
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
