import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.service.AnatomicalStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/anatomical-structures")
public class AnatomicalStructureController {

    private final AnatomicalStructureService structureService;
    private final AnatomicalStructureMapper structureMapper;

    @Autowired
    public AnatomicalStructureController(AnatomicalStructureService structureService, AnatomicalStructureMapper structureMapper) {
        this.structureService = structureService;
        this.structureMapper = structureMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AnatomicalStructureDTO> createStructure(@RequestBody AnatomicalStructureDTO structureDTO) {
        AnatomicalStructureDTO createdStructure = structureService.createStructure(structureDTO);
        return ResponseEntity.ok(createdStructure);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AnatomicalStructureDTO> getStructureById(@PathVariable UUID id) {
        AnatomicalStructureDTO structureDTO = structureService.getStructureById(id);
        if (structureDTO != null) {
            return ResponseEntity.ok(structureDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnatomicalStructureDTO> updateStructure(@PathVariable UUID id, @RequestBody AnatomicalStructureDTO structureDTO) {
        AnatomicalStructureDTO updatedStructure = structureService.updateStructure(id, structureDTO);
        if (updatedStructure != null) {
            return ResponseEntity.ok(updatedStructure);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable UUID id) {
        boolean deleted = structureService.deleteStructure(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}