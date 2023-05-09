package com.woniuxy.mall.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniuxy.mall.config.AlipayConfig;
import com.woniuxy.mall.entiy.Order;
import com.woniuxy.mall.service.OrderService;
import com.woniuxy.mall.utils.MallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Configuration
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayConfig alipayConfig;
    @Autowired
    private OrderService orderService;
    @RequestMapping("pay")
    public void doPay(Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=" + alipayConfig.getCharset());
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getApp_id(),
                alipayConfig.getMerchant_private_key(), "json", alipayConfig.getCharset(),
                alipayConfig.getAlipay_public_key(), alipayConfig.getSign_type());
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());

        String out_trade_no = "WN202305083124"+ LocalDateTime.now();
        String total_amount = "1000";
        String subject = "Subject";
        String body = "商品";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            throw new RuntimeException("支付失败", e);
        }
        System.out.println(result);
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.close();
    }

    @GetMapping("return")
    public String returnUrl(){
        return "alipay_suc";
    }

    @RequestMapping("notify")
    public void notifyUrl(){
        //获取订单标识（订单号）,根据订单号修改其状态为已支付
    }
}
