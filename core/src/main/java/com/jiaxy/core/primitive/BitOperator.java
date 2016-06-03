package com.jiaxy.core.primitive;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @author <a href=mailto:taobaorun@gmail.com>wutao</a>
 *
 * @since 2016/06/02 16:29
 */
public class BitOperator {

    public static void main(String[] args){

        System.out.println(0xff);
        System.out.println((byte)-1 & 0xff);
        System.out.println(((byte)-1 & 0xff) << 16 );
        System.out.println(((byte)-1 & 0xff) << 16 |((byte)-1 & 0xff) << 8 );
        System.out.println(((byte)-1 & 0xff) << 16 |((byte)-1 & 0xff) << 8 |((byte)-1 & 0xff) );
        System.out.println(0xFFFFFFFFL);
        System.out.println(0xFFFFFFFF);//-1.-1的补码
        System.out.println(0x0F);
        System.out.println(0xFFFFFFFF);
    }
}
