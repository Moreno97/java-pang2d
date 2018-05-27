package mapanel;

import java.util.Timer;
import java.util.TimerTask;

public class Clock implements Runnable {
    public int segundos;//manejar el valor del contador
    public boolean stp; //manejar el estado del contador



    public Clock(int segundos, boolean stp) {
        this.segundos = segundos;
        this.stp = stp;
    }

    public void iniClock(){
        stp = false;
    }

    public void iniStop(){
        stp = true;
    }

    public void iniReset(){
        stp= true;
        segundos = 120;
    }

    public int getSegundos() {
        return segundos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (stp==false || segundos != 0) {
                    segundos--;
                    //lblTime.setText(String.valueOf(segundos));
                    //System.out.println(String.valueOf(segundos));
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.getMessage();
            }

        }
    }
}
