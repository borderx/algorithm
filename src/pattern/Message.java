package pattern;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息
 * 
 * @author
 */
class Message {
	// 消息类型
	public static final int SHUTDOWN_MSG = -1;
	public static final int KEY_MSG = 1;
	public static final int MOUSE_MSG = 2;
	public static final int SYS_MSG = 3;

	private int type; // 类型
	private String source; // 来源
	private String info; // 信息

	public Message(int type, String source, String info) {
		super();
		this.type = type;
		this.source = source;
		this.info = info;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Message [type=" + type + ", source=" + source + ", info="
				+ info + "]";
	}
	
	
}

class MessageClient implements Runnable {

	private MessageServer server;
	
	public MessageClient(MessageServer server) {
		this.server = server;
	}
	
	public MessageServer getServer() {
		return server;
	}

	public void setServer(MessageServer server) {
		this.server = server;
	}

	public void sendMessage(Message message){
		System.out.println("发送消息：" + message);
		server.getMessage(message);
	}
	
	@Override
	public void run() {
		System.out.println("消息客户端启动。");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String msgInfo = scanner.nextLine();
			if("-1".equals(msgInfo.trim())){
				Message msg = new Message(Message.SHUTDOWN_MSG, null, null);
				sendMessage(msg); // 发送关闭消息消息
				break;
			}
			String[] msgArray = msgInfo.split(",");
			Message msg = new Message(Integer.valueOf(msgArray[0]), msgArray[1], msgArray[2]);
			sendMessage(msg); // 发送消息
		}
		scanner.close();
		System.out.println("消息客户端关闭。");
	}
	
}

class MessageServer implements Runnable {

	private ArrayBlockingQueue<Message> msgQueue;
	private MessageProcess messageProcess;

	public MessageServer(ArrayBlockingQueue<Message> msgQueue, MessageProcess messageProcess) {
		this.msgQueue = msgQueue;
		this.messageProcess = messageProcess;
	}

	@Override
	public void run() {
		System.out.println("消息服务器启动。");
		Message msg = null;
		while (true) {
			try {
				msg = msgQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			if(msg.getType() == Message.SHUTDOWN_MSG) {
				break;
			}
			messageProcess.doMessage(msg);
		}
		System.out.println("消息服务器关闭。");
	}
	
	public void getMessage(Message message){
		System.out.println("接收到消息：" + message);
		msgQueue.add(message);
	}
	
}

interface MessageProcess {
	public void doMessage(Message msg);
}

/**
 * 窗口模拟类
 */
class WindowSimulator implements MessageProcess {

	@Override
	/**
	 * 消息处理
	 */
	public void doMessage(Message msg) {
		switch (msg.getType()) {
			case Message.KEY_MSG:
				onKeyDown(msg);
				break;
			case Message.MOUSE_MSG:
				onMouseDown(msg);
				break;
			default:
				onSysEvent(msg);
		}
	}

	// 键盘事件
	public static void onKeyDown(Message msg) {
		System.out.println("键盘事件：");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}

	// 鼠标事件
	public static void onMouseDown(Message msg) {
		System.out.println("鼠标事件：");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}

	// 操作系统产生的消息
	public static void onSysEvent(Message msg) {
		System.out.println("系统事件：");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}
}

