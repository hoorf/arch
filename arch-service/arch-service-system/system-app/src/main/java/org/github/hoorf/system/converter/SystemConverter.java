package org.github.hoorf.system.converter;

import org.github.hoorf.system.entity.SysUser;
import org.github.hoorf.system.dto.UserPageVO;
import org.github.hoorf.system.dto.query.UserPageQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SystemConverter {
    SystemConverter INSTANCE = Mappers.getMapper(SystemConverter.class);

    SysUser toSysUser(UserPageQuery query);

    List<UserPageVO> toUserPageVO(List<SysUser> userList);
}
