package com.storage.service.Impl;

import com.storage.model.Plan;
import com.storage.repository.PlanRepository;
import com.storage.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }


    @Override
    public Plan getFree() {
        return planRepository.findByName("Free");
    }
}
