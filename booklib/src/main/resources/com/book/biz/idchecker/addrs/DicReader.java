package com.book.biz.idchecker.addrs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 地址码读取类
 */
public class DicReader {

    /**
     * 读取地址码
     *
     * @param addrNum
     * @return 若存在，则返回该地址码对应的地址名称，若不存在，返回null
     */
    public static String readAddress(String addrNum) {
        char first = addrNum.charAt(0);
        if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6') {
            String filePath = first + ".dic";
            String addr = readAddress(filePath, "UTF-8", addrNum);
            return addr;
        }
        return null;
    }

    /**
     * 读取地址码是否存在
     *
     * @param filePath 文件路径
     * @param charset  文件编码
     * @param addrNum  地址码，6位数字
     * @return 存在返回地址名称，否则返回null
     */
    public static String readAddress(String filePath, String charset, String addrNum) {
        String addr = null;
        try {
            InputStream is = DicReader.class.getResourceAsStream(filePath);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(is, charset));

            String line;
            while ((line = buffReader.readLine()) != null) {
                if (addrNum.equals(line.substring(0, 6))) {
                    addr = line.substring(7, line.length());
                    break;
                }
            }
            buffReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("找到不地址码文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("读取地址码文件失败");
            e.printStackTrace();
        }
        return addr;
    }

}
