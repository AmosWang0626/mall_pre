package com.mall.user.common.pojo;

import com.mall.common.api.BaseConverter;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.user.api.pojo.request.LoginRequest;
import com.mall.user.dao.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 模块名称: mall
 * 模块描述: UserConverter
 *
 * @author amos.wang
 * @date 2020/4/8 17:22
 */
@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter {

    @Mappings({})
    UserEntity convert(LoginRequest vo);

    @Mappings({})
    UserEntity convert(AuthUserVO vo);

    @Mappings({@Mapping(source = "id", target = "userId")})
    AuthUserVO reduction(UserEntity entity);

}