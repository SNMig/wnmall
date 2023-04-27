package com.woniuxy.mall.filter;

import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AuthFilter implements Filter {
    @Value("${JWT.secretKey}")
    private String secretKey;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug("AuthFilter");
        //1，获取请求头的authorization
        //2,校验jwt，如果校验成功，放行，否则，将响应（转为JSON）返回客户端
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        String path=req.getRequestURI();
        if (path.equals("/api/admin/login")||path.equals("/api/captcha")||path.equals("/api/upload")){
            chain.doFilter(request, response);
            return;
        }
        String authorization= req.getHeader("authorization");
        if (authorization==null||authorization.equals("")){
            ResponseResult<Void> responseResult=new ResponseResult<>(ResponseCode.NOT_LOGIN);
            ObjectMapper objectMapper=new ObjectMapper();
            String result=objectMapper.writeValueAsString(responseResult);
            PrintWriter writer=res.getWriter();
            writer.write(result);
            writer.close();
            return;
        }

        try {
            if (JWT.of(authorization).setKey(secretKey.getBytes()).verify()){
                chain.doFilter(request, response);
            }else {
                ResponseResult<Void> responseResult=new ResponseResult<>(ResponseCode.NO_AUTHED);
                ObjectMapper objectMapper=new ObjectMapper();
                String result=objectMapper.writeValueAsString(responseResult);
                PrintWriter writer=res.getWriter();
                writer.write(result);
                writer.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            ResponseResult<Void> responseResult=new ResponseResult<>(ResponseCode.FAIL);
            ObjectMapper objectMapper=new ObjectMapper();
            String result=objectMapper.writeValueAsString(responseResult);
            PrintWriter writer=res.getWriter();
            writer.write(result);
            writer.close();
        }
        


    }
}
