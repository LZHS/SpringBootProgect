package com.lzhs.data_object;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Description: 类目表实体类 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/7 : 6:11 PM<br/>
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private int categoryType;

    /**
     * 该记录创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date updateTime;

}
