package com.xhnj.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TAdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;


}
