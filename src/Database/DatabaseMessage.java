package Database;

import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.jdbc.PreparedStatement;
import model.Message;

public class DatabaseMessage {
	public static void addMessage(Message message) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "insert into message values(" + message.getMid() + ",'" + message.getSourceid() + "','"
					+ message.getReceiveid() + "','" + message.getContent() + "'," + message.isAdminMessage() + ",'"
					+ message.getTime().toString() + "'," + message.getAttribute() + ");";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Add message error!");
			e.printStackTrace();
		}
	}

	// ��ȡid
	public static int getmid() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			System.out.println("Ready to get mid!");
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

	// ���ݴ����mid��attribute�ı�ĳһmessage��״̬
	public static void changeAttribute(int mid, int attribute) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update message set attribute=" + attribute + " where mid=" + mid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Change attribute error!");
			e.printStackTrace();
		}
	}

	// ���ݴ����username���ض�Ӧ��mid����
	public static int[] getTReceiveMessage(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select mid from message where receiveid='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); //�Ƶ����һ��     
			int rowCount = rs.getRow(); //�õ���ǰ�кţ�Ҳ���Ǽ�¼��     
			rs.beforeFirst(); //�����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��   
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("mid");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get Receive IDs error!");
			e.printStackTrace();
			return null;
		}
	}

	public static int[] getPublicMessage() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select mid from message where attribute = 10;";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); //�Ƶ����һ��     
			int rowCount = rs.getRow(); //�õ���ǰ�кţ�Ҳ���Ǽ�¼��     
			rs.beforeFirst(); //�����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��   
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("mid");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get Public Messages error!");
			e.printStackTrace();
			return null;
		}
	}

	// ����mid ����һ��message
	public static Message getMessage(int mid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from message where mid=" + mid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Message message = new Message(mid, rs.getString("sourceid"), rs.getString("receiveid"),
					rs.getString("content"), rs.getBoolean("isAD"), rs.getString("time"), rs.getInt("attribute"));
			pstmt.close();
			connect.close();
			return message;
		} catch (Exception e) {
			System.out.print("Get Message error!");
			e.printStackTrace();
			return new Message(0, null, null, null, false, null, 0);
		}
	}

	// ����midɾ��Message
	public static void deleteMessage(int mid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "delete from message where mid=" + mid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Delete Message error!");
			e.printStackTrace();
		}
	}
}
