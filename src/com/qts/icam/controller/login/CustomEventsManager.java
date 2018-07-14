package com.qts.icam.controller.login;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;




public class CustomEventsManager extends DHXEventsManager{
	
	private static String usrId = null;
	private static String urlAsParameter = null;
	private static String userName = null;
	private static String passwordAsParameter = null;
	private static String driverClassName = null;
	
	
	private static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;
        try {
            Class.forName(driverClassName);
            String url = urlAsParameter;
            String user = userName;
            String password = passwordAsParameter;
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e1) {
                      e1.printStackTrace();
        } catch (SQLException e1) {
                      e1.printStackTrace();
        }
        return conn;
	}
	
	public CustomEventsManager(HttpServletRequest request, String usrId, String urlAsParameter, String userName, String passwordAsParameter ,String driverClassName) {
		super(request);
		CustomEventsManager.usrId = usrId;
		CustomEventsManager.urlAsParameter = urlAsParameter;
		CustomEventsManager.userName = userName;
		CustomEventsManager.passwordAsParameter = passwordAsParameter;
		CustomEventsManager.driverClassName = driverClassName;
	}
	
	
	public Iterable getEvents() {
		
		System.out.println("I am here CustomEventsmanager with user Id : " + usrId);
		
		List<DHXEvent> evs = new ArrayList<DHXEvent>();
		
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
        
        try {
             java.sql.Connection conn = getConnection();
             java.sql.PreparedStatement ps = null;
             ResultSet rs = null;
             String query = null;

             //String query = "SELECT my_events_id as event_id, my_events_desc as event_name, to_char((select to_timestamp (my_events_start_date)), 'yyyy-MM-dd HH12:MI:ss') as start_date , to_char((select to_timestamp (my_events_end_date)), 'yyyy-MM-dd HH12:MI:ss') as end_date FROM my_events WHERE my_events_usrid =" + usrId;
             query = "SELECT my_events_id as event_id, my_events_desc as event_name, my_events_start_date as start_date, my_events_end_date as end_date FROM my_events WHERE my_events_usrid = ?";
             ps = conn.prepareStatement(query);
             ps.setString(1, usrId);
             rs = ps.executeQuery();

		    while (rs.next()) {
		       DHXEvent e = new DHXEvent();
		       e.setId(Integer.parseInt(rs.getString("event_id")));
		       e.setText(rs.getString("event_name"));
		       e.setStart_date(rs.getString("start_date"));
		       e.setEnd_date(rs.getString("end_date"));
		       evs.add(e);
		    }
		    conn.close();
        } catch (SQLException e1) {
                      e1.printStackTrace();
        }
        
        DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
		return evs;
	}
	
	
    public DHXEv createEvent(String id, DHXStatus status) {
                   return new DHXEvent();
    }
    
  
	
    @Override
    public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
    	
    	//System.out.println("I am in saveEvent of  CustomEventsmanager with user Id : " + usrId);
    	
        java.sql.Connection conn = getConnection();
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet result = null;
        
        try {
        	String query = null;
    		String start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                                        format(event.getStart_date());
    		String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                                      format(event.getEnd_date());
    		System.out.println("event.getStart_date()=="+event.getStart_date());
    		System.out.println("event.getEnd_date()=="+event.getEnd_date());
    		System.out.println("start_date==="+start_date);
    		
    		System.out.println("end_date==="+end_date);
    		
    		if (status == DHXStatus.UPDATE) {
    			query = "UPDATE my_events SET my_events_start_date=?, my_events_end_date=?, my_events_desc=? WHERE my_events_id=?";
    			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    			ps.setString(1, start_date);
    			ps.setString(2, end_date);
    			ps.setString(3, event.getText());
    			ps.setInt(4, event.getId());
    		}else if (status == DHXStatus.INSERT) {
    			query = "INSERT INTO my_events (my_events_usrid, my_events_start_date, my_events_end_date, my_events_desc)"+ 
    					"VALUES (?, ?, ?, ?)";
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, usrId);
				ps.setString(2, start_date);
				ps.setString(3, end_date);
				ps.setString(4, event.getText());
			} else if (status == DHXStatus.DELETE) {
				query = "DELETE FROM my_events WHERE my_events_id=?";
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, event.getId());
			}
    		if (ps!=null) {
    			ps.executeUpdate();
    			result = ps.getGeneratedKeys();
    			if (result.next()) {
    				event.setId(result.getInt(1));
    			}
    		}

        } catch (SQLException e) {
                      e.printStackTrace();
        } finally {
			 if (result != null) try { result.close(); } catch (SQLException e) {}
			 if (ps != null) try { ps.close(); } catch (SQLException e) {}
			 if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}

        return status;
    }
}
