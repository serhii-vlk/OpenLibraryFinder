package com.sample.openlibrary.ui.features.booksearch

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.openlibrary.R
import com.sample.openlibrary.databinding.FragmentBookSearchBinding
import com.sample.openlibrary.di.DataProvider
import com.sample.openlibrary.ui.features.booksearch.di.inject
import javax.inject.Inject
import javax.inject.Provider

class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    @Inject
    lateinit var factory: BookSearchViewModel.Factory
    private val viewModel: BookSearchViewModel by viewModels { factory }

    private var _binding: FragmentBookSearchBinding? = null
    private val binding: FragmentBookSearchBinding get() = checkNotNull(_binding)

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        (context as Provider<DataProvider>).get().also(this::inject)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBookSearchBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
