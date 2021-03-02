package id.itborneo.wonderfullindonesia

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cioccarellia.ksprefs.KsPrefs
import id.itborneo.wonderfullindonesia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    companion object {
        lateinit var appContext: Context
        val prefs by lazy { KsPrefs(appContext) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val a = prefs.pull("username","nothing")
        Log.d("TAG", " onCreate $a")

        prefs.push(
            "username", "irpan"
        )
        prefs.save()

        initBottomNav()
//        bottomNavAdmin()
    }

    fun initBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)

    }
}