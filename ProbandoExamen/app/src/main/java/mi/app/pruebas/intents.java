package mi.app.pruebas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class intents extends AppCompatActivity {
    Intent intentImplicito;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);
        Button contactar = (Button) findViewById(R.id.contactar);
        Button agenda = (Button) findViewById(R.id.agenda);
        Button camara = (Button) findViewById(R.id.camara);
        Button ruta = (Button) findViewById(R.id.ruta);
        Button llamar = (Button) findViewById(R.id.llamar);
        Button web = (Button) findViewById(R.id.web);
        Button volver = (Button) findViewById(R.id.volver);

        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentImplicito = new Intent();
                //Nos va a permitir seleccionar una aplicacion que nos permita enviar un mensaje
                intentImplicito.setAction(Intent.ACTION_SEND);
                intentImplicito.putExtra(Intent.EXTRA_TEXT, "Texto por defecto");
                //Indicamos el tipo de cifrado o codificacion
                intentImplicito.setType("text/plain");
                //Al estar enviando un email, tienes que poner un asunto y el email al cual enviar el mensaje
                intentImplicito.putExtra(Intent.EXTRA_SUBJECT, "Por defecto ");
                intentImplicito.putExtra(Intent.EXTRA_EMAIL, new String[]{"https://palomatica.info/"});
                //Esto es opcional, va a comprobar que la actividad que queremos realizar no "crashea" al ejecutarla
                if (intentImplicito.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentImplicito);
                }
            }
        });
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        ruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 40.459418, -3.717462"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+34913980300"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.palomafp.org/"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),MainActivity.class);
                startActivity(comunicacion);
            }
        });
    }
}
