package com.demo.springboot.web.controller;

import com.alibaba.excel.EasyExcel;
import com.demo.springboot.web.entity.Product;
import com.demo.springboot.web.listener.ExcelListener;
import com.demo.springboot.web.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.netty.http.server.HttpServerResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试excelcontroller
 * web中读取；
 * @date 2021/1/13 19:49
 * @see
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {


    @Autowired
    private ProductMapper productMapper;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 上传excel，读取，入库
     * @date 2021/1/13 19:52
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {

        EasyExcel.read(file.getInputStream(), Product.class, new ExcelListener(productMapper)).sheet().doRead();

        return "success";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description excel下载，excel写
     * @date 2021/1/13 20:21
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 1.准备数据；
        List<Product> list = productMapper.getProductInfo("");
        // 2.导出excel
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("easyExcel导出测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Product.class).sheet("商品信息").doWrite(list);
    }

    @GetMapping("/index")
    public String index() {
        return "/excel/excelIndex";
    }
}
