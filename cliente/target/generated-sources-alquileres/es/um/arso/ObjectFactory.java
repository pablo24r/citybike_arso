
package es.um.arso;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.um.arso package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EntidadNoEncontrada_QNAME = new QName("http://um.es/arso", "EntidadNoEncontrada");
    private final static QName _RepositorioException_QNAME = new QName("http://um.es/arso", "RepositorioException");
    private final static QName _Alquilar_QNAME = new QName("http://um.es/arso", "alquilar");
    private final static QName _AlquilarResponse_QNAME = new QName("http://um.es/arso", "alquilarResponse");
    private final static QName _ConfirmarReserva_QNAME = new QName("http://um.es/arso", "confirmarReserva");
    private final static QName _ConfirmarReservaResponse_QNAME = new QName("http://um.es/arso", "confirmarReservaResponse");
    private final static QName _DejarBicicleta_QNAME = new QName("http://um.es/arso", "dejarBicicleta");
    private final static QName _DejarBicicletaResponse_QNAME = new QName("http://um.es/arso", "dejarBicicletaResponse");
    private final static QName _HistorialUsuario_QNAME = new QName("http://um.es/arso", "historialUsuario");
    private final static QName _HistorialUsuarioResponse_QNAME = new QName("http://um.es/arso", "historialUsuarioResponse");
    private final static QName _LiberarBloqueo_QNAME = new QName("http://um.es/arso", "liberarBloqueo");
    private final static QName _LiberarBloqueoResponse_QNAME = new QName("http://um.es/arso", "liberarBloqueoResponse");
    private final static QName _ObtenerId_QNAME = new QName("http://um.es/arso", "obtenerId");
    private final static QName _ObtenerIdResponse_QNAME = new QName("http://um.es/arso", "obtenerIdResponse");
    private final static QName _RecuperarAlquiler_QNAME = new QName("http://um.es/arso", "recuperarAlquiler");
    private final static QName _RecuperarAlquilerResponse_QNAME = new QName("http://um.es/arso", "recuperarAlquilerResponse");
    private final static QName _Reservar_QNAME = new QName("http://um.es/arso", "reservar");
    private final static QName _ReservarResponse_QNAME = new QName("http://um.es/arso", "reservarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.um.arso
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntidadNoEncontrada }
     * 
     */
    public EntidadNoEncontrada createEntidadNoEncontrada() {
        return new EntidadNoEncontrada();
    }

    /**
     * Create an instance of {@link RepositorioException }
     * 
     */
    public RepositorioException createRepositorioException() {
        return new RepositorioException();
    }

    /**
     * Create an instance of {@link Alquilar }
     * 
     */
    public Alquilar createAlquilar() {
        return new Alquilar();
    }

    /**
     * Create an instance of {@link AlquilarResponse }
     * 
     */
    public AlquilarResponse createAlquilarResponse() {
        return new AlquilarResponse();
    }

    /**
     * Create an instance of {@link ConfirmarReserva }
     * 
     */
    public ConfirmarReserva createConfirmarReserva() {
        return new ConfirmarReserva();
    }

    /**
     * Create an instance of {@link ConfirmarReservaResponse }
     * 
     */
    public ConfirmarReservaResponse createConfirmarReservaResponse() {
        return new ConfirmarReservaResponse();
    }

    /**
     * Create an instance of {@link DejarBicicleta }
     * 
     */
    public DejarBicicleta createDejarBicicleta() {
        return new DejarBicicleta();
    }

    /**
     * Create an instance of {@link DejarBicicletaResponse }
     * 
     */
    public DejarBicicletaResponse createDejarBicicletaResponse() {
        return new DejarBicicletaResponse();
    }

    /**
     * Create an instance of {@link HistorialUsuario }
     * 
     */
    public HistorialUsuario createHistorialUsuario() {
        return new HistorialUsuario();
    }

    /**
     * Create an instance of {@link HistorialUsuarioResponse }
     * 
     */
    public HistorialUsuarioResponse createHistorialUsuarioResponse() {
        return new HistorialUsuarioResponse();
    }

    /**
     * Create an instance of {@link LiberarBloqueo }
     * 
     */
    public LiberarBloqueo createLiberarBloqueo() {
        return new LiberarBloqueo();
    }

    /**
     * Create an instance of {@link LiberarBloqueoResponse }
     * 
     */
    public LiberarBloqueoResponse createLiberarBloqueoResponse() {
        return new LiberarBloqueoResponse();
    }

    /**
     * Create an instance of {@link ObtenerId }
     * 
     */
    public ObtenerId createObtenerId() {
        return new ObtenerId();
    }

    /**
     * Create an instance of {@link ObtenerIdResponse }
     * 
     */
    public ObtenerIdResponse createObtenerIdResponse() {
        return new ObtenerIdResponse();
    }

    /**
     * Create an instance of {@link RecuperarAlquiler }
     * 
     */
    public RecuperarAlquiler createRecuperarAlquiler() {
        return new RecuperarAlquiler();
    }

    /**
     * Create an instance of {@link RecuperarAlquilerResponse }
     * 
     */
    public RecuperarAlquilerResponse createRecuperarAlquilerResponse() {
        return new RecuperarAlquilerResponse();
    }

    /**
     * Create an instance of {@link Reservar }
     * 
     */
    public Reservar createReservar() {
        return new Reservar();
    }

    /**
     * Create an instance of {@link ReservarResponse }
     * 
     */
    public ReservarResponse createReservarResponse() {
        return new ReservarResponse();
    }

    /**
     * Create an instance of {@link Alquiler }
     * 
     */
    public Alquiler createAlquiler() {
        return new Alquiler();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntidadNoEncontrada }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EntidadNoEncontrada }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "EntidadNoEncontrada")
    public JAXBElement<EntidadNoEncontrada> createEntidadNoEncontrada(EntidadNoEncontrada value) {
        return new JAXBElement<EntidadNoEncontrada>(_EntidadNoEncontrada_QNAME, EntidadNoEncontrada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RepositorioException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RepositorioException }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "RepositorioException")
    public JAXBElement<RepositorioException> createRepositorioException(RepositorioException value) {
        return new JAXBElement<RepositorioException>(_RepositorioException_QNAME, RepositorioException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Alquilar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Alquilar }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "alquilar")
    public JAXBElement<Alquilar> createAlquilar(Alquilar value) {
        return new JAXBElement<Alquilar>(_Alquilar_QNAME, Alquilar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlquilarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AlquilarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "alquilarResponse")
    public JAXBElement<AlquilarResponse> createAlquilarResponse(AlquilarResponse value) {
        return new JAXBElement<AlquilarResponse>(_AlquilarResponse_QNAME, AlquilarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarReserva }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmarReserva }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "confirmarReserva")
    public JAXBElement<ConfirmarReserva> createConfirmarReserva(ConfirmarReserva value) {
        return new JAXBElement<ConfirmarReserva>(_ConfirmarReserva_QNAME, ConfirmarReserva.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarReservaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmarReservaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "confirmarReservaResponse")
    public JAXBElement<ConfirmarReservaResponse> createConfirmarReservaResponse(ConfirmarReservaResponse value) {
        return new JAXBElement<ConfirmarReservaResponse>(_ConfirmarReservaResponse_QNAME, ConfirmarReservaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DejarBicicleta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DejarBicicleta }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "dejarBicicleta")
    public JAXBElement<DejarBicicleta> createDejarBicicleta(DejarBicicleta value) {
        return new JAXBElement<DejarBicicleta>(_DejarBicicleta_QNAME, DejarBicicleta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DejarBicicletaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DejarBicicletaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "dejarBicicletaResponse")
    public JAXBElement<DejarBicicletaResponse> createDejarBicicletaResponse(DejarBicicletaResponse value) {
        return new JAXBElement<DejarBicicletaResponse>(_DejarBicicletaResponse_QNAME, DejarBicicletaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistorialUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HistorialUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "historialUsuario")
    public JAXBElement<HistorialUsuario> createHistorialUsuario(HistorialUsuario value) {
        return new JAXBElement<HistorialUsuario>(_HistorialUsuario_QNAME, HistorialUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistorialUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HistorialUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "historialUsuarioResponse")
    public JAXBElement<HistorialUsuarioResponse> createHistorialUsuarioResponse(HistorialUsuarioResponse value) {
        return new JAXBElement<HistorialUsuarioResponse>(_HistorialUsuarioResponse_QNAME, HistorialUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LiberarBloqueo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LiberarBloqueo }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "liberarBloqueo")
    public JAXBElement<LiberarBloqueo> createLiberarBloqueo(LiberarBloqueo value) {
        return new JAXBElement<LiberarBloqueo>(_LiberarBloqueo_QNAME, LiberarBloqueo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LiberarBloqueoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LiberarBloqueoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "liberarBloqueoResponse")
    public JAXBElement<LiberarBloqueoResponse> createLiberarBloqueoResponse(LiberarBloqueoResponse value) {
        return new JAXBElement<LiberarBloqueoResponse>(_LiberarBloqueoResponse_QNAME, LiberarBloqueoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerId }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "obtenerId")
    public JAXBElement<ObtenerId> createObtenerId(ObtenerId value) {
        return new JAXBElement<ObtenerId>(_ObtenerId_QNAME, ObtenerId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "obtenerIdResponse")
    public JAXBElement<ObtenerIdResponse> createObtenerIdResponse(ObtenerIdResponse value) {
        return new JAXBElement<ObtenerIdResponse>(_ObtenerIdResponse_QNAME, ObtenerIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecuperarAlquiler }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RecuperarAlquiler }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "recuperarAlquiler")
    public JAXBElement<RecuperarAlquiler> createRecuperarAlquiler(RecuperarAlquiler value) {
        return new JAXBElement<RecuperarAlquiler>(_RecuperarAlquiler_QNAME, RecuperarAlquiler.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecuperarAlquilerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RecuperarAlquilerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "recuperarAlquilerResponse")
    public JAXBElement<RecuperarAlquilerResponse> createRecuperarAlquilerResponse(RecuperarAlquilerResponse value) {
        return new JAXBElement<RecuperarAlquilerResponse>(_RecuperarAlquilerResponse_QNAME, RecuperarAlquilerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reservar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Reservar }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "reservar")
    public JAXBElement<Reservar> createReservar(Reservar value) {
        return new JAXBElement<Reservar>(_Reservar_QNAME, Reservar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReservarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://um.es/arso", name = "reservarResponse")
    public JAXBElement<ReservarResponse> createReservarResponse(ReservarResponse value) {
        return new JAXBElement<ReservarResponse>(_ReservarResponse_QNAME, ReservarResponse.class, null, value);
    }

}
