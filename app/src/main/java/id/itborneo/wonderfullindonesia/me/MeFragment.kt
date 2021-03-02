package id.itborneo.wonderfullindonesia.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.itborneo.wonderfullindonesia.databinding.FragmentMeBinding
import id.itborneo.wonderfullindonesia.utils.toastUtils.ToastTop


class MeFragment : Fragment() {


    private lateinit var binding: FragmentMeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnMe.setOnClickListener {
            ToastTop.blueBackgroundShow(requireContext(), "Fitur Masih Dalam Pengembangan")
        }

        binding.btnCredits.setOnClickListener {
            ToastTop.blueBackgroundShow(requireContext(), "Thanks To Hoai Le @Lottie for animation")

        }
    }


}