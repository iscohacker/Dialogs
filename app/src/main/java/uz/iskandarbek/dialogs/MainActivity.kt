package uz.iskandarbek.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import uz.iskandarbek.dialogs.databinding.ActivityMainBinding
import uz.iskandarbek.dialogs.databinding.ItemBottomBinding
import uz.iskandarbek.dialogs.databinding.ItemDialogBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.apply {

            alert.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle("Bu Alert dialog")
                dialog.setMessage("Iskandar Developer mi ?")

                dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(this@MainActivity, "😁", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.setNegativeButton("Yo'q", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(this@MainActivity, "🪄", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.setNeutralButton("Bekor qilish", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(this@MainActivity, "😒", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.show()
            }

            custom.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemCustom = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemCustom.root)
                itemCustom.login.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Saqlandi !", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }
                dialog.show()
            }
            bottomshet.setOnClickListener {
                val dialog = BottomSheetDialog(this@MainActivity)
                val itemBottom = ItemBottomBinding.inflate(layoutInflater)
                dialog.setContentView(itemBottom.root)
                dialog.setCancelable(false)
                dialog.show()
                itemBottom.ok.setOnClickListener {
                    dialog.cancel()
                }
            }
            datapicker.setOnClickListener {
                val datePickerDialog = DatePickerDialog(this@MainActivity)

                datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                    Toast.makeText(
                        this@MainActivity,
                        "${dayOfMonth}.${month + 1}.$year",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                datePickerDialog.show()
            }
            timepicker.setOnClickListener {
                val timePickerDialog = TimePickerDialog(
                    this@MainActivity,
                    object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            Toast.makeText(
                                this@MainActivity,
                                "$hourOfDay:$minute",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                    24,
                    60,
                    true
                )
//            timePickerDialog.updateTime(LocalTime.now().hour, LocalTime.now().minute)
                timePickerDialog.updateTime(12, 50)
                timePickerDialog.show()
            }

            snackbar.setOnClickListener {
                val snackbar = Snackbar.make(it, "Amaliyot bajarildi !", Snackbar.LENGTH_LONG)

                snackbar.setAction("Bekor qilish", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        Toast.makeText(
                            this@MainActivity,
                            "Amaliyot bekor qilindi ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

                snackbar.show()
            }
            fragment.setOnClickListener {
                val fragmentDialog = FragmentDialog()
                fragmentDialog.show(supportFragmentManager, "Fragment Dialog")
            }
        }
    }
}