package com.woniuxy.mall.vo;

import com.woniuxy.mall.mallenum.ResponseCode;
import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    public ResponseResult(ResponseCode responseCode,T data){
        this.code= responseCode.getCode();
        this.msg=responseCode.getMsg();
        this.data=data;
    }
    public ResponseResult(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
    public ResponseResult(T data){
        this(ResponseCode.SUCCESS,data);
    }
    public static ResponseResult<Void>ok(){
        return new ResponseResult<>(ResponseCode.SUCCESS);
    }
    public static ResponseResult<Void>fail(){
        return new ResponseResult<>(ResponseCode.FAIL);
    }
    public static<T> ResponseResult<T>ok(T data){
        return new ResponseResult(ResponseCode.SUCCESS,data);
    }


    public ResponseResult(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
