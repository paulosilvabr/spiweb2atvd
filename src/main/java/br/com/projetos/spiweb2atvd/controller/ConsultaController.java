package br.com.projetos.spiweb2atvd.controller;

import br.com.projetos.spiweb2atvd.model.Consulta;
import br.com.projetos.spiweb2atvd.repository.ConsultaRepository;
import br.com.projetos.spiweb2atvd.repository.MedicoRepository;
import br.com.projetos.spiweb2atvd.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador Web responsável pelas requisições referentes a Consulta.
 * O @Controller diz ao Spring que esta classe processará requisições web (MVC).
 * O @Transactional garante que toda a execução do método ocorra dentro de uma transação do banco,
 * mantendo a sessão do Hibernate aberta para permitir o Lazy Loading quando as entidades acessarem suas coleções.
 */
@Controller
@Transactional
@RequestMapping("consulta")
public class ConsultaController {

    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaController(ConsultaRepository repository, 
                              PacienteRepository pacienteRepository, 
                              MedicoRepository medicoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    /**
     * Método acionado ao acessar /consulta/list.
     * Busca os registros e direciona para a view que monta a tabela.
     */
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("consulta/list");
        mv.addObject("consultas", repository.findAll());
        return mv;
    }

    /**
     * Exibe o formulário. O fluxo aqui é muito importante: além da própria entidade Consulta,
     * precisamos buscar a lista de Pacientes e de Médicos no banco para popular as tags <select> do HTML.
     */
    @GetMapping("/form")
    public ModelAndView form(Consulta consulta) {
        ModelAndView mv = new ModelAndView("consulta/form");
        mv.addObject("consulta", consulta);
        
        // Busca as listas no banco para alimentar as caixas de seleção
        mv.addObject("pacientes", pacienteRepository.findAll());
        mv.addObject("medicos", medicoRepository.findAll());
        
        return mv;
    }

    /**
     * Recebe os dados do formulário via POST. Após salvar no banco de dados,
     * aplica o padrão PRG (Post-Redirect-Get) redirecionando para a rota "/consulta/list".
     * Isso impede que o usuário aperte F5 e reenvie acidentalmente o formulário.
     */
    @PostMapping("/save")
    public String save(Consulta consulta) {
        repository.save(consulta);
        return "redirect:/consulta/list";
    }

    /**
     * Ação de preparar a edição. Busca o ID no banco e reutiliza o método form()
     * para carregar a entidade e as listas dos <select>.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Consulta consulta = repository.findById(id);
        return form(consulta);
    }

    /**
     * Assim como no save(), este metodo recebe os dados editados, atualiza
     * e novamente utiliza o padrão PRG redirecionando a página.
     */
    @PostMapping("/update")
    public String update(Consulta consulta, @RequestParam Long pacienteId, @RequestParam Long medicoId) {
        consulta.setPaciente(pacienteRepository.findById(consulta.getPaciente().getId()));
        consulta.setMedico(medicoRepository.findById(consulta.getMedico().getId()));

        repository.update(consulta);
        return "redirect:/consulta/list";
    }

    /**
     * Remove uma consulta do banco pelo ID e redireciona de volta para a lista.
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.delete(id);
        return "redirect:/consulta/list";
    }
}
