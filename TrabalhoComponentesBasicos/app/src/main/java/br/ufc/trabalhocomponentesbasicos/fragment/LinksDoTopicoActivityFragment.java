package br.ufc.trabalhocomponentesbasicos.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopico;
import br.ufc.trabalhocomponentesbasicos.controller.ControllerTopicoImpl;
import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * A placeholder fragment containing a simple view.
 */
public class LinksDoTopicoActivityFragment extends Fragment {

    private ControllerTopico controllerTopico;
    public static final String ARG_NOME_TOPICO = "nome_topico";

    private ListView listView;
    private ArrayAdapter<Link> adapterLinks;
    private Topico topico;

    public LinksDoTopicoActivityFragment() {
        controllerTopico = new ControllerTopicoImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_links_do_topico, container, false);

        Bundle args = getArguments();
        if(args != null){

            String nomeTopico = args.getString(ARG_NOME_TOPICO);
            topico = controllerTopico.getTopico(nomeTopico);
            if(topico != null && topico.getLinks().size() > 0){

                listView = (ListView) view.findViewById(R.id.list_view);
                adapterLinks = new ArrayAdapter<Link>(getActivity(),
                        android.R.layout.simple_dropdown_item_1line, topico.getLinks());
                listView.setAdapter(adapterLinks);

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        Link link = adapterLinks.getItem(position);

                        Toast.makeText(getContext(), link.toString(), Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });

                view.findViewById(R.id.nenhum_link).setVisibility(View.GONE);
            }else{
                view.findViewById(R.id.nenhum_link).setVisibility(View.VISIBLE);
            }

        }else{
            view.findViewById(R.id.nenhum_link).setVisibility(View.VISIBLE);
        }
        return view;
    }
}
