// src/main/java/com/example/gaia/controller/ComponentController.java
package com.example.gaia.controller;

import com.example.gaia.model.Component;
import com.example.gaia.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/components")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    /**
     * 모든 Component를 조회합니다.
     *
     * @return 모든 Component의 리스트
     */
    @GetMapping
    public ResponseEntity<Iterable<Component>> getAllComponents() {
        Iterable<Component> components = componentService.getAllComponents();
        return ResponseEntity.ok(components);
    }

    /**
     * 특정 Component를 조회합니다.
     *
     * @param id 조회할 Component의 ID
     * @return 해당 Component
     */
    @GetMapping("/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable Long id) {
        Optional<Component> componentOpt = componentService.getComponentById(id);
        return componentOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 새로운 Component를 생성합니다.
     *
     * @param component JSON으로부터 전달된 Component 객체
     * @return 저장된 Component 객체
     */
    @PostMapping
    public ResponseEntity<Component> createComponent(@RequestBody Component component) {
        try {
            Component savedComponent = componentService.saveComponent(component);
            return ResponseEntity.ok(savedComponent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 특정 Component를 삭제합니다.
     *
     * @param id 삭제할 Component의 ID
     * @return 상태 코드
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long id) {
        try {
            componentService.deleteComponent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
