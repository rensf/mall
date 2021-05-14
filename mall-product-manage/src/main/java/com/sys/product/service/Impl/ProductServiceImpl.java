package com.sys.product.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.product.config.MyPropsConfig;
import com.sys.product.entity.BrProductType;
import com.sys.product.entity.Product;
import com.sys.product.entity.ProductImage;
import com.sys.product.mapper.BrProductTypeMapper;
import com.sys.product.mapper.ProductImageMapper;
import com.sys.product.mapper.ProductMapper;
import com.sys.product.service.IProductService;
import com.sys.product.util.GenerateID;
import com.sys.product.util.ToJson;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rensf
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    private ProductMapper productMapper;
    private BrProductTypeMapper brProductTypeMapper;
    private ProductImageMapper productImageMapper;
    private MyPropsConfig props;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, BrProductTypeMapper brProductTypeMapper, ProductImageMapper productImageMapper, MyPropsConfig props) {
        this.productMapper = productMapper;
        this.brProductTypeMapper = brProductTypeMapper;
        this.productImageMapper = productImageMapper;
        this.props = props;
    }

    @Override
    public IPage<Product> queryProductListByPage(Map param) {
        JSONObject jsonParam = ToJson.toJson(param);
        Page<Product> page = new Page<>();
        page.setCurrent(jsonParam.getLong("current"));
        page.setTotal(jsonParam.getLong("total"));
        page.setSize(jsonParam.getLong("size"));
        return productMapper.queryProductListByPage(page, jsonParam);
    }

    @Override
    public List<Product> queryProductList() {
        return null;
    }

    @Override
    public Integer addProduct(Product product) {
        product.setProductId(GenerateID.generateID());
        insertBrProductType(product);
        insertProductImage(product);
        return productMapper.insert(product);
    }

    @Override
    public Integer updateProduct(Product product) {
        insertBrProductType(product);
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

    private void insertBrProductType(Product product) {
        Map cond = new HashMap();
        cond.put("product_id", product.getProductId());
        brProductTypeMapper.deleteByMap(cond);
        String[] typeIds = product.getTypeIds();
        for (String typeId : typeIds) {
            BrProductType brProductType = new BrProductType();
            brProductType.setProductTypeId(GenerateID.generateID());
            brProductType.setProductId(product.getProductId());
            brProductType.setProductTypeId(typeId);
            brProductTypeMapper.insert(brProductType);
        }
    }

    private void insertProductImage(Product product) {
        Map cond = new HashMap();
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
