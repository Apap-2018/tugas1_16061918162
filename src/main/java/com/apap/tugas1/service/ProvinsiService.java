package com.apap.tugas1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tugas1.model.Provinsi;

@Service
public interface ProvinsiService {
	List<Provinsi> getProvList();
	Provinsi getProvinsiDetailById(long provinsiId);

}
