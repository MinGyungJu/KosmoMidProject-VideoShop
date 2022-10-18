package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.RentDao;

public class RentDaoImpl implements RentDao {

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@192.168.0.77:1521:xe";
	final static String USER = "scott";
	final static String PASS = "tiger";
	Connection con = null;
	PreparedStatement ps = null;

	public RentDaoImpl() throws Exception {
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("CUSTOMER DRIVER LOADING SUCESS");
	}

	public ArrayList selectList() throws Exception {
		ArrayList data = new ArrayList();
		//2. 연결객체 얻어오기
		con = DriverManager.getConnection(URL, USER, PASS);
		//3. sql문장
		String sql = "SELECT v.vno vno, v.title title, c.name name, c.tel tel, r.bw_date + 3 bw_date, '미납' status"
				+ " FROM customer c join rental r on c.tel = r.tel "
				+ "join video v on v.vno = r.vno and r.status = 'N' ";
		//4. 전송객체
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ArrayList t = new ArrayList();
			t.add(rs.getInt("vno"));
			t.add(rs.getString("title"));
			t.add(rs.getString("name"));
			t.add(rs.getString("tel"));
			t.add(rs.getString("bw_date"));
			t.add(rs.getString("status"));
			data.add(t);
		}//while
		// 6. 닫기
		ps.close();
		con.close();
		return data;
	}//selectList
	
	public boolean rentVideo(String tel, int vnum) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		//checking if the video is rented
		String sqlpre = "SELECT * FROM rental WHERE vno = ? and status = 'N'";
		ps = con.prepareStatement(sqlpre);
		// ? 세팅 
		ps.setInt(1, vnum); 
		ResultSet rs  = ps.executeQuery();
		if(rs.next()) {
			System.out.println("HELLO");
			JOptionPane.showMessageDialog(null, "이미 대여 되어있습니다.");
			return false;
		}//if
		//inserting into rental
		String sql = "INSERT INTO rental(rno, tel, vno, bw_date, status) VALUES(seq_rent_no.nextval,?,?,SYSDATE,'N')";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		// ? 세팅
		ps.setString(1, tel);
		ps.setInt(2, vnum);
		ps.executeUpdate();
		// 6. 닫기
		ps.close();
		con.close();
		return true;
	}// rentVideo

	public void returnVideo(int vnum) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "UPDATE rental SET status = 'Y' WHERE vno = ? AND status ='N'";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		// ? 세팅
		ps.setInt(1, vnum);
		ps.executeUpdate();
		// 6. 닫기
		ps.close();
		con.close();
	}// returnVideo
	
	public String selectName(String tel) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		String name = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			//3. sql문 불러오기
			String sql = "SELECT name FROM CUSTOMER WHERE TEL = ?";

			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);
			// ?세팅 -#
			ps.setString(1, tel);


			// 5. sql 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}

			// 6. 닫기 
		} finally {
			// 6. 닫기
			ps.close();
			con.close();

		}

		return name;



	}

}// RentDaoImpl
