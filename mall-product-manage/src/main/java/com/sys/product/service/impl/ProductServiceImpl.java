package com.sys.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.utils.GenerateID;
import com.sys.common.utils.JsonUtils;
import com.sys.product.config.MyPropsConfig;
import com.sys.product.entity.Product;
import com.sys.product.entity.ProductImage;
import com.sys.product.entity.ProductProductType;
import com.sys.product.mapper.ProductImageMapper;
import com.sys.product.mapper.ProductMapper;
import com.sys.product.mapper.ProductProductTypeMapper;
import com.sys.product.service.IProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

/**
 * @author rensf
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductProductTypeMapper productProductTypeMapper;
    @Resource
    private ProductImageMapper productImageMapper;
    @Resource
    private MyPropsConfig props;

    @Override
    public IPage<Product> queryProductListByPage(Map<String, Object> param) {
        JSONObject jsonParam = JsonUtils.mapToJson(param);
        Page<Product> page = new Page<>();
        if (Objects.nonNull(jsonParam.getLong("current")) && Objects.nonNull(jsonParam.getLong("total")) && Objects.nonNull(jsonParam.getLong("size"))) {
            page.setCurrent(jsonParam.getLong("current"));
            page.setTotal(jsonParam.getLong("total"));
            page.setSize(jsonParam.getLong("size"));
        }
        return productMapper.queryProductListByPage(page, jsonParam);
    }

    @Override
    public List<Product> queryProductList() {
        return null;
    }

    @Override
    public Integer addProduct(Product product) {
        product.setProductId(GenerateID.generateID());
        insertProductProductType(product);
        insertProductImage(product);
        return productMapper.insert(product);
    }

    @Override
    public Integer updateProduct(Product product) {
        insertProductProductType(product);
        insertProductImage(product);
        return productMapper.updateById(product);
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
    public String uploadProductImage(MultipartFile image) throws IOException {
        String imageId = GenerateID.generateID();
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
        String[] typeIds = product.getTypeIds();
        for (String typeId : typeIds) {
            ProductProductType productProductType = new ProductProductType();
            productProductType.setProductTypeId(GenerateID.generateID());
            productProductType.setProductId(product.getProductId());
            productProductType.setProductTypeId(typeId);
            productProductTypeMapper.insert(productProductType);
        }
    }

    private void insertProductImage(Product product) {
        Map<String, Object> cond = new HashMap<>();
        cond.put("product_id", product.getProductId());
        productImageMapper.deleteByMap(cond);
        String[] images = product.getImages();
        for (String image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setProductImageId(GenerateID.generateID());
            productImage.setProductId(product.getProductId());
            productImage.setProductImage(image);
            productImageMapper.insert(productImage);
        }

        String[] homeImages = product.getHomeImages();
        for (String homeImage : homeImages) {
            ProductImage productImage = new ProductImage();
            productImage.setProductImageId(GenerateID.generateID());
            productImage.setProductId(product.getProductId());
            productImage.setProductImage(homeImage);
            productImage.setImagePosition("home");
            productImageMapper.insert(productImage);
        }
    }

}
