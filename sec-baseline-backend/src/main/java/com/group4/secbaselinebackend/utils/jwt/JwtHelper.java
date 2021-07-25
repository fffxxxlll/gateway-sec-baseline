package com.group4.secbaselinebackend.utils.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.secbaselinebackend.exceptions.UnAuthorizedException;
import com.group4.secbaselinebackend.infrastructure.ObjectSelector;
import com.group4.secbaselinebackend.infrastructure.usercontext.IdentityContext;
import com.group4.secbaselinebackend.models.Admin;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;
import com.group4.secbaselinebackend.valueObjects.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//生成安全凭据
@Service
public class JwtHelper {
    @Resource(name = "tokenIdentityContext")
    private IdentityContext identityContext;

    //生成token的帮助函数
    public Map<String, Object> createJWT(String issuer, long expiredMillis, Admin admin)
            throws InvocationTargetException, IntrospectionException, NoSuchFieldException, IllegalAccessException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String secret = identityContext.getAuthKey();
        long nowMillis = new Date().getTime();
        Date now = new Date(nowMillis);
        secret = new BASE64Encoder().encode(secret.getBytes());
        //添加Token过期时间
        long expMillis = nowMillis + expiredMillis;
        Date exp = new Date(expMillis);
        //序列化待优化
        String select = "admin(id,userName,pwd,salt)";
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer(issuer)
                .claim("identity", new ObjectSelector().mapObject(admin, select))
                .signWith(signatureAlgorithm, secret);
        builder.setExpiration(exp).setNotBefore(now);
        Map<String, Object> jwtMap = new HashMap<>();
        jwtMap.put("token", builder.compact());
        jwtMap.put("expires_time", expMillis / 1000);
        return jwtMap;
    }

    //解析token的帮助函数
    public Claims parseJWT(String jsonWebToken) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(new BASE64Encoder().encode(identityContext.getAuthKey().getBytes()))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return claims;
    }
    //获取token的payload函数
    public Map getPayload(String jsonWebToken) throws IOException {
        if (jsonWebToken == null || jsonWebToken.isEmpty()) {
            throw new UnAuthorizedException(ErrorMessage.INVALID_TOKEN);
        }
        String key = identityContext.getAuthKey();
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(new BASE64Encoder().encode(key.getBytes()))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("identity", claims.get("identity"));
        return map;
    }

    public JsonNode convertTokenToJsonNode(String token) throws IOException {
        Map map = getPayload(token);
        if(map == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectMapper.writeValueAsString(map), JsonNode.class);
    }
    //返回token的帮助类
    public Token getToken(String issuer, long expiredIn, Admin admin) throws IntrospectionException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Map<String, Object> jwtMap = createJWT(issuer, expiredIn * 1000, admin);
        Token token = new Token();
        token.setToken(jwtMap.get("token").toString());
        token.setExpiresIn(expiredIn);
        token.setExpiresTime(Long.parseLong(jwtMap.get("expires_time").toString()));
        return token;
    }
}
