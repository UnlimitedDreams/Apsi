/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.com.control;

import dao.com.modelo.Dias;
import dao.com.modelo.Dias_Ajuste;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author USR_Toshiba
 */
public class Calendario {
        ArrayList<Dias_Ajuste> Festivos = new ArrayList(); //Festivos que hay durante el año

    public Calendario() throws ClassNotFoundException {
        Traer_festivos();
    }

        
    public ArrayList<Dias_Ajuste> getFestivos() {
        return Festivos;
    }

    public void setFestivos(ArrayList<Dias_Ajuste> Festivos) {
        this.Festivos = Festivos;
    }
    
    public void Traer_festivos() throws ClassNotFoundException {
        //se traen los festivos del respetivo año para mas adelante tenerlos encuenta
        Festivos.clear();
        Control.conectar();
        Date fecha1 = new Date();
        String año = fecha1.toString();
        String año2 = año.substring((año.length() - 4), año.length());
        Control.ejecuteQuery("select festivos.* from festivos,año_festivo where\n"
                + "año_festivo.cod_año=festivos.cod_año\n"
                + "and año_festivo.año='" + año2 + "'");
        Date fecha = null;
        String fecha2 = "";
        int dia = 0, mes = 0;
        try {
            while (Control.rs.next()) {
                fecha = Control.rs.getDate(2);
                fecha2 = fecha.toString();
                dia = Integer.parseInt(fecha2.substring((fecha2.length() - 2), fecha2.length()));
                mes = Integer.parseInt(fecha2.substring((fecha2.length() - 5), fecha2.length() - 4));
                Dias_Ajuste dia1 = new Dias_Ajuste(dia, mes, Integer.parseInt(año2));
                Festivos.add(dia1);
            }
        } catch (Exception ex) {
        }
    }
    public String Numero_asesorias(int tot, String fecha,ArrayList dias) throws ParseException {
        //este metodo me calcula la fecha final de todas las asesorias
        System.out.println("numero de asesorias "+tot);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dia2 = "", mes = "", año = "";
        dia2 = fecha.substring(8, 10);
        mes = fecha.substring(5, 7);
        año = fecha.substring(0, 4);
        String dias_semana = "";
        String fecha_final = "";
        ArrayList<Dias_Ajuste> n = new ArrayList();
        if (dias.size() >= 1) {
            int tot2 = tot;
            int dia = 0;
            int num_id = 0;
            boolean p = true;
            int a = Integer.parseInt(dia2);
            int m = Integer.parseInt(mes);
            int d = Integer.parseInt(año);
            Dias_Ajuste temp = null;
            int conce = 1;
            boolean con2 = true;
            while (p) {
                //tomamos la fecha ingresada y la pasamos a un calendar
                //mandamos los valores al metodo dia_semana el cual me devuelve un arreglo con los dias qe conto
                //a esos dias qe trajo le paso el estado de true y cuanto cuantos dias me trajo
                //la variable de tot2 comienza con la cantidad de dias que se necesitan llegar
                //y a esa variable de tot2 le empiezo a rebajar los dias qe traje
                //hasta cuando llege a cero no parara este proceso
                //al final me doy cuenta cual es la fecha final buscando el dia en la posicion tot
                Calendar Diadehoy = Calendar.getInstance();
                String fecha_convertida = "" + a + "-" + m + "-" + d;
                Date date = formatter.parse(fecha_convertida);
                Diadehoy.setTime(date);
                conce = conce + num_id;
                num_id = 0;
                n = dia_semana(Diadehoy, tot2, n, conce, con2,dias);
                for (int i = 0; i < n.size(); i++) {
                    temp = (Dias_Ajuste) n.get(i);
                    if (temp.isR() == false) {
                        num_id++;
                        temp.setR(true);
                    }
                }
                tot2 = tot2 - num_id;
                if (tot2 <= 0) {
                    p = false;
                }
                if (m == 11) {
                    m = 0;
                    d++;
                } else {
                    m++;
                }
                con2 = false;
            }
        }
        for(int i=0;i<n.size();i++){
            if(i==tot){
                fecha_final=n.get(i).getDia()+"-"+n.get(i).getMes()+"-"+n.get(i).getAño();
            }
        }
        System.out.println("fecha final " + fecha_final);
        return fecha_final;
    }

    public ArrayList dia_semana(Calendar x, int tot, ArrayList p, int conce, boolean con,ArrayList dias) throws ParseException {
        //este metodo cuenta cuantos dias de los selecionado hay en un mes y devuelve el resultado guardandolo en un arreglo
        int cuenta = 0;
        int mes = x.get(Calendar.MONTH);
        if (con == false) {
            int diii = x.get(Calendar.DAY_OF_MONTH);
            x.add(Calendar.DAY_OF_MONTH, -diii + 1);
        }
        int diia = 0;
        while (x.get(Calendar.MONTH) == mes) {
            String r = "";
            Dias temp = null;
            for (int i = 0; i < dias.size(); i++) {
                //le asignamos un numero al dia porque asii esta determinado en el calendario de java
                temp = (Dias) dias.get(i);
                if (temp.isEstado() == true) {
                    if (temp.getDia().equalsIgnoreCase("lunes")) {
                        diia = 2;
                    } else if (temp.getDia().equalsIgnoreCase("martes")) {
                        diia = 3;
                    } else if (temp.getDia().equalsIgnoreCase("miercoles")) {
                        diia = 4;
                    } else if (temp.getDia().equalsIgnoreCase("jueves")) {
                        diia = 5;
                    } else if (temp.getDia().equalsIgnoreCase("viernes")) {
                        diia = 6;
                    } else if (temp.getDia().equalsIgnoreCase("sabado")) {
                        diia = 7;
                    }
                    boolean EsFestivo = false;
                    System.out.println("---");
                    Dias_Ajuste temp2 = null;
                    //miramos si el dia que se va a contar es festivo para no contarlo
                    for (int k = 0; k < Festivos.size(); k++) {
                        temp2 = (Dias_Ajuste) Festivos.get(k);
                        if (temp2.getMes() == mes && temp2.getDia() == (x.get(Calendar.DAY_OF_MONTH))) {
                            EsFestivo = true;
                        }
                    }
                    if (EsFestivo == false) {
                        //comparamos que el dia de la semana sea el mismo qe el calendario
                        if (x.get(Calendar.DAY_OF_WEEK) == diia) {
                            Dias_Ajuste Festi = null;
                            if (cuenta >= tot) {
                                cuenta = x.get(Calendar.DAY_OF_MONTH);
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
                            } else {
                                cuenta++;
                                Dias_Ajuste na = new Dias_Ajuste(cuenta, conce, x.get(Calendar.DAY_OF_MONTH), x.get(Calendar.MONTH), x.get(Calendar.YEAR), false);
                                p.add(na);
                            }
                            conce++;
                        }
                    } else {
                        System.out.println("Es Festivo");
                    }

                }

            }
            x.add(Calendar.DAY_OF_MONTH, 1);
        }
        // end while   
        return p;
    }
}
