package utils;

import java.util.Arrays;

import org.junit.Test;

public abstract class BitUtils {

	/**
	 * ��int��������ת��Ϊ�����Ʊ�ʾ ��char[]
	 * @param num	Ҫת��������
	 * @param length	Ҫ�����ĳ��� 	-1��ʾ�ж���λ�ͱ�������λ
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
        //Ĭ��ȫ'0'
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
	 * ��int��������ת��Ϊ�����Ʊ�ʾ ��char[]
	 * @param num
	 * @return
	 */
	public static char[] intToBits(int num) {
		return intToBits(num, -1);
	}
	
	/**
	 * ����һ��32λ�����Ķ�������1�ĸ�������ż�ԣ�
	 * ���������ݵĶ����Ʊ�ʾ����ż��������1ʱ�������0���������������1
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
	 * ����һ��32λ�����Ķ�������1�ĸ���
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
		x = (x & 0x55555555) + ((x >>> 1) & 0x55555555);	//����ʹ�� >> ���� >>> ������
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
	//���ֲ���32λ������ǰ��0����
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
	 *  ����һ��С��2^32���������������������һ��32λ�Ķ���������ʾ������32λ��0���㣩�����ǳ��������������ǰ16λΪ����λ������16λΪ����λ����
	 *  �����ĸߵ�λ���������ǿ��Եõ�һ���µ���
	 * @param x
	 * @return
	 */
	public static int exchangeHighAndLow(int x) {
		return (x >> 16) | (x << 16);
	}
	
	/**
	 * ����������
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
	 * ��λ������ȡ����ֵ
	 * ����xΪ32λ��������  �Ľ����x�ľ���ֵ 
	 * 
	 * x >>> 31�Ƕ����Ƶ����λ����������ʾx�ķ��š�
	 * �����Ϊ0��xΪ�������� ~ (x >>> 31) + 1����$00000000������κ�����������䣻
	 * ������λΪ1��xΪ�������� ~ (x >>> 31) + 1����$FFFFFFFF��x������൱��������λȡ�����������ټ�һ��
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
