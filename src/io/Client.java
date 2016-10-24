package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
	
	private int port;
	
	
	
	public Client(int port) {
		super();
		this.port = port;
	}


	public static void main(String[] args) {
		int port = 9999;
		Client client = new Client(port);
		client.start();
	}
	
	
	public void start() {
        SocketChannel socketChannel;
        Selector selector;
        try {
        	socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(port));
            selector = Selector.open();
            SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(128);
            clientKey.attach(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        while (true) {
            try {
                int acceptableKeysCount = selector.select();
                if(acceptableKeysCount == 0) {
                	continue;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                	if (key.isConnectable()) {
                        System.out.println("connect successfully!");
//                        SocketChannel client = (SocketChannel) key.channel();
//                        while(! client.finishConnect() ) {
//                        	System.out.println("waiting!");
//                        }
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.clear();
                        output.put("Hello world!".getBytes());
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer tmp = (ByteBuffer) key.attachment();
                    	int count = client.read(tmp);
                    	if (count >= 0) {  
                    		tmp.flip();
                        	byte[] buf = new byte[count];
                        	tmp.get(buf);
                        	System.out.println("read : " + new String(buf)); 
                        	tmp.compact();
                        }  
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        client.write(output);
                        output.compact();
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
        
        
	}
}
