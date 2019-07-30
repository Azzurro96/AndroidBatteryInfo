
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.content.Context;
import android.os.Environment;



public class BatteryInfoGet4 extends Thread {

    private Thread t;
    private static int cnt = 0;
    IntentFilter filter2 = new IntentFilter();
    filter2.addAction(Intent.ACTION_BATTERY_CHANGED);
    Intent mIntent;
    private Context mContext;

    public BatteryInfoGet4(Context context, Intent intent)
    {
        mContext = context;
        mIntent = intent;
    }

    public void start(){
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    public void run() {
        try {
            while (true) {
                mContext.registerReceiver(receiver,filter2);
                cnt++;
                System.out.println(cnt);
                Thread.sleep( 500);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread "+" interrupted.");
        }

    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int vol = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            BatteryManager mBatteryManager = (BatteryManager)mContext.getSystemService(Context.BATTERY_SERVICE);
            Long current = mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
            System.out.println("Voltage----:" + vol + "----current:" + current);

        }
    };
}
