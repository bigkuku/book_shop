package com.bx.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bx.bookshop.entity.OrderItem;
import com.bx.bookshop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 16:37
 * @Description:
 */
@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper,OrderItem> {
}
