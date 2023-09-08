package co.edu.unipiloto.stopwatch0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Determinar si el cronometro esta en ejecucion
    private boolean running;
    //Contar los segundos cuando el cronometro esta en ejecucion
    private int seconds=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Invocar el hilo para cronometrar
        runTimer();
    }

    public void onClickStart(View view) {
        running=true;

    }

    public void onClickStop(View view) {
        running=false;
    }

    public void onClickReset(View view) {
        running=false;
        seconds=0;
    }

    private void runTimer(){
        //Relacionar un objeto TextView con el elemento grafico correspondiente
        TextView timeView=(TextView) findViewById(R.id.time_view);
        //Declarar un handles para manejar el tiempo en el hilo de ejecución
        Handler handler= new Handler();
        //INvocar el metodo post e instanciar el runnable
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });


    }
}