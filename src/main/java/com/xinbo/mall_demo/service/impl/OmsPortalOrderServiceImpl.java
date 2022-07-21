package com.xinbo.mall_demo.service.impl;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.component.CancelOrderSender;
import com.xinbo.mall_demo.model.dto.OrderParam;
import com.xinbo.mall_demo.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理Service实现类
 * @author xinbo
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public Result generateOrder(OrderParam orderParam) {
        LOGGER.info("process generateOrder");

        sendDelayMessageCancelOrder(11L);
        return Result.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        LOGGER.info("process cancelOrder orderId:{}", orderId);

    }

    @Override
    public Integer cancelTimeOutOrder() {
        return null;
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
