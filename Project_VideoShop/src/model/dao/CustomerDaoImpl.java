package model.dao;

import model.CustomerDao;
import model.vo.CustomerVO;

public class CustomerDaoImpl implements CustomerDao{

	
	
	public CustomerDaoImpl() throws Exception{
	 	// 1. 드라이버로딩
		
		
	}
	
	public void insertCustomer(CustomerVO vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
		// 3. sql 문장 만들기
		// 4. sql 전송객체 (PreparedStatement)		
		// 5. sql 전송
		// 6. 닫기 

	}
	
	public CustomerVO selectByTel(String tel) throws Exception{
		CustomerVO dao = new CustomerVO();
		
		
		return dao;
		
	}
	
	public int updateCustomer(CustomerVO vo) throws Exception{
		
		int result = 0;
		
		
		return result;
	}
}
