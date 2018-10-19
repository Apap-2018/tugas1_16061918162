package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.Provinsi;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
	
	@Autowired
	ProvinsiDB provinsiDB;
	
	@Override
	public List<Provinsi> getProvList() {
		return provinsiDB.findAll();
	}
	
	@Override
	public Provinsi getProvinsiDetailById(long id) {
		return provinsiDB.findById(id).get();
	}

}
