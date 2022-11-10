package com.learn.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * jwt工具
 */
public class JwtUtils {
    /**
     * 采用salt加密
     *
     * @param claims 载荷内容
     * @param subject 签发对象
     * @param expiration 过期时长，单位为毫秒
     * @param salt 盐
     * @return
     */
    public static String generateToken(String subject, Map<String, Object> claims, int expiration, String salt) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, salt)
                .compact();
    }

    /**
     * 采用公钥加密
     *
     * @param claims 载荷内容
     * @param subject 签发对象
     * @param expiration 过期时长，单位为毫秒
     * @param publicKey 加密公钥
     * @return
     */
    public static String generateToken(String subject, Map<String, Object> claims, int expiration, PublicKey publicKey) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, publicKey)
                .compact();
    }

    /**
     * 解析用salt加密的Token
     *
     * @param token token
     * @param salt 盐
     * @return Claims
     */
    public static Claims parseToken(String token, String salt){
        return Jwts.parser()
                .setSigningKey(salt)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析用公钥加密的Token
     *
     * @param token token
     * @param privateKey 私钥
     * @return Claims
     */
    public static Claims parseToken(String token, PrivateKey privateKey) {
        return Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
