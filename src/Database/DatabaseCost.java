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

	// ��ȡid
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

	// ���ݴ����username���ض�Ӧ��costid����
	public static int[] getCostByUser(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select costid from cost where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); // �Ƶ����һ��
			int rowCount = rs.getRow(); // �õ���ǰ�кţ�Ҳ���Ǽ�¼��
			rs.beforeFirst(); // �����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��
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

	// ��ȡ�������Ѽ�¼
	public static int[] getAllCost() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from cost;";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); // �Ƶ����һ��
			int rowCount = rs.getRow(); // �õ���ǰ�кţ�Ҳ���Ǽ�¼��
			rs.beforeFirst(); // �����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��
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

	// ����mid ����һ��cost
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

	// ɾ��cost��¼
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
