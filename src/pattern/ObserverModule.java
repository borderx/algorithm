package pattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverModule {
	public static void main(String args[]) {
		TemperatureObserver temperatureObserver = new TemperatureObserver();
		BoildObserver boildObserver1 = new BoildObserver("�رյ�Դ...");
		BoildObserver boildObserver2 = new BoildObserver("������ʪ...");
		Water water = new Water(temperatureObserver);
		water.addObserver(boildObserver1);
		water.addObserver(boildObserver2);
		water.change(45);
		water.change(80);
		water.change(100);
	}
}

// �۲���
interface Observer {
	public void update(Observable observable);
}

// ���۲���
abstract class Observable {
	protected boolean isChanaged;
	protected List<Observer> observers = new ArrayList<Observer>();

	public Observable() {
		isChanaged = false;
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void clearObservers() {
		observers.clear();
	}

	public void notifyObservers() {
		if (isChanaged) {
			for (int i = 0; i < observers.size(); i++) {
				observers.get(i).update(this);
			}
			isChanaged = false;
		}
	}
}

// �ˣ��¶ȼ��
class TemperatureObserver implements Observer {
	@Override
	public void update(Observable observable) {
		Water water = (Water) observable;
		System.out.println("�¶ȣ�" + water.getTemperature() + "    ״̬��" + water.getStatus());
		System.out.println("TemperatureObserver observing...");
	}
}

class BoildObserver implements Observer {
	String doSomthing;

	BoildObserver(String doSomthing) {
		this.doSomthing = doSomthing;
	}

	@Override
	public void update(Observable observable) {
		Water water = (Water) observable;
		if (water.getTemperature() >= 100) {
			System.out.println("״̬��" + water.getStatus());
			System.out.println("BoildObserver:" + doSomthing);
		}

	}
}

// ˮ�����۲���
class Water extends Observable {
	private double temperature;
	private String status;

	public Water() {
		super();
		this.temperature = 0;
		this.status = "��ˮ";
	}

	public Water(Observer observer) {
		this();
		observers.add(observer);
	}

	public double getTemperature() {
		return temperature;
	}

	public String getStatus() {
		return status;
	}

	public void change(double temperature) {
		this.temperature = temperature;
		if (temperature < 40) {
			status = "��ˮ";
		} else if (temperature >= 40 && temperature < 60) {
			status = "��ˮ";
		} else if (temperature >= 60 && temperature < 100) {
			status = "��ˮ";
		} else {
			status = "��ˮ";
		}
		this.isChanaged = true;
		notifyObservers();
	}
}