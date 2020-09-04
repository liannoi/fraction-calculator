package org.itstep.liannoi.fractioncalculator.presentation.fractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import org.itstep.liannoi.fractioncalculator.R
import org.itstep.liannoi.fractioncalculator.application.fractions.commands.AppendCommand
import org.itstep.liannoi.fractioncalculator.application.fractions.commands.BackspaceCommand
import org.itstep.liannoi.fractioncalculator.application.fractions.commands.DivideCommand
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

        subscribeButtons()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Subscriptions
    ///////////////////////////////////////////////////////////////////////////

    private fun subscribeButtons() {
        subscribeButton(R.id.calc_divide_button) {
            DivideCommand.Handler().handle(DivideCommand(viewModel.calc))
        }

        subscribeButton(R.id.calc_clear_button) {
            BackspaceCommand.Handler().handle(BackspaceCommand(viewModel.calc))
        }

        subscribeButton(R.id.calc_zero_button, 0)
        subscribeButton(R.id.calc_first_button, 1)
        subscribeButton(R.id.calc_second_button, 2)
        subscribeButton(R.id.calc_third_button, 3)
        subscribeButton(R.id.calc_fourth_button, 4)
        subscribeButton(R.id.calc_fifth_button, 5)
        subscribeButton(R.id.calc_sixth_button, 6)
        subscribeButton(R.id.calc_seven_button, 7)
        subscribeButton(R.id.calc_eighth_button, 8)
        subscribeButton(R.id.calc_ninth_button, 9)
    }

    private fun subscribeButton(id: Int, send: () -> Unit) {
        prepareButtonClick(id).subscribe { send() }
    }

    private fun subscribeButton(id: Int, value: Int) {
        prepareButtonClick(id).subscribe {
            AppendCommand.Handler().handle(AppendCommand(viewModel.calc, value.toString()))
        }
    }

    private fun prepareButtonClick(id: Int): Observable<Unit> =
        activity?.findViewById<Button>(id)!!.clicks()
}
