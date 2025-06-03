package dev.destiny.afrorusbackend.service;

import dev.destiny.afrorusbackend.model.Space;
import dev.destiny.afrorusbackend.model.SpaceType;
import dev.destiny.afrorusbackend.repository.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceService {
    @Autowired
    SpaceRepo spaceRepo;


    public List<Space> getAllSpaces(){
        return spaceRepo.findAll();
    }

    public List<Space> getAllFreeSpaces() {
        List<Space> result = new ArrayList<>();
        result.addAll(spaceRepo.findSpacesByType(SpaceType.LOAD_AVAILABLE));
        result.addAll(spaceRepo.findSpacesByType(SpaceType.DOCUMENT_AVAILABLE));
        return result;
    }

    public List<Space> getAllAvailableSpacesForDocs() {
      return  spaceRepo.findSpacesByType(SpaceType.DOCUMENT_AVAILABLE);
    }

    public List<Space> getAllAvailableSpacesForLoad() {
        return  spaceRepo.findSpacesByType(SpaceType.LOAD_AVAILABLE);
    }

    public List<Space> getAllNeededSpaces() {
        List<Space> result = new ArrayList<>();
        result.addAll(spaceRepo.findSpacesByType(SpaceType.LOAD_NEEDED));
        result.addAll(spaceRepo.findSpacesByType(SpaceType.DOCUMENT_NEEDED));
        return result;
    }

    public List<Space> getAllNeededSpacesForDocs() {
        return  spaceRepo.findSpacesByType(SpaceType.DOCUMENT_NEEDED);

    }

    public List<Space> getAllNeededSpacesForLoad() {
        return  spaceRepo.findSpacesByType(SpaceType.LOAD_NEEDED);

    }

    public String addSpace(Space space){
       var s =  spaceRepo.save(space);
       return "Added space "+s.toString();
    }


}
