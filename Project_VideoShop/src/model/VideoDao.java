package model;

import model.vo.VideoVO;

public interface VideoDao {
	public void insertVideo(VideoVO vo, int count) throws Exception;
}
