package id.itborneo.wonderfullindonesia.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.itborneo.wonderfullindonesia.data.CityModel
import id.itborneo.wonderfullindonesia.databinding.ItemCityBinding


class HomeAdapter(private val listener: (CityModel) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val TAG = "PlacesAdapter"

    private var cities = listOf<CityModel>()

    fun set(cities: List<CityModel>) {
        this.cities = cities
        Log.d(TAG, cities.toString())
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size

    inner class ViewHolder(private val itemBinding: ItemCityBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(city: CityModel) {
            itemBinding.apply {
                tvTitle.text = city.name
                tvSubTitle.text = city.description

                Picasso.get()
                    .load(city.image)
                    .fit()
                    .centerCrop()
                    .into(ivImage)
            }
        }
    }
}