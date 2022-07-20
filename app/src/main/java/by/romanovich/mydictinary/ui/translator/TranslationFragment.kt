package by.romanovich.mydictinary.ui.translator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.romanovich.mydictinary.databinding.FragmentTranslationBinding
import by.romanovich.mydictinary.domain.utils.isValidSearchingWord
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TranslationFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTranslationBinding? = null
    private val binding get() = _binding !!
    private var onSearchClickListener: OnSearchClickListener? = null


    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int, count:
            Int
        ) {
            val searchWord = binding.searchEditText.text
            if (isValidSearchingWord(searchWord)) {
                binding.searchButtonTextview.isEnabled = true
                binding.clearTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchButtonTextview.isEnabled = false
                binding.clearTextImageview.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }

        override fun afterTextChanged(s: Editable) {}
    }


    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)

        addOnClearClickListener()
    }


    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButtonTextview.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): TranslationFragment {
            return TranslationFragment()
        }
    }
}