package com.flowable.reportapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CITIZEN_PLAN_INFO")
public class CitizenPlan {
    @Id
    @GeneratedValue
    private Integer citizenId;
    private String citizenName;
    private String citizenEmail;
    private Long citizenPhoneNo;
    private String gender;
    private Long ssn;
    private String planName;
    private String planStatus;
}
