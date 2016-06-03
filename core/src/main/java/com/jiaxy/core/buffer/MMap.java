package com.jiaxy.core.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @author <a href=mailto:taobaorun@gmail.com>wutao</a>
 *
 * @since 2016/06/03 11:14
 */
public class MMap {

    public static void main(String[] args) throws IOException, InterruptedException {
//        int length = 0x8fffffff;//128M
        int length = 2;

        MappedByteBuffer mmap = new RandomAccessFile("test.txt","rw")
                .getChannel().map(FileChannel.MapMode.READ_WRITE,0,length);
        for (int i = 0 ;i < length;i++){
            mmap.put((byte)'x');
        }
        TimeUnit.SECONDS.sleep(30);
        mmap.put(0,(byte)'y');
        System.out.println(mmap.get(0));

    }
}
