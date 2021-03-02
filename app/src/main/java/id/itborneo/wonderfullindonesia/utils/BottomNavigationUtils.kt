package id.itborneo.wonderfullindonesia.utils

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.itborneo.wonderfullindonesia.R


object BottomNavigationUtils {


    fun visible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)?.visibility =
            View.VISIBLE
    }

    fun invisible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)?.visibility =
            View.GONE
    }
}
