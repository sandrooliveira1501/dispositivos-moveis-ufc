package br.ufc.trabalhocomponentesbasicos.fragment;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopico;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopicoImpl;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddTopicoActivityFragment extends Fragment {

    private ControllerTopico controllerTopico;
    private Spinner spinnerCategoria;
    private Button btAddTopico;
    private EditText etNome;
    private RadioGroup rgPadrao;
    private Topico topico;
    public AddTopicoActivityFragment() {
        controllerTopico = new ControllerTopicoImpl();
        topico = new Topico();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_topico, container, false);;

        List<String> categorias = Arrays.asList(controllerTopico.CATEGORIAS);

        ArrayAdapter<String> adapterCategorias =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                        categorias);

        spinnerCategoria = (Spinner) view.findViewById(R.id.spinner_categoria);
        spinnerCategoria.setAdapter(adapterCategorias);
        topico.setCategoria(controllerTopico.CATEGORIAS[0]);
        //alterando a categoria do tópico assim que o item é selecionado, exemplo de listener de item selecionado no spinner
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topico.setCategoria(controllerTopico.CATEGORIAS[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        etNome = (EditText) view.findViewById(R.id.nome);
        rgPadrao = (RadioGroup) view.findViewById(R.id.radioEscolherPadrao);
        btAddTopico = (Button) view.findViewById(R.id.bt_add_topico);
        btAddTopico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarTopico();
            }
        });

        return view;
    }

    public void adicionarTopico(){
        topico.setNome(etNome.getText().toString().trim());
        if(topico.getNome().equals("")){
            Toast.makeText(getContext(), getResources().getString(R.string.camposInvalidos), Toast.LENGTH_SHORT).show();
            return;
        }

        if(rgPadrao.getCheckedRadioButtonId() == R.id.rbSim){
            controllerTopico.addTopico(topico, true);
        }else{
            controllerTopico.addTopico(topico, false);
        }

        MediaPlayer sound = MediaPlayer.create(getContext(), R.raw.sound);
        sound.start();

        getActivity().finish();
    }

}
