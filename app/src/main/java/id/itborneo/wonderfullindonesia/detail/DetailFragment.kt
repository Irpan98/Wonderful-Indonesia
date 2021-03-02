package id.itborneo.wonderfullindonesia.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.itborneo.wonderfullindonesia.MainActivity
import id.itborneo.wonderfullindonesia.R
import id.itborneo.wonderfullindonesia.data.CityModel
import id.itborneo.wonderfullindonesia.data.EXTRA_CITY
import id.itborneo.wonderfullindonesia.databinding.FragmentDetailBinding
import id.itborneo.wonderfullindonesia.utils.BottomNavigationUtils
import id.itborneo.wonderfullindonesia.utils.toastUtils.ToastTop


class DetailFragment : Fragment() {


    private val TAG = "DetailFragment"

    private lateinit var binding: FragmentDetailBinding
    private lateinit var city: CityModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveData()
        updateUi()
        initAppbar()
        detailInfo(requireContext(), binding.root)
        hideBottomNav()
        initNavController(view)
        setupBackPressListener()

        binding.ivShare.setOnClickListener {
            ToastTop.show(requireContext(), "Fitur Masih Dalam Pengembangan")

        }

    }

    @Suppress("DEPRECATION")
    private fun initAppbar() {

        binding.ivBack.setOnClickListener {
            navController.popBackStack()

        }

        requireActivity().window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )

        }
    }

    private fun setupBackPressListener() {

    }

    private lateinit var navController: NavController
    private fun initNavController(view: View) {
        navController = Navigation.findNavController(view)
    }


    private fun updateUi() {
        binding.ivImage.setImageDrawable(
            ResourcesCompat.getDrawable(resources, city.image, null)
        )
    }

    fun retrieveData() {
        city = arguments?.getParcelable(EXTRA_CITY)!!


    }

    private fun back() {
        navController.popBackStack()
    }

    private fun detailInfo(context: Context, rootView: ViewGroup) {
        val dialog = BottomSheetDialog(context, R.style.SheetDialog)
//        dialog.behavior.isHideable = false
        dialog.behavior.state = STATE_COLLAPSED

        val view =
            LayoutInflater.from(context).inflate(R.layout.bottom_sheet_detail, rootView, false)
        dialog.setContentView(view)
        view.findViewById<TextView>(R.id.tvTitle).text = city.name
        view.findViewById<TextView>(R.id.tvDetail).text = city.description

        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                // Do what you want to do on back press
                dialog.dismiss()
                back()
                true
            } else
                false
        }


        val ivFavorite = view?.findViewById<ImageView>(R.id.ivFavorite)


        updateFavorite(city.isfavorite, ivFavorite, true)

        ivFavorite?.setOnClickListener {

            city.isfavorite = !city.isfavorite
            Log.d(TAG, "ivFavorite ${city.isfavorite}")

            MainActivity.prefs.push(city.id, city.isfavorite)
            MainActivity.prefs.save()
            updateFavorite(city.isfavorite, ivFavorite)

        }


        binding.fabInfo.setOnClickListener {
            dialog.show()

        }
        dialog.show()
//        dialog.behavior.state = STATE_COLLAPSED
    }


    fun updateFavorite(isFavorite: Boolean, view: ImageView?, firstTime: Boolean = false) {
        if (isFavorite) {
            view?.setImageResource(R.drawable.ic_favorite_true)

            if (firstTime) return
            ToastTop.show(requireContext(), "Kota Berhasil ditambahkan ke Favorite")

        } else {
            view?.setImageResource(R.drawable.ic_favorite)

            if (firstTime) return
            ToastTop.show(requireContext(), "Kota Berhasil dihapus dari Favorite")
        }

    }

    private fun hideBottomNav() {
        BottomNavigationUtils.invisible(activity)
    }

}