package model;

import java.util.ArrayList;

public interface RentDao {
	//대여
	public boolean rentVideo(String tel, int vnum) throws Exception;
	
	//반납
	public void returnVideo(int vnum) throws Exception;
	
	//미납목록검색
	public ArrayList selectList() throws Exception;
	//번호넣고 엔터쳤을 때 고객명 나오기
		public String selectName(String tel) throws Exception;
	
}
