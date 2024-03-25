package com.companyname.springbootcrudrest.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    private long eid;
    private String name;
    private String address;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eid")
    public long getEid() {
        return eid;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee(long eid, String name, String address) {
        this.eid = eid;
        this.name = name;
        this.address = address;
    }

    public Employee() {
    }
}