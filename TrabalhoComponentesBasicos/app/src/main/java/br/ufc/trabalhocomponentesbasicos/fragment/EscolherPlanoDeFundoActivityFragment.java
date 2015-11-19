package br.ufc.trabalhocomponentesbasicos.fragment;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import br.ufc.trabalhocomponentesbasicos.Constantes;
import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.SettingsApplication;
import br.ufc.trabalhocomponentesbasicos.adapter.ImageAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class EscolherPlanoDeFundoActivityFragment extends Fragment {

    private GridView gridView;

    public EscolherPlanoDeFundoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escolher_plano_de_fundo, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                escolherImagem(position);

            }
        });

        return view;
    }

    void escolherImagem(int position){

        SettingsApplication application = (SettingsApplication)getActivity().getApplication();
        application.setBackgroundImage(ImageAdapter.BACKGROUNDS[position]);

        MediaPlayer sound = MediaPlayer.create(getContext(), R.raw.sound);
        sound.start();

        getActivity().setResult(Constantes.SUCESSO);
        getActivity().finish();
    }


}
