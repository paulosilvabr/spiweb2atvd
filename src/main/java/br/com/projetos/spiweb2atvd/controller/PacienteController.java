package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Paciente;
import br.com.projetos.spiweb2atvd.repository.PacienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteRepository repository;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("paciente/list");
        mv.addObject("pacientes", repository.findAll());
        return mv;
    }

    @GetMapping("/form")
    public ModelAndView form(Paciente paciente) {
        ModelAndView mv = new ModelAndView("paciente/form");
        mv.addObject("paciente", paciente);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Paciente paciente, BindingResult binding) {
        if (binding.hasErrors()) {
            return form(paciente);
        }
        // O método save() do JpaRepository serve tanto para INSERT quanto para UPDATE
        repository.save(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        // JpaRepository retorna um Optional, por isso usamos orElseThrow()
        Paciente paciente = repository.findById(id).orElseThrow();
        return form(paciente);
    }

    @PostMapping("/update")
    public String update(@Valid Paciente paciente, BindingResult binding) {
        if (binding.hasErrors()) {
            // Em caso de erro na edição, precisamos devolver o ModelAndView
            // Mas para simplificar e manter a sua assinatura de método String, chamamos o save diretamente
            // No entanto, a boa prática é o método update usar ModelAndView como no save.
            // Para não quebrar a sua estrutura atual, vou manter o save abaixo.
            // Mas o ideal era juntar a rota /save e /update numa só no futuro!
            return "paciente/form";
        }
        // O save() atualiza o registo se o ID já existir
        repository.save(paciente);
        return "redirect:/paciente/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        // JpaRepository tem um método direto para deletar por ID
        repository.deleteById(id);
        return "redirect:/paciente/list";
    }

    @GetMapping("/consultas/{id}")
    public ModelAndView consultasPaciente(@PathVariable("id") Long id) {
        Paciente paciente = repository.findById(id).orElseThrow();
        ModelAndView mv = new ModelAndView("paciente/consultas");
        mv.addObject("paciente", paciente);
        mv.addObject("consultas", paciente.getConsultas());
        return mv;
    }
}