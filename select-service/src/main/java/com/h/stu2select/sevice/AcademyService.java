package com.h.stu2select.sevice;

import com.h.stu2select.dao.AcademyDao;
import com.h.stu2select.entity.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AcademyService {
    @Resource
    AcademyDao academyDao;

    public Page<Academy> getAllAcademy(PageRequest pageRequest) {
        return academyDao.findAll(pageRequest);
    }

    public void setAcademy(Academy academy) {
        academyDao.save(academy);
    }

    public Optional<Academy> getAcademyById(Integer id) {
        return academyDao.findById(id);
    }

    public boolean deleteAcademyById(Integer id) {
        if (!academyDao.existsById(id)) return false;
        else {
            academyDao.deleteById(id);
            return true;
        }
    }
}
