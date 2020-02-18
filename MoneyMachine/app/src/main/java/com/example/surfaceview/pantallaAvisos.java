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
    private Bitmap bitmapFondo, aux;
    private Rect cuadro;
    public Rect btnAceptar, btnCancelar;


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




    //El bitmap fondo y auxiliar sirven para poder poner la imagen que va a aparecer con transpariencia
    //Necesario que el texto no se salga del cuadro(no creo que lo haga de forma correcta, probare cuantas letras
    //entran mas o menos cuando cambie la fuente, y añadire un salto de linea y pa diante)
    //Todos los colores se pueden cambiar con los pinceles


    //METODOS

    //Metodo que dibuja un cuadro de dialogo informativo, sin botones y sin ser interactuable
    public void cuadroEstandar(Canvas c){
        aux = BitmapFactory.decodeResource(context.getResources(), R.drawable.mejoras);
        bitmapFondo = aux.createScaledBitmap(aux,anchoPantalla, altoPantalla,true);
        cuadro = new Rect(anchoPantalla/2 - 300, altoPantalla/2 - 300, anchoPantalla/2 + 300, altoPantalla/2 + 300);
        //Zona de dibujado
        c.drawBitmap(bitmapFondo, 0, 0, pincelFondo);
        c.drawRect(cuadro, pincelCuadro);
        c.drawText(texto, anchoPantalla/2, altoPantalla/2, pincelTexto);
    }//end method cuadroEstandar



    public void cuadroBotones(Canvas c){
        aux = BitmapFactory.decodeResource(context.getResources(), R.drawable.mejoras);
        bitmapFondo = aux.createScaledBitmap(aux,anchoPantalla, altoPantalla,true);
        cuadro = new Rect(anchoPantalla/2 - 300, altoPantalla/2 - 300, anchoPantalla/2 + 300, altoPantalla/2 + 300);
        btnAceptar = new Rect(anchoPantalla/2 - 300, altoPantalla/2 + 150, anchoPantalla/2, altoPantalla/2 + 300);
        btnCancelar = new Rect(anchoPantalla/2, altoPantalla/2 + 150, anchoPantalla/2 + 300, altoPantalla/2 + 300);


        //Zona de dibujado
        c.drawBitmap(bitmapFondo, 0, 0, pincelFondo);
        c.drawRect(cuadro, pincelCuadro);
        c.drawText(texto, anchoPantalla/2, altoPantalla/2, pincelTexto);
        pincelCuadro.setColor(Color.RED);
        c.drawRect(btnAceptar, pincelCuadro);
        c.drawRect(btnCancelar, pincelCuadro);
        int aceptCenterX = btnAceptar.centerX();
        int aceptCenterY = btnAceptar.centerY();
        int cancelCenterX = btnCancelar.centerX();
        int cancelCenterY = btnCancelar.centerY();
        c.drawText("Aceptar", aceptCenterX, aceptCenterY, pincelTexto);
        c.drawText("Cancelar", cancelCenterX, cancelCenterY, pincelTexto);

    }//end method cuadroBotones





}//end class PantallaAvisos
