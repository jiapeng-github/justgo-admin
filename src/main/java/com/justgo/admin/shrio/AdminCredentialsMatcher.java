package com.justgo.admin.shrio;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

/**
 * 密码验证
 * author : LYS
 * date: 2017/6/28 下午4:11
 */
@Component
public class AdminCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String salt;
        if (info instanceof SimpleAuthenticationInfo) {
            ByteSource _salt = ((SimpleAuthenticationInfo) info).getCredentialsSalt();
            salt = new String(_salt.getBytes());
        } else {
            throw new RuntimeException("没有获取到SALT");
        }
        boolean b = false;
        try {
            b = PasswordHelper.checkPassword((String) info.getCredentials(), new String((char[]) token.getCredentials()), salt);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return b;
    }
}
