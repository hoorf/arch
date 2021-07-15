package org.github.hoorf.system.entity;

import lombok.Data;
import org.github.hoorf.domain.BaseEntity;

@Data
public class SysUserRole extends BaseEntity {


    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;
}
