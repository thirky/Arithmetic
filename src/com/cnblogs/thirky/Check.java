package com.cnblogs.thirky;

import com.sun.glass.ui.ClipboardAssistance;

import java.io.*;

/**
 * 检验类，检验文件答案
 */
public class Check {
	/**
	 * 核对答案文件内答案是否正确并生成一个Grade文件存放信息
	 *
	 * @param str 读取命令行内输入的文件
	 * @throws Exception
	 */
	public static void checkout(String str) throws Exception {
		BufferedReader bufferedReader1 = null;
		String line = "";
		String Correct = "(";
		String Wrong = "(";
		String Results = null;
		String Answers = null;
		int a = 0;
		int b = 0;
		String Ans = str;//answer.txt
		FileWriter fw = new FileWriter("./Grade.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		FileReader fileReader = new FileReader("./Results.txt");//真答案
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		if (Ans!=null) {
			FileReader fileReader1 = new FileReader(Ans);
			bufferedReader1 = new BufferedReader(fileReader1);
		}

		for (int i = 1; (line = bufferedReader.readLine()) != null; i++) {
			String []Result = line.split("\\. ");//真答案
			if (Result.length > 1) {
				Results = Result[1];
			}

			String []Answer = bufferedReader1.readLine().split("\\. ");//假答案
			if (Answer.length > 1) {
				Answers = Answer[1];
			}
			if (Results.equals(Answers)) {
				Correct = Correct + i + ",";
				a++;
			} else {
				Wrong = Wrong + i + ",";
				b++;
			}
		}
		Correct = Correct.substring(0, Correct.length() - 1);
		Wrong = Wrong.substring(0, Wrong.length() - 1);
		bw.write("Correct: " + a + Correct + ")");
		bw.newLine();
		bw.write("Wrong: " + b + Wrong + ")");
		bw.close();
	}
}
