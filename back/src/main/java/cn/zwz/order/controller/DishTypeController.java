package cn.zwz.order.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.order.entity.DishType;
import cn.zwz.order.service.IDishTypeService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 * 
 */
@Slf4j
@RestController
@Api(tags = "药品类型管理接口")
@RequestMapping("/zwz/dishType")
@Transactional
public class DishTypeController {

    @Autowired
    private IDishTypeService iDishTypeService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条药品类型")
    public Result<DishType> get(@RequestParam String id){
        return new ResultUtil<DishType>().setData(iDishTypeService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品类型个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iDishTypeService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品类型")
    public Result<List<DishType>> getAll(){
        return new ResultUtil<List<DishType>>().setData(iDishTypeService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询药品类型")
    public Result<IPage<DishType>> getByPage(@ModelAttribute DishType dishType ,@ModelAttribute PageVo page){
        QueryWrapper<DishType> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(dishType.getTitle())) {
            qw.like("title",dishType.getTitle());
        }
        if(!ZwzNullUtils.isNull(dishType.getStatus())) {
            qw.eq("status",dishType.getStatus());
        }
        IPage<DishType> data = iDishTypeService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<DishType>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改药品类型")
    public Result<DishType> saveOrUpdate(DishType dishType){
        if(iDishTypeService.saveOrUpdate(dishType)){
            return new ResultUtil<DishType>().setData(dishType);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增药品类型")
    public Result<DishType> insert(DishType dishType){
        if(Objects.equals(0,dishType.getSortOrder().compareTo(BigDecimal.ZERO))) {
            dishType.setSortOrder(BigDecimal.valueOf(iDishTypeService.count() + 1L));
        }
        iDishTypeService.saveOrUpdate(dishType);
        return new ResultUtil<DishType>().setData(dishType);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑药品类型")
    public Result<DishType> update(DishType dishType){
        iDishTypeService.saveOrUpdate(dishType);
        return new ResultUtil<DishType>().setData(dishType);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品类型")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iDishTypeService.removeById(id);
        }
        return ResultUtil.success();
    }
}
