// src/main/java/com/example/gaia/service/ComponentService.java
package com.example.gaia.service;

import com.example.gaia.model.Component;
import com.example.gaia.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    /**
     * JSON으로부터 전달된 Component 트리를 저장합니다.
     * 모든 하위 컴포넌트, 콘텐츠, 스타일도 함께 저장됩니다.
     *
     * @param component JSON으로부터 전달된 Component 객체
     * @return 저장된 Component 객체
     */
    @Transactional
    public Component saveComponent(Component component) {
        // 이미 존재하는 Component ID인지 확인
        if (component.getComponentId() != null && componentRepository.existsById(component.getComponentId())) {
            throw new IllegalArgumentException("Component with ID " + component.getComponentId() + " already exists.");
        }

        // 부모 설정 (필요한 경우)
        if (component.getParent() != null) {
            Optional<Component> parentOpt = componentRepository.findById(component.getParent().getComponentId());
            if (parentOpt.isPresent()) {
                component.setParent(parentOpt.get());
            } else {
                throw new IllegalArgumentException("Parent component with ID " + component.getParent().getComponentId() + " not found.");
            }
        }

        // 자식 컴포넌트, 콘텐츠, 스타일은 CascadeType.ALL로 인해 자동으로 저장됩니다.
        return componentRepository.save(component);
    }

    /**
     * 특정 Component ID로 Component를 조회합니다.
     *
     * @param componentId 조회할 Component의 ID
     * @return Optional<Component>
     */
    public Optional<Component> getComponentById(Long componentId) {
        return componentRepository.findById(componentId);
    }

    /**
     * 모든 Component를 조회합니다.
     *
     * @return 모든 Component의 리스트
     */
    public Iterable<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    /**
     * 특정 Component를 삭제합니다.
     *
     * @param componentId 삭제할 Component의 ID
     */
    @Transactional
    public void deleteComponent(Long componentId) {
        if (!componentRepository.existsById(componentId)) {
            throw new IllegalArgumentException("Component with ID " + componentId + " does not exist.");
        }
        componentRepository.deleteById(componentId);
    }
}
