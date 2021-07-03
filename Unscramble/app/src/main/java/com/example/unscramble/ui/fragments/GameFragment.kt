package com.example.unscramble.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.unscramble.R
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.databinding.GameFragmentBinding
import com.example.unscramble.viewmodel.GameViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {
    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = GameFragmentBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )
        Log.d("GameFragment", "onCreateView() GameFragment created/re-created!")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.gameViewModel = gameViewModel
        binding.maxNoOfWords = MAX_NO_OF_WORDS
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonSkip.setOnClickListener { onSkipWord() }
        binding.buttonSubmit.setOnClickListener { onSubmitWord() }
        gameViewModel.score.observe(
            viewLifecycleOwner,
            { newScore -> binding.score.text = getString(R.string.score, newScore) })
    }

    private fun onSubmitWord() {
        val playerWord = binding.answerInputEditText.text.toString()
        if (gameViewModel.isUserWordCorrect(playerWord)) {
            setErrorForInputTextFiled(false)
            if (!gameViewModel.nextWord()) {
                showFinalScoreDialog()
            }
        } else {
            setErrorForInputTextFiled(true)
        }
    }

    private fun setErrorForInputTextFiled(isError: Boolean) {
        if (isError) {
            binding.answerTextField.isErrorEnabled = true
            binding.answerTextField.error = getString(R.string.try_again)
        } else {
            binding.answerTextField.isErrorEnabled = false
            binding.answerTextField.error = null
        }
    }

    private fun onSkipWord() {
        if (gameViewModel.nextWord()) {
            setErrorForInputTextFiled(false)
        } else {
            showFinalScoreDialog()
        }
    }

    /**
     * Creates and shows an AlertDialog with the final score.
     */
    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.alert_dialog_title)
            .setMessage(getString(R.string.alert_dialog_message, gameViewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(R.string.alert_dialog_negative_button_text) { dialogInterface: DialogInterface, i: Int ->
                exitGame()
            }
            .setPositiveButton(R.string.alert_dialog_positive_button_text) { dialogInterface: DialogInterface, i: Int ->
                restartGame()
            }.show()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    private fun exitGame() {
        activity?.finish()
    }

    private fun restartGame() {
        gameViewModel.reinitializeData()
        setErrorForInputTextFiled(false)
    }
}