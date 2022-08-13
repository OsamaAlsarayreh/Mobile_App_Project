package com.example.project3
//Osama Alsarayreh 20200336
//Abdalrahman Abudabaseh 20200514
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
class NewService : Service() {
    private lateinit var player:MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

        player.setLooping(true)
        player.start()
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
