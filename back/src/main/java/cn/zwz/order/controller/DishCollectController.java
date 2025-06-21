package cn.zwz.order.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.order.entity.DishCollect;
import cn.zwz.order.entity.DishVariety;
import cn.zwz.order.service.IDishCollectService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.order.service.IDishVarietyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 *
 */
@Slf4j
@RestController
@Api(tags = "药品收藏管理接口")
@RequestMapping("/zwz/dishCollect")
@Transactional
public class DishCollectController {

    @Autowired
    private IDishCollectService iDishCollectService;

    @Autowired
    private IDishVarietyService iDishVarietyService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/addOne", method = RequestMethod.GET)
    @ApiOperation(value = "新增收藏")
    public Result<DishCollect> addOne(@RequestParam String id){
        DishVariety dish = iDishVarietyService.getById(id);
        if(dish == null) {
            return ResultUtil.error("药品不存在");
        }
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<DishCollect> qw = new QueryWrapper<>();
        qw.eq("dish_id",id);
        qw.eq("collect_id",currUser.getId());
        if(iDishCollectService.count(qw) > 0L) {
            return ResultUtil.success();
        }
        DishCollect dishCollect = new DishCollect();
        dishCollect.setDishId(dish.getId());
        dishCollect.setTitle(dish.getTitle());
        dishCollect.setType(dish.getType());
        dishCollect.setContent(dish.getContent());
        dishCollect.setImage(dish.getImage());
        dishCollect.setPrice(dish.getPrice());
        dishCollect.setCollectId(currUser.getId());
        dishCollect.setCollectName(currUser.getNickname());
        dishCollect.setCollectTime(DateUtil.now());
        iDishCollectService.saveOrUpdate(dishCollect);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/cancelOne", method = RequestMethod.GET)
    @ApiOperation(value = "取消收藏")
    public Result<DishCollect> cancelOne(@RequestParam String id){
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<DishCollect> qw = new QueryWrapper<>();
        qw.eq("dish_id",id);
        qw.eq("collect_id",currUser.getId());
        iDishCollectService.remove(qw);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条药品收藏")
    public Result<DishCollect> get(@RequestParam String id){
        return new ResultUtil<DishCollect>().setData(iDishCollectService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品收藏个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iDishCollectService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品收藏")
    public Result<List<DishCollect>> getAll(){
        return new ResultUtil<List<DishCollect>>().setData(iDishCollectService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询药品收藏")
    public Result<IPage<DishCollect>> getByPage(@ModelAttribute DishCollect dishCollect ,@ModelAttribute PageVo page){
        QueryWrapper<DishCollect> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        qw.eq("collect_id",currUser.getId());
        if(!ZwzNullUtils.isNull(dishCollect.getTitle())) {
            qw.like("title",dishCollect.getTitle());
        }
        if(!ZwzNullUtils.isNull(dishCollect.getCollectName())) {
            qw.like("collect_name",dishCollect.getCollectName());
        }
        IPage<DishCollect> data = iDishCollectService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<DishCollect>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改药品收藏")
    public Result<DishCollect> saveOrUpdate(DishCollect dishCollect){
        if(iDishCollectService.saveOrUpdate(dishCollect)){
            return new ResultUtil<DishCollect>().setData(dishCollect);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增药品收藏")
    public Result<DishCollect> insert(DishCollect dishCollect){
        DishVariety dish = iDishVarietyService.getById(dishCollect.getDishId());
        if(dish == null) {
            return ResultUtil.error("药品不存在");
        }
        dishCollect.setTitle(dish.getTitle());
        dishCollect.setType(dish.getType());
        dishCollect.setContent(dish.getContent());
        dishCollect.setImage(dish.getImage());
        dishCollect.setPrice(dish.getPrice());
        User currUser = securityUtil.getCurrUser();
        dishCollect.setCollectId(currUser.getId());
        dishCollect.setCollectName(currUser.getNickname());
        dishCollect.setCollectTime(DateUtil.now());
        iDishCollectService.saveOrUpdate(dishCollect);
        return new ResultUtil<DishCollect>().setData(dishCollect);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑药品收藏")
    public Result<DishCollect> update(DishCollect dishCollect){
        iDishCollectService.saveOrUpdate(dishCollect);
        return new ResultUtil<DishCollect>().setData(dishCollect);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品收藏")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iDishCollectService.removeById(id);
        }
        return ResultUtil.success();
    }
}
