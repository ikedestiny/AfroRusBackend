package dev.destiny.afrorusbackend.controller;

import dev.destiny.afrorusbackend.dto.SpaceAvailableForDocDto;
import dev.destiny.afrorusbackend.dto.SpaceAvailableForLoadDto;
import dev.destiny.afrorusbackend.dto.SpaceNeedeForDocDto;
import dev.destiny.afrorusbackend.dto.SpaceNeededForLoadDto;
import dev.destiny.afrorusbackend.model.Space;
import dev.destiny.afrorusbackend.service.SpaceService;
import dev.destiny.afrorusbackend.utils.SpaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/space")
public class SpaceController {
    @Autowired
    SpaceService spaceService;

    @PostMapping("/doc-space-available")
    public ResponseEntity<?> createDocumentSpaceAvailable(@RequestBody SpaceAvailableForDocDto request) {
        Space space = SpaceMapper.from(request);
        return ResponseEntity.ok(spaceService.addSpace(space));
    }

    @PostMapping("/doc-space-needed")
    public ResponseEntity<?> createDocumentSpaceNeeded(@RequestBody SpaceNeedeForDocDto request) {
        Space space = SpaceMapper.from(request);
        return ResponseEntity.ok(spaceService.addSpace(space));
    }

    @PostMapping("/load-space-needed")
    public ResponseEntity<?> createLoadSpaceNeeded(@RequestBody SpaceNeededForLoadDto request) {
        Space space = SpaceMapper.from(request);
        return ResponseEntity.ok(spaceService.addSpace(space));
    }

    @PostMapping("/load-space-available")
    public ResponseEntity<?> createLoadSpaceAvailable(@RequestBody SpaceAvailableForLoadDto request) {
        Space space = SpaceMapper.from(request);
        return ResponseEntity.ok(spaceService.addSpace(space));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllSpaces(){
        return ResponseEntity.ok(spaceService.getAllSpaces());
    }

}
