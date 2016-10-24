package pattern;

import java.util.concurrent.ArrayBlockingQueue;

public class MessageTest {
	// ��Ϣ����
	private static ArrayBlockingQueue<Message> messageQueue = new ArrayBlockingQueue<Message>(100);

	public static void main(String[] args) {
		MessageServer server = new MessageServer(messageQueue, new WindowSimulator());
		new Thread(server).start();

		MessageClient client = new MessageClient(server);
		new Thread(client).start();
	}
}
