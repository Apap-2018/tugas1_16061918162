package com.apap.tugas1.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.Instansi;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;

@Controller
public class PegawaiController {

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private InstansiService instansiService;

	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("jabatan", jabatanService.getListJabatan());
		model.addAttribute("instansi", instansiService.getListInstansi());
		return "home";
	}

	@RequestMapping(value = "/pegawai")
	private String CariNip(@RequestParam(value = "nip") String nip, Model model) {
		Pegawai pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		return "search-nip";
	}

	@RequestMapping(value = "/pegawai/termuda-tertua")
	private String tuaMuda(@RequestParam(value = "idInstansi") long id, Model model) {

		Instansi instansi = instansiService.getInstansiById(id);
		List<Pegawai> listPegawai = instansi.getPegawai();

		Pegawai muda;
		Pegawai tua;

		if(listPegawai.size() > 0) {
			muda = listPegawai.get(1);
			tua = listPegawai.get(1);

			for (Pegawai pegawai : listPegawai) {
				Date tanggal = pegawai.getTanggalLahir();
				if (tanggal.before(tua.getTanggalLahir())) {
					tua = pegawai;
				}
				else if (tanggal.after(muda.getTanggalLahir())) {
					muda = pegawai;
				}
			}

			model.addAttribute("pegawaiTermuda", muda);
			model.addAttribute("pegawaiTertua", tua);
			return "tua-muda";
		}
		return "response";
	}

}
