package br.com.onitama.model.response;

import java.util.Date;

public class TokenResponse {
    private Date expiration;
    private String token;

    public TokenResponse(Date expiration, String token) {
        this.expiration = expiration;
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
