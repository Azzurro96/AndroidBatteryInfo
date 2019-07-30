# Some ways to get android battery information

Three different ways to get the battery information on a android device.

- Read system files directly under the folder like "/sys/class/power_supply/battery/CurrentNow"
- get the information under the corresponding path with ShellUtils
- Using a BroadcastReceiver

The **FileUtils.java** is used for saving the information on your device.

The **ShellUtils.java** used in **BatteryInfoGet3.java** can be found at [bolg]( https://www.cnblogs.com/wansho/p/5104323.html).



-----------------------------------------------------------------------------------------------------------------------------------------------------------



三种在安卓端获取电池信息的方法：

- 直接读取sys路径下的文件
- 基于ShellUtils获取
- 接收系统广播

**FileUtils.java**是用来在设备上写入获取到的数据的。

**ShellUtils.java**借用了别人博客中的[代码]( https://www.cnblogs.com/wansho/p/5104323.html)