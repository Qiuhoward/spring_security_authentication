package com.example.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final int expiredTime=1000*1000;
    private static final String key="645367566B59703373367639792442264528482B4D6251655468576D5A713474";//網路抓的加密鑰匙(下面getKey進行解密)
    public String generateToken(UserDetails userDetails){
        Map<String,Objects>claims=new HashMap<>();
        return Jwts.builder()
                .setIssuer("howard")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredTime))
                .setSubject(userDetails.getUsername())
                .signWith(getKey(),SignatureAlgorithm.HS256)//簽名產生jwt
                .compact();
    }

    public String extraUserEmail(String token) {
        return extraClaim(token,Claims::getSubject);//其實get這些東西 這些參數都是claims類的所以傳下去function<為claims類,回傳則不一定>
    }

    /**
     * 解析指定claim
     */
    public <T> T extraClaim(String token, Function<Claims,T>claimsTFunction){ //java8 的function T為function回傳值 Claims為引數
        final Claims claims=extraAllClaim(token);//先解析所有claims 下一步套用function就可以了
        return claimsTFunction.apply(claims);//引數為CLAIMS所以回傳就是CLAIMS
    }

    /**
     * 解析所有claims資訊
     */
    private Claims extraAllClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)//JWS而非JWT
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extraExpired(token).before(new Date());//在現在之前過期回傳true
    }

    private Date extraExpired(String token){
        return extraClaim(token,Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName=extraUserEmail(token);
        return userName.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }


    /**
     * 獲取私鑰
     */
    public Key getKey(){
        byte[]keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
