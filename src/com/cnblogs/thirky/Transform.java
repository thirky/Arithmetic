package com.cnblogs.thirky;

import java.util.Stack;

/**
 * 中缀转后缀操作
 *
 */
public class Transform {
	public static void test(String str) {
		String str1=str;
		String suffix= create(str1);
		System.out.println(suffix);
	}

	/**
	 * 检测数字是否正常
	 * @param x：待检测数字
	 * @return 如果数字在0到9，则返回true，否则返回false
	 */
	public static boolean isNUm(char x) { //�ж��Ƿ�Ϊ����;
		return(x>='0'&&x<='9');
	}

	/**
	 * 返回一个操作符的优先级
	 * @param x：操作符
	 * @return 优先级
	 */
	public static int priority(char x)
	{
	    if (x == '-' || x == '+')
	        return 1;
	    else if (x == '*' || x == '/')
	        return 2;
	    else if (x == '(')
	        return 0;
	    else
	        return -1;
	}

	/**
	 * 生成后缀表达式
	 * @param str：中缀表达式
	 * @return 后缀表达式
	 */
	public static String create(String str) {
		int num=0;
		int tmp=0;
		int stackLen=0;//栈的长度
		String expression="";//输出结果字符串
		char element='a';
		boolean haveNum= false;//判断是否有数字
		Stack<Character> stack= new Stack<>();//字符栈
		str=str+"!";//为了判断结束方便加入!做为字符串结尾
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(isNUm(c)) {
				haveNum=true;
				tmp=tmp*10+(c-'0');
			}else {
				if(haveNum) {
					haveNum=false;
					expression+=tmp;
					tmp=0;
				}
				if(c=='(') {//遇到左括号无条件进栈
					num++;
					stack.push(c);
				}else if(c=='+'||c=='-'||c=='!') { //因为+-优先级最低所以全部弹出
					if(num>0) {//如果遇到左括号则停止
						stackLen= stack.size();
						for(int j=0;j<stackLen;j++) {
							element = stack.peek();
							if(element=='(') {
								break;
							}
							expression+=stack.pop();
						}
					}else {//全部弹出;
						expression+=' ';
						if(stack.size()!=0) {
							stackLen = stack.size();
							for(int j = 0; j < stackLen; j++ ) {
								expression += stack.pop();
							}
						}
					}
					if(c=='!') {
						break;
					}
					stack.push(c);
					expression+=' ';
				}else if(c=='*'||c=='/') {
					if(str.charAt(i-1)==' '){
						while (!stack.empty() && priority(c) <= priority(stack.peek())){ //弹出栈顶优先级大于当前元素的符号;
							expression +=' ';
							expression += stack.peek();
							stack.pop();
						}
						stack.push(c);
					}else {
						expression+=c;
					}
				}else if(c==')') {
					if(stack.size()!=0) {
						stackLen = stack.size();
						for(int j = 0; j < stackLen; j++ ) {
							element = stack.pop();
							if(element=='(') {
								break;
							}
							expression += element;
						}
						num--;
					}
				}else if(c==' ') {//遇到空格则加空格;
					expression+=' ';
				}
			}
		}
		return expression;
	}
}
