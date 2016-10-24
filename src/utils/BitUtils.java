package utils;

import java.util.Arrays;

import org.junit.Test;

public abstract class BitUtils {

	/**
	 * 将int类型数字转换为二进制表示 的char[]
	 * @param num	要转换的数字
	 * @param length	要保留的长度 	-1表示有多少位就保留多少位
	 * @return
	 */
	public static char[] intToBits(int num, int length) {
		if(length == 0) {
			throw new IllegalArgumentException("Length can't be 0.");
		}
		int defaultLength = 32;
		int charPos = defaultLength;
		char[] buf = new char[defaultLength];
        char[] digits = {'0', '1'};
        //默认全'0'
        for(int i=0; i<buf.length; i++) {
        	buf[i] = digits[0];
        }
        do {
            buf[--charPos] = digits[num & 1];
            num >>>= 1;
        } while (num != 0);
        if(length == -1) {
        	return Arrays.copyOfRange(buf, charPos, buf.length);
        }
        return Arrays.copyOfRange(buf, defaultLength - length, buf.length);
	}
	
	/**
	 * 将int类型数字转换为二进制表示 的char[]
	 * @param num
	 * @return
	 */
	public static char[] intToBits(int num) {
		return intToBits(num, -1);
	}
	
	/**
	 * 计算一个32位整数的二进制中1的个数的奇偶性，
	 * 当输入数据的二进制表示里有偶数个数字1时程序输出0，有奇数个则输出1
	 * @param x
	 * @return
	 */
	public static int bitCountParityFun1(int x) {
		int count = 0;
		for(int i = 0; i < 32; i++) {
			count += (x & 1);
			x = x >> 1;
		}
		return count & 1;
	}
	
	public static int bitCountParityFun2(int x){
		x = x ^ ( x >> 1);
		x = x ^ ( x >> 2);
		x = x ^ ( x >> 4);
		x = x ^ ( x >> 8);
		x = x ^ ( x >> 16);
		return x & 1;
	}
	
	public static int bitCountParityFun3(int x){
		x = x ^ ( x >>> 1);
		x = x ^ ( x >>> 2);
		x = x ^ ( x >>> 4);
		x = x ^ ( x >>> 8);
		x = x ^ ( x >>> 16);
		return x & 1;
	}
	
	/**
	 * 计算一个32位整数的二进制中1的个数
	 * @param x
	 * @return
	 */
	public static int bitCountFun1(int x){
		int count = 0;
		for(int i = 0; i < 32; i++) {
			count += (x & 1);
			x = x >> 1;
		}
		return count;
	}
	
	public static int bitCountFun2(int x){
		x = (x & 0x55555555) + ((x >>> 1) & 0x55555555);	//这里使用 >> 或者 >>> 都可以
		x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
		x = (x & 0x0F0F0F0F) + ((x >>> 4) & 0x0F0F0F0F);
		x = (x & 0x00FF00FF) + ((x >>> 8) & 0x00FF00FF);
		x = (x & 0x0000FFFF) + ((x >>> 16) & 0x0000FFFF);
		return x;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	//二分查找32位整数的前导0个数
	public static int bitPreZeroCountFun1(int x) {
		int n = 0;
		for(int i = 31; i>=0; i--) {
			if(((x >> i) & 1) != 0){
				break;
			}
			n ++;
		}
		return n;
	}
	
	public static int bitPreZeroCountFun2(int x) {
		int n = 0;
		if (x == 0) {
			return 32;
		}
		if ((x >> 16) == 0) {n = n + 16; x = x <<16;}
		if ((x >> 24) == 0) {n = n + 8; x = x << 8;}
		if ((x >> 28) == 0) {n = n + 4; x = x << 4;}
		if ((x >> 30) == 0) {n = n + 2; x = x << 2;}
		if ((x >> 31) == 0) {n = n + 1; x = x << 1;}
		return n;
	}

	/**
	 *  给出一个小于2^32的正整数。这个数可以用一个32位的二进制数表示（不足32位用0补足）。我们称这个二进制数的前16位为“高位”，后16位为“低位”。
	 *  将它的高低位交换，我们可以得到一个新的数
	 * @param x
	 * @return
	 */
	public static int exchangeHighAndLow(int x) {
		return (x >> 16) | (x << 16);
	}
	
	/**
	 * 二进制逆序
	 * @param x
	 * @return
	 */
	public static int bitReverse(int x) {
		int y = 0;
		for(int i = 0; i < 16; i++) {
			y = y | (((x >> i) & 1) << (31 - i));
			y = y | (((x >> (31 - i)) & 1) << i);
		}
		return y;
	}
	
	/**
	 * 用位运算来取绝对值
	 * 假设x为32位整数，则  的结果是x的绝对值 
	 * 
	 * x >>> 31是二进制的最高位，它用来表示x的符号。
	 * 如果它为0（x为正），则 ~ (x >>> 31) + 1等于$00000000，异或任何数结果都不变；
	 * 如果最高位为1（x为负），则 ~ (x >>> 31) + 1等于$FFFFFFFF，x异或它相当于所有数位取反，异或完后再加一。
	 * @param x
	 * @return
	 */
	public static int abs(int x) {
		return  (x ^ (~ (x >>> 31) + 1)) + (x >>> 31);
	}
	
	@Test
	public void test1() {
		int x = -120;
		System.out.println(Math.abs(x));
		System.out.println(abs(x));
		System.out.println(B.abs(x));
	}
}

class B extends BitUtils {

}
