package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Paciente;
import br.com.projetos.spiweb2atvd.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador responsável por gerenciar as requisições relacionadas a Pacientes.
 * Aplica a arquitetura MVC e o padrão PRG (Post-Redirect-Get).
 */
@Controller
@Transactional
@RequestMapping("paciente")
public class PacienteController {

    private final PacienteRepository repository;

    /**
     * Construtor para injeção de dependência do repositório.
     * @param repository O repositório de Paciente.
     */
    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os pacientes cadastrados.
     * @return ModelAndView com a view de listagem e a lista de pacientes.
     */
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("paciente/list");
        mv.addObject("pacientes", repository.findAll());
        return mv;
    }

    /**
     * Exibe o formulário de cadastro de um novo paciente ou edição de um existente.
     * @param paciente O objeto paciente (novo ou carregado do banco).
     * @return ModelAndView com a view do formulário.
     */
    @GetMapping("/form")
    public ModelAndView form(Paciente paciente) {
        ModelAndView mv = new ModelAndView("paciente/form");
        mv.addObject("paciente", paciente);
        return mv;
    }

    /**
     * Salva um novo paciente no banco de dados.
     * Aplica o padrão PRG redirecionando para a lista após salvar.
     * @param paciente O paciente preenchido no formulário.
     * @return String de redirecionamento para a lista.
     */
    @PostMapping("/save")
    public String save(Paciente paciente) {
        repository.save(paciente);
        return "redirect:/paciente/list";
    }

    /**
     * Prepara a edição de um paciente, buscando-o pelo ID e enviando para o formulário.
     * @param id O ID do paciente a ser editado.
     * @return ModelAndView renderizando o formulário com os dados do paciente.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Paciente paciente = repository.findById(id);
        return form(paciente);
    }

    /**
     * Atualiza os dados de um paciente já existente no banco de dados.
     * @param paciente O paciente com os dados atualizados.
     * @return String de redirecionamento para a lista.
     */
    @PostMapping("/update")
    public String update(Paciente paciente) {
        repository.update(paciente);
        return "redirect:/paciente/list";
    }

    /**
     * Exclui um paciente pelo seu ID.
     * @param id O ID do paciente a ser removido.
     * @return String de redirecionamento para a lista.
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.delete(id);
        return "redirect:/paciente/list";
    }

    /**
     * Exibe todas as consultas agendadas para um determinado paciente.
     * @param id O ID do paciente.
     * @return ModelAndView com a view de consultas do paciente, enviando o paciente e sua lista de consultas.
     */
    @GetMapping("/consultas/{id}")
    public ModelAndView consultasPaciente(@PathVariable("id") Long id) {
        Paciente paciente = repository.findById(id);
        ModelAndView mv = new ModelAndView("paciente/consultas");
        mv.addObject("paciente", paciente);
        // Devido ao @Transactional na classe, a lista de consultas será carregada corretamente pelo Hibernate (Lazy Loading)
        mv.addObject("consultas", paciente.getConsultas());
        return mv;
    }
}
