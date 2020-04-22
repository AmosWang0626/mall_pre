package com.mall.order.web.controller;

import com.mall.common.base.GenericResponse;
import com.mall.order.core.service.ProductService;
import com.mall.order.pojo.form.ProductForm;
import com.mall.order.pojo.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 商品
 *
 * @author amos.wang
 * @date 2020/4/22 16:15
 */
@RestController
@RequestMapping("product")
@Api(tags = "B01-商品相关")
public class ProductController {

    @Resource
    private ProductService productService;


    @PostMapping
    @ApiOperation("创建商品")
    public GenericResponse<ProductVO> create(@RequestBody ProductForm form) {
        return productService.save(form);
    }

    @PutMapping
    @ApiOperation("修改商品")
    public GenericResponse<ProductVO> update(@RequestBody ProductForm form) {
        return productService.save(form);
    }

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productNo", value = "商品编号", required = true,
                    dataType = "String", paramType = "query", example = "PROD20200422225715507993d376bc38")
    })
    @GetMapping
    @ApiOperation("根据商品编号获取商品")
    public GenericResponse<ProductVO> get(String productNo) {
        return new GenericResponse<>(productService.getByProductNo(productNo));
    }

    @GetMapping("list")
    @ApiOperation("获取全部商品")
    public GenericResponse<List<ProductVO>> list() {
        return new GenericResponse<>(productService.list(null));
    }

    @DeleteMapping
    @ApiOperation("根据商品编号删除商品")
    public GenericResponse<String> delete(String productNo) {
        return productService.delete(productNo);
    }

}
