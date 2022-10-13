package model;

import java.util.ArrayList;

import model.vo.VideoVO;

public interface VideoDao {
	// 정보 삽입
	public void insertVideo(VideoVO vo, int count) throws Exception;
	
	// 비디오 찾기 (+이름과 감독으로 검색 가능)
	public ArrayList selectVideo(int idx, String word) throws Exception;
	
	// 이름으로 고객 정보 불러오기
	public VideoVO selectByNum(int vNum) throws Exception; 
}
