package id.itborneo.wonderfullindonesia.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.wonderfullindonesia.MainActivity
import id.itborneo.wonderfullindonesia.R
import id.itborneo.wonderfullindonesia.data.CityModel
import id.itborneo.wonderfullindonesia.data.DummyData
import id.itborneo.wonderfullindonesia.data.EXTRA_CITY
import id.itborneo.wonderfullindonesia.databinding.FragmentFavoriteBinding
import id.itborneo.wonderfullindonesia.home.HomeAdapter
import id.itborneo.wonderfullindonesia.utils.BottomNavigationUtils


class FavoriteFragment : Fragment() {

    private val TAG = "FavoriteFragment"
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: HomeAdapter
    private var cities = mutableListOf<CityModel>()
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavController(view)
        initListCity()
        initDummyData()
        visibleBottomNav()
    }

    private fun visibleBottomNav() {
        BottomNavigationUtils.visible(activity)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private fun initNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initDummyData() {
        cities.clear()
        cities = DummyData()

        cities.forEach {
            it.isfavorite = MainActivity.prefs.pull(it.id, false)
            Log.d(TAG, "isFavorite ${it.isfavorite}")
        }

        val filteredCities = cities.filter {
            it.isfavorite
        }

        if (filteredCities.isNullOrEmpty()) {
            binding.incEmptyFaorite.root.visibility = View.VISIBLE
        } else {
            binding.incEmptyFaorite.root.visibility = View.GONE
        }
        adapter.set(filteredCities)

    }


    private fun initListCity() {
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter {
            actionMoveToDetail(it)
        }
        binding.rvHome.adapter = adapter
    }

    private fun actionMoveToDetail(city: CityModel) {
        val bundle = bundleOf(EXTRA_CITY to city)
        navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
    }

}