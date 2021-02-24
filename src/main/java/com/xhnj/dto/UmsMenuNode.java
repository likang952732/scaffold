package com.xhnj.dto;

import com.xhnj.model.TMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 */
@Getter
@Setter
public class UmsMenuNode extends TMenu {
    private List<UmsMenuNode> children;
}
