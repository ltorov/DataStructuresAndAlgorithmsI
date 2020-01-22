import java.util.*;
public class RepFecha
{
    private final int dia;
    private final int mes;
    private final int anyo;
    private String fecha;

    public RepFecha(int dia, int mes, int anyo){
        this.dia=dia;
        this.mes=mes;
        this.anyo=anyo;
    }

    public RepFecha (String fecha) {
        this.fecha = fecha;
        this.dia = Integer.parseInt(fecha.substring(0,(fecha.indexOf("/"))));
        this.mes = Integer.parseInt(fecha.substring((fecha.indexOf("/"))+1,(fecha.lastIndexOf("/"))));
        this.anyo = Integer.parseInt(fecha.substring((fecha.lastIndexOf("/"))+1));
    }

    public int GetDia (){
        return dia;
    }

    public int GetMes (){
        return mes;
    } 

    public int GetAnyo (){
        return anyo;
    } 

    public String GetFecha(){
        return fecha;
    }

    public boolean Equals (String fecha2) {
        int dia2 = Integer.parseInt(fecha2.substring(0,(fecha2.indexOf("/"))));
        int mes2 = Integer.parseInt(fecha2.substring((fecha2.indexOf("/"))+1,(fecha2.lastIndexOf("/"))));
        int anyo2 = Integer.parseInt(fecha2.substring((fecha2.lastIndexOf("/"))+1));

        return ((anyo == anyo2) && (mes == mes2) && (dia == dia2));
    } 

    public int compare (String fecha2){
        int a =0;
        int dia2 = Integer.parseInt(fecha2.substring(0,(fecha2.indexOf("/"))));
        int mes2 = Integer.parseInt(fecha2.substring((fecha2.indexOf("/"))+1,(fecha2.lastIndexOf("/"))));
        int anyo2 = Integer.parseInt(fecha2.substring((fecha2.lastIndexOf("/"))+1));

        if (this.Equals(fecha2))
            a=0;
        else{
            if(anyo2 > anyo)
                a = 1;
            else{
                if(anyo2 < anyo)
                    a = -1;
                else {
                    if(mes2 > mes)
                        a = 1;
                    else {
                        if(mes2 < mes)
                            a = -1; 
                        else{
                            if(dia2 > dia)
                                a = 1;
                            else{
                                if(dia2< dia)
                                    a = -1;
                            }
                        }
                    }
                }
            }
        }
        return a;
    }
}

