package cn.zwz.order.serviceimpl;

import cn.zwz.order.mapper.DishCollectMapper;
import cn.zwz.order.entity.DishCollect;
import cn.zwz.order.service.IDishCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品收藏 服务层接口实现
 * @author 
 */
@Slf4j
@Service
@Transactional
public class IDishCollectServiceImpl extends ServiceImpl<DishCollectMapper, DishCollect> implements IDishCollectService {

    @Autowired
    private DishCollectMapper dishCollectMapper;
}
