package cn.zwz.order.serviceimpl;

import cn.zwz.order.mapper.NewsMapper;
import cn.zwz.order.entity.News;
import cn.zwz.order.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品资讯 服务层接口实现
 * @author 
 */
@Slf4j
@Service
@Transactional
public class INewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    private NewsMapper newsMapper;
}
