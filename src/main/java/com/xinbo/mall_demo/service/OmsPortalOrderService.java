package com.xinbo.mall_demo.service;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.model.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 * @author xinbo
 */
public interface OmsPortalOrderService {

    /**
     * 根据订单信息生成订单
     */
    @Transactional
    Result generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    void cancelOrder(Long orderId);

    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();

}
