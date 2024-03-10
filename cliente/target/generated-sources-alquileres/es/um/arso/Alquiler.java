
package es.um.arso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para alquiler complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="alquiler"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fin" type="{http://um.es/arso}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="idBicicleta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="inicio" type="{http://um.es/arso}localDateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alquiler", propOrder = {
    "fin",
    "idBicicleta",
    "inicio"
})
public class Alquiler {

    protected LocalDateTime fin;
    protected String idBicicleta;
    protected LocalDateTime inicio;

    /**
     * Obtiene el valor de la propiedad fin.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getFin() {
        return fin;
    }

    /**
     * Define el valor de la propiedad fin.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setFin(LocalDateTime value) {
        this.fin = value;
    }

    /**
     * Obtiene el valor de la propiedad idBicicleta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBicicleta() {
        return idBicicleta;
    }

    /**
     * Define el valor de la propiedad idBicicleta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBicicleta(String value) {
        this.idBicicleta = value;
    }

    /**
     * Obtiene el valor de la propiedad inicio.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getInicio() {
        return inicio;
    }

    /**
     * Define el valor de la propiedad inicio.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setInicio(LocalDateTime value) {
        this.inicio = value;
    }

}
