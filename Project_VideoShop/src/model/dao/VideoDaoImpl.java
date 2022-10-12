package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.VideoDao;
import model.vo.VideoVO;

public class VideoDaoImpl implements VideoDao{
	
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@192.168.0.77:1521:xe";
	final static String USER = "scott";
	final static String PASS = "tiger";
	Connection con = null;
	PreparedStatement ps = null;
	
	public VideoDaoImpl() throws Exception{
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("VIDEO DRIVER LOADING SUCESS");
	}
	
	//비디오를 추가하기
	public void insertVideo(VideoVO vo, int count) throws Exception{
		// 2. Connection 연결객체 얻어오기
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "INSERT INTO video(vno, genre, title, director, actor, v_desc) "
				+ "VALUES (seq_video_no.nextval, ?, ?, ?, ?, ?)";
		// 4. sql 전송객체 (PreparedStatement)	
		ps = con.prepareStatement(sql);
		ps.setString(1,vo.getGenre());
		ps.setString(2,vo.getTitle());
		ps.setString(3,vo.getDirector());
		ps.setString(4,vo.getActor());
		ps.setString(5,vo.getV_desc());
		// 5. sql 전송
		for(int i = 0; i < count; i++)
			ps.executeUpdate();
		// 6. 닫기
		ps.close();
		con.close();
		
	}

	//검색
	public ArrayList<VideoVO> selectVideo(String titlediretor, String vname) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결객체 얻어오기
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3.sql 문장
		// 4.전송객체
		String sql;
		if (vname.equals("")) {
			sql ="SELECT vno, genre, title, director, actor FROM video";
			ps = con.prepareStatement(sql);
		} else if (titlediretor.equals("감독")) {
			sql  ="SELECT vno, genre, title, director, actor FROM video where director = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vname);
		}
		else {
			sql = "SELECT vno, genre, title, director, actor FROM video WHERE title = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vname);

		}
		// 5. 전송
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("vno"));
			temp.add(rs.getString("title"));
			temp.add(rs.getString("genre"));
			temp.add(rs.getString("director"));
			temp.add(rs.getString("actor"));
			data.add(temp);
		} // while
			// 6. 닫기

		return data;
	}//selectVideo
	

}
