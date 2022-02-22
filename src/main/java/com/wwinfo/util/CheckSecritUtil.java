package com.wwinfo.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.*;

/*
 @Description secrit校验工具
 *@author kang.li
 *@date 2021/8/3 13:06   
 */
@Component
@Slf4j
public class CheckSecritUtil {

    @Value("${system.secritmap}")
    private String secritmap;

    public final static String codeParamName = "restcode";
    public final static String timeParamName = "resttime";
    public final static String CHECKPARAMNAME = "checksecrit";
    private static Map<String, String> serviceSecrit = new HashMap<>();

    /*
     * 对接收到的REST请求进行验证
     * @return:
     * null: 验证成功
     * String: 失败原因
     */
    public Map<String, Object> checkSecrit(HttpServletRequest request, Map<String, Object> requestParam ) throws Exception{
        Map<String,String[]> mapParam = request.getParameterMap();
        List<String> listParam = new ArrayList<>();
        JSONObject result = new JSONObject();
        for(String name : mapParam.keySet()){
            if (name.equals(CHECKPARAMNAME)){	//验证值参数不需要加入到验证内容中
                continue;
            }
            listParam.add(name);
        }
        Object checkCodeObj = requestParam.get(CHECKPARAMNAME);
        Object codeObj = requestParam.get(codeParamName);
        Object timeObj = requestParam.get(timeParamName);

        JSONArray secritArray = JSONUtil.parseArray(secritmap);

        if (checkCodeObj == null){
            result.put("status","5001");
            //参数错误，未提交验证码
            result.put("message","Parameter error, verification code not submitted");
            return result;
        }
        if(codeObj == null) {
            result.put("status","5002");
            //参数错误，未提交应用代码
            result.put("message","Parameter error, application code not submitted");
            return result;
        }
        if(timeObj == null){
            result.put("status","5003");
            //参数错误，未提交时间戳
            result.put("message","Parameter error, no timestamp submitted");
            return result;
        }
        JSONObject secritJson = null;
        String secrit = "";
        for (int i = 0; i < secritArray.size(); i++){
            secritJson = secritArray.getJSONObject(i);
            if(codeObj.toString().equals(secritJson.getStr("restcode"))){
                secrit = secritJson.getStr("secrit");
                break;
            }
        }
        if(StrUtil.isBlank(secrit)){
            result.put("status","5004");
            //不存在的应用代码
            result.put("message","Application code that does not exist");
            return result;
        }
        String checkCode = checkCodeObj.toString();
        Collections.sort(listParam);	//参数以字母顺序排序
        StringBuilder contentBuilder=new StringBuilder();
        String code = null;
        Long time = null;
        for(String param: listParam){
            String value = request.getParameter(param);
            if (contentBuilder.length()>0)
                contentBuilder.append("&");
            contentBuilder.append(param);
            contentBuilder.append("=");
            contentBuilder.append(value);
            if (param.equals(codeParamName)){
                code = value;
            }else if (param.equals(timeParamName)){
                try{
                    time = Long.valueOf(value);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        long curTime = (new Date()).getTime();
        time = Long.valueOf(timeObj.toString());
        if (Math.abs(curTime - time) > 5*60*1000L){
            //log.info("curTime: {}",curTime);
            result.put("status","5004");
            //时钟不同步，相差超过5分钟
            result.put("message","Clocks are out of sync, more than 5 minutes apart");
            return result;
        }
        //计算验证码
        String calcCode = calcCheckSecrit(contentBuilder.toString(), secrit);
        if (!checkCode.equals(calcCode)){
            log.info("calcCode: {}",calcCode);
            result.put("status","5005");
            //验证码错误
            result.put("message","Verification code error");
            return result;
        }
        return null;
    }

    private String calcCheckSecrit(String content, String secrit){
        //把secrit拼接到内容最后，计算32位小写MD5码
        String temp = content + secrit;
        return getMD5(temp, false);
    }

    private String getMD5(String orgString, boolean isUpperCase){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(orgString.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            String ret = buf.toString();
            if (isUpperCase)
                return ret.toUpperCase();
            else
                return ret;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
