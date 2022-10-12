package model;

import java.util.ArrayList;

import model.vo.VideoVO;

public interface VideoDao {
	public void insertVideo(VideoVO vo, int count) throws Exception;
	public ArrayList selectVideo(String titlediretor, String vname) throws Exception;

}
