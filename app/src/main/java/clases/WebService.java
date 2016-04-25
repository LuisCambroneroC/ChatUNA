package clases;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import entidades.Usuario;

/**
 * Created by LuisCambronero on 14/04/2016.
 */
public class WebService {
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

    /**
     * Procedimiento para listar usuarios.
     * @return Lista de usuarios.
     */
    public static List<Usuario> invokeListarUsuarios()
    {
        //Lista de usuarios a retornar
        List<Usuario> usuarios = new ArrayList<Usuario>();
        SoapObject request = new SoapObject(NAMESPACE,"ListarUsuarios");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            //Llamada al WS.
            androidHttpTransport.call(SOAP_ACTION + "ListarUsuarios",envelope);

            //Recolectar los datos.
            Vector<SoapObject> rs = (Vector<SoapObject>)envelope.getResponse();

            if (rs != null)
                for (SoapObject so : rs){
                  Usuario u = new Usuario(0,so.getPropertyAsString("usuNombre"),so.getPropertyAsString("usuNumero"),
                          so.getPropertyAsString("usuEstadoaccion"),so.getPropertyAsString("usuEstadoconexion"),
                          so.getPropertyAsString("usuEstado"));

                          if (so.hasProperty("usuImg"))u.setUsuImg(so.getPropertyAsString("usuImg"));

                  usuarios.add(u);
                }

        }catch (Exception e){
            Log.wtf("Error",e.getMessage());}

        return usuarios;
    }

    public static String invokeRegistrarUsuario(String pNombre,String pNumero, String pImg){

        String respStr = "";

        SoapObject request = new SoapObject(NAMESPACE,"RegistrarUsuario");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        HttpTransportSE andHttpTransp = new HttpTransportSE(URL);

        PropertyInfo pi = new PropertyInfo();
        pi.setName("pNombre"); pi.setValue(pNumero); pi.setType(String.class);

        request.addProperty(getNewPropertyInfo("pNombre",pNombre,String.class));
        request.addProperty(getNewPropertyInfo("pNumero", pNumero, String.class));
        request.addProperty(getNewPropertyInfo("pImg",pImg,String.class));

        envelope.setOutputSoapObject(request);

        try {

            andHttpTransp.call(SOAP_ACTION + "RegistrarUsuario" ,envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
            respStr = response.toString();

        }catch (Exception e)
        {
           Log.wtf("Error Insertar", e.getMessage());
        }

        return respStr;
    }
}
