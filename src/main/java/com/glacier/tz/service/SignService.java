package com.glacier.tz.service;

import com.glacier.tz.model.Sign;

import java.util.List;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public interface SignService {

    public int signOperation(String accessToken, int operation);

    public List<Sign> getRecordsByDate(String beginDate, String endDate);

    public List<Sign> getRecordsByDateWithStuID(String stuID, String beginDate, String endDate);

}
