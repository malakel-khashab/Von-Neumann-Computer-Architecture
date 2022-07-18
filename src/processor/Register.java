package processor;

public class Register {
	private String name;
	private int value=0;
	public Register(String name) {
		this.name=name;
	}
	public Register(String name, int value) {
		this.name=name;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
