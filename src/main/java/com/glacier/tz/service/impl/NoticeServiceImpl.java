package com.glacier.tz.service.impl;

import com.glacier.tz.dao.NoticeMapper;
import com.glacier.tz.model.Notice;
import com.glacier.tz.service.AccountService;
import com.glacier.tz.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Glacierlx on 2015/12/16.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private AccountService accountService;

    public int addNotice(String accessToken, String content) {
        Notice record = new Notice();
        record.setAccessToken(accessToken);
        record.setContent(content);
        return noticeMapper.insert(record);
    }

    public List<Notice> getNoticeList(Integer skip) {
        return noticeMapper.selectNoticeWithSkip(skip * 10);
    }

    public List<Notice> getNoticeListWithStuID(String stuID, Integer skip) {
        String accessToken = accountService.selectAccessTokenByStuID(stuID);
        return noticeMapper.selectNoticeWithSkipAndStuID(accessToken, skip * 10);
    }
}
