package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.Instansi;

public interface InstansiService {
	
	Instansi getInstansiById(long id);
	List<Instansi> getListInstansi();
}
