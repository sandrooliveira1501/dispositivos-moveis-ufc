package br.ufc.trabalhocomponentesbasicos.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.activity.LinksDoTopicoActivity;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopico;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopicoImpl;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

public class TopicosFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<Topico> adapterTopicos;
    private ControllerTopico controllerTopico = new ControllerTopicoImpl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topicos, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        adapterTopicos = new  ArrayAdapter<Topico>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, controllerTopico.getTopicos());
        listView.setAdapter(adapterTopicos);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                Topico topico = adapterTopicos.getItem(position);

                PopupMenu popup = new PopupMenu(getActivity(), view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        topicoSelecionado(position);
                        return true;
                    }
                });

                popup.show(); //showing popup menu

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topicoSelecionado(position);
            }
        });

        return view;
    }

    void topicoSelecionado(int position){
        Topico topico =  adapterTopicos.getItem(position);
        Intent intent = new Intent(getActivity(), LinksDoTopicoActivity.class);
        intent.putExtra(LinksDoTopicoActivityFragment.ARG_NOME_TOPICO, topico.getNome());
        getActivity().startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        int sizeList = adapterTopicos.getCount();
        List<Topico> topicos = controllerTopico.getTopicos();
        List<Topico> topicosNovos = topicos.subList(sizeList, topicos.size());
        sizeList = topicos.size();
        //adapterTopicos.addAll(topicosNovos);
        adapterTopicos.notifyDataSetChanged();
    }

}
