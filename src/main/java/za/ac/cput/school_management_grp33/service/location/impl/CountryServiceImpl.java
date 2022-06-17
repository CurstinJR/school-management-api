package za.ac.cput.school_management_grp33.service.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;
import za.ac.cput.school_management_grp33.service.location.CountryService;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(Country newCountry) {
        String Id = newCountry.getId();
        return findById(Id).map(country -> {
            String id = country.getId();
            String name = newCountry.getName();
            country = CountryFactory.build(id, name);
            return countryRepository.save(country);
        }).orElseGet(() -> {
            String id = newCountry.getId();
            String name = newCountry.getName();
            return countryRepository.save(CountryFactory.build(id, name));
        });
    }
    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    public Optional<Country> findById(String id) {
        return countryRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        countryRepository.deleteById(id);
    }

}
