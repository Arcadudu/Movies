package ru.arcadudu.movies;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MyDialog extends AppCompatDialogFragment {

    private EditText etTitle, etYear, etRating, etPlot;
    private MyDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_movie, null);

        builder.setView(view)
                .setTitle("Добавить фильм")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = etTitle.getText().toString();
                        String year = etYear.getText().toString();
                        String rating = etRating.getText().toString();
                        String plot = etPlot.getText().toString();
                        listener.applyText(title, year, rating, plot);
                    }
                });

        etTitle = view.findViewById(R.id.et_title);
        etYear = view.findViewById(R.id.et_year);
        etRating = view.findViewById(R.id.et_rating);
        etPlot = view.findViewById(R.id.et_plot);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (MyDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement MyDialogListener");
        }
    }

    public interface MyDialogListener{
        void applyText(String title, String year, String rating, String plot);
    }
}
