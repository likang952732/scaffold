package com.wwinfo.pojo.bo;

import com.wwinfo.model.TMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 */
@Getter
@Setter
public class MenuNode extends TMenu {

    private List<MenuNode> children;

}
