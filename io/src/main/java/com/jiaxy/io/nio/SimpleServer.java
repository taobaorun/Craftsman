package com.jiaxy.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wutao on 15-1-25.
 */
public class SimpleServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private ServerSocket ss;

    public void config(int port){
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            ss = serverSocketChannel.socket();
            ss.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
            while ( true ){

                try {
                    selector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Set keys = selector.selectedKeys();
                Iterator iterator = keys.iterator();
                while ( iterator.hasNext()){
                    SelectionKey key = (SelectionKey) iterator.next();
                    try {

                        iterator.remove();
                        if (key.isAcceptable()){
                                System.out.println("accept");
                                ByteBuffer buffer = ByteBuffer.allocate(200);
                                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                SocketChannel clientChannel = server.accept();
                                clientChannel.configureBlocking(false);
                                clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                                System.out.println("accepted from " + clientChannel);
                                buffer.put(("server "+server+" ack" + System.currentTimeMillis()).getBytes());
                                buffer.flip();
                                clientChannel.write(buffer);
                            } else if ( key.isWritable() || key.isReadable()){
                                System.out.println(" readyOps "+key.readyOps());
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer clientBuff = (ByteBuffer) key.attachment();
                                ByteBuffer buffer = ByteBuffer.allocate(70);
                                Thread.sleep(1000);
                                buffer.put(("server ack " + System.currentTimeMillis()).getBytes());
                                buffer.flip();
                                clientChannel.write(buffer);
                                if ( clientBuff != null)
                                    System.out.println(clientBuff.getInt());
                            }

                        } catch (IOException e) {
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            try {
                                System.out.println("close client");
                                clientChannel.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } catch (InterruptedException e) {
                        }
                }
        }
    }
    public static void main(String[] args){
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.config(1028);
        simpleServer.start();
        while ( true ){
            synchronized (SimpleServer.class){
                try {
                    SimpleServer.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }

    }

}
