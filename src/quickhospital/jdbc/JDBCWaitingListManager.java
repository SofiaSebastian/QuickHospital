package quickhospital.jdbc;

import java.sql.*;
import java.time.*;
import quickhospital.pojos.*;
import java.util.*;
import quickhospital.ui.*;



public class JDBCWaitingListManager {
private JDBCManager manager;
	
	
	public JDBCWaitingListManager(JDBCManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<WaitingList> readWaitingLists(){
		String sql= "SELECT * FROM WaitingLists;";
		ArrayList<WaitingList> waitinglists= new ArrayList<>();
		WaitingList w;
		try {
			Statement stmt=manager.getConnection().createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				String d= rs.getString("Date");
				LocalDate date= Main.stringToDate(d);
				String t = rs.getString("Time");
				LocalTime time= Main.stringToTime(t);
				int patId= rs.getInt("Patient_Id");
				Patient p= Main.idToPatient(patId);
				int spId = rs.getInt("Speciality_Id");
				int hId = rs.getInt("Hospital_Id");
				w=new WaitingList(date,time, p, hId,spId);
				waitinglists.add(w);
				}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return waitinglists;
		
	}
	
	
	
	public void addWaitingList(WaitingList w) {
		
		String sql= "INSERT INTO WaitingLists (Date, Time, Patient_Id, Hospital_Id, Speciality_Id) VALUES (?,?,?,?,?); ";

		try {
			PreparedStatement p= manager.getConnection().prepareStatement(sql);
			String time= w.getTime().toString();
			p.setString(2,time);
			String date= w.getDate().toString();
			p.setString(1, date);
			p.setInt(3, w.getPatients().getId());
			p.setInt(4, w.getHosp_Id());
			p.setInt(5, w.getSp_Id());
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
