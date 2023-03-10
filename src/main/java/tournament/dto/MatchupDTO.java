package tournament.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchupDTO {
    private Long id;
    private TeamDTO team1;
    private TeamDTO team2;
    private TeamDTO winner;
    private TeamDTO loser;
    private boolean hasEnded;
    private int round;
}
