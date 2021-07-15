package org.github.hoorf.system.dto.query;

import com.alibaba.cola.dto.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserPageQuery extends PageQuery {


    /**
     * 部门ID
     */

    @ApiModelProperty("部门id")
    private Long deptId;

    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    private String userName;

    /**
     * 用户昵称
     */

    private String nickName;

    /**
     * 用户邮箱
     */

    private String email;

    /**
     * 手机号码
     */

    private String phonenumber;

}
