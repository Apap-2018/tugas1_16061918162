package com.apap.tugas1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tugas1.model.Jabatan;

@Service
public interface JabatanService {
	List<Jabatan> getListJabatan();
	Jabatan findJabatanById (long id);
	void addJabatan (Jabatan jabatan);
	void updateJabatan (Jabatan jabatan);
	void deleteJabatan (long id);

}
