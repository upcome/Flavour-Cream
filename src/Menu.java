import java.util.ArrayList;

public class Menu {
	ArrayList<Milkshake> menu;

	Menu() {
		menu = new ArrayList<Milkshake>();
	}

	public void addInMenu(Milkshake obj) {
		menu.add(obj);
	}

	public ArrayList<Milkshake> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Milkshake> menu) {
		this.menu = menu;
	}

	public void deleteInMenu(int id) {
		menu.remove(id);

	}

	public double getInMenu(int i) {

		Milkshake amilkshake = menu.get(i);

		return amilkshake.getPrice();

	}

	public void display() {
		for (Milkshake o : menu) {
			System.out.println(o.getMilkshakeid() + " " + o.getName() + " "
					+ o.getPrice());
		}

	}

}
