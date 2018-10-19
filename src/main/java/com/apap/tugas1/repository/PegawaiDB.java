package com.apap.tugas1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.Pegawai;

public interface PegawaiDB extends JpaRepository<Pegawai, Long>{
	Pegawai findByNip(String nip);

}
