package org.example.searchservice;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PatientRepository extends ElasticsearchRepository<Patient, String> {
    List<Patient> findByNameContainingIgnoreCase(String name);
}