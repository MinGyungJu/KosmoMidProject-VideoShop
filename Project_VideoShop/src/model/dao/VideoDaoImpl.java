package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.VideoDao;
import model.vo.CustomerVO;
import model.vo.VideoVO;

public class VideoDaoImpl implements VideoDao{
	
	final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@192.168.0.77:1521:xe"; // 지선이 컴퓨터 ip
	final String USER = "scott";
	final String PASS = "tiger";
	
	Connection con = null;
	PreparedStatement ps = null;
	
	public VideoDaoImpl() throws Exception{
		
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("DRIVER LOADING SUCCESS!");
	}
	
	
	public void insertVideo(VideoVO vo, int count) throws Exception{
		// 2. Connection 연결객체 얻어오기
		con = DriverManager.getConnection(URL, USER, PASS);
		
		// 3. sql 문장 만들기
		String sql = "INSERT INTO video(vno, genre, title, director, actor, v_desc) " + " VALUES(seq_video_no.nextval, ?, ?, ?, ?, ?)";
		
		// 4. sql 전송객체 (PreparedStatement)		
		ps = con.prepareStatement(sql);
		
		ps.setString(1,vo.getGenre());
		ps.setString(2,vo.getTitle());
		ps.setString(3,vo.getDirector());
		ps.setString(4,vo.getActor());
		ps.setString(5,vo.getV_desc());
		
		// 5. sql 전송
		for (int i = 0; i<count; i++) {
		ps.executeUpdate();
		}
		
		// 6. 닫기
		ps.close();
		con.close();
	}

	// --------------------------------------------------------------------------------------
	
	@Override
	public ArrayList selectVideo(int idx, String word) throws Exception {
		// 2. 연결객체 얻어오기
		con = DriverManager.getConnection(URL,USER,PASS);
		// 3. sql & 전송객체
		String [] colNames = {"title", "director"};
		String sql;
		sql  ="SELECT vno, genre, title, director, actor FROM video "
				+ "where "+colNames[idx]+" LIKE '%"+word+"%' ";
		ps = con.prepareStatement(sql);
		// 5. 전송
		ResultSet rs = ps.executeQuery();
		ArrayList data = new ArrayList();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("vno"));
			temp.add(rs.getString("title"));
			temp.add(rs.getString("genre"));
			temp.add(rs.getString("director"));
			temp.add(rs.getString("actor"));
			data.add(temp);
		}
		// 6. 닫기
		ps.close();
		con.close();
		rs.close();
			
		return data;
		
	}//selectVideo
	
	// --------------------------------------------------------------------------------------
	
	public VideoVO selectByNum(int vNum) throws Exception {
		
		VideoVO vo = new VideoVO();
		
		// 2. 연결객체 얻어오기
		con = DriverManager.getConnection(URL,USER,PASS);

		// 3. sql 문장
		String sql = "SELECT vno, title, genre, director, actor, v_desc FROM video WHERE vno=?";

		// 4. 전송객체
		ps = con.prepareStatement(sql);
		ps.setInt(1, vNum);
		// 5. 전송
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			vo.setVno(rs.getInt("vno"));
			vo.setTitle(rs.getString("title"));
			vo.setGenre(rs.getString("genre"));
			vo.setDirector(rs.getString("director"));
			vo.setActor(rs.getString("actor"));
			vo.setV_desc(rs.getString("v_desc"));
		}

		return vo;
	}
	

}
