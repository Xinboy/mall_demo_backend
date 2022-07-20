package com.xinbo.mall_demo.model.mongodb.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 会员浏览记录
 * @author xinbo
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadHistory {
    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-mm-dd HH:mm:ss")
    private Date createTime;
}
