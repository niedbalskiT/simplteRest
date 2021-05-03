package com.niedbalski.simpleRest.exceptions_handling;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class GithubErrorResponse {

    private Integer status;
    private String error;
    private String message;
    private Date timestamp;

    public GithubErrorResponse(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = new Date();
    }
}
