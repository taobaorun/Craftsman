package com.jiaxy.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @since 2016/03/02 18:23
 */
public class FileLock {


    public static void main(String[] args) throws IOException {
       /* File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File file1 = new File(tmpDir.getPath()+"/file1");
        file1.createNewFile();*/

        multiThreadWriteFile(50);


    }

    private static void multiThreadWriteFile(int num){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(read());
                        write(String.valueOf(Thread.currentThread().getId()) + "test" + 1 );
                        System.out.println("-----write over------");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
         new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(read());
                        write(String.valueOf(Thread.currentThread().getId()) + "test" + 2);
                        System.out.println("-----write over------");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(read());
                        write(String.valueOf(Thread.currentThread().getId()) + "test" + 3);
                        System.out.println("-----write over------");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

    }

    private static void write(String str) throws IOException {
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File file1 = new File(tmpDir.getPath()+"/file1");
        FileWriter fw = new FileWriter(file1,false);
        fw.write(str);
        fw.close();
    }

    private static String read() throws IOException {
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File file1 = new File(tmpDir.getPath()+"/file1");
        RandomAccessFile fis = new RandomAccessFile(file1,"rw");
        byte[] buf = new byte[100];
        StringBuilder sb = new StringBuilder();
        while ( fis.read(buf) != -1 ){
           sb.append(new String(buf));
        }
        fis.close();
        return sb.toString();
    }
}
