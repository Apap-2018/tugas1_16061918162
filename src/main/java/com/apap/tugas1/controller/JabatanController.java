package com.apap.tugas1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.Jabatan;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(Model model, @RequestParam(value = "idJabatan") long idJabatan) {
		model.addAttribute("jabatan", jabatanService.findJabatanById(idJabatan));
		return "viewJabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String tambahJabatan(Model model) {
		model.addAttribute("jabatan", new Jabatan());
		return "tambahJabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String tambahJabatanSukses(@ModelAttribute Jabatan jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		return "tambahJabatanSukses";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updateJabatan(@RequestParam(value = "idJabatan") long idJabatan, Model model) {
		Jabatan jabatan = jabatanService.findJabatanById(idJabatan);
		//model.addAttribute("jabatan", jabatan);
		//System.out.println("ini bisa pad");
		System.out.println(jabatan.getId());
		System.out.println(jabatan.getNama());
		model.addAttribute("jabatan", jabatan);
		return "ubahJabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatanSukses(@RequestParam(value = "idJabatan") long id, @ModelAttribute Jabatan jabatan, Model model) {
		jabatan.setId(id);
		jabatanService.updateJabatan(jabatan);
		return "ubahJabatanSukses";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.GET)
	private String hapusJabatan(@RequestParam(value = "idJabatan") long id, Model model) {
		jabatanService.deleteJabatan(id);
		return "hapusJabatan";
	}
	
	@RequestMapping(value = "/jabatan/viewall")
	public String viewAll(Model model) {
		model.addAttribute("listJabatan", jabatanService.getListJabatan());
		return "viewAllJabatan";
	}
}