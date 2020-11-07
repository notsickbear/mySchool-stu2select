package com.h.stu2select.dao;

import com.h.stu2select.entity.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorDao extends JpaRepository<Tutor, Integer> {
    /**
     * 根据研究方向的匹配数量排序，并且实现分页
     * Hql 不支持 FROM 嵌套查询 ，所以这里使用了原生 SQL, 使用 CreateSQLQuery() 方法似乎也可以解决该问题
     */
    @Query(value = "SELECT `id`, `no`, `name`, major_id, select_state, area1_id, area2_id, area3_id, num_limit " +
            "FROM (SELECT `id`, `no`, `name`, major_id, select_state, area1_id, area2_id, area3_id, num_limit, " +
            "(CASE WHEN area1_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END)+" +
            "(CASE WHEN area2_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END)+" +
            "(CASE WHEN area3_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END) meet " +
            "FROM tutor WHERE select_state=:select_state ORDER BY meet DESC) AS smart",
            nativeQuery = true)
    Page<Tutor> getSmartSortTutor(@Param("area1_id") Integer area1_id,
                                  @Param("area2_id") Integer area2_id,
                                  @Param("area3_id") Integer area3_id,
                                  @Param("select_state") Integer select_state,
                                  Pageable of);
}
