package com.book.biz.idchecker.check;

import com.book.biz.idchecker.addrs.DicReader;
import com.book.biz.idchecker.entity.ID;

import java.util.HashSet;
import java.util.Set;

/**
 * 身份证检验类
 */
public class Checker {

    private ID id;        //身份证号码
    private String errorMsg = "";    //非法反馈信息
    private Set<String> errorMsgs = new HashSet<String>();    //非法信息集合
    private String addr = "";        //地址信息

    /**
     * 构造方法
     *
     * @param num 身份证字符串,18位
     */
    public Checker(String num) {
        if (num.length() >= 18) {
            this.id = new ID(num);
        } else {
            this.id = new ID("0000000000000000000");
        }

    }

    /**
     * 总的身份证验证
     * 验证顺序：长度 -> 生日 -> 最后一位校验码 -> 地址
     *
     * @return 若遇到有一项目不合法即返回false，所有验证通过才返回true
     */
    public boolean check() {
        boolean right = false;
        right = checkLength();        //验证长度
        if (right) {
            right = checkBirth();        //验证生日
            if (right) {
                right = checkCheckCode();        //验证最后一校验码
                if (right) {
                    right = checkAddr();        //验证地址
                }
            }
        }
        return right;
    }

    /**
     * 总的身份证验证
     * 验证项目包括长度、地址、生日、最后一位校验码
     *
     * @return 身份证合法则返回true，否则false
     */
    public boolean checkAll() {
        if (!checkLength()) {        //验证长度
            errorMsgs.add(errorMsg);
            return false;            //长度都不对，其他的就不用验证了
        }
        if (!checkAddr()) {
            errorMsgs.add(errorMsg);    //验证地址
        }
        if (!checkBirth()) {
            errorMsgs.add(errorMsg);    //验证生日
        }
        if (!checkCheckCode()) {            //验证最后一位校验码
            errorMsgs.add(errorMsg);
        }
        return errorMsgs.size() == 0 ? true : false;
    }

    /**
     * 检查身份证长度是否正确
     *
     * @return 长度为18则返回true，否则返回false
     */
    public boolean checkLength() {
        int length = id.getNum().length();
        if (length == 18) {
            return true;
        }
        errorMsg = "身份证长度不正确";
        return false;
    }

    /**
     * 验证身份证出生年月日是否合法
     *
     * @return 合法返回true, 否则返回false
     */
    public boolean checkBirth() {
        String birth = id.getBirth();
        //System.out.println(birth);
        int year, month, day;        //年月日
        try {
            year = Integer.valueOf(birth.substring(0, 4));
            month = Integer.valueOf(birth.substring(4, 6));
            day = Integer.valueOf(birth.substring(6, 8));
        } catch (NumberFormatException e) {
            errorMsg = "身份证生日码不正确！";
            return false;
        }
        if ((year >= 1900 && year <= 2010) && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
            return true;
        }
        errorMsg = "身份证生日码不正确！";
        return false;
    }

    /**
     * 验证地址码是否存在
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean checkAddr() {
        String addrCode = id.getAddr();
        //System.out.println("addrcode = " + addrCode);
        addr = DicReader.readAddress(addrCode);
        if (addr != null) {
            return true;
        }
        errorMsg = "身份证地址码不正确！";
        return false;
    }

    /**
     * 验证校验码是否正确
     *
     * @return 正确返回true，否则返回false
     */
    public boolean checkCheckCode() {
        String chCode = id.caculateCheckCode();        //计算正确的末位校验码
        if (id.getCheck().equalsIgnoreCase(chCode)) {
            return true;
        }
        errorMsg = "身份证校验码不正确, 正确的校验码是 " + chCode;
        return false;
    }


    /**
     * 获得出生年月日
     *
     * @return
     */
    public String getBirth() {
        return id.getFormatBirth();
    }

    public String getPureBirth() {
        return id.getPureBirth();
    }

    /**
     * 获得地址
     *
     * @return
     */
    public String getAddr() {
        if ("".equals(addr)) {
            checkAddr();
        }
        return addr;
    }

    /**
     * 获取性别
     *
     * @return
     */
    public String getSex() {
        return id.getSex();
    }

    public int getIntSex() {
        return id.getIntSex();
    }

    /**
     * 取得错误信息
     *
     * @return
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 取得错误信息集合
     *
     * @return
     */
    public Set<String> getErrorMsgs() {
        return errorMsgs;
    }

    /**
     * 取得身份证对象
     *
     * @return
     */
    public ID getId() {
        return id;
    }


}
