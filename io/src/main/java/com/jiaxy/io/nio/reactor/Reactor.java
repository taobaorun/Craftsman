package com.jiaxy.io.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * Description: <br/>
 *
 * Single threaded version
 *
 * <p/>
 * <br/>
 *
 * @Date: 2017/07/17 11:55
 */
public class Reactor implements Runnable {

    private final Selector selector;

    private final ServerSocketChannel serverSocket;


    public Reactor(int port) throws IOException {
        SelectorProvider sp = SelectorProvider.provider();
        selector = sp.openSelector();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());

    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> it = set.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                set.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if (r != null) {
            r.run();
        }
    }


    class Acceptor implements Runnable {


        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(c, selector);
                }

            } catch (Exception e) {

            }

        }
    }

    final class Handler implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        ByteBuffer input = ByteBuffer.allocate(1000);

        ByteBuffer output = ByteBuffer.allocate(2000);

        static final int READING = 0, SENDING = 1;

        int state = READING;


        public Handler(SocketChannel socket, Selector selector) throws IOException {
            socket.configureBlocking(false);
            this.socket = socket;
            // Optionally try first read now
            sk = socket.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        public void run() {

            try {
                if (state == READING) read();
                else if (state == SENDING) send();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // Normally also do first write now
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            socket.write(output);
            if (outputIsComplete()) {
                sk.cancel();
            }
        }

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsComplete() {
            return true;
        }

        void process() { /* ... */ }
    }
}
