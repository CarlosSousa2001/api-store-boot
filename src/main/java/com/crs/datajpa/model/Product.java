package com.crs.datajpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "produto",
    uniqueConstraints = {@UniqueConstraint(name = "unq_cod", columnNames = {"cod"})}
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , nullable = false)
    @Size(min = 2, max = 100)
    private String title;

    @Column(name = "description" )
    @Size(max = 275)
    private String description;

    @Column(name = "price", precision = 10, scale = 2) // value decimal(19,2)
    private int price;

    @Column(nullable = false, length = 50)
    private String cod;

    @URL
    @Column(name = "photo_url")
    @Size(max = 225)
    private String photoUrl;

    @Column(name = "created_at", updatable = false) // não posso atualizar
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false) // não posso adicionar ou modificiar
    private LocalDateTime updateAt;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany
    @JoinTable(name = "product_category",
        joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_category_product")),
        inverseJoinColumns = @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_inverse_product_category_product"))
    )
    private Set<Category> categories = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "product_tag",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "tag")
    @Size(max = 75)
    private List<String> tags = new ArrayList<>();



    public Product(){}

    public Product(Long id, String title, String description, int price, String cod, String photoUrl, LocalDateTime createdAt, LocalDateTime updateAt, Set<Category> categories, List<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.cod = cod;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.categories = categories;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
