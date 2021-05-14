package br.com.hinto.controlador;

import br.com.hinto.entidade.dto.MidiaAnimeCriadoDTO;
import br.com.hinto.entidade.dto.MidiaRetornadoDTO;
import br.com.hinto.servico.MidiaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/midia")
public class MidiaControlador {
	
    @Autowired
    private MidiaServico servico;

    @PostMapping
	@CrossOrigin(origins = "*")
    public MidiaRetornadoDTO salvar(@Valid @RequestBody MidiaAnimeCriadoDTO midiaCriadoDTO){
        return this.servico.salvar(midiaCriadoDTO);
    }

    @DeleteMapping("/{idMidia}")
	@CrossOrigin(origins = "*")
    public void deletar(@PathVariable("idMidia") Long idMidia){
        this.servico.deletar(idMidia);
    }

    @PutMapping("/{idMidia}")
	@CrossOrigin(origins = "*")
    public void atualizar(@Valid @RequestBody MidiaAnimeCriadoDTO midia, @PathVariable("idMidia") Long idMidia){
        this.servico.atualizar(midia, idMidia);
    }

    @GetMapping
	@CrossOrigin(origins = "*")
    public List<MidiaRetornadoDTO> encontrarTodos() {
        return this.servico.encontrarTodos();
    }

    @GetMapping("/{idMidia}")
	@CrossOrigin(origins = "*")
    public MidiaRetornadoDTO encontrarPorId(@PathVariable("idMidia") Long idMidia) {
        return this.servico.encontrarPorId(idMidia);
    }
    
	@CrossOrigin(origins = "*")
    @GetMapping("/buscar/{stringBusca}")
    public List<MidiaRetornadoDTO> buscarTitulosPorString(@PathVariable("stringBusca") String stringBusca) {
    	return this.servico.buscarPorString(stringBusca);
    }
}
