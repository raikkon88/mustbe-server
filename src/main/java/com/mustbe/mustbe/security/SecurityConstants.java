package com.mustbe.mustbe.security;

public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String PUBLIC_USER_URLS = "/api/auth/**";

}
