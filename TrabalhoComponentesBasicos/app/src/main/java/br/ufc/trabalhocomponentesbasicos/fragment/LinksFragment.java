package br.ufc.trabalhocomponentesbasicos.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerLink;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerLinkImpl;
import br.ufc.trabalhocomponentesbasicos.model.Link;


public class LinksFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<Link> adapterLinks;
    private ControllerLink controllerLink = new ControllerLinkImpl();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);



        listView = (ListView) view.findViewById(R.id.list_view);
        adapterLinks = new  ArrayAdapter<Link>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, controllerLink.getLinks());
        listView.setAdapter(adapterLinks);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Link link = adapterLinks.getItem(position);

                Toast.makeText(getContext(), link.toString(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        if(controllerLink.getLinks().size() > 0){
            view.findViewById(R.id.nenhum_link).setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int sizeList = adapterLinks.getCount();
        List<Link> links = controllerLink.getLinks();
        List<Link> linksNovos = links.subList(sizeList, links.size());
        sizeList = links.size();
        adapterLinks.addAll(linksNovos);
        adapterLinks.notifyDataSetChanged();
        if(sizeList > 0 && getView() != null){
            getView().findViewById(R.id.nenhum_link).setVisibility(View.GONE);
        }
    }


}
