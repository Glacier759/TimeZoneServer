package com.glacier.tz.dao;

import com.glacier.tz.model.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> selectNoticeWithSkip(Integer skip);

    List<Notice> selectNoticeWithSkipAndStuID(@Param("accessToken")String accessToken, @Param("skip")Integer skip);

    List<Notice> selectAllNotice();

    List<Notice> refresh(Integer lastID);
}