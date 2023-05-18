package com.maruf.movieflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maruf.movieflix.BaseFragment
import com.maruf.movieflix.R
import com.maruf.movieflix.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun getFragmentView(): Int {
        return R.layout.fragment_details
    }

    override fun configUi() {

    }

}