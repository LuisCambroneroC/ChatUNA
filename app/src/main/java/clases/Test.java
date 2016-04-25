package clases;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

/**
 * Created by LuisCambronero on 22/04/2016.
 */
public class Test {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://WS/";
    //Webservice URL - WSDL File location
    private static String URL = "http://10.0.3.2:9090/WSUNAchat/WSUNAchat?wsdl"; //si es celular aparte pone la ip de la pc
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://WS/WSUNAchat/";

    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    static PropertyInfo getNewPropertyInfo(String pNombre,Object pValor,Class pClase)
    {
        PropertyInfo pi = new PropertyInfo();
        pi.setName(pNombre);
        pi.setValue(pValor);
        pi.setType(pClase);

        return pi;
    }

    public static List<Usuario> invikeListarUsuarios(){
        List<Usuario> usuarios = new ArrayList<Usuario>();

        SoapObject request = new SoapObject(NAMESPACE,"ListarUsuarios");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //Agregar propiedades
        request.addProperty(getNewPropertyInfo("prop1","algo",String.class));

        //Setear la salida
        envelope.setOutputSoapObject(request);

        //Crear http
        HttpTransportSE andHttpTransp = new HttpTransportSE(URL);

        //Hacer la llamada
        try {
            andHttpTransp.call(SOAP_ACTION+"ListarUsuaios",envelope);
        }catch (Exception e){

        }

        return usuarios;
    }
}
