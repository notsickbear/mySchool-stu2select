package com.h.stu2select.sevice;

import com.h.stu2select.dao.SelectStateDao;
import com.h.stu2select.entity.SelectState;
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
public class SelectStateService {
    @Resource
    SelectStateDao selectStateDao;
    @Resource
    StudentService studentService;
    @Resource
    TutorService tutorService;

    public Page<SelectState> getAllSelectState(PageRequest pageRequest) {
        return selectStateDao.findAll(pageRequest);
    }

    /**
     * FinalTutor被設置時，檢查導師名額是否已滿(即状态是否为 disable)，
     * 如果不滿，保存 selectState 並在對應學生列添加導師信息和修改选择状态
     * 并在次检测是否已满，已满改变导师选择状态
     * 如果滿，不進行保存並返回 false
     * 其他情況自動保存
     */
    public Boolean setSelectState(SelectState selectState) {
        if (selectState != null) {
            // FinalTutor被設置時
            if (selectState.getFinalTutor() != null) {
                Optional<Tutor> tutor = tutorService.getTutorById(selectState.getFinalTutor().getId());
                // 檢查導師名額是否已滿
                if (tutor.isPresent() && tutor.get().getSelectState() == SelectStateEnum.enable.ordinal()) {
                    studentService.checkAndSetFinalTutor(selectState.getStudent(), selectState.getFinalTutor());
                    selectStateDao.save(selectState);
                    // 在次检测是否已满
                    if (!tutorService.checkNumLimit(tutor.get(),
                            studentService.countAllStudentByTutorIdAndPeriod(
                                    tutor.get().getId(), selectState.getPeriod()))) {
                        tutor.get().setSelectState(SelectStateEnum.disable.ordinal());
                        tutorService.setTutor(tutor.get());
                    }
                    return true;
                } else return false;
            } else {
                selectStateDao.save(selectState);
                return true;
            }
        }
        return false;
    }


    public Optional<SelectState> getSelectStateById(Integer id) {
        return selectStateDao.findById(id);
    }

    public boolean deleteSelectStateById(Integer id) {
        if (!selectStateDao.existsById(id)) return false;
        else {
            selectStateDao.deleteById(id);
            return true;
        }
    }

    public Optional<SelectState> getSelectStateByStuIdAndPeriod(Integer id, Integer period) {
        SelectState selectState = new SelectState();
        Student student = new Student();
        student.setId(id);
        selectState.setStudent(student);
        selectState.setPeriod(period);
        return selectStateDao.findOne(Example.of(selectState));
    }
}
