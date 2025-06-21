package cn.zwz.order.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.order.entity.News;
import cn.zwz.order.service.INewsService;
import cn.hutool.core.util.StrUtil;
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
@Api(tags = "药品资讯管理接口")
@RequestMapping("/zwz/news")
@Transactional
public class NewsController {

    @Autowired
    private INewsService iNewsService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条药品资讯")
    public Result<News> get(@RequestParam String id){
        return new ResultUtil<News>().setData(iNewsService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品资讯个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iNewsService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品资讯")
    public Result<List<News>> getAll(){
        return new ResultUtil<List<News>>().setData(iNewsService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询药品资讯")
    public Result<IPage<News>> getByPage(@ModelAttribute News news ,@ModelAttribute PageVo page){
        QueryWrapper<News> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(news.getTitle())) {
            qw.like("title",news.getTitle());
        }
        if(!ZwzNullUtils.isNull(news.getStatus())) {
            qw.eq("status",news.getStatus());
        }
        if(!ZwzNullUtils.isNull(news.getUserName())) {
            qw.like("user_name",news.getUserName());
        }
        IPage<News> data = iNewsService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<News>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改药品资讯")
    public Result<News> saveOrUpdate(News news){
        if(iNewsService.saveOrUpdate(news)){
            return new ResultUtil<News>().setData(news);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增药品资讯")
    public Result<News> insert(News news){
        User currUser = securityUtil.getCurrUser();
        news.setUserName(currUser.getNickname());
        news.setTime(DateUtil.now());
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑药品资讯")
    public Result<News> update(News news){
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品资讯")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iNewsService.removeById(id);
        }
        return ResultUtil.success();
    }
}
