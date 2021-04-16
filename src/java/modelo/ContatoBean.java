/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mb")
@RequestScoped

public class ContatoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Contato contato;
    private List<Contato> contatos;

    public List<Contato> getContatos() {
        try {
            contatos = new ContatoDao().getContatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    {
        contato = new Contato();
    }

    public ContatoBean() {
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void gravar() {
    FacesContext fc = FacesContext.getCurrentInstance();
        try {
            new ContatoDao().inserir(contato);
            contato = new Contato();
             fc.addMessage("form1", new FacesMessage("Dados Gravados!!!"));
        } catch (Exception e) {
            fc.addMessage("form1", new FacesMessage("Erro: " + e.getMessage()));
        }
    }

    public void excluir() {
    FacesContext fc = FacesContext.getCurrentInstance();
        try {
            new ContatoDao().remover(contato.getId());
            contato = new Contato();
            fc.addMessage("form2", new FacesMessage("Registro Excluído!!!"));
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage("Erro: " + e.getMessage()));
            
        }
    }

    public void alterar() {
    FacesContext fc = FacesContext.getCurrentInstance();
        try {
            new ContatoDao().atualizar(contato);
            contato = new Contato();
             fc.addMessage(null, new FacesMessage("Dados Alterados!!!"));
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage("Erro: " + e.getMessage()));
        }
    }
}
