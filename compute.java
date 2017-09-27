

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

/**
 * 首先我们创建了compute这个类，用于安全校验和阻塞管理。而其中的chaoliu是用于潮流计算的方法。
 * @author zsj
 * @Time 2017-06-03
 */

public class compute {
	/** 声明变量P12，表示1、2之间线路上流过的功率 */
	public static double P12=0;
	/** 声明变量P13，表示1、3之间线路上流过的功率 */
    public static double P13=0;
    /** 声明变量P24，表示2、4之间线路上流过的功率 */
    public static double P24=0;
    /** 声明变量P34，表示3、4之间线路上流过的功率 */
    public static double P34=0;
    /** 声明变量x55，表示交易达成的交易单价 */
    public static int x55=0;

    public static void chaoliu()
    {
    	/** 线路参数的设置，为了方便初期的计算核对和程序调试，这里选取了较为简化的线路结构和参数。
    	 *bi0表示的是节点没有对地支路，各节点电抗为1，电纳则为-1。相当于是一个最简单的算例。
    	 *当然，后期我们可以设计不同的线路，然后在这里修改相应的参数  */
		int b10=0;
		int b20=0;
		int b30=0;
		int b40=0;
		int b12=-1,b21=-1;
		int b13=-1,b31=-1;
		int b24=-1,b42=-1;
		int b34=-1,b43=-1;
		/** n表示矩阵阶数，四节点系统的节点导纳矩阵当然是4阶*/
		int n=4;
		/** 声明电纳矩阵 */
		Matrix B = DenseMatrix.Factory.zeros(n, n);
		/** 对电纳矩阵中的元素进行赋值，具体设置方法为（输入的值，行。列）*/
		B.setAsInt(b10+b12+b13,0,0);
		B.setAsInt(b20+b21+b24,1,1);
		B.setAsInt(b30+b31+b34,2,2);
		B.setAsInt(b40+b42+b43,3,3);
		B.setAsInt(-b12,0,1);
		B.setAsInt(-b12,1,0);
		B.setAsInt(-b13,0,2);
		B.setAsInt(-b13,2,0);
		B.setAsInt(-b24,1,3);
		B.setAsInt(-b24,3,1);
		B.setAsInt(-b34,2,3);
		B.setAsInt(-b34,3,2);
		/** 声明注入功率矩阵*/
		Matrix P = DenseMatrix.Factory.zeros(4, 1);
		/** 对可视化界面中用户输入的交易信息进行获取。
		 * x2为供应方，x4为接收方。
		 * x3和x5比较特殊，分别为用户输入的交易量和交易单价。
		 * 但这两个数据是以字符串形式获取的，而我们之后计算需要数字，因此在这里进行了强制类型转换。*/
		String x2=visual5.textField.getText();
		String x3=visual5.textField_1.getText();
		int x33=Integer.parseInt(x3);
		String x4=visual5.textField_2.getText();
		String x5=visual5.textField_3.getText();
		x55=Integer.parseInt(x5);
		/** 将四个节点的名称用ci代替，方便后面判断程序的编写*/
		String c1="A";
		String c2="B";
		String c3="C";
		String c4="D";
		/** 这里是对交易方和接收方进行判断，进行相应的注入功率矩阵的赋值
		 * 比如说，若交易方为A，则注入功率矩阵的第一行第一列元素为-x33,若接收方为A，则注入功率矩阵的第一行第一列元素为x33。
		 * 其余情况都为0.
		 * B、C、D的过程也是如此*/
		if(x2.equals(c1))
		{
			P.setAsInt(-x33,0,0);
		}
		else if(x4.equals(c1))
		{
			P.setAsInt(x33,0,0);
		}
		else
		{
			P.setAsInt(0,0,0);
		}
		if(x2.equals(c2))
		{
			P.setAsInt(-x33,1,0);
		}
		else if(x4.equals(c2))
		{
			P.setAsInt(x33,1,0);
		}
		else
		{
			P.setAsInt(0,1,0);
		}
		if(x2.equals(c3))
		{
			P.setAsInt(-x33,2,0);
		}
		else if(x4.equals(c3))
		{
			P.setAsInt(x33,2,0);
		}
		else
		{
			P.setAsInt(0,2,0);
		}
		if(x2.equals(c4))
		{
			P.setAsInt(-x33,3,0);
		}
		else if(x4.equals(c4))
		{
			P.setAsInt(x33,3,0);
		}
		else
		{
			P.setAsInt(0,3,0);
		}
		/** 根据Bθ=P，我们需要对θ的初值进行求解
		 * BO为B矩阵求逆，乘以P则得到θ矩阵，这里用O矩阵表示*/
		Matrix B0=B.pinv();
		Matrix O=B0.mtimes(P);
		/** 由于ujmp中矩阵元素不能直接引用，我们需要把O矩阵元素提取出来*/
		double o1=O.getAsDouble(0,0);
		double o2=O.getAsDouble(1,0);
		double o3=O.getAsDouble(2,0);
		double o4=O.getAsDouble(3,0);
		/** 声明a,用于之后迭代中传递值*/
		double a=0;
		/** 声明w,即松弛因子，根据文章中的收敛范围（0-2），取1进行计算*/
		double w=1;
		/** 声明系数r,r=w/电抗和*/
		double r=0;
		r=w/2;
		/** 和上面一样，对交易方和供应方进行判断，然后带入迭代公式，更新相应的相角值*/
		if(x2.equals(c1))
		{
			while((x33+(o1-o2)/1+(o1-o3)/1)>=0.01)
			{
			a=o1-r*(x33+(o1-o2)/1+(o1-o3)/1);
			o1=a;
			}
		}
		else if(x4.equals(c1))
		{
			while((-x33+(o1-o2)/1+(o1-o3)/1)>=0.01)
			{
			a=o1-r*(-x33+(o1-o2)/1+(o1-o3)/1);
			o1=a;
			}
		}
		if(x2.equals(c2))
		{
			while((x33+(o2-o1)/1+(o2-o4)/1)>=0.01)
			{
			a=o2-r*(x33+(o2-o1)/1+(o2-o4)/1);
			o2=a;
			}
		}
		else if(x4.equals(c2))
		{
			while((-x33+(o2-o1)/1+(o2-o4)/1)>=0.01)
			{
			a=o2-r*(-x33+(o2-o1)/1+(o2-o4)/1);
			o2=a;
			}
		}
		if(x2.equals(c3))
		{
			while((x33+(o3-o1)/1+(o3-o4)/1)>=0.01)
			{
			a=o3-r*(x33+(o3-o1)/1+(o3-o4)/1);
			o3=a;
			}
		}
		else if(x4.equals(c3))
		{
			while((-x33+(o3-o1)/1+(o3-o4)/1)>=0.01)
			{
			a=o3-r*(-x33+(o3-o1)/1+(o3-o4)/1);
			o3=a;
			}
		}
		if(x2.equals(c4))
		{
			while((x33+(o4-o2)/1+(o4-o3)/1)>=0.01)
			{
			a=o4-r*(x33+(o4-o2)/1+(o4-o3)/1);
			o4=a;
			}
		}
		else if(x4.equals(c4))
		{
			while((x33+(o4-o2)/1+(o4-o3)/1)>=0.01)
			{
			a=o4-r*(x33+(o4-o2)/1+(o4-o3)/1);
			o4=a;
			}
		}
		/** 根据得到的相角值，求解各线路的功率。
		 * 我这里还进行了取绝对值的步骤，方便之后与线路约束的最大潮流进行比较*/
	    P12=Math.abs((o1-o2)/1);
	    P13=Math.abs((o1-o3)/1);
	    P24=Math.abs((o2-o4)/1);
	    P34=Math.abs((o3-o4)/1);
	    /** 比较得到最大功率，因为四个功率中任一个超过线路极限，就不符合条件，而如果最大功率都没超过线路极限，那么肯定符合条件
	     * 当然，这是因为我在这里将四条线路的极限都设置为2
	     * 如果今后进行更复杂网络的调试，每条线路都有不同的极限值，那就每条线路的功率和线路极限进行比较，然后把结果用或逻辑进行连接判断*/
	    double P1=Math.max(P12, P13);
	    double P2=Math.max(P24, P34);
	    double PP=Math.max(P1, P2);
	    double Pmax=2;
	    /** 声明阻塞价格price*/
	    double price=0;
	    /** 将安全校验的判断，若没通过安全校验，则弹出警告信息框并计算相应的阻塞价格*/
		if(PP>Pmax)
		{
			price=x55+(PP-Pmax)*Pmax;
			JOptionPane.showMessageDialog(null, "安全校验未通过", "警告", JOptionPane.ERROR_MESSAGE);
		}
		/** 若通过了安全校验，则弹出恭喜信息框*/
		else
		{
			JOptionPane.showMessageDialog(null, "安全校验通过", "恭喜", JOptionPane.PLAIN_MESSAGE);
		}
		/** 输出阻塞价格，在Console里显示*/
		System.out.printf("阻塞价格为%f",price);
    }	
}