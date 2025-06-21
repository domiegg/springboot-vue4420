package cn.zwz.order.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_dish_collect")
@TableName("a_dish_collect")
@ApiModel(value = "药品收藏")
public class DishCollect extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "药品ID")
    private String dishId;

    @ApiModelProperty(value = "药品名称")
    private String title;

    @ApiModelProperty(value = "药品类型")
    private String type;

    @ApiModelProperty(value = "药品介绍")
    private String content;

    @ApiModelProperty(value = "药品图片")
    private String image;

    @ApiModelProperty(value = "药品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "收藏人ID")
    private String collectId;

    @ApiModelProperty(value = "收藏人")
    private String collectName;

    @ApiModelProperty(value = "收藏时间")
    private String collectTime;
}
