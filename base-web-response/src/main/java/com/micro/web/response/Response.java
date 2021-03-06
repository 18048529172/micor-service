package com.micro.web.response;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Response  implements Serializable{

    public Response(){}

    public Response(Meta meta){
        this.meta = meta;
    }
    @ApiModelProperty(value = "返回信息头")
    private Meta meta;
    @ApiModelProperty(value = "返回信息体")
    private Body body;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }


    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    public boolean isSuccess(){
        return this.getMeta().isSuccess();
    }
}
