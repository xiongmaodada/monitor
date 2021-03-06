package monitor.view.domain.entity;

import java.util.Date;

/**
 * Created by lizhitao on 2018/1/17.
 * 产品实体
 */
public class Product {
    private Integer id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品负责人
     */
    private String owner;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime = new Date(System.currentTimeMillis());

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
