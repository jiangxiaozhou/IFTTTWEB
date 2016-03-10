package Database;

import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import model.Cost;

public class DatabaseCost {
	public static void addCost(Cost cost) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "insert into cost values(" + cost.getCostid() + "," + cost.getMoney() + ",'"
					+ cost.getUsername() + "','" + cost.getTime() + "');";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Add cost error!");
			e.printStackTrace();
		}
	}

	// 获取id
	public static int getCostid() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			Statement st;
			st = connect.createStatement();
			ResultSet rs = st.executeQuery("select * from messagesta");
			rs.next();
			int mid = rs.getInt("mnumber");
			st.executeUpdate("Update messagesta set mnumber=mnumber+1,mcount=mcount+1;");
			System.out.println(mid);
			st.close();
			connect.close();
			rs.close();
			return mid;
		} catch (Exception e) {
			System.out.print("get ID error!");
			e.printStackTrace();
			return -1;
		}
	}

	// 根据传入的username返回对应的costid数组
	public static int[] getCostByUser(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select costid from cost where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); // 移到最后一行
			int rowCount = rs.getRow(); // 得到当前行号，也就是记录数
			rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("costid");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get cost ids error!");
			e.printStackTrace();
			return null;
		}
	}

	// 获取所有消费记录
	public static int[] getAllCost() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from cost;";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); // 移到最后一行
			int rowCount = rs.getRow(); // 得到当前行号，也就是记录数
			rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("costid");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get ALL Costids error!");
			e.printStackTrace();
			return null;
		}
	}

	// 根据mid 返回一个cost
	public static Cost getCost(int costid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from cost where costid=" + costid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Cost cost = new Cost(costid, rs.getInt("money"), rs.getString("username"), rs.getString("time"));
			pstmt.close();
			connect.close();
			return cost;
		} catch (Exception e) {
			System.out.print("Get Cost error!");
			e.printStackTrace();
			return new Cost(costid, 0, null);
		}
	}

	// 删除cost记录
	public static void deleteCost(int costid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "delete from cost where costid=" + costid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Delete cost error!");
			e.printStackTrace();
		}
	}
}
