package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the COMENTARIO database table.
 * 
 */
@Entity
@Table(name="COMENTARIO")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=250)
	private String contenido;

	@Column(length=20)
	private String usuario;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="ARTICULO_ID", nullable=false)
	private Articulo articulo;

	public Comentario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}