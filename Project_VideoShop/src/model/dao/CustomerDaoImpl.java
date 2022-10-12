package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.CustomerDao;
import model.vo.CustomerVO;

public class CustomerDaoImpl implements CustomerDao {

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@192.168.0.77:1521:xe"; // 127.0.0.1
	final static String USER = "scott";
	final static String PASS = "tiger";
	Connection con = null;
	PreparedStatement ps = null;

	public CustomerDaoImpl() throws Exception {
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("DRIVER LOADING SUCESS");
	}

	/*
	 * method: insertCustomer/ param: CustomerVO/ return: void/ function: inserts
	 * customer according to vo/
	 */
	public void insertCustomer(CustomerVO vo) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "INSERT INTO customer(tel,name, addr, email, tel2) " + "VALUES(?,?,?,?,?)";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		// ? 세팅
		ps.setString(1, vo.getTel());
		ps.setString(2, vo.getName());
		ps.setString(3, vo.getAddr());
		ps.setString(4, vo.getEmail());
		ps.setString(5, vo.getTel2());
		// 5. sql 전송
		ps.executeUpdate();
		// 6. 닫기
		ps.close();
		con.close();
	}

	/*
	 * method: selectByTel/ param: telephone number/ return: CustomerVO/ function:
	 * selects vo according to the telephone number
	 */
	public CustomerVO selectByTel(String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "SELECT * FROM customer WHERE tel = ?";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		// 5. sql 전송
		ResultSet rs = ps.executeQuery();
		// 정보저장 객체 만들기
		CustomerVO dao = new CustomerVO();
		if (rs.next()) {
			dao.setAddr(rs.getString("addr"));
			dao.setEmail(rs.getString("email"));
			dao.setName(rs.getString("name"));
			dao.setTel(rs.getString("tel"));
			dao.setTel2(rs.getString("tel2"));
		}
		// 6. 닫기
		rs.close();
		ps.close();
		con.close();

		return dao;
	}// selectByTel

	/*
	 * method: updateCustomer/ param: CustomerVO/ return: int/ function: updates
	 * customer with given vo
	 */
	public int updateCustomer(CustomerVO vo) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "UPDATE customer SET addr = ?, email = ?, name = ?, tel2 = ? WHERE tel = ?";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getAddr());
		ps.setString(2, vo.getEmail());
		ps.setString(3, vo.getName());
		ps.setString(4, vo.getTel2());
		ps.setString(5, vo.getTel());

		int result = ps.executeUpdate();
		;
		return result;
	}// updateCustomer

	/*
	 * method: selectByName/ param: name/ return: ArrayList/ function: finds all
	 * record with the given name and returns the list
	 */
	public ArrayList<CustomerVO> selectByName(String name) throws Exception {
		// 2. Connection 연결객체 얻어오기 (con, ps)
		con = DriverManager.getConnection(URL, USER, PASS);
		// 3. sql 문장 만들기
		String sql = "SELECT * FROM customer WHERE name = ?";
		// 4. sql 전송객체 (PreparedStatement)
		ps = con.prepareStatement(sql);
		// ? 세팅
		ps.setString(1, name);
		// 5. sql 전송
		ResultSet rs = ps.executeQuery();
		ArrayList<CustomerVO> list = new ArrayList<CustomerVO>();
		while (rs.next()) {
			CustomerVO vo = new CustomerVO();
			vo.setAddr(rs.getString("addr"));
			vo.setEmail(rs.getString("email"));
			vo.setName(rs.getString("name"));
			vo.setTel(rs.getString("tel"));
			vo.setTel2(rs.getString("tel2"));
			list.add(vo);
		}
		// 6. 닫기
		rs.close();
		ps.close();
		con.close();

		return list;
	}// selectByName()
}
