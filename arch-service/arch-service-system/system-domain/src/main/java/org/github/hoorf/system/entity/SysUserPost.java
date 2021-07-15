package org.github.hoorf.system.entity;

import lombok.Data;
import org.github.hoorf.domain.BaseEntity;

@Data
public class SysUserPost extends BaseEntity {

    /** 用户ID */
    private Long userId;

    /** 岗位ID */
    private Long postId;
}
