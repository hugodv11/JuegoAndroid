package com.example.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.HashMap;

public class EscenaMejoras extends Escenas {

    Rect btnVolver, mejoraPulsación, mejoraAutoclick, mejoraTiempoAutoClick;

    public EscenaMejoras(int numEscena, Context context, int altoPantalla, int anchoPantalla) {
        super(numEscena, context, altoPantalla, anchoPantalla);
        aux = BitmapFactory.decodeResource(context.getResources(),R.drawable.mejoras);
        bitmapFondo = aux.createScaledBitmap(aux,anchoPantalla, altoPantalla,true);

        //Botones
        btnVolver = new Rect(0, 0, 100, 100);
        mejoraPulsación = new Rect(100, altoPantalla - 300, 300, altoPantalla - 50);
        mejoraTiempoAutoClick =new Rect(700, altoPantalla -300, 900, altoPantalla - 50);
        mejoraAutoclick = new Rect(400, altoPantalla - 300, 600, altoPantalla - 50);

        //Muy importante mas ideas para mejorar parte clicker, para hacer más profunda la progresión

        //Crear colisiones y particulas en la pantalla principal, cuando se pulsa el boton salen disparadas
        //x cosas(pueden ser diferentes skins que dependan de los logros) de forma aleatoria pero controlada
        //que vayan disminuyendo su transparencia hasta que desaparezcan, y mientras, que reboten entre si y entre
        //las paredes.

        //Crear una clase(o metodo, pero tiene que ser personalizable)que genere "pantalla de avisos" o ventanas emergentes o como quieras llamarlo
        //Una forma seria pintar un fondo de color x por encima de la pantalla con cierta transparencia
        //y luego por encima un rectangulo con el mensaje/toma de decisiones o lo que sea.



        //Poner boton dev en la pantalla principal para sumar dinero y probar las cosas(Más o menos).


    }//end constructor

    @Override
    public void dibujar(Canvas c) {
        //Aqui dibujamos
        c.drawBitmap(bitmapFondo,0, 0,null);
        c.drawRect(btnVolver, pincelRec);
        c.drawRect(mejoraPulsación, pincelRec);
        c.drawRect(mejoraAutoclick, pincelRec);
        c.drawRect(mejoraTiempoAutoClick, pincelRec);
        pincelTxt.setColor(Color.WHITE);
        c.drawText("Coste : " + costoMejoraPulsacion, 175, altoPantalla - 350, pincelTxt);
        c.drawText("Coste : " + costoMejoraAutoclick, 525, altoPantalla - 350, pincelTxt);
        c.drawText("Coste : " + costoTiempoAutoclick, 900, altoPantalla - 350, pincelTxt);
        super.dibujar(c);
    }//end dibujar

    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
    }


    @Override
    public int onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(btnVolver.contains(x, y)) {
            return 1;
        }//end if
        if(mejoraPulsación.contains(x, y)) {
            if(money >= costoMejoraPulsacion){
                dineroPulsacion += 2;
                money -= costoMejoraPulsacion;
                costoMejoraPulsacion *= 2;
                //editor.putInt("money", money);
                //editor.putInt("dineroPulsacion", dineroPulsacion);
               // editor.putInt("costoMejoraPulsacion", costoMejoraPulsacion);
                //editor.commit();
            }//end if
        }//end if

        if(mejoraAutoclick.contains(x, y)) {
            if(money >= costoMejoraAutoclick){
                autoclick++;
                money -= costoMejoraAutoclick;
                costoMejoraAutoclick *= 4;
                //editor.putInt("money", money);
                //editor.putInt("autoclick", autoclick);
                //editor.putInt("costoMejoraAutoclick", costoMejoraAutoclick);
                //editor.commit();
            }//end if
        }//end if

        if(mejoraTiempoAutoClick.contains(x, y)){
            if(money >= costoTiempoAutoclick) {
                tiempoAutoclick -= 500;
                money -= costoTiempoAutoclick;
                costoTiempoAutoclick *= 5;
                //editor.putInt("money", money);
                //editor.putInt("tiempoAutoClick", tiempoAutoclick);
                //editor.putInt("costoTiempoAutoClick", costoTiempoAutoclick);
                //editor.commit();
            }//end if
        }//end if

        return numEscena;
    }//end onTouchEvent





}//end class EscenaMejoras
