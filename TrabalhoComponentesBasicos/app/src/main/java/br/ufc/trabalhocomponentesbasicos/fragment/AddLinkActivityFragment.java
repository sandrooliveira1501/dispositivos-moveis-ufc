package br.ufc.trabalhocomponentesbasicos.fragment;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.Constantes;
import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerLink;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerLinkImpl;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopico;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopicoImpl;
import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddLinkActivityFragment extends Fragment {

    private ControllerTopico controllerTopico;
    private ControllerLink controllerLink;
    private AutoCompleteTextView autoTextTopico;
    private ToggleButton tgEscolherTopico;
    private EditText etURL;
    private EditText etDescricao;
    private Button btAddLink;
    private Link link;

    public AddLinkActivityFragment() {
        controllerTopico = new ControllerTopicoImpl();
        controllerLink = new ControllerLinkImpl();
        link = new Link();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_link, container, false);

        autoTextTopico = (AutoCompleteTextView) view.findViewById(R.id.text_topico);

        ArrayAdapter<Topico> adapterTopicos = new ArrayAdapter<Topico>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, controllerTopico.getTopicos());

        autoTextTopico.setAdapter(adapterTopicos);
        autoTextTopico.setThreshold(0);

        Topico topicoPadrao = controllerTopico.getTopicoPadrao();
        if(topicoPadrao != null){
            autoTextTopico.setText(topicoPadrao.toString());
        }

        tgEscolherTopico = (ToggleButton) view.findViewById(R.id.toggle_sem_topico);
        tgEscolherTopico.setChecked(false);
        tgEscolherTopico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    autoTextTopico.setEnabled(false);
                }else{
                    autoTextTopico.setEnabled(true);
                }
            }
        });

        etURL = (EditText) view.findViewById(R.id.url);
        if(getArguments() != null && getArguments().getString(Constantes.ARG_URL) != null){
            etURL.setText(getArguments().getString(Constantes.ARG_URL));
        }

        etDescricao = (EditText) view.findViewById(R.id.text_descricao);

        btAddLink = (Button) view.findViewById(R.id.bt_add_link);
        btAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarLink();
            }
        });

        return view;
    }

    public void adicionarLink(){

        String descricao = etDescricao.getText().toString().trim();
        String url = etURL.getText().toString().trim();
        String nomeTopico = autoTextTopico.getText().toString().trim();

        if(descricao.equals("") || url.equals("") || (nomeTopico.equals("") && (tgEscolherTopico.isChecked() == false))){
            Toast.makeText(getContext(), getResources().getString(R.string.camposInvalidos), Toast.LENGTH_SHORT).show();
        } else {

            link.setUrl(url);
            link.setDescricao(descricao);

            if (tgEscolherTopico.isChecked()) {
                controllerLink.addLink(link);
            } else {
                Topico topico = controllerTopico.getTopico(nomeTopico);
                if (topico == null) {
                    topico = new Topico(nomeTopico, controllerTopico.CATEGORIAS[0]);
                }
                controllerLink.addLink(link, topico);
            }

            Toast.makeText(getContext(), getResources().getString(R.string.mensagem_link_adicionado), Toast.LENGTH_SHORT).show();

            MediaPlayer sound = MediaPlayer.create(getContext(), R.raw.sound);
            sound.start();

            getActivity().finish();
        }
    }
}
