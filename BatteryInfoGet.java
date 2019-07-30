import android.os.Environment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.Thread;


public class BatteryInfoGet extends Thread {

    private Thread t;
    private static int cnt = 0;
    private static long current_time = 0;
    private String file_name = "power_history.txt";//file name for battery info saving
    private String file_path = Environment.getExternalStoragePublicDirectory("") + "/getBatteryInfo/";// New folder under the root directory to store information

    public void start(){
        if (t == null) 
        {
            t = new Thread (this);
            t.start ();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                long startTime=System.nanoTime();
                String o = getCurrent2HN();
                cnt++;
                FileUtils.writeTxtToFile(cnt + "---" + (current_time/1000) + o,file_path,file_name);//
                //System.out.println(cnt +  "time" + current_time/1000 + "us" + o);
                long endTime=System.nanoTime();
                current_time = endTime-startTime;
                //System.out.println("----" + current_time+"ns");
                //System.out.println("----" + current_time + "ns");
                Thread.sleep( 0);
                
            }
        }catch (InterruptedException e) {
            System.out.println("Thread "+" interrupted.");
        }

    }
    //2HN means that this function was tested on a Honor mobile phone
    private String getCurrent2HN() {
        String result = "null";
        try {

                String filePath ="/sys/class/power_supply/Battery/current_now";
                int current = readFile(filePath, 100);
                int voltage = readFile("/sys/class/power_supply/Battery/voltage_now", 0) / 1000;
                result = "----" + (-current) + "----" + voltage;
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int readFile(String path, int defaultValue) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    path));
            int i = Integer.parseInt(bufferedReader.readLine(), 10);
            bufferedReader.close();
            return i;
        } catch (Exception localException) {
        }
        return defaultValue;
    }



}
