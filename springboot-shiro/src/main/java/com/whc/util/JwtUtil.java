package com.whc.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO JWT加密，校验工具，并且使用用户自己的密码充当加密密钥
 * @Author Administrator
 * @Date 2019/1/5 14:46
 * @Version 1.0
 */
public class JwtUtil {

    //过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String secret){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @param token token中包含的用户名
     * @return
     */
    public static String getUsername(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    /**
     * 生成签名，5分钟过期
     * @param username 用户名
     * @param secret  用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            //附带username信息
            return JWT.create().
                    withClaim("username", username).//用户名
                    withExpiresAt(date).//过期时间
                    sign(algorithm);//算法

        }catch (UnsupportedEncodingException  e){
            return null;
        }
    }
}
