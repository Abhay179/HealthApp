package com.HealthApp.HealthApp.Provider;

import com.HealthApp.HealthApp.PateintsDTO;
import com.HealthApp.HealthApp.providerDTO;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProviderServices {
    @Autowired
    private ProviderRepository providerRepository;

    public List<ProviderEntity> getAll(){
        return providerRepository.findAll();
    }

    public ProviderEntity getById(ObjectId id){
        return  providerRepository.findById(id).orElseThrow(()->new RuntimeException("ID DOES NOT EXISTS"+id));
    }

    public Boolean createProvider(providerDTO data){
        ProviderEntity provider=new ProviderEntity();
        ModelMapper modelMapper=new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(data,provider);

        provider.setCreatedDate(LocalDateTime.now());
        provider.setUpdatedDate(LocalDateTime.now());
        providerRepository.save(provider);
        return true;
    }
//
    public boolean deleteById(ObjectId id){
        ProviderEntity provider=providerRepository.findById(id).orElse(null);
        if(provider!=null){
            providerRepository.deleteById(id);
            return true;
        }
        return false;
    }
//
    public ProviderEntity updateById(ObjectId id , providerDTO updates){
        ProviderEntity old=providerRepository.findById(id).orElseThrow(()->new RuntimeException("USER DOES NOT EXISTS IN DATABASE"+id));

        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.map(updates,old);


        old.setUpdatedDate(LocalDateTime.now());
        providerRepository.save(old);


        return old;
    }
}
