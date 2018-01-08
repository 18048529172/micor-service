package com.micro.zuul.server.filter;

import com.micro.base.web.response.Response;
import com.micro.base.web.response.ResponseTemplate;
import com.micro.zuul.server.feign.TokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 说明：
 *
 * appKey:代表一个租户
 *
 * 通过appKey和用户id，生成accessToken
 *
 * accessToken：包含租户id
 *
 * @author liw@suncd.com
 * @date 2018/1/3 13:31
 */
@Component
public class AccessTokenZuulFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenZuulFilter.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //数字越大越靠后
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String accessToken = request.getHeader("accessToken");
        if(accessToken == null){
           //直接返回给前端
            String responseMessage = ResponseTemplate.createFail("accessToken为空").toJsonString();
            this.writeResponse(response,responseMessage);
        }else{
            //验证token是否合法
           this.checkToken(response,accessToken);
        }
        return null;
    }

    private void checkToken(HttpServletResponse response, String accessToken) {
        //验证accessToken是否有效（包括没有失效）
        Response result = tokenService.getAppIdByAccessToken(accessToken);
        if(!result.isSuccess()){
            String responseMessage = ResponseTemplate.createFail(result.getMeta().getMessage()).toJsonString();
            this.writeResponse(response,responseMessage);
        }
    }

    private void writeResponse(HttpServletResponse response, String responseMessage) {
        PrintWriter writer = null;
        try{
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            writer.write(responseMessage);
            writer.flush();
        }catch (Exception e){
            LOGGER.error("",e);
        }finally {
            if(writer != null){
               writer.close();
            }
        }
    }

}
