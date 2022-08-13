package com.example.project3
//Osama Alsarayreh 20200336
//Abdalrahman Abudabaseh 20200514
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*

class Grades:Activity() {
    @SuppressLint("Recycle", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grades)
        var options = arrayOf("CS", "SE", "BIT", "AI")
        val spinnerval: Spinner = findViewById(R.id.majorspinner)

        spinnerval.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        var majorRes: String = "SE"

        spinnerval.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                majorRes = spinnerval.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        var sbmtbtn : Button = findViewById(R.id.insert)
        sbmtbtn.setOnClickListener {
            val values = ContentValues()
            values.put(
                StudentProvider.NAME,
                (findViewById<View>(R.id.nameinput) as EditText).text.toString()
            )
            values.put(
                StudentProvider.MAJOR,
                (majorRes)
            )
            values.put(
                StudentProvider.AVERAGE,
                (findViewById<View>(R.id.average) as EditText).text.toString()
            )

            val uri = contentResolver.insert(
                StudentProvider.CONTENT_URI, values
            )
            Toast.makeText(baseContext, "inserted successfully", Toast.LENGTH_LONG).show()
        }

        var cnclbtn : Button = findViewById(R.id.delete)

        cnclbtn.setOnClickListener {
            var del : TextView = findViewById(R.id.deleteid)
            var deltext = del.text.toString()
            val q:String = deltext
            val uri = contentResolver.delete(StudentProvider.CONTENT_URI, "_id like ?",
                Array(1){deltext})
            Toast.makeText(baseContext, uri.toString() + "rows had been deleted successfully", Toast.LENGTH_LONG).show()
        }

        var editbtn : Button = findViewById(R.id.update)
        editbtn.setOnClickListener {
            var editid : TextView = findViewById(R.id.updateid)
            var edit:String = editid.text.toString()
            val values = ContentValues()
            values.put(
                StudentProvider.AVERAGE,
                (findViewById<View>(R.id.updateavg) as EditText).text.toString()
            )
            val uri = contentResolver.update(StudentProvider.CONTENT_URI,values, "_id like ?",
                Array(1){edit})
            Toast.makeText(baseContext, uri.toString() + "rows had been updated successfully", Toast.LENGTH_LONG).show()
        }

        var viewbtn : Button = findViewById(R.id.viewdb)
        viewbtn.setOnClickListener {
            val URL = "content://com.example.Project3.StudentProvider"
            val students = Uri.parse(URL)
            //\  val c = contentResolver!!.query(students,null,null,null,"name"
            var c = contentResolver.query(
                StudentProvider.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            //val c = managedQuery(students, null, null, null, "name")
            if (c != null) {
                if (c?.moveToFirst()) {
                    do {

                        Toast.makeText(this,
                            c.getString(c.getColumnIndex(StudentProvider.ID)) + ", " + c.getString(c.getColumnIndex(
                                StudentProvider.NAME)) + " ," + c.getString(c.getColumnIndex(StudentProvider.MAJOR))+ ", " +
                                    c.getString(c.getColumnIndex(StudentProvider.AVERAGE)),
                            Toast.LENGTH_SHORT).show()
                    } while (c.moveToNext())
                }
            }
        }
    }
}