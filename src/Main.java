import Ynu.Sei.cpLibrary.DS.SET;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	static ArrayList<SET<Integer>> Result = new ArrayList<SET<Integer>>();// ���ս��
	static ArrayList<Integer> LineNums = new ArrayList<Integer>();// ѡ����

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int key = 100;// ��Ҫ�ļ�����
		int P = 10;// 20%
		int Counter = 0;
		ArrayList<SET<Integer>> A = new ArrayList<SET<Integer>>(); // ��ȡ�ļ��е����ݷ���A
		ArrayList<SET<Integer>> C = new ArrayList<SET<Integer>>(); // ��ȡ�ļ��е����ݷ���A
		ArrayList<SET<Integer>> B = new ArrayList<SET<Integer>>(); // ����
		ArrayList<SET<Integer>> Sp = new ArrayList<SET<Integer>>();// ����Sp
		ArrayList<SET<Integer>> U = new ArrayList<SET<Integer>>();// ����U
		duqu(A);// ��ȡ�ļ�
	

		// ����ļ��м�����ĿС��KEYֵ��С����ʾ���ݲ���
		if (key > A.size()) {
			System.out.println("���ݲ���");
			System.exit(0);
		} 
		// ��S<3Kʱ������̰���㷨
		if (key * 3 >= A.size()) {
			System.out.println("̰���㷨");

			// Result = A;//������A����Result��
			while (Counter < key) { // Counter�����ж�������ȡ������
				B = chuqu(A);
				Counter++;				
				A = B;			
			}

		
			ShowResult();
			Date d=new Date();
			
			System.out.println(d);

			}

		else {

			System.out.println("RSO�㷨"); // ����Sp����
			//����һ��С�ļ���Sp-----------------------------------
			while (true) {
				//����һ��С�ļ���Sp
				for (int i = 0; i < A.size(); i++) {
					int random1 = (int) (Math.random() * 100);
					if (random1 > P) {// ͨ��r�����ȡA��Ԫ�� 
						Sp.add(A.get(i));//����ȡ����Ԫ�ؼ���Sp��
						A.remove(i);// ��A���Ƴ���Ԫ��
						if (Sp .size() == 3 * key)//ֱ��Sp��Ԫ��Ϊ3key 
							break; }

					}
					if (Sp.size() == 3 * key)
						break;
				/*	for(int j=0;j<Sp.size();j++){
						System.out.println("SpSize"+Sp.size());
						SET<Integer> a2=Sp.get(j);
						for (int e : a2)
							System.out.print(e + " ");
						System.out.println(" ");
					}*/
				}
			//��ӡ��Sp����
			/*
			System.out.println("Sp����"+Sp.size());
			for(int num=0;num<Sp.size();num++){
				SET<Integer> a=Sp.get(num);
				for (int e : a)
					System.out.print(e + " ");
				System.out.println(" ");
			}
			*/
			//��Sp����ʹ��̰���㷨
			while (Counter < key) { // Counter�����ж�������ȡ������
				B = chuqu(Sp);
				Counter++;				
				Sp = B;			
			}
		
			//ShowResult();
			//System.out.println("��Sp���Ͻ���̰���㷨�õ��Ľ��");
			//�ϲ�С���������-----------------------------------------
			SET<Integer> a3=Result.get(0);
			for(int j=1;j<Result.size();j++){
			
				
				a3=a3.union(Result.get(j));

				}
			/*
			for(int e : a3)
				System.out.print(e+" ");
				System.out.println(" ");
				*/
			//-------------------------------------------------------------   
			//	int s = A.size();// ����Ԫ�غ�ʣ��Ԫ�ص�����s
			//	System.out.println("ʣ�༯����"+s);
				// ���켯��U------------------------------------------------
				for (int i = 0; i < A.size(); i++) {
					SET<Integer> i2 = A.get(i).difference(a3);
					
					if (i2.size()>0) {
						U.add(i2);
						
						// s=A.size();
						}
				
				}
				//ShowResult();
				//---------------------------------------------------------
				System.out.println("U-----------");
				System.out.println("U�������Ӽ�������"+U.size());
				/*for(int num=0;num<U.size();num++){
					SET<Integer> a=U.get(num);
					for (int e : a)
						System.out.print(e + " ");
					System.out.println(" ");
				}*/
//��U����ʹ��̰���㷨	
		int Counter2=0;
		while (Counter2 < key) {
			B = chuqu(U);
			Counter2++;
			U = B;
			
		}
		System.out.println("-------------------");
		int Num=0;
		int Nums=0;
		for(int j=0;j<Result.size();j++){

			Num=Result.get(j).size();
			Nums=Nums+Num;
					}
		Nums=Nums-key;
		System.out.println("��������Ԫ������Ϊ"+Nums);
		/*
		for(int ii=key;ii<Result.size();ii++){
			SET<Integer> a=Result.get(ii);
			for (int e : a)
				System.out.print(e + " ");
			System.out.println(" ");
		}
		*/
	    Date d=new Date();
		
		System.out.println(d);
		System.out.println("U�����Ӽ�����"+U.size());
//----------------------------------------------------------
		
	}
	}
	
	 
	// �ڿ���̨������
	public static void ShowResult() {
		int Num=0;
		int Nums=0;
		System.out.println("-------");
		for(int j=0;j<Result.size();j++){

			Num=Result.get(j).size();
			Nums=Nums+Num;
					}
		System.out.println("��������Ԫ������Ϊ"+Nums);

		/*for(int j=0;j<Result.size();j++){
			SET<Integer> a3=Result.get(j);
			for(int e : a3)
				System.out.print(e+" ");
				 System.out.println(" ");
					}*/
	}

	// ȡ�����Ԫ�أ��õ�ȥ���Ԫ�ؼ���ļ���
	public static ArrayList<SET<Integer>> chuqu(ArrayList<SET<Integer>> A)
			throws IOException {
		SET<Integer> a = A.get(zuichangn(A));// ��ȡ������A�����һ�����ϼ���a
		/*for(int e : a)
					System.out.print(e+" ");
					 System.out.println(" ");
					 */
		LineNums.add(zuichangn(A));// ����+1
		Result.add(a);// ��a��������C
		/*for(int j=0;j<Result.size();j++){
		SET<Integer> a3=Result.get(j);
		for(int e : a3)
			System.out.print(e+" ");
			 System.out.println(" ");
				}
				*/
		ArrayList<SET<Integer>> B = new ArrayList<SET<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			SET<Integer> a1 = A.get(i).difference(a);// ������A�е�Ԫ������Ԫ�����Ƚϣ�ȥ���ظ�Ԫ��
			// for(int e : a1)
			// System.out.print(e+" ");
			// System.out.println(" ");
			if(a1.size()>0){
			B.add(a1);// ���ȽϺ�Ľ������B

			
					
			}
		}
		/*for(int j=0;j<B.size();j++){
			SET<Integer> a2=B.get(j);
			for (int e : a2)
				System.out.print(e + " ");
			System.out.println(" ");
		}
		System.out.println("------ ");
*/
		return B;

	}

	// �����λ�ô�0��ʼ
	// �ڼ���A��ȡ��Ԫ�����ļ���
	public static int zuichangn(ArrayList<SET<Integer>> A) throws IOException {
		int max = 0;// ������
		int num = 0;
		for (int i = 0; i < A.size(); i++) {// ��������Ԫ�س���
			// ͨ���Ƚϵó����������Ӽ�
			if (A.get(i).size() > max) {
				max = A.get(i).size();

				num = i;

			}

		}
		
		return num;
	}
	public static int zuichang(ArrayList<SET<Integer>> C) throws IOException {
		int max = 0;// ������
		int num = 0;
		for (int i = 0; i < C.size(); i++) {// ��������Ԫ�س���
			// ͨ���Ƚϵó����������Ӽ�
			if (C.get(i).size() > max) {
				max = C.get(i).size();

				num = i;

			}

		}
		
		SET<Integer> a2=C.get(num);
		for (int e : a2)
			System.out.print(e + " ");
		System.out.println(" ");
		return num;
	}

	// ��ȡ�ļ�����ü���A
	public static ArrayList<SET<Integer>> duqu(ArrayList<SET<Integer>> A)
			throws IOException {
		InputStream is = new FileInputStream("E:\\3.txt");
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine();// ���ж�ȡ

		while (line != null) {
			String cut[] = line.split(" ");// ÿһ��ʹ�ÿո�ָ�

			SET<Integer> s = new SET<Integer>();// �洢һ������

			for (int i = 0; i < cut.length; i++) {
				s.add(Integer.valueOf(cut[i]));// ��һ�������ڵ�Ԫ�ؼ��뼯��S����������һ������ͬ������
			}

			A.add(s);// ��ÿһ�����ϼ��뼯��A
			//for (int e : s)
			//	System.out.print(e + " ");
			//System.out.println(" ");
			line = reader.readLine();
		}
		reader.close();
		is.close();
		System.out.println("���������� " + A.size());
		return A;

	}

}
	
