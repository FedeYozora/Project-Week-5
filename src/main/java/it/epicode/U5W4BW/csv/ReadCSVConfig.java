package it.epicode.U5W4BW.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.epicode.U5W4BW.entities.Municipality;
import it.epicode.U5W4BW.entities.Province;
import it.epicode.U5W4BW.repositories.MunicipalityDAO;
import it.epicode.U5W4BW.repositories.ProvinceDAO;
import it.epicode.U5W4BW.services.MunicipalitySRV;
import it.epicode.U5W4BW.services.ProvinceSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ReadCSVConfig {

    @Autowired
    private ProvinceSRV provinceSRV;
    @Autowired
    private ProvinceDAO provinceDAO;

    @Autowired
    private MunicipalityDAO municipalityDAO;

    @Autowired
    private MunicipalitySRV municipalitySRV;

    public ReadCSVConfig() throws FileNotFoundException {
    }


    @Bean
    public List<Province> parseProvinceCSV() throws IOException {
        FileReader file = new FileReader("src/main/java/it/epicode/U5W4BW/files/province-italiane.csv");
        List<Province> beans = new CsvToBeanBuilder<Province>(file)
                .withType(Province.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build().parse();
        if (provinceDAO != null) {
            for (Province province : beans) {
                Province newProvince = new Province(province.getName(), province.getAcronym(), province.getRegion());
                provinceSRV.save(newProvince);
            }
        }
        return beans;
    }

    @Bean
    public List<Municipality> parseMunicipalityCSV() throws IOException {
        FileReader file = new FileReader("src/main/java/it/epicode/U5W4BW/files/comuni-italiani.csv");
        List<Municipality> beans = new CsvToBeanBuilder<Municipality>(file)
                .withType(Municipality.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build().parse();

        int provinceSerialCounter = 1;
        if (municipalityDAO != null) {
            for (Municipality municipality : beans) {
                String name = String.valueOf(municipality.getTempProvince());
                String correctName = null;
                Province province = null;
                switch (name) {
                    case "Monza e della Brianza":
                        province = provinceDAO.findByName("Monza-Brianza");
                        correctName = "Monza-Brianza";
                        break;
                    case "Verbano-Cusio-Ossola":
                        province = provinceDAO.findByName("Verbania");
                        correctName = "Verbania";
                        break;
                    case "Valle d'Aosta/Vallée d'Aoste":
                        province = provinceDAO.findByName("Aosta");
                        correctName = "Aosta";
                        break;
                    case "Sud Sardegna":
                        province = provinceDAO.findByName("Carbonia Iglesias");
                        correctName = "Carbonia Iglesias";
                        break;
                    case "Vibo Valentia":
                        province = provinceDAO.findByName("Vibo-Valentia");
                        correctName = "Vibo-Valentia";
                        break;
                    case "Ascoli Piceno":
                        province = provinceDAO.findByName("Ascoli-Piceno");
                        correctName = "Ascoli-Piceno";
                        break;
                    case "Pesaro e Urbino":
                        province = provinceDAO.findByName("Pesaro-Urbino");
                        correctName = "Pesaro-Urbino";
                        break;
                    case "Forlì-Cesena":
                        province = provinceDAO.findByName("Forli-Cesena");
                        correctName = "Forli-Cesena";
                        break;
                    case "Reggio nell'Emilia":
                        province = provinceDAO.findByName("Reggio-Emilia");
                        correctName = "Reggio-Emilia";
                        break;
                    case "La Spezia":
                        province = provinceDAO.findByName("La-Spezia");
                        correctName = "La-Spezia";
                        break;
                    case "Bolzano/Bozen":
                        province = provinceDAO.findByName("Bolzano");
                        correctName = "Bolzano";
                        break;
                    case "Reggio Calabria":
                        province = provinceDAO.findByName("Reggio-Calabria");
                        correctName = "Reggio-Calabria";
                        break;
                    default:
                        province = provinceDAO.findByName(name);
                        correctName = name;
                }
                province.setName(correctName);
                provinceDAO.save(province);
                Province found = provinceDAO.findByName(correctName);
                if (name.equals("Sassari")) {
                    String stringComp = "00" + provinceSerialCounter;
                    if (stringComp.length() > 3) {
                        stringComp = stringComp.substring(1);
                    }
                    municipality.setProvinceSerial(stringComp);
                    provinceSerialCounter++;
                }

                if (found != null) {
                    Municipality newMunicipality = new Municipality(municipality.getProvinceCode(), municipality.getProvinceSerial(), municipality.getName());
                    newMunicipality.setProvince(found);
                    municipalitySRV.save(newMunicipality);
                } else {
                    System.out.println("Province not found for municipality: " + municipality.getName());
                }
            }
        }
        provinceSRV.renameProvince("Carbonia Iglesias", "Sud Sardegna");
        provinceSRV.renameAcronym("Roma", "RM");
        provinceSRV.renameAcronym("CI", "SU");
        provinceSRV.findByNameAndDelete("Ogliastra");
        provinceSRV.findByNameAndDelete("Medio Campidano");
        return beans;
    }

    public void read(FileReader file) throws FileNotFoundException {
        try (CSVReader csvReader = new CSVReader(file)) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                System.out.println(Arrays.asList(values));
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }


    }
}
