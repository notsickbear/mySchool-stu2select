package com.h.stu2select.sevice;

import com.h.stu2select.dao.TutorDao;
import com.h.stu2select.entity.Student;
import com.h.stu2select.entity.Tutor;
import com.h.stu2select.myEnum.SelectStateEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TutorService {
    @Resource
    TutorDao tutorDao;
    @Resource
    StudentService studentService;
    @Resource
    ResearchAreaService researchAreaService;
    @Resource
    SelectStateService selectStateService;

    public Page<Tutor> getAllTutor(PageRequest of) {
        return tutorDao.findAll(of);
    }

    public void setTutor(Tutor tutor) {
        tutorDao.save(tutor);
    }

    public Optional<Tutor> getTutorById(Integer id) {
        return tutorDao.findById(id);
    }

    public boolean deleteTutorById(Integer id) {
        if (!tutorDao.existsById(id)) return false;
        else {
            tutorDao.deleteById(id);
            return true;
        }
    }

    public Page<Tutor> getAllEnableTutor(PageRequest of) {
        Tutor tutor = new Tutor();
        tutor.setSelectState(SelectStateEnum.enable.ordinal());
        return tutorDao.findAll(Example.of(tutor), of);
    }

    public Page<Tutor> getSmartSortTutor(PageRequest of, Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(value -> tutorDao.getSmartSortTutor(value.getArea1().getId(),
                value.getArea2().getId(), value.getArea3().getId(),
                SelectStateEnum.enable.ordinal(), of)).orElse(null);
    }

    /**
     * 返回是否已满，满返回 false，不满返回 true
     */
    public boolean checkNumLimit(Tutor tutor, Integer count) {
        return !(tutor != null && count.equals(tutor.getNumLimit()));
    }

    public Page<Student> getEnableStudent(PageRequest of, Integer id, Integer period) {
        return studentService.getEnableStudent(of, id, period);
    }

    public Page<Student> getAllEnableStudent(PageRequest of, Integer period) {
        return studentService.getAllEnableStudent(of, period);
    }
}
