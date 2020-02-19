package com.example.lab2_b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;
    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onReceive(Context context, Intent intent) {


        String action = intent.getAction();
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                sb = new StringBuilder();
                List<ScanResult> wifiList = wifiManager.getScanResults();
                ArrayList<String> deviceList = new ArrayList<>();
                for (ScanResult scanResult : wifiList) {
                    sb.append("\n").append(scanResult.SSID).append(" - ").append(scanResult.capabilities);
                    deviceList.add(scanResult.SSID + " - " + scanResult.capabilities   );
                }
//                try(BufferedWriter bw = new BufferedWriter(new FileWriter("Wifi.txt"))){
//                    bw.write("Hello");
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    File file = new File("wifi.txt");
//                    /*If file gets created then the createNewFile()
//                     * method would return true or if the file is
//                     * already present it would return false
//                     */
//                    boolean fvar = file.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());
                wifiDeviceList.setAdapter(arrayAdapter);
            }



    }

}