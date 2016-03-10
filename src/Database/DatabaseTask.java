package Database;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.Task;

public class DatabaseTask {
	public static void addTask(Task task) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "insert into task values(" + task.getTid() + ",'" + task.getUsername() + "','"
					+ task.getTaskName() + "'," + task.getStatus() + "," + task.getThistype() + "," + task.getThattype()
					+ ",'" + task.getThatId() + "','" + task.getThatPass() + "','" + task.getThatContent() + "','"
					+ task.getThatRec() + "','" + task.getThatSub() + "','" + task.getThisstr1() + "','"
					+ task.getThisstr2() + "')";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Add task error!");
			e.printStackTrace();
		}
	}

	// ���ݴ����tid��status�ı�ĳһ�����״̬
	public static void changeTaskStatus(int tid, int status) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "update task set status=" + status + " where tid=" + tid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Change task status error!");
			e.printStackTrace();
		}
	}

	// ���ݴ����username���ض�Ӧ��tid����
	public static int[] getTaskOfUserid(String username) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select tid from task where username='" + username + "';";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); //移到最后一行     
			int rowCount = rs.getRow(); //得到当前行号，也就是记录数     
			rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置   
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("tid");
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
	public static int[] getAllTask() {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from task;";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last(); //移到最后一行     
			int rowCount = rs.getRow(); //得到当前行号，也就是记录数     
			rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置   
			int[] a = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				a[i] = rs.getInt("tid");
				i++;
			}
			pstmt.close();
			connect.close();
			return a;
		} catch (Exception e) {
			System.out.print("Get ALL Taskids error!");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Task getTask(int taskid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "select * from task where tid=" + taskid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Task task = new Task(rs.getInt("tid"), rs.getString("username"), rs.getString("taskname"), rs.getInt("status"),
					rs.getInt("thistype"), rs.getInt("thattype"), rs.getString("thatid"), rs.getString("thatpassword"),
					rs.getString("thatContent"), rs.getString("thatRec"), rs.getString("thatsub"),
					rs.getString("thisstr1"), rs.getString("thisstr2"));
			System.out.println(task.getInfo());
			pstmt.close();
			connect.close();
			return task;
		} catch (Exception e) {
			System.out.print("Get Task error!");
			e.printStackTrace();
			return new Task(0, "Null", "NUll");
		}
	}

	
	public static void deleteTask(int tid) {
		try {
			java.sql.Connection connect = DatabaseCommon.connect();
			String sql = "delete from task where tid=" + tid + ";";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) connect.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			connect.close();
		} catch (Exception e) {
			System.out.print("Delete Task error!");
			e.printStackTrace();
		}
	}
}