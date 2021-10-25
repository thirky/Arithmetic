package com.cnblogs.thirky;

import java.io.File;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		int flag = 1;
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Calc calc = new Calc();
		String inStr = "待读取命令行输入";
		File file = null;
		int a = 0;
		int b = 0;
		int n = 0;//题目数量
		try {
			for (int i = 0; i < args.length; i++) {
				if ("-n".equals(args[i])) {
					calc.num = Integer.parseInt(args[i + 1]);
				}
				if ("-r".equals(args[i])) {
					n = Integer.parseInt(args[i+1]);
						Calc.IO(n, calc);
				}
				if ("-a".equals(args[i])) {
					inStr=args[i+1];
					Check.checkout(inStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
				System.out.println("使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围,格式:-r 10");
				if (sc1.hasNextLine()) {
					inStr = sc1.nextLine();
				}
				String[] path = inStr.split(" ");
				if (path.length > 1) {
					calc.num = Integer.parseInt(path[1]);
				} else {
					System.out.println("输入错误");
				}
				System.out.println("使用 -n 参数控制生成题目的个数,格式:-n 10");
				if (sc1.hasNextLine()) {
					inStr = sc1.nextLine();
				}
				path = inStr.split(" ");
				if (path.length > 1) {
					n = Integer.parseInt(path[1]);
				} else {
					System.out.println("输入错误");
				}
				try {
					Calc.IO(n, calc);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("判断答案对错（输入格式：Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt）：");
				if (sc1.hasNextLine()) {
					inStr = sc1.nextLine();
				}
				try {
					Check.checkout(inStr);                                                //********
				} catch (Exception e) {
					System.out.println("错误");
					e.printStackTrace();
				}
				*/
	}
}
