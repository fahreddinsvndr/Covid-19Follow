package com.fahreddinsevindir.covid19takip.view.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import com.fahreddinsevindir.covid19takip.R
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadLocate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)
        menuInflater.inflate(R.menu.search_menu,menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if (searchItem !=null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()){

                    }else{

                    }
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val language= arrayOf("Es","Ru","Tr","Cn","En")
        val mBuilder = AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(language,-1){dialog, which ->
            if (which ==0){
                setLocate("Es")
                recreate()
            }else if (which==1){
                setLocate("Ru")
            }else if (which ==2){
                setLocate("TR")
            }else if (which == 3){
                setLocate("Cn")
            }else if (which == 4){
                setLocate("En")
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
        return super.onOptionsItemSelected(item)
    }
    private fun setLocate(Lang:String){
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Change Language",Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }
    private fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Change Language", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang","")
        setLocate(language!!)
    }
}