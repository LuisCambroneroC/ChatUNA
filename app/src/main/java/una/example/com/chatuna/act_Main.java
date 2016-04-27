package una.example.com.chatuna;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import clases.WebService;
import entidades.Usuario;

//Prueba de commit desde Cristhian

public class act_Main extends AppCompatActivity {

    ListView lvUsuarios;
    Button btnListarUsuaios;
    List<Usuario> listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);

        lvUsuarios = (ListView)findViewById(R.id.lvUsuarios);
        btnListarUsuaios = (Button)findViewById(R.id.btnListarUsuarios);

        btnListarUsuaios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TareaListar tl = new TareaListar();
                tl.execute();
            }
        });
    }

    class UsuarioAdapter extends ArrayAdapter<Usuario>{

        public UsuarioAdapter(Context context, List<Usuario> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Usuario usuario = getItem(position);

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario,parent,false);
            }

            TextView lblNombre = (TextView)convertView.findViewById(R.id.lblNombre);
            TextView lblNumero = (TextView)convertView.findViewById(R.id.lblNumero);

            lblNombre.setText(usuario.getUsuNombre());
            lblNumero.setText(usuario.getUsuNumero());

            return  convertView;
        }
    }

    class TareaListar extends AsyncTask<String, Void, Void>{

        public TareaListar() {
        }

        @Override
        protected Void doInBackground(String... params) {
            listaUsuarios = WebService.invokeListarUsuarios();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            UsuarioAdapter ua = new UsuarioAdapter(act_Main.this,listaUsuarios);
            lvUsuarios.setAdapter(ua);
        }
    }


}


