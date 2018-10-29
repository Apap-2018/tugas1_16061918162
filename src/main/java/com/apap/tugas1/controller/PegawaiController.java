package com.apap.tugas1.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.Instansi;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;

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
		model.addAttribute("gajiLengkap", Math.round(pegawaiService.getGaji(nip)));
		model.addAttribute("jabatanList", pegawai.getJabatanList());
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
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String tambahPegawai(Model model) {
		Pegawai pegawai = new Pegawai();
		pegawai.setInstansi(new Instansi());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", provinsiService.getProvList());
		model.addAttribute("listJabatan", jabatanService.getListJabatan());
		
		return "tambahPegawai";
	}

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String tambahPegawaiSukses(@ModelAttribute Pegawai pegawai, Model model) {
		String nip = "";
		
		nip += pegawai.getInstansi().getId();
		
		String[] tanggalLahir = pegawai.getTanggalLahir().toString().split("-");
		String tanggalLahirCombine = tanggalLahir[2] + tanggalLahir[1] + tanggalLahir[0].substring(2, 4);
		nip += tanggalLahirCombine;

		nip += pegawai.getTahunMasuk();

		int counter = 1;
		for (Pegawai pegawaiInstansi:pegawai.getInstansi().getPegawai()) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir())) {
				counter += 1;
			}	
		}
		nip += "0" + counter;
		
		pegawai.setNip(nip);
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "tambahPegawaiSukses";
	}
	
	@RequestMapping(value = "/pegawai/ubah")
	public String ubahPegawai(@RequestParam("nip") String nip, Model model) {
		Pegawai pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("listProvinsi", provinsiService.getProvList());
		model.addAttribute("listJabatan", jabatanService.getListJabatan());
		model.addAttribute("pegawai", pegawai);
		return "ubahPegawai";	
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String ubahPegawaiSukses(@ModelAttribute Pegawai pegawai, Model model) {
		String nip = "";
		
		nip += pegawai.getInstansi().getId();
		
		String[] tanggalLahir = pegawai.getTanggalLahir().toString().split("-");
		String tanggalLahirCombine = tanggalLahir[2] + tanggalLahir[1] + tanggalLahir[0].substring(2, 4);
		nip += tanggalLahirCombine;
		
		nip += pegawai.getTahunMasuk();
		
		int counter = 1;
		for (Pegawai pegawaiInstansi:pegawai.getInstansi().getPegawai()) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir()) && pegawaiInstansi.getId() != pegawai.getId()) {
				counter += 1;
			}	
		}
		nip += "0" + counter;

		pegawai.setNip(nip);
	
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "ubahPegawaiSukses";
	}

}
