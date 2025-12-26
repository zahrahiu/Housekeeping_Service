package org.example.housekeepingservice.Controller;

import org.example.housekeepingservice.dto.RequestHousekeepingTaskDTO;
import org.example.housekeepingservice.dto.ResponseHousekeepingTaskDTO;
import org.example.housekeepingservice.Service.HousekeepingService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/v1/housekeeping")
@Tag(name = "Housekeeping", description = "API pour gérer les tâches de ménage")
public class HousekeepingController {

    private final HousekeepingService service;

    public HousekeepingController(HousekeepingService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Créer une tâche de ménage", description = "Crée une nouvelle tâche de ménage avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tâche créée avec succès",
                    content = @Content(schema = @Schema(implementation = ResponseHousekeepingTaskDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
    })
    public ResponseHousekeepingTaskDTO createTask(
            @RequestBody(description = "Tâche de ménage à créer", required = true,
                    content = @Content(schema = @Schema(implementation = RequestHousekeepingTaskDTO.class)))
            @org.springframework.web.bind.annotation.RequestBody RequestHousekeepingTaskDTO dto) {
        return service.createTask(dto);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les tâches", description = "Retourne la liste de toutes les tâches de ménage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(schema = @Schema(implementation = ResponseHousekeepingTaskDTO.class))),
    })
    public List<ResponseHousekeepingTaskDTO> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Lister les tâches par statut", description = "Retourne toutes les tâches de ménage filtrées par leur statut")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tâches récupérées avec succès",
                    content = @Content(schema = @Schema(implementation = ResponseHousekeepingTaskDTO.class))),
            @ApiResponse(responseCode = "404", description = "Aucune tâche trouvée avec ce statut", content = @Content)
    })
    public List<ResponseHousekeepingTaskDTO> getByStatus(
            @PathVariable String status) {
        return service.getTasksByStatus(status);
    }
}
