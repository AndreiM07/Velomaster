package com.andrei.velomasterlsk

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val LAST_SELECTED_ITEM="LAST_SELECTED_ITEM"
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationMenu:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationMenu = findViewById(R.id.bottom_navigation)

        bottomNavigationMenu.setOnItemSelectedListener { item->
            var fragment: Fragment? = null
            when(item.itemId){
                R.id.company_info->{
                    fragment = InfoFragment()
                }
                R.id.spare_part->{
                    fragment = SparePartFragment()
                }
                R.id.services->{
                    fragment = ServicesFragment()
                }
                R.id.product->{
                    fragment = ProductFragment()
                }

            }
            replaceFragment(fragment!!)
            true



        }
        bottomNavigationMenu.selectedItemId=savedInstanceState?.getInt(LAST_SELECTED_ITEM)?:R.id.product


        val moreDetailed: Button = findViewById(R.id.more_detailed)
        val link = Uri.parse("https://vk.com/veloremont_liski")
        val intent = Intent(Intent.ACTION_VIEW, link)

        moreDetailed.setOnClickListener {
            startActivity(intent)
        }



    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_SELECTED_ITEM, bottomNavigationMenu.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun replaceFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()






    }






}