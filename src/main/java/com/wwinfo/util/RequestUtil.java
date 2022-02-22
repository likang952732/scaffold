package com.wwinfo.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 @Description
 *@author kang.li
 *@date 2021/8/3 12:54   
 */
@Slf4j
public class RequestUtil {

    /**
     * 处理请求参数
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, Object> analysisRequestParam(HttpServletRequest request) throws Exception {
        Map<String, Object> paramMap = new HashMap();
        if(ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List requestItems = upload.parseRequest(request);
            requestItems.stream().forEach(item -> {
                FileItem fileItem = (FileItem)item;
                try {
                    if(!fileItem.isFormField()) {
                        paramMap.put(fileItem.getFieldName(), fileItem);
                    } else if(StrUtil.isNotBlank(fileItem.getString("UTF-8"))) {
                        paramMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                    }
                } catch (Exception e) {
                    log.error("处理请求参数异常: {}", e);
                }
            });
        } else {
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String paramName = (String)enumeration.nextElement();
                String paramValue = request.getParameter(paramName);
                if(StrUtil.isNotBlank(paramValue)) {
                    paramMap.put(paramName, paramValue);
                }
            }
        }
        return paramMap;
    }

}
