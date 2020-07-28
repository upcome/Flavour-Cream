import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bill {

	private int Billid;

	Bill(int id) {
		this.Billid = id;
	}

	public int getBillid() {
		return Billid;
	}

	public void setBillid(int billid) {
		Billid = billid;
	}

	public double priceCalculation(Order o, Menu m)
			throws ClassNotFoundException, SQLException {

		String username = "sa";
		String password = "123456abcd";
		String url = "jdbc:sqlserver://localhost;databaseName=Milkshake";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		// to check connection
		/*
		 * ResultSet rs=stmt.executeQuery("select * from dbo.Milkshake");
		 * while(rs.next()) { System.out.println(rs.getInt(1));
		 * System.out.println(rs.getString(2));
		 * System.out.println(rs.getDouble(3)); } rs.close();
		 */
		ResultSet rstuid = stmt
				.executeQuery("select MAX(userid) as id from MilkshakeOrder");
		int uid = 0;
		while (rstuid.next()) {
			uid = rstuid.getInt(1);
		}
		//System.out.println(uid);
		String updateString = "select orderid as id from MilkshakeOrder where userid='"
				+ uid + "'";
		ResultSet rstoid = stmt.executeQuery(updateString);
		int oid = 0;

		// rst.absolute(1);
		// int uid= 23;
		PreparedStatement pstmtitem = con
				.prepareStatement("insert into dbo.MilkshakeOrderItems values(?,?,?)");

		double sum = 0;
		int count = 0;

		for (int i : o.getL()) {
			count++;
			int quantity = o.getH().get(i);
			rstoid.absolute(count);
			pstmtitem.setInt(1, rstoid.getInt(1));
			pstmtitem.setInt(2, i);
			pstmtitem.setInt(3, quantity);
			pstmtitem.executeUpdate();
			double price = 0.0;
			for (Milkshake ms : m.getMenu()) {
				if (ms.getMilkshakeid() == i) {
					price = ms.getPrice();
				}

			}

			sum += quantity * price;

		}
		PreparedStatement pstmtbill = con
				.prepareStatement("insert into dbo.MilkshakeBill values(?,?)");
		pstmtbill.setInt(1, uid);
		pstmtbill.setDouble(2, sum);
		pstmtbill.executeUpdate();
		pstmtbill.close();
		pstmtitem.close();
		con.close();
		return sum;

	}
}
