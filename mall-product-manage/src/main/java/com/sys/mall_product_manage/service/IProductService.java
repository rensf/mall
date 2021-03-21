package com.sys.mall_product_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.mall_product_manage.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IProductService extends IService<Product> {

    IPage<Product> queryProductListByPage(Page page);

    List<Product> queryProductList();

    Integer addProduct(Product product);

    Integer updateProduct(Product product);

    void viewProductImage(String imageName, HttpServletResponse response) throws IOException;

    String uploadProductImage(MultipartFile file) throws IOException;

}
