package com.example.project3
//Osama Alsarayreh 20200336
//Abdalrahman Abudabaseh 20200514
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = findViewById<View>(R.id.toggleButton) as ToggleButton
        toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                startService(Intent(this, NewService::class.java))
            } else {
                stopService(Intent(this, NewService::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menumain, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.year1->call_first()
            R.id.location->call_location()
        }
        return super.onOptionsItemSelected(item)
    }
    fun call_first(){
        val intent = Intent(this, Grades::class.java)
        intent.putExtra("Key","Grades")
        startActivity(intent)
    }
    fun call_location(){
        val mapIntent: Intent = Uri.parse(
            "geo:32.023712,35.876686 ?q="
        ).let { location ->

        Intent(Intent.ACTION_VIEW, location)
        }
        startActivity(mapIntent);
    }
}