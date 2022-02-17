package com.wwinfo.pojo.bo;

import com.wwinfo.model.Organize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-17 15:17
 */
@Getter
@Setter
public class OrganizeNode extends Organize {

    private List<OrganizeNode> children;
}
