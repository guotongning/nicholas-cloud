package com.ning.springcloud.cloud.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liam
 * @date 2020/3/26 下午10:10
 * @description JWT工具类
 */
public class JwtUtil {
    /**
     * 设置过期时间及密匙
     * CALENDAR_FIELD 时间单位
     * CALENDAR_INTERVAL 有效时间
     * SECRET_KEY 密匙
     */
    public static final int CALENDAR_FIELD = Calendar.SECOND;
    public static final int CALENDAR_INTERVAL = 60 * 60 * 24 * 7;
    private static final String SECRET_KEY = "6A50A18D70FA63636645C686964F1D78";

    public static void main(String[] args) {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("userId", "6123462453445");
        System.out.println(createToken(userMap));
    }

    /**
     * 创建Token
     *
     * @param userMap 自己需要存储进token中的信息
     * @return token
     */
    public static String createToken(HashMap<String, Object> userMap) {
        // 头部
        Map<String, Object> headerMap = new HashMap<>(4);
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        // 当前时间与过期时间
        Calendar time = Calendar.getInstance();
        Date now = time.getTime();
        time.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        Date expireTime = time.getTime();

        // 选择签名加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        // 创建token并返回
        return JWT.create().withHeader(headerMap)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withSubject("user")
                .withClaim("userInfo", userMap)
                .sign(algorithm);
    }

    /**
     * 验证、解析Token
     *
     * @param token 用户提交的token
     * @return 该token中的信息
     */
    public static Map<String, Object> verifyToken(String token) {
        DecodedJWT verifier = null;
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        try {
            verifier = JWT.require(algorithm).build().verify(token);
        } catch (Exception e) {
            //TODO 可以当业务异常处理
            e.printStackTrace();
        }
        assert verifier != null;
        return verifier.getClaim("userInfo").asMap();
    }

}