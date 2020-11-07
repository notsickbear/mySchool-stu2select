package com.h.stu2select.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer"})
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
public class Tutor {
    /* 基础信息 */
    @Id
    @GeneratedValue
    private Integer id;
    private Integer no;
    private String name;
    @ManyToOne
    private Major major;
    /* 选课信息 */
    private Integer numLimit;
    private Integer selectState; // 选择情况，0未完成1已完成
    @OneToOne(fetch = FetchType.LAZY)
    private ResearchArea area1;
    @OneToOne(fetch = FetchType.LAZY)
    private ResearchArea area2;
    @OneToOne(fetch = FetchType.LAZY)
    private ResearchArea area3;
}
