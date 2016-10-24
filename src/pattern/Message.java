package pattern;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ��Ϣ
 * 
 * @author
 */
class Message {
	// ��Ϣ����
	public static final int SHUTDOWN_MSG = -1;
	public static final int KEY_MSG = 1;
	public static final int MOUSE_MSG = 2;
	public static final int SYS_MSG = 3;

	private int type; // ����
	private String source; // ��Դ
	private String info; // ��Ϣ

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
		System.out.println("������Ϣ��" + message);
		server.getMessage(message);
	}
	
	@Override
	public void run() {
		System.out.println("��Ϣ�ͻ���������");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String msgInfo = scanner.nextLine();
			if("-1".equals(msgInfo.trim())){
				Message msg = new Message(Message.SHUTDOWN_MSG, null, null);
				sendMessage(msg); // ���͹ر���Ϣ��Ϣ
				break;
			}
			String[] msgArray = msgInfo.split(",");
			Message msg = new Message(Integer.valueOf(msgArray[0]), msgArray[1], msgArray[2]);
			sendMessage(msg); // ������Ϣ
		}
		scanner.close();
		System.out.println("��Ϣ�ͻ��˹رա�");
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
		System.out.println("��Ϣ������������");
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
		System.out.println("��Ϣ�������رա�");
	}
	
	public void getMessage(Message message){
		System.out.println("���յ���Ϣ��" + message);
		msgQueue.add(message);
	}
	
}

interface MessageProcess {
	public void doMessage(Message msg);
}

/**
 * ����ģ����
 */
class WindowSimulator implements MessageProcess {

	@Override
	/**
	 * ��Ϣ����
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

	// �����¼�
	public static void onKeyDown(Message msg) {
		System.out.println("�����¼���");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}

	// ����¼�
	public static void onMouseDown(Message msg) {
		System.out.println("����¼���");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}

	// ����ϵͳ��������Ϣ
	public static void onSysEvent(Message msg) {
		System.out.println("ϵͳ�¼���");
		System.out.println("type:" + msg.getType());
		System.out.println("info:" + msg.getInfo());
	}
}

