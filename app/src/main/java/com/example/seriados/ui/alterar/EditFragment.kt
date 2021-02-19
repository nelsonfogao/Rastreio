package com.example.seriados.ui.alterar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seriados.R
import com.example.seriados.database.AppDatabase
import com.example.seriados.database.EpisodiosUtil
import com.example.seriados.model.Episodios
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {

    private lateinit var viewModel: EpisodiosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)


        val appDatabase =
            AppDatabase.getInstance(requireContext().applicationContext)              // DB

        val episodiosDao = appDatabase.episodiosDao()
        val episodiosViewModelFactory = EpisodiosViewModelFactory(episodiosDao) //    // DB

        viewModel = ViewModelProvider(
            this, episodiosViewModelFactory
        ).get(EpisodiosViewModel::class.java)   //

        viewModel.let {
            it.msg.observe(viewLifecycleOwner) { msg ->
                if (!msg.isNullOrBlank()){
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                }
            }
            it.status.observe(viewLifecycleOwner) { status ->
                if (status)
                    findNavController().popBackStack()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (EpisodiosUtil.episodioSelecionado != null)
            preencherFormulario(EpisodiosUtil.episodioSelecionado!!)

        buttonSalvarEpisodio.setOnClickListener {

            val numero = editTextEpisodioNumero.text.toString()
            val serieId = editTextidserie.text.toString().toLong()
            viewModel.salvarEpisodios(numero, serieId)
        }
    }

    private fun preencherFormulario(episodios: Episodios){
        editTextEpisodioNumero.setText(episodios.numero)
    }


}