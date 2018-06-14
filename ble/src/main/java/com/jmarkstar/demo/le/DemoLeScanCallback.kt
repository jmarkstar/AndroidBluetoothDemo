package com.jmarkstar.demo.le

import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.util.Log
import com.jmarkstar.demo.LeDevicesActivity

class DemoLeScanCallback(val activity: LeDevicesActivity): ScanCallback() {

    override fun onBatchScanResults(results: MutableList<ScanResult>?) {
        super.onBatchScanResults(results)
        Log.i("batch", "hello")
        results?.forEach { result ->
            Log.i("batch", "${result.device?.name} - ${result.device?.address} - ${result.rssi}")
        }
    }

    override fun onScanResult(callbackType: Int, result: ScanResult?) {
        super.onScanResult(callbackType, result)

        Log.i("DemoLeScanCallback", "${result?.device?.name} - ${result?.device?.address} - ${result?.rssi}")

        val demoBleDevice = DemoBleDevice(result?.device!!, result.rssi)

        activity.bleDeviceAdapter.addDevice(demoBleDevice)
    }

    override fun onScanFailed(errorCode: Int) {
        super.onScanFailed(errorCode)

        Log.e("DemoLeScanCallback", "$errorCode = error scanning")
    }
}