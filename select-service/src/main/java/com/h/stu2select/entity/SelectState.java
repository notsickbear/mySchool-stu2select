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
@DynamicInsert
public class SelectState {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer period; // 第几届
    private Integer selectState; // 选择情况，0未完成1已完成
    @OneToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToOne(fetch = FetchType.LAZY)
    private Tutor tutor1;
    @OneToOne(fetch = FetchType.LAZY)
    private Tutor tutor2;
    @OneToOne(fetch = FetchType.LAZY)
    private Tutor tutor3;
    @OneToOne(fetch = FetchType.LAZY)
    private Tutor finalTutor;
}
