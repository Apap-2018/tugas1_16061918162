package com.apap.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.repository.JabatanPegawaiDB;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {
	
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDB;
	
	@Override
	public long sizeJabatanPegawai() {
		return jabatanPegawaiDB.count();
	}
	
	

}
