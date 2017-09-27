

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
 * �������Ǵ�����compute����࣬���ڰ�ȫУ����������������е�chaoliu�����ڳ�������ķ�����
 * @author zsj
 * @Time 2017-06-03
 */

public class compute {
	/** ��������P12����ʾ1��2֮����·�������Ĺ��� */
	public static double P12=0;
	/** ��������P13����ʾ1��3֮����·�������Ĺ��� */
    public static double P13=0;
    /** ��������P24����ʾ2��4֮����·�������Ĺ��� */
    public static double P24=0;
    /** ��������P34����ʾ3��4֮����·�������Ĺ��� */
    public static double P34=0;
    /** ��������x55����ʾ���״�ɵĽ��׵��� */
    public static int x55=0;

    public static void chaoliu()
    {
    	/** ��·���������ã�Ϊ�˷�����ڵļ���˶Ժͳ�����ԣ�����ѡȡ�˽�Ϊ�򻯵���·�ṹ�Ͳ�����
    	 *bi0��ʾ���ǽڵ�û�жԵ�֧·�����ڵ�翹Ϊ1��������Ϊ-1���൱����һ����򵥵�������
    	 *��Ȼ���������ǿ�����Ʋ�ͬ����·��Ȼ���������޸���Ӧ�Ĳ���  */
		int b10=0;
		int b20=0;
		int b30=0;
		int b40=0;
		int b12=-1,b21=-1;
		int b13=-1,b31=-1;
		int b24=-1,b42=-1;
		int b34=-1,b43=-1;
		/** n��ʾ����������Ľڵ�ϵͳ�Ľڵ㵼�ɾ���Ȼ��4��*/
		int n=4;
		/** �������ɾ��� */
		Matrix B = DenseMatrix.Factory.zeros(n, n);
		/** �Ե��ɾ����е�Ԫ�ؽ��и�ֵ���������÷���Ϊ�������ֵ���С��У�*/
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
		/** ����ע�빦�ʾ���*/
		Matrix P = DenseMatrix.Factory.zeros(4, 1);
		/** �Կ��ӻ��������û�����Ľ�����Ϣ���л�ȡ��
		 * x2Ϊ��Ӧ����x4Ϊ���շ���
		 * x3��x5�Ƚ����⣬�ֱ�Ϊ�û�����Ľ������ͽ��׵��ۡ�
		 * �����������������ַ�����ʽ��ȡ�ģ�������֮�������Ҫ���֣���������������ǿ������ת����*/
		String x2=visual5.textField.getText();
		String x3=visual5.textField_1.getText();
		int x33=Integer.parseInt(x3);
		String x4=visual5.textField_2.getText();
		String x5=visual5.textField_3.getText();
		x55=Integer.parseInt(x5);
		/** ���ĸ��ڵ��������ci���棬��������жϳ���ı�д*/
		String c1="A";
		String c2="B";
		String c3="C";
		String c4="D";
		/** �����ǶԽ��׷��ͽ��շ������жϣ�������Ӧ��ע�빦�ʾ���ĸ�ֵ
		 * ����˵�������׷�ΪA����ע�빦�ʾ���ĵ�һ�е�һ��Ԫ��Ϊ-x33,�����շ�ΪA����ע�빦�ʾ���ĵ�һ�е�һ��Ԫ��Ϊx33��
		 * ���������Ϊ0.
		 * B��C��D�Ĺ���Ҳ�����*/
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
		/** ����B��=P��������Ҫ�Ԧȵĳ�ֵ�������
		 * BOΪB�������棬����P��õ��Ⱦ���������O�����ʾ*/
		Matrix B0=B.pinv();
		Matrix O=B0.mtimes(P);
		/** ����ujmp�о���Ԫ�ز���ֱ�����ã�������Ҫ��O����Ԫ����ȡ����*/
		double o1=O.getAsDouble(0,0);
		double o2=O.getAsDouble(1,0);
		double o3=O.getAsDouble(2,0);
		double o4=O.getAsDouble(3,0);
		/** ����a,����֮������д���ֵ*/
		double a=0;
		/** ����w,���ɳ����ӣ����������е�������Χ��0-2����ȡ1���м���*/
		double w=1;
		/** ����ϵ��r,r=w/�翹��*/
		double r=0;
		r=w/2;
		/** ������һ�����Խ��׷��͹�Ӧ�������жϣ�Ȼ����������ʽ��������Ӧ�����ֵ*/
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
		/** ���ݵõ������ֵ��������·�Ĺ��ʡ�
		 * �����ﻹ������ȡ����ֵ�Ĳ��裬����֮������·Լ������������бȽ�*/
	    P12=Math.abs((o1-o2)/1);
	    P13=Math.abs((o1-o3)/1);
	    P24=Math.abs((o2-o4)/1);
	    P34=Math.abs((o3-o4)/1);
	    /** �Ƚϵõ�����ʣ���Ϊ�ĸ���������һ��������·���ޣ��Ͳ��������������������ʶ�û������·���ޣ���ô�϶���������
	     * ��Ȼ��������Ϊ�������ｫ������·�ļ��޶�����Ϊ2
	     * ��������и���������ĵ��ԣ�ÿ����·���в�ͬ�ļ���ֵ���Ǿ�ÿ����·�Ĺ��ʺ���·���޽��бȽϣ�Ȼ��ѽ���û��߼����������ж�*/
	    double P1=Math.max(P12, P13);
	    double P2=Math.max(P24, P34);
	    double PP=Math.max(P1, P2);
	    double Pmax=2;
	    /** ���������۸�price*/
	    double price=0;
	    /** ����ȫУ����жϣ���ûͨ����ȫУ�飬�򵯳�������Ϣ�򲢼�����Ӧ�������۸�*/
		if(PP>Pmax)
		{
			price=x55+(PP-Pmax)*Pmax;
			JOptionPane.showMessageDialog(null, "��ȫУ��δͨ��", "����", JOptionPane.ERROR_MESSAGE);
		}
		/** ��ͨ���˰�ȫУ�飬�򵯳���ϲ��Ϣ��*/
		else
		{
			JOptionPane.showMessageDialog(null, "��ȫУ��ͨ��", "��ϲ", JOptionPane.PLAIN_MESSAGE);
		}
		/** ��������۸���Console����ʾ*/
		System.out.printf("�����۸�Ϊ%f",price);
    }	
}