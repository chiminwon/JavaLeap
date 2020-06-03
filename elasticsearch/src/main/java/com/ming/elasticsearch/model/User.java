package com.ming.elasticsearch.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "userindex", type = "user")
@Data
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createtm;
}
