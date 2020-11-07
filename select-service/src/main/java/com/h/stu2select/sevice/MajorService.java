package com.h.stu2select.sevice;

import com.h.stu2select.dao.MajorDao;
import com.h.stu2select.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MajorService {
    @Resource
    MajorDao majorDao;

    public Page<Major> getAllMajor(PageRequest pageRequest) {
        return majorDao.findAll(pageRequest);
    }

    public void setMajor(Major major) {
        majorDao.save(major);
    }

    public Optional<Major> getMajorById(Integer id) {
        return majorDao.findById(id);
    }

    public boolean deleteMajorById(Integer id) {
        if (!majorDao.existsById(id)) return false;
        else {
            majorDao.deleteById(id);
            return true;
        }
    }
}
