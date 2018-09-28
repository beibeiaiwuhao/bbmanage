package com.beibei.bbmanage.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * ClassName: 字符串处理工具类
 * Function: TODO ADD FUNCTION.
 * date: 2017年5月3日 下午3:20:22
 *
 * @author zhao rui
 * @version
 */
public class StringUtil {
	
	/**  
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)  
     */    
    static final char DBC_CHAR_START = 33; // 半角!    
    
    /**  
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)  
     */    
    static final char DBC_CHAR_END = 126; // 半角~    
    
    /**  
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281  
     */    
    static final char SBC_CHAR_START = 65281; // 全角！    
    
    /**  
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374  
     */    
    static final char SBC_CHAR_END = 65374; // 全角～    
    
    /**  
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移  
     */    
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔    
    
    /**  
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理  
     */    
    static final char SBC_SPACE = 12288; // 全角空格 12288    
    
    /**  
     * 半角空格的值，在ASCII中为32(Decimal)  
     */    
    static final char DBC_SPACE = ' '; // 半角空格 
	
	/**
	 * 判断字符串是否为null、“ ”、“null”
	 * @param obj
	 * @return
	 */
	public static boolean isNull(String obj) {
		if (obj == null){
			return true;
		}
		else if (obj.toString().trim().equals("")){
			return true;
		}
		else if(obj.toString().trim().toLowerCase().equals("null")){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断字符串是否为null、“ ”、“null”
	 * @param obj
	 * @return
	 */
	public static String convtNull2Blank(String obj) {
		if (obj == null){
			return "";
		}
		else if(obj.toString().trim().equals(" ")){
			return "";
		}
		else if(obj.toString().trim().toLowerCase().equals("null")){
			return "";
		}
		
		return obj;
	}

	/**
	 * 正则验证是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
		Matcher match = pattern.matcher(str);
		
		return match.matches();
	}
   
	/** 
     * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端 
     *  
     * @param l 
     *            长整数 
     * @return 代表长整数的字节数组 
     */  
    public static byte[] longToBytes(long l) {  
        byte[] b = new byte[8];  
        b[0] = (byte) (l >>> 56);  
        b[1] = (byte) (l >>> 48);  
        b[2] = (byte) (l >>> 40);  
        b[3] = (byte) (l >>> 32);  
        b[4] = (byte) (l >>> 24);  
        b[5] = (byte) (l >>> 16);  
        b[6] = (byte) (l >>> 8);  
        b[7] = (byte) (l);  
        return b;  
    } 
    
    /**  
     * <PRE>  
     * 半角字符->全角字符转换    
     * 只处理空格，!到˜之间的字符，忽略其他  
     * </PRE>  
     */    
    public static String strConvertHalf2FullWidth(String src) {    
        if (StringUtil.isNull(src)) {    
            return src;    
        }    
        StringBuilder buf = new StringBuilder(src.length());    
        char[] ca = src.toCharArray();    
        for (int i = 0; i < ca.length; i++) {    
            if (ca[i] == DBC_SPACE) {
                buf.append(SBC_SPACE);    
            } 
            else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) {
                buf.append((char) (ca[i] + CONVERT_STEP));    
            } else {
                buf.append(ca[i]);    
            }    
        }    
        return buf.toString();    
    }    
    
    /**  
     * <PRE>  
     * 全角字符->半角字符转换    
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他  
     * </PRE>  
     */    
    public static String strConvertFull2Half(String src) {    
        if (StringUtil.isNull(src)) {    
            return src;    
        }    
        StringBuilder buf = new StringBuilder(src.length());    
        char[] ca = src.toCharArray();    
        for (int i = 0; i < src.length(); i++) {    
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) {
                buf.append((char) (ca[i] - CONVERT_STEP));    
            } 
            else if (ca[i] == SBC_SPACE) {
                buf.append(DBC_SPACE);    
            } else {
                buf.append(ca[i]);    
            }    
        }    
        return buf.toString();    
    }
    
    /**
     * 1.先把字符串转换成字节数组(注意字符集"gbk")
     * 2.根据要截取的长度,从index=len-1开始,倒序编列数组
     * 3.判断每个字节十进制的值
     * 4.如果<0,则计数器+1; 循环直到>0
     * 5.判断计数器为偶数,则直接截取(0,len)字符串
     * 6.若果计数器为奇数,则截取(0,len-1)字符串
     *
     * @return 截取后的字符串
     * @throws IOException
     */
    @SuppressWarnings("unused")
	private static void getStringByByte(String orignal, String newStr, StringBuffer retStr, int countIdx, int maxSize, String addSymbol) throws IOException {
    	int idx = 0;
    	if(isNull(newStr)) {
    		newStr = orignal;
    	}
    	
        byte[] b = newStr.getBytes("gbk");
        int count = 0;
        for (int i = maxSize - 1; i >= 0; i--) {
            if (b[i] < 0) {
                count++;
            }
            else {
            	break;
            }   
        }
        if (count % 2 == 0) {
        	idx = maxSize;
        }
        else {
        	idx = maxSize - 1;
        }
        countIdx += idx;
        retStr.append(new String(b, 0, idx, "gbk")).append(addSymbol);
        String temp = orignal.substring(countIdx - 1, orignal.length());
        if(temp.getBytes("gbk").length <= maxSize) {
        	retStr.append(temp);
        }
        else {
        	getStringByByte(orignal, temp, retStr, countIdx, maxSize, addSymbol);
        }
    }
    
    public static String splitStrAddBr(String orignal, int maxSize) {
    	StringBuffer retStr = new StringBuffer();
    	String strTemp = StringUtil.strConvertHalf2FullWidth(orignal);
    	if(strTemp.length() <= maxSize) {
         	return strTemp;
        }
    	
    	int retCnt = (int) (strTemp.length() / maxSize);
    	double retMod = strTemp.length() % maxSize;
    	for (int i = 0; i < retCnt; i++) {
    		if((i == retCnt - 1) && retMod == 0) {
    			retStr.append(strTemp.substring((i * maxSize), (i * maxSize + maxSize)));
    		}
    		else {
    			retStr.append(strTemp.substring((i * maxSize), (i * maxSize + maxSize))).append(Constants.HTML_BR);
    		}
		}
    	if(retMod != 0) {
    		retStr.append(strTemp.substring((retCnt * maxSize), strTemp.length()));
    	}
    	
    	return retStr.toString();
    }
}
