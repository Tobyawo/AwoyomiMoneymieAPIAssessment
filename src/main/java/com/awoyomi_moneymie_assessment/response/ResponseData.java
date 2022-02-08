package com.awoyomi_moneymie_assessment.response;

import java.io.Serializable;

public class ResponseData implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    private Integer code;
    private String description;

    public ResponseData() {
    }

    public ResponseData(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public ResponseData(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.description = responseCodeEnum.getDescription();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

