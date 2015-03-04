package pankaj.com.contactdetails.expandable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData(ArrayList<String> phNumbers, ArrayList<String> emailids) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Phones = new ArrayList<String>();
        Phones.addAll(phNumbers);
//        Phones.add("Beats sued for noise-cancelling tech");
//        Phones.add("Wikipedia blocks US Congress edits");
//        Phones.add("Google quizzed over deleted links");
//        Phones.add("Nasa seeks aid with Earth-Mars links");
//        Phones.add("The Good, the Bad and the Ugly");


        List<String> emails = new ArrayList<String>();
        emails.addAll(emailids);
//        emails.add("Goldfinch novel set for big screen");
//        emails.add("Anderson stellar in Streetcar");
//        emails.add("Ronstadt receives White House honour");
//        emails.add("Toronto to open with The Judge");
//        emails.add("British dancer return from Russia");

        expandableListDetail.put("Phones", Phones);
        expandableListDetail.put("Emails", emails);

        return expandableListDetail;
    }

}
