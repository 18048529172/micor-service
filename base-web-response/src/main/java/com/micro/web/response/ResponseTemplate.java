package com.micro.web.response;


import com.micro.base.exception.AbsException;
import com.micro.base.exception.ExceptionCodes;

/**
 * @author 李伟
 */
public class ResponseTemplate {

    private String errorMessage = "请求失败";

    public ResponseTemplate(){
        this("请求失败");
    }

    public ResponseTemplate(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public Response doResponse(ResponseHandler handler){
        Response response = new Response();
        Meta meta = new Meta();
        long startTime = System.currentTimeMillis();
        try{
            Body body = handler.execute();
            meta.setCode("200");
            meta.setMessage("success");
            response.setBody(body);
        }catch (Exception e){
            if( e instanceof AbsException){
                AbsException absException = (AbsException) e;
                meta.setMessage(absException.getMessage());
                meta.setCode(absException.getExceptionCodes().getCode());
            }else{
                meta.setMessage(this.errorMessage);
                meta.setCode(ExceptionCodes.ERROR.getCode());
            }
        }finally {
            long endTime = System.currentTimeMillis();
            meta.setRequestTime(endTime - startTime);
            response.setMeta(meta);
        }
        return response;
    }

    public static Response createSuccess(){
        return createResponse("200","success");
    }

    public static Response createFail( String errorMessage ){
        return createResponse(ExceptionCodes.ERROR.getCode(),errorMessage);
    }

    public static Response createResponse(String code,String message){
        Response response = new Response();
        Meta meta = new Meta();
        meta.setCode(code);
        meta.setMessage(message);
        response.setMeta(meta);
        return response;
    }


}
