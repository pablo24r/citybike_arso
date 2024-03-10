
package es.um.arso;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@SuppressWarnings("deprecation")
@WebServiceClient(name = "ServicioAlquileresService", targetNamespace = "http://um.es/arso", wsdlLocation = "http://localhost:8080/ws/alquileres?wsdl")
public class ServicioAlquileresService
    extends Service
{

    private final static URL SERVICIOALQUILERESSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICIOALQUILERESSERVICE_EXCEPTION;
    private final static QName SERVICIOALQUILERESSERVICE_QNAME = new QName("http://um.es/arso", "ServicioAlquileresService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ws/alquileres?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICIOALQUILERESSERVICE_WSDL_LOCATION = url;
        SERVICIOALQUILERESSERVICE_EXCEPTION = e;
    }

    public ServicioAlquileresService() {
        super(__getWsdlLocation(), SERVICIOALQUILERESSERVICE_QNAME);
    }

    public ServicioAlquileresService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICIOALQUILERESSERVICE_QNAME, features);
    }

    public ServicioAlquileresService(URL wsdlLocation) {
        super(wsdlLocation, SERVICIOALQUILERESSERVICE_QNAME);
    }

    public ServicioAlquileresService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICIOALQUILERESSERVICE_QNAME, features);
    }

    public ServicioAlquileresService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServicioAlquileresService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IServicioAlquileres
     */
    @WebEndpoint(name = "ServicioAlquileresPort")
    public IServicioAlquileres getServicioAlquileresPort() {
        return super.getPort(new QName("http://um.es/arso", "ServicioAlquileresPort"), IServicioAlquileres.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IServicioAlquileres
     */
    @WebEndpoint(name = "ServicioAlquileresPort")
    public IServicioAlquileres getServicioAlquileresPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://um.es/arso", "ServicioAlquileresPort"), IServicioAlquileres.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICIOALQUILERESSERVICE_EXCEPTION!= null) {
            throw SERVICIOALQUILERESSERVICE_EXCEPTION;
        }
        return SERVICIOALQUILERESSERVICE_WSDL_LOCATION;
    }

}
