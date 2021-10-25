package com.cnblogs.thirky;
import java.util.Stack;


/**
 * 计算后缀表达式
 */
public class Suffix {
	public static String count(String str1) {
		String str=Transform.create(str1);
		char x='b';
		boolean haveNum= false;//是否有数字
		boolean haveFraction= false;//是否为分数
		int tmp=0;//保存数字也充当分母
		int numer=0;//分子
		int div=0;//最大公约数
		Num a=null;
		Num b=null;
		Num c=null;
		Stack<Num> number= new Stack<>();
		for(int i=0; i<str.length();i++) {
			x=str.charAt(i);
			if(isNum(x)) {
					haveNum=true;
					tmp=tmp*10+(x-'0');
				}else {
					if(haveNum) {
						haveNum= false;
						if(isSpace(x)) {
							if(haveFraction) {
								Num xx= new Num(numer,tmp);
								number.push(xx);//存分数进栈
								tmp=0;
							}else {
								Num xx= new Num(tmp,1);
								number.push(xx);//存整数进栈
								tmp=0;
							}
						}else {
							haveFraction= true;
							numer=tmp;
							tmp=0;
							continue;
						}
					}
					if(x=='+') { //以下操作遇到字符则从数栈中弹出两个数进行计算
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c = add(a,b);
					}else if(x=='-') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=subtract(a, b);
					}else if(x=='*') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=mul(a, b);
					}else if(x=='/') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=division(a, b);
					}else if(x==' ') {
						if(haveFraction) {
							haveFraction = false;
						}
						continue;
					}
					number.push(c);
				}
			}
		if (number.size() > 1) {
			System.out.println("错误"); //如果所有操作符都进行相应操作数后栈内还有多个元素，则说明出错
		}
		Num z=number.peek();
		if (z.numerator != 0) {               //若分子不为0，则对分子分母约分 
			div = gcd(c.denominator, c.numerator);
			z.numerator /= div;
			z.denominator /= div;
		}
		number.pop();
		return Suffix.transform(z.numerator, z.denominator);
	}
	
	public static boolean isNum(char x) { //是否为数字
		return(x>='0'&&x<='9');
	}
	
	public static Num add(Num a, Num b) { //加法
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator + b.numerator * a.denominator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num subtract(Num a, Num b) { //减法
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator - b.numerator * a.denominator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num mul(Num a, Num b) { //乘法
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.numerator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num division(Num a, Num b) { //除法
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator;
		c.denominator = a.denominator * b.numerator;
		return c;
	}
	
	public static int gcd(int a,int b) { //辗转相除法求最大公约数
		{
		    int c=a % b;
		    while(c!=0)
		    {
		        
		        a = b;
		        b = c;
		        c = a % b;
		    }
		    return b;
		}
	}
	public static boolean isSpace(char x) {
		return(x==' '||x=='*'||x=='+'||x=='-'); //判断不为除法符号;
	}
	
	public static String transform(int i, int j) {
		if(i>=j) {
			if(j==1) {
				return i+"";
			}
			return i/j+"'"+i%j+"/"+j;
		}else {
			return i+"/"+j;
		}
	}
}
