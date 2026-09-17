package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Paciente;
import br.com.projetos.spiweb2atvd.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("paciente/list");
        mav.addObject("pacientes", pacienteRepository.findAll());
        return mav;
    }

    @GetMapping("/novo")
    public ModelAndView formNovo() {
        ModelAndView mav = new ModelAndView("paciente/form");
        mav.addObject("paciente", new Paciente());
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formEditar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("paciente/form");
        mav.addObject("paciente", pacienteRepository.findById(id));
        return mav;
    }

    @PostMapping("/salvar")
    @Transactional
    public String salvar(@ModelAttribute Paciente paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/excluir/{id}")
    @Transactional
    public String excluir(@PathVariable Long id) {
        pacienteRepository.delete(id);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/consultas")
    public ModelAndView consultasDoPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id);
        ModelAndView mav = new ModelAndView("paciente/consultas");
        mav.addObject("paciente", paciente);
        mav.addObject("consultas", paciente.getConsultas());
        return mav;
    }
}
