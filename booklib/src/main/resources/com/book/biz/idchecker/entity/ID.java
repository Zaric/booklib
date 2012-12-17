package com.book.biz.idchecker.entity;

/**
 * 身份证号码
 * 例如：320105198209275127 ->
 * addr   birth    seq  check
 * 320105 19820927 512  7
 */
public class ID {

    private String num;        //全长身份证号码
    private String addr;    //地址
    private String birth;    //生日
    private String seq;        //序号
    private String check;    //校验码

    /**
     * 构造方法 1
     *
     * @param num 格式如："320105198209275127"
     */
    public ID(String num) {
        this.num = num;
        this.addr = num.substring(0, 6);
        this.birth = num.substring(6, 14);
        this.seq = num.substring(14, 17);
        this.check = num.substring(17, 18);
    }

    /**
     * 构造方法 2
     *
     * @param addr  地址码， 格式如："320105"
     * @param birth 生日码，格式如："19820927"
     * @param seq   顺序码，格式如："512"
     * @param check 校验码，格式如："7"
     */
    public ID(String addr, String birth, String seq, String check) {
        this.addr = addr;
        this.birth = birth;
        this.seq = seq;
        this.check = check;
        this.num = addr + birth + seq + check;
    }

    /**
     * 分离身份证号码
     *
     * @return 字符数组
     */
    public char[] separate() {
        return this.num.toCharArray();
    }

    /**
     * 分离身份证号码
     *
     * @return 整型数组，最后一位若是'X'，则返回10
     */
    public int[] separate2int() {
        int length = 18;
        int[] ins = new int[length];
        int i = 0;
        for (; i < length - 1; i++) {
            ins[i] = Integer.valueOf(num.substring(i, i + 1));
        }
        String last = num.substring(i, i + 1);
        ins[i] = "X".equals(last) ? 10 : Integer.valueOf(last);
        return ins;
    }

    /**
     * 计算校验位
     *
     * @return
     */
    public String caculateCheckCode() {
        int total = 0;        //校验值和
        int length = 18;    //身份证长度
        int[] ins = new int[length];
        int[] checkCodes = Checkcode.checkCodes;
        int i = 0;
        try {
            for (; i < length - 1; i++) {
                ins[i] = Integer.valueOf(num.substring(i, i + 1));
                total += (ins[i] * checkCodes[i]);
            }
        } catch (NumberFormatException e) {
            return null;
        }

        int modResult = total % 11;
        return Checkcode.checkResult(modResult);

    }

    /**
     * 获取中文格式的出生年月日
     *
     * @return
     */
    public String getFormatBirth() {
        return birth.substring(0, 4) + "年" + birth.substring(4, 6) + "月" + birth.substring(6, 8) + "日";
    }

    public String getPureBirth() {
        return birth.substring(0, 8);
    }

    /**
     * 获取性别
     *
     * @return
     */
    public String getSex() {
        return seq.charAt(2) % 2 == 0 ? "女" : "男";
    }

    public int getIntSex() {
        return seq.charAt(2) % 2 == 0 ? 2 : 1;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


}
