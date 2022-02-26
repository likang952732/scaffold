package com.wwinfo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-24 22:02
 */
@Component
public class BusinUtil {

    @Autowired
    private CheckSecritUtil checkSecritUtil;

    public String checkRequest(HttpServletRequest request, Map<String, Object> requestParam){
        String checkMessage = "校验失败";
        try {
            checkMessage = checkSecritUtil.checkSecrit(request, requestParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkMessage;
    }

}
