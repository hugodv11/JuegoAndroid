package com.example.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.core.view.GestureDetectorCompat;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;

public class EscenaPrincipal extends Escenas {

    Rect pulsador, btnMejora, btnOpciones;
    //botones de prueba
    Rect btnDinero, btnTiempo;
    Timer timer;
    int gap=2000;
    long tempTiempo=0;

    Boolean aviso;




    public EscenaPrincipal(int numEscena, Context context, int altoPantalla, int anchoPantalla) {
        super(numEscena, context, altoPantalla, anchoPantalla);
        aux = BitmapFactory.decodeResource(context.getResources(),R.drawable.oficina);
        bitmapFondo = aux.createScaledBitmap(aux,anchoPantalla, altoPantalla,true);

        //Cuadrados que se utilizaran para saber si se a pulsado en ellos
        pulsador = new Rect(anchoPantalla / 3,(altoPantalla/3) * 2,anchoPantalla - anchoPantalla / 3,altoPantalla - anchoPantalla / 3);
        btnMejora = new Rect(anchoPantalla - 100, 0, anchoPantalla, 100);
        btnOpciones = new Rect(0, 0, 100,100);
        btnDinero = new Rect(150, 150, 300,300);
        btnTiempo = new Rect(150, 400, 300,550);

        //Timer pero esta vez tipo oficial que se supone que ya crea su propio hilo
        TimerTask timerTask = new TimerTask() {
            //Dentro de run es donde se pone todo el codigo
            public void run() {
                //Codigo del timer
                money += autoclick;
            }//end run
        };
        // Aquí se pone en marcha el timer cada segundo.
        timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 2000 milisegundos
        timer.scheduleAtFixedRate(timerTask, 0, tiempoAutoclick);

        //Crea un cuadro de dialogo
        avisoDineroOffline = new pantallaAvisos(altoPantalla,anchoPantalla, "Has ganado " + moneyOffline + " mientras estabas fuera!", context, pincelFondo, pincelCuadro, pincelTexto);
        cuadroConBotones = new pantallaAvisos(altoPantalla,anchoPantalla, "Hola buenas tardes", context, pincelFondo, pincelCuadro, pincelTexto);
        aviso = false;

    }//end constructor

    @Override
    public void dibujar(Canvas c) {
        try {

            pincelFondo.setAlpha(150);
            pincelCuadro.setColor(Color.BLACK);
            pincelTexto.setColor(Color.WHITE);
            pincelTexto.setTextAlign(Paint.Align.CENTER);
            pincelTexto.setTextSize(40);
            pincelTexto.setAntiAlias(true);


            c.drawBitmap(bitmapFondo,0, 0,null);
            c.drawRect(pulsador,pincelRec);
            c.drawRect(btnMejora, pincelRec);
            c.drawRect(btnOpciones, pincelRec);
            c.drawRect(btnDinero, pincelRec);
            c.drawRect(btnTiempo, pincelRec);





            //Si cuadroDialogo = true se dibuja el cuadro de dialogo
            if(cuadroDialogo){
                avisoDineroOffline.cuadroEstandar(c);
            }//end if

            if(aviso){
                cuadroConBotones.cuadroBotones(c);
            }//end if


        }catch(Exception e){
            e.printStackTrace();
        }//end catch
        super.dibujar(c);
    }//end method dibujar

    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
    }



    @Override
    public int onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();


        if(aviso) {
            //Cuando se toca la pantalla se cierra el cuadro de dialogo
            if (cuadroDialogo)
                cuadroDialogo = false;

            if(cuadroConBotones.btnAceptar.contains(x, y)){
                //Acciones para aceptar
                aviso = false;
            }//end if
            if(cuadroConBotones.btnCancelar.contains(x, y)){
                //Acciones para cancelar
                aviso = false;
            }//end if

            return numEscena;
        }//end if
        else {
            if (btnOpciones.contains(x, y)) {
                timer.cancel();
                timer.purge();
                return 4;
            }//end if
            if (pulsador.contains(x, y)) {
                money += dineroPulsacion;
                editor.putInt("money", money).commit();
                return numEscena;
            }//end if
            if (btnMejora.contains(x, y)) {
                timer.cancel();
                timer.purge();
                return 2;
            }//end if
            if (btnDinero.contains(x, y)) {
                money += 1000;
                editor.putInt("money", money).commit();
            }//end if
        }//end else
        if(btnTiempo.contains(x, y)){
            aviso = true;
        }//end btnTiempo
        return numEscena;

   }//end onTouchEvent






}//end class EscenaPrincipal
