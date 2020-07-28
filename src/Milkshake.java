public class Milkshake {
	private String name;
	private double price;
	private int milkshakeid;
	private static int j = 0;

	Milkshake(String s, double no) {
		this.name = s;
		this.price = no;
		this.milkshakeid = ++j;

	}

	public int getMilkshakeid() {
		return milkshakeid;
	}

	public void setMilkshakeid(int milkshakeid) {
		this.milkshakeid = milkshakeid;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
