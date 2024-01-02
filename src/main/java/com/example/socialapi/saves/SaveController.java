package com.example.socialapi.saves;

import com.example.socialapi.saves.dto.SaveDTO;
import com.example.socialapi.saves.request.SaveReq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saves")
@AllArgsConstructor
@Tag(name = "Save")
@SecurityRequirement(name = "bearerAuth")
public class SaveController {
    private final SaveService service;
    private static final Logger logging = LoggerFactory.getLogger(SaveController.class);

    /* create entity */
    @PostMapping(value = "/create")
    public ResponseEntity<SaveDTO> createEntity(@RequestBody SaveReq req) {
        try {
            logging.debug("create save entity");
            return ResponseEntity.ok(service.createEntityService(req));
        } catch (Exception e) {
            logging.error("Internal error cannot save post");
            return new ResponseEntity("Internal error cannot save post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* get saves by postId */
    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<SaveDTO>> fetchAllByPostId(@PathVariable String postId) {
        try {
            logging.debug("get all saved posts");
            return ResponseEntity.ok(service.getAllByPostId(new ObjectId(postId)));
        } catch (Exception e) {
            logging.error("Internal error cannot get saved posts");
            return new ResponseEntity("Internal error cannot get saved posts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* delete entity by postId, userId */
    @DeleteMapping(value = "/p/{postId}/u/{userId}/delete")
    public ResponseEntity<String> deleteEntity(@PathVariable String postId, @PathVariable String userId) {
        try {
            logging.debug("deleting saved post");
            return ResponseEntity.ok(service.deleteEntityService(new ObjectId(postId),new ObjectId(userId)));
        } catch (Exception e) {
            logging.error("Internal error cannot delete saved post");
            return new ResponseEntity("Internal error cannot delete saved post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
