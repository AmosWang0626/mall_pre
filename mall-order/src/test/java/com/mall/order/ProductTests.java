package com.mall.order;

import com.mall.order.core.service.ProductService;
import com.mall.order.pojo.form.ProductForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 模块名称: mall
 * 模块描述: 商品
 *
 * @author amos.wang
 * @date 2020/4/22 16:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTests {

    @Resource
    private ProductService productService;

    @Test
    public void save() {
        System.out.println(productService.save(new ProductForm()
                .setProductNo("PROD20200422225715507993d376bc38")
                .setName("劳斯莱斯").setUnitPrice(new BigDecimal("10000"))
                .setDescription("Rolls-Royce 劳斯莱斯幻影，世界上最安静的车")));

        System.out.println(productService.save(new ProductForm()
                .setProductNo("PROD20200422225716fd77330c101bc4")
                .setName("莱肯超级跑车").setUnitPrice(new BigDecimal("2000"))
                .setDescription("W Motors 莱肯超级跑车，第一个位于中东的高性能豪华跑车的开发商")));
    }

    @Test
    public void getByProductNo() {
        System.out.println(productService.getByProductNo("PROD20200422225716fd77330c101bc4"));
    }

    @Test
    public void list() {
        System.out.println(productService.list(null));
    }

}
