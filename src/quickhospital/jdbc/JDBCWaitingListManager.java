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
		String sql= "SELECT * FROM WaitingLists";
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
				w=new WaitingList(date, hId,spId);
				if(waitinglists.size()==0) {//añadimos primera linea
					w.addPatient(p);
					w.addTime(time);
					waitinglists.add(w);
				}else {
					boolean in= false;
					for(int i=0;i<waitinglists.size();i++) {//añadimos paciente y time a una waitinglist y existente
						if(w.getDate().equals(waitinglists.get(i).getDate()) && w.getHosp_Id().equals(waitinglists.get(i).getHosp_Id()) && w.getSp_Id().equals(waitinglists.get(i).getSp_Id())) {
							waitinglists.get(i).addPatient(p);
							waitinglists.get(i).addTime(time);
							in=true;
						}
					}
					if(in==false) {//si no existia esa waiting list añadimos nueva waitinglist
						w.addPatient(p);
						w.addTime(time);
						waitinglists.add(w);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return waitinglists;
		
	}
	
	//crear funcion que me devuelva la waiting list de una especialidad, hospital y dia / sacarlo de java
	
	
	public void addToWaitingList(LocalDate date,LocalTime time,Integer hospId, Integer spId, Integer patId ) {//meter date y time
		String sql= "INSERT INTO WaitingList (Date, Time, Patient_Id, Hospital_Id, Speciality_Id) VALUES ('" + patId + "', '"+ hospId	+ "', '" + spId	+ "'); ";

		try {
			Statement stmt= manager.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
