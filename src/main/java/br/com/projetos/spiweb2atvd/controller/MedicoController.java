package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Medico;
import br.com.projetos.spiweb2atvd.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("medico/list");
        mav.addObject("medicos", medicoRepository.findAll());
        return mav;
    }

    @GetMapping("/novo")
    public ModelAndView formNovo() {
        ModelAndView mav = new ModelAndView("medico/form");
        mav.addObject("medico", new Medico());
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formEditar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("medico/form");
        mav.addObject("medico", medicoRepository.findById(id));
        return mav;
    }

    @PostMapping("/salvar")
    @Transactional
    public String salvar(@ModelAttribute Medico medico) {
        medicoRepository.save(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/excluir/{id}")
    @Transactional
    public String excluir(@PathVariable Long id) {
        medicoRepository.delete(id);
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/consultas")
    public ModelAndView consultasDoMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.findById(id);
        ModelAndView mav = new ModelAndView("medico/consultas");
        mav.addObject("medico", medico);
        mav.addObject("consultas", medico.getConsultas());
        return mav;
    }
}
