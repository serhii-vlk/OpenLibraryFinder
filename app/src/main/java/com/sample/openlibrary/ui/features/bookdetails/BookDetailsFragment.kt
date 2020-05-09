package com.sample.openlibrary.ui.features.bookdetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.sample.openlibrary.R
import com.sample.openlibrary.databinding.FragmentBookDetailsBinding
import com.sample.openlibrary.ui.base.BaseFragment
import com.sample.openlibrary.ui.extension.viewBinding
import com.sample.openlibrary.ui.features.bookdetails.di.inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BookDetailsFragment : BaseFragment(R.layout.fragment_book_details) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: BookDetailsViewModel by viewModels { factory }

    private val binding: FragmentBookDetailsBinding by viewBinding(FragmentBookDetailsBinding::bind)

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.state.collect { render(it) }
        }
    }

    private fun render(state: BookDetailsViewModel.UiState) = with(binding) {
        title.text = state.title
        authors.text = state.authors
        subtitle.text = state.subtitle
        state.coverUrl?.also { cover.load(it) }
    }
}
