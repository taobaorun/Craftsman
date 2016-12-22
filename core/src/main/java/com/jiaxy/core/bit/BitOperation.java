package com.jiaxy.core.bit;

/**
 * Title: <br>
 * <p/>
 * Description: <br>
 * <p/>
 *
 */
public class BitOperation {


    public static void main(String[] args){
        System.out.println(-(1 << 5));
        System.out.println( (byte)(0xa0 | 1));
        BitOperation bo = new BitOperation();
        System.out.println(System.identityHashCode(bo));
        System.out.println(System.identityHashCode(bo) % 100);
        int hash = System.identityHashCode(bo) % 100 & 100;
        System.out.println(hash);

        System.out.println("-----------------");
        String str = byte2hex("ABC".getBytes());
        System.out.println(str);
        for (byte b:hex2byte(str.getBytes())){
            System.out.println(b);
        }
        System.out.println("--------------");
        for (byte b:hexStringToByteArray(str)){
            System.out.println(b);
        }

        System.out.println(0x1 << 10 -1 );
    }

    /**
     * 十六转byte
     *
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException();
        //in Hexadecimal 1 letter is 4 bits,2 letters for each byte(8 bits)
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

     /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}
