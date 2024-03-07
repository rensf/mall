package com.sys.product.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.util.IDUtils;
import com.sys.product.config.MyPropsConfig;
import com.sys.product.entity.Product;
import com.sys.product.entity.ProductAttr;
import com.sys.product.entity.ProductImage;
import com.sys.product.entity.ProductProductType;
import com.sys.product.mapper.ProductImageMapper;
import com.sys.product.mapper.ProductMapper;
import com.sys.product.mapper.ProductProductTypeMapper;
import com.sys.product.service.IProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品 服务层
 *
 * @author rensf
 * @date 2021/3/26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductProductTypeMapper productProductTypeMapper;
    @Resource
    private ProductImageMapper productImageMapper;
    @Resource
    private MyPropsConfig props;

    @Override
    public IPage<Product> queryProductListByPage(Page<Product> page, Product product) {
        return this.baseMapper.queryProductListByPage(page, product);
    }

    @Override
    public Product queryProductById(String productId) {
        return this.baseMapper.queryProductById(productId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean addProduct(Product product) {
        product.setProductId(IDUtils.generateID());
        insertProductProductType(product);
        insertProductImage(product);
        return this.save(product);
    }

    @Override
    public Boolean delProduct(String productId) {
        return this.removeById(productId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean updateProduct(Product product) {
        insertProductProductType(product);
        insertProductImage(product);
        return this.updateById(product);
    }

    @Override
    public void viewProductImage(String imageName, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(props.getFilepath() + imageName);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(fileInputStream, outputStream);
        outputStream.close();
        fileInputStream.close();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String uploadProductImage(MultipartFile image) throws IOException {
        String imageId = IDUtils.generateID();
        String imageName = image.getOriginalFilename();
        String imageType = imageName.substring(imageName.indexOf("."));
        String newName = imageId + imageType;
        File newFile = new File(props.getFilepath());
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(newFile + "/" + newName);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();
        return newName;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteProductImage(String imageName) {
        File file = new File(props.getFilepath() + imageName);
        file.delete();
        UpdateWrapper<ProductImage> uw = new UpdateWrapper<>();
        uw.eq("product_image", imageName);
        ProductImage productImage = new ProductImage();
        productImage.setFlag(0);
        return productImageMapper.update(productImage, uw);
    }

    private void insertProductProductType(Product product) {
        Map<String, Object> cond = new HashMap<>();
        cond.put("product_id", product.getProductId());
        productProductTypeMapper.deleteByMap(cond);
        List<String> typeIds = product.getTypeIds();
        for (String typeId : typeIds) {
            ProductProductType productProductType = new ProductProductType();
            productProductType.setProductTypeId(IDUtils.generateID());
            productProductType.setProductId(product.getProductId());
            productProductType.setProductTypeId(typeId);
            productProductTypeMapper.insert(productProductType);
        }
    }

    private void insertProductImage(Product product) {
        Map<String, Object> cond = new HashMap<>();
        cond.put("product_id", product.getProductId());
        productImageMapper.deleteByMap(cond);
        List<String> images = product.getImages();
        for (String image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setProductImageId(IDUtils.generateID());
            productImage.setProductId(product.getProductId());
            productImage.setProductImage(image);
            productImageMapper.insert(productImage);
        }

        List<String> homeImages = product.getHomeImages();
        for (String homeImage : homeImages) {
            ProductImage productImage = new ProductImage();
            productImage.setProductImageId(IDUtils.generateID());
            productImage.setProductId(product.getProductId());
            productImage.setProductImage(homeImage);
            productImage.setImagePosition("home");
            productImageMapper.insert(productImage);
        }
    }

}
