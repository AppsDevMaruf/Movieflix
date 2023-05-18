package com.maruf.movieflix.ui

import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }


}