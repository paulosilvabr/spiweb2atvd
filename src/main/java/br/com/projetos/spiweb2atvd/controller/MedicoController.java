package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Medico;
import br.com.projetos.spiweb2atvd.repository.MedicoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador responsável por gerenciar as requisições relacionadas a Médicos.
 * Aplica a arquitetura MVC e o padrão PRG (Post-Redirect-Get).
 */
@Controller
@Transactional
@RequestMapping("medico")
public class MedicoController {

    private final MedicoRepository repository;

    /**
     * Construtor para injeção de dependência do repositório.
     * @param repository O repositório de Medico.
     */
    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os médicos cadastrados.
     * @return ModelAndView com a view de listagem e a lista de médicos.
     */
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("medico/list");
        mv.addObject("medicos", repository.findAll());
        return mv;
    }

    /**
     * Exibe o formulário de cadastro de um novo médico ou edição de um existente.
     * @param medico O objeto médico (novo ou carregado do banco).
     * @return ModelAndView com a view do formulário.
     */
    @GetMapping("/form")
    public ModelAndView form(Medico medico) {
        ModelAndView mv = new ModelAndView("medico/form");
        mv.addObject("medico", medico);
        return mv;
    }

    /**
     * Salva um novo médico no banco de dados.
     * Aplica o padrão PRG redirecionando para a lista após salvar.
     * @param medico O médico preenchido no formulário.
     * @return String de redirecionamento para a lista.
     */
    @PostMapping("/save")
    public String save(Medico medico) {
        repository.save(medico);
        return "redirect:/medico/list";
    }

    /**
     * Prepara a edição de um médico, buscando-o pelo ID e enviando para o formulário.
     * @param id O ID do médico a ser editado.
     * @return ModelAndView renderizando o formulário com os dados do médico.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Medico medico = repository.findById(id);
        return form(medico);
    }

    /**
     * Atualiza os dados de um médico já existente no banco de dados.
     * @param medico O médico com os dados atualizados.
     * @return String de redirecionamento para a lista.
     */
    @PostMapping("/update")
    public String update(Medico medico) {
        repository.update(medico);
        return "redirect:/medico/list";
    }

    /**
     * Exclui um médico pelo seu ID.
     * @param id O ID do médico a ser removido.
     * @return String de redirecionamento para a lista.
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.delete(id);
        return "redirect:/medico/list";
    }

    /**
     * Exibe todas as consultas agendadas para um determinado médico.
     * @param id O ID do médico.
     * @return ModelAndView com a view de consultas do médico, enviando o médico e sua lista de consultas.
     */
    @GetMapping("/consultas/{id}")
    public ModelAndView consultasMedico(@PathVariable("id") Long id) {
        Medico medico = repository.findById(id);
        ModelAndView mv = new ModelAndView("medico/consultas");
        mv.addObject("medico", medico);
        // Devido ao @Transactional na classe, a lista de consultas será carregada corretamente pelo Hibernate (Lazy Loading)
        mv.addObject("consultas", medico.getConsultas());
        return mv;
    }
}
