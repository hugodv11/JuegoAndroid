package com.example.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class EscenaPrincipal extends Escenas {

    Rect pulsador, btnMejora, btnOpciones;
    //botones de prueba
    Rect btnDinero, btnTiempo;
    Timer timer;
    int gap=2000;
    long tempTiempo=0;

    //Variables para el movimiento del texto por pantalla
    //cuando se pulsa el boton
    Point posicion;
    int alpha;
    int randomPosX;

    Boolean aviso, pulsacion;




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

        //Cuadros de dialogo
        avisoDineroOffline = new pantallaAvisos(altoPantalla,anchoPantalla, "Has ganado " + moneyOffline + " mientras estabas fuera!", context, pincelFondo, pincelCuadro, pincelTexto);
        cuadroConBotones = new pantallaAvisos(altoPantalla,anchoPantalla, "Hola buenas tardes", context, pincelFondo, pincelCuadro, pincelTexto);
        randomPosX = new Random().nextInt(pulsador.width()) + anchoPantalla/3;
        Log.i("tiempo", "Random : " + randomPosX);
        posicion = new Point(randomPosX, (altoPantalla/3) * 2);
        alpha = 255;
        pulsacion = false;
        //Aviso es una booleana que controla cuando se dibujan los cuadros de información, dependiendo de diferentes eventos
        //cambiaremos esta booleana a true.
        aviso = false;

    }//end constructor

    @Override
    public void dibujar(Canvas c) {
        try {
            //setAlpha va desde 0(casper) hasta 255(totalmente visible)
            pincelFondo.setAlpha(150);
            pincelCuadro.setColor(Color.BLACK);
            pincelTexto.setColor(Color.WHITE);
            pincelTexto.setTextAlign(Paint.Align.CENTER);
            pincelTexto.setTextSize(40);
            pincelTexto.setAntiAlias(true);

            pincelPrueba.setColor(Color.BLACK);
            pincelPrueba.setTextSize(70);
            //pincelPrueba.setAlpha(alpha);

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
            if(pulsacion){
                c.drawText("1", posicion.x,posicion.y, pincelPrueba);
            }


        }catch(Exception e){
            e.printStackTrace();
        }//end catch
        super.dibujar(c);
    }//end method dibujar

    @Override
    public void actualizarFisica() {
        //super.actualizarFisica();
        //Movimiento del texto por pantalla
        if(pulsacion) {
            while (alpha > 0) {
                alpha -= 1;
            }//end while
           // pulsacion = false;
        }//end if


    }//end actualizarFisica



    @Override
    public int onTouchEvent(MotionEvent event) {
        //Tarea pendiente quitar todos los editor.put de cada touch y simplemente
        //ponerlos solo cuando se vaya a cambiar de pantalla

        int x = (int)event.getX();
        int y = (int)event.getY();
        if(aviso) {
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
                pulsacion = true;
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

        //Cuando se toca la pantalla se cierra el cuadro de dialogo
        if (cuadroDialogo)
            cuadroDialogo = false;

        return numEscena;

   }//end onTouchEvent






}//end class EscenaPrincipal
