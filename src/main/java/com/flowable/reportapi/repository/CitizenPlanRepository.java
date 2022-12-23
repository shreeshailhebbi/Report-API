package com.flowable.reportapi.repository;

import com.flowable.reportapi.model.CitizenPlan;
import com.flowable.reportapi.model.SearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Serializable> {
    @Query("SELECT DISTINCT(planName) FROM CitizenPlan")
    List<String> findDistinctByPlanName();

    @Query("SELECT DISTINCT(planStatus) FROM CitizenPlan")
    List<String> findDistinctByPlanStatus();
}
