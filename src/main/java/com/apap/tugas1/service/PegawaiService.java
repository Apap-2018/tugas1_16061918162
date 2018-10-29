package com.apap.tugas1.service;

import org.springframework.stereotype.Service;

import com.apap.tugas1.model.Pegawai;

@Service
public interface PegawaiService {
	Pegawai getPegawaiDetailByNip(String nip);
	void searchPegawai(long id);
	void addPegawai(Pegawai pegawai);
}
