package com.example.demo.dto.mapper;

import com.example.demo.dto.CompanyRequest;
import com.example.demo.dto.CompanyResponse;
import com.example.demo.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        BeanUtils.copyProperties(company, response);
        return response;
    }

    public List<CompanyResponse> toResponse(List<Company> companies) {
        return companies.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Company toEntity(CompanyRequest request) {
        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        return company;
    }
}