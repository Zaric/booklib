package com.book.biz.idchecker.entity;

/**
 * 校验码类
 */
public class Checkcode {

    //校验加权因子数组
    public static final int[] checkCodes = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    //校验码对应值:
    //1 0 X 9 8 7 6 5 4 3 2
    public static final String checkResult(int code) {
        String result = null;
        switch (code) {
            case 0:
                result = "1";
                break;
            case 1:
                result = "0";
                break;
            case 2:
                result = "X";
                break;
            case 3:
                result = "9";
                break;
            case 4:
                result = "8";
                break;
            case 5:
                result = "7";
                break;
            case 6:
                result = "6";
                break;
            case 7:
                result = "5";
                break;
            case 8:
                result = "4";
                break;
            case 9:
                result = "3";
                break;
            case 10:
                result = "2";
                break;
            default:
                break;
        }
        return result;
    }

}
