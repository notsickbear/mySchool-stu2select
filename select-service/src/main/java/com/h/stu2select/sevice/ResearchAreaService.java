package com.h.stu2select.sevice;

import com.h.stu2select.dao.ResearchAreaDao;
import com.h.stu2select.entity.ResearchArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ResearchAreaService {
    @Resource
    ResearchAreaDao researchAreaDao;

    public Page<ResearchArea> getAllResearchArea(PageRequest pageRequest) {
        return researchAreaDao.findAll(pageRequest);
    }

    public void setResearchArea(ResearchArea researchArea) {
        researchAreaDao.save(researchArea);
    }

    public Optional<ResearchArea> getResearchAreaById(Integer id) {
        return researchAreaDao.findById(id);
    }

    public boolean deleteResearchAreaById(Integer id) {
        if (!researchAreaDao.existsById(id)) return false;
        else {
            researchAreaDao.deleteById(id);
            return true;
        }
    }
}
