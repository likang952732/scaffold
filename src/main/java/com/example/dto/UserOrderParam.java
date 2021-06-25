package com.example.dto;

import lombok.Data;

/*
 @Description
 *@author kang.li
 *@date 2021/6/25 13:57   
 */
@Data
public class UserOrderParam {
    private String dinnerName;

    private String dinnerArea;

    private String username;

    private Integer status;
}
