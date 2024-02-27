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


        if (provinceDAO == null) {
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

        if (municipalityDAO == null) {
            for (Municipality municipality : beans) {
                Municipality newMunicipality = new Municipality();
                municipalitySRV.save(newMunicipality);
            }
        }
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
