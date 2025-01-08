package com.reservas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reservas.model.Resource;
import com.reservas.repository.ResourceRepository;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Recurso não encontrado!"));
    }

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource resourceDetails) {
        Resource resource = getResourceById(id);
        resource.setName(resourceDetails.getName());
        resource.setType(resourceDetails.getType());
        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        Resource resource = getResourceById(id);
        resourceRepository.delete(resource);
    }
}
