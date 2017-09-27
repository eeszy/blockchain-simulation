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
import javax.swing.table.DefaultTableModel;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.color.*;
import javax.swing.JEditorPane;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
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

public class visual3 extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
    private JLabel jl;
    public static JTable table;
    public static DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual3 frame = new visual3();
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
	public void paint(Graphics g){
			super.paint(g);
			Graphics2D g1=(Graphics2D)g;
			g1.setStroke(new BasicStroke(6.0f));
			g1.setColor(Color.GRAY);
			g1.drawLine(226, 149, 300, 294);
			g1.drawLine(212, 149, 130, 294);
			g1.drawLine(176, 306, 266, 306);
		}
	
	public visual3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 430);
		setTitle("区块链电力交易平台");
		Image image=new ImageIcon("e:/timg.jpg").getImage();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 
		
		JLabel label = new JLabel();
		label.setBounds(298, 102, 54, 15);
		contentPane.add(label);	
		
		JButton btnNewButton_1 = new JButton("A");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			label.setText("现存电量");
			}
		});
		btnNewButton_1.setBounds(166, 94, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("B");
		btnNewButton.setBounds(74, 266, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnC = new JButton("C");
		btnC.setBounds(259, 266, 93, 23);
		contentPane.add(btnC);
		
		JButton button = new JButton("\u7CFB\u7EDF\u6A21\u578B");
		button.setBounds(166, 42, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u533A\u5757\u94FE");
		button_1.setBounds(534, 42, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u751F\u6210\u533A\u5757");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 ResultSet rs=null;
			    	
			    	String sql2="select * from blockchain4";
			    	Connection cnn=test.getConn();

			    	try{
			    		PreparedStatement ps=cnn.prepareStatement(sql2);
			    		rs=ps.executeQuery();
			    		
			    		DefaultTableModel model=new DefaultTableModel();
			    		 table=new JTable(model);
			    		Vector<Object> data=new Vector<Object>();
			    		Vector<String> names=new Vector<String>();
			    		String[] columnNames={"区块序号","区块编码","供应方","交易量","交易单价","总价","接收方","时间戳"};
			    		model.setDataVector(data, names);
			    		for(int i=0;i<8;i++){
			    		names.add(columnNames[i]);	
			    		}
			    		while(rs.next())
			    		{
			    			Object[][] rowData=new Object[8][8];
			    			Vector<Object> row=new Vector<Object>();
			    			rowData[0][0]=rs.getString(1);
			    		    row.add(rowData[0][0]);
			    		    rowData[0][1]=rs.getDouble(2);
			    		    row.add(rowData[0][1]);
			    		    rowData[0][2]=rs.getString(3);
			    		    row.add(rowData[0][2]);
			    		    rowData[0][3]=rs.getInt(4);
			    		    row.add(rowData[0][3]);
			    		    rowData[0][4]=rs.getInt(5);
			    		    row.add(rowData[0][4]);
			    		    rowData[0][5]=rs.getInt(6);
			    		    row.add(rowData[0][5]);
			    		    rowData[0][6]=rs.getString(7);
			    		    row.add(rowData[0][6]);
			    		    rowData[0][7]=rs.getString(8);
			    		    row.add(rowData[0][7]);
			    		    data.add(row);
			    		    model.setDataVector(data, names);
			              
			    		}  
			    	}
			    	catch(SQLException e1)
			    	{
			    		e1.printStackTrace();}
		        JScrollPane jsp=new JScrollPane(table);
				jsp.setBounds(380, 100, 400, 200);
				contentPane.add(jsp);
				 
			}
		});
		button_2.setBounds(534, 328, 93, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u8F93\u5165\u4EA4\u6613\u4FE1\u606F");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new visual6().setVisible(true);
			}
		});
		button_3.setBounds(154, 328, 115, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\u8FD4\u56DE\u9996\u9875");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new visual2().setVisible(true);
			}
		});
		button_4.setBounds(681, 359, 93, 23);
		contentPane.add(button_4);
		
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