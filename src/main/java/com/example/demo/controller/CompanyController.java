package com.example.demo.controller;

import com.example.demo.dto.CompanyRequest;
import com.example.demo.dto.CompanyResponse;
import com.example.demo.dto.mapper.CompanyMapper;
import com.example.demo.entity.Company;
import com.example.demo.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final List<Company> companies = new ArrayList<>();

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return companyService.getCompanies(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody @Validated  CompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateCompany(@PathVariable int id, @RequestBody CompanyRequest updatedCompany) {
        Company company = companyMapper.toEntity(updatedCompany);
        return companyService.updateCompany(id, company);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }


}
