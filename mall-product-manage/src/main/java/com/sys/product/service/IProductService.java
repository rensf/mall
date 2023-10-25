package com.sys.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 产品 服务接口
 * @author rensf
 * @date 2021/3/26
 */
public interface IProductService extends IService<Product> {

    /**
     * 分页查询产品列表
     *
     * @param page 分页参数
     * @param product 产品参数
     * @return 产品分页列表
     */
    IPage<Product> queryProductListByPage(Page<Product> page, Product product);

    /**
     * 查询产品列表
     * @return
     */
    List<Product> queryProductList();

    /**
     * 添加产品
     * @param product
     * @return
     */
    Integer addProduct(Product product);

    /**
     * 更新产品信息
     * @param product
     * @return
     */
    Integer updateProduct(Product product);

    /**
     * 预览产品图片
     * @param imageName
     * @param response
     * @throws IOException
     */
    void viewProductImage(String imageName, HttpServletResponse response) throws IOException;

    /**
     * 上传产品图片
     * @param image
     * @return
     * @throws IOException
     */
    String uploadProductImage(MultipartFile image) throws IOException;

    /**
     * 删除产品图片
     * @param imageName
     * @return
     */
    Integer deleteProductImage(String imageName);

}
