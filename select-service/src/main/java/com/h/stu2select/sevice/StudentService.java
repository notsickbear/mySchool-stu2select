package com.h.stu2select.sevice;

import com.h.stu2select.dao.StudentDao;
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
public class StudentService {
    private final int AREA_SIZE = 3;
    @Resource
    StudentDao studentDao;
    @Resource
    TutorService tutorService;

    public Page<Student> getAllStudent(PageRequest pageRequest) {
        return studentDao.findAll(pageRequest);
    }

    public void setStudent(Student student) {
        studentDao.save(student);
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentDao.findById(id);
    }

    public boolean deleteStudentById(Integer id) {
        if (studentDao.existsById(id)) return false;
        else {
            studentDao.deleteById(id);
            return true;
        }
    }

/*    public Page<Student> getAllEnableStudent(PageRequest of) {
        Student student = new Student();
        student.setSelectState(SelectStateEnum.enable.ordinal());
        return studentDao.findAll(Example.of(student), of);
    }*/

    public Page<Student> getAllStudentByTutorId(PageRequest of, Integer id) {
        Tutor tutor = new Tutor();
        tutor.setId(id);
        Student student = new Student();
        student.setTutor(tutor);
        return studentDao.findAll(Example.of(student), of);
    }

    public Page<Student> getAllStudentByTutorIdAndPeriod(PageRequest of, Integer id, Integer period) {
        Tutor tutor = new Tutor();
        tutor.setId(id);
        Student student = new Student();
        student.setTutor(tutor);
        student.setPeriod(period);
        return studentDao.findAll(Example.of(student), of);
    }

    public Page<Student> getSmartSortStudent(PageRequest of, Integer id, Integer period) {
        Optional<Tutor> tutor = tutorService.getTutorById(id);
        return tutor.map(value -> studentDao.getSmartSortStudent(value.getArea1().getId(),
                value.getArea2().getId(), value.getArea3().getId(),
                period, SelectStateEnum.enable.ordinal(), of)).orElse(null);
    }

    public Integer countAllStudentByTutorIdAndPeriod(Integer id, Integer period) {
        Tutor tutor = new Tutor();
        tutor.setId(id);
        Student student = new Student();
        student.setTutor(tutor);
        student.setPeriod(period);
        return (int) studentDao.count(Example.of(student));
    }

    public void checkAndSetFinalTutor(Student student, Tutor tutor) {
        if (student != null && student.getId() != null) {
            Optional<Student> student1 = studentDao.findById(student.getId());
            if (student1.isPresent() && student1.get().getSelectState() == SelectStateEnum.enable.ordinal()) {
                student1.get().setTutor(tutor);
                student1.get().setSelectState(SelectStateEnum.disable.ordinal());
                studentDao.save(student1.get());
            }
        }
    }

    public Page<Student> getEnableStudent(PageRequest of, Integer id, Integer period) {
        return studentDao.getEnableStudent(of, id, period);
    }

    public Page<Student> getAllEnableStudent(PageRequest of, Integer period) {
        Student student = new Student();
        student.setPeriod(period);
        student.setSelectState(SelectStateEnum.enable.ordinal());
        return studentDao.findAll(Example.of(student), of);
    }
}
