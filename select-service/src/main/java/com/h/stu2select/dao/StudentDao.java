package com.h.stu2select.dao;

import com.h.stu2select.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDao extends JpaRepository<Student, Integer> {
    /**
     * 根据研究方向的匹配数量排序，并且实现分页
     * Hql 不支持 FROM 嵌套查询 ，所以这里使用了原生 SQL, 使用 CreateSQLQuery() 方法似乎也可以解决该问题
     */
    @Query(value = "SELECT `id`, `no`, `name`, `period`, major_id, select_state, area1_id, area2_id, area3_id, tutor_id " +
            "FROM (SELECT `id`, `no`, `name`, `period`, major_id, select_state, area1_id, area2_id, area3_id, tutor_id, " +
            "(CASE WHEN area1_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END)+" +
            "(CASE WHEN area2_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END)+" +
            "(CASE WHEN area3_id IN (:area1_id, :area2_id, :area3_id) THEN 1 ELSE 0 END) meet " +
            "FROM student WHERE select_state=:select_state AND `period`=:period " +
            "ORDER BY meet DESC) AS smart",
            nativeQuery = true)
    Page<Student> getSmartSortStudent(@Param("area1_id") Integer area1_id,
                                      @Param("area2_id") Integer area2_id,
                                      @Param("area3_id") Integer area3_id,
                                      @Param("period") Integer period,
                                      @Param("select_state") Integer select_state,
                                      Pageable of);

    @Query(value = "SELECT stu.`id`, stu.`no`, stu.`name`, stu.`period`, stu.major_id, stu.select_state, stu.area1_id, stu.area2_id, stu.area3_id, stu.tutor_id " +
            "FROM student stu, select_state ss  WHERE stu.id = ss.student_id AND stu.period=:period AND ss.select_state=0 AND :id in (ss.tutor1_id, ss.tutor2_id, ss.tutor3_id)",
            nativeQuery = true)
    Page<Student> getEnableStudent(Pageable of, @Param("id") Integer id, @Param("period") Integer period);
}
