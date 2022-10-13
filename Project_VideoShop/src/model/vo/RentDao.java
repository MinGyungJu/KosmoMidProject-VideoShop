package model.vo;

import java.util.ArrayList;

public interface RentDao {
	//대여
	public void rentVideo(String tel, int vnum) throws Exception;
	
	//반납
	public void returnVideo(int vnum) throws Exception;
	
	//미납목록검색
	public ArrayList selectList() throws Exception;
	
}
