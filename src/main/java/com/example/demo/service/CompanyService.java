package com.example.demo.service;

import com.example.demo.dto.CompanyResponse;
import com.example.demo.dto.mapper.CompanyMapper;
import com.example.demo.entity.Company;
import com.example.demo.repository.ICompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final ICompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    public CompanyService(ICompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyResponse> getCompanies(Integer page, Integer size) {
        if(page == null || size == null){
            return companyMapper.toResponse(companyRepository.findAll());
        }else {
            Pageable pageable = PageRequest.of(page-1, size);
            return companyMapper.toResponse(companyRepository.findAll(pageable).toList());
        }
    }

    public CompanyResponse createCompany(Company company) {
        if(company.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company id must be null");
        }
        return companyMapper.toResponse(companyRepository.save(company));
    }

    public CompanyResponse updateCompany(int id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id);
        }
        Company company = companyOptional.get();
        company.setId(id);
        company.setName(updatedCompany.getName());
        return companyMapper.toResponse(companyRepository.save(company));
    }

    public CompanyResponse getCompanyById(int id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id);
        }
        return companyMapper.toResponse(company.get());
    }

    public void deleteCompany(int id) {
        if (!companyRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }



}
