package com.yed.common.shiro;

import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.util.Arrays;

public class Base64Test {

    /**
     * Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key
     */
    public static void main(String[] args) {
        String keyStr = "长瑞华通";
        
        byte[] keys = keyStr.getBytes(Charset.forName("UTF-8"));
        
        System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 16)));
    }

}
