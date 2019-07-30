package com.gwecom.myapplication;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;
import java.util.regex.Pattern;
import static android.content.ContentValues.TAG;

public class BatteryInfoGet3 extends Thread {
    private Thread t;
    private String file_name = "power_history.txt";//file name for battery info saving
    private String file_path = Environment.getExternalStoragePublicDirectory("") + "/getBatteryInfo/";// New folder under the root directory to store information

    public void start(){
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    @Override
    public void run() {
        try {
            int cnt = 0;
            while (true) {
                cnt++;
                long startTime=System.currentTimeMillis();   //获取开始时间 get the begin time
                String o1 = getCurrent();
                //System.out.println(o1);
                String o2 = getVoltage();
                //System.out.println(o2);
                FileUtils.writeTxtToFile(cnt + o1+o2,file_path,file_name);
                long endTime=System.currentTimeMillis();
                System.out.println("----running time: "+(endTime-startTime)+"ms");
                //Thread.sleep(5);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread "+" interrupted.");
        }

    }
    //获取电流
    //get the current
    public String getCurrent() {
        String[] cmds = {"cat /sys/class/power_supply/Battery/current_now"};
        ShellUtils.CommandResult rs = ShellUtils.execCommand(cmds, false, true);
        if (!TextUtils.isEmpty(rs.successMsg)) {
            return "----current:"+rs.successMsg + "-mA";
        } else {
            return "current:" + "unknown";
        }
    }
    //get the voltage
    //获取电压
    public String getVoltage() {
        String[] cmds = {"cat /sys/class/power_supply/Battery/voltage_now"};
        ShellUtils.CommandResult rs = ShellUtils.execCommand(cmds, false, true);
        if (!TextUtils.isEmpty(rs.successMsg)) {
            return "----Voltage:"+rs.successMsg + "-uV";
        } else {
            return "----Voltage:" + "unknown";
        }
    }

}
