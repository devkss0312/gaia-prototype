// src/main/java/com/example/gaia/model/ComponentStyle.java
package com.example.gaia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "component_styles")
public class ComponentStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_id")
    private Long styleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    @JsonBackReference(value = "component-styles")
    private Component component;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Style name is required")
    @Size(max = 255, message = "Style name must be at most 255 characters")
    private String name;

    @Column(name = "value", nullable = false)
    @NotBlank(message = "Style value is required")
    @Size(max = 255, message = "Style value must be at most 255 characters")
    private String value;

    @Column(name = "type", nullable = false)
    @NotBlank(message = "Style type is required")
    @Size(max = 50, message = "Style type must be at most 50 characters")
    private String type; // 예: "color", "font-size" 등
}
