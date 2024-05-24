package com.sys.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.util.IDUtils;
import com.sys.stock.entity.Stock;
import com.sys.stock.mapper.StockMapper;
import com.sys.stock.service.IStockService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存 服务层
 *
 * @author rensf
 * @date 2024/5/22
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Override
    public List<Stock> querySpecList(String productId) {
        // 查询产品规格列表
        List<Stock> productSpecList = this.baseMapper.querySpecsByProductId(productId);
        // 获取ID和规格的笛卡尔积组合
        List<String> idComb = cartesian(productSpecList.stream().map(Stock::getProductAttrIdList).collect(Collectors.toList()), "-");
        List<String> specComb = cartesian(productSpecList.stream().map(Stock::getProductSpecList).collect(Collectors.toList()), "，");
        // 重新组织数据
        List<Stock> productSpecs = new ArrayList<>();
        for (int i = 0; i < idComb.size(); i++) {
            Stock stock = new Stock();
            stock.setStockId(IDUtils.generateID());
            stock.setProductId(productId);
            stock.setProductAttrIds(idComb.get(i));
            stock.setStockSpecs(specComb.get(i));
            productSpecs.add(stock);
        }
        return productSpecs;
    }

    /**
     * 计算笛卡尔积
     * @param list 列表
     * @param separator 分隔符
     * @return 笛卡尔积
     */
    private List<String> cartesian(List<List<String>> list, String separator) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<String> result = list.remove(0);
        for (List<String> strings : list) {
            List<String> temp = new ArrayList<>();
            for (String r : result) {
                for (String s : strings) {
                    temp.add(r + separator + s);
                }
            }
            result = temp;
        }
        return result;
    }

}
