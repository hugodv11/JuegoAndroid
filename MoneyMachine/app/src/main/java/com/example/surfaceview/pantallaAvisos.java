package com.example.surfaceview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;

//Clase que se utilizará para crear ventanas emergentes en la app
public class pantallaAvisos {

    private int altoPantalla, anchoPantalla;
    private String texto;
    private Context context;
    private Paint pincelFondo, pincelCuadro, pincelTexto;
    private Bitmap bitmapFondo;
    private Bitmap aux;
    private Rect cuadro;


    public pantallaAvisos(int altoPantalla, int anchoPantalla, String texto, Context context, Paint pincelFondo, Paint pincelCuadro, Paint pincelTexto) {
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.texto = texto;
        this.context = context;
        this.pincelFondo = pincelFondo;
        this.pincelCuadro = pincelCuadro;
        this.pincelTexto = pincelTexto;
    }//end constructor

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Paint getPincelFondo() {
        return pincelFondo;
    }

    public void setPincelFondo(Paint pincelFondo) {
        this.pincelFondo = pincelFondo;
    }

    public Paint getPincelCuadro() {
        return pincelCuadro;
    }

    public void setPincelCuadro(Paint pincelCuadro) {
        this.pincelCuadro = pincelCuadro;
    }

    public Paint getPincelTexto() {
        return pincelTexto;
    }

    public void setPincelTexto(Paint pincelTexto) {
        this.pincelTexto = pincelTexto;
    }

    public Bitmap getBitmapFondo() {
        return bitmapFondo;
    }

    public void setBitmapFondo(Bitmap bitmapFondo) {
        this.bitmapFondo = bitmapFondo;
    }

    //METODOS

    public void cuadroEstandar(Canvas c){
        aux = BitmapFactory.decodeResource(context.getResources(), R.drawable.mejoras);
        bitmapFondo = aux.createScaledBitmap(aux,anchoPantalla, altoPantalla,true);
        cuadro = new Rect(anchoPantalla/2 - 300, altoPantalla/2 - 300, anchoPantalla/2 + 300, altoPantalla/2 + 300);

        //Zona de dibujado
        c.drawBitmap(bitmapFondo, 0, 0, pincelFondo);
        c.drawRect(cuadro, pincelCuadro);
        c.drawText(texto, anchoPantalla/2, altoPantalla/2, pincelTexto);
    }//end method cuadroEstandar


















}//end class PantallaAvisos
