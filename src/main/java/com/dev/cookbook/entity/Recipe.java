package com.dev.cookbook.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recipe")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String preamble;
    private String description;
    @Column(name = "first_recipe", nullable = false)
    private boolean isFirstVersion;
    @OneToMany
    @JoinColumn(name = "ingredient")
    private Set<Ingredient> ingredients;
    @ManyToMany
    @JoinColumn(name = "previous_version")
    private List<Recipe> previousVersions;
    @ManyToOne
    private Recipe parent;
}
