package com.mall.common.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mall.common.pojo.response.AuthUserVO;

/**
 * PROJECT: mall
 * DESCRIPTION: jwt utils
 *
 * @author amos
 * @date 2019/7/23
 */
public class JwtBaseUtils {

    /**
     * 根据 token 获取 AuthUserVO
     */
    public static AuthUserVO getAuthUser(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);

            AuthUserVO authUserVO = new AuthUserVO();
            authUserVO.setUserId(decode.getClaim(JwtBaseConstant.USER_ID).asString());
            authUserVO.setAccount(decode.getClaim(JwtBaseConstant.ACCOUNT).asString());
            authUserVO.setUsername(decode.getClaim(JwtBaseConstant.USERNAME).asString());

            return authUserVO;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
