package com.cnblogs.thirky;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


/**
 * 生成中缀表达式
 *
 */
public class Calc {
	int num;//式子中数字小于num

	/**
	 * 分数转换，将真分数转化为假分数
	 * @param a：真分数
	 * @return 假分数
	 */
	public String NumShift(String a) {
		String b[] = a.split("'");
		String c[] = b[1].split("/");
        int d = Integer.parseInt(b[0]);
        int e = Integer.parseInt(c[0]);//分子
        int f = Integer.parseInt(c[1]);//分母
        int g = d * f + e;
        String h = g + "/" + f;
        return h;
	}

	/**
	 * 生成整数或分数
	 * @return 整数或分数
	 */
	public String CreateNum() {
		Random rand = new Random();
		int a = rand.nextInt(num - 1);
		int b = rand.nextInt(4);//分子
		int c = rand.nextInt(6) + b;//分母
		String d = Integer.toString(a);
		if(b >= 3) {
			if(d.equals("0")) {
				d = b + "/" + c;
			}
			else 
				d = a + "'" + b + "/" + c;
		}
		if(d.equals("0")) d = Integer.toString(1);
		return d;
	}

	/**
	 * 随机生成运算符号
	 * @return 运算符号
	 */
	public String operator() {
		Random rand = new Random();
		int a = rand.nextInt(4);
		String str = null;
		if(a == 0)
			str = "+";
		else if(a == 1)
			str = "-";
		else if(a == 2)
			str = "*";
		else 
			str = "/";
		return str;
	}

	/**
	 * 生成表达式
	 * @return 表达式
	 */
    public String create(){
		 String str = "";
		 String str1=""; 
		 do { 
			str = "";
			str1 = "";
		 int t = 0;
		 Random rand = new Random();
		 t = rand.nextInt(3) + 2;
		 String[] number = new String[t];//存放数字
		 String[] symbol = new String[t-1];//存放运算符号
		 String[] total = new String[4*t-3];//存放式子
		 String[] total1 = new String[4*t-3];
		 for(int i = 0;i < t;i++) {
			 number[i] = CreateNum();
		 }
		 for(int i = 0;i < t-1;i++) {
			 symbol[i] = operator();
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 if(i%4 == 0) {
				 total[i] = number[i/4];
				 total1[i] = number[i/4];
			 }
			 else if(i%4 == 2) {
				 int k = (i+2)/4 - 1;
				 total[i] = symbol[k];
				 total1[i] = symbol[k];
			 }
			 else {total[i] = " ";
			       total1[i] = " ";
			 }
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 if(total1[i].contains("'")) {
				total1[i] = NumShift(total1[i]);
			 }
		 }
		 if(t == 4) {
			 if((symbol[1] == "*"||symbol[1] == "/") && (symbol[0] == "+"||symbol[0] == "-")) {
				                      total[0] = "(" + total[0];
                                      total[4] = total[4] + ")"; 
                                      total1[0] = "(" + total1[0];
                                      total1[4] = total1[4] + ")"; 
			                                    }
			 if((symbol[1] == "*"||symbol[1] == "-") && (symbol[2] == "+"||symbol[2] == "-")) {
				 total[8] = "(" + total[8];
                 total[12] = total[12] + ")";
                 total1[8] = "(" + total1[8];
                 total1[12] = total1[12] + ")";
			 }
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 str = str + total[i];
			 str1 = str1 + total1[i];
		 }
		 }while(Suffix.count(str1).contains("-"));
		 // System.out.println(str);
		 return str+" ="+";"+str1;
	}

	/**
	 * 存放文件操作
	 * @param d
	 * @param a
	 * @throws Exception
	 */
    public static void IO(int d, Calc a) throws Exception{
    	String s="";
    	Random random=new Random();
		FileWriter fileWriter = new FileWriter("./Exercises.txt");//题目文件
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		FileWriter fileWriter1 = new FileWriter("./Answers.txt");//假答案文件
		BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);

		FileWriter fileWriter2 = new FileWriter("./Results.txt");//真答案文件
		BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);

    	for(int i=0; i < d ; i++) {
    		String[] path = a.create().split(";");
    		bufferedWriter.write(i+1+". "+path[0]);
    		bufferedWriter.newLine();
    		bufferedWriter.flush();

    		bufferedWriter1.write(i+1+". " + random.nextInt(9));
    		bufferedWriter1.newLine();
    		bufferedWriter1.flush();

			bufferedWriter2.write(i+1+". " + Suffix.count(path[1]));
			bufferedWriter2.newLine();
			bufferedWriter2.flush();

    	}
    	bufferedWriter.close();
    	bufferedWriter1.close();
		bufferedWriter2.close();

    }
}
