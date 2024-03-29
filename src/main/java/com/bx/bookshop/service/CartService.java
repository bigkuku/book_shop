package com.bx.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bx.bookshop.entity.Cart;
import com.bx.bookshop.entity.CartVo;
import com.bx.bookshop.entity.UserCartVo;
import com.bx.bookshop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: jzhang
 * @Date: 2019/9/26 11:29
 * @Description:
 */
@Service
public class CartService extends ServiceImpl<CartMapper,Cart> {
    @Autowired
    private CartMapper cartMapper;

    /**
     * 根据用户查询购物车记录
     * @param userId
     * @return
     */
    public List<CartVo> findCartByUser(Integer userId){
        return cartMapper.findCartListByUserId(userId);
    }

    /**
     * 根据购物车id查询对应的记录
     */
    public List<CartVo> findCartByIds(String ids){
        return cartMapper.findCartListByIds(Arrays.asList(ids));
    }


    /**
     * 统计当前用户购物车的总计
     */
    public double getCartItemTotal(List<CartVo> list){
        double sum = 0.0;
        for (CartVo cart: list) {
            sum += cart.getCount() * cart.getNewPrice();
        }
        return sum;
    }

    /**
     * 批量删除
     */
    public String batchDelete(String ids){
        if(ids != null){
            String[] idArray = ids.split(",");
            cartMapper.deleteBatchIds(Arrays.asList(idArray));
        }
        return "success";
    }


    /**
     * 包装用户购物车信息数据
     */
    public UserCartVo wrapperCart(List<CartVo> list){
        UserCartVo userCartVo = new UserCartVo();
        userCartVo.setNum(list.size());
        userCartVo.setTotalPrice(getCartItemTotal(list));
        return userCartVo;
    }
}
