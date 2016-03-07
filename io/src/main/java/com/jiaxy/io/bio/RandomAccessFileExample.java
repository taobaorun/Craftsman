package com.jiaxy.io.bio;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @since 2015/07/08 14:36
 */
public class RandomAccessFileExample {

    public static void main(String[] args){
        try {
            String filePath = "/export/Logs/source.txt";
            long length = writeData(filePath, "Data", 0);
            System.out.println(new String(readCharsFromFile(filePath,0, (int) length)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static long writeData(String filePath, String data, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        file.write(data.getBytes());
        long mark = file.getFilePointer();
        System.out.println(mark);
        file.writeInt(1);
        long mark2 = file.getFilePointer();
        file.seek(mark);
        file.writeInt(2);
        long mark3 = file.getFilePointer();
        long length = file.length();
        System.out.println(mark);
        System.out.println(mark2);
        System.out.println(mark3);
        System.out.println(length);
        file.seek(mark2-4);
        System.out.println(file.readInt());
        file.seek(mark3-4);
        System.out.println(file.readInt());
        file.close();
        return length;
    }

    private static byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }
}
