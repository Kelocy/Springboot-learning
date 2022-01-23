package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /** 进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null
         * 可以节省流量，另一方面提升了效率
         */
        for (District d: list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }
}
