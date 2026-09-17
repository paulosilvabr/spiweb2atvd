package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Consulta;
import br.com.projetos.spiweb2atvd.model.Medico;
import br.com.projetos.spiweb2atvd.model.Paciente;
import br.com.projetos.spiweb2atvd.repository.ConsultaRepository;
import br.com.projetos.spiweb2atvd.repository.MedicoRepository;
import br.com.projetos.spiweb2atvd.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaController(ConsultaRepository consultaRepository,
                              PacienteRepository pacienteRepository,
                              MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @GetMapping
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("consulta/list");
        mav.addObject("consultas", consultaRepository.findAll());
        return mav;
    }

    @GetMapping("/nova")
    public ModelAndView formNova() {
        ModelAndView mav = new ModelAndView("consulta/form");
        mav.addObject("consulta", new Consulta());
        mav.addObject("pacientes", pacienteRepository.findAll());
        mav.addObject("medicos", medicoRepository.findAll());
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formEditar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("consulta/form");
        mav.addObject("consulta", consultaRepository.findById(id));
        mav.addObject("pacientes", pacienteRepository.findAll());
        mav.addObject("medicos", medicoRepository.findAll());
        return mav;
    }

    @PostMapping("/salvar")
    @Transactional
    public String salvar(@RequestParam(required = false) Long id,
                         @RequestParam String observacao,
                         @RequestParam double valor,
                         @RequestParam String data,
                         @RequestParam Long pacienteId,
                         @RequestParam Long medicoId) {

        Consulta consulta;
        if (id != null) {
            consulta = consultaRepository.findById(id);
        } else {
            consulta = new Consulta();
        }

        consulta.setObservacao(observacao);
        consulta.setValor(valor);
        consulta.setData(java.time.LocalDateTime.parse(data));
        consulta.setPaciente(pacienteRepository.findById(pacienteId));
        consulta.setMedico(medicoRepository.findById(medicoId));

        consultaRepository.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/excluir/{id}")
    @Transactional
    public String excluir(@PathVariable Long id) {
        consultaRepository.delete(id);
        return "redirect:/consultas";
    }
}
