package br.com.carlosclaro.calculadora_final;

import android.app.Activity;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;

public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    TextView resultado;
    TextView historico;

    double numeroAnterior;
    double resultadoParcial;
    String ultimaAcao;

    String numeroParcial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        historico = (TextView) findViewById(R.id.Historico);
        resultado = (TextView) findViewById(R.id.Resultado);
        iniciaVariaveis();
    }

    public void iniciaVariaveis(){
        resultadoParcial = 0;
        numeroParcial = "";
        numeroAnterior = 0;
        ultimaAcao = "";
        System.out.println("inicou");
    }

    public void Resetar(View view)
    {
        Button botao = (Button)view;
        String acao = botao.getText().toString();
        System.out.println("reseta");
        System.out.println(acao);
        historico.setText(" ");
        resultado.setText(" ");
        iniciaVariaveis();

    }

    private double setCalculo( )
    {
        System.out.println(resultadoParcial);
        System.out.println(ultimaAcao);
        System.out.println(numeroAnterior);
        double valor = 0;
        switch( ultimaAcao ) {
            case "+":
                valor = resultadoParcial + numeroAnterior;
                break;
            case "-":
                valor = resultadoParcial - numeroAnterior;
                break;
            case "/":
                valor = resultadoParcial / numeroAnterior;
                break;
            case "*":
                valor = resultadoParcial * numeroAnterior;
                break;
            default:
                break;
        }
        return valor;
    }

    public void CarregaSimbolo(View view) {
        Button botao = (Button)view;
        String acao = botao.getText().toString();

        System.out.println(acao);

        String valorHistorico = historico.getText().toString();
        String valorResultado = resultado.getText().toString();

        if ( numeroParcial != "" )
        {

            numeroAnterior = Double.parseDouble(numeroParcial);
            numeroParcial = "";
            historico.setText( valorHistorico + acao );
            if ( ultimaAcao == "" )
            {
                ultimaAcao = acao;
            }
            else
            {
                double valor;
                valor = setCalculo();
                String tot = new Double(valor).toString();
                resultado.setText( tot );
                resultadoParcial = valor;
                numeroParcial = "";
                if ( acao == "=")
                {
                    ultimaAcao = "";
                }
                else
                {
                    ultimaAcao = acao;
                }

            }

        }

    }


    public void CarregaNumero(View view) {
        Button botao = (Button)view;
        System.out.println(numeroParcial);
        String valorHistorico = historico.getText().toString();
        String valorResultado = resultado.getText().toString();
        String numero = botao.getText().toString();
        numeroParcial = numeroParcial + numero;
        System.out.println(numeroParcial);
        historico.setText( valorHistorico + numero );
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.carlosclaro.calculadora_final/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.carlosclaro.calculadora_final/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
