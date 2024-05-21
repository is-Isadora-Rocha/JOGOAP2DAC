package bean;

import java.util.Date;
import java.util.List;
import java.util.Random;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import DAO.JogoDAO;
import entities.*;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private Integer maiorNumSortTable;
	private Integer maiorNumVar;
	private String resultVerificacao;
	
	public String salvar() {
		if (jogo != null) {
			jogo.setDataCadastro(new Date());
			
			Random random = new Random();
			jogo.setNumeroSorteado(random.nextInt(10) + 1);
			
			JogoDAO.salvar(jogo);
			jogo = new Jogo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Feito!", "Jogo criado!"));
			lista = JogoDAO.listar();
			return "listagem.xhtml";

		}
		return null;
	}
	
	public String editar(Jogo jogoEditado) {
		if (jogoEditado != null) {
			JogoDAO.editar(jogoEditado);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo atualizado!"));
			lista = JogoDAO.listar();
			return "listagem.xthml";
		}
		return null;
	}
	
	public String deletar(int id) {

		JogoDAO.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo excluído!"));
		lista = JogoDAO.listar();
		return "listagem.xthml";

	}
	
	public void validarNumero(FacesContext contexto, UIComponent componente, Object valor) {
	    int numero = (Integer) valor;
	    if (numero < 1 || numero > 10) {
	        FacesMessage mensagem = new FacesMessage("Inválido! Digite entre 1 e 10.");
	        throw new ValidatorException(mensagem);
	    }
	}

	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public List<Jogo> getLista() {
		if (lista == null) {
			lista = JogoDAO.listar();
		}
		return lista;
	}
	
	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}
	
 	public Integer getMaxNumeroSorteado() {
 	maiorNumSortTable = JogoDAO.encontrarMaximoNumeroSorteado();
 	return maiorNumSortTable;
 }

 public Integer getMaxNumeroDasVariaveis(Jogo jogo) {
 	maiorNumVar = JogoDAO.encontrarMaximoNumeroDasVariaveis(jogo);
 System.out.println("maior num da var" + maiorNumVar);
 	return maiorNumVar;
 }
	
	public void verificarNumeroSorteado(Jogo jogo, int numeroSorteado) {
		for (int i = jogo.getV1(); i <= jogo.getV5(); i++) {
	        if (i == numeroSorteado) {
	        	resultVerificacao = "sim";
		    } else {
		        resultVerificacao = "não";
		    }
		}
	}
	
	public Integer getMaiorNumSortTable() {
		return maiorNumSortTable;
	}
	public void setMaiorNumSortTable(Integer maiorNumSortTable) {
		this.maiorNumSortTable = maiorNumSortTable;
	}
	public Integer getMaiorNumVar() {
		return maiorNumVar;
	}
	public void setMaiorNumVar(Integer maiorNumVar) {
		this.maiorNumVar = maiorNumVar;
	}
	public String getResultVerificacao() {
		return resultVerificacao;
	}
	public void setResultVerificacao(String resultVerificacao) {
		this.resultVerificacao = resultVerificacao;
	}
	
	

}
