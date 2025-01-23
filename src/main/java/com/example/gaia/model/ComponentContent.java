// src/main/java/com/example/gaia/model/ComponentContent.java
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
@Table(name = "component_contents")
public class ComponentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    @JsonBackReference(value = "component-contents")
    private Component component;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Content name is required")
    @Size(max = 255, message = "Content name must be at most 255 characters")
    private String name;

    @Column(name = "value", nullable = false)
    @NotBlank(message = "Content value is required")
    @Size(max = 255, message = "Content value must be at most 255 characters")
    private String value;

    @Column(name = "type", nullable = false)
    @NotBlank(message = "Content type is required")
    @Size(max = 50, message = "Content type must be at most 50 characters")
    private String type; // 예: "text", "json" 등
}
