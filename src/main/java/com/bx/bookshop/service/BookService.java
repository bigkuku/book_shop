package com.bx.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bx.bookshop.entity.Book;
import com.bx.bookshop.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: jzhang
 * @Date: 2019/9/24 09:56
 * @Description: 图书业务层
 */
@Service
public class BookService extends ServiceImpl<BookMapper,Book> {
}
