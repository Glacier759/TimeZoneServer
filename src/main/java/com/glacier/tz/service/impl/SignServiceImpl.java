package com.glacier.tz.service.impl;

import com.glacier.tz.dao.SignMapper;
import com.glacier.tz.model.Sign;
import com.glacier.tz.service.SignService;
import com.glacier.tz.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Glacierlx on 2015/12/15.
 */
@Service
public class SignServiceImpl implements SignService {

    @Resource
    private SignMapper signMapper;

    public int signOperation(String accessToken, int operation) {
        Sign sign = new Sign();
        sign.setAccessToken(accessToken);
        sign.setOperation(operation);
        return signMapper.insert(sign);
    }

    public List<Sign> getRecordsByDate(String beginDate, String endDate) {
        HashMap<String,String> params = new HashMap<String,String>();
        if (TimeUtils.aGreaterThanb(beginDate, endDate)) {
            params.put("end", beginDate);
            params.put("begin", endDate);
        }
        else {
            params.put("begin", beginDate);
            params.put("end", endDate);
        }
        return signMapper.selectSignRecords(params);
    }

}
