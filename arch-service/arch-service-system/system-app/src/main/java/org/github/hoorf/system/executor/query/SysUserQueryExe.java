package org.github.hoorf.system.executor.query;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cola.dto.PageQuery;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.github.hoorf.system.entity.SysUser;
import org.github.hoorf.system.converter.SystemConverter;
import org.github.hoorf.system.dto.UserPageVO;
import org.github.hoorf.system.dto.query.UserPageQuery;
import org.github.hoorf.system.gateway.SysUserGateway;
import org.github.hoorf.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysUserQueryExe {

    @Autowired
    private SysUserGateway sysUserGateway;

    public PageResponse<UserPageVO> list(UserPageQuery query) {
        startPage(query);
        SysUser user = SystemConverter.INSTANCE.toSysUser(query);
        List<SysUser> list = sysUserGateway.selectUserList(user);
        return getDataTable(list);
    }

    protected void startPage(PageQuery pageQuery) {
        Integer pageNum = pageQuery.getPageIndex();
        Integer pageSize = pageQuery.getPageSize();
        String order = pageQuery.getOrderBy();
        if (ObjectUtil.isNotNull(pageNum) && ObjectUtil.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(order);
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    protected PageResponse<UserPageVO> getDataTable(List<SysUser> list) {
        if (list instanceof Page) {
            PageInfo<SysUser> pageInfo = new PageInfo<>(list);
            List<UserPageVO> pageVOS = SystemConverter.INSTANCE.toUserPageVO(list);
            return PageResponse.of(pageVOS, pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getPageNum());
        }
        return PageResponse.buildSuccess();
    }
}
