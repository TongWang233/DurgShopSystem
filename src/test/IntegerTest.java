package test;

public class IntegerTest {

	public static void main(String[] args) {
		
		//装箱
		Integer integer1 = new Integer(1);
		integer1  = Integer.valueOf(2);
		
		//拆箱
		int i = integer1.intValue();
		
		//完成数值类型间的转换
		long l  = integer1.longValue();
		short s = integer1.shortValue();
		byte b = integer1.byteValue();
		float f  = integer1.floatValue();
		double d = integer1.doubleValue();
		
		//完成数值类型与字符类型间的转换
		String string = "123";
		int num = new Integer(string).intValue();
		
		//完成数值类型与字符类型间的转换 
		string = new Integer(num).toString();
		//+号在字符串运算中是字符串连接符，不是算术运算符
		string = num  +  "";
		
		//自动装箱
		Integer integer2 = 1;
		//自动拆箱
		int k = integer2;
		
		//自动类型转换: 小范围的数据类型可以   自动转换成   大范围的数据类型
		// byte ->short  -> int -->long    ->double
		//                      --> float -->
		//        char  -->           
		byte bt1 = 88;
		short s2 = bt1;
		int i2 = s2;
		long l2 = i2;
		
		//字符类型保存数据时，保存的是图形符号的编码值: 0~65535 
		char c = 'A';
		int j = c;
		
		
		//强制类型转换：类型强制转换时会损失数据的精度,高位数据会被截取掉，造成损失.
		int q = 65537;
		//溢出
		char ch = (char)q;
		q = ch;
		System.out.println(q);
		

		
	}
}
