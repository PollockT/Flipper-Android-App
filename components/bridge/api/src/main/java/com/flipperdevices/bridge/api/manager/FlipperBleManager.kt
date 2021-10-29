package com.flipperdevices.bridge.api.manager

import android.bluetooth.BluetoothDevice
import com.flipperdevices.bridge.api.manager.delegates.FlipperConnectionInformationApi
import com.flipperdevices.bridge.api.manager.service.FlipperInformationApi
import com.flipperdevices.bridge.api.manager.service.FlipperSerialApi

interface FlipperBleManager {
    // Manager delegates
    val connectionInformationApi: FlipperConnectionInformationApi

    // This section provide access to device apis
    val informationApi: FlipperInformationApi
    val flipperRequestApi: FlipperRequestApi
    val serialApi: FlipperSerialApi

    /**
     * Connect to device {@param device}
     * Await while disconnect process is not finish
     */
    suspend fun connectToDevice(device: BluetoothDevice)

    /**
     * Disconnect from current device
     * Await while disconnect process is not finish
     */
    suspend fun disconnectDevice()

    /**
     * Close manager, unregister receivers
     */
    fun close()
}