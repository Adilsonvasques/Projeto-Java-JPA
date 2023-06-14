package modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto {

	private String autor;
	private Integer numerosDePaginas;
	
	
	public Livro(){
	}
	
	public Livro(String autor, Integer numerosDePaginas) {
		this.autor = autor;
		this.numerosDePaginas = numerosDePaginas;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getNumerosDePaginas() {
		return numerosDePaginas;
	}
	public void setNumerosDePaginas(Integer numerosDePaginas) {
		this.numerosDePaginas = numerosDePaginas;
	}
	
	
}
