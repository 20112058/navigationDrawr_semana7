package com.example.navigationdrawr_semana7.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawr_semana7.databinding.FragmentGalleryBinding
import com.example.navigationdrawr_semana7.ui.shared.HomGalleryViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val homGalleryViewModel: HomGalleryViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val wvPage: WebView = binding.wvPage
        wvPage.settings.javaScriptEnabled = true

        wvPage.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if(url!=null)
                    view?.loadUrl(url)

                return true
            }

        }

        homGalleryViewModel.url.observe(viewLifecycleOwner){url->
            wvPage.loadUrl(url)

        }

        wvPage.loadUrl("https://www.ue.edu.pe/dap")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}