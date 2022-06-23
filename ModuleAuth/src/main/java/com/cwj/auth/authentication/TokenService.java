package com.cwj.auth.authentication;

import com.cwj.common.Constants.Constants;
import com.cwj.common.utils.IdUtils;
import com.cwj.datasource.redis.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TokenService token验证处理
 *
 * @author ChengWenjia
 * @since 2022-02-09 10:58
 */
@Component
public class TokenService {

    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final Long MILLIS_MINUTE_TWENTY = 20 * 60 * 1000L;
    private final Logger logger = LoggerFactory.getLogger("TokenService ---->");
    /**
     * 获取：令牌自定义标识
     */
    @Value("${token.header}")
    private String header;

    /**
     * 获取：令牌秘钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 获取：令牌有效期（默认30分钟）
     */
    @Value("${token.expireTime}")
    private int expireTime;

    /**
     * 获取：JWT的唯一标识
     */
    @Value("${token.jwtId}")
    private String jwtId;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUserDetails getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        //logger.info("getLoginUser token : {}", token);
        if (StringUtils.hasLength(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                //logger.info("getLoginUser parse info : {}", user);
                System.out.println("get obj user: " + redisCacheUtil.getCacheObject(userKey));
                return redisCacheUtil.getCacheObject(userKey);
            } catch (Exception e) {
                System.out.println("details: "+ e.toString());
            }
        }
        return null;
    }


    /**
     * 设置用户身份信息
     * <p>
     * 修改用户个人信息时需要调用此方法
     */
    public void setLoginUser(LoginUserDetails loginUserDetails) {
        if (loginUserDetails != null && StringUtils.hasLength(loginUserDetails.getToken())) {
            refreshToken(loginUserDetails);
        }
    }

    /**
     * 删除用户身份信息
     *
     * @param token 令牌
     */
    public void delLoginUser(String token) {
        if (StringUtils.hasLength(token)) {
            String userKey = getTokenKey(token);
            redisCacheUtil.deleteObject(userKey);
        }
    }

    /**
     * 创建token
     *
     * @param loginUserDetails 用户信息
     * @return token
     */
    public String createUserToken(LoginUserDetails loginUserDetails) {
        String token = IdUtils.fastUUID();
        //logger.info("createUserToken loginUserDetails : {}", loginUserDetails.toString());
        //logger.info("createUserToken fastUUID : {}", token);
        loginUserDetails.setToken(token);
        refreshToken(loginUserDetails);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }


    /**
     * 根据负载数据创建token
     *
     * @param claims 负载数据
     * @return token
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                // 一定要先设置自己创建的私有的声明，一旦写在标准的声明赋值之后，就会覆盖了那些原有的标准声明。
                .setClaims(claims)
                // 设置签名使用的 签名算法 和 签名秘钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        //logger.info("createToken claims : {}", claims.toString());
        //logger.info("createToken token: {}", token);
        return token;
    }

    /**
     * 从token中获取负载数据
     *
     * @param token 包含负载的token
     * @return 负载数据
     */
    private Claims parseToken(String token) {
        //logger.info("parseToken : " + token);
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    // 注意使用带有s的方法
                    .parseClaimsJws(token)
                    .getBody();
            //logger.info("parseToken claims : {}", claims.toString());
        } catch (Exception e) {
            //logger.error("parseToken Exception: {}", e.toString());
        }

        return claims;
    }

    /**
     * 刷新token有效期
     *
     * @param loginUserDetails 用户登录信息
     */
    private void refreshToken(LoginUserDetails loginUserDetails) {
        // 更新过期时间
        loginUserDetails.setLoginTime(System.currentTimeMillis());
        loginUserDetails.setExpireTime(loginUserDetails.getLoginTime() + expireTime * MILLIS_MINUTE);

        // 更新loginUser缓存
        String userKey = getTokenKey(loginUserDetails.getToken());
        //logger.info("userKey : {}", userKey);
        redisCacheUtil.setCacheObject(userKey, loginUserDetails, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 获取请求token
     *
     * @param request 请求体
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        //logger.info("getToken 'Authorization' value is : {}", request.getHeader(header));
        return request.getHeader(header);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUserDetails 用户登录信息
     */
    public void verifyToken(LoginUserDetails loginUserDetails) {
        //logger.info("verifyToken : {}", loginUserDetails.toString());
        long expireTime = loginUserDetails.getExpireTime();
        long currentTime = System.currentTimeMillis();
        //logger.info("verifyToken two time: {}", (expireTime - currentTime));
        if (expireTime - currentTime <= MILLIS_MINUTE_TWENTY) {
            refreshToken(loginUserDetails);
        }
    }
}
