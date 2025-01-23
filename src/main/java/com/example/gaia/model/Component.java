// src/main/java/com/example/gaia/model/Component.java
package com.example.gaia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "components")
public class Component {

    @Id
    @Column(name = "component_id")
    private Long componentId; // 클라이언트에서 제공하는 ID

    @Column(name = "type", nullable = false)
    private String type;

    // 부모 컴포넌트와의 관계 (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "parent-child")
    private Component parent;

    // 자식 컴포넌트들과의 관계 (One-to-Many)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "parent-child")
    private Set<Component> children = new HashSet<>();

    // 컴포넌트에 속한 콘텐츠들 (One-to-Many)
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "component-contents")
    private Set<ComponentContent> contents = new HashSet<>();

    // 컴포넌트에 속한 스타일들 (One-to-Many)
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "component-styles")
    private Set<ComponentStyle> styles = new HashSet<>();
}
