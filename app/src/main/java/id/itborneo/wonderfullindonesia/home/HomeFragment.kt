package id.itborneo.wonderfullindonesia.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.wonderfullindonesia.R
import id.itborneo.wonderfullindonesia.data.CityModel
import id.itborneo.wonderfullindonesia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val cities = mutableListOf<CityModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated called")
        initListCity()
        initDummyData()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initDummyData() {
        repeat(10) {
            cities.add(
                CityModel(
                    "city $it", "hahahahah", R.drawable.img1
                )
            )
        }
        adapter.set(cities)
    }


    private fun initListCity() {
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter {

        }
        binding.rvHome.adapter = adapter
    }
}