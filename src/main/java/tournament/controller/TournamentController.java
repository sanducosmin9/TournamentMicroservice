package tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tournament.model.Tournament;
import tournament.service.TournamentService;

@RestController
@RequestMapping("/tournament")
@RequiredArgsConstructor
@CrossOrigin
public class TournamentController {

    private final TournamentService tournamentService;

    // post create tournament -> returns id

    // put update tournament by id

    // get tournament by id

    // get tournament by owner id

    @GetMapping
    public ResponseEntity<Tournament> something() {
        return ResponseEntity.ok(tournamentService.createTournament());
    }

}
