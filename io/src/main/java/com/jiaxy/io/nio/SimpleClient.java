package com.jiaxy.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by wutao on 15-1-25.
 */
public class SimpleClient {

    public static void main(String[] args) throws IOException {

        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 1028));
        sc.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(2);
        while ( true ){
            StringBuilder sb = new StringBuilder();
            int read = sc.read(buffer);
            while (  read > 0 ){
                /*System.out.println("position "+buffer.position());
                System.out.println("limit " + buffer.limit());*/
                buffer.flip();
                sb.append(new String(buffer.array()));
                buffer.clear();
                read = sc.read(buffer);
            }
            if ( sb.length() > 0 ){
                System.out.println(sb);
            }
            if ( read == -1 ){
                sc.close();
                break;
            }
//            System.out.println(sc.isConnected());
//            sc.write(ByteBuffer.wrap("".getBytes()));
        }
    }
}
