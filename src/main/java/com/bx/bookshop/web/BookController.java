package com.bx.bookshop.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bx.bookshop.entity.Book;
import com.bx.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: jzhang
 * @Date: 2019/9/24 10:10
 * @Description: 图书控制器
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 获取图书信息
     */
    @RequestMapping("/getBookData")
    public String getBookData(Model model,Integer page,Integer category){
        //mybatis plus分页
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category",category);
        IPage<Book> iPage = bookService.page(new Page<>(page,4),queryWrapper);
        model.addAttribute("bookList",iPage.getRecords());
        model.addAttribute("pre",iPage.getCurrent() - 1);
        model.addAttribute("next",iPage.getCurrent() + 1);
        model.addAttribute("cur",iPage.getCurrent());
        model.addAttribute("last",iPage.getPages());
        model.addAttribute("category",category);
        return "bookData";
    }

    /**
     * 图书列表页
     */
    @RequestMapping("/bookList")
    public String bookList(String category, Model model){
        model.addAttribute("category",category);
        return "books_list";
    }

    /**
     * 获取图书列表数据
     */
    @RequestMapping("/getBookListData")
    public String getBookListData(String category,Integer page, Integer pageSize, Model model){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category",category);
        IPage<Book> iPage = bookService.page(new Page<Book>(page,pageSize),queryWrapper);
        model.addAttribute("bookList",iPage.getRecords());
        model.addAttribute("pre",iPage.getCurrent() - 1);
        model.addAttribute("next",iPage.getCurrent() + 1);
        model.addAttribute("cur",iPage.getCurrent());
        model.addAttribute("pages",iPage.getPages());
        model.addAttribute("category",category);
        model.addAttribute("pageSize",pageSize);
        return "booksListData";
    }

    /**
     * 图书详情
     */
    @RequestMapping("/detail")
    public String detail(Integer id,Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        return "details";
    }

}
