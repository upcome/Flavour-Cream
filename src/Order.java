import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	private ArrayList<Integer> l;

	private HashMap<Integer, Integer> h;

	Order() {
		l = new ArrayList<Integer>();
		h = new HashMap<Integer, Integer>();
	}

	public void addOrder(int id, int quantity) throws SQLException {
		l.add(id);
		h.put(id, quantity);

		/*
		 * String username = "sa"; String password = "123456abcd"; String url =
		 * "jdbc:sqlserver://localhost;databaseName=Milkshake"; Connection con =
		 * DriverManager.getConnection(url, username, password); int count=0;
		 * 
		 * Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		 * ResultSet.CONCUR_UPDATABLE); ResultSet resultset =
		 * stmt.executeQuery("select max(userid) as id from MilkshakeUser");
		 * resultset.absolute(1); int userid = resultset.getInt(1);
		 * PreparedStatement pstmt =
		 * con.prepareStatement("insert into dbo.MilkshakeOrder values(?)");
		 * pstmt.setInt(1, userid); pstmt.executeUpdate();
		 * 
		 * ResultSet
		 * rs=stmt.executeQuery("select max(orderid) as id from MilkshakeOrder"
		 * ); rs.absolute(1); int orderid= rs.getInt(1); PreparedStatement
		 * pstmtt =
		 * con.prepareStatement("insert into dbo.MilkshakeOrderItems values(?,?,?)"
		 * ); pstmtt.setInt(1, orderid); pstmtt.setInt(2, id); pstmtt.setInt(3,
		 * quantity); pstmtt.executeUpdate();
		 */

	}

	public void deleteOrder() {
		try {
			int index = l.size() - 1;
			h.remove(l.get(index));
			l.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("there is no order to delete");
		}

	}

	public void display(Menu m) {
		for (int i : l) {
			System.out.println("item id" + i + "quantity" + h.get(i));
		}
	}

	public ArrayList<Integer> getL() {
		return l;
	}

	public void setL(ArrayList<Integer> l) {
		this.l = l;
	}

	public HashMap<Integer, Integer> getH() {
		return h;
	}

	public void setH(HashMap<Integer, Integer> h) {
		this.h = h;
	}
}