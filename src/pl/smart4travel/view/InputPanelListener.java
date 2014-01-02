package pl.smart4travel.view;

import pl.smart4travel.model.Model;
import pl.smart4travel.model.Stop;

import java.util.Date;

public interface InputPanelListener {
       void onSubmit(String from, String to, int date, Model model);
}
