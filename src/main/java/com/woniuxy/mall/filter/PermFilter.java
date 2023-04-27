package com.woniuxy.mall.filter;

import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.entiy.Perm;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.PermService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PermFilter implements Filter {
    @Autowired
    private PermService permService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        String path=req.getRequestURI();
        if (path.equals("/api/admin/login")||path.equals("/api/menu/list")||path.equals("/api/captcha")||path.equals("/api/upload")){
            chain.doFilter(request, response);
            return;
        }
        String authorization= req.getHeader("authorization");
        JWT jwt=JWT.of(authorization);
        Object id=jwt.getPayload("id");
        List<Perm> perms=permService.getPermByUserId(Integer.parseInt((String) id));
        for (Perm perm : perms) {
            if (path.equals(perm.getUrl())){
                chain.doFilter(request, response);
                return;
            }
        }
        ResponseResult<Void> responseResult=new ResponseResult<>(ResponseCode.NO_AUTHED);
        ObjectMapper objectMapper=new ObjectMapper();
        String result=objectMapper.writeValueAsString(responseResult);
        PrintWriter writer=res.getWriter();
        writer.write(result);
        writer.close();
    }
}
