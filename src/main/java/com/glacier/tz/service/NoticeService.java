package com.glacier.tz.service;

import com.glacier.tz.model.Notice;

import java.util.List;

/**
 * Created by Glacierlx on 2015/12/16.
 */
public interface NoticeService {

    public int addNotice(String accessToken, String content);

    public List<Notice> getNoticeList(Integer skip);

    public List<Notice> getNoticeListWithStuID(String stuID, Integer skip);

}
