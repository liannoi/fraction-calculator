package org.itstep.liannoi.fractioncalculator.presentation.fractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.itstep.liannoi.fractioncalculator.databinding.FragmentFractionsBinding
import org.itstep.liannoi.fractioncalculator.presentation.common.extensions.getViewModelFactory

class FractionsFragment : Fragment() {

    private val viewModel: FractionsViewModel by viewModels { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentFractionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentFractionsBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
    }
}
