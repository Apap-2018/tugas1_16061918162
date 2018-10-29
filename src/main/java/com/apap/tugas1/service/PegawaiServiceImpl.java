package com.apap.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.Jabatan;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

	@Autowired
	private PegawaiDB pegawaiDb;

	@Override
	public void searchPegawai(long id) {
		pegawaiDb.findById(id);
	}

	@Override
	public Pegawai getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public void addPegawai(Pegawai pegawai) {
		pegawaiDb.save(pegawai);

	}

	@Override
	public double getGaji(String nip) {
		double gajiLengkap = 0;
		Pegawai pegawai = this.getPegawaiDetailByNip(nip);
		double gajiTerbesar = 0; 
		for (Jabatan jabatan:pegawai.getJabatanList()) {
			if (jabatan.getGajiPokok() > gajiTerbesar) {
				gajiTerbesar = jabatan.getGajiPokok();
			}
		}
		gajiLengkap += gajiTerbesar;
		double presentaseTunjangan = pegawai.getInstansi().getProvinsi().getTunjangan_prov();
		gajiLengkap += (gajiLengkap * presentaseTunjangan/100);
		return gajiLengkap;
	}

}
