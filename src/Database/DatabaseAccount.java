package Database;

import com.mysql.jdbc.PreparedStatement;
import model.Account;
import java.sql.*;

public class DatabaseAccount {
	public static void insert(Account account) {
		try {
			/*
			 * TODO:userid锟斤拷锟斤拷锟捷匡拷锟斤拷锟斤拷锟斤拷一锟斤拷全锟街憋拷锟斤拷,
			 * money锟斤拷level锟斤拷值10锟斤拷1 锟斤拷锟斤拷锟斤拷使锟斤拷一锟斤拷锟教讹拷值锟斤拷锟斤拷沙锟斤拷锟斤拷锟斤拷锟�
			 */
			int userid = getUserid();
			java.sql.Connection connect = DatabaseCommon.connect();
			System.out.println("From insert Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			String username = account.getUsername();
			String password = account.getPassword();
			String email = account.getEmailaddress();

			String r1 = "insert into Account values(" + userid + ",'" + username + "' " + "," + "'" + password + "'"
					+ ",'" + email + "'" + ",1000,1);";
			stmt.executeUpdate("Update static set userNumber=userNumber+1,usercount=usercount+1;");
			System.out.println(r1);
			stmt.execute(r1);
			stmt.close();
			connect.close();

		} catch (Exception e) {
			System.out.print("From insert get data error!");
			e.printStackTrace();

		}
	}

	public static int getUserid() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			Statement st;
			st = connect.createStatement();
			ResultSet rs = st.executeQuery("select * from static");
			rs.next();
			int userid = rs.getInt("userNumber") + 1001;
			System.out.println(userid);
			st.close();
			connect.close();
			rs.close();
			return userid;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("get ID error!");
			e.printStackTrace();
			return -1;
		}
	}

	public static int getLevel(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			Statement st;
			st = connect.createStatement();
			ResultSet rs = st.executeQuery("select * from account where username='" + username + "';");
			rs.next();
			int level = rs.getInt("level");
			st.close();
			connect.close();
			rs.close();
			return level;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("get level error!");
			e.printStackTrace();
			return -1;
		}
	}

	// 设置会员等级
	public static void setLevel(String username, int level) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update Account set level=" + level + " where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("set level error!");
			e.printStackTrace();
		}
	}

	public static Account getAccount(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			System.out.println("Ready to get level!");
			Statement st;
			st = connect.createStatement();
			ResultSet rs = st.executeQuery("select * from account where username='" + username + "';");
			rs.next();
			Account a = new Account(rs.getInt("userid"), username, rs.getString("passwords"),
					rs.getString("emailaddress"), rs.getInt("money"), rs.getInt("level"));
			st.close();
			connect.close();
			rs.close();
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("get ID error!");
			e.printStackTrace();
			return new Account(username, null, null);
		}
	}

	// 获取所有account的username
	public static String[] getAllAccount() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from account;";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); //移到最后一行     
			int rowCount = rs.getRow(); //得到当前行号，也就是记录数     
			rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置   
			String[] a = new String[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getString("username");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get Taskids error!");
			e.printStackTrace();
			return null;
		}
	}

	public static Account search(Account account) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			System.out.println("From search Success connect Mysql server!");
			String username = "'" + account.getUsername() + "'";
			String password = "'" + account.getPassword() + "'";
			String email = "'" + account.getEmailaddress() + "'";
			String Squery = "select * from Account where username = " + username + " and " + "passwords = " + password
					+ ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(Squery);
			ResultSet rs = pstmt.executeQuery();
			int num = 0;
			while (rs.next()) {
				num++;
			}
			System.out.println(num);
			if (num == 0) {
				System.out.println("False");
				pstmt.close();
				connect.close();
				return null;
			}
			pstmt.close();
			connect.close();
			return new Account(username, password, email);

		} catch (Exception e) {
			System.out.print("From search get data error!");
			e.printStackTrace();
			return null;
		}
	}

	public static Account searchByName(Account account) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			System.out.println("From searchByName Success connect Mysql server!");
			String username = "'" + account.getUsername() + "'";
			String email = "'" + account.getEmailaddress() + "'";
			String Squery = "select * from Account where username = " + username + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(Squery);
			ResultSet rs = pstmt.executeQuery();
			int num = 0;
			while (rs.next()) {
				num++;
			}
			System.out.println(num);
			if (num == 0) {
				System.out.println("False");
				pstmt.close();
				connect.close();
				return null;
			}
			pstmt.close();
			connect.close();

			return new Account(username, "", email);

		} catch (Exception e) {
			System.out.print("From searchByName get data error!");
			e.printStackTrace();
			return null;

		}
	}

	// 根据传入的username和password,email改变
	public static void changeInfo(String username, String password, String email) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update Account set passwords='" + password + "',emailaddress='" + email + "' where username='"
					+ username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Change Info error!");
			e.printStackTrace();
		}
	}

	// 增加会费
	public static void addMoney(String username, int money) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update Account set money=money+" + money + " where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Change money error!");
			e.printStackTrace();
		}
	}

	// 扣款
	public static void costMoney(String username, int money) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update Account set money=money-" + money + " where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Cost money error!");
			e.printStackTrace();
		}
	}

	public static void deleteAccount(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "delete from account where username= '" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.executeUpdate("Update static set usercount=usercount-1;");
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Delete Account error!");
			e.printStackTrace();
		}
	}
}
