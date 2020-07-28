import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class UserInterface {
	public static void view(Menu m) throws SQLException {
		m.display();

	}

	public static void addorder(Order o) throws SQLException {
		Scanner s = new Scanner(System.in);
		System.out.print("enter item id that you want");
		int id = s.nextInt();
		System.out.println("enter how many you want");
		int quantity = s.nextInt();
		o.addOrder(id, quantity);

	}

	public static void deleteorder(Order o) {
		o.deleteOrder();

	}

	public static void billing(int count, Order o, Menu menuboard)
			throws ClassNotFoundException, SQLException {
		Bill b = new Bill(count);
		System.out.println(b.priceCalculation(o, menuboard));
		o.getL().clear();
		o.getH().clear();
	}

	public static void main(String args[]) throws SQLException,
			ClassNotFoundException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		String username = "sa";
		String password = "123456abcd";
		String url = "jdbc:sqlserver://localhost;databaseName=Milkshake";

		Connection con;

		con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();

		Menu menuboard = new Menu();
		menuboard.addInMenu(new Milkshake("vanilla", 30));
		menuboard.addInMenu(new Milkshake("strawberry", 35));
		menuboard.addInMenu(new Milkshake("oreo", 50));
		menuboard.addInMenu(new Milkshake("butterscotch", 40));
		menuboard.addInMenu(new Milkshake("pista", 45));

		Scanner s = new Scanner(System.in);
		Order o = new Order();
		boolean flag = true;
		int count = 0;
		int userid;

		stmt.executeUpdate("insert into MilkshakeUser values(null,null,null)");

		while (flag) {
			System.out
					.println("1 for view 2 for addorder 3 for deleteorder 4 for bill 5 for exit");
			int option = s.nextInt();
			switch (option) {
			case 1:

				view(menuboard);
				break;

			case 2:
				addorder(o);
				ResultSet rst = stmt
						.executeQuery("select MAX(userid)as id from MilkshakeUser");
				int uid = 0;
				while (rst.next()) {
					uid = rst.getInt(1);
				}
				// System.out.println(uid);
				// rst.absolute(1);

				PreparedStatement pstmt = con
						.prepareStatement("insert into dbo.MilkshakeOrder values(?)");
				pstmt.setInt(1, uid);
				pstmt.executeUpdate();
				pstmt.close();
				break;

			case 3:
				deleteorder(o);
				break;

			case 4:
				billing(count, o, menuboard);
				break;

			case 5:
				System.out.print("thank you for your visit");
				flag = false;
				break;

			default:
				System.out.println("add a valid option");

			}

		}

		stmt.close();
		con.close();

	}
}