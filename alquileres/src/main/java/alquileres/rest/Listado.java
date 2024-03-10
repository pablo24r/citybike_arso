package alquileres.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import alquileres.servicio.UsuarioResumen;

@XmlRootElement
public class Listado {
	public static class ResumenExtendido {
		private String url;
		private UsuarioResumen resumen;
		// Métodos get y set
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public UsuarioResumen getResumen() {
			return resumen;
		}
		public void setResumen(UsuarioResumen resumen) {
			this.resumen = resumen;
		}
	}
		

	private List<ResumenExtendido> usuario;
	// Métodos get y set

	public List<ResumenExtendido> getUsuario() {
		return usuario;
	}

	public void setAlquiler(List<ResumenExtendido> usuario) {
		this.usuario = usuario;
	}
	
	
}