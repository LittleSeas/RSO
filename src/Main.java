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

	static ArrayList<SET<Integer>> Result = new ArrayList<SET<Integer>>();// 最终结果
	static ArrayList<Integer> LineNums = new ArrayList<Integer>();// 选择后的

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int key = 100;// 需要的集合数
		int P = 10;// 20%
		int Counter = 0;
		ArrayList<SET<Integer>> A = new ArrayList<SET<Integer>>(); // 读取文件中的数据放入A
		ArrayList<SET<Integer>> C = new ArrayList<SET<Integer>>(); // 读取文件中的数据放入A
		ArrayList<SET<Integer>> B = new ArrayList<SET<Integer>>(); // 缓存
		ArrayList<SET<Integer>> Sp = new ArrayList<SET<Integer>>();// 集合Sp
		ArrayList<SET<Integer>> U = new ArrayList<SET<Integer>>();// 集合U
		duqu(A);// 读取文件
	

		// 如果文件中集合数目小于KEY值大小，提示数据不足
		if (key > A.size()) {
			System.out.println("数据不足");
			System.exit(0);
		} 
		// 当S<3K时，调用贪心算法
		if (key * 3 >= A.size()) {
			System.out.println("贪心算法");

			// Result = A;//将集合A放入Result中
			while (Counter < key) { // Counter用来判断做几次取出操作
				B = chuqu(A);
				Counter++;				
				A = B;			
			}

		
			ShowResult();
			Date d=new Date();
			
			System.out.println(d);

			}

		else {

			System.out.println("RSO算法"); // 生成Sp集合
			//构造一个小的集合Sp-----------------------------------
			while (true) {
				//构造一个小的集合Sp
				for (int i = 0; i < A.size(); i++) {
					int random1 = (int) (Math.random() * 100);
					if (random1 > P) {// 通过r随机抽取A中元素 
						Sp.add(A.get(i));//将抽取到的元素加入Sp中
						A.remove(i);// 在A中移除该元素
						if (Sp .size() == 3 * key)//直到Sp中元素为3key 
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
			//打印出Sp集合
			/*
			System.out.println("Sp集合"+Sp.size());
			for(int num=0;num<Sp.size();num++){
				SET<Integer> a=Sp.get(num);
				for (int e : a)
					System.out.print(e + " ");
				System.out.println(" ");
			}
			*/
			//对Sp集合使用贪心算法
			while (Counter < key) { // Counter用来判断做几次取出操作
				B = chuqu(Sp);
				Counter++;				
				Sp = B;			
			}
		
			//ShowResult();
			//System.out.println("对Sp集合进行贪心算法得到的结果");
			//合并小集合求解结果-----------------------------------------
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
			//	int s = A.size();// 抽中元素后剩余元素的数量s
			//	System.out.println("剩余集合数"+s);
				// 构造集合U------------------------------------------------
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
				System.out.println("U集合中子集的数量"+U.size());
				/*for(int num=0;num<U.size();num++){
					SET<Integer> a=U.get(num);
					for (int e : a)
						System.out.print(e + " ");
					System.out.println(" ");
				}*/
//对U集合使用贪心算法	
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
		System.out.println("并集最终元素总数为"+Nums);
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
		System.out.println("U集的子集数量"+U.size());
//----------------------------------------------------------
		
	}
	}
	
	 
	// 在控制台输出结果
	public static void ShowResult() {
		int Num=0;
		int Nums=0;
		System.out.println("-------");
		for(int j=0;j<Result.size();j++){

			Num=Result.get(j).size();
			Nums=Nums+Num;
					}
		System.out.println("并集最终元素总数为"+Nums);

		/*for(int j=0;j<Result.size();j++){
			SET<Integer> a3=Result.get(j);
			for(int e : a3)
				System.out.print(e+" ");
				 System.out.println(" ");
					}*/
	}

	// 取出最长的元素，得到去掉最长元素集后的集合
	public static ArrayList<SET<Integer>> chuqu(ArrayList<SET<Integer>> A)
			throws IOException {
		SET<Integer> a = A.get(zuichangn(A));// 先取出集合A中最长的一个集合加入a
		/*for(int e : a)
					System.out.print(e+" ");
					 System.out.println(" ");
					 */
		LineNums.add(zuichangn(A));// 行数+1
		Result.add(a);// 将a加入结果集C
		/*for(int j=0;j<Result.size();j++){
		SET<Integer> a3=Result.get(j);
		for(int e : a3)
			System.out.print(e+" ");
			 System.out.println(" ");
				}
				*/
		ArrayList<SET<Integer>> B = new ArrayList<SET<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			SET<Integer> a1 = A.get(i).difference(a);// 将集合A中的元素与中元素做比较，去掉重复元素
			// for(int e : a1)
			// System.out.print(e+" ");
			// System.out.println(" ");
			if(a1.size()>0){
			B.add(a1);// 将比较后的结果存入B

			
					
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

	// 求最长的位置从0开始
	// 在集合A中取出元素最多的集合
	public static int zuichangn(ArrayList<SET<Integer>> A) throws IOException {
		int max = 0;// 监听哨
		int num = 0;
		for (int i = 0; i < A.size(); i++) {// 遍历所有元素长度
			// 通过比较得出长度最大的子集
			if (A.get(i).size() > max) {
				max = A.get(i).size();

				num = i;

			}

		}
		
		return num;
	}
	public static int zuichang(ArrayList<SET<Integer>> C) throws IOException {
		int max = 0;// 监听哨
		int num = 0;
		for (int i = 0; i < C.size(); i++) {// 遍历所有元素长度
			// 通过比较得出长度最大的子集
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

	// 读取文件，获得集合A
	public static ArrayList<SET<Integer>> duqu(ArrayList<SET<Integer>> A)
			throws IOException {
		InputStream is = new FileInputStream("E:\\3.txt");
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine();// 按行读取

		while (line != null) {
			String cut[] = line.split(" ");// 每一行使用空格分割

			SET<Integer> s = new SET<Integer>();// 存储一个集合

			for (int i = 0; i < cut.length; i++) {
				s.add(Integer.valueOf(cut[i]));// 把一个集合内的元素加入集合S，会消除掉一行中相同的数字
			}

			A.add(s);// 把每一个集合加入集合A
			//for (int e : s)
			//	System.out.print(e + " ");
			//System.out.println(" ");
			line = reader.readLine();
		}
		reader.close();
		is.close();
		System.out.println("集合总数： " + A.size());
		return A;

	}

}
	
