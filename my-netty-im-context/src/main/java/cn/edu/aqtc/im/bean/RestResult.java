package cn.edu.aqtc.im.bean;

import cn.edu.aqtc.im.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: rest服务返回结果
 * @ClassName: RestResult
 * @Author: zhangjj
 * @Date: 2020-04-01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description= "返回响应数据结果")
public class RestResult<T> {

    //状态码
    @JsonProperty("CODE")
    @ApiModelProperty(value = "返回状态码",notes = "200:成功;400:失败;401:未认证（签名错误）;404:接口不存在;500:服务器内部错误")
    private int code;
    @JsonProperty("DATA")
    @ApiModelProperty(value = "返回的数据内容")
    private T t;
    @JsonProperty("RETURN_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "请求响应时间")
    private Date responseDate;
    @JsonProperty("MESSAGE")
    @ApiModelProperty(value = "返回信息",notes = "如果返回成功，此字段不展示；如果失败，返回错误信息")
    private String message;

    public static <T> RestResult getSuccessRestResult(T t){
        RestResult<T> restResult = new RestResult();
        restResult.setCode(ResultCode.SUCCESS.getCode());
        restResult.setT(t);
        restResult.setResponseDate(DateUtils.getCurrentTime().toDate());
        return restResult;
    }

    public static <T> RestResult getSuccessRestResult(){
        RestResult<T> restResult = new RestResult();
        restResult.setCode(ResultCode.SUCCESS.getCode());
        restResult.setResponseDate(DateUtils.getCurrentTime().toDate());
        return restResult;
    }

    enum ResultCode {
        SUCCESS(200),//成功
        FAIL(400),//失败
        UNAUTHORIZED(401),//未认证（签名错误）
        NOT_FOUND(404),//接口不存在
        INTERNAL_SERVER_ERROR(500);//服务器内部错误

        private int code;

        private ResultCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

}
