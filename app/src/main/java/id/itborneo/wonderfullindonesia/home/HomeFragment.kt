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
        cities.apply {
            add(
                CityModel(
                    "Oro Oro Ombo, Indonesia",
                    "Oro-oro Ombo adalah sebuah desa di wilayah Kecamatan Batu, Kota Batu, Provinsi Jawa Timur. Desa Oro-Oro Ombo merupakan sebuah desa yang berada di wilayah Kecamatan Batu Kota Batu, kurang lebih berjarak 2 km di sebelah selatan Kantor Camat Batu. Desa Oro-Oro Ombo terbagi dalam tiga perdukuhan, yakni Dusun Krajan Oro-Oro Ombo, Dusun Gondorejo, dan Dusun Dresel. Sedangkan untuk kelancaran dan kemudahan administrasi pemerintahan Desa Oro-oro Ombo terbagi dalam 13 Rukun Warga, yang tersebar pada tiga dusun. Dusun Krajan terdiri dari tujuh RW, sedangkan Dusun Dresel terdiri dari tiga RW, dan Dusun Gondorejo terdiri dari tiga RW.\n" +
                            "Sebagai daerah yang berkedudukan di daerah dataran tinggi, Oro-Oro Ombo sangat menarik perhatian pihak pertelevisian dengan memilih wilayah Dresel sebagai tempat stasiun pemancar ulang (relay), hal ini terbukti dengan adanya + 11 stasiun pemancar ulang (relay) yang ada di wilayah Dusun Dresel Oro-oro Ombo.\n" +
                            "Demikian juga dengan pariwisata, keindahan alam berupa air terjun Coban Rais dan luasnya bumi perkemahan Coban Rais tidak pernah sepi dikunjungi oleh orang-orang yang ingin lebih mendekatkan diri dengan alam. Ditambah lagi konsep hiburan rekreasi keluarga yang beroperasi pada malam hari, yakni BNS (Batu Night Specstacular), dan juga Museum Satwa-Jatim Park2 yang akan segera beroperasi awal tahun 2010, Desa Oro-Oro Ombo berpotensi untuk menjadi daerah pusat pariwisata yang dapat mensejahterakan penduduknya dengan tata kelola pemerintahan yang baik serta dukungan dan partisipasi aktif dari masyarakat desa sendiri.",
                    R.drawable.img1
                )
            )
        }
        repeat(10) {
            cities.apply {
                add(
                    CityModel(
                        "Oro Oro Ombo, Indonesia",
                        "Oro-oro Ombo adalah sebuah desa di wilayah Kecamatan Batu, Kota Batu, Provinsi Jawa Timur. Desa Oro-Oro Ombo merupakan sebuah desa yang berada di wilayah Kecamatan Batu Kota Batu, kurang lebih berjarak 2 km di sebelah selatan Kantor Camat Batu. Desa Oro-Oro Ombo terbagi dalam tiga perdukuhan, yakni Dusun Krajan Oro-Oro Ombo, Dusun Gondorejo, dan Dusun Dresel. Sedangkan untuk kelancaran dan kemudahan administrasi pemerintahan Desa Oro-oro Ombo terbagi dalam 13 Rukun Warga, yang tersebar pada tiga dusun. Dusun Krajan terdiri dari tujuh RW, sedangkan Dusun Dresel terdiri dari tiga RW, dan Dusun Gondorejo terdiri dari tiga RW.\n" +
                                "Sebagai daerah yang berkedudukan di daerah dataran tinggi, Oro-Oro Ombo sangat menarik perhatian pihak pertelevisian dengan memilih wilayah Dresel sebagai tempat stasiun pemancar ulang (relay), hal ini terbukti dengan adanya + 11 stasiun pemancar ulang (relay) yang ada di wilayah Dusun Dresel Oro-oro Ombo.\n" +
                                "Demikian juga dengan pariwisata, keindahan alam berupa air terjun Coban Rais dan luasnya bumi perkemahan Coban Rais tidak pernah sepi dikunjungi oleh orang-orang yang ingin lebih mendekatkan diri dengan alam. Ditambah lagi konsep hiburan rekreasi keluarga yang beroperasi pada malam hari, yakni BNS (Batu Night Specstacular), dan juga Museum Satwa-Jatim Park2 yang akan segera beroperasi awal tahun 2010, Desa Oro-Oro Ombo berpotensi untuk menjadi daerah pusat pariwisata yang dapat mensejahterakan penduduknya dengan tata kelola pemerintahan yang baik serta dukungan dan partisipasi aktif dari masyarakat desa sendiri.",
                        R.drawable.img1
                    )
                )
            }
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