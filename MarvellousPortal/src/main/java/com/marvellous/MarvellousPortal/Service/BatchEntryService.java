package com.marvellous.MarvellousPortal.Service;

import com.marvellous.MarvellousPortal.Entity.BatchEntry;
import com.marvellous.MarvellousPortal.Repository.BatchEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BatchEntryService
{
    @Autowired
    private BatchEntryRepository batchEntryRepository;

    //C : Create
    //POST : HTTP VERB
    public void saveEntry(BatchEntry batchEntry)
    {
        batchEntryRepository.save(batchEntry);
    }

    //R:READ
    //GET

    public List <BatchEntry> getAll()
    {
        return batchEntryRepository.findAll();
    }


    //R:READ
    //GET
    public Optional <BatchEntry> findById(ObjectId id)
    {
        return batchEntryRepository.findById(id);
    }


    //D:Delete
    //DELETE
    public void deleteById(ObjectId id)
    {
        batchEntryRepository.deleteById(id);
    }
}
