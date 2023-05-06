package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entiy.Address;
import com.woniuxy.mall.entiy.Region;
import com.woniuxy.mall.mapper.AddressMapper;
import com.woniuxy.mall.mapper.RegionMapper;
import com.woniuxy.mall.service.AddressService;
import com.woniuxy.mall.service.RegionService;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {
}
