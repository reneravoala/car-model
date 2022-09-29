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

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    protected Make() {}

    public Make(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    @JsonSerialize
    private String LogoUrl() {
        return "http://127.0.0.1:8080/logo/"+getName().replace(" ", "_").toLowerCase();
    }

    //ito ts mety itany vue que misy le parametre request
    @JsonSerialize
    private String baseUrl(HttpServletRequest request) throws IOException {
        String uri = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        return uri;
    }
}
