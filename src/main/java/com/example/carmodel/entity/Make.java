package com.example.carmodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "make")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "logo", length = 50, nullable = false)
    private String logo;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected Make() {}

    public Make(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    private List<Model> models = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @JsonSerialize
    public long countModels() {
        return models.size();
    }


}
