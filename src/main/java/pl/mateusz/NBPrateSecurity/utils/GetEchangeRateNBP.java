package pl.mateusz.NBPrateSecurity.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.mateusz.NBPrateSecurity.interfaces.GetXMLRateNBP;
import pl.mateusz.NBPrateSecurity.models.forms.ExchangeRateNBP;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GetEchangeRateNBP implements GetXMLRateNBP {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = null;
    private List<ExchangeRateNBP> excangeRateList = new ArrayList<>();
    private ExchangeRateNBP exchangeRateNBP;



    public List<ExchangeRateNBP> echangeRateTableA(Element root) {
        exchangeRateNBP = new ExchangeRateNBP();
        exchangeRateNBP.setNumberTableNBP(root.getElementsByTagName("numer_tabeli").item(0).getTextContent());
        exchangeRateNBP.setDatePublication(root.getElementsByTagName("data_publikacji").item(0).getTextContent());

        NodeList children = root.getElementsByTagName("pozycja");

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element chidrenElement = (Element) child;

                exchangeRateNBP.setCurrencyName(chidrenElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                exchangeRateNBP.setConverter(Integer.valueOf(chidrenElement.getElementsByTagName("przelicznik").item(0).getTextContent()));
                exchangeRateNBP.setCurrencyCode(chidrenElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
                exchangeRateNBP.setAvarageRate(Double.valueOf((chidrenElement.getElementsByTagName("kurs_sredni").item(0).getTextContent()).replace(",", ".")));

                excangeRateList.add(exchangeRateNBP);

            }
            exchangeRateNBP = new ExchangeRateNBP();
        }

        return excangeRateList;

    }

    public List<ExchangeRateNBP> echangeRateTableC(Element root) {
        exchangeRateNBP = new ExchangeRateNBP();
        exchangeRateNBP.setNumberTableNBP(root.getElementsByTagName("numer_tabeli").item(0).getTextContent());
        exchangeRateNBP.setDateOfListing(root.getElementsByTagName("data_notowania").item(0).getTextContent());
        exchangeRateNBP.setDatePublication(root.getElementsByTagName("data_publikacji").item(0).getTextContent());

        NodeList children = root.getElementsByTagName("pozycja");

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element chidrenElement = (Element) child;

                exchangeRateNBP.setCurrencyName(chidrenElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                exchangeRateNBP.setConverter(Integer.valueOf(chidrenElement.getElementsByTagName("przelicznik").item(0).getTextContent()));
                exchangeRateNBP.setCurrencyCode(chidrenElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
                exchangeRateNBP.setBuyRate(Double.valueOf(chidrenElement.getElementsByTagName("kurs_kupna").item(0).getTextContent().replace(",",".")));
                exchangeRateNBP.setSellRate(Double.valueOf(chidrenElement.getElementsByTagName("kurs_sprzedazy").item(0).getTextContent().replace(",",".")));

                excangeRateList.add(exchangeRateNBP);

            }
            exchangeRateNBP = new ExchangeRateNBP();
        }

        return excangeRateList;

    }


    //-------------------------------METODY POMOCNICZE----------------------------------------

    @Override
    public Element getTableRate(String url) {

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(url);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = document.getDocumentElement();

        return root;
    }

}
