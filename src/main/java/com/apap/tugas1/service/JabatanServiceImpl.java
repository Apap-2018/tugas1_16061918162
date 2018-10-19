package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.Jabatan;
import com.apap.tugas1.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public List<Jabatan> getListJabatan() {
		return jabatanDB.findAll();
	}
	
	@Override
	public Jabatan findJabatanById(long idJabatan) {
		return jabatanDB.getOne(idJabatan);
	}

	@Override
	public void addJabatan(Jabatan jabatan) {
		jabatanDB.save(jabatan);
		
	}

	@Override
	public void updateJabatan(Jabatan jabatan) {
		Jabatan update = jabatanDB.findById(jabatan.getId()).get();
		update.setNama(jabatan.getNama());
		update.setDeskripsi(jabatan.getDeskripsi());
		update.setGajiPokok(jabatan.getGajiPokok());
		jabatanDB.save(update);
	}

	@Override
	public void deleteJabatan(long id) {
		jabatanDB.deleteById(id);
		
	}
	
	

}
