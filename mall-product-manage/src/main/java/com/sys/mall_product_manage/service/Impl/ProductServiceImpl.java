package com.sys.mall_product_manage.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.mall_product_manage.config.MyPropsConfig;
import com.sys.mall_product_manage.entity.Product;
import com.sys.mall_product_manage.entity.ProductImage;
import com.sys.mall_product_manage.entity.RProductType;
import com.sys.mall_product_manage.mapper.ProductImageMapper;
import com.sys.mall_product_manage.mapper.ProductMapper;
import com.sys.mall_product_manage.mapper.RProductTypeMapper;
import com.sys.mall_product_manage.service.IProductService;
import com.sys.mall_product_manage.util.GenerateID;
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

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private RProductTypeMapper rProductTypeMapper;

    @Resource
    private ProductImageMapper productImageMapper;

    @Resource
    private MyPropsConfig props;

    @Override
    public IPage<Product> queryProductListByPage(Page page) {
        IPage<Product> products = productMapper.queryProductListByPage(page);
        return products;
    }

    @Override
    public List<Product> queryProductList() {
        return null;
    }

    @Override
    public Integer addProduct(Product product) {
        product.setProductId(GenerateID.generateID());
        insertRProductType(product);
        insertProductImage(product);
        return productMapper.insert(product);
    }

    @Override
    public Integer updateProduct(Product product) {
        insertRProductType(product);
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
        FileOutputStream fileOutputStream = new FileOutputStream(newFile + "/" + newName );
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();
        return newName;
    }

    private void insertRProductType(Product product) {
        Map cond = new HashMap();
        cond.put("product_id", product.getProductId());
        rProductTypeMapper.deleteByMap(cond);
        String[] typeIds = product.getTypeIds();
        for (String typeId : typeIds) {
            RProductType rProductType = new RProductType();
            rProductType.setProductTypeId(GenerateID.generateID());
            rProductType.setProductId(product.getProductId());
            rProductType.setProductTypeId(typeId);
            rProductTypeMapper.insert(rProductType);
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
