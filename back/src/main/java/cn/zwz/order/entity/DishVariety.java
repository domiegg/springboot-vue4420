package cn.zwz.order.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_dish_variety")
@TableName("a_dish_variety")
@ApiModel(value = "药品")
public class DishVariety extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

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

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "是否收藏")
    private Long collectFlag;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "加购数量")
    private BigDecimal buyNumber;
}
