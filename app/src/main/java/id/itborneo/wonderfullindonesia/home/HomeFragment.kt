package id.itborneo.wonderfullindonesia.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
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
import id.itborneo.wonderfullindonesia.databinding.FragmentHomeBinding
import id.itborneo.wonderfullindonesia.utils.BottomNavigationUtils


class HomeFragment : Fragment() {


    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private var cities = mutableListOf<CityModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated called")
        initNavController(view)
        initListCity()
        initDummyData()
        visibleBottomNav()

        initializeSearchBar()


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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initDummyData() {
        cities.clear()
        cities = DummyData()

        cities.forEach {
            it.isfavorite = MainActivity.prefs.pull(it.id, false)
            Log.d(TAG, "isFavorite ${it.isfavorite}")
        }

        adapter.set(cities)
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
        navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun visibleBottomNav() {
        BottomNavigationUtils.visible(activity)
    }

    private fun initializeSearchBar() {

        binding.apply {
            sbCities.setOnClickListener {
                sbCities.onActionViewExpanded()
            }

            sbCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null) {
                        val newList = cities.filter {
                            it.name.contains(newText, true)
                        }
                        adapter.set(newList)

//                        showNoFavorite(false)
                        if (newList.isNullOrEmpty()) {
                            binding.incEmptyFaorite.tvTitle.setText("Keyword Tersebut Tidak Ada Dalam Database Kami")
                            binding.incEmptyFaorite.root.visibility = View.VISIBLE
                        } else {
                            binding.incEmptyFaorite.root.visibility = View.GONE

                        }

                    }


                    return true
                }

            })
        }


    }

    private fun showNoFavorite(showIt: Boolean) {

        if (showIt) {
            binding.flEmptyFavorite.visibility = View.VISIBLE

        } else {
            binding.flEmptyFavorite.visibility = View.GONE

        }
    }

}