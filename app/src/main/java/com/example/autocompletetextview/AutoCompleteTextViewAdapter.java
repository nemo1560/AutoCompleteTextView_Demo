package com.example.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class AutoCompleteTextViewAdapter {
    public static class AutoCompleteAdapter extends ArrayAdapter<iCountry>{
        Context context;
        int resource;
        List<iCountry> list,tempCountryLst, suggestions;

        public AutoCompleteAdapter(Context context, int resource, List<iCountry> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.list = objects;
            tempCountryLst = new ArrayList<>(list);
            suggestions = new ArrayList<>();
        }

        @Override
        public Filter getFilter() {
            return filter;
        }

        Filter filter = new Filter() {
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                String str = ((iCountry)resultValue).getName();
                return str;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if(constraint != null){
                    suggestions.clear();
                    for(iCountry iCountry : tempCountryLst){
                        if(iCountry.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            suggestions.add(iCountry);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                }else {
                    return  new FilterResults();

                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<iCountry> iCountryList = (List<iCountry>) results.values;
                if(results != null && results.count > 0){
                    clear();
                    for(iCountry iCountry : iCountryList){
                        add(iCountry);
                        notifyDataSetChanged();
                    }
                }
            }
        };

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return createView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return createView(position, convertView, parent);
        }

        private View createView(int position, View convertView, ViewGroup parent) {
            iCountry iCountry = list.get(position);
            View view = LayoutInflater.from(context).inflate(resource, parent, false);

            ((TextView) view).setText(iCountry.getName());
            return view;
        }
    }
}
