package com.awoyomi_moneymie_assessment.response;

public enum ResponseCodeEnum {

    SUCCESS(0, "Successful"),
    ERROR(-1, "Error"),
    NO_RECORDS_FOUND(-10, "Your search was successful, but no records found"),
    ;

    private ResponseCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
